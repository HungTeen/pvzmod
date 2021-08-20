package com.hungteen.pvz.common.entity.plant.defence;

import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class PumpkinEntity extends PVZPlantEntity{

	public PumpkinEntity(EntityType<? extends CreatureEntity> p_i48575_1_, World p_i48575_2_) {
		super(p_i48575_1_, p_i48575_2_);
	}

	@Override
	public PlantType getPlantType() {
		return PVZPlants.PUMPKIN;
	}

	@Override
	public int getSuperTimeLength() {
		return 0;
	}


}
