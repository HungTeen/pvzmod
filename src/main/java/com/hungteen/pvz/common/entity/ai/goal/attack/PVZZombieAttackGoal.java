package com.hungteen.pvz.common.entity.ai.goal.attack;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;

public class PVZZombieAttackGoal extends PVZMeleeAttackGoal {

	protected final PVZZombieEntity zombie;
	
	public PVZZombieAttackGoal(PVZZombieEntity creature, boolean useLongMemory) {
		super(creature, 1.0, useLongMemory);
		this.zombie = creature;
	}
	
	@Override
	public void tick() {
		if(! this.zombie.canZombieNormalUpdate()) {
			this.zombie.setAggressive(false);
			return ;
		}
		super.tick();
	}
	
	@Override
	protected void checkAndPerformAttack(LivingEntity target) {
		double dis = EntityUtil.getNearestDistance(this.attacker, target);
		double range = this.getAttackReachSqr(target);
		if (range >= dis) {
			if(this.attackTick <= 0) {
			    this.attackTick = this.zombie.getAttackCD();
			    this.attacker.swing(Hand.MAIN_HAND);
			    this.attacker.doHurtTarget(target);
			}
		} else {
			if((this.attacker.getNavigation().getPath() == null || this.attacker.getNavigation().getPath().isDone()) && dis <= 10 && this.attacker.getDeltaMovement().length() <= 1 && this.attacker.isOnGround()) {
				Vector3d speed = target.position().subtract(this.attacker.position()).normalize();
				this.attacker.setDeltaMovement(speed.scale(this.attacker.getAttributeValue(Attributes.MOVEMENT_SPEED)));
			}
		}
		this.attacker.setAggressive(dis <= 20);
	}

}
