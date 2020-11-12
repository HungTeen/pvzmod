package com.hungteen.pvz.entity.misc;

import java.util.UUID;

import javax.annotation.Nullable;

import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ZombieHandEntity extends MobEntity {

	private LivingEntity owner;
	private UUID ownerUuid;
	private int lifeTick;
	private final int maxLifeTick = 40;

	public ZombieHandEntity(EntityType<? extends MobEntity> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
		this.setInvulnerable(true);
		this.noClip = true;
	}

	@Override
	public void tick() {
		super.tick();
		if(this.lifeTick<maxLifeTick) {
			this.lifeTick++;
		}else {
			if(!this.world.isRemote) {
			    this.performAttack();
			    this.remove();
			}
		}
	}
	
	protected void performAttack() {
		this.owner = this.getOwner();
		if(this.owner == null) {
			return ;
		}
		for(Entity target:EntityUtil.getEntityAttackableTarget(this.owner, EntityUtil.getEntityAABB(this, 0.5f, 1f))) {
			if(target instanceof LivingEntity) {
			    target.attackEntityFrom(PVZDamageSource.causeNormalDamage(this, this.owner), getAttackDamage((LivingEntity) target));
			    target.setPosition(target.getPosX(), target.getPosY() - 3, target.getPosZ());
			}
		}
	}
	
	private float getAttackDamage(LivingEntity target) {
		return 5;
	}
	
	public int getTick() {
		return this.lifeTick;
	}
	
	@Override
	protected void collideWithEntity(Entity entityIn) {
	}
	
	@Override
	protected void collideWithNearbyEntities() {
	}
	
	@Override
	public boolean canBeCollidedWith() {
		return false;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.4f, 0.5f, false);
	}

	public void setOwner(@Nullable LivingEntity owner) {
		this.owner = owner;
		this.ownerUuid = owner == null ? null : owner.getUniqueID();
	}

	@Nullable
	public LivingEntity getOwner() {
		if (this.owner == null && this.ownerUuid != null && this.world instanceof ServerWorld) {
			Entity entity = ((ServerWorld) this.world).getEntityByUuid(this.ownerUuid);
			if (entity instanceof LivingEntity) {
				this.owner = (LivingEntity) entity;
			}
		}
		return this.owner;
	}

	@Override
	public boolean hasNoGravity() {
		return true;
	}
	
	public void readAdditional(CompoundNBT compound) {
		if (compound.hasUniqueId("OwnerUUID")) {
			this.ownerUuid = compound.getUniqueId("OwnerUUID");
		}
	}

	public void writeAdditional(CompoundNBT compound) {
		if (this.ownerUuid != null) {
			compound.putUniqueId("OwnerUUID", this.ownerUuid);
		}
	}

}
