package com.hungteen.pvz.entity.zombie.roof;

import java.util.EnumSet;

import com.hungteen.pvz.entity.ai.BreakBlockGoal;
import com.hungteen.pvz.entity.ai.PVZLookRandomlyGoal;
import com.hungteen.pvz.entity.ai.PVZSwimGoal;
import com.hungteen.pvz.entity.ai.attack.PVZZombieAttackGoal;
import com.hungteen.pvz.entity.ai.target.ZombieNearestTargetGoal;
import com.hungteen.pvz.entity.plant.spear.SpikeRockEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class GargantuarEntity extends PVZZombieEntity {

	private static final DataParameter<Boolean> HAS_IMP = EntityDataManager.createKey(GargantuarEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> TOOL_TYPE = EntityDataManager.createKey(GargantuarEntity.class, DataSerializers.VARINT);
	public boolean isSad = false;
	
	public GargantuarEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.maxDeathTime = 40;
	}
	
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if(! world.isRemote) {
			this.setToolType(GargantuarType.values()[this.getRNG().nextInt(GargantuarType.values().length)]);
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(HAS_IMP, true);
		this.dataManager.register(TOOL_TYPE, GargantuarType.DOLL.ordinal());
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new PVZLookRandomlyGoal(this));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new PVZSwimGoal(this));
		this.goalSelector.addGoal(3, new GargantuarMoveToTargetGoal(this, true));
		this.goalSelector.addGoal(2, new CrushAttackGoal(this));
		this.goalSelector.addGoal(1, new ThrowImpGoal(this));
		this.goalSelector.addGoal(6, new BreakBlockGoal(BlockRegister.FLOWER_POT.get(), this, 1F, 10));
		this.targetSelector.addGoal(0, new ZombieNearestTargetGoal(this, true, 60, 30));
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.SLOW);
	}
	
	public void throwImp(LivingEntity target) {
		EntityUtil.playSound(this, SoundRegister.THROW_IMP.get());
		Vec3d vec = new Vec3d(this.getRNG().nextFloat() - 0.5, this.getRNG().nextFloat() / 4, this.getRNG().nextFloat() - 0.5).normalize();
		if(target != null) {
			double speed = 2F;
			vec = target.getPositionVec().subtract(this.getPositionVec()).normalize().scale(speed);
		} else {
			double speed = 0.5F;
			vec = vec.scale(speed);
		}
		ImpEntity imp = EntityRegister.IMP.get().create(world);
		imp.setMotion(vec);
		EntityUtil.onMobEntitySpawn(world, imp, getPosition().add(0, this.getHeight(), 0));
		this.setHasImp(false);
	}
	
	@Override
	protected boolean canZombieTarget(LivingEntity target) {
		if(target instanceof SpikeRockEntity) return true;
		return super.canZombieTarget(target);
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		if(! world.isRemote) {
			EntityUtil.playSound(this, SoundRegister.GROUND_SHAKE.get());
		}
		if(!EntityUtil.isEntityValid(entityIn)) return false;
		return super.attackEntityAsMob(entityIn);
	}
	
	@Override
	protected PVZDamageSource getZombieAttackDamageSource() {
		return PVZDamageSource.causeCrushDamage(this, this);
	}
	
	@Override
	protected float getModifyAttackDamage(Entity entity, float f) {
		if(entity instanceof LivingEntity) {
			return 2 * EntityUtil.getCurrentMaxHealth(((LivingEntity) entity));
		}
		return f;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		if(this.isMiniZombie()) return EntitySize.flexible(0.6F, 1.8F);
		return EntitySize.flexible(0.95f, 4f);
	}
	
	public boolean canThrowImp() {
		return this.canZombieNormalUpdate() && this.getHealth() / this.getMaxHealth() < 0.5F;
	}
	
	public int getCrushCD() {
		return 40;
	}
	
	public int getThrowCD() {
		return 30;
	}
	
	@Override
	public float getLife() {
		return 300;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return SoundRegister.GARGANTUAR_DEATH.get();
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundRegister.GARGANTUAR_SAY.get();
	}
	
	@Override
	public int getTalkInterval() {
		return 200;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.GARGANTUAR;
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(compound.contains("has_imp")) {
			this.setHasImp(compound.getBoolean("has_imp"));
		}
		if(compound.contains("weapon_type")) {
			this.setToolType(GargantuarType.values()[compound.getInt("weapon_type")]);
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("has_imp", this.hasImp());
		compound.putInt("weapon_type", this.getToolType().ordinal());
	}
	
	public void setHasImp(boolean has) {
		this.dataManager.set(HAS_IMP, has);
	}
	
	public boolean hasImp() {
		return this.dataManager.get(HAS_IMP);
	}
	
	public void setToolType(GargantuarType type) {
		this.dataManager.set(TOOL_TYPE, type.ordinal());
	}
	
	public GargantuarType getToolType() {
		return GargantuarType.values()[this.dataManager.get(TOOL_TYPE)];
	}
	
	public static enum GargantuarType {
		POLE,
		SIGN,
		DOLL
	}

	private final class GargantuarMoveToTargetGoal extends PVZZombieAttackGoal {
		
		public GargantuarMoveToTargetGoal(GargantuarEntity creature, boolean useLongMemory) {
			super(creature, useLongMemory);
		}
		
		@Override
		public boolean shouldExecute() {
			return this.zombie.getAttackTime() == 0 && super.shouldExecute();
		}
		
		@Override
		public boolean shouldContinueExecuting() {
			return this.zombie.getAttackTime() == 0 && super.shouldContinueExecuting();
		}
		
		@Override
		protected void checkAndPerformAttack(LivingEntity target) {
			double dis = EntityUtil.getNearestDistance(this.attacker, target);
			double range = this.getAttackReachSqr(target);
			if (range >= dis && this.attackTick <= 0) {
				this.attackTick = this.zombie.getAttackCD();
				this.attacker.swingArm(Hand.MAIN_HAND);
				this.zombie.setAttackTime(GargantuarEntity.this.getCrushCD());
			}
		}
		
	}
	
	private final class CrushAttackGoal extends Goal {

		protected final GargantuarEntity attacker;
		
		public CrushAttackGoal(GargantuarEntity creature) {
			this.attacker = creature;
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}
		
		@Override
		public boolean shouldExecute() {
			return this.attacker.getAttackTime() > 0;
		}
		
		@Override
		public boolean shouldContinueExecuting() {
			return this.attacker.getAttackTime() > 0;
		}
		
		@Override
		public void tick() {
			if(this.attacker.canZombieNormalUpdate() && this.attacker.getAttackTime() > 0) {
				this.attacker.setAttackTime(this.attacker.getAttackTime() - 1);
				if(this.attacker.getAttackTime() == this.attacker.getCrushCD() * 1 / 3) {
					this.attacker.attackEntityAsMob(this.attacker.getAttackTarget());
				}
			}
		}
		
	}
	
	private final class ThrowImpGoal extends Goal {

		protected final GargantuarEntity attacker;
		
		public ThrowImpGoal(GargantuarEntity creature) {
			this.attacker = creature;
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}
		
		@Override
		public boolean shouldExecute() {
			return this.attacker.hasImp() && this.attacker.canThrowImp();
		}
		
		@Override
		public void startExecuting() {
			this.attacker.setAttackTime(- this.attacker.getThrowCD());
		}
		
		@Override
		public boolean shouldContinueExecuting() {
			return this.attacker.getAttackTime() < 0;
		}
		
		@Override
		public void resetTask() {
			this.attacker.setAttackTime(0);
		}
		
		@Override
		public void tick() {
			if(this.attacker.canZombieNormalUpdate() && this.attacker.getAttackTime() < 0) {
				this.attacker.setAttackTime(this.attacker.getAttackTime() + 1);
				if(this.attacker.hasImp() && - this.attacker.getAttackTime() == this.attacker.getThrowCD() * 1 / 4) {
					this.attacker.throwImp(this.attacker.getAttackTarget());
				}
			}
		}
		
	}
	
	
}
