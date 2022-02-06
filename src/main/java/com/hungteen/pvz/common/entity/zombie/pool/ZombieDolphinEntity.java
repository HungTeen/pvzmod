package com.hungteen.pvz.common.entity.zombie.pool;

import com.hungteen.pvz.common.entity.ai.goal.attack.PVZZombieAttackGoal;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.impl.zombie.PoolZombies;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.ZombieUtil;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.DolphinLookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class ZombieDolphinEntity extends PVZZombieEntity {

	public ZombieDolphinEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
		this.moveControl = new MoveHelperController(this);
		this.lookControl = new DolphinLookController(this, 10);
		this.canBeMini = false;
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new BreatheAirGoal(this));
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
		this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 2.0F));
		this.goalSelector.addGoal(5, new ZombieDolphinJumpGoal(this, 10));
		this.goalSelector.addGoal(8, new FollowBoatGoal(this));
		this.goalSelector.addGoal(0, new PVZZombieAttackGoal(this, true));
		this.registerTargetGoals();
	}
	
	@Override
	public VariantType getVariantType() {
		return VariantType.NORMAL;
	}
	
	@Override
	protected void initAttributes() {
		super.initAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(1.2F);
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(1f, 0.7f);
	}

	@Override
	public float getEatDamage() {
		return ZombieUtil.VERY_LOW;
	}

	@Override
	public float getLife() {
		return 12;
	}
	
	@Override
	protected float getWaterSlowDown() {
		return 0.89f;
	}
	
	@Override
	public double getPassengersRidingOffset() {
		return -0.5f;
	}

	public boolean checkSpawnObstruction(IWorldReader worldIn) {
		return worldIn.isUnobstructed(this);
	}

	@Override
	public boolean rideableUnderWater() {
		return true;
	}

	@Override
	public boolean canBeRiddenInWater(Entity rider) {
		return true;
	}
	
	@Override
	public int getAmbientSoundInterval() {
		return 200;
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundRegister.DOLPHIN_SAY.get();
	}

	@Override
	public CreatureAttribute getMobType() {
		return CreatureAttribute.WATER;
	}

	@Override
	protected PathNavigator createNavigation(World worldIn) {
		return new SwimmerPathNavigator(this, worldIn);
	}
	
	@Override
	public ZombieType getZombieType() {
		return PoolZombies.ZOMBIE_DOLPHIN;
	}

	static class MoveHelperController extends MovementController {
		private final ZombieDolphinEntity dolphin;

		public MoveHelperController(ZombieDolphinEntity dolphinIn) {
			super(dolphinIn);
			this.dolphin = dolphinIn;
		}

		public void tick() {
			if (this.dolphin.isInWater()) {
				this.dolphin.setDeltaMovement(this.dolphin.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
			}

			if (this.operation == MovementController.Action.MOVE_TO && !this.dolphin.getNavigation().isDone()) {
				double d0 = this.wantedX - this.dolphin.getX();
				double d1 = this.wantedY - this.dolphin.getY();
				double d2 = this.wantedZ - this.dolphin.getZ();
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				if (d3 < (double) 2.5000003E-7F) {
					this.mob.setZza(0.0F);
				} else {
					float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
					this.dolphin.yRot = this.rotlerp(this.dolphin.yRot, f, 10.0F);
					this.dolphin.yBodyRot = this.dolphin.yRot;
					this.dolphin.yHeadRot = this.dolphin.yRot;
					float f1 = (float) (this.speedModifier
							* this.dolphin.getAttribute(Attributes.MOVEMENT_SPEED).getValue());
					if (this.dolphin.isInWater()) {
						this.dolphin.setSpeed(f1 * 0.02F);
						float f2 = -((float) (MathHelper.atan2(d1, (double) MathHelper.sqrt(d0 * d0 + d2 * d2))
								* (double) (180F / (float) Math.PI)));
						f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
						this.dolphin.xRot = this.rotlerp(this.dolphin.xRot, f2, 5.0F);
						float f3 = MathHelper.cos(this.dolphin.xRot * ((float) Math.PI / 180F));
						float f4 = MathHelper.sin(this.dolphin.xRot * ((float) Math.PI / 180F));
						this.dolphin.zza = f3 * f1;
						this.dolphin.yya = -f4 * f1;
					} else {
						this.dolphin.setSpeed(f1 * 0.1F);
					}

				}
			} else {
				this.dolphin.setSpeed(0.0F);
				this.dolphin.setXxa(0.0F);
				this.dolphin.setYya(0.0F);
				this.dolphin.setZza(0.0F);
			}
		}
	}

	static class ZombieDolphinJumpGoal extends JumpGoal {
		private static final int[] JUMP_DISTANCES = new int[] { 0, 1, 4, 5, 6, 7 };
		private final ZombieDolphinEntity dolphin;
		private final int chance;
		private boolean breached;

		public ZombieDolphinJumpGoal(ZombieDolphinEntity zombie, int chance) {
			this.dolphin = zombie;
			this.chance = chance;
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state
		 * necessary for execution in this method as well.
		 */
		public boolean canUse() {
			if (this.dolphin.getRandom().nextInt(this.chance) != 0) {
				return false;
			} else {
				Direction direction = this.dolphin.getMotionDirection();
				int i = direction.getStepX();
				int j = direction.getStepZ();
				BlockPos blockpos = this.dolphin.blockPosition();

				for (int k : JUMP_DISTANCES) {
					if (!this.canJumpTo(blockpos, i, j, k) || !this.isAirAbove(blockpos, i, j, k)) {
						return false;
					}
				}

				return true;
			}
		}

		private boolean canJumpTo(BlockPos pos, int dx, int dz, int scale) {
			BlockPos blockpos = pos.offset(dx * scale, 0, dz * scale);
			return this.dolphin.level.getFluidState(blockpos).is(FluidTags.WATER)
					&& !this.dolphin.level.getBlockState(blockpos).getMaterial().blocksMotion();
		}

		@SuppressWarnings("deprecation")
		private boolean isAirAbove(BlockPos pos, int dx, int dz, int scale) {
			return this.dolphin.level.getBlockState(pos.offset(dx * scale, 1, dz * scale)).isAir()
					&& this.dolphin.level.getBlockState(pos.offset(dx * scale, 2, dz * scale)).isAir();
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		public boolean canContinueToUse() {
			double d0 = this.dolphin.getDeltaMovement().y;
			return (!(d0 * d0 < (double) 0.03F) || this.dolphin.xRot == 0.0F
					|| !(Math.abs(this.dolphin.xRot) < 10.0F) || !this.dolphin.isInWater())
					&& !this.dolphin.onGround;
		}

		public boolean isInterruptable() {
			return false;
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void start() {
			Direction direction = this.dolphin.getMotionDirection();
			this.dolphin.setDeltaMovement(this.dolphin.getDeltaMovement().add((double) direction.getStepX() * 0.6D, 0.7D,
					(double) direction.getStepZ() * 0.6D));
			this.dolphin.getNavigation().stop();
		}

		/**
		 * Reset the task's internal state. Called when this task is interrupted by
		 * another one
		 */
		public void stop() {
			this.dolphin.xRot = 0.0F;
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		@SuppressWarnings("deprecation")
		public void tick() {
			boolean flag = this.breached;
			if (!flag) {
				FluidState ifluidstate = this.dolphin.level.getFluidState(this.dolphin.blockPosition());
				this.breached = ifluidstate.is(FluidTags.WATER);
			}

			if (this.breached && !flag) {
				this.dolphin.playSound(SoundEvents.DOLPHIN_JUMP, 1.0F, 1.0F);
			}

			Vector3d vec3d = this.dolphin.getDeltaMovement();
			if (vec3d.y * vec3d.y < (double) 0.03F && this.dolphin.xRot != 0.0F) {
				this.dolphin.xRot = MathHelper.rotlerp(this.dolphin.xRot, 0.0F, 0.2F);
			} else {
				double d0 = Math.sqrt(Entity.getHorizontalDistanceSqr(vec3d));
				double d1 = Math.signum(-vec3d.y) * Math.acos(d0 / vec3d.length()) * (double) (180F / (float) Math.PI);
				this.dolphin.xRot = (float) d1;
			}

		}
	}

}
