package com.hungteen.pvz.common.entity.plant.base;

import java.util.EnumSet;
import java.util.Optional;

import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.appease.StarFruitEntity;
import com.hungteen.pvz.common.entity.plant.spear.CatTailEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.interfaces.IShooter;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public abstract class PlantShooterEntity extends PVZPlantEntity implements IShooter {

	//use for normal shoot attack animation and shoot goal.
	private static final DataParameter<Integer> SHOOT_TICK = EntityDataManager.defineId(PVZPlantEntity.class, DataSerializers.INT);
	public static final float FORWARD_SHOOT_ANGLE = 0;
	public static final float BACK_SHOOT_ANGLE = 180;
	public static final float FORWARD_LEFT_SHOOT_ANGLE = -7.5F;
	public static final float FORWARD_RIGHT_SHOOT_ANGLE = 7.5F;
	public static final int SHOOT_ANIM_CD = 10;
	public static final int SHOOT_POINT = SHOOT_ANIM_CD * 3 / 4;
	public static final int SHOOT_POINT_OFFSET = SHOOT_ANIM_CD - SHOOT_POINT;
	
	public PlantShooterEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SHOOT_TICK, 0);
	}
	
	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(getShootRange());
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
	    this.goalSelector.addGoal(0, new ShooterAttackGoal(this));
	    this.addTargetGoals();
	}
	
	protected void addTargetGoals() {
		this.targetSelector.addGoal(0, new ShooterNearestTargetGoal(this, true, false, getShootRange(), getShootHeight()));
	}

	@Override
	public void normalPlantTick() {
		super.normalPlantTick();
		if(! this.level.isClientSide && this.canAttackNow()) {
			this.shootBullet();
		}
	    if(this.getAttackTime() > 0) {
		    this.setAttackTime(this.getAttackTime() - 1);
		}
	}
	
	/**
	 * shoot pea with offsets.
	 */
	public void performShoot(double forwardOffset, double rightOffset, double heightOffset, boolean needSound, double angleOffset) {
		Optional.ofNullable(this.getTarget()).ifPresent(target -> {
			final Vector3d vec = EntityUtil.getNormalisedVector2d(this, target);
            final double deltaY = this.getDimensions(getPose()).height * 0.7F + heightOffset;
            final double deltaX = forwardOffset * vec.x - rightOffset * vec.z;
            final double deltaZ = forwardOffset * vec.z + rightOffset * vec.x;
            final AbstractBulletEntity bullet = this.createBullet();
            bullet.setPos(this.getX() + deltaX, this.getY() + deltaY, this.getZ() + deltaZ);
            bullet.shootPea(vec.x, target.getY() + target.getBbHeight() - bullet.getY(), vec.z, this.getBulletSpeed(), angleOffset);
            if(needSound) {
            	EntityUtil.playSound(this, this.getShootSound());
            }
            bullet.summonByOwner(this);
            this.level.addFreshEntity(bullet);
		});
	}
	
	/**
	 * {@link #performShoot(double, double, double, boolean, ShootTypes)}
	 */
	protected abstract AbstractBulletEntity createBullet();
	
	protected SoundEvent getShootSound() {
		return SoundEvents.SNOW_GOLEM_SHOOT;
	}
	
	protected boolean canAttackNow() {
		return this.getAttackTime() > 0;
	}
	
	/**
	 * get shooter bullet attack damage.
	 */
	public float getAttackDamage() {
		return MathUtil.getProgressAverage(getPlantLvl(), PlantUtil.MAX_PLANT_LEVEL, 1F, 4F);
	}
	
	@Override
	public boolean canPlantTarget(Entity entity) {
		return this.checkY(entity) && super.canPlantTarget(entity);
	}
	
	@Override
	public boolean checkY(Entity target) {
		final double dx = target.getX() - this.getX();
		final double dz = target.getZ() - this.getZ();
		final double minY = target.getY() - this.getY() - this.getEyeHeight();
		final double maxY = minY + target.getBbHeight();
		final double dis = Math.sqrt(dx * dx + dz * dz);
		final double y = dis / getMaxShootAngle();
		return minY < y && maxY > - y;
	}
	
	/**
	 * use to check horizontal shoot path.
	 * {@link #checkY(Entity)}
	 * {@link AbstractBulletEntity#getShootPeaAngle()}
	 */
	public double getMaxShootAngle() {
		return 60;
	}
	
	/**
	 * max target horizontal distance.
	 */
	public float getShootRange() {
		return 30;
	}
	
	/**
	 * max target height.
	 */
	public float getShootHeight() {
		return 2;
	}
	
	/**
	 * check weather the shooter can shoot currently.
	 * {@link ShooterNearestTargetGoal#checkOther(LivingEntity)}
	 */
	public boolean canShoot() {
		return this.canPlantNormalUpdate();
	}
	
	@Override
	public int getShootCD() {
		return getNormalAttackCD();
	}
	
	/**
	 * get normal attack CD
	 */
	public int getNormalAttackCD() {
		return 30;
	}
	
	@Override
	public float getBulletSpeed() {
		return 1.5F;
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("plant_shoot_tick")) {
			this.setShootTick(compound.getInt("plant_shoot_tick"));
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("plant_shoot_tick", this.getShootTick());
	}
	
	public int getShootTick() {
		return this.entityData.get(SHOOT_TICK);
	}
	
	public void setShootTick(int tick) {
		this.entityData.set(SHOOT_TICK, tick);
	}
	
	static class ShooterAttackGoal extends Goal {

		protected final PlantShooterEntity shooter;
		protected LivingEntity target;
		
		public ShooterAttackGoal(PlantShooterEntity shooter) {
			this.shooter = shooter;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}
		
		@Override
		public boolean canUse() {
			if(! this.shooter.canShoot()) {//can not shoot because of the shooter itself.
				this.shooter.setShootTick(0);
				return false;
			}
			this.target = this.shooter.getTarget();
			if(! this.checkTarget()) {//can not shoot because of its target.
				this.target = null;
				this.shooter.setTarget(null);
				return false;
			}
			return true;
		}
		
		@Override
		public boolean canContinueToUse() {
			return this.canUse();
		}
		
		@Override
		public void stop() {
			this.shooter.setShootTick(0);
		}

		@Override
		public void tick() {
			if(! (this.shooter instanceof StarFruitEntity)) {//star fruit don't need to look at target.
				this.shooter.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
			}
			if(this.shooter.isPlantInSuperMode()) {
				this.shooter.startShootAttack();
				this.shooter.setShootTick(0);
				return ;
			}
			final int time = this.shooter.getShootTick();
			final int T = this.shooter.getShootCD();
			if(time >= T) {
				this.shooter.setShootTick(0);
			} else {
				if(time == T - SHOOT_POINT_OFFSET) {
				    this.shooter.startShootAttack();
			    }
				this.shooter.setShootTick(time + 1);
			}
		}
		
		private boolean checkTarget() {
			if(EntityUtil.canAttackEntity(this.shooter, this.target)) {
				if(this.shooter instanceof CatTailEntity) {
					return EntityUtil.canSeeEntity(this.shooter, this.target);
				}
				return this.shooter.getSensing().canSee(this.target);
			}
			return false;
		}
		
	}
	
	protected static class ShooterNearestTargetGoal extends PVZNearestTargetGoal{

		private final PlantShooterEntity shooter;
		
		public ShooterNearestTargetGoal(PlantShooterEntity mobIn, boolean checkSight, boolean memory, float w, float h) {
			super(mobIn, checkSight, memory, w, h);
			this.shooter = mobIn;
		}
		
		@Override
		protected boolean checkOther(LivingEntity entity) {
			return super.checkOther(entity) && this.shooter.checkY(entity);
		}
		
	}
}