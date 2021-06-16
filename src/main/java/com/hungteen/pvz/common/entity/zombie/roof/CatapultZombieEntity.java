package com.hungteen.pvz.common.entity.zombie.roof;

import com.hungteen.pvz.common.entity.ai.goal.attack.PultAttackGoal;
import com.hungteen.pvz.common.entity.bullet.BallEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;
import com.hungteen.pvz.utils.interfaces.IPult;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;

public class CatapultZombieEntity extends PVZZombieEntity implements IPult {

	private static final DataParameter<Integer> BALL_COUNT = EntityDataManager.defineId(CatapultZombieEntity.class, DataSerializers.INT);
	private static final float PULT_DISTANCE = 2000;
	
	public CatapultZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.canBeButter = false;
		this.canBeFrozen = false;
		this.maxDeathTime = 1;
		this.canLostHand = false;
		this.canLostHead = false;
	}
	
	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_LITTLE_SLOW);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(BALL_COUNT, 0);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new CataPultAttackGoal(this));
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
		return 40;
	}

	@Override
	public boolean shouldPult() {
		return this.getBallCount() < this.getMaxBallUse();
	}
	
	public boolean checkY(LivingEntity target) {
		return this.getY() + 9 >= target.getY() + target.getBbHeight();
	}
	
	@Override
	protected PVZDamageSource getZombieAttackDamageSource() {
		return PVZDamageSource.causeCrushDamage(this, this);
	}
	
	@Override
	protected float getModifyAttackDamage(Entity entity, float f) {
		if(entity instanceof LivingEntity) {
			return EntityUtil.getCurrentMaxHealth(((LivingEntity) entity));
		}
		return f;
	}
	
	@Override
	public void pultBullet() {
		LivingEntity target = this.getTarget();
		if(target == null) return ;
		BallEntity ball = new BallEntity(level, this);
        ball.setPos(this.getX(), this.getY() + 1.7f, this.getZ());
        ball.shootPultBullet(target);
        EntityUtil.playSound(this, SoundRegister.BASKETBALL.get());
        this.level.addFreshEntity(ball);
        this.setBallCount(this.getBallCount() + 1);
	}
	
	@Override
	protected void onZombieRemove() {
		if(! level.isClientSide) {
			EntityUtil.playSound(this, SoundRegister.CAR_EXPLOSION.get());
		}
		else {
			for(int i = 0; i < 4; ++ i) {
			    this.level.addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
			}
		}
		super.onZombieRemove();
	}
	
	/**
	 * how many ball can it pult.
	 */
	public int getMaxBallUse() {
		return 50;
	}
	
	public float getAttackDamage() {
		return (float) this.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue();
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		if(this.isMiniZombie()) return EntitySize.scalable(0.6F, 1.1F);
		return EntitySize.scalable(0.9F, 2F);
	}
	
    @Override
	public float getLife() {
		return 105;
	}
    
    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
    	super.readAdditionalSaveData(compound);
    	if(compound.contains("ball_count")) {
    		this.setBallCount(compound.getInt("ball_count"));
    	}
    }
    
    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
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
	public Zombies getZombieEnumName() {
		return Zombies.CATAPULT_ZOMBIE;
	}
	
	private static final class CataPultAttackGoal extends PultAttackGoal {

		private final CatapultZombieEntity zombie;
		
		public CataPultAttackGoal(IPult pult) {
			super(pult);
			this.zombie = (CatapultZombieEntity) pult;
			if(! (pult instanceof CatapultZombieEntity)) {
				System.out.println("ERROR : Wrong Pult Attack AI Owner !");
			}
		}
		
		@Override
		public void stop() {
			this.target = null;
		}
		
		@Override
		protected boolean checkTarget(LivingEntity target) {
			return super.checkTarget(target) && this.attacker.distanceToSqr(target) <= PULT_DISTANCE && this.zombie.checkY(target);
		}
		
	}
}
