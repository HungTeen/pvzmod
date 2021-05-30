package com.hungteen.pvz.common.entity.plant.enforce;

import com.hungteen.pvz.common.entity.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.misc.SmallChomperEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
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
import net.minecraft.world.World;

public class ChomperEntity extends PVZPlantEntity {

	public final int ATTACK_CD = 30;
	private final int SUPER_RANGE = 20;
	private static final DataParameter<Integer> REST_TICK = EntityDataManager.defineId(ChomperEntity.class,
			DataSerializers.INT);

	public ChomperEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		entityData.define(REST_TICK, 0);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 5, 2));
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return new EntitySize(0.9f, 1.9f, false);
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if (!level.isClientSide && this.getTarget() != null) {
			this.lookControl.setLookAt(this.getTarget(), 30f, 30f);
		}
		if (!level.isClientSide) {
			if (this.getAttackTime() < this.ATTACK_CD / 2) {// attack stage
				if (this.getRestTick() > 0) {// rest time cannot attack
					this.setAttackTime(0);
					this.setRestTick(this.getRestTick() - 1);
					return;
				}
				if (this.getTarget() == null) {// no target
					this.setAttackTime(0);
					return;
				}
				if (!this.getTarget().isAlive() || this.distanceToSqr(this.getTarget()) > 9) {// target too far away
					this.setTarget(null);
					this.setAttackTime(0);
					return;
				}
				this.setAttackTime(this.getAttackTime() + 1);
				if (this.getAttackTime() == this.ATTACK_CD / 2) {// attack
					this.performAttack();
				}
			} else {
				this.setAttackTime(this.getAttackTime() + 1);
				if (this.getAttackTime() > this.ATTACK_CD) {
					this.setAttackTime(0);
				}
			}
		}
	}

	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		int cnt = this.getSuperAttackCnt();
		for (Entity target : EntityUtil.getEntityTargetableEntity(this, EntityUtil.getEntityAABB(this, this.SUPER_RANGE, this.SUPER_RANGE))) {
			SmallChomperEntity chomper = EntityRegister.SMALL_CHOMPER.get().create(level);
			chomper.setOwner(this);
			EntityUtil.onEntitySpawn(level, chomper, target.blockPosition());
			-- cnt;
			if (cnt == 0) {
				break;
			}
		}
	}

	@Override
	public boolean isInvulnerable() {
		return this.getAttackTime() > 0;
	}

	/**
	 * deal damage
	 */
	private void performAttack() {
		LivingEntity target = this.getTarget();
		if (EntityUtil.getCurrentHealth(target) <= this.getAttackDamage()) {// eat to death need rest
			this.setRestTick(this.getRestCD());
		}
		EntityUtil.playSound(this, SoundRegister.CHOMP.get());
		target.hurt(PVZDamageSource.causeEatDamage(this, this), this.getAttackDamage(target));
	}

	/**
	 * real damage to target
	 */
	public float getAttackDamage(LivingEntity target) {
		if (target.getHealth() <= this.getAttackDamage()) {// eat to death
			return this.getAttackDamage();
		} else {
			return this.getAttackDamage() / 30;
		}
	}

	/**
	 * max damage to target
	 */
	public float getAttackDamage() {
		int lvl = this.getPlantLvl();
		if (lvl <= 19) return 147 + 3 * lvl;
		return 210;
	}

	/**
	 * rest time after each kill
	 */
	public int getRestCD() {
		int lvl = this.getPlantLvl();
		if (lvl <= 19) return 850 - 10 * lvl;
		return 640;
	}

	/**
	 * how many ground chomper to summon
	 */
	public int getSuperAttackCnt() {
		if(this.isPlantInStage(1)) return 3;
		if(this.isPlantInStage(2)) return 4;
		return 5;
	}

	@Override
	public int getSuperTimeLength() {
		return 20;
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("rest_tick", this.getRestTick());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("rest_tick")) {
			this.setRestTick(compound.getInt("rest_tick"));
		}
	}

	public int getRestTick() {
		return this.entityData.get(REST_TICK);
	}

	public void setRestTick(int tick) {
		this.entityData.set(REST_TICK, tick);
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.CHOMPER;
	}

}
