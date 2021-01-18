package com.hungteen.pvz.entity.plant.defence;

import com.hungteen.pvz.entity.plant.base.PlantDefenderEntity;
import com.hungteen.pvz.item.tool.BowlingGloveItem;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class WallNutEntity extends PlantDefenderEntity{

	public WallNutEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if(stack.getItem() instanceof BowlingGloveItem) {
			if(! world.isRemote) {
				BowlingGloveItem.onPickUpBowlingPlant(this, stack);
			}
			return true;
		}
		return super.processInteract(player, hand);
	}

	@Override
	public float getPlantHealth() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) return 390 + 10 * lvl;
	    return 600;
	}
	
	@Override
	public float getSuperLife() {
		if(this.isPlantInStage(1)) return 400;
		if(this.isPlantInStage(2)) return 500;
		return 600;
	}

	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.8f, 1.3f);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.WALL_NUT;
	}

}
