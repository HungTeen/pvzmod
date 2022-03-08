package com.hungteen.pvz.common.entity.bullet.itembullet;

import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.IRendersAsItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public abstract class PVZItemBulletEntity extends AbstractBulletEntity implements IRendersAsItem {

	public PVZItemBulletEntity(EntityType<?> type, Level worldIn) {
		super(type, worldIn);
	}

	public PVZItemBulletEntity(EntityType<?> type, Level worldIn, LivingEntity shooter) {
		super(type, worldIn, shooter);
	}
	
}
