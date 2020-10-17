package com.hungteen.pvz.entity.ai;

import java.util.EnumSet;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;

public class PVZMeleeAttackGoal extends Goal {

	protected final CreatureEntity attacker;
	protected int attackTick;
	private Path path;
	protected float speed = 1.0f;

	public PVZMeleeAttackGoal(CreatureEntity creature) {
		this.attacker = creature;
		this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean shouldExecute() {
		LivingEntity target = this.attacker.getAttackTarget();
		if (target == null || !target.isAlive()) {
			return false;
		}
		this.path = this.attacker.getNavigator().getPathToEntity(target, 0);
		if (this.path != null) {
			return true;
		} else {
			return this.getAttackReachSqr(target) >= this.attacker.getDistanceSq(target);
		}
	}

	@Override
	public boolean shouldContinueExecuting() {
		LivingEntity target = this.attacker.getAttackTarget();
		if (target == null || !target.isAlive()) {
			return false;
		}
		if (this.getAttackReachSqr(target) < this.attacker.getDistanceSq(target)
				&& this.attacker.getNavigator().noPath()) {
			return false;
		}
		return EntityUtil.checkCanEntityTarget(this.attacker, target);
	}

	@Override
	public void startExecuting() {
		this.attacker.getNavigator().setPath(this.path, this.speed);
		this.attacker.setAggroed(true);
	}

	public void resetTask() {
		LivingEntity livingentity = this.attacker.getAttackTarget();
		if (!EntityPredicates.CAN_AI_TARGET.test(livingentity)) {
			this.attacker.setAttackTarget((LivingEntity) null);
		}
		this.attacker.setAggroed(false);
		this.attacker.getNavigator().clearPath();
	}

	public void tick() {
		LivingEntity target = this.attacker.getAttackTarget();
		this.attacker.getLookController().setLookPositionWithEntity(target, 30.0F, 30.0F);
		this.attacker.getNavigator().tryMoveToEntityLiving(target, this.speed);
		this.attackTick = Math.max(this.attackTick - 1, 0);
		this.checkAndPerformAttack(target);
	}

	protected void checkAndPerformAttack(LivingEntity target) {
		double dis = this.attacker.getDistanceSq(target);
		double range = this.getAttackReachSqr(target);
		if (range >= dis && this.attackTick <= 0) {
			this.attackTick = ((PVZZombieEntity) this.attacker).getAttackCD();
			this.attacker.swingArm(Hand.MAIN_HAND);
			this.attacker.attackEntityAsMob(target);
		}
	}

	protected double getAttackReachSqr(LivingEntity attackTarget) {
		double two = Math.sqrt(2);
		double dis = (this.attacker.getWidth() / two + attackTarget.getWidth() / two + 0.5f);
		return dis * dis;
	}

}
