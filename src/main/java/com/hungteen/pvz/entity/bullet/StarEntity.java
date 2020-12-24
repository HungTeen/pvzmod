package com.hungteen.pvz.entity.bullet;

import com.hungteen.pvz.entity.plant.appease.StarFruitEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;

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

	private static final DataParameter<Integer> STAR_TYPE = EntityDataManager.createKey(StarEntity.class,
			DataSerializers.VARINT);
	private static final DataParameter<Integer> STAR_STATE = EntityDataManager.createKey(StarEntity.class,
			DataSerializers.VARINT);
	
	public StarEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}
	
	public StarEntity(EntityType<?> type, World worldIn, LivingEntity livingEntityIn, StarTypes starType, StarStates starState) {
		super(type, worldIn, livingEntityIn);
		this.setStarType(starType);
		this.setStarState(starState);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(STAR_TYPE, 0);
		this.dataManager.register(STAR_STATE, 0);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		boolean flag = false;
		if (result.getType() == RayTraceResult.Type.ENTITY) {
			Entity target = ((EntityRayTraceResult) result).getEntity();
			if (checkCanAttack(target)) {
				target.hurtResistantTime = 0;
				this.dealStarDamage(target); // attack 
				flag = true;
			}
		}
		if (!this.world.isRemote) {
			this.world.setEntityState(this, (byte) 3);
			if (flag || !this.checkLive(result)) {
				this.remove();
			}
		}
	}
	
	private void dealStarDamage(Entity target) {
		target.attackEntityFrom(PVZDamageSource.causeAppeaseDamage(this, this.getThrower()), this.getAttackDamage());
	}
	
	private float getAttackDamage() {
		float damage = 0;
		if(this.getThrower() instanceof StarFruitEntity) {
			damage = ((StarFruitEntity) this.getThrower()).getAttackDamage();
		}
		return damage;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.2f, 0.2f);
	}

	@Override
	protected float getGravityVelocity() {
		return 0f;
	}
	
	/**
	 * Updates the entity motion clientside, called by packets from the server
	 */
	@OnlyIn(Dist.CLIENT)
	public void setVelocity(double x, double y, double z) {
		this.setMotion(x, y, z);
		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
		    this.rotationYaw += 10;
		    this.prevRotationYaw = this.rotationYaw;
		    this.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, this.rotationPitch);
		}
	}	
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setStarState(StarStates.values()[compound.getInt("star_state")]);
		this.setStarType(StarTypes.values()[compound.getInt("star_type")]);
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("star_state", this.getStarState().ordinal());
		compound.putInt("star_type", this.getStarType().ordinal());
	}
	
	public StarStates getStarState() {
		return StarStates.values()[dataManager.get(STAR_STATE)];
	}

	public void setStarState(StarStates state) {
		dataManager.set(STAR_STATE, state.ordinal());
	}

	public StarTypes getStarType() {
		return StarTypes.values()[dataManager.get(STAR_TYPE)];
	}

	public void setStarType(StarTypes type) {
		dataManager.set(STAR_TYPE, type.ordinal());
	}
	
	public enum StarStates {
		YELLOW, PINK
	}

	public enum StarTypes {
		NORMAL, BIG, HUGE
	}

}
