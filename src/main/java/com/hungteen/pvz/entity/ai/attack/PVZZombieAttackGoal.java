package com.hungteen.pvz.entity.ai.attack;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;

public class PVZZombieAttackGoal extends PVZMeleeAttackGoal {

	protected final PVZZombieEntity zombie;
	
	public PVZZombieAttackGoal(PVZZombieEntity creature, boolean useLongMemory) {
		super(creature, 1.0, useLongMemory);
		this.zombie = creature;
	}
	
	@Override
	public void tick() {
		if(! this.zombie.canZombieNormalUpdate()) return ;
		super.tick();
	}
	
	@Override
	protected void checkAndPerformAttack(LivingEntity target) {
		double dis = EntityUtil.getNearestDistance(this.attacker, target);
		double range = this.getAttackReachSqr(target);
		if (range >= dis && this.attackTick <= 0) {
			this.attackTick = this.zombie.getAttackCD();
			this.attacker.swingArm(Hand.MAIN_HAND);
			this.attacker.attackEntityAsMob(target);
		}
	}

}
