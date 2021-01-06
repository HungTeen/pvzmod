package com.hungteen.pvz.item.tool.card;

import java.util.function.Consumer;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.capability.player.PlayerDataManager;
import com.hungteen.pvz.enchantment.EnchantmentUtil;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.event.events.SummonCardUseEvent;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.EnchantmentRegister;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
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

	public final Plants plantType;

	public PlantCardItem(Plants plant, boolean isFragment) {
		super(isFragment);
		this.plantType = plant;
	}

	@Override
	public int getItemEnchantability() {
		Ranks rank = PlantUtil.getPlantRankByName(plantType);
		return 18 - rank.ordinal();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand handIn) {
		ItemStack stack = player.getHeldItem(handIn);
		if(! this.plantType.isWaterPlant || this.plantType == Plants.CAT_TAIL) {
			return ActionResult.resultPass(stack);
		}
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
				checkSunAndSummonPlant(player, stack, this, pos, (l)->{});
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
		if (world.isRemote) {
			return ActionResultType.SUCCESS;
		}
		if (plantType.isOuterPlant || plantType.isUpgradePlant) {//need place on entity.
			if(plantType == Plants.CAT_TAIL && world.getBlockState(pos).getBlock() == BlockRegister.LILY_PAD.get()) {
				checkSunAndSummonPlant(player, stack, this, pos.down(), (l)->{
					world.setBlockState(pos, Blocks.AIR.getDefaultState());
				});
				return ActionResultType.SUCCESS;
			}
			return ActionResultType.FAIL;
		}
		if (plantType.isWaterPlant) {// can only plant in water.
			return ActionResultType.PASS;
		}
		BlockPos spawnPos = pos;
		if(! world.getBlockState(pos).getCollisionShape(world, pos).isEmpty()) {
			spawnPos = pos.offset(context.getFace());
		}
		if (context.getFace() == Direction.UP && world.isAirBlock(pos.up())) {// can plant here
			checkSunAndSummonPlant(player, stack, this, spawnPos, (l)->{});
		}
		return ActionResultType.SUCCESS;
	}

	/**
	 * check sunCost and spawn plantEntity.
	 */
	public static void checkSunAndSummonPlant(PlayerEntity player, ItemStack stack, PlantCardItem cardItem, BlockPos pos, Consumer<PVZPlantEntity> consumer) {
		if(player.getCooldownTracker().hasCooldown(cardItem)) return ;
		Plants plantType = cardItem.plantType;
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
			PlayerDataManager manager = l.getPlayerData();
			int num = manager.getPlayerStats().getPlayerStats(Resources.SUN_NUM);
			int sunCost = SummonCardItem.getItemStackSunCost(stack);
			if (num >= sunCost) { // sun is enough
				EntityType<? extends PVZPlantEntity> entityType = PlantUtil.getPlantEntityType(plantType);
				PVZPlantEntity plantEntity = (PVZPlantEntity) entityType.spawn(player.world, stack, player, pos, SpawnReason.SPAWN_EGG, true, true);
				if (plantEntity == null) {
					PVZMod.LOGGER.debug("no such plant");
					return;
				}
				l.getPlayerData().getPlayerStats().addPlayerStats(Resources.SUN_NUM, - sunCost);
				int lvl = manager.getPlantStats().getPlantLevel(plantType);
				plantEntity.onSpawnedByPlayer(player, lvl);//update level health and owner.
				PlantCardItem.onUsePlantCard(player, stack, cardItem, lvl);//handle CD.
				if (canPlantBreakOut(stack)) {// break out enchantment
					if (plantEntity.canStartSuperMode()) {
						plantEntity.startSuperMode(false);
					}
				}
				if(EnchantmentHelper.getEnchantmentLevel(EnchantmentRegister.CHARM.get(), stack) > 0) {
					plantEntity.onPlantBeCharmed();
				}
				consumer.accept(plantEntity);
			}
		});
	}

	/**
	 * check sunCost and place plantBlock.
	 */
	public static void checkSunAndPlaceBlock(PlayerEntity player, BlockPlantCardItem cardItem, ItemStack stack, BlockPos pos) {
		if(player.getCooldownTracker().hasCooldown(cardItem)) return ;
		Plants plantType = cardItem.plantType;
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
			PlayerDataManager manager = l.getPlayerData();
			int num = manager.getPlayerStats().getPlayerStats(Resources.SUN_NUM);
			int sunCost = SummonCardItem.getItemStackSunCost(stack);
			if (num >= sunCost) { // sun is enough
				l.getPlayerData().getPlayerStats().addPlayerStats(Resources.SUN_NUM, - sunCost);
				onUsePlantCard(player, stack, cardItem, manager.getPlantStats().getPlantLevel(plantType));
				BlockState state = BlockPlantCardItem.getBlockState(player, plantType);
			    if(state == null) return;
				player.world.setBlockState(pos, state, 11);
			    if (player instanceof ServerPlayerEntity) {
			        CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) player, pos, stack);
		        }
			}
		});
	}
	
	/**
	 * check sunCost and add outerplant for plantEntity
	 */
	public static void checkSunAndOuterPlant(PlayerEntity player, PVZPlantEntity plantEntity, PlantCardItem cardItem,
			ItemStack stack) {
		if(player.getCooldownTracker().hasCooldown(cardItem)) return ;
		Plants plantType = cardItem.plantType;
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
			PlayerDataManager manager = l.getPlayerData();
			int num = manager.getPlayerStats().getPlayerStats(Resources.SUN_NUM);
			int sunCost = SummonCardItem.getItemStackSunCost(stack);
			if (num >= sunCost) { // sun is enough
				l.getPlayerData().getPlayerStats().addPlayerStats(Resources.SUN_NUM, - sunCost);
				onUsePlantCard(player, stack, cardItem, manager.getPlantStats().getPlantLevel(plantType));
				if(plantType == Plants.PUMPKIN) {
					float life = PlantUtil.PUMPKIN_LIFE;
				    if (canPlantBreakOut(stack)) {// break out enchantment
					    life += PlantUtil.PUMPKIN_SUPER_LIFE;
				    }
				    plantEntity.setPumpkinLife(life);
				    plantEntity.setOuterPlantType(Plants.PUMPKIN);
				}
			}
		});
	}
	
	/**
	 * check sunCost and heal defender plantEntity.
	 */
	public static void checkSunAndHealPlant(PlayerEntity player, PVZPlantEntity plantEntity, PlantCardItem cardItem,
			ItemStack stack) {
		if(player.getCooldownTracker().hasCooldown(cardItem)) return ;
		Plants plantType = cardItem.plantType;
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
			PlayerDataManager manager = l.getPlayerData();
			int num = manager.getPlayerStats().getPlayerStats(Resources.SUN_NUM);
			int sunCost = SummonCardItem.getItemStackSunCost(stack);
			if (num >= sunCost) { // sun is enough
				l.getPlayerData().getPlayerStats().addPlayerStats(Resources.SUN_NUM, - sunCost);
				onUsePlantCard(player, stack, cardItem, manager.getPlantStats().getPlantLevel(plantType));
				plantEntity.heal(plantEntity.getMaxHealth());
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
    
	/**
	 * set player-item cool down
	 */
	public static void handlePlantCardCoolDown(PlayerEntity player, ItemStack stack, PlantCardItem item, int plantLvl) {
		final int cd = getPlantCardCD(player, stack, item, plantLvl);
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
			l.getPlayerData().getItemCDStats().setPlantCardCD(item.plantType, cd);
		});
		player.getCooldownTracker().setCooldown(stack.getItem(), cd);
	}
	
	/**
	 * get cooldown for current plant
	 */
	public static int getPlantCardCD(PlayerEntity player, ItemStack stack, PlantCardItem item, int plantLvl) {
		int cd = PlantUtil.getPlantCoolDownTime(item.plantType, plantLvl);
		if (player.isPotionActive(EffectRegister.EXCITE_EFFECT.get())) {
			int lvl = player.getActivePotionEffect(EffectRegister.EXCITE_EFFECT.get()).getAmplifier();
			float mult = Math.max(0, 0.9f - 0.1f * lvl);
			cd = (int) Math.floor(cd * mult);
		}
		return cd;
	}

	public static boolean canPlantBreakOut(ItemStack stack) {
		return random.nextInt(100) < EnchantmentUtil.getPlantBreakOutChance(stack);
	}
	
}
