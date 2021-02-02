package com.hungteen.pvz.utils.interfaces;

import net.minecraft.entity.LivingEntity;

public interface ICloser {

	float getCloseWidth();
	
	float getCloseHeight();
	
	/**
	 * perform attack when target is close
	 */
	void performAttack(LivingEntity target);
	
}
