package com.hungteen.pvz.api.interfaces;

import net.minecraft.world.entity.LivingEntity;

/**
 * implement this to make entity collectible by Collector
 */
public interface ICollectible {

    /**
     * whether it can be collect or not.
     */
    boolean canCollectBy(LivingEntity living);

    /**
     * run when it's collected by living.
     */
    void onCollect(LivingEntity living);

    /**
     * how much does it have.
     */
    int getAmount();

}
