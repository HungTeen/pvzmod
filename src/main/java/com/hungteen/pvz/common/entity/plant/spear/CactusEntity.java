package com.hungteen.pvz.common.entity.plant.spear;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.bullet.ThornEntity;
import com.hungteen.pvz.common.entity.bullet.ThornEntity.ThornStates;
import com.hungteen.pvz.common.entity.bullet.ThornEntity.ThornTypes;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.common.entity.zombie.pool.BalloonZombieEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class CactusEntity extends PlantShooterEntity {

	private static final DataParameter<Float> CACTUS_HEIGHT = EntityDataManager.defineId(CactusEntity.class, DataSerializers.FLOAT);
	private static final DataParameter<Boolean> POWERED = EntityDataManager.defineId(CactusEntity.class, DataSerializers.BOOLEAN);
	public static final float MAX_SEGMENT_NUM = 4;
	public static final float SEGMENT_HEIGHT = 0.54F;
	private static final float MIN_SHOOT_HEIGHT = 1.25F;
	private static final float MAX_SHOOT_HEIGHT = MIN_SHOOT_HEIGHT + MAX_SEGMENT_NUM * SEGMENT_HEIGHT;
	protected static final double SHOOT_OFFSET = 0.3D; //pea position offset
	
	
	public CactusEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(CACTUS_HEIGHT, 0f);
		this.entityData.define(POWERED, false);
	}
	
	@Override
	public void normalPlantTick() {
		super.normalPlantTick();
		if(! level.isClientSide) {
			final LivingEntity target = this.getTarget();
			if(EntityUtil.isEntityValid(target)) {
				if(! this.isSuitableHeight(target)) {
					final float dh = SEGMENT_HEIGHT;
				    if(this.getY() < target.getY()) {
					    this.setCactusHeight(Math.min(this.getCactusHeight() + dh, SEGMENT_HEIGHT * MAX_SEGMENT_NUM));
				    } else {
				    	this.setCactusHeight(Math.max(this.getCactusHeight() - dh, 0));
				    }
				}
			} else {
				this.setCactusHeight(0);
			}
		}
	}
	
	@Override
	public void onSyncedDataUpdated(DataParameter<?> data) {
		super.onSyncedDataUpdated(data);
		if(data.equals(CACTUS_HEIGHT)){
			this.refreshDimensions();
		}
	}
	
	@Override
	public void shootBullet() {
		this.performShoot(SHOOT_OFFSET, 0, 0, this.getAttackTime() == 1, FORWARD_SHOOT_ANGLE);
	}
	
	@Override
	protected AbstractBulletEntity createBullet() {
		final ThornEntity thorn = new ThornEntity(level, this);
		thorn.setThornType(ThornTypes.NORMAL);
		thorn.setThornState(this.isCactusPowered() ? ThornStates.POWER : ThornStates.NORMAL);
		thorn.setExtraHitCount(this.isCactusPowered() ? this.getThornCount() : 1);
		return thorn;
	}
	
	
	@Override
	public boolean hurt(DamageSource source, float amount) {
		if(EntityUtil.canAttackEntity(this, source.getEntity())) {
			final float damage = this.isCactusPowered() ? this.getAttackDamage() * 4 : this.getAttackDamage() * 2;
			source.getEntity().hurt(PVZDamageSource.causeThornDamage(this, this), damage);
		}
		return super.hurt(source, amount);
	}
	
	public int getThornCount() {
		return 2;
//		return this.isPlantInStage(1) ? 2 : this.isPlantInStage(2) ? 3 : 4;
	}
	
	public float getCurrentHeight() {
		return this.getCactusHeight() + MIN_SHOOT_HEIGHT;
	}
	
	/**
	 * {@link #normalPlantTick()}
	 */
	private boolean isSuitableHeight(Entity target) {
		double dx = target.getX() - this.getX();
		double ly = target.getY() - this.getY() - this.getCurrentHeight();
		double ry = ly + target.getBbHeight();
		double dz = target.getZ() - this.getZ();
		double dis = Math.sqrt(dx * dx + dz * dz);
		double y = dis / getMaxShootAngle();
		return ly <= y && ry >= - y;
	}
	
	@Override
	public boolean canPlantTarget(Entity entity) {
		if(entity instanceof BalloonZombieEntity) {
			return true;
		}
		return super.canPlantTarget(entity);
	}
	
	@Override
	public boolean checkY(Entity target) {
		final double dx = target.getX() - this.getX();
		final double dz = target.getZ() - this.getZ();
		final double dis = Math.sqrt(dx * dx + dz * dz);
		final double y = dis / getMaxShootAngle();
		return this.getY() + MAX_SHOOT_HEIGHT + y >= target.getY() &&  this.getY() + MIN_SHOOT_HEIGHT - y <= target.getY() + target.getBbHeight();
	}
	
	@Override
	public void startShootAttack() {
		this.setAttackTime(1);
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8F, 2.0F + this.getCactusHeight());
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		this.setCactusPowered(true);
	}
	
	@Override
	public boolean canStartSuperMode() {
		return super.canStartSuperMode() && ! this.isCactusPowered();
	}
	
	@Override
	public int getSuperTimeLength() {
		return 20;
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("cactus_powered")) {
			this.setCactusPowered(compound.getBoolean("cactus_powered"));
		}
		if(compound.contains("cactus_height")) {
			this.setCactusHeight(compound.getFloat("cactus_height"));
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("cactus_powered", this.isCactusPowered());
		compound.putFloat("cactus_height", this.getCactusHeight());
	}
	
	public float getCactusHeight() {
		return this.entityData.get(CACTUS_HEIGHT);
	}
	
	public void setCactusHeight(float h) {
		this.entityData.set(CACTUS_HEIGHT, h);
	}
	
	public boolean isCactusPowered() {
		return this.entityData.get(POWERED);
	}
	
	public void setCactusPowered(boolean is) {
		this.entityData.set(POWERED, is);
	}

	@Override
	public IPlantType getPlantType() {
		return PVZPlants.CACTUS;
	}

	@Override
	public float getAttackDamage() {
		return 0;
	}

}
