package com.hungteen.pvz.entity.zombie.roof;

import java.util.Random;

import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.entity.ai.target.PVZRandomTargetGoal;
import com.hungteen.pvz.entity.bullet.TargetArrowEntity;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class BungeeZombieEntity extends PVZZombieEntity {

	public static final Zombies[] ZOMBIES = new Zombies[] {Zombies.NORMAL_ZOMBIE, Zombies.CONEHEAD_ZOMBIE, Zombies.BUCKETHEAD_ZOMBIE, Zombies.SCREENDOOR_ZOMBIE};
	private static final DataParameter<Integer> BUNGEE_STATE = EntityDataManager.defineId(BungeeZombieEntity.class, DataSerializers.INT);
	private static final DataParameter<Integer> BUNGEE_TYPE = EntityDataManager.defineId(BungeeZombieEntity.class, DataSerializers.INT);
	private static final DataParameter<BlockPos> ORIGIN_POS = EntityDataManager.defineId(BungeeZombieEntity.class, DataSerializers.BLOCK_POS);
	private LivingEntity stealTarget;
	
	public BungeeZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.canBeMini = false;
		this.canBeButter = false;
		this.canBeFrozen = false;
		this.canBeStealByBungee = false;
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(BUNGEE_STATE, BungeeStates.WAIT.ordinal());
		this.entityData.define(BUNGEE_TYPE, BungeeTypes.STEAL.ordinal());
		this.entityData.define(ORIGIN_POS, BlockPos.ZERO);
	}
	
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		this.setOriginPos(blockPosition());
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	public void tick() {
		this.noPhysics = true;
		super.tick();
	}
	
	@Override
	protected void registerGoals() {
		this.targetSelector.addGoal(0, new BungeeRandomTargetGoal(this, true, 50, 60));
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(! level.isClientSide) {
			if(this.getBungeeState() == BungeeStates.PUSH_BACK) {
				this.setAttackTime(this.getAttackTime() - 1);
				this.setDeltaMovement(new Vector3d(0, 1.2, 0));
				if(this.getAttackTime() <= - 60) {
					this.remove();
					if(this.getBungeeType() == BungeeTypes.SUMMON && EntityUtil.isEntityValid(this.getStealTarget())) {
						this.getStealTarget().remove();
					}
				}
				return ;
			}
			if(this.getBungeeType() == BungeeTypes.STEAL) {
				this.tickSteal();
			} else if(this.getBungeeType() == BungeeTypes.HELP) {
				this.tickHelp();
			} else if(this.getBungeeType() == BungeeTypes.SUMMON) {
				this.tickSummon();
			}
		}
	}
	
	protected void tickHelp() {
		if(! this.isSuitableTarget(this.getStealTarget())) {
			if(this.getAttackTime() >= 0) this.setAttackTime(- 1);
			this.setBungeeState(BungeeStates.UP);//chosen target was not fitable then die.
		}
		if(this.getBungeeState() == BungeeStates.WAIT) {
			if(this.isSuitableTarget(this.getStealTarget())) {
				this.setBungeeState(BungeeStates.DOWN);
				EntityUtil.playSound(this.getStealTarget(), SoundRegister.BUNGEE_SCREAM.get());
			}
		} else if(this.getBungeeState() == BungeeStates.DOWN) {// move from sky close to target
			if(this.isNearToTarget()) {
				this.setAttackTime(this.getStayTime());
				this.getStealTarget().startRiding(this);
				this.setBungeeState(BungeeStates.CATCH);
				EntityUtil.playSound(this, SoundRegister.DRAG.get());
			} else {
				this.moveToTarget();
			}
		} else if(this.getBungeeState() == BungeeStates.CATCH) {// wait some time to catch
			this.setDeltaMovement(Vector3d.ZERO);
			if(this.getAttackTime() > 0) {
				this.setAttackTime(this.getAttackTime() - 1);
			}
			if(this.getAttackTime() == 0) {
				this.setBungeeState(BungeeStates.UP);
			}
		} else if(this.getBungeeState() == BungeeStates.UP) {
//			System.out.println("up");
			this.setAttackTime(this.getAttackTime() - 1);
			this.moveBackToOrigin();
			if(this.getAttackTime() < - 60) {
				this.dealDamage();
				return ;
			}
		}
	}
	
	protected void tickSteal() {
		if(this.getBungeeState() != BungeeStates.WAIT && ! this.isSuitableTarget(this.getStealTarget())) {
			this.setBungeeState(BungeeStates.BACK_WAIT); //chosen target was not fitable then die.
		}
		if(this.getBungeeState() == BungeeStates.WAIT) {
			if(this.isSuitableTarget(this.getTarget())) {
				if(this.getY() - this.getTarget().getY() <= 15) {
					this.setOriginPos(this.getTarget().blockPosition().above(20));
					this.setBungeeState(BungeeStates.BACK_WAIT);
					return ;
				}
				this.setBungeeState(BungeeStates.DOWN);
				this.setStealTarget(this.getTarget());
				this.shootArrowToTarget();
				EntityUtil.playSound(this.getStealTarget(), SoundRegister.BUNGEE_SCREAM.get());
			}
		} else if(this.getBungeeState() == BungeeStates.BACK_WAIT) {
			this.moveBackToOrigin();
			if(this.distanceToSqr(this.getOriginPos().getX(), this.getOriginPos().getY(), this.getOriginPos().getZ()) <= 2) {
				this.setBungeeState(BungeeStates.WAIT);
				this.setDeltaMovement(Vector3d.ZERO);
			}
		} else if(this.getBungeeState() == BungeeStates.DOWN) {
			if(this.isNearToTarget()) {
				this.setAttackTime(this.getStayTime());
				this.getStealTarget().startRiding(this);
				this.setBungeeState(BungeeStates.CATCH);
				EntityUtil.playSound(this, SoundRegister.DRAG.get());
			} else {
				this.moveToTarget();
			}
		} else if(this.getBungeeState() == BungeeStates.CATCH) {// wait some time to catch
			this.setDeltaMovement(Vector3d.ZERO);
			if(this.getAttackTime() > 0) {
				this.setAttackTime(this.getAttackTime() - 1);
			}
			this.getStealTarget().startRiding(this);
			if(this.getAttackTime() == 0) {
				this.setBungeeState(BungeeStates.UP);
			}
		} else if(this.getBungeeState() == BungeeStates.UP) {
			this.setAttackTime(this.getAttackTime() - 1);
			this.moveBackToOrigin();
			this.getStealTarget().startRiding(this);
			if(this.getAttackTime() < - 60) {
				this.dealDamage();
				return ;
			}
		}
	}
	
	public void pushBack() {
		this.setBungeeState(BungeeStates.PUSH_BACK);
	}
	
	@Override
	public boolean checkCanZombieTarget(LivingEntity target) {
		if(! canBungeeSteal(target)) return false;
		return super.checkCanZombieTarget(target);
	}
	
	protected void tickSummon() {
		if(this.getBungeeState() != BungeeStates.WAIT && ! EntityUtil.isEntityValid(this.getStealTarget())) {
			this.setBungeeState(BungeeStates.UP);
		}
		if(this.getBungeeState() == BungeeStates.WAIT) {
			if(this.getPassengers().isEmpty()) {
				this.summonZombie();
				this.setBungeeState(BungeeStates.DOWN);
				EntityUtil.playSound(this.getStealTarget(), SoundRegister.BUNGEE_SCREAM.get());
			}
		} else if(this.getBungeeState() == BungeeStates.DOWN) {
			if(level.getBlockState(this.blockPosition().below()).getBlock() != Blocks.AIR) {
				this.getStealTarget().stopRiding();
				this.setAttackTime(0);
				this.setBungeeState(BungeeStates.UP);
			} else {
				this.getStealTarget().startRiding(this);
				this.setDeltaMovement(new Vector3d(0, - 0.5D, 0));
			}
		} else if(this.getBungeeState() == BungeeStates.UP) {
			this.moveBackToOrigin();
			this.setAttackTime(this.getAttackTime() - 1);
			if(this.getAttackTime() < - 60) {
				this.remove();
				return ;
			}
		}
	}
	
	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return this.getBungeeState() != BungeeStates.CATCH || super.isInvulnerableTo(source);
	}
	
	private void summonZombie() {
		int pos = this.getRandom().nextInt(ZOMBIES.length);
		Zombies type = ZOMBIES[pos];
		PVZZombieEntity zombie = ZombieUtil.getZombieEntity(level, type);
		EntityUtil.onMobEntitySpawn(level, zombie, blockPosition());
		zombie.startRiding(this);
		this.setStealTarget(zombie);
	}
	
	@Override
	public double getPassengersRidingOffset() {
		return 0;
	}
	
	/**
	 * is chosen target suitable
	 */
	private boolean isSuitableTarget(LivingEntity target) {
		if(! EntityUtil.isEntityValid(target)) return false;
		if(target instanceof BungeeZombieEntity) return false;
		if(target.getVehicle() instanceof BungeeZombieEntity) {
			return target.getVehicle().is(this);
		}
		return true;
	}
	
	private void dealDamage() {
		this.getStealTarget().hurt(PVZDamageSource.causeNormalDamage(this, this), EntityUtil.getCurrentMaxHealth(this.getStealTarget()));
		this.remove();
	}
	
	public int getStayTime() {
		return 60;
	}
	
	private void moveToTarget() {
		if(this.getStealTarget() == null) return;
		Vector3d vec = this.getStealTarget().position().subtract(this.position()).normalize();
		double speed = 0.5D;
		this.setDeltaMovement(vec.multiply(speed, speed, speed));
	}
	
	private void moveBackToOrigin() {
		Vector3d vec = new Vector3d(this.getOriginPos().getX() - this.getX(), this.getOriginPos().getY() - this.getY(), this.getOriginPos().getZ() - this.getZ()).normalize();
		double speed = 0.35D;
		this.setDeltaMovement(vec.multiply(speed, speed, speed));
	}
	
	private boolean isNearToTarget() {
		if(this.getStealTarget() == null) return false;
		return this.distanceToSqr(this.getStealTarget()) <= 2;
	}
	
	private void shootArrowToTarget() {
		TargetArrowEntity arrow = new TargetArrowEntity(level, this);
		arrow.setPos(this.getX(), this.getY(), this.getZ());
		arrow.shoot(this.getStealTarget());
		level.addFreshEntity(arrow);
	}

	public static boolean canBungeeSpawn(EntityType<? extends PVZZombieEntity> zombieType, IWorld worldIn,
			SpawnReason reason, BlockPos pos, Random rand) {
		if(!hasZombieSpawn(zombieType, worldIn)) return false;
		return worldIn.getBrightness(LightType.BLOCK, pos) < 8 && worldIn.getDifficulty() != Difficulty.PEACEFUL && (reason == SpawnReason.SPAWNER || worldIn.isEmptyBlock(pos));
	}
	
	@Override
	public float getLife() {
		return 45;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(1.2F, 2F);
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.BUNGEE_ZOMBIE;
	}
	
	@Override
	public boolean rideableUnderWater() {
		return true;
	}
	
	@Override
	public boolean isNoGravity() {
		return true;
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("bungee_state")) {
			this.setBungeeState(BungeeStates.values()[compound.getInt("bungee_state")]);
		}
		if(compound.contains("bungee_type")) {
			this.setBungeeType(BungeeTypes.values()[compound.getInt("bungee_type")]);
		}
		if(compound.contains("steal_target")) {
			this.stealTarget = (LivingEntity) level.getEntity(compound.getInt("steal_target"));
		}
		if(compound.contains("origin_pos")) {
			CompoundNBT nbt = compound.getCompound("origin_pos");
			this.setOriginPos(new BlockPos(nbt.getInt("origin_pos_x"), nbt.getInt("origin_pos_y"), nbt.getInt("origin_pos_z")));
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("bungee_state", this.getBungeeState().ordinal());
		compound.putInt("bungee_type", this.getBungeeType().ordinal());
		if(this.stealTarget != null) {
			compound.putInt("steal_target", this.stealTarget.getId());
		}
		CompoundNBT nbt = new CompoundNBT();
		nbt.putInt("origin_pos_x", this.getOriginPos().getX());
		nbt.putInt("origin_pos_y", this.getOriginPos().getY());
		nbt.putInt("origin_pos_z", this.getOriginPos().getZ());
		compound.put("origin_pos", nbt);
	}
	
	public void setBungeeState(BungeeStates state) {
		this.entityData.set(BUNGEE_STATE, state.ordinal());
	}
	
	public void setBungeeType(BungeeTypes type) {
		this.entityData.set(BUNGEE_TYPE, type.ordinal());
	}
	
	public BungeeStates getBungeeState() {
		return BungeeStates.values()[this.entityData.get(BUNGEE_STATE)];
	}
	
	public BungeeTypes getBungeeType() {
		return BungeeTypes.values()[this.entityData.get(BUNGEE_TYPE)];
	}
	
	public BlockPos getOriginPos() {
		return this.entityData.get(ORIGIN_POS);
	}
	
	public void setOriginPos(BlockPos pos) {
		this.entityData.set(ORIGIN_POS, pos);
	}
	
	public LivingEntity getStealTarget() {
		return this.stealTarget;
	}
	
	public void setStealTarget(LivingEntity target) {
		this.stealTarget = target;
	}
	
	public static boolean canBungeeSteal(Entity target) {
		if(! target.canChangeDimensions()) return false;
		if(! (target instanceof LivingEntity)) return false;
		if(target instanceof PVZZombieEntity) {
			return ((PVZZombieEntity) target).canBeStealByBungee();
		}
		if(target instanceof PVZPlantEntity) {
			return true;
		}
		return ((LivingEntity) target).getMaxHealth() < 100;
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.BUNGEE_ZOMBIE;
	}
	
	public static enum BungeeStates {
		WAIT,
		BACK_WAIT,
		DOWN,
		CATCH,
		UP,
		PUSH_BACK,
	}
	
	public static enum BungeeTypes {
		STEAL,//wait, shoot, down, up
		HELP,//down, up
		SUMMON//down, up
	}
	
	public static class BungeeRandomTargetGoal extends PVZRandomTargetGoal {

		public BungeeRandomTargetGoal(MobEntity mobIn, boolean checkSight, float w, float h) {
			super(mobIn, checkSight, w, h);
		}
		
		@Override
		public boolean canContinueToUse() {
			LivingEntity entity = this.mob.getTarget();
			if (entity == null) {
				entity = this.targetMob;
			}
			if (entity == null || ! entity.isAlive()) {
				return false;
			}
			if (EntityUtil.checkCanEntityTarget(mob, entity) && entity != this.mob) {
				if (this.checkOther(entity)) {
					this.mob.setTarget(entity);
					return true;
				}
			}
			return false;
		}
		
	}

}
