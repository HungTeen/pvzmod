package com.hungteen.pvz.entity.plant.base;

import com.hungteen.pvz.entity.ai.ShooterAttackGoal;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.interfaces.IShooter;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public abstract class PlantShooterEntity extends PVZPlantEntity implements IShooter{

	public PlantShooterEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(getShootRange());
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
	    this.goalSelector.addGoal(0, new ShooterAttackGoal(this));
	}

	@Override
	public void normalPlantTick() {
		super.normalPlantTick();
		if(!this.world.isRemote) {
		    if(this.getAttackTime() > 0) {
			    this.shootBullet();
			    this.setAttackTime(this.getAttackTime() - 1);
			}
		}
	}
	
	/**
	 * get shooter bullet attack damage
	 */
	public float getAttackDamage() {
		int lvl = this.getPlantLvl();
		if(lvl <= 18) {
			int now = (lvl -1) / 2;
			return 2 + 0.25f * now;
		}
		if(lvl <= 19) return 4.25f;
		return 4.5f;
	}
	
	@Override
	public boolean checkY(Entity target) {
		double dx = target.getPosX() - this.getPosX();
		double ly = target.getPosY() - this.getPosY();
		double ry = ly + target.getHeight();
		double dz = target.getPosZ() - this.getPosZ();
		double dis = Math.sqrt(dx * dx + dz * dz);
		double y=dis / getMaxShootAngle();
		return ly <= y && ry >= -y;
	}
	
	@Override
	public double getMaxShootAngle() {
		return 20;
	}
	
	@Override
	public float getShootRange() {
		return 40;
	}
	
	@Override
	public int getShootCD() {
		if(this.isPlantInSuperMode()) {
			return 1;
		}
		return getNormalAttackCD();
	}
	
	@Override
	public boolean canShoot() {
		return this.canPlantNormalUpdate();
	}
	
	/**
	 * get normal attack CD
	 */
	public int getNormalAttackCD() {
		return 30;
	}
	
	@Override
	public float getBulletSpeed() {
		return 1f;
	}
	
}