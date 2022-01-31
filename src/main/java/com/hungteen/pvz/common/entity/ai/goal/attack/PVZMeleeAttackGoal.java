package com.hungteen.pvz.common.entity.ai.goal.attack;

import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.BlockPos;

import java.util.EnumSet;

public abstract class PVZMeleeAttackGoal extends Goal {

	protected final CreatureEntity attacker;
	protected int attackTick;
	protected final double speedTowardsTarget;
	protected final boolean longMemory;
	protected Path path;
	private int delayCnt = 1;
	private int delayCounter;
	private double targetX;
	private double targetY;
	private double targetZ;
	protected final int attackInterval = 20;
	private long lastExcuteTime = 0;
	private int failedPathFindingPenalty = 0;
	protected boolean canPenalize = false;

	public PVZMeleeAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
	    this.attacker = creature;
	    this.speedTowardsTarget = speedIn;
	    this.longMemory = useLongMemory;
	    this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}
	
	@Override
	public boolean canUse() {
		final long currentTime = this.attacker.level.getGameTime();
		if (currentTime - this.lastExcuteTime < 20L) {
			return false; //search each second.
		}
		this.lastExcuteTime = currentTime;
		final LivingEntity living = this.attacker.getTarget();
		if (! EntityUtil.isEntityValid(living)) return false;
		if (canPenalize) {
			if (-- this.delayCounter <= 0) {
				this.path = this.attacker.getNavigation().createPath(living, 0);
				this.delayCounter = 4 + this.attacker.getRandom().nextInt(7);
				return this.path != null;
			} 
			return true;
		}
		this.path = this.attacker.getNavigation().createPath(living, 0);
		if (this.path != null) {
			return true;//has walk path.
		}
		return this.getAttackReachSqr(living) >= EntityUtil.getNearestDistance(attacker, living);// target is already in attack range
	}
	
	@Override
	public boolean canContinueToUse() {
		LivingEntity living = this.attacker.getTarget();
		if (! EntityUtil.isEntityValid(living)) return false;
		if (! this.longMemory) return ! this.attacker.getNavigation().isDone();
		if (! this.attacker.isWithinRestriction(new BlockPos(living.position()))) {
			return false;
		}
		return EntityUtil.checkCanEntityBeAttack(this.attacker, living);
	}

	@Override
	public void start() {
		this.attacker.getNavigation().moveTo(this.path, this.speedTowardsTarget);
		this.delayCounter = 0;
	}

	@Override
	public void stop() {
		this.attacker.setAggressive(false);
		this.attacker.getNavigation().stop();
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void tick() {
		LivingEntity target = this.attacker.getTarget();
		this.attacker.getLookControl().setLookAt(target, 30.0F, 30.0F);
		-- this.delayCounter;
		if ((this.longMemory || EntityUtil.canSeeEntity(this.attacker, target)) && this.delayCounter <= 0 
				&& (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D
						|| target.distanceToSqr(this.targetX, this.targetY, this.targetZ) >= 1.0D
						|| this.attacker.getRandom().nextFloat() < 0.05F)) {
			this.targetX = target.getX();
			this.targetY = target.getY();
			this.targetZ = target.getZ();
			this.delayCounter = 4 + this.attacker.getRandom().nextInt(7);
			if (this.canPenalize) {
				this.delayCounter += failedPathFindingPenalty;
				if (this.attacker.getNavigation().getPath() != null) {
					net.minecraft.pathfinding.PathPoint finalPathPoint = this.attacker.getNavigation().getPath().getEndNode();
					if (finalPathPoint != null && target.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1) {
						failedPathFindingPenalty = 0;
					} else {
						failedPathFindingPenalty += 10;
					}
				} else {
					failedPathFindingPenalty += 10;
				}
			}
			if (! this.attacker.getNavigation().moveTo(target, this.speedTowardsTarget)) {
				this.delayCounter += 15 * this.delayCnt;
				this.delayCnt = Math.min(10, this.delayCnt + 1);
		    } else {
		    	this.delayCnt = Math.max(1, this.delayCnt - 1);
		    }
		}
		this.attackTick = Math.max(this.attackTick - 1, 0);
		this.checkAndPerformAttack(target);
	}

	protected abstract void checkAndPerformAttack(LivingEntity target);

	protected double getAttackReachSqr(LivingEntity attackTarget) {
		return EntityUtil.getAttackRange(attacker, attackTarget, 0.5F);
	}
	
}
