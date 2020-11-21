package com.hungteen.pvz.entity.plant.light;

import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class TwinSunFlowerEntity extends SunFlowerEntity{

	public TwinSunFlowerEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.isUpgradePlant = true;
	}

	@Override
	public void genSomething() {
		for(int i = 0; i < 2; ++ i) {
			this.genSun(this.getSunAmount());
		}
	}

	@Override
	public void genSuper() {
		int ge = 4;
		int amount = this.isPlantInStage(1) ? 50 : this.isPlantInStage(2) ? 60 : 75;
		for(int i = 1; i <= ge; ++ i) {
		    this.genSun(amount);
		}
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.9f, 1.85f);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.TWIN_SUNFLOWER;
	}
	
	@Override
	public Plants getUpgradePlantType() {
		return null;
	}
	
}
