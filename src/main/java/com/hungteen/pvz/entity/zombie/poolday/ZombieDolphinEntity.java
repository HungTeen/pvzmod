package com.hungteen.pvz.entity.zombie.poolday;

import com.hungteen.pvz.entity.ai.ZombieMeleeAttackGoal;
import com.hungteen.pvz.entity.ai.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.creature.FoodieZombieEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.misc.loot.PVZLoot;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.controller.DolphinLookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.BreatheAirGoal;
import net.minecraft.entity.ai.goal.FollowBoatGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.JumpGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.monster.GuardianEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class ZombieDolphinEntity extends PVZZombieEntity {

	public ZombieDolphinEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.setPathPriority(PathNodeType.WATER, 0.0F);
		this.moveController = new MoveHelperController(this);
		this.lookController = new DolphinLookController(this, 10);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new BreatheAirGoal(this));
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
		this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(5, new DolphinJumpGoal(this, 10));
		this.goalSelector.addGoal(8, new FollowBoatGoal(this));
		this.goalSelector.addGoal(0, new ZombieMeleeAttackGoal(this));
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 80, 60));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, GuardianEntity.class)).setCallsForHelp());
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.2F);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.VERY_LOW);
	}

	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(1f, 0.7f);
	}
	
	@Override
	public float getLife() {
		return 12;
	}
	
	@Override
	protected float getWaterSlowDown() {
		return 0.85f;
	}
	
	@Override
	public double getMountedYOffset() {
		return -0.5f;
	}

	public boolean isNotColliding(IWorldReader worldIn) {
		return worldIn.checkNoEntityCollision(this);
	}

	@Override
	public boolean canBeRiddenInWater() {
		return true;
	}

	@Override
	public boolean canBeRiddenInWater(Entity rider) {
		return true;
	}
	
	@Override
	public int getTalkInterval() {
		return 200;
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundRegister.DOLPHIN_SAY.get();
	}

	@Override
	public boolean isPushedByWater() {
		return false;
	}
	
	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.WATER;
	}

	@Override
	protected PathNavigator createNavigator(World worldIn) {
		return new SwimmerPathNavigator(this, worldIn);
	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return PVZLoot.ZOMBIE_DOLPHIN;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.ZOMBIE_DOLPHIN;
	}

	@Override
	public boolean checkCanZombieTarget(LivingEntity target) {
		return target instanceof DolphinEntity || target instanceof PlayerEntity
				|| target instanceof FoodieZombieEntity;
	}
	
	static class MoveHelperController extends MovementController {
		private final ZombieDolphinEntity dolphin;

		public MoveHelperController(ZombieDolphinEntity dolphinIn) {
			super(dolphinIn);
			this.dolphin = dolphinIn;
		}

		public void tick() {
			if (this.dolphin.isInWater()) {
				this.dolphin.setMotion(this.dolphin.getMotion().add(0.0D, 0.005D, 0.0D));
			}

			if (this.action == MovementController.Action.MOVE_TO && !this.dolphin.getNavigator().noPath()) {
				double d0 = this.posX - this.dolphin.getPosX();
				double d1 = this.posY - this.dolphin.getPosY();
				double d2 = this.posZ - this.dolphin.getPosZ();
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				if (d3 < (double) 2.5000003E-7F) {
					this.mob.setMoveForward(0.0F);
				} else {
					float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
					this.dolphin.rotationYaw = this.limitAngle(this.dolphin.rotationYaw, f, 10.0F);
					this.dolphin.renderYawOffset = this.dolphin.rotationYaw;
					this.dolphin.rotationYawHead = this.dolphin.rotationYaw;
					float f1 = (float) (this.speed
							* this.dolphin.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
					if (this.dolphin.isInWater()) {
						this.dolphin.setAIMoveSpeed(f1 * 0.02F);
						float f2 = -((float) (MathHelper.atan2(d1, (double) MathHelper.sqrt(d0 * d0 + d2 * d2))
								* (double) (180F / (float) Math.PI)));
						f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
						this.dolphin.rotationPitch = this.limitAngle(this.dolphin.rotationPitch, f2, 5.0F);
						float f3 = MathHelper.cos(this.dolphin.rotationPitch * ((float) Math.PI / 180F));
						float f4 = MathHelper.sin(this.dolphin.rotationPitch * ((float) Math.PI / 180F));
						this.dolphin.moveForward = f3 * f1;
						this.dolphin.moveVertical = -f4 * f1;
					} else {
						this.dolphin.setAIMoveSpeed(f1 * 0.1F);
					}

				}
			} else {
				this.dolphin.setAIMoveSpeed(0.0F);
				this.dolphin.setMoveStrafing(0.0F);
				this.dolphin.setMoveVertical(0.0F);
				this.dolphin.setMoveForward(0.0F);
			}
		}
	}

	static class DolphinJumpGoal extends JumpGoal {
		private static final int[] JUMP_DISTANCES = new int[] { 0, 1, 4, 5, 6, 7 };
		private final ZombieDolphinEntity dolphin;
		private final int chance;
		private boolean field_220713_d;

		public DolphinJumpGoal(ZombieDolphinEntity zombie, int chance) {
			this.dolphin = zombie;
			this.chance = chance;
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state
		 * necessary for execution in this method as well.
		 */
		public boolean shouldExecute() {
			if (this.dolphin.getRNG().nextInt(this.chance) != 0) {
				return false;
			} else {
				Direction direction = this.dolphin.getAdjustedHorizontalFacing();
				int i = direction.getXOffset();
				int j = direction.getZOffset();
				BlockPos blockpos = new BlockPos(this.dolphin);

				for (int k : JUMP_DISTANCES) {
					if (!this.canJumpTo(blockpos, i, j, k) || !this.isAirAbove(blockpos, i, j, k)) {
						return false;
					}
				}

				return true;
			}
		}

		private boolean canJumpTo(BlockPos pos, int dx, int dz, int scale) {
			BlockPos blockpos = pos.add(dx * scale, 0, dz * scale);
			return this.dolphin.world.getFluidState(blockpos).isTagged(FluidTags.WATER)
					&& !this.dolphin.world.getBlockState(blockpos).getMaterial().blocksMovement();
		}

		@SuppressWarnings("deprecation")
		private boolean isAirAbove(BlockPos pos, int dx, int dz, int scale) {
			return this.dolphin.world.getBlockState(pos.add(dx * scale, 1, dz * scale)).isAir()
					&& this.dolphin.world.getBlockState(pos.add(dx * scale, 2, dz * scale)).isAir();
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		public boolean shouldContinueExecuting() {
			double d0 = this.dolphin.getMotion().y;
			return (!(d0 * d0 < (double) 0.03F) || this.dolphin.rotationPitch == 0.0F
					|| !(Math.abs(this.dolphin.rotationPitch) < 10.0F) || !this.dolphin.isInWater())
					&& !this.dolphin.onGround;
		}

		public boolean isPreemptible() {
			return false;
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void startExecuting() {
			Direction direction = this.dolphin.getAdjustedHorizontalFacing();
			this.dolphin.setMotion(this.dolphin.getMotion().add((double) direction.getXOffset() * 0.6D, 0.7D,
					(double) direction.getZOffset() * 0.6D));
			this.dolphin.getNavigator().clearPath();
		}

		/**
		 * Reset the task's internal state. Called when this task is interrupted by
		 * another one
		 */
		public void resetTask() {
			this.dolphin.rotationPitch = 0.0F;
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		@SuppressWarnings("deprecation")
		public void tick() {
			boolean flag = this.field_220713_d;
			if (!flag) {
				IFluidState ifluidstate = this.dolphin.world.getFluidState(new BlockPos(this.dolphin));
				this.field_220713_d = ifluidstate.isTagged(FluidTags.WATER);
			}

			if (this.field_220713_d && !flag) {
				this.dolphin.playSound(SoundEvents.ENTITY_DOLPHIN_JUMP, 1.0F, 1.0F);
			}

			Vec3d vec3d = this.dolphin.getMotion();
			if (vec3d.y * vec3d.y < (double) 0.03F && this.dolphin.rotationPitch != 0.0F) {
				this.dolphin.rotationPitch = MathHelper.rotLerp(this.dolphin.rotationPitch, 0.0F, 0.2F);
			} else {
				double d0 = Math.sqrt(Entity.horizontalMag(vec3d));
				double d1 = Math.signum(-vec3d.y) * Math.acos(d0 / vec3d.length()) * (double) (180F / (float) Math.PI);
				this.dolphin.rotationPitch = (float) d1;
			}

		}
	}

}
