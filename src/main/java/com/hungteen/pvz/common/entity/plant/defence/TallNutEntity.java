package com.hungteen.pvz.common.entity.plant.defence;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.impl.plant.PVZPlants;

import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.EntitySize;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;

public class TallNutEntity extends WallNutEntity{

	public TallNutEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	@Override
	public float getSuperLife() {
		return 800;
	}

	@Override
	public int getArmor() {
		return 15;
	}

	@Override
	public int getArmorToughness() {
		return 10;
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
	public IPlantType getPlantType() {
		return PVZPlants.TALL_NUT;
	}
	
}
