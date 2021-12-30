package com.hungteen.pvz.common.entity.misc;

import com.hungteen.pvz.common.entity.effect.OriginEffectEntity;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class DoomFixerEntity extends OriginEffectEntity {

	public DoomFixerEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
		this.maxEffectTick = 40;
	}
	
}
