package com.hungteen.pvz.common.entity.bullet;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public abstract class AbstractShootBulletEntity extends AbstractBulletEntity {

	public AbstractShootBulletEntity(EntityType<?> type, Level worldIn) {
		super(type, worldIn);
	}
	
	public AbstractShootBulletEntity(EntityType<?> type, Level worldIn, LivingEntity livingEntityIn) {
		super(type, worldIn, livingEntityIn);
	}


}
