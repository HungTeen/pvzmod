package com.hungteen.pvz.common.entity.bullet;

import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.entity.EntityRegister;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class KernelEntity extends PultBulletEntity {

	public KernelEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}
	
	public KernelEntity(World worldIn, LivingEntity shooter) {
		super(EntityRegister.KERNEL.get(), worldIn, shooter);
	}

	protected void dealDamage(Entity target) {
		target.hurt(PVZEntityDamageSource.kernel(this, this.getThrower()), this.getAttackDamage() / 2F);
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.4F, 0.4F);
	}
	
}
