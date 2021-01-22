package com.hungteen.pvz.entity.ai;

import java.util.EnumSet;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
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
		this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	@Override
	public boolean shouldExecute() {
		long currentTime = this.attacker.world.getGameTime();
		if (currentTime - this.excuteTime < 20L) return false;//search each second.
		this.excuteTime = currentTime;
		LivingEntity target = this.attacker.getAttackTarget();
//		System.out.println(target);
		if (target == null || ! target.isAlive()) {
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
	public void startExecuting() {
		this.attacker.getNavigator().setPath(this.path, this.speed);
		this.attacker.setAggroed(true);
		this.delayCounter = 0;
		this.delayCnt = 1;
	}

	@Override
	public void resetTask() {
		this.attacker.setAttackTarget(null);
		this.attacker.setAggroed(false);
		this.attacker.getNavigator().clearPath();
	}

	@Override
	public void tick() {
		LivingEntity target = this.attacker.getAttackTarget();
		this.attacker.getLookController().setLookPositionWithEntity(target, 30.0F, 30.0F);
		--this.delayCounter;
		if(this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || target.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.attacker.getRNG().nextFloat() < 0.05F)) {
			this.targetX = target.getPosX();
	        this.targetY = target.getPosY();
	        this.targetZ = target.getPosZ();
			this.delayCounter = 5 + this.attacker.getRNG().nextInt(10);
//			System.out.println("find path");
			if(!this.attacker.getNavigator().tryMoveToEntityLiving(target, this.speed)) {
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
		double dis = this.attacker.getDistanceSq(target);
		double range = this.getAttackReachSqr(target);
		if (range >= dis && this.attackTick <= 0) {
			this.attackTick = this.attacker.getAttackCD();
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
