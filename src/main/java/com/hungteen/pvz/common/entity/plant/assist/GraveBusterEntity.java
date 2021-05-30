package com.hungteen.pvz.common.entity.plant.assist;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.grassnight.TombStoneEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class GraveBusterEntity extends PVZPlantEntity{

	private static final DataParameter<Boolean> IS_EATING = EntityDataManager.defineId(GraveBusterEntity.class, DataSerializers.BOOLEAN);
	private static final int MAX_LIVE_TICK = 100;
	private int killCount = 0;
	
	public GraveBusterEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canBeCharmed = false;
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(IS_EATING, false);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<TombStoneEntity>(this, TombStoneEntity.class, 5, true, true, (tomb)-> {
			return tomb.getPassengers().isEmpty();
		}));
		this.targetSelector.addGoal(0, new EatTombStoneGoal(this));
	}
	
	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(5);
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(this.isEating()) {
			this.setLiveTick(0);
			if(this.getAttackTime() % 20 == 0) {
				EntityUtil.playSound(this, SoundRegister.PLANT_HURT.get());
			}
		}
	}
	
	public int getAttackCD() {
		int lvl = this.getPlantLvl();
		if(lvl <= 19) {
			return 102 - 2 * lvl;
		}
		return 60;
	}
	
	public int getMaxKillCnt() {
		if(this.isPlantInStage(1)) return 1;
		if(this.isPlantInStage(2)) return 2;
		return 3;
	}
	
	public int getMaxLiveTick() {
		return MAX_LIVE_TICK;
	}
	
	@Override
	protected boolean canPlantTarget(LivingEntity entity) {
		return entity instanceof TombStoneEntity && entity.isAlive() && entity.getPassengers().isEmpty();
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(1f, 1.6f);
	}
	
	public void setEating(boolean is) {
		this.entityData.set(IS_EATING, is);
	}
	
	public boolean isEating() {
		return this.entityData.get(IS_EATING);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("kill_cnt", this.killCount);
		compound.putBoolean("is_eating", this.isEating());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("kill_cnt")) {
			this.killCount = compound.getInt("kill_cnt");
		}
		if(compound.contains("is_eating")) {
			this.setEating(compound.getBoolean("is_eating"));
		}
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.GRAVE_BUSTER;
	}

	@Override
	public int getSuperTimeLength() {
		return 0;
	}
	
	static class EatTombStoneGoal extends Goal{
		
		private GraveBusterEntity buster;
		private LivingEntity target;
		
		public EatTombStoneGoal(GraveBusterEntity buster) {
			this.buster = buster;
		}
		
		@Override
		public boolean canUse() {
			LivingEntity target = this.buster.getTarget();
			if(target == null || !target.isAlive()) {
				return false;
			}
			if(!target.getPassengers().isEmpty()) {
				this.buster.setTarget(null);
				this.target = null;
				return false;
			}
			this.target = target;
			return true;
		}
		
		@Override
		public void start() {
			this.buster.setEating(true);
			this.buster.startRiding(this.target, true);
		}
		
		@Override
		public void stop() {
			this.buster.setEating(false);
			this.target = null;
		}
		
		@Override
		public boolean canContinueToUse() {
			return this.target !=null && this.target instanceof TombStoneEntity;
		}
		
		@Override
		public void tick() {
			if(!this.buster.canPlantNormalUpdate()) {
				return ;
			}
			this.buster.setAttackTime(this.buster.getAttackTime() + 1);
			if(this.buster.getAttackTime() >= this.buster.getAttackCD()) {
				this.buster.setAttackTime(0);
				this.buster.killCount ++;
				this.buster.setEating(false);
				this.target.hurt(PVZDamageSource.causeEatDamage(this.buster, this.buster), this.target.getMaxHealth() * 1.5f);
				if(this.buster.killCount >= this.buster.getMaxKillCnt()) {
					this.buster.remove();
				}
			}
		}
	}
}
