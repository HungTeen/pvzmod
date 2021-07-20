package com.hungteen.pvz.common.entity.bullet;

import com.hungteen.pvz.common.entity.plant.appease.AngelStarFruitEntity;
import com.hungteen.pvz.common.entity.plant.appease.StarFruitEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class StarEntity extends AbstractBulletEntity {

	private static final DataParameter<Integer> STAR_TYPE = EntityDataManager.defineId(StarEntity.class,
			DataSerializers.INT);
	private static final DataParameter<Integer> STAR_STATE = EntityDataManager.defineId(StarEntity.class,
			DataSerializers.INT);
	
	public StarEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}
	
	public StarEntity(World worldIn, LivingEntity livingEntityIn, StarTypes starType, StarStates starState) {
		super(EntityRegister.STAR.get(), worldIn, livingEntityIn);
		this.setStarType(starType);
		this.setStarState(starState);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(STAR_TYPE, 0);
		this.entityData.define(STAR_STATE, 0);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		boolean flag = false;
		if (result.getType() == RayTraceResult.Type.ENTITY) {
			Entity target = ((EntityRayTraceResult) result).getEntity();
			if (checkCanAttack(target)) {
				target.invulnerableTime = 0;
				this.dealStarDamage(target); // attack 
				flag = true;
			}
		}
		this.level.broadcastEntityEvent(this, (byte) 3);
		if (flag || !this.checkLive(result)) {
			this.remove();
		}
	}
	
	private void dealStarDamage(Entity target) {
		target.hurt(PVZDamageSource.star(this, this.getThrower()), this.getFixDamage());
	}
	
	@Override
	protected int getMaxLiveTick() {
		return 80;
	}
	
	private float getFixDamage() {
		float damage = this.attackDamage;
		if(this.getStarType() == StarTypes.BIG) damage += 5;
		if(this.getStarType() == StarTypes.HUGE) damage += 10;
		return damage;
	}
	
	protected float getAttackDamage() {
		if(this.getThrower() instanceof StarFruitEntity) return ((StarFruitEntity) this.getThrower()).getAttackDamage();
		if(this.getThrower() instanceof AngelStarFruitEntity) return ((AngelStarFruitEntity) this.getThrower()).getAttackDamage();
		return 0;
		
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		if(this.getStarType() == StarTypes.BIG) return EntitySize.scalable(0.5f, 0.2f);
		if(this.getStarType() == StarTypes.HUGE) return EntitySize.scalable(0.8f, 0.2f);
		return EntitySize.scalable(0.2f, 0.2f);
	}

	@Override
	protected float getGravityVelocity() {
		return 0f;
	}
	
	/**
	 * Updates the entity motion clientside, called by packets from the server
	 */
	@OnlyIn(Dist.CLIENT)
	public void lerpMotion(double x, double y, double z) {
		this.setDeltaMovement(x, y, z);
		if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
		    this.yRot += 10;
		    this.yRotO = this.yRot;
		    this.moveTo(this.getX(), this.getY(), this.getZ(), this.yRot, this.xRot);
		}
	}	
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("star_state")) {
		    this.setStarState(StarStates.values()[compound.getInt("star_state")]);
		}
		if(compound.contains("star_type")) {
			this.setStarType(StarTypes.values()[compound.getInt("star_type")]);
		}
		
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("star_state", this.getStarState().ordinal());
		compound.putInt("star_type", this.getStarType().ordinal());
	}
	
	public StarStates getStarState() {
		return StarStates.values()[entityData.get(STAR_STATE)];
	}

	public void setStarState(StarStates state) {
		entityData.set(STAR_STATE, state.ordinal());
	}

	public StarTypes getStarType() {
		return StarTypes.values()[entityData.get(STAR_TYPE)];
	}

	public void setStarType(StarTypes type) {
		entityData.set(STAR_TYPE, type.ordinal());
	}
	
	public enum StarStates {
		YELLOW, PINK
	}

	public enum StarTypes {
		NORMAL, BIG, HUGE
	}

}
