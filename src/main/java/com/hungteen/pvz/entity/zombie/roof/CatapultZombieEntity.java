package com.hungteen.pvz.entity.zombie.roof;

import com.hungteen.pvz.entity.ai.attack.PultAttackGoal;
import com.hungteen.pvz.entity.bullet.BallEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
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
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;

public class CatapultZombieEntity extends PVZZombieEntity implements IPult {

	private static final DataParameter<Integer> BALL_COUNT = EntityDataManager.createKey(CatapultZombieEntity.class, DataSerializers.VARINT);
	private static final float PULT_DISTANCE = 2000;
	
	public CatapultZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.canBeButter = false;
		this.canBeFrozen = false;
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.LITTLE_SLOW);
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(BALL_COUNT, 0);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new CataPultAttackGoal(this));
	}

	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(! world.isRemote && this.getAttackTime() > 0) {
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
		return this.getPosY() + 9 >= target.getPosY() + target.getHeight();
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
		LivingEntity target = this.getAttackTarget();
		if(target == null) return ;
		BallEntity ball = new BallEntity(world, this);
        ball.setPosition(this.getPosX(), this.getPosY() + 1.7f, this.getPosZ());
        ball.shootPultBullet(target);
        EntityUtil.playSound(this, SoundRegister.BASKETBALL.get());
        this.world.addEntity(ball);
        this.setBallCount(this.getBallCount() + 1);
	}
	
	@Override
	protected void onDeathUpdate() {
		super.onDeathUpdate();
		if(this.deathTime == 1) {
			if(! world.isRemote) {
				EntityUtil.playSound(this, SoundRegister.CAR_EXPLOSION.get());
			}
			else {
				for(int i = 0; i < 4; ++ i) {
				    this.world.addParticle(ParticleTypes.EXPLOSION, this.getPosX(), this.getPosY(), this.getPosZ(), 0, 0, 0);
				}
			}
		}
	}
	
	/**
	 * how many ball can it pult.
	 */
	public int getMaxBallUse() {
		return 50;
	}
	
	public float getAttackDamage() {
		return (float) this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getBaseValue();
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.9F, 2F);
	}
	
    @Override
	public float getLife() {
		return 105;
	}
    
    @Override
    public void readAdditional(CompoundNBT compound) {
    	super.readAdditional(compound);
    	if(compound.contains("ball_count")) {
    		this.setBallCount(compound.getInt("ball_count"));
    	}
    }
    
    @Override
    public void writeAdditional(CompoundNBT compound) {
    	super.writeAdditional(compound);
    	compound.putInt("ball_count", this.getBallCount());
    }
    
    public void setBallCount(int cnt) {
    	this.dataManager.set(BALL_COUNT, cnt);
    }
    
    public int getBallCount() {
    	return this.dataManager.get(BALL_COUNT);
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
		public void resetTask() {
			this.target = null;
		}
		
		@Override
		protected boolean checkTarget(LivingEntity target) {
			return super.checkTarget(target) && this.attacker.getDistanceSq(target) <= PULT_DISTANCE && this.zombie.checkY(target);
		}
		
	}
}
