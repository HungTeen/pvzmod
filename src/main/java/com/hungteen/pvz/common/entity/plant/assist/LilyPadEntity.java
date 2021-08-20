package com.hungteen.pvz.common.entity.plant.assist;

import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class LilyPadEntity extends PVZPlantEntity{

	public LilyPadEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public PlantType getPlantType() {
		return PVZPlants.LILY_PAD;
	}

	@Override
	public int getSuperTimeLength() {
		return 0;
	}

}
