package com.hungteen.pvz.entity.misc;

import java.util.UUID;

import javax.annotation.Nullable;

import com.hungteen.pvz.entity.plant.enforce.ChomperEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.SoundRegister;
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

public class SmallChomperEntity extends MobEntity {

	private LivingEntity owner;
	private UUID ownerUuid;
	private int lifeTick;
	private final int maxLifeTick = 20;

	public SmallChomperEntity(EntityType<? extends MobEntity> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
		this.setInvulnerable(true);
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
		this.owner=this.getOwner();
		if(this.owner==null) {
			return ;
		}
		for(LivingEntity target:EntityUtil.getEntityAttackableTarget(this.owner, EntityUtil.getEntityAABB(this, 0.5f, 1f))) {
			target.attackEntityFrom(PVZDamageSource.causeEatDamage(this, this.owner), getAttackDamage(target));
		}
		this.playSound(SoundRegister.CHOMP.get(), 1, 1);
	}
	
	private float getAttackDamage(LivingEntity target) {
		if(this.owner instanceof ChomperEntity) {
			return ((ChomperEntity) this.owner).getAttackDamage(target);
		}
		return 40;
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
