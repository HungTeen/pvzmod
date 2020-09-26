package com.hungteen.pvz.entity.plant.defence;

import com.hungteen.pvz.entity.plant.base.PlantDefenderEntity;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class WallNutEntity extends PlantDefenderEntity{

	public WallNutEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public float getSuperLife() {
		return getArmorLife(this.getPlantLvl());
	}

	public static int getArmorLife(int lvl) {
		if(lvl<=6) return 400;
		else if(lvl<=13) return 500;
		else if(lvl<=20) return 600;
		return 400;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.WALL_NUT;
	}

}
