package com.hungteen.pvz.entity.plant.explosion;

import com.hungteen.pvz.entity.plant.defence.WallNutEntity;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class ExplodeONutEntity extends WallNutEntity {

	public ExplodeONutEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.EXPLODE_O_NUT;
	}

}
