package com.hungteen.pvz.item.tool.card;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.capabilities.player.PlayerDataManager;
import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.event.events.SummonCardUseEvent;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.EnchantmentRegister;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.enchantment.EnchantmentHelper;
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

public class PlantCardItem extends SummonCardItem{

	protected final Plants plant;
	
	public PlantCardItem(Plants plant,boolean isFragment) {
		super(isFragment);
		this.plant = plant;
	}
	
	@Override
	public int getItemEnchantability() {
		Ranks rank = PlantUtil.getPlantRankByName(plant);
		return 18-rank.ordinal();
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
		Hand hand = context.getHand();
		PlayerEntity player = context.getPlayer();
		ItemStack stack=player.getHeldItem(hand);
		BlockPos pos = context.getPos();
		World world = context.getWorld();
		if(hand==Hand.OFF_HAND) {//only use right hand can plant 
			return ActionResultType.FAIL;
		}
		for(Plants p:PlantUtil.WATER_PLANTS) {
			if(this.plant==p) return ActionResultType.PASS;
		}
		if(!world.isRemote&&context.getFace()==Direction.UP&&world.isAirBlock(pos.up())) {//can plant here
			checkSunAndPlant(world, player, stack, pos);
		}
		return ActionResultType.SUCCESS;
	}
	
	protected void checkSunAndPlant(World world, PlayerEntity player, ItemStack stack, BlockPos pos) {
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
			PlayerDataManager manager = l.getPlayerData();
			int num=manager.getPlayerStats().getPlayerStats(Resources.SUN_NUM);
			int sunCost=getSunCost(stack);
			if(num>=sunCost) {//sun is enough
				PVZPlantEntity plantEntity = PlantUtil.getPlantEntity(world, this.plant);
			    if(plantEntity==null) {//no such plant
			    	PVZMod.LOGGER.debug("no such plant");
				    return;
			    }
			    MinecraftForge.EVENT_BUS.post(new SummonCardUseEvent(player, stack));
				l.getPlayerData().getPlayerStats().addPlayerStats(Resources.SUN_NUM, -sunCost);
				int lvl=manager.getPlantStats().getPlantLevel(plant);
				if(this.isEnjoyCard) {
					lvl=1;
					stack.shrink(1);
				}else {
					
				}
				player.getCooldownTracker().setCooldown(stack.getItem(), PlantUtil.getPlantCoolDownTime(plant, lvl));
				plantEntity.setPlantLvl(lvl);
				plantEntity.setOwnerUUID(player.getUniqueID());
				double dy = 1;
				if(world.getBlockState(pos).getBlock()==BlockRegister.LILY_PAD.get()) dy=0.1d;
				plantEntity.setPosition(pos.getX()+0.5D,pos.getY()+dy,pos.getZ()+0.5D);
//				if(EnchantmentHelper.getEnchantmentLevel(En, stack)) {// hypno
//					
//				}
				plantEntity.onInitialSpawn(world, world.getDifficultyForLocation(pos), SpawnReason.SPAWN_EGG, null, null);
				world.addEntity(plantEntity);
				if(this.canPlantBreakOut(stack)) {
					if(plantEntity.canStartSuperMode()) {
						plantEntity.startSuperMode(false);
					}
				}
				player.addStat(Stats.ITEM_USED.get(this));
			}
		});
	}
	
	protected boolean canPlantBreakOut(ItemStack stack) {
		int lvl=EnchantmentHelper.getEnchantmentLevel(EnchantmentRegister.BREAK_OUT.get(), stack);
		int num=lvl*3+7;
		if(lvl==4) num++;
		else if(lvl==0) num=0;
		return random.nextInt(100)<num;
	}
	
	@Override
	protected int getSunCost(ItemStack stack) {
		int cost=PlantUtil.getPlantSunCost(plant);
		return Math.max(cost-getSunReduceNum(stack), 0);
	}
	
	public Plants getPlant() {
		return this.plant;
	}
	
}
