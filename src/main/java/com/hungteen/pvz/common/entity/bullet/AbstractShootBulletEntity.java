package com.hungteen.pvz.common.entity.bullet;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public abstract class AbstractShootBulletEntity extends AbstractBulletEntity {

	public AbstractShootBulletEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}
	
	public AbstractShootBulletEntity(EntityType<?> type, World worldIn, LivingEntity livingEntityIn) {
		super(type, worldIn, livingEntityIn);
	}


}
