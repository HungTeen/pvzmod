package com.hungteen.pvz.utils.interfaces;

import com.hungteen.pvz.common.entity.ai.goal.misc.PlantAttractGoal;

import net.minecraft.entity.LivingEntity;

/**
 * {@link PlantAttractGoal}
 */
public interface ICanAttract {

	/**
	 * check can attract specific target.
	 * 
	 */
    boolean canAttract(LivingEntity target);
	
	/**
	 * attract the attacker's target
	 */
	void attract(LivingEntity target);
	
	/**
	 * how far can it attract.
	 */
	float getAttractRange();
	
}
