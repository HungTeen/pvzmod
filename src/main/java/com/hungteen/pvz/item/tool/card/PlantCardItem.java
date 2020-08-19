package com.hungteen.pvz.item.tool.card;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.capabilities.CapabilityHandler;
import com.hungteen.pvz.capabilities.player.PlayerDataManager;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlantCardItem extends SummonCardItem{

	protected final Plants plant;
	public PlantCardItem(Plants plant,boolean isFragment) {
		super(isFragment);
		this.plant = plant;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		if(this.isFragment) return false;
		return super.canApplyAtEnchantingTable(stack, enchantment);
	}
	
//	@Override
//	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
//		ItemStack stack=player.getHeldItem(hand);
//		if(hand==Hand.OFF_HAND) return ActionResult.resultPass(stack);
//		if(!player.world.isRemote) {
//			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
//				PlayerDataManager manager = l.getPlayerData();
//				int num=manager.getPlayerStats().getPlayerStats(Resources.SUN_NUM);
//				int sunCost=PlantUtil.getPlantSunCost(plant);
//				int reduce=this.getSunReduceNum(stack);
//				sunCost=Math.max(sunCost-reduce,0);
//				if(num>=sunCost) {//can plant 
//					l.getPlayerData().getPlayerStats().addPlayerStats(Resources.SUN_NUM, -sunCost);
//					int lvl=manager.getPlantStats().getPlantLevel(plant);
//					player.getCooldownTracker().setCooldown(stack.getItem(), PlantUtil.getPlantCoolDownTime(plant, lvl));
//					world.playSound(x, y, z, soundIn, category, volume, pitch, distanceDelay);
//				}
//			});
//		}
//		return ActionResult.resultSuccess(stack);
//	}
	
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
		if(!world.isRemote&&context.getFace()==Direction.UP&&world.isAirBlock(pos.up())) {//can plant here
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
				PlayerDataManager manager = l.getPlayerData();
				int num=manager.getPlayerStats().getPlayerStats(Resources.SUN_NUM);
				PVZPlantEntity plantEntity = getPlantEntity(world);
				if(plantEntity==null) {//no such plant
					return;
				}
				int sunCost=getSunCost(stack);
				if(num>=sunCost) {//sun is enough
					l.getPlayerData().getPlayerStats().addPlayerStats(Resources.SUN_NUM, -sunCost);
					int lvl=manager.getPlantStats().getPlantLevel(plant);
					if(this.isFragment) {
						stack.shrink(1);
					}
					player.getCooldownTracker().setCooldown(stack.getItem(), PlantUtil.getPlantCoolDownTime(plant, lvl));
					plantEntity.setPlantLvl(lvl);
					plantEntity.setOwnerUUID(player.getUniqueID());
					plantEntity.setPosition(pos.getX()+0.5D,pos.getY()+1D,pos.getZ()+0.5D);
//					if(EnchantmentHelper.getEnchantmentLevel(En, stack)) {// hypno
//						
//					}
					plantEntity.onInitialSpawn(world, world.getDifficultyForLocation(pos), SpawnReason.SPAWN_EGG, null, null);
					world.addEntity(plantEntity);
					
				}
			});
		}
		return ActionResultType.SUCCESS;
	}
	
	public PVZPlantEntity getPlantEntity(World world){
		switch(this.plant) {
		case PEA_SHOOTER:return EntityRegister.PEA_SHOOTER.get().create(world);
		case SUN_FLOWER:return EntityRegister.SUN_FLOWER.get().create(world);
		case CHERRY_BOMB:return EntityRegister.CHERRY_BOMB.get().create(world);
		case WALL_NUT:return EntityRegister.WALL_NUT.get().create(world);
		case POTATO_MINE:return EntityRegister.POTATO_MINE.get().create(world);
		case SNOW_PEA:return EntityRegister.SNOW_PEA.get().create(world);
		case REPEATER:return EntityRegister.REPEATER.get().create(world);
		default:{
			PVZMod.LOGGER.debug("No such plant entity!");
			return null;
		}
		}
	}
	
	@Override
	protected int getSunCost(ItemStack stack) {
		int cost=PlantUtil.getPlantSunCost(plant);
		return Math.max(cost-getSunReduceNum(stack), 0);
	}
}
