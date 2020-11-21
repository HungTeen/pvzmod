package com.hungteen.pvz.entity.plant.light;

import com.hungteen.pvz.entity.plant.base.PlantProducerEntity;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class SunFlowerEntity extends PlantProducerEntity{

	public SunFlowerEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void genSomething() {
		this.genSun(this.getSunAmount());
	}

	@Override
	public void genSuper() {
		int ge = this.isPlantInStage(2) ? 3 : 4;
		int amount = this.isPlantInStage(1) ? 35 : 50;
		for(int i = 1; i <= ge; ++ i) {
		    this.genSun(amount);
		}
	}

	public int getSunAmount(){
		if(this.isPlantInStage(1)) return 25;
		if(this.isPlantInStage(2)) return 35;
		return 50;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.8f, 1.65f);
	}
	
	@Override
	public int getGenCD() {
		int time = 500;
		return this.world.isDaytime() ? time : 4 * time;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.SUN_FLOWER;
	}
	
	@Override
	public Plants getUpgradePlantType() {
		return Plants.TWIN_SUNFLOWER;
	}

}
