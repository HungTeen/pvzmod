package com.hungteen.pvz.entity.plant.toxic;

import com.hungteen.pvz.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.entity.plant.interfaces.IShroomPlant;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class FumeShroomEntity extends PlantShooterEntity implements IShroomPlant{

	public FumeShroomEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public void shootBullet() {
	}

	@Override
	public void startShootAttack() {
		
	}
	
    @Override
	public int getSuperTimeLength() {
		return 0;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.FUME_SHROOM;
	}
	
}
