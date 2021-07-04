package com.hungteen.pvz.utils.interfaces;

import net.minecraft.entity.LivingEntity;

public interface IDefender {

	/**
	 * for extra life
	 */
	float getSuperLife();

	float getAttractRange();
	
	boolean canAttract(LivingEntity target);
	
	/**
	 * attract the attacker's target
	 */
	void attract();
}
