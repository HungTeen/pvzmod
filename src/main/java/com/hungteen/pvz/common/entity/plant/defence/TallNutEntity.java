package com.hungteen.pvz.common.entity.plant.defence;

import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class TallNutEntity extends WallNutEntity{

	public TallNutEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.damageMultiple = 0.5F;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.9f, 1.9f, false);
	}
	
	@Override
	public float getAttractRange() {
		return 3.5F;
	}
	
	@Override
	public Plants getUpgradePlantType() {
		return null;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.TALL_NUT;
	}
	
}
