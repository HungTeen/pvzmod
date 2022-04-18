package com.hungteen.pvz.common.entity.ai.goal.attack;

import com.hungteen.pvz.common.entity.zombie.base.PVZZombie;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec3;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-18 12:46
 **/
public class PVZZombieMeleeAttackGoal extends PVZMeleeAttackGoal {

    protected final PVZZombie zombie;
    protected final int LeapCD = 50;
    protected int zombieAttackTick = 0;
    protected int leapTick = 0;

    public PVZZombieMeleeAttackGoal(PVZZombie mob, double speedIn, boolean useLongMemory) {
        super(mob, speedIn, useLongMemory);
        this.zombie = mob;
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
    protected void checkAndPerformAttack(LivingEntity target, double dis) {
        double range = this.getAttackReachSqr(target);
        this.zombieAttackTick = Math.max(0, this.zombieAttackTick - 1);
        if (range >= dis) {
            if(isTimeToAttack()) {
                this.resetAttackCooldown();
                this.zombie.swing(InteractionHand.MAIN_HAND);
                this.zombie.doHurtTarget(target);
            }
        } else {
            //stop move.
            if(this.zombie.canNormalUpdate() && this.zombie.getDeltaMovement().length() <= 0.1D){
                this.checkLeapToTarget(target);
            }
        }
        this.zombie.setAggressive(dis <= 20);
    }

    /**
     * leap to target when zombie can not reach there.
     */
    protected void checkLeapToTarget(LivingEntity target){
        if(++ this.leapTick >= this.LeapCD){
            if((this.zombie.getNavigation().getPath() == null || this.zombie.getNavigation().getPath().isDone()) && this.zombie.isOnGround()) {
                //ground jump or change target.
                final float random = this.zombie.getRandom().nextFloat();
                if(random < 0.55) {
                    Vec3 speed = target.position().subtract(this.zombie.position()).normalize();
                    this.zombie.setDeltaMovement(speed.scale(this.zombie.getRandom().nextDouble() * 0.4 + 0.4).scale(this.zombie.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                } else if(this.zombie.getLastHurtByMob() != null) {
                    if(random < 0.85) {
                        if(this.zombie.distanceTo(target) >= this.zombie.distanceTo(this.zombie.getLastHurtByMob())) {
                            this.zombie.setTarget(this.zombie.getLastHurtByMob());
                        }
                    } else {
                        this.zombie.setTarget(this.zombie.getLastHurtByMob());
                    }
                }
                this.leapTick = 0;
            } else if(this.zombie.isInWater()) {
                Vec3 speed = target.position().subtract(this.zombie.position()).normalize();
                this.zombie.setDeltaMovement(speed.scale(this.zombie.getRandom().nextDouble() * 0.3 + 0.2).scale(this.zombie.getAttributeValue(Attributes.MOVEMENT_SPEED)));
                this.leapTick = 0;
            }
        }
    }

    @Override
    protected boolean isTimeToAttack() {
        return this.zombieAttackTick <= 0;
    }

    @Override
    protected void resetAttackCooldown() {
        this.zombieAttackTick = this.adjustedTickDelay((int) this.zombie.getCurrentAttackCD());
    }

}
