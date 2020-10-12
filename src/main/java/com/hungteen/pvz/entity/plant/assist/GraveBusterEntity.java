package com.hungteen.pvz.entity.plant.assist;

import com.hungteen.pvz.entity.ai.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.zombie.grassnight.TombStoneEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class GraveBusterEntity extends PVZPlantEntity{

	private static final DataParameter<Boolean> IS_EATING = EntityDataManager.createKey(GraveBusterEntity.class, DataSerializers.BOOLEAN);
	private static final int MAX_LIVE_TICK = 100;
	private int killCount = 0;
	
	public GraveBusterEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(IS_EATING, false);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 5, 3, 2, 0));
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(!world.isRemote) {
			if(this.getAttackTarget() != null && this.getAttackTarget().isAlive()) {//has target
				if(this.getAttackTime() == 0) {// start attack
					if(!this.getAttackTarget().getPassengers().isEmpty()) {//target has passenger
					    return ;
				    }
					this.setEating(true);
					this.startRiding(this.getAttackTarget(), true);
				}
				this.setLiveTick(0);//reset live tick to avoid death
				this.setAttackTime(this.getAttackTime() + 1);
				if(this.getAttackTime() >= this.getAttackCD()) {//deal damage
					this.setAttackTime(0);
					this.killCount ++;
					this.setEating(false);
					if(this.killCount >= this.getMaxKillCnt()) {
						this.remove();
					}
					this.getAttackTarget().attackEntityFrom(PVZDamageSource.causeEatDamage(this, this), this.getAttackTarget().getHealth() * 2);
				}
			}else {
				this.setEating(false);
				this.setAttackTime(0);
			}
			if(this.isEating() && this.getAttackTime() % 20 == 0) {
				this.playSound(SoundRegister.PLANT_HURT.get(), 1f, 1f);
			}
		}
	}
	
	public int getAttackCD() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) {
			int now = (lvl - 1) / 4;
			return 100 - now * 10;
		}
		return 100;
	}
	
	public int getMaxKillCnt() {
		int lvl = this.getPlantLvl();
		if(lvl <= 6) {
			return 1;
		}else if(lvl <= 13) {
			return 2;
		}else if(lvl <= 20) {
			return 3;
		}
		return 1;
	}
	
	@Override
	public int getMaxLiveTick() {
		return MAX_LIVE_TICK;
	}
	
	@Override
	public boolean checkCanPlantTarget(LivingEntity entity) {
		return entity instanceof TombStoneEntity && entity.isAlive() && entity.getPassengers().isEmpty();
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(1f, 1.6f);
	}
	
	public void setEating(boolean is) {
		this.dataManager.set(IS_EATING, is);
	}
	
	public boolean isEating() {
		return this.dataManager.get(IS_EATING);
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("kill_cnt", this.killCount);
		compound.putBoolean("is_eating", this.isEating());
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.killCount = compound.getInt("kill_cnt");
		this.setEating(compound.getBoolean("is_eating"));
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.GRAVE_BUSTER;
	}

	@Override
	public int getSuperTimeLength() {
		return 0;
	}

}
