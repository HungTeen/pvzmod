package com.hungteen.pvz.common.entity.bullet;

import com.hungteen.pvz.common.entity.plant.arma.KernelPultEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class ButterEntity extends PultBulletEntity {

	public ButterEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}
	
	public ButterEntity(World worldIn, LivingEntity shooter) {
		super(EntityRegister.BUTTER.get(), worldIn, shooter);
	}

	protected void dealDamage(Entity target) {
		PVZDamageSource source = PVZDamageSource.butter(this, this.getThrower());
		if(this.getThrower() instanceof KernelPultEntity) {
			source.addEffect(((KernelPultEntity) this.getThrower()).getButterEffect());
		}
		target.hurt(source, this.attackDamage);
	}
	
	protected float getAttackDamage() {
		if(this.getThrower() instanceof KernelPultEntity) return ((KernelPultEntity) this.getThrower()).getButterDamage();
		return 0;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.6F, 0.6F);
	}
	
}
