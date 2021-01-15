package com.hungteen.pvz.item.tool;

import java.util.Optional;

import com.hungteen.pvz.entity.misc.AbstractBowlingEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.GroupRegister;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BowlingGloveItem extends Item {

	private Optional<Plants> plantType;
	
	public BowlingGloveItem() {
		super(new Item.Properties().group(GroupRegister.PVZ_MISC).maxStackSize(1));
		this.setPlantType(Plants.WALL_NUT);
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		PlayerEntity player = context.getPlayer();
		Hand hand = context.getHand();
		ItemStack stack = player.getHeldItem(hand);
		BlockPos pos = context.getPos();
		if(! this.plantType.isPresent()) return ActionResultType.FAIL;
		Plants plant = this.plantType.get();
		BlockPos spawnPos = pos;
		if(! world.getBlockState(pos).getCollisionShape(world, pos).isEmpty()) {
			spawnPos = pos.offset(context.getFace());
		}
		if (context.getFace() == Direction.UP && world.isAirBlock(pos.up())) {// can plant here
			EntityType<? extends AbstractBowlingEntity> entityType = getEntityTypeByPlant(plant);
			if(entityType == null) {
				System.out.println("Error : no such bowling entity !");
				return ActionResultType.FAIL;
			}
			AbstractBowlingEntity entity = (AbstractBowlingEntity) entityType.spawn(player.world, stack, player, spawnPos, SpawnReason.SPAWN_EGG, true, true);
			if(entity == null) {
				System.out.println("Error : bowling entity spawn error!");
				return ActionResultType.FAIL;
			}
			entity.setThrower(player);
			entity.shoot(player);
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.FAIL;
	}
	
	private static EntityType<? extends AbstractBowlingEntity> getEntityTypeByPlant(Plants plant){
		if(plant == Plants.WALL_NUT) return EntityRegister.WALL_NUT_BOWLING.get();
		return null;
	}
	
	public Optional<Plants> getPlantType() {
		return plantType;
	}

	public void setPlantType(Plants plantType) {
		this.plantType = Optional.of(plantType);
	}
	

}
