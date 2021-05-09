package com.hungteen.pvz.entity.bullet;

import com.hungteen.pvz.entity.plant.arma.KernelPultEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;

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
		target.hurt(PVZDamageSource.causeThrowDamage(this, this.getThrower()), this.attackDamage);
	}
	
	@Override
	protected float getAttackDamage() {
		if(this.getThrower() instanceof KernelPultEntity) return ((KernelPultEntity) this.getThrower()).getKernelDamage();
		return 0;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.4F, 0.4F);
	}
	
}
