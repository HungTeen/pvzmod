package com.hungteen.pvz.common.entity.plant.defence;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.base.PlantDefenderEntity;
import com.hungteen.pvz.common.impl.plant.CustomPlants;

import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.EntitySize;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.SwimGoal;
import net.minecraft.world.level.Level;

public class WaterGuardEntity extends PlantDefenderEntity{

	public WaterGuardEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new SwimGoal(this));
	}
	
	@Override
	public float getLife() {
		return 200;
	}
	
	@Override
	public float getSuperLife() {
		return 300;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 20;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.8f, 0.8f, false);
	}

	@Override
	public IPlantType getPlantType() {
		return CustomPlants.WATER_GUARD;
	}

}
