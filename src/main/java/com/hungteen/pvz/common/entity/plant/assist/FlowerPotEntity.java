package com.hungteen.pvz.common.entity.plant.assist;

import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class FlowerPotEntity extends PVZPlantEntity{

	public FlowerPotEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public PlantType getPlantType() {
		return PVZPlants.FLOWER_POT;
	}

	@Override
	public int getSuperTimeLength() {
		return 0;
	}

}
