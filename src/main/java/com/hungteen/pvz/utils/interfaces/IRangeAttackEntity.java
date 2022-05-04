package com.hungteen.pvz.utils.interfaces;

import net.minecraft.world.entity.Entity;

import javax.annotation.Nonnull;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-29 17:19
 **/
public interface IRangeAttackEntity {

    /**
     * can not attack because of itself.
     */
    boolean canAttack();

    /**
     * set attack tick for animation.
     */
    void setAttackTick(int tick);

    /**
     * get attack tick for animation.
     */
    int getAttackTick();

    /**
     * real cool down each attack.(after affected by effect etc.)
     */
    double getCurrentAttackCD();

    /**
     * perform attack to entity.
     */
    void startAttack(@Nonnull Entity target);

    int getStartAttackTick();

}
