package com.hungteen.pvz.entity.ai;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.util.Hand;

public class ZombieMeleeAttackGoal extends MeleeAttackGoal {

	public ZombieMeleeAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
		super(creature, speedIn, useLongMemory);
	}

	@Override
	public boolean shouldContinueExecuting() {
		if (this.attacker instanceof PVZZombieEntity && !((PVZZombieEntity) this.attacker).canZombieNormalUpdate()) {
			return false;
		}
		return super.shouldContinueExecuting() && EntityUtil.checkCanEntityTarget(this.attacker, this.attacker.getAttackTarget());
	}

	@Override
	protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
		double d0 = this.getAttackReachSqr(enemy);
//		System.out.println(this.attackTick);
		if (distToEnemySqr <= d0 && this.attackTick <= 0) {
			this.attackTick = ((PVZZombieEntity) this.attacker).getAttackCD();
			this.attacker.swingArm(Hand.MAIN_HAND);
//			System.out.println(this.attacker.world.isRemote);
			this.attacker.attackEntityAsMob(enemy);
		}
	}

	@Override
	protected double getAttackReachSqr(LivingEntity attackTarget) {
		double two = Math.sqrt(2);
		double dis = (this.attacker.getWidth() / two + attackTarget.getWidth() / two + 0.5f);
		return dis * dis;
	}
}
