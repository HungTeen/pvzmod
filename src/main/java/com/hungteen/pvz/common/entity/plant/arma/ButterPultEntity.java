package com.hungteen.pvz.common.entity.plant.arma;

import com.hungteen.pvz.common.entity.bullet.ButterEntity;
import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class ButterPultEntity extends KernelPultEntity {

	public ButterPultEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.setCurrentBullet(CornTypes.BUTTER);
	}
	
	@Override
	protected PultBulletEntity createBullet() {
		return new ButterEntity(level, this);
	}
	
	@Override
	protected void changeBullet() {
		this.setCurrentBullet(CornTypes.BUTTER);
	}

	@Override
	public float getAttackDamage() {
		final int lvl = this.getPlantLvl();
		return lvl <= 20 ? 0.1F * lvl : 2;
	}
	
	@Override
	public int getButterDuration() {
		return PlantUtil.getPlantAverageProgress(this, 80, 240);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.BUTTER_PULT;
	}
	
}
