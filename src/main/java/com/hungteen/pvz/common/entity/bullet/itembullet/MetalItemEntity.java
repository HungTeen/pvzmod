package com.hungteen.pvz.common.entity.bullet.itembullet;

import java.util.List;

import com.hungteen.pvz.common.entity.plant.assist.MagnetShroomEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.remove.MetalTypes;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class MetalItemEntity extends PVZItemBulletEntity {

	private static final DataParameter<Integer> METAL_TYPE = EntityDataManager.defineId(MetalItemEntity.class,
			DataSerializers.INT);
	private static final DataParameter<Integer> METAL_STATE = EntityDataManager.defineId(MetalItemEntity.class,
			DataSerializers.INT);
	private ItemStack stack = null;
	
	public MetalItemEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}
	
	public MetalItemEntity(World worldIn, LivingEntity shooter, MetalTypes metalType) {
		super(EntityRegister.METAL.get(), worldIn, shooter);
		this.setMetalType(metalType);
	}
	
	@Override
	protected void defineSynchedData() {
		entityData.define(METAL_TYPE, MetalTypes.EMPTY.ordinal());
		entityData.define(METAL_STATE, MetalStates.ABSORB.ordinal());
	}
	
	@Override
	public void tick() {
		super.tick();
		this.noPhysics = true;
		if(! level.isClientSide && this.getThrower() instanceof MagnetShroomEntity) {
			final MagnetShroomEntity thrower = (MagnetShroomEntity) this.getThrower();
			if(this.distanceToSqr(thrower) <= 3) {
				// near the thrower
				if(this.getMetalState() == MetalStates.ABSORB) {
					thrower.setMetalType(getMetalType());
				    this.remove();
				} else if(this.getMetalState() == MetalStates.BULLET){
					this.setMetalState(MetalStates.WAIT);
				}
			}
			if(this.getMetalState() == MetalStates.BULLET || this.getMetalState() == MetalStates.ABSORB) {
				Vector3d vec = thrower.position().add(0, thrower.getBbHeight(), 0).subtract(this.position());
			    this.setDeltaMovement(vec.normalize().scale(0.8D));
			} else if(this.getMetalState() == MetalStates.WAIT){
				LivingEntity target = this.getAttackTarget(thrower);
				if(target == null) {
					this.setDeltaMovement(0, 0, 0);
					return ;
				}
				this.setMetalState(MetalStates.SHOOT);
				Vector3d vec = target.position().add(0, target.getEyeHeight(), 0).subtract(this.position());
				this.shootPea(vec.x, vec.y, vec.z, 1.4F, 0);
			}
		}
	}
	
	private LivingEntity getAttackTarget(MagnetShroomEntity thrower) {
		if(this.tickCount % 50 != 0) return null;
		final float range = 20;
		List<LivingEntity> list = level.getEntitiesOfClass(LivingEntity.class, EntityUtil.getEntityAABB(thrower, range, range), entity -> {
			return EntityUtil.checkCanEntityBeTarget(thrower, entity);
		});
		if(list.size() == 0) {
			return null;
		}
		int pos = thrower.getRandom().nextInt(list.size());
		return list.get(pos);
	}
	
	@Override
	protected void onImpact(RayTraceResult result) {
		boolean flag = false;
		if (result.getType() == RayTraceResult.Type.ENTITY) {
			Entity target = ((EntityRayTraceResult) result).getEntity();
			if (this.shouldHit(target)) {
				target.invulnerableTime = 0;
				target.hurt(PVZEntityDamageSource.metal(this, this.getThrower()), this.getAttackDamage());
				EntityUtil.playSound(this, SoundRegister.METAL_HIT.get());
				flag = true;
			}
		}
		this.level.broadcastEntityEvent(this, (byte) 3);
		if (flag || ! this.checkLive(result)) {
			this.remove();
		}
	}
	
	@Override
	protected boolean shouldHit(Entity target) {
		return this.getMetalState() == MetalStates.SHOOT && EntityUtil.canTargetEntity(this, target);
	}
	
	@Override
	protected boolean checkLive(RayTraceResult result) {
		if(this.getMetalState() != MetalStates.SHOOT || result.getType() == RayTraceResult.Type.BLOCK) {
			return true;
		}
		return super.checkLive(result);
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.2f, 0.2f, false);
	}

	@Override
	protected int getMaxLiveTick() {
		return 1200;
	}
	
	@Override
	public ItemStack getItem() {
		return this.stack == null ? this.stack = new ItemStack(MetalTypes.getMetalItem(this.getMetalType())) : this.stack;
	}
	
	@Override
	protected float getGravityVelocity() {
		return 0;
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("metal_type", this.getMetalType().ordinal());
		compound.putInt("metal_state", this.getMetalState().ordinal());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("metal_type")) {
			this.setMetalType(MetalTypes.values()[compound.getInt("metal_type")]);
		}
		if(compound.contains("metal_state")) {
			this.setMetalState(MetalStates.values()[compound.getInt("metal_state")]);
		}
	}

	public MetalTypes getMetalType() {
		return MetalTypes.values()[entityData.get(METAL_TYPE)];
	}

	public void setMetalType(MetalTypes type) {
		entityData.set(METAL_TYPE, type.ordinal());
	}
	
	public MetalStates getMetalState() {
		return MetalStates.values()[entityData.get(METAL_STATE)];
	}

	public void setMetalState(MetalStates type) {
		entityData.set(METAL_STATE, type.ordinal());
	}
	
	public enum MetalStates {
		ABSORB,
		BULLET,
		WAIT,
		SHOOT;
	}
	
}
