package com.hungteen.pvz.entity.bullet;

import com.hungteen.pvz.entity.plant.arma.KernelPultEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;

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
	
	public KernelEntity(EntityType<?> type, World worldIn, LivingEntity shooter) {
		super(type, worldIn, shooter);
	}

	protected void dealDamage(Entity target) {
		target.attackEntityFrom(PVZDamageSource.causeThrowDamage(this, this.getThrower()), this.getKernelDamage());
	}
	
	private float getKernelDamage() {
		float damage = 0;
		if(this.getThrower() instanceof KernelPultEntity) {
			damage += ((KernelPultEntity) this.getThrower()).getKernelDamage();
		}
		return damage;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.4F, 0.4F);
	}
	
}
