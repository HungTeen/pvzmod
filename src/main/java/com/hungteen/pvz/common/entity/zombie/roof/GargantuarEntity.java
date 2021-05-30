package com.hungteen.pvz.common.entity.zombie.roof;

import java.util.EnumSet;

import com.hungteen.pvz.common.entity.goal.BreakBlockGoal;
import com.hungteen.pvz.common.entity.goal.PVZLookRandomlyGoal;
import com.hungteen.pvz.common.entity.goal.PVZSwimGoal;
import com.hungteen.pvz.common.entity.goal.attack.PVZZombieAttackGoal;
import com.hungteen.pvz.common.entity.goal.target.ZombieNearestTargetGoal;
import com.hungteen.pvz.common.entity.plant.spear.SpikeRockEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.data.loot.PVZLoot;
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
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public class GargantuarEntity extends PVZZombieEntity {

	private static final DataParameter<Boolean> HAS_IMP = EntityDataManager.defineId(GargantuarEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> TOOL_TYPE = EntityDataManager.defineId(GargantuarEntity.class, DataSerializers.INT);
	public boolean isSad = false;
	
	public GargantuarEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.maxDeathTime = 40;
	}
	
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if(! level.isClientSide) {
			this.setToolType(GargantuarType.values()[this.getRandom().nextInt(GargantuarType.values().length)]);
		}
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(HAS_IMP, true);
		this.entityData.define(TOOL_TYPE, GargantuarType.DOLL.ordinal());
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
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.SLOW);
	}
	
	public void throwImp(LivingEntity target) {
		EntityUtil.playSound(this, SoundRegister.THROW_IMP.get());
		Vector3d vec = new Vector3d(this.getRandom().nextFloat() - 0.5, this.getRandom().nextFloat() / 4, this.getRandom().nextFloat() - 0.5).normalize();
		if(target != null) {
			double speed = 2F;
			vec = target.position().subtract(this.position()).normalize().scale(speed);
		} else {
			double speed = 0.5F;
			vec = vec.scale(speed);
		}
		ImpEntity imp = EntityRegister.IMP.get().create(level);
		imp.setDeltaMovement(vec);
		imp.setCharmed(this.isCharmed());
		EntityUtil.onMobEntitySpawn(level, imp, blockPosition().offset(0, this.getBbHeight(), 0));
		this.setHasImp(false);
	}
	
	@Override
	protected boolean canZombieTarget(LivingEntity target) {
		if(target instanceof SpikeRockEntity) return true;
		return super.canZombieTarget(target);
	}
	
	@Override
	public boolean doHurtTarget(Entity entityIn) {
		if(! level.isClientSide) {
			EntityUtil.playSound(this, SoundRegister.GROUND_SHAKE.get());
		}
		if(!EntityUtil.isEntityValid(entityIn)) return false;
		return super.doHurtTarget(entityIn);
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
	public EntitySize getDimensions(Pose poseIn) {
		if(this.isMiniZombie()) return EntitySize.scalable(0.6F, 1.8F);
		return EntitySize.scalable(0.8f, 4f);
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
	public int getAmbientSoundInterval() {
		return 200;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.GARGANTUAR;
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("has_imp")) {
			this.setHasImp(compound.getBoolean("has_imp"));
		}
		if(compound.contains("weapon_type")) {
			this.setToolType(GargantuarType.values()[compound.getInt("weapon_type")]);
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("has_imp", this.hasImp());
		compound.putInt("weapon_type", this.getToolType().ordinal());
	}
	
	public void setHasImp(boolean has) {
		this.entityData.set(HAS_IMP, has);
	}
	
	public boolean hasImp() {
		return this.entityData.get(HAS_IMP);
	}
	
	public void setToolType(GargantuarType type) {
		this.entityData.set(TOOL_TYPE, type.ordinal());
	}
	
	public GargantuarType getToolType() {
		return GargantuarType.values()[this.entityData.get(TOOL_TYPE)];
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.GARGANTUAR;
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
		public boolean canUse() {
			return this.zombie.getAttackTime() == 0 && super.canUse();
		}
		
		@Override
		public boolean canContinueToUse() {
			return this.zombie.getAttackTime() == 0 && super.canContinueToUse();
		}
		
		@Override
		protected void checkAndPerformAttack(LivingEntity target) {
			double dis = EntityUtil.getNearestDistance(this.attacker, target);
			double range = this.getAttackReachSqr(target);
			if (range >= dis && this.attackTick <= 0) {
				this.attackTick = this.zombie.getAttackCD();
				this.attacker.swing(Hand.MAIN_HAND);
				this.zombie.setAttackTime(GargantuarEntity.this.getCrushCD());
			}
		}
		
	}
	
	private final class CrushAttackGoal extends Goal {

		protected final GargantuarEntity attacker;
		
		public CrushAttackGoal(GargantuarEntity creature) {
			this.attacker = creature;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}
		
		@Override
		public boolean canUse() {
			return this.attacker.getAttackTime() > 0;
		}
		
		@Override
		public boolean canContinueToUse() {
			return this.attacker.getAttackTime() > 0;
		}
		
		@Override
		public void tick() {
			if(this.attacker.canZombieNormalUpdate() && this.attacker.getAttackTime() > 0) {
				this.attacker.setAttackTime(this.attacker.getAttackTime() - 1);
				if(this.attacker.getAttackTime() == this.attacker.getCrushCD() * 1 / 3) {
					this.attacker.doHurtTarget(this.attacker.getTarget());
				}
			}
		}
		
	}
	
	private final class ThrowImpGoal extends Goal {

		protected final GargantuarEntity attacker;
		
		public ThrowImpGoal(GargantuarEntity creature) {
			this.attacker = creature;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}
		
		@Override
		public boolean canUse() {
			return this.attacker.hasImp() && this.attacker.canThrowImp();
		}
		
		@Override
		public void start() {
			this.attacker.setAttackTime(- this.attacker.getThrowCD());
		}
		
		@Override
		public boolean canContinueToUse() {
			return this.attacker.getAttackTime() < 0;
		}
		
		@Override
		public void stop() {
			this.attacker.setAttackTime(0);
		}
		
		@Override
		public void tick() {
			if(this.attacker.canZombieNormalUpdate() && this.attacker.getAttackTime() < 0) {
				this.attacker.setAttackTime(this.attacker.getAttackTime() + 1);
				if(this.attacker.hasImp() && - this.attacker.getAttackTime() == this.attacker.getThrowCD() * 1 / 4) {
					this.attacker.throwImp(this.attacker.getTarget());
				}
			}
		}
		
	}
	
	
}
