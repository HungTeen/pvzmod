package com.hungteen.pvz.entity.plant.base;

import com.hungteen.pvz.entity.ai.attack.ShooterAttackGoal;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.interfaces.IShooter;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.world.World;

public abstract class PlantShooterEntity extends PVZPlantEntity implements IShooter {

	public PlantShooterEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(getShootRange());
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
	    this.goalSelector.addGoal(0, new ShooterAttackGoal(this));
	}

	@Override
	public void normalPlantTick() {
		super.normalPlantTick();
		if(! this.level.isClientSide && this.canAttackNow()) {
			this.shootBullet();
		}
	    if(this.getAttackTime() > 0) {
		    this.setAttackTime(this.getAttackTime() - 1);
		}
	}
	
	protected boolean canAttackNow() {
		return this.getAttackTime() > 0;
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
	protected boolean canPlantTarget(LivingEntity entity) {
		return this.checkY(entity) && super.canPlantTarget(entity);
	}
	
	@Override
	public boolean checkY(Entity target) {
		double dx = target.getX() - this.getX();
		double ly = target.getY() - this.getY() - this.getEyeHeight();
		double ry = ly + target.getBbHeight();
		double dz = target.getZ() - this.getZ();
		double dis = Math.sqrt(dx * dx + dz * dz);
		double y = dis / getMaxShootAngle();
		return ly <= y && ry >= - y;
	}
	
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