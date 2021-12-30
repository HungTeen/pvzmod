package com.hungteen.pvz.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class PVZEntityBase extends Entity {

	public PVZEntityBase(EntityType<?> type, World world) {
		super(type, world);
	}

	@Override
	protected void defineSynchedData() {
	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT p_70037_1_) {
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT p_213281_1_) {
	}
	
	@Override
	public void tick() {
		super.tick();
		if(this.tickCount <= 5) {
			this.refreshDimensions();
		}
	}
	
	protected void tickMove() {
		Vector3d vec3d = this.getDeltaMovement();
		double d0 = this.getX() + vec3d.x;
		double d1 = this.getY() + vec3d.y;
		double d2 = this.getZ() + vec3d.z;
		float f1;
		if (this.isInWater()) {
			for (int i = 0; i < 4; ++i) {
				this.level.addParticle(ParticleTypes.BUBBLE, d0 - vec3d.x * 0.25D, d1 - vec3d.y * 0.25D,
						d2 - vec3d.z * 0.25D, vec3d.x, vec3d.y, vec3d.z);
			}
			f1 = 0.8F;
		} else {
			f1 = 1F;
		}
		this.setDeltaMovement(vec3d.scale((double) f1));
		if (! this.isNoGravity()) {
			Vector3d vec3d1 = this.getDeltaMovement();
			this.setDeltaMovement(vec3d1.x, vec3d1.y - (double) this.getGravityVelocity(), vec3d1.z);
		}
		this.move(MoverType.SELF, this.getDeltaMovement());
	}
	
	protected float getGravityVelocity() {
		return 0.05F;
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}