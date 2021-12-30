package com.hungteen.pvz.common.entity.plant.light;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.impl.plant.PVZPlants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class TwinSunFlowerEntity extends SunFlowerEntity{

	public TwinSunFlowerEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void genSomething() {
		for(int i = 0; i < 2; ++ i) {
			this.genSun(this.getSunAmount());
		}
	}

	@Override
	public int getSuperSunAmount() {
		return 750;
//		return this.getThreeStage(750, 1000, 1250);
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.9f, 1.85f);
	}
	
	@Override
	public IPlantType getPlantType() {
		return PVZPlants.TWIN_SUNFLOWER;
	}
	
}
