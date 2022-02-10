package com.hungteen.pvz.common.entity.zombie.roof;

import com.hungteen.pvz.api.interfaces.ICanPushBack;
import com.hungteen.pvz.common.entity.AbstractPAZEntity;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZRandomTargetGoal;
import com.hungteen.pvz.common.entity.bullet.TargetArrowEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.impl.zombie.RoofZombies;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.tag.PVZEntityTypeTags;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.ConfigUtil;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.interfaces.ICanAttract;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class BungeeZombieEntity extends PVZZombieEntity implements ICanPushBack {

	private static final DataParameter<Integer> BUNGEE_STATE = EntityDataManager.defineId(BungeeZombieEntity.class, DataSerializers.INT);
	private static final DataParameter<Integer> BUNGEE_TYPE = EntityDataManager.defineId(BungeeZombieEntity.class, DataSerializers.INT);
	private static final DataParameter<BlockPos> ORIGIN_POS = EntityDataManager.defineId(BungeeZombieEntity.class, DataSerializers.BLOCK_POS);
	protected EntityType<?> entityType;
	private BlockPos stealPos;
	private LivingEntity stealTarget;
	
	public BungeeZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canBeMini = false;
		this.canBeStealByBungee = false;
		this.setImmuneAllEffects();
		this.setIsWholeBody();
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(BUNGEE_STATE, BungeeStates.WAIT.ordinal());
		this.entityData.define(BUNGEE_TYPE, BungeeTypes.STEAL.ordinal());
		this.entityData.define(ORIGIN_POS, BlockPos.ZERO);
	}

	@Override
	public void finalizeSpawn(CompoundNBT tag) {
		super.finalizeSpawn(tag);
		this.setOriginPos(blockPosition());
	}
	
	@Override
	public void tick() {
		this.noPhysics = true;
		super.tick();
	}
	
	@Override
	protected void registerGoals() {
		this.registerTargetGoals();
	}
	
	@Override
	protected void registerTargetGoals() {
		this.targetSelector.addGoal(0, new BungeeRandomTargetGoal(this, true, true, ZombieUtil.CLOSE_TARGET_RANGE, ZombieUtil.HIGH_TARGET_HEIGHT));
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(! level.isClientSide) {
			//being push back by umbrella leaf.
			if(this.getBungeeState() == BungeeStates.PUSH_BACK) {
				this.setAttackTime(this.getAttackTime() - 1);
				this.setDeltaMovement(new Vector3d(0, 1.2, 0));
				//remove after 3 seconds.
				if(this.getAttackTime() <= - 60) {
					if(this.getBungeeType() == BungeeTypes.SUMMON && EntityUtil.isEntityValid(this.getStealTarget())) {
						this.getStealTarget().remove();
					}
					this.remove();
				}
				return ;
			}
			//tick for different type of bungee.
			if(this.getBungeeType() == BungeeTypes.STEAL) {
				this.tickSteal();
			} else if(this.getBungeeType() == BungeeTypes.HELP) {
				this.tickHelp();
			} else if(this.getBungeeType() == BungeeTypes.SUMMON) {
				this.tickSummon();
			}
		}
	}
	
	/**
	 * help type tick.
	 * {@link #normalZombieTick()}
	 */
	protected void tickHelp() {
		//if chosen target was not suitable then die.
		if(! this.isSuitableTarget(this.getStealTarget())) {
			if(this.getAttackTime() >= 0) {
				this.setAttackTime(- 1);
			}
			this.setBungeeState(BungeeStates.UP);
		}
		if(this.getBungeeState() == BungeeStates.WAIT) {//find suitable target.
			if(this.isSuitableTarget(this.getStealTarget())) {
				this.setBungeeState(BungeeStates.DOWN);
				EntityUtil.playSound(this.getStealTarget(), SoundRegister.BUNGEE_SCREAM.get());
			}
		} else if(this.getBungeeState() == BungeeStates.DOWN) {//move from sky close to target.
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
			this.setAttackTime(this.getAttackTime() - 1);
			this.moveBackToOrigin();
			if(this.getStealTarget() != null && this.getAttackTime() < - 60) {
				this.dealDamageAndRemove();
				return ;
			}
		}
	}
	
	/**
	 * steal type tick.
	 * {@link #normalZombieTick()}
	 */
	protected void tickSteal() {
		//not wait, but target is not suitable now, then back to wait.
		if(this.getBungeeState() != BungeeStates.WAIT && ! this.isSuitableTarget(this.getStealTarget())) {
			this.setBungeeState(BungeeStates.BACK_WAIT); //chosen target was not fitable then die.
			this.setStealTarget(null);
		}
		if(this.getBungeeState() == BungeeStates.WAIT) {
			if(this.isSuitableTarget(this.getTarget())) {
				if(this.getOriginPos().getY() - this.getTarget().getY() <= 15) {//not enough high, then move to 20 block higher place.
					this.setOriginPos(this.getTarget().blockPosition().above(20));
					this.setBungeeState(BungeeStates.BACK_WAIT);
					return ;
				}
				this.setBungeeState(BungeeStates.DOWN);
				this.setStealTarget(this.getTarget());
				this.shootArrowToTarget();
				EntityUtil.playSound(this.getStealTarget(), SoundRegister.BUNGEE_SCREAM.get());
			}
		} else if(this.getBungeeState() == BungeeStates.BACK_WAIT) {//move back to origin to wait next target.
			this.moveBackToOrigin();
			if(this.distanceToSqr(this.getOriginPos().getX(), this.getOriginPos().getY(), this.getOriginPos().getZ()) <= 2) {
				this.setBungeeState(BungeeStates.WAIT);
				this.setDeltaMovement(Vector3d.ZERO);
			}
		} else if(this.getBungeeState() == BungeeStates.DOWN) {
			if(this.isNearToTarget()) {//already close to target.
				this.setAttackTime(this.getStayTime());
				this.stealPos = this.getStealTarget().blockPosition();
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
				this.dealDamageAndRemove();
				return ;
			}
		}
	}
	
	/**
	 * summon type tick.
	 * {@link #normalZombieTick()}
	 */
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
	public void pushBack() {
		this.setBungeeState(BungeeStates.PUSH_BACK);
		if(this.getBungeeType() == BungeeTypes.STEAL && this.getStealTarget() != null) {
			this.getStealTarget().stopRiding();
			if(this.stealPos != null){
				this.getStealTarget().setPos(this.stealPos.getX(), this.stealPos.getY(), this.stealPos.getZ());
			}
			this.setStealTarget(null);
		}
	}
	
	@Override
	public boolean canPAZTarget(Entity target) {
		return canBungeeSteal(target);
	}
	
	@Override
	public boolean canBeTargetBy(LivingEntity living) {
		return super.canBeTargetBy(living) && this.getBungeeState() == BungeeStates.CATCH;
	}
	
	@Override
	protected boolean isZombieInvulnerableTo(DamageSource source) {
		return super.isZombieInvulnerableTo(source) || this.getBungeeState() != BungeeStates.CATCH;
	}

	public void setSummonType(EntityType<?> entityType){
		this.entityType = entityType;
	}
	
	/**
	 * summon zombies.
	 * {@link #tickSummon()}
	 */
	public void summonZombie() {
		if(this.entityType == null){
			this.entityType = PVZEntityTypeTags.BUNGEE_SPAWNS.getRandomElement(this.getRandom());
		}
		Entity entity = this.entityType.create(this.level);
		if(entity instanceof LivingEntity){
			EntityUtil.onEntitySpawn(level, entity, blockPosition());
			entity.startRiding(this);
			this.setStealTarget((LivingEntity) entity);
		}
	}
	
	@Override
	public double getPassengersRidingOffset() {
		return 0;
	}
	
	private void dealDamageAndRemove() {
		this.getStealTarget().hurt(PVZEntityDamageSource.causeDeadlyDamage(this, this), EntityUtil.getMaxHealthDamage(this.getStealTarget()));
		this.remove();
	}
	
	/**
	 * CD when catching.
	 */
	public int getStayTime() {
		return 80;
	}
	
	@Override
	public boolean canBeAttractedBy(ICanAttract defender) {
		return false;
	}
	
	/**
	 * move to catch target. 
	 */
	private void moveToTarget() {
		if(! EntityUtil.isEntityValid(this.getStealTarget())) {
			return;
		}
		Vector3d vec = this.getStealTarget().position().subtract(this.position()).normalize();
		final double speed = 0.55D;
		this.setDeltaMovement(vec.multiply(speed, speed, speed));
	}
	
	/**
	 * move back to origin blockpos.
	 */
	private void moveBackToOrigin() {
		Vector3d vec = new Vector3d(this.getOriginPos().getX() - this.getX(), this.getOriginPos().getY() - this.getY(), this.getOriginPos().getZ() - this.getZ()).normalize();
		final double speed = 0.4D;
		this.setDeltaMovement(vec.multiply(speed, speed, speed));
	}
	
	/**
	 * {@link #tickSteal()}
	 */
	private boolean isNearToTarget() {
		return EntityUtil.isEntityValid(this.getStealTarget()) && this.distanceToSqr(this.getStealTarget()) <= 2;
	}
	
	/**
	 * {@link #tickSteal()}
	 */
	private void shootArrowToTarget() {
		TargetArrowEntity arrow = new TargetArrowEntity(level, this);
		arrow.setPos(this.getX(), this.getY(), this.getZ());
		arrow.shoot(this.getStealTarget());
		level.addFreshEntity(arrow);
	}

	/**
	 * can entity be choose as target.
	 * {@link #canPAZTarget(Entity)}
	 */
	public static boolean canBungeeSteal(Entity target) {
		//is boss entity.
		if((! (target instanceof LivingEntity)) || EntityUtil.isEntityBoss((LivingEntity) target) || isHoldingZombieDoll((LivingEntity) target)) {
			return false;
		}
		if(target instanceof AbstractPAZEntity) {
			
			return ((AbstractPAZEntity) target).canBeStealByBungee();
		}
		return EntityUtil.getCurrentMaxHealth((LivingEntity) target) <= ConfigUtil.getLimitDamage();
	}
	
	/**
	 * is chosen target still suitable.
	 */
	protected boolean isSuitableTarget(LivingEntity target) {
		//player held zombie doll will not be target.
		if(! EntityUtil.isEntityValid(target) || isHoldingZombieDoll(target)) {
			return false;
		}
		if(target.getVehicle() instanceof BungeeZombieEntity) {//target has been stole.
			return target.getVehicle().is(this);
		}
		return true;
	}
	
	/**
	 * {@link #canBungeeSteal(Entity)}
	 * {@link #isSuitableTarget(LivingEntity)}
	 */
	private static boolean isHoldingZombieDoll(LivingEntity target) {
		return target.getMainHandItem().getItem().equals(ItemRegister.ZOMBIE_DOLL.get()) 
					|| target.getOffhandItem().getItem().equals(ItemRegister.ZOMBIE_DOLL.get());
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
    public ZombieType getZombieType() {
	    return RoofZombies.BUNGEE_ZOMBIE;
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
		if(compound.contains("summon_type")){
			this.entityType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(compound.getString("summon_type")));
		}
		if(compound.contains("steal_pos")) {
			CompoundNBT nbt = compound.getCompound("steal_pos");
			this.stealPos = new BlockPos(nbt.getInt("steal_pos_x"), nbt.getInt("steal_pos_y"), nbt.getInt("steal_pos_z"));
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
		if(this.entityType != null){
			compound.putString("summon_type", this.entityType.getRegistryName().toString());
		}
		if(this.stealPos != null){
			CompoundNBT nbt = new CompoundNBT();
			nbt.putInt("steal_pos_x", this.stealPos.getX());
			nbt.putInt("steal_pos_y", this.stealPos.getY());
			nbt.putInt("steal_pos_z", this.stealPos.getZ());
			compound.put("steal_pos", nbt);
		}
		{
			CompoundNBT nbt = new CompoundNBT();
			nbt.putInt("origin_pos_x", this.getOriginPos().getX());
			nbt.putInt("origin_pos_y", this.getOriginPos().getY());
			nbt.putInt("origin_pos_z", this.getOriginPos().getZ());
			compound.put("origin_pos", nbt);
		}
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
	
	public enum BungeeStates {
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
	
	static class BungeeRandomTargetGoal extends PVZRandomTargetGoal {

		private final BungeeZombieEntity zombie;
		
		public BungeeRandomTargetGoal(BungeeZombieEntity mobIn, boolean checkSight, boolean mustReach, float w, float h) {
			super(mobIn, checkSight, mustReach, w, h);
			this.zombie = mobIn;
		}
		
		@Override
		public boolean canContinueToUse() {
			return this.zombie.isSuitableTarget(this.zombie.getTarget());
		}
		
	}

}
