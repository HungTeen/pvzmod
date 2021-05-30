package com.hungteen.pvz.common.entity.bullet.itembullet;

import com.hungteen.pvz.common.entity.bullet.AbstractBulletEntity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public abstract class PVZItemBulletEntity extends AbstractBulletEntity implements IRendersAsItem {

	public PVZItemBulletEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}

	public PVZItemBulletEntity(EntityType<?> type, World worldIn, LivingEntity shooter) {
		super(type, worldIn, shooter);
	}
	
}
