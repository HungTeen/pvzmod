package com.hungteen.pvz.entity.plant.enforce;

import com.hungteen.pvz.entity.ai.target.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.misc.SmallChomperEntity;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
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
	private static final DataParameter<Integer> REST_TICK = EntityDataManager.createKey(ChomperEntity.class,
			DataSerializers.VARINT);

	public ChomperEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerData() {
		super.registerData();
		dataManager.register(REST_TICK, 0);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 5, 2));
	}

	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.9f, 1.9f, false);
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if (!world.isRemote && this.getAttackTarget() != null) {
			this.lookController.setLookPositionWithEntity(this.getAttackTarget(), 30f, 30f);
		}
		if (!world.isRemote) {
			if (this.getAttackTime() < this.ATTACK_CD / 2) {// attack stage
				if (this.getRestTick() > 0) {// rest time cannot attack
					this.setAttackTime(0);
					this.setRestTick(this.getRestTick() - 1);
					return;
				}
				if (this.getAttackTarget() == null) {// no target
					this.setAttackTime(0);
					return;
				}
				if (!this.getAttackTarget().isAlive() || this.getDistanceSq(this.getAttackTarget()) > 9) {// target too far away
					this.setAttackTarget(null);
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
			SmallChomperEntity chomper = EntityRegister.SMALL_CHOMPER.get().create(world);
			chomper.setOwner(this);
			EntityUtil.onEntitySpawn(world, chomper, target.getPosition());
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
		LivingEntity target = this.getAttackTarget();
		if (EntityUtil.getCurrentHealth(target) <= this.getAttackDamage()) {// eat to death need rest
			this.setRestTick(this.getRestCD());
		}
		EntityUtil.playSound(this, SoundRegister.CHOMP.get());
		target.attackEntityFrom(PVZDamageSource.causeEatDamage(this, this), this.getAttackDamage(target));
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
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("rest_tick", this.getRestTick());
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("rest_tick")) {
			this.setRestTick(compound.getInt("rest_tick"));
		}
	}

	public int getRestTick() {
		return this.dataManager.get(REST_TICK);
	}

	public void setRestTick(int tick) {
		this.dataManager.set(REST_TICK, tick);
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.CHOMPER;
	}

}
