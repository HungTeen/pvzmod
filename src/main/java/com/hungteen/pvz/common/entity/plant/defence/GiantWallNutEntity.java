package com.hungteen.pvz.common.entity.plant.defence;

import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.impl.plant.PVZPlants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class GiantWallNutEntity extends TallNutEntity {

	public GiantWallNutEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.damageMultiple = 0.25F;
	}
	
	@Override
	public float getAttractRange() {
		return 5;
	}
	
	@Override
	public float getPlantHealth() {
		return this.getAverageProgress(500F, 1000F);
	}
	
	@Override
	public boolean canPlaceOuterPlant() {
		return false;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(2F, 3F);
	}
	
	@Override
	public int getSuperTimeLength() {
		return 30;
	}
	
	@Override
	public PlantType getPlantType() {
		return PVZPlants.GIANT_WALL_NUT;
	}

}
