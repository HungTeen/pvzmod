package com.hungteen.pvz.common.entity.ai.goal.attack;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;

public class PVZZombieAttackGoal extends PVZMeleeAttackGoal {

	protected final PVZZombieEntity zombie;
	protected final int LeapCD = 50;
	protected int leapTick = 0;
	
	public PVZZombieAttackGoal(PVZZombieEntity creature, boolean useLongMemory) {
		super(creature, 1.0, useLongMemory);
		this.zombie = creature;
	}
	
	@Override
	public void tick() {
		if(! this.zombie.canNormalUpdate()) {
			this.zombie.setAggressive(false);
			return ;
		}
		super.tick();
	}
	
	@Override
	protected void checkAndPerformAttack(LivingEntity target) {
		final double dis = EntityUtil.getNearestDistance(this.attacker, target);
		double range = this.getAttackReachSqr(target);
		if (range >= dis) {
			if(this.attackTick <= 0) {
			    this.attackTick = this.zombie.getAttackCD();
			    this.attacker.swing(Hand.MAIN_HAND);
			    this.attacker.doHurtTarget(target);
			}
		} else {
			if(this.zombie.canClimbWalls()){
				this.checkLeapToTarget(target);
			}
		}
		this.attacker.setAggressive(dis <= 20);
	}

	/**
	 * leap to target when zombie can not reach there.
	 */
	protected void checkLeapToTarget(LivingEntity target){
		if(++ this.leapTick >= this.LeapCD){
			if((this.attacker.getNavigation().getPath() == null || this.attacker.getNavigation().getPath().isDone())
					&& this.zombie.canNormalUpdate() && this.attacker.getDeltaMovement().length() <= 0.1D && this.attacker.isOnGround()) {
				final float random = this.zombie.getRandom().nextFloat();
				if(random < 0.6) {
					Vector3d speed = target.position().subtract(this.attacker.position()).normalize();
					this.attacker.setDeltaMovement(speed.scale(this.attacker.getRandom().nextDouble() * 0.4 + 0.4).scale(this.attacker.getAttributeValue(Attributes.MOVEMENT_SPEED)));
				} else if(this.zombie.getLastHurtByMob() != null) {
					if(random < 0.9) {
						if(this.zombie.distanceTo(target) >= this.zombie.distanceTo(this.zombie.getLastHurtByMob())) {
							this.zombie.setTarget(this.zombie.getLastHurtByMob());
						}
					} else {
						this.zombie.setTarget(this.zombie.getLastHurtByMob());
					}
				}
				this.leapTick = 0;
			}
		}
	}

}
