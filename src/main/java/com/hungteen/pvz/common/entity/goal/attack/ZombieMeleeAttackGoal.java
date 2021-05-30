package com.hungteen.pvz.common.entity.goal.attack;

import java.util.EnumSet;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.Hand;

public class ZombieMeleeAttackGoal extends Goal {

	protected final PVZZombieEntity attacker;
	protected int attackTick;
	private Path path;
	private double targetX;
	private double targetY;
	private double targetZ;
	protected float speed = 1.0f;
	private int delayCounter;
	private int delayCnt = 1;
	private long excuteTime = 0;

	public ZombieMeleeAttackGoal(PVZZombieEntity creature) {
		this.attacker = creature;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean canUse() {
		long currentTime = this.attacker.level.getGameTime();
		if (currentTime - this.excuteTime < 20L) return false;//search each second.
		this.excuteTime = currentTime;
		LivingEntity target = this.attacker.getTarget();
//		System.out.println(target);
		if (target == null || ! target.isAlive()) {
			return false;
		}
		this.path = this.attacker.getNavigation().createPath(target, 0);
		if (this.path != null) {
			return true;
		} else {
			return this.getAttackReachSqr(target) >= this.attacker.distanceToSqr(target);
		}
	}

	@Override
	public boolean canContinueToUse() {
		LivingEntity target = this.attacker.getTarget();
		if (target == null || ! target.isAlive()) {
			return false;
		}
//		if (this.getAttackReachSqr(target) < this.attacker.getDistanceSq(target)
//				&& this.attacker.getNavigator().noPath()) {
//			return false;
//		}
//		if(this.attacker instanceof TrickZombieEntity) {
//			System.out.println(EntityUtil.checkCanEntityTarget(this.attacker, target));
//		}
		return EntityUtil.checkCanEntityTarget(this.attacker, target);
	}

	@Override
	public void start() {
		this.attacker.getNavigation().moveTo(this.path, this.speed);
		this.attacker.setAggressive(true);
		this.delayCounter = 0;
		this.delayCnt = 1;
	}

	@Override
	public void stop() {
		this.attacker.setTarget(null);
		this.attacker.setAggressive(false);
		this.attacker.getNavigation().stop();
	}

	@Override
	public void tick() {
		LivingEntity target = this.attacker.getTarget();
		this.attacker.getLookControl().setLookAt(target, 30.0F, 30.0F);
		--this.delayCounter;
		if(this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || target.distanceToSqr(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.attacker.getRandom().nextFloat() < 0.05F)) {
			this.targetX = target.getX();
	        this.targetY = target.getY();
	        this.targetZ = target.getZ();
			this.delayCounter = 5 + this.attacker.getRandom().nextInt(10);
//			System.out.println("find path");
			if(!this.attacker.getNavigation().moveTo(target, this.speed)) {
			    this.delayCounter += 20 * this.delayCnt;
			    this.delayCnt = Math.min(10, this.delayCnt + 1);
		    } else {
		    	this.delayCnt = 1;
		    }
		}
		this.attackTick = Math.max(this.attackTick - 1, 0);
		this.checkAndPerformAttack(target);
	}

	protected void checkAndPerformAttack(LivingEntity target) {
		double dis = this.attacker.distanceToSqr(target);
		double range = this.getAttackReachSqr(target);
		if (range >= dis && this.attackTick <= 0) {
			this.attackTick = this.attacker.getAttackCD();
			this.attacker.swing(Hand.MAIN_HAND);
			this.attacker.doHurtTarget(target);
		}
	}

	protected double getAttackReachSqr(LivingEntity attackTarget) {
		double two = Math.sqrt(2);
		double dis = (this.attacker.getBbWidth() / two + attackTarget.getBbWidth() / two + 0.5f);
		return dis * dis;
	}

}
