package com.hungteen.pvz.entity.zombie.poolday;

import java.util.EnumSet;

import com.hungteen.pvz.entity.ai.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.ai.ZombieMeleeAttackGoal;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SnorkelZombieEntity extends PVZZombieEntity {

	private static final float UP_SPEED = 0.05f;
	protected PathNavigator waterNavigator;
	protected PathNavigator groundNavigator;

	public SnorkelZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
//		this.moveController=new MoveHelperController(this);
		this.waterNavigator = new SwimmerPathNavigator(this, worldIn);
		this.groundNavigator = new GroundPathNavigator(this, worldIn);
		this.setPose(Pose.STANDING);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(4, new DiveGoal(this));
		this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(0, new ZombieMeleeAttackGoal(this, 1.0, false));
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 80, 60));
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.LITTLE_FAST);
	}

//	@Override
//	public void normalZombieTick() {
//		super.normalZombieTick();
//		if(!world.isRemote&&this.getAttackTarget()!=null&&this.isInWater()) {
//			Vec3d dir = this.getAttackTarget().getPositionVec().add(this.getPositionVec().inverse()).normalize();
//			double speed = this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue();
//			float modify = 0.25f;
//			dir=dir.scale(speed*modify);
//			this.setMotion(dir.getX(),dir.getY(),dir.getZ());
//		}
//	}

	@Override
	public void updateSwimming() {
		if (!this.world.isRemote) {
			if (this.isServerWorld() && this.isInWater()) {
				this.navigator = this.waterNavigator;
				this.setSwimming(true);
			} else {
				this.navigator = this.groundNavigator;
				this.setSwimming(false);
			}
		}
	}

	@Override
	public boolean isPushedByWater() {
		return false;
	}

	@Override
	public EntitySize getSize(Pose poseIn) {
		return new EntitySize(0.7f, 1.9f, false);
	}

	@Override
	public boolean canBeFrozen() {
		return false;
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.93f;
	}

	@Override
	public float getLife() {
		return 20;
	}

	public float getMaxSubmergedHeight() {
		return this.getWidth() * 0.85f;
	}

	static class DiveGoal extends Goal {

		private final SnorkelZombieEntity snorkel;

		public DiveGoal(SnorkelZombieEntity entity) {
			this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP));
			this.snorkel = entity;
		}

		@Override
		public boolean shouldExecute() {
			return this.snorkel.isInWater() && this.snorkel.getSubmergedHeight() > this.snorkel.getMaxSubmergedHeight();
		}

		@Override
		public void startExecuting() {
			Vec3d v = this.snorkel.getMotion();
			this.snorkel.setMotion(v.getX(), UP_SPEED, v.getZ());
		}

	}

//	static class MoveHelperController extends MovementController {
//		private final SnorkelZombieEntity zombie;
//
//		public MoveHelperController(SnorkelZombieEntity zombie) {
//			super(zombie);
//			this.zombie = zombie;
//		}
//
//		public void tick() {
//			if (this.action == MovementController.Action.MOVE_TO && !this.zombie.getNavigator().noPath()) {
//				double d0 = this.posX - this.zombie.getPosX();
//				double d2 = this.posZ - this.zombie.getPosZ();
//				double d3 = d0 * d0 + d2 * d2;
//				if (d3 < (double) 2.5000003E-7F) {
//					this.mob.setMoveForward(0.0F);
//				} else {
//					float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
//					this.zombie.rotationYaw = this.limitAngle(this.zombie.rotationYaw, f, 10.0F);
//					this.zombie.renderYawOffset = this.zombie.rotationYaw;
//					this.zombie.rotationYawHead = this.zombie.rotationYaw;
//					float f1 = (float) (this.speed
//							* this.zombie.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
//					if (this.zombie.isInWater()) {
//						this.zombie.setAIMoveSpeed(f1 * 0.02F);
//						float f2 = -((float) (MathHelper.atan2(0, (double) MathHelper.sqrt(d0 * d0 + d2 * d2))
//								* (double) (180F / (float) Math.PI)));
//						f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
//						this.zombie.rotationPitch = this.limitAngle(this.zombie.rotationPitch, f2, 5.0F);
//						float f3 = MathHelper.cos(this.zombie.rotationPitch * ((float) Math.PI / 180F));
//						float f4 = MathHelper.sin(this.zombie.rotationPitch * ((float) Math.PI / 180F));
//						this.zombie.moveForward = f3 * f1;
//						this.zombie.moveVertical = -f4 * f1;
//					} else {
//						this.zombie.setAIMoveSpeed(f1 * 0.1F);
//					}
//
//				}
//			} else {
//				this.zombie.setAIMoveSpeed(0.0F);
//				this.zombie.setMoveStrafing(0.0F);
//				this.zombie.setMoveVertical(0.0F);
//				this.zombie.setMoveForward(0.0F);
//			}
//		}
//	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.SNORKEL_ZOMBIE;
	}

}
