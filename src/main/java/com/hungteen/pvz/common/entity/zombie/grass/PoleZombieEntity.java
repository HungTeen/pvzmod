package com.hungteen.pvz.common.entity.zombie.grass;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZRandomTargetGoal;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity;
import com.hungteen.pvz.common.entity.zombie.pool.DiggerZombieEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.impl.zombie.GrassZombies;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EffectUtil;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.interfaces.ICanAttract;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.Optional;

public class PoleZombieEntity extends PVZZombieEntity{

	private static final DataParameter<Boolean> HAS_POLE = EntityDataManager.defineId(DiggerZombieEntity.class, DataSerializers.BOOLEAN);
	protected final float HorizontalJumpSpeed = 1.5F;
	protected final float VerticalJumpSpeed = 0.7F;
	protected Vector3d jumpDstPoint = Vector3d.ZERO;
	protected int pole_jump_cnt;
	
	public PoleZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(HAS_POLE, true);
	}

	@Override
	protected void registerAttackGoals() {
		super.registerAttackGoals();
		this.goalSelector.addGoal(0, new PoleJumpGoal(this));
	}
	
	@Override
	protected void registerTargetGoals() {
		this.targetSelector.addGoal(0, new PVZRandomTargetGoal(this, true, true, ZombieUtil.NORMAL_TARGET_RANGE, ZombieUtil.LOW_TARGET_HEIGHT));
	}
	
	@Override
	public void onSyncedDataUpdated(DataParameter<?> data) {
		super.onSyncedDataUpdated(data);
		if(data.equals(HAS_POLE)) {
			this.addEffect(EffectUtil.effect(Effects.MOVEMENT_SLOWDOWN, 1000000, 0));
		}
	}
	
	@Override
	public boolean canBeAttractedBy(ICanAttract defender) {
		if(defender instanceof PVZPlantEntity) {
			final IPlantType plant = ((PVZPlantEntity) defender).getPlantType();
			return plant == PVZPlants.TALL_NUT;
		}
		return true;
	}
	
	@Override
	public void attractBy(ICanAttract defender) {
		super.attractBy(defender);
		if(this.hasPole()) {
		    this.setPole(false);
		    this.setDeltaMovement(0, 0, 0);
		    EntityUtil.playSound(this, SoundRegister.HAMMER_BONK.get());
		}
	}
	
	@Override
	public boolean canBeTargetBy(LivingEntity living) {
		return super.canBeTargetBy(living) && ! this.isPoleJumping();
	}
	
	@Override
	protected boolean isZombieInvulnerableTo(DamageSource source) {
		return super.isZombieInvulnerableTo(source) || this.isPoleJumping();
	}
	
	@Override
	protected void setBodyStates(ZombieDropBodyEntity body) {
		super.setBodyStates(body);
		body.setHandDefence(this.hasPole());
	}
	
	/**
	 * set jump and motion.
	 * {@link PoleJumpGoal#tick()}
	 */
	public void perfromJump() {
		Optional.ofNullable(this.getTarget()).ifPresent(target -> {
			Vector3d vec = MathUtil.getHorizontalNormalizedVec(this.position(), this.jumpDstPoint);
			final double speedXZ = this.HorizontalJumpSpeed + (this.random.nextDouble() - 0.3D) / 2;
			final double speedY = this.VerticalJumpSpeed + (this.random.nextDouble() - 0.3D) / 2;
			this.setDeltaMovement(vec.x * speedXZ , speedY, vec.z * speedXZ);
			EntityUtil.playSound(this, SoundRegister.POLE_JUMP.get());
		});
	}
	
	/**
	 * how many times can it jump.
	 */
	public int getMaxJumpCount() {
		return 1;
	}

	@Override
	public float getWalkSpeed() {
		return ZombieUtil.WALK_FAST;
	}

	@Override
	public float getLife() {
		return 50;
	}
	
	/**
	 * time to anim jump.
	 */
	public int getPoleJumpCD() {
		return 20;
	}
	
	/**
	 * Common plants can not target jumping PoleZombie.
	 * {@link PVZPlantEntity#checkCanPAZTarget(net.minecraft.entity.Entity)}
	 */
	public boolean isPoleJumping() {
		return this.getAttackTime() > 0;
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("zombie_has_pole")) {
			this.setPole(compound.getBoolean("zombie_has_pole"));
		}
		if(compound.contains("jump_dst_point")) {
			CompoundNBT nbt = compound.getCompound("jump_dst_point");
			this.jumpDstPoint = new Vector3d(nbt.getDouble("XXX"), nbt.getDouble("YYY"), nbt.getDouble("ZZZ"));
		}
		if(compound.contains("pole_jump_count")) {
			this.pole_jump_cnt = compound.getInt("pole_jump_count");
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("zombie_has_pole", this.hasPole());
		final CompoundNBT nbt = new CompoundNBT();
		nbt.putDouble("XXX", this.jumpDstPoint.x);
		nbt.putDouble("YYY", this.jumpDstPoint.y);
		nbt.putDouble("ZZZ", this.jumpDstPoint.z);
		compound.put("jump_dst_point", nbt);
		compound.putInt("pole_jump_count", this.pole_jump_cnt);
	}
	
	public void setPole(boolean has) {
		this.entityData.set(HAS_POLE, has);
	}
	
	public boolean hasPole() {
		return this.entityData.get(HAS_POLE);
	}
	
	@Override
	public ZombieType getZombieType() {
		return GrassZombies.POLE_ZOMBIE;
	}
	
	/**
	 * PoleZombie Jump condition:
	 * 1.it has a valid target and suitable distance.
	 * 2.it's on ground and it must has extra jump count.
	 * 3.its RayTrace up can not hit block.
	 */
	static class PoleJumpGoal extends Goal{

		private final PoleZombieEntity zombie;
		private int delayCnt = 0;
		
		public PoleJumpGoal(PoleZombieEntity zombie) {
			this.zombie = zombie;
			this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE, Goal.Flag.LOOK));
		}
		
		@Override
		public boolean canUse() {
			//already start last launch.
			if(this.zombie.getAttackTime() > 0) {
				return true;
			}
			if(this.delayCnt > 0) {
				-- this.delayCnt;
				return false;
			}
			LivingEntity target = zombie.getTarget();
			int left_jump_chance = this.zombie.getMaxJumpCount() - this.zombie.pole_jump_cnt;
			if(! EntityUtil.isEntityValid(target) || 
					! this.zombie.isOnGround() || left_jump_chance <= 0) {
				return false;
			}
			double dis = this.zombie.distanceToSqr(target);
			//can not be so close or so far.
			if(dis < 64 || dis > Math.max(100, 100 * left_jump_chance * left_jump_chance)) {
				return false;
			}
			Vector3d vec = MathUtil.getHorizontalNormalizedVec(zombie.position(), target.position())
					.scale(this.zombie.HorizontalJumpSpeed)
					.add(0, this.zombie.VerticalJumpSpeed * 2, 0);
			if(! EntityUtil.canEntityPass(zombie, vec, 10)) {
				this.delayCnt = this.zombie.random.nextInt(50);
				return false;
			}
			this.zombie.jumpDstPoint = target.position();
			this.zombie.setTarget(target);
			return true;
		}
		
		@Override
		public void start() {
			this.zombie.setAttackTime(zombie.getPoleJumpCD());
			this.zombie.setAggressive(false);
		}
		
		@Override
		public boolean canContinueToUse() {
			return this.zombie.getAttackTime() > 0;
		}
		
		@Override
		public void tick() {
			int time = this.zombie.getAttackTime();
			int cd = this.zombie.getPoleJumpCD();
			this.zombie.getLookControl().setLookAt(this.zombie.jumpDstPoint);
			if(time == cd * 3 / 4) {
				this.zombie.perfromJump();
			} else if(time == 1) {
				if(++ this.zombie.pole_jump_cnt == this.zombie.getMaxJumpCount()) {
					this.zombie.setPole(false);
				}
			}
			this.zombie.setAttackTime(Math.max(0, time - 1));
		}
		
	}
}
