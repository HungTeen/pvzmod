package com.hungteen.pvz.entity.plant.toxic;

import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.plant.interfaces.IShroomPlant;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class PuffShroomEntity extends PVZPlantEntity implements IShroomPlant{

	public PuffShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.PUFF_SHROOM;
	}

	@Override
	public int getSuperTimeLength() {
		return 30;
	}

}
