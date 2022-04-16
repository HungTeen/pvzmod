package com.hungteen.pvz.common.entity.plant.base;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.PVZDamageSource;
import com.hungteen.pvz.common.entity.ai.target.PVZNearestTargetGoal;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-16 17:04
 **/
public abstract class CloseInstantPlant extends PVZPlant {

    public CloseInstantPlant(EntityType<? extends PVZPlant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(0, new CloseNearestTargetGoal(this, true, false));
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(this.getCloseDistance());
    }

    @Override
    protected void normalPlantTick() {
        super.normalPlantTick();
        if(! this.level.isClientSide && this.canCheckDistance()) {
            if(EntityUtil.isEntityValid(this.getTarget())) {//target is close enough
                this.focusOnTarget(this.getTarget());
                final int time = this.getAnimTick();
                if(time >= this.getAnimationCD()) {
                    this.performAttack(this.getTarget());
                }
                this.setAnimTick(time + 1);
            } else {
                this.setAnimTick(0);
            }
        }
    }

    /**
     * perform when target is in range.
     * {@link #normalPlantTick()}
     */
    public void focusOnTarget(@Nonnull LivingEntity target) {
        this.getLookControl().setLookAt(target, 30f, 30f);
    }

    /**
     * whether plants can check distance and perform attack.
     * {@link #normalPlantTick()}
     */
    public boolean canCheckDistance() {
        return true;
    }

    @Override
    public int getEnergeticDuration() {
        return 20;
    }

    /**
     * perform attack when target is close.
     * {@link #normalPlantTick()}
     */
    public abstract void performAttack(LivingEntity target);

    @Override
    public boolean isPlantImmuneTo(DamageSource source) {
        if(this.canBeImmuneToEnforce(source.getEntity())) {
            return super.isPlantImmuneTo(source) || PVZDamageSource.isEnforceDamage(source);
        }
        return super.isPlantImmuneTo(source);
    }

    protected boolean canBeImmuneToEnforce(Entity entity) {
        return checkCanPAZTarget(entity);
    }

    /**
     * distance needed when attack.
     * {@link #registerGoals()}
     */
    public float getCloseDistance() {
        return 3F;
    }

    /**
     * use for render.
     */
    public int getAnimationCD(){
        return 10;
    }

    private static class CloseNearestTargetGoal extends PVZNearestTargetGoal {

        public CloseNearestTargetGoal(Mob mobIn, boolean mustSee, boolean mustReach) {
            super(mobIn, mustSee, mustReach);
            this.targetChance = 30;
        }

    }

}
