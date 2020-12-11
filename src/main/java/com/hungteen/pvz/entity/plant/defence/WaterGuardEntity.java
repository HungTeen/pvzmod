package com.hungteen.pvz.entity.plant.defence;

import com.hungteen.pvz.entity.plant.base.PlantDefenderEntity;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.world.World;

public class WaterGuardEntity extends PlantDefenderEntity{

	public WaterGuardEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new SwimGoal(this));
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.8f, 0.8f, false);
	}
	
	@Override
	public float getPlantHealth() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) return 190 + lvl * 10;
		return 400;
	}
	
	@Override
	public float getSuperLife() {
		if(this.isPlantInStage(1)) return 200;
		if(this.isPlantInStage(2)) return 300;
		return 400;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 20;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.WATER_GUARD;
	}

}
