package com.hungteen.pvz.common.entity.plant.base;

import com.hungteen.pvz.common.entity.ai.goal.attack.PultAttackGoal;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.bullet.PultBulletEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.IPult;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

import java.util.Optional;

public abstract class PlantPultEntity extends PVZPlantEntity implements IPult {

	protected boolean isSuperOut = false;
	
	public PlantPultEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new PultAttackGoal(this));
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, false, getPultRange(), this.getPultHeight()));
	}
	
	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(this.getPultRange());
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(! level.isClientSide && this.getAttackTime() > 0) {
			this.setAttackTime(this.getAttackTime() - 1);
			if(this.getAttackTime() == this.getPultAnimTime() / 2) {
				if(this.isPlantInSuperMode() && ! this.isSuperOut) {
					this.isSuperOut = true;
					this.superAttack();
				} else {
					this.pultBullet();
				}
			}
		}
	}
	
	public void performPult(LivingEntity target1){
		Optional.ofNullable(target1).ifPresent(target -> {
			PultBulletEntity bullet = this.createBullet();
			bullet.setPos(this.getX(), this.getY() + 1.7f, this.getZ());
			bullet.shootPultBullet(target);
			bullet.summonByOwner(this);
			bullet.setAttackDamage(this.isPlantInSuperMode() ? this.getSuperDamage() : this.getAttackDamage());
	        this.level.addFreshEntity(bullet);
	        EntityUtil.playSound(this, SoundRegister.PLANT_THROW.get());
		});
	}
	
	protected void superAttack() {
		final float range = this.getSuperRange();
		EntityUtil.getTargetableLivings(this, EntityUtil.getEntityAABB(this, range, range)).forEach(target -> {
			this.doSuperAttackToTarget(target);
		});
	}
	
	@Override
	public void pultBullet() {
		this.performPult(this.getTarget());
	}
	
	/**
	 * {@link #superAttack()}
	 */
	protected void doSuperAttackToTarget(LivingEntity target) {
		this.performPult(target);
	}
	
	/**
	 * {@link #performPult(LivingEntity))}
	 */
	protected abstract PultBulletEntity createBullet();
	
	/**
	 * {@link #performPult(LivingEntity))}
	 */
	public abstract float getSuperDamage();
	
	@Override
	public boolean shouldPult() {
		return this.canNormalUpdate();
	}
	
	@Override
	public boolean canPlantTarget(Entity entity) {
		return this.checkY(entity) && super.canPlantTarget(entity);
	}
	
	protected boolean checkY(Entity target) {
		return this.getY() + this.getPultHeight() >= target.getY() + target.getBbHeight();
	}
	
	public float getSuperRange() {
		return 15;
//		return this.isPlantInStage(1) ? 15 : this.isPlantInStage(2) ? 20 : 25;
	}
	
	public float getAttackDamage() {
		return 2;
//		return PlantUtil.getPlantAverageProgress(this, 2F, 8F);
	}
	
	@Override
	public float getPultRange() {
		return 30;
	}
	
	/**
	 * max target height.
	 */
	public float getPultHeight() {
		return 15;
	}
	
	@Override
	public void startPultAttack() {
		this.setAttackTime(this.getPultAnimTime());
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		this.isSuperOut = false;
		this.startPultAttack();
	}
	
	public int getPultAnimTime() {
		return 20;
	}
	
	@Override
	public int getPultCD() {
		return 60;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 60;
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("is_plant_super_out")) {
			this.isSuperOut = compound.getBoolean("is_plant_super_out");
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("is_plant_super_out", this.isSuperOut);
	}

}
