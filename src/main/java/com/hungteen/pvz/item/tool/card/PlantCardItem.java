package com.hungteen.pvz.item.tool.card;

import com.hungteen.pvz.PVZEventHandler;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.capability.player.PlayerDataManager;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.event.events.SummonCardUseEvent;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.EnchantmentRegister;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class PlantCardItem extends SummonCardItem {

	protected final Plants plant;

	public PlantCardItem(Plants plant, boolean isFragment) {
		super(isFragment);
		this.plant = plant;
	}

	@Override
	public int getItemEnchantability() {
		Ranks rank = PlantUtil.getPlantRankByName(plant);
		return 18 - rank.ordinal();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand handIn) {
		ItemStack stack = player.getHeldItem(handIn);
		RayTraceResult raytraceresult = rayTrace(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
		if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
			return ActionResult.resultPass(stack);
		} else if (world.isRemote) {
			return ActionResult.resultSuccess(stack);
		} else {
			BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
			BlockPos pos = blockraytraceresult.getPos();
			if (!(world.getBlockState(pos).getBlock() instanceof FlowingFluidBlock)) {
				return ActionResult.resultPass(stack);
			}
			if (world.isBlockModifiable(player, pos) && player.canPlayerEdit(pos, blockraytraceresult.getFace(), stack)) {
				checkSunAndPlant(world, player, stack, pos);
				return ActionResult.resultSuccess(stack);
			} else {
				return ActionResult.resultFail(stack);
			}
		}
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		PlayerEntity player = context.getPlayer();
		Hand hand = context.getHand();
		ItemStack stack = player.getHeldItem(hand);
		BlockPos pos = context.getPos();
		if (hand == Hand.OFF_HAND) {// only use right hand to plant.
			return ActionResultType.FAIL;
		}
		if (Plants.isOuterPlant(this.plant) || Plants.isUpgradePlant(this.plant)) {// can not place on blocks, like pumpkin.
			return ActionResultType.FAIL;
		}
		if (Plants.isWaterPlant(this.plant)) {// can only plant in water.
			return ActionResultType.PASS;
		}
		if (world.isRemote) {
			return ActionResultType.SUCCESS;
		}
		BlockPos spawnPos = pos;
		if(!world.getBlockState(pos).getCollisionShape(world, pos).isEmpty()) {
			spawnPos = pos.offset(context.getFace());
		}
		if (context.getFace() == Direction.UP && world.isAirBlock(pos.up())) {// can plant here
			checkSunAndPlant(world, player, stack, spawnPos);
		}
		return ActionResultType.SUCCESS;
	}

	/**
	 * check sunCost and plant plantEntity
	 */
	protected void checkSunAndPlant(World world, PlayerEntity player, ItemStack stack, BlockPos pos) {
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
			PlayerDataManager manager = l.getPlayerData();
			int num = manager.getPlayerStats().getPlayerStats(Resources.SUN_NUM);
			int sunCost = getSunCost(stack);
			if (num >= sunCost) {// sun is enough
				EntityType<? extends PVZPlantEntity> entityType = PlantUtil.getPlantEntityType(plant);
				PVZPlantEntity plantEntity = (PVZPlantEntity) entityType.spawn(world, stack, player, pos, SpawnReason.SPAWN_EGG, true, true);
				if (plantEntity == null) {
					PVZMod.LOGGER.debug("no such plant");
					return;
				}
				l.getPlayerData().getPlayerStats().addPlayerStats(Resources.SUN_NUM, - sunCost);
				int lvl = manager.getPlantStats().getPlantLevel(plant);
				PVZEventHandler.onPlantEntitySummonByPlayer(player, plantEntity, this, stack, lvl);
				if (this.canPlantBreakOut(stack)) {// break out enchantment
					if (plantEntity.canStartSuperMode()) {
						plantEntity.startSuperMode(false);
					}
				}
			}
		});
	}

	public static void onUsePlantCard(PlayerEntity player, ItemStack stack, PlantCardItem item, int plantLvl) {
		MinecraftForge.EVENT_BUS.post(new SummonCardUseEvent(player, stack));
		if (item.isEnjoyCard) {
			stack.shrink(1);
		} else {
			handlePlantCardCoolDown(player, stack, item, plantLvl);
		}
		player.addStat(Stats.ITEM_USED.get(item));
	}

	public static void plantPumpkin(PlayerEntity player, PVZPlantEntity plantEntity, PlantCardItem item,
			ItemStack stack) {
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
			PlayerDataManager manager = l.getPlayerData();
			int num = manager.getPlayerStats().getPlayerStats(Resources.SUN_NUM);
			int sunCost = item.getSunCost(stack);
			if (num >= sunCost) {// sun is enough
				l.getPlayerData().getPlayerStats().addPlayerStats(Resources.SUN_NUM, -sunCost);
				onUsePlantCard(player, stack, item, manager.getPlantStats().getPlantLevel(item.getPlant()));
				float life = PlantUtil.PUMPKIN_LIFE;
				if (item.canPlantBreakOut(stack)) {// break out enchantment
					life += PlantUtil.PUMPKIN_SUPER_LIFE;
				}
				plantEntity.setPumpkinLife(life);
				plantEntity.setOuterPlantType(Plants.PUMPKIN);
			}
		});
	}

	/**
	 * set player-item cool down
	 */
	public static void handlePlantCardCoolDown(PlayerEntity player, ItemStack stack, PlantCardItem item, int plantLvl) {
		final int cd = getPlantCardCD(player, stack, item, plantLvl);
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
			l.getPlayerData().getItemCDStats().setPlantCardCD(item.getPlant(), cd);
		});
		player.getCooldownTracker().setCooldown(stack.getItem(), cd);
	}
	
	/**
	 * get cooldown for current plant
	 */
	public static int getPlantCardCD(PlayerEntity player, ItemStack stack, PlantCardItem item, int plantLvl) {
		int cd = PlantUtil.getPlantCoolDownTime(item.getPlant(), plantLvl);
		if (player.isPotionActive(EffectRegister.EXCITE_EFFECT.get())) {
			int lvl = player.getActivePotionEffect(EffectRegister.EXCITE_EFFECT.get()).getAmplifier();
			float mult = Math.max(0, 0.9f - 0.1f * lvl);
			cd = (int) Math.floor(cd * mult);
		}
		return cd;
	}

	public boolean canPlantBreakOut(ItemStack stack) {
		int lvl = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegister.BREAK_OUT.get(), stack);
		int num = lvl * 3 + 7;
		if (lvl == 4)
			num++;
		else if (lvl == 0)
			num = 0;
		return random.nextInt(100) < num;
	}

	@Override
	public int getSunCost(ItemStack stack) {
		int cost = PlantUtil.getPlantSunCost(plant);
		return Math.max(cost - getSunReduceNum(stack), 0);
	}

	public Plants getPlant() {
		return this.plant;
	}

}
