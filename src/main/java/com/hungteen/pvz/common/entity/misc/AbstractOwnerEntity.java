package com.hungteen.pvz.common.entity.misc;

import java.util.UUID;

import javax.annotation.Nullable;

import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.IGroupEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class AbstractOwnerEntity extends Entity implements IGroupEntity {

	protected LivingEntity owner;
	protected UUID ownerId;
	protected int groupType;
	
	public AbstractOwnerEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
		this.groupType = this.getInitialEntityGroup();
	}
	
	public AbstractOwnerEntity(EntityType<?> type, World worldIn, LivingEntity livingEntityIn) {
		super(type, worldIn);
		this.owner = livingEntityIn;
		this.ownerId = livingEntityIn.getUUID();
		this.groupType = EntityUtil.getEntityGroup(owner);
	}

	@Override
	protected void defineSynchedData() {
	}
	
	@Override
	public void tick() {
		super.tick();
		if(this.tickCount <= 10) {
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
	public boolean isAttackable() {
		return false;
	}
	
	public void setOwner(LivingEntity player) {
		this.owner = player;
	}

	@Nullable
	public LivingEntity getOwner() {
		if ((this.owner == null || ! this.owner.isAlive()) && this.ownerId != null && this.level instanceof ServerWorld) {
			Entity entity = ((ServerWorld) this.level).getEntity(this.ownerId);
			if (entity instanceof LivingEntity) {
				this.owner = (LivingEntity) entity;
			} else {
				this.owner = null;
			}
		}
		return this.owner;
	}
	
	public int getInitialEntityGroup() {
		return 1;
	}
	
	@Override
	public int getEntityGroupType() {
		return this.groupType;
	}
	
	public void addAdditionalSaveData(CompoundNBT compound) {
		if (this.ownerId != null) {
			compound.put("owner", NBTUtil.createUUID(this.ownerId));
		}
		compound.putInt("entity_tick_exist", this.tickCount);
		compound.putInt("group_owner_type", this.groupType);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readAdditionalSaveData(CompoundNBT compound) {
		this.owner = null;
		if (compound.contains("owner", 10)) {
			this.ownerId = NBTUtil.loadUUID(compound.getCompound("owner"));
		}
		if(compound.contains("entity_tick_exist")) {
			this.tickCount = compound.getInt("entity_tick_exist");
		}
		if(compound.contains("group_owner_type")) {
			this.groupType = compound.getInt("group_owner_type");
		}
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
