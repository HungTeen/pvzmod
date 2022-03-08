package com.hungteen.pvz.common.entity.zombie.roof;

import java.util.Optional;

import com.hungteen.pvz.api.interfaces.IHasWheel;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.entity.ai.goal.attack.PultAttackGoal;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZRandomTargetGoal;
import com.hungteen.pvz.common.entity.bullet.BallEntity;
import com.hungteen.pvz.common.entity.zombie.base.CarZombieEntity;
import com.hungteen.pvz.common.impl.zombie.RoofZombies;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.interfaces.IPult;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.EntitySize;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.level.Level;

public class CatapultZombieEntity extends CarZombieEntity implements IPult,IHasWheel {

	private static final DataParameter<Integer> BALL_COUNT = EntityDataManager.defineId(CatapultZombieEntity.class, DataSerializers.INT);
	private static final float PULT_DISTANCE = 2000;
	
	public CatapultZombieEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void initAttributes() {
		super.initAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_LITTLE_SLOW);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(BALL_COUNT, 0);
	}
	
	@Override
	protected void registerAttackGoals() {
		super.registerAttackGoals();
		this.goalSelector.addGoal(2, new CataPultAttackGoal(this));
	}

	@Override
	protected void registerTargetGoals() {
		this.targetSelector.addGoal(0, new PVZRandomTargetGoal(this, true, true, ZombieUtil.NORMAL_TARGET_RANGE, ZombieUtil.NORMAL_TARGET_HEIGHT));
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(! level.isClientSide && this.getAttackTime() > 0) {
			this.setAttackTime(this.getAttackTime() - 1);
			if(this.getAttackTime() == this.getPultAnimTime() / 2) {
				this.pultBullet();
			}
		}
	}
	
	@Override
	public void spikeWheelBy(LivingEntity entity) {
		this.hurt(PVZEntityDamageSource.thorns(entity), EntityUtil.getMaxHealthDamage(this, 2));
	}
	
	@Override
	public void startPultAttack() {
		this.setAttackTime(this.getPultAnimTime());
	}

	@Override
	public int getPultCD() {
		return 60;
	}

	public int getPultAnimTime() {
		return 20;
	}
	
	@Override
	public float getPultRange() {
		return 28;
	}

	@Override
	public boolean shouldPult() {
		return this.canNormalUpdate() && this.getBallCount() < this.getMaxBallUse();
	}
	
	public boolean checkY(LivingEntity target) {
		return this.getY() + 12 >= target.getY() + target.getBbHeight();
	}
	
	@Override
	public void pultBullet() {
		Optional.ofNullable(this.getTarget()).ifPresent(target -> {
			BallEntity ball = EntityRegister.BALL.get().create(level);
            ball.setPos(this.getX(), this.getY() + 1.7f, this.getZ());
            ball.shootPultBullet(target);
            ball.summonByOwner(this);
            ball.setAttackDamage(this.getAttackDamage());
            EntityUtil.playSound(this, SoundRegister.BASKETBALL.get());
            this.level.addFreshEntity(ball);
            this.setBallCount(this.getBallCount() + 1);
		});
	}
	
	/**
	 * how many ball can it pult.
	 */
	public int getMaxBallUse() {
		return 20;
	}
	
	public float getAttackDamage() {
		return (float) this.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue();
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8F, 2F);
	}
	
    @Override
	public float getLife() {
		return 105;
	}
    
    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
    	super.readAdditionalSaveData(compound);
    	if(compound.contains("ball_count")) {
    		this.setBallCount(compound.getInt("ball_count"));
    	}
    }
    
    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
    	super.addAdditionalSaveData(compound);
    	compound.putInt("ball_count", this.getBallCount());
    }
    
    public void setBallCount(int cnt) {
    	this.entityData.set(BALL_COUNT, cnt);
    }
    
    public int getBallCount() {
    	return this.entityData.get(BALL_COUNT);
    }

    @Override
    public ZombieType getZombieType() {
	    return RoofZombies.CATAPULT_ZOMBIE;
    }
	
	private static final class CataPultAttackGoal extends PultAttackGoal {

		private final CatapultZombieEntity zombie;
		
		public CataPultAttackGoal(CatapultZombieEntity zombie) {
			super(zombie);
			this.zombie = zombie;
		}
		
		@Override
		public void stop() {
		    this.target = null;
		}
		
		@Override
		protected boolean checkTarget(LivingEntity target) {
			return super.checkTarget(target) && this.attacker.distanceToSqr(target) <= this.zombie.getPultRange() * this.zombie.getPultRange() && this.zombie.checkY(target);
		}
		
	}
}
