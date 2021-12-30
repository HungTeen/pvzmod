package com.hungteen.pvz.common.entity.plant.arma;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.CabbageEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantPultEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class CabbagePultEntity extends PlantPultEntity {

	public CabbagePultEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected PultBulletEntity createBullet() {
		return new CabbageEntity(level, this);
	}
	
	@Override
	public float getSuperDamage() {
//		final float add = this.getThreeStage(20, 40, 60);
//		return this.getAttackDamage() + add;
		return this.getAttackDamage() + 20;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8F, 1F);
	}
	
	@Override
	public IPlantType getPlantType() {
		return PVZPlants.CABBAGE_PULT;
	}

}
