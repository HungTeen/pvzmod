package com.hungteen.pvz.entity.misc;

import com.hungteen.pvz.entity.plant.explosion.BambooLordEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
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

	private static final DataParameter<Integer> FUSE = EntityDataManager.createKey(FireCrackersEntity.class,
			DataSerializers.VARINT);

	public FireCrackersEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}

	public FireCrackersEntity(World worldIn, LivingEntity living) {
		super(EntityRegister.FIRE_CRACKERS.get(), worldIn, living);
	}

	protected void registerData() {
		this.dataManager.register(FUSE, 80);
	}
	
	@Override
	public void tick() {
		super.tick();
		if(! world.isRemote) {
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
		float range = 1.5F;
		EntityUtil.playSound(this, SoundEvents.ENTITY_GENERIC_EXPLODE);
		EntityUtil.getAttackEntities(this, EntityUtil.getEntityAABB(this, range, range)).forEach((target) -> {
			target.attackEntityFrom(PVZDamageSource.causeExplosionDamage(this, this.getOwner()), this.getAttackDamage());
			target.setMotion(target.getMotion().add(0, BambooLordEntity.UP_SPEED, 0));
		});
		for(int i = 0;i < 2; ++ i) {
		    EntityUtil.spawnParticle(this, 5);
	    }
	}
	
	protected float getAttackDamage() {
		if(this.getOwner() instanceof BambooLordEntity) return ((BambooLordEntity) this.getOwner()).getAttackDamage();
		return 0;
	}

	protected boolean canTriggerWalking() {
		return false;
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("fuse_tick")) {
			this.setFuse(compound.getInt("fuse_tick"));
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("fuse_tick", this.getFuse());
	}
	
	public void setFuse(int tick) {
		this.dataManager.set(FUSE, tick);
	}
	
	public int getFuse() {
		return this.dataManager.get(FUSE);
	}

}
