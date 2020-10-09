package com.hungteen.pvz.entity.zombie;

import com.hungteen.pvz.entity.PVZMultiPartEntity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public abstract class PVZZombiePartEntity extends PVZMultiPartEntity{

	public PVZZombiePartEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}

	public PVZZombiePartEntity(EntityType<?> entityTypeIn, LivingEntity owner, float sizeX, float sizeY) {
		super(entityTypeIn, owner, sizeX, sizeY);
	}
	
}
