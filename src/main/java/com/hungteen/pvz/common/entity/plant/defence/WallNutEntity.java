package com.hungteen.pvz.common.entity.plant.defence;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.base.PlantDefenderEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class WallNutEntity extends PlantDefenderEntity{

	public WallNutEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public float getLife() {
		return 400;
//		return MathUtil.getProgressAverage(this.getSkills(), PlantUtil.MAX_PLANT_LEVEL, 400, 800);
	}
	
	@Override
	public float getSuperLife() {
//		return this.isPlantInStage(1) ? 500 : this.isPlantInStage(2) ? 750 : 1000;
		return 100;
	}


	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8f, 1.3f);
	}
	
	@Override
	public IPlantType getPlantType() {
		return PVZPlants.WALL_NUT;
	}

}
