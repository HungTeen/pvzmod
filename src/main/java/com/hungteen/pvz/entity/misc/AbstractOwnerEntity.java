package com.hungteen.pvz.entity.misc;

import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class AbstractOwnerEntity extends Entity {

	protected LivingEntity owner;
	private UUID ownerId;
	
	public AbstractOwnerEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}
	
	public AbstractOwnerEntity(EntityType<?> type, World worldIn, PlayerEntity livingEntityIn) {
		this(type, worldIn);
		this.owner = livingEntityIn;
		this.ownerId = livingEntityIn.getUniqueID();
	}

	@Override
	protected void registerData() {
	}
	
	@Override
	public void tick() {
		super.tick();
		if(this.ticksExisted <= 10) {
			this.recalculateSize();
		}
	}
	
	protected void tickMove() {
		Vec3d vec3d = this.getMotion();
		double d0 = this.getPosX() + vec3d.x;
		double d1 = this.getPosY() + vec3d.y;
		double d2 = this.getPosZ() + vec3d.z;
		float f1;
		if (this.isInWater()) {
			for (int i = 0; i < 4; ++i) {
				this.world.addParticle(ParticleTypes.BUBBLE, d0 - vec3d.x * 0.25D, d1 - vec3d.y * 0.25D,
						d2 - vec3d.z * 0.25D, vec3d.x, vec3d.y, vec3d.z);
			}
			f1 = 0.8F;
		} else {
			f1 = 1F;
		}
		this.setMotion(vec3d.scale((double) f1));
		if (! this.hasNoGravity()) {
			Vec3d vec3d1 = this.getMotion();
			this.setMotion(vec3d1.x, vec3d1.y - (double) this.getGravityVelocity(), vec3d1.z);
		}
		this.move(MoverType.SELF, this.getMotion());
	}
	
	protected float getGravityVelocity() {
		return 0.05F;
	}
	
	@Override
	public boolean canBeAttackedWithItem() {
		return false;
	}
	
	public void setOwner(LivingEntity player) {
		this.owner = player;
	}

	@Nullable
	public LivingEntity getOwner() {
		if ((this.owner == null || ! this.owner.isAlive()) && this.ownerId != null && this.world instanceof ServerWorld) {
			Entity entity = ((ServerWorld) this.world).getEntityByUuid(this.ownerId);
			if (entity instanceof LivingEntity) {
				this.owner = (LivingEntity) entity;
			} else {
				this.owner = null;
			}
		}
		return this.owner;
	}
	
	public void writeAdditional(CompoundNBT compound) {
		if (this.ownerId != null) {
			compound.put("owner", NBTUtil.writeUniqueId(this.ownerId));
		}
		compound.putInt("entity_tick_exist", this.ticksExisted);
	}
	
	public int getEntityGroup() {
		return 1;
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readAdditional(CompoundNBT compound) {
		this.owner = null;
		if (compound.contains("owner", 10)) {
			this.ownerId = NBTUtil.readUniqueId(compound.getCompound("owner"));
		}
		if(compound.contains("entity_tick_exist")) {
			this.ticksExisted = compound.getInt("entity_tick_exist");
		}
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
