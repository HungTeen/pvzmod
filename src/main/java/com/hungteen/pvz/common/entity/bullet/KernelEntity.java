package com.hungteen.pvz.common.entity.bullet;

import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.entity.EntityRegister;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySize;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;

public class KernelEntity extends PultBulletEntity {

	public KernelEntity(EntityType<?> type, Level worldIn) {
		super(type, worldIn);
	}
	
	public KernelEntity(Level worldIn, LivingEntity shooter) {
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
