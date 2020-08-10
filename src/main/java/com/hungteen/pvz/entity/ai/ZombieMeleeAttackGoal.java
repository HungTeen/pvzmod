package com.hungteen.pvz.entity.ai;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.util.Hand;

public class ZombieMeleeAttackGoal extends MeleeAttackGoal {

	public ZombieMeleeAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
		super(creature, speedIn, useLongMemory);
	}

	@Override
	public void tick() {
		if (this.attacker instanceof PVZZombieEntity) {
			if (((PVZZombieEntity) this.attacker).getIsButter() || ((PVZZombieEntity) this.attacker).getIsFrozen()) {
				return;
			}
		}
		super.tick();
	}

	@Override
	protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
		double d0 = this.getAttackReachSqr(enemy);
//		System.out.println(this.attackTick);
		if (distToEnemySqr <= d0 && this.attackTick <= 0) {
			this.attackTick = ((PVZZombieEntity) this.attacker).getAttackCD();
			this.attacker.swingArm(Hand.MAIN_HAND);
			this.attacker.attackEntityAsMob(enemy);
		}
	}

	@Override
	protected double getAttackReachSqr(LivingEntity attackTarget) {
		double dis = (this.attacker.getWidth() / 2 + attackTarget.getWidth() / 2 + 1f);
		return dis * dis;
	}
}
