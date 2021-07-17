package com.hungteen.pvz.api.interfaces;

import javax.annotation.Nullable;

import net.minecraft.entity.LivingEntity;

/**
 * use to check can entity be charmed.
 */
public interface ICanBeCharmed {

	boolean isCharmed();
	
	void onCharmedBy(@Nullable LivingEntity entity);
}
