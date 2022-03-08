package com.hungteen.pvz.common.entity.plant.arma;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.entity.bullet.itembullet.CabbageEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantPultEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.PVZPlants;

import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.EntitySize;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;

public class CabbagePultEntity extends PlantPultEntity {

	public CabbagePultEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected PultBulletEntity createBullet() {
		return new CabbageEntity(level, this);
	}

	@Override
	public float getAttackDamage() {
		return this.getSkillValue(SkillTypes.MORE_CABBAGE_DAMAGE);
	}

	@Override
	public float getSuperDamage() {
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
