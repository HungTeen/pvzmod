package com.hungteen.pvz.common.entity.misc;

import com.hungteen.pvz.common.entity.AbstractOwnerEntity;
import com.hungteen.pvz.common.entity.plant.explosion.BambooLordEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class FireCrackersEntity extends AbstractOwnerEntity {

	private static final DataParameter<Integer> FUSE = EntityDataManager.defineId(FireCrackersEntity.class,
			DataSerializers.INT);

	public FireCrackersEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}

	public FireCrackersEntity(World worldIn, LivingEntity living) {
		super(EntityRegister.FIRE_CRACKERS.get(), worldIn, living);
	}

	protected void defineSynchedData() {
		this.entityData.define(FUSE, 80);
	}
	
	@Override
	public void tick() {
		super.tick();
		if(! level.isClientSide) {
			if(this.getFuse() > 0) {
				this.setFuse(this.getFuse() - 1);
				if(this.getFuse() <= 0) {
					this.explode();
					this.remove();
				}
			}
		}
		this.tickMove();
	}
	
	protected void explode() {
		final float range = 2F;
		EntityUtil.playSound(this, SoundEvents.GENERIC_EXPLODE);
		EntityUtil.getTargetableEntities(this, EntityUtil.getEntityAABB(this, range, range)).forEach(target -> {
			target.hurt(PVZEntityDamageSource.explode(this, this.getOwner()), 50F);
			target.setDeltaMovement(target.getDeltaMovement().add(0, BambooLordEntity.UP_SPEED, 0));
		});
		for(int i = 0; i < 2; ++ i) {
		    EntityUtil.spawnParticle(this, 5);
	    }
	}
	
	protected boolean isMovementNoisy() {
		return false;
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("fuse_tick")) {
			this.setFuse(compound.getInt("fuse_tick"));
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("fuse_tick", this.getFuse());
	}
	
	public void setFuse(int tick) {
		this.entityData.set(FUSE, tick);
	}
	
	public int getFuse() {
		return this.entityData.get(FUSE);
	}

}
