package com.hungteen.pvz.entity.plant.enforce;

import com.hungteen.pvz.entity.ai.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SquashEntity extends PVZPlantEntity{

	protected int extraChance = 0;
	private final int range = 3;
	private int restTick = 0;
	private final int CD = 20;
	
	public SquashEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, range, 1));
	}

	@Override
	protected boolean shouldLockXZ() {
		return false;
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(!world.isRemote) {
			if(restTick>0) {
				--restTick;
				return ;
			}
			LivingEntity target = this.getAttackTarget();
			if(target != null && EntityUtil.isSuitableTargetInRange(this, target, range)) {
				this.setAttackTarget(null);
			}
			if(this.getAttackTarget()!=null) {
				this.getLookController().setLookPositionWithEntity(getAttackTarget(), 30f, 30f);
			}
			if(this.getAttackTime()>0) {
				if(EntityUtil.isOnGround(this)) {
//					System.out.println("on ground!");
					this.playSound(SoundRegister.GROUND_SHAKE.get(), 1f, 1f);
					this.dealDamage();
					if(this.extraChance>0) {
						--this.extraChance;
					}else {
						if(this.getRNG().nextInt(100)<this.getDeathChance()) {
							this.remove();
						}
					}
					this.setAttackTime(0);
					this.restTick = CD;
				}
			}else {
				if(this.getAttackTarget()!=null) {
					this.playSound(SoundRegister.SQUASH_HMM.get(), 1f, 1f);
					this.jumpToTarget(this.getAttackTarget());
				}
			}
		}
	}
	
	@Override
	protected boolean shouldCollideWithEntity(LivingEntity target) {
		return !(target instanceof PlayerEntity) && !EntityUtil.checkCanEntityAttack(this, target);
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		if(!world.isRemote) {
		    this.extraChance = this.getSuperBonusChance();
		}
	}
	
	protected void dealDamage(){
		this.setAttackTime(0);
		for(Entity entity : EntityUtil.getEntityTargetableEntity(this, EntityUtil.getEntityAABB(this, 0.5f, 0.5f))) {
			entity.attackEntityFrom(PVZDamageSource.causeNormalDamage(this, this), this.getAttackDamage());
		}
	}
	
	/**
	 * jump to the top of the target
	 */
	private void jumpToTarget(LivingEntity target) {
		int tick=10;
		Vec3d pos = target.getPositionVec().add(target.getMotion().mul(tick, tick, tick));
		this.setPosition(pos.getX(),pos.getY()+target.getHeight()+1,pos.getZ());
		this.setMotion(this.getMotion().getX(),0,this.getMotion().getZ());
		this.setAttackTime(1);
	}
	
	/**
	 * extra smash times
	 */
	protected int getSuperBonusChance(){
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 2;
		else if(lvl<=13) return 3;
		else if(lvl<=20) return 4;
		return 2;
	}
	
	public float getAttackDamage(){
		int lvl = this.getPlantLvl();
		if(lvl<=20) {
			int now=(lvl-1)/5;
			return 140+now*20;
		}
		return 140;
	}
	
	/**
	 * die chance for each smash
	 */
	public int getDeathChance(){
		int lvl=this.getPlantLvl();
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 90-5*now;
		}
		return 90;
	}
	
	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		if(source==DamageSource.FALL) {
		    return true;
	    }
		return PVZDamageSource.isEnforceDamage(source) || super.isInvulnerableTo(source);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.SQUASH;
	}

	@Override
	public int getSuperTimeLength() {
		return 20;
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.extraChance = compound.getInt("extra_chance");
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("extra_chance", this.extraChance);
	}

}
