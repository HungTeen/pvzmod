package com.hungteen.pvz.api.interfaces;

import net.minecraft.entity.LivingEntity;

/**
 * implement this to make entity collectible by Collector
 */
public interface ICollectible {

	boolean canCollectBy(LivingEntity living);
	
	void onCollect(LivingEntity living);
	
}
