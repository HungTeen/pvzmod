package com.hungteen.pvz.common.entity.plant.assist;

import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.grassnight.AbstractTombStoneEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class GraveBusterEntity extends PVZPlantEntity{

	private static final int MAX_LIVE_TICK = 100;
	private int killCount = 0;
	
	public GraveBusterEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canCollideWithPlant = false;
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new PVZNearestTargetGoal(this, true, false, 5, 5));
		this.goalSelector.addGoal(0, new EatTombStoneGoal(this));
	}
	
	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(!this.level.isClientSide) {
			if(this.isEatingTomb()) {
			    if(this.getAttackTime() % 20 == 10) {
				    EntityUtil.playSound(this, SoundRegister.PLANT_HURT.get());
			    }
			    this.setExistTick(0);
			}
			if(this.getExistTick() > MAX_LIVE_TICK) {
				this.remove();
			}
		}
	}
	
	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		final float range = 10;
		int cnt = this.getSuperAttackCnt();
		for (LivingEntity target : EntityUtil.getTargetableLivings(this, EntityUtil.getEntityAABB(this, range, range))) {
			if(! (target instanceof AbstractTombStoneEntity)) {
				continue;
			}
			GraveBusterEntity buster = EntityRegister.GRAVE_BUSTER.get().create(level);
			PlantUtil.copyPlantData(buster, this);
			EntityUtil.onEntitySpawn(level, buster, target.blockPosition());
			buster.startRiding(target);
			buster.setTarget(target);
			if (-- cnt == 0) {
				break;
			}
		}
	}
	
	/**
	 * {@link EatTombStoneGoal#canUse()}
	 */
	public boolean isEatingTomb() {
		return this.getAttackTime() > 0 && this.getVehicle() instanceof AbstractTombStoneEntity;
	}
	
	public int getEatTombCD() {
		return PlantUtil.getPlantAverageProgress(this, 100, 20);
	}
	
	public int getMaxKillCnt() {
		return MathUtil.getProgressByDif(5, 1, this.getPlantLvl(), PlantUtil.MAX_PLANT_LEVEL, 1, 4);
	}
	
	/**
	 * how many gravebuster to summon.
	 */
	public int getSuperAttackCnt() {
		return this.isPlantInStage(1) ? 3 : this.isPlantInStage(2) ? 4 : 5;
	}
	
	@Override
	public boolean canPlantTarget(Entity entity) {
		return entity instanceof AbstractTombStoneEntity && (entity.getPassengers().isEmpty() || entity.is(this.getVehicle()));
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(1f, 1.6f);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("kill_cnt", this.killCount);
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("kill_cnt")) {
			this.killCount = compound.getInt("kill_cnt");
		}
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.GRAVE_BUSTER;
	}

	@Override
	public int getSuperTimeLength() {
		return 20;
	}
	
	static class EatTombStoneGoal extends Goal{
		
		private GraveBusterEntity buster;
		private LivingEntity target;
		
		public EatTombStoneGoal(GraveBusterEntity buster) {
			this.buster = buster;
		}
		
		@Override
		public boolean canUse() {
			final LivingEntity target = this.buster.getTarget();
			if(this.buster.isEatingTomb()) {
				return true;
			}
			if(! EntityUtil.isEntityValid(target) || ! this.buster.canPlantTarget(target)) {
				this.buster.setTarget(null);
			    this.target = null;
				return false;
			}
			this.target = target;
			this.buster.startRiding(this.target, true);
			return true;
		}
		
		@Override
		public void stop() {
			this.target = null;
		}
		
		@Override
		public boolean canContinueToUse() {
			return this.buster.isEatingTomb();
		}
		
		@Override
		public void tick() {
			if(! this.buster.canPlantNormalUpdate()) {
				return ;
			}
			final int tick = this.buster.getAttackTime();
			if(tick >= this.buster.getEatTombCD()) {
				this.buster.setAttackTime(0);
				++ this.buster.killCount;
				this.target.hurt(PVZDamageSource.causeEatDamage(this.buster, this.buster), EntityUtil.getMaxHealthDamage(this.buster.getTarget(), 1.5F));
			    if(this.buster.killCount >= this.buster.getMaxKillCnt()) {
					this.buster.remove();
				}
			} else {
				this.buster.setAttackTime(tick + 1);
			}
		}
	}
}
