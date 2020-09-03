package com.hungteen.pvz.item.tool.card;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.capabilities.CapabilityHandler;
import com.hungteen.pvz.capabilities.player.PlayerDataManager;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.event.events.SummonCardUseEvent;
import com.hungteen.pvz.register.EnchantmentRegister;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.enchantment.Enchantment;
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
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		if(this.isEnjoyCard) return false;
		return super.canApplyAtEnchantingTable(stack, enchantment);
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
		if(plant == Plants.TANGLE_KELP) {
			return ActionResultType.PASS;
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
				PVZPlantEntity plantEntity = getPlantEntity(world);
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
				plantEntity.setPosition(pos.getX()+0.5D,pos.getY()+1D,pos.getZ()+0.5D);
//				if(EnchantmentHelper.getEnchantmentLevel(En, stack)) {// hypno
//					
//				}
				plantEntity.onInitialSpawn(world, world.getDifficultyForLocation(pos), SpawnReason.SPAWN_EGG, null, null);
				world.addEntity(plantEntity);
				if(this.canPlantBreakOut(stack)) {
					if(plantEntity.canStartSuperMode()) {
						plantEntity.startSuperMode();
					}
				}
				player.addStat(Stats.ITEM_USED.get(this));
			}
		});
	}
	
	public PVZPlantEntity getPlantEntity(World world){
		switch(this.plant) {
		case PEA_SHOOTER:return EntityRegister.PEA_SHOOTER.get().create(world);
		case SUN_FLOWER:return EntityRegister.SUN_FLOWER.get().create(world);
		case CHERRY_BOMB:return EntityRegister.CHERRY_BOMB.get().create(world);
		case WALL_NUT:return EntityRegister.WALL_NUT.get().create(world);
		case POTATO_MINE:return EntityRegister.POTATO_MINE.get().create(world);
		case SNOW_PEA:return EntityRegister.SNOW_PEA.get().create(world);
		case CHOMPER:return EntityRegister.CHOMPER.get().create(world);
		case REPEATER:return EntityRegister.REPEATER.get().create(world);
		case SQUASH:return EntityRegister.SQUASH.get().create(world);
		case THREE_PEATER:return EntityRegister.THREE_PEATER.get().create(world);
		case TANGLE_KELP:return EntityRegister.TANGLE_KELP.get().create(world);
		case JALAPENO:return EntityRegister.JALAPENO.get().create(world);
		case SPIKE_WEED:return EntityRegister.SPIKE_WEED.get().create(world);
		default:{
			PVZMod.LOGGER.debug("No such plant entity!");
			return null;
		}
		}
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
	
}
