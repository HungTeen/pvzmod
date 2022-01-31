package com.hungteen.pvz.common.entity.zombie.grass;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.impl.zombie.GrassZombies;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.WorldUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public class DancingZombieEntity extends PVZZombieEntity{

	private static final DataParameter<Integer> SUMMON_TIME = EntityDataManager.defineId(DancingZombieEntity.class, DataSerializers.INT);
	public static final int MAX_DANCER_NUM = 4;
	private static final float[][] POS_OFFSET = new float[][] {{2, 0}, {-2, 0}, {0, 2}, {0, -2}};
	public static final int SUMMON_CD = 10;
	public static final int DANCE_CD = 100;
	private static final int MIN_REST_CD = 60;
	private static final int MAX_REST_CD = 300;
	private final List<Optional<BackupDancerEntity>> Dancers = new ArrayList<>(MAX_DANCER_NUM);
	private int summonCnt = 0;
	private int restTick = 0;
	
	public DancingZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canCollideWithZombie = false;
		this.setRestTick();
		for(int i = 0; i < MAX_DANCER_NUM; ++ i) {
			this.Dancers.add(Optional.empty());
		}
		this.clearDancers();
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SUMMON_TIME, 0);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new ZombieDanceGoal(this));
		this.goalSelector.addGoal(0, new SummonDancerGoal(this));
	}
	
	/**
	 * update all dancers.
	 * {@link ZombieDanceGoal}
	 */
	public void tickDance() {
		for(int i = 0; i < MAX_DANCER_NUM; ++ i) {
			this.Dancers.get(i).ifPresent(dancer -> {
				
			});
		}
	}
	
	/**
	 * summon dancers at empty place.
	 * {@link SummonDancerGoal#tick()}
	 */
	public void summonEmptyDancers() {
		for(int i = 0; i < MAX_DANCER_NUM; ++ i) {
			if(! this.Dancers.get(i).isPresent() && this.summonCnt < this.getMaxSummonCnt()) {//no dancer here.
				BackupDancerEntity dancer = EntityRegister.BACKUP_DANCER.get().create(level);
				BlockPos pos = WorldUtil.getSuitableHeightPos(level, this.blockPosition().offset(POS_OFFSET[i][0], 0, POS_OFFSET[i][1]));
				dancer.setDancingOwner(this);
				dancer.setZombieRising();
				ZombieUtil.copySummonZombieData(this, dancer);
				this.setDancer(i, dancer);
				++ this.summonCnt;
				EntityUtil.onEntitySpawn(level, dancer, pos);
				this.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 40, 5, false, false));
			}
		}
	}
	
	/**
	 * {@link ZombieDanceGoal#tick()}
	 */
	protected void setRestTick() {
		this.restTick = MathUtil.getRandomMinMax(random, MIN_REST_CD, MAX_REST_CD);
	}
	
	protected void updateSpeed() {
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.getAttackTime() > 0 ? 0 : getWalkSpeed());
	}

	@Override
	public float getWalkSpeed() {
		return ZombieUtil.WALK_LITTLE_SLOW;
	}

	/**
	 * can summon extra dancer to fill the place.
	 * {@link SummonDancerGoal#canUse()}
	 */
	protected boolean hasEmptyPlace() {
		for(int i = 0; i < MAX_DANCER_NUM; ++ i) {
			if(! this.Dancers.get(i).isPresent()) {
				return true;
			} else {
				if(! EntityUtil.isEntityValid(this.Dancers.get(i).get())) {
					this.Dancers.set(i, Optional.empty());
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * {@link #DancingZombieEntity(EntityType, World)}
	 */
	private void clearDancers() {
		for(int i = 0;i < MAX_DANCER_NUM; ++ i) {
			this.Dancers.set(i, Optional.empty());
		}
	}
	
	private void setDancer(int pos, Entity entity) {
		if(EntityUtil.isEntityValid(entity) && entity instanceof BackupDancerEntity) {
			this.Dancers.set(pos, Optional.ofNullable((BackupDancerEntity) entity));
		}
	}
	
	public boolean isDancing() {
		return this.getAttackTime() > 0;
	}
	
	/**
	 * set limit to summon count.
	 */
	public int getMaxSummonCnt() {
		return 20;
	}
	
	@Override
	public float getLife() {
		return 50;
	}

	@Override
	public int getArmorToughness() {
		return 10;
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("zombie_summon_tick")) {
			this.setSummonTime(compound.getInt("zombie_summon_tick"));
		}
		if(compound.contains("zombie_rest_tick")) {
			this.restTick = compound.getInt("zombie_rest_tick");
		}
		if(compound.contains("dancer_ids")) {
			CompoundNBT nbt = compound.getCompound("dancer_ids");
			for(int i = 0; i < MAX_DANCER_NUM; ++ i) {
				if(nbt.contains("dancer_" + i)) {
				    this.setDancer(i, level.getEntity(nbt.getInt("dancer_" + i)));
				}
			}
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("zombie_summon_tick", this.getSummonTime());
		compound.putInt("zombie_rest_tick", this.restTick);
		CompoundNBT nbt = new CompoundNBT();
		for(int i = 0; i < MAX_DANCER_NUM; ++ i) {
			if(! this.Dancers.get(i).isPresent()) {
				continue;
			}
			BackupDancerEntity dancer = this.Dancers.get(i).get();
			if(EntityUtil.isEntityValid(dancer)) {
				nbt.putInt("dancer_" + i, dancer.getId());
			}
		}
		compound.put("dancer_ids", nbt);
	}
	
	public int getSummonTime(){
		return entityData.get(SUMMON_TIME);
	}
	
	public void setSummonTime(int time){
		entityData.set(SUMMON_TIME, time);
	}

	@Override
	public ZombieType getZombieType() {
		return GrassZombies.DANCING_ZOMBIE;
	}
	
	static class ZombieDanceGoal extends Goal{

		private final DancingZombieEntity dancer;
		
		public ZombieDanceGoal(DancingZombieEntity dancer) {
			this.dancer = dancer;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}
		
		@Override
		public boolean canUse() {
			if(this.dancer.getAttackTime() > 0) {//dancer is dancing at last game.
				return true;
			}
			if(this.dancer.restTick > 0) {//still in rest time, so can not dance.
				-- this.dancer.restTick;
				return false;
			}
			if(this.dancer.getRandom().nextFloat() < 0.05F) {
				this.dancer.setAttackTime(DANCE_CD);
				return true;
			}
			return false;
		}
		
		@Override
		public void start() {
			this.dancer.updateSpeed();
		}
		
		@Override
		public boolean canContinueToUse() {
			return this.dancer.getAttackTime() > 0;
		}
		
		@Override
		public void tick() {
			int tick = this.dancer.getAttackTime();
			if(tick == 1) {
				this.dancer.setRestTick();
			}
			this.dancer.tickDance();
			this.dancer.setAttackTime(Math.max(0, tick - 1));
		}
		
		@Override
		public void stop() {
			this.dancer.updateSpeed();
		}
		
	}
	
	static class SummonDancerGoal extends Goal{

		private final DancingZombieEntity dancer;
		
		public SummonDancerGoal(DancingZombieEntity dancer) {
			this.dancer = dancer;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}
		
		@Override
		public boolean canUse() {
			if(this.dancer.summonCnt >= this.dancer.getMaxSummonCnt()) {//up to limit already.
				return false;
			}
			if(this.dancer.getSummonTime() > 0) {
				return true;
			}
			if(this.dancer.restTick > 0 && this.dancer.hasEmptyPlace() && this.dancer.getRandom().nextFloat() < 0.05F) {
				this.dancer.setSummonTime(SUMMON_CD);
				return true;
			}
			return false;
		}
		
		@Override
		public void start() {
			this.dancer.updateSpeed();
		}
		
		@Override
		public boolean canContinueToUse() {
			return this.dancer.getSummonTime() > 0;
		}
		
		@Override
		public void tick() {
			int tick = this.dancer.getSummonTime();
			if(tick == 5) {
				this.dancer.summonEmptyDancers();
			}
			this.dancer.setSummonTime(Math.max(0, tick - 1));
		}
		
		@Override
		public void stop() {
			this.dancer.updateSpeed();
		}
		
	}

}
