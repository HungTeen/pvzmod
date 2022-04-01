package com.hungteen.pvz.utils.interfaces;

import com.hungteen.pvz.common.tag.PVZEntityTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-01 17:39
 **/
public interface ICanAttract {

    /**
     * check can attract specific target.
     */
    default boolean canAttract(LivingEntity target){
        if(target instanceof ICanBeAttracted && ! ((ICanBeAttracted) target).canBeAttractedBy(this)) {
            return false;
        }
        return ! target.getType().is(PVZEntityTags.IGNORE_ATTRACTS);
    }

    /**
     * attract the attacker's target
     */
    default void attract(LivingEntity target){
        if(target instanceof Mob && this instanceof LivingEntity){
            ((Mob) target).setTarget(((LivingEntity) this));
        }
        if(target instanceof ICanBeAttracted){
            ((ICanBeAttracted) target).attractBy(this);
        }
    }

    /**
     * range.
     */
    double getAttractRange();

}
