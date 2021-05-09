package com.hungteen.pvz.entity.plant.spear;

import com.hungteen.pvz.entity.ai.target.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.bullet.ThornEntity;
import com.hungteen.pvz.entity.bullet.ThornEntity.ThornStates;
import com.hungteen.pvz.entity.bullet.ThornEntity.ThornTypes;
import com.hungteen.pvz.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.entity.zombie.poolnight.BalloonZombieEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

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
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CactusEntity extends PlantShooterEntity {

	private static final DataParameter<Float> CACTUS_HEIGHT = EntityDataManager.defineId(CactusEntity.class, DataSerializers.FLOAT);
	private static final DataParameter<Boolean> POWERED = EntityDataManager.defineId(CactusEntity.class, DataSerializers.BOOLEAN);
	public static final float MAX_SEGMENT_NUM = 4;
	public static final float SEGMENT_HEIGHT = 0.54F;
	private static final float MIN_SHOOT_HEIGHT = 1.25F;
	private static final float MAX_SHOOT_HEIGHT = MIN_SHOOT_HEIGHT + MAX_SEGMENT_NUM * SEGMENT_HEIGHT;
	protected final double LENTH = 0.3D; //pea position offset
	
	
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
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 5, getShootRange(), 6, 0));
	}
	
	@Override
	public void normalPlantTick() {
		super.normalPlantTick();
		this.refreshDimensions();
		if(! level.isClientSide) {
			LivingEntity target = this.getTarget();
			if(target != null) {
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
	public boolean hurt(DamageSource source, float amount) {
		this.dealThornBackDamage(source);
		return super.hurt(source, amount);
	}
	
	private void dealThornBackDamage(DamageSource source) {
		if(EntityUtil.checkCanEntityAttack(this, source.getDirectEntity())) {
			float damage = this.getThornDamage();
			if(this.isCactusPowered()) damage *= 2;
			source.getDirectEntity().hurt(PVZDamageSource.causeThornDamage(this, this), damage);
		}
	}
	
	public int getThornCount() {
		if(this.isPlantInStage(1)) return 2;
		if(this.isPlantInStage(2)) return 3;
		return 4;
	}
	
	public float getThornDamage() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) {
			return 1.75F + 0.25F * lvl;
		}
		return 6.5F;
	}
	
	public float getShootHeight() {
		return this.getCactusHeight() + MIN_SHOOT_HEIGHT;
	}
	
	private boolean isSuitableHeight(Entity target) {
		double dx = target.getX() - this.getX();
		double ly = target.getY() - this.getY() - this.getShootHeight();
		double ry = ly + target.getBbHeight();
		double dz = target.getZ() - this.getZ();
		double dis = Math.sqrt(dx * dx + dz * dz);
		double y = dis / getMaxShootAngle();
		return ly <= y && ry >= - y;
	}
	
	@Override
	protected boolean canPlantTarget(LivingEntity entity) {
		if(entity instanceof BalloonZombieEntity) return true;
		return super.canPlantTarget(entity);
	}
	
	@Override
	public boolean checkY(Entity target) {
		double dx = target.getX() - this.getX();
		double dz = target.getZ() - this.getZ();
		double dis = Math.sqrt(dx * dx + dz * dz);
		double y = dis / getMaxShootAngle();
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
		return ! this.isCactusPowered();
	}
	
	@Override
	public void shootBullet() {
		LivingEntity target = this.getTarget();
		if(target == null) return ;
		double dx = target.getX() - this.getX();
        double dz = target.getZ() - this.getZ();
        double y = this.getY() + this.getShootHeight();
        double dis =MathHelper.sqrt(dx * dx + dz * dz);
        double tmp = this.LENTH / dis;
        double deltaX = tmp * dx;
        double deltaZ = tmp * dz;
		ThornEntity thorn = new ThornEntity(level, this);
		thorn.setThornType(ThornTypes.NORMAL);
		thorn.setThornState(this.isCactusPowered() ? ThornStates.POWER : ThornStates.NORMAL);
		thorn.setExtraHitCount(this.isCactusPowered() ? this.getThornCount() : 1);
		thorn.setPos(this.getX() + deltaX, y, this.getZ() + deltaZ);
        thorn.shootPea(dx, target.getY() + target.getEyeHeight() - y, dz, this.getBulletSpeed());
		this.level.addFreshEntity(thorn);
	}
	
	@Override
	public float getBulletSpeed() {
		return 1.2F;
	}
	
	@Override
	public int getShootCD() {
		return getNormalAttackCD();
	}
	
	@Override
	public float getAttackDamage() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			int now = (lvl - 1) / 2;
			return 2.25F + now * 0.25F;
		}
		return 4.5F;
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
	public Plants getPlantEnumName() {
		return Plants.CACTUS;
	}

	@Override
	public int getSuperTimeLength() {
		return 20;
	}

}
