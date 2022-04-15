package com.hungteen.pvz.common.entity.ai.target;

import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.AABB;

import java.util.EnumSet;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-30 22:18
 **/
public abstract class PVZTargetGoal extends Goal {

    protected final Mob mob;
    protected LivingEntity targetMob;
    protected final boolean mustSee;
    protected final boolean mustReach;
    protected int targetChance = 5;

    public PVZTargetGoal(Mob mobIn, boolean mustSee, boolean mustReach) {
        this.mob = mobIn;
        this.mustSee = mustSee;
        this.mustReach = mustReach;
        this.setFlags(EnumSet.of(Goal.Flag.TARGET));
    }


    @Override
    public void start() {
        this.mob.setTarget(this.targetMob);
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity entity = this.mob.getTarget();
        //alternative target not exist.
        if (!EntityUtil.isEntityValid(this.targetMob)) {
            return false;
        }
        if (!EntityUtil.isEntityValid(entity)) {
            entity = this.targetMob;
        } else if (!entity.is(this.targetMob)) {
            this.targetMob = entity;
        }
        if (!EntityUtil.isEntityValid(entity)) {
            return false;
        }
        //already out range.
        final double range = this.getRange();
        if (this.mob.distanceToSqr(entity) > range * range) {
            return false;
        }
        //can attack.
        if (EntityUtil.canAttackEntity(mob, entity) && (this.mustReach || (!this.mustSee || this.checkSenses(entity)))) {
            if (this.checkOther(entity)) {
                this.mob.setTarget(entity);
                return true;
            }
        }
        return false;
    }

    @Override
    public void stop() {
        this.mob.setTarget(null);
    }

    protected boolean checkSenses(Entity entity) {
        return this.mob.getSensing().hasLineOfSight(entity);
    }

    protected boolean checkOther(LivingEntity entity) {
        //invisible entity need closer, except it has light eyes effect.
//        if (this.mustSee && entity.isInvisible() && this.mob.distanceToSqr(entity) > 100
//                && !this.mob.hasEffect(EffectRegister.LIGHT_EYE_EFFECT.get())) {
//            return false;
//        }
        return true;
    }

    protected AABB getAABB() {
        final double range = this.getRange();
        return new AABB(this.mob.getX() + range, this.mob.getY() + range,
                this.mob.getZ() + range, this.mob.getX() - range,
                this.mob.getY() - range, this.mob.getZ() - range);
    }

    protected double getRange(){
        return this.mob.getAttributeValue(Attributes.FOLLOW_RANGE);
    }
}