package com.hungteen.pvz.entity.ai.attack;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;

public class PVZZombieAttackGoal extends PVZMeleeAttackGoal {

	private final PVZZombieEntity zombie;
	
	public PVZZombieAttackGoal(PVZZombieEntity creature, boolean useLongMemory) {
		super(creature, 1.0, useLongMemory);
		this.zombie = creature;
	}
	
	@Override
	protected void checkAndPerformAttack(LivingEntity target) {
		double dis = this.attacker.getDistanceSq(target);
		double range = this.getAttackReachSqr(target);
		if (range >= dis && this.attackTick <= 0) {
			this.attackTick = this.zombie.getAttackCD();
			this.attacker.swingArm(Hand.MAIN_HAND);
			this.attacker.attackEntityAsMob(target);
		}
	}

}
