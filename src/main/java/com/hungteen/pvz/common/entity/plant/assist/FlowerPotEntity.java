package com.hungteen.pvz.common.entity.plant.assist;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class FlowerPotEntity extends PVZPlantEntity{

	public FlowerPotEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.FLOWER_POT;
	}

	@Override
	public int getSuperTimeLength() {
		return 0;
	}

}
