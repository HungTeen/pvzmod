package com.hungteen.pvz.common.entity.plant.defence;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class PumpkinEntity extends PVZPlantEntity{

	protected PumpkinEntity(EntityType<? extends CreatureEntity> p_i48575_1_, World p_i48575_2_) {
		super(p_i48575_1_, p_i48575_2_);
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.PUMPKIN;
	}

	@Override
	public int getSuperTimeLength() {
		return 0;
	}


}
