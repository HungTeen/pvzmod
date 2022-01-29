package com.hungteen.pvz.common.entity.bullet;

import com.hungteen.pvz.common.entity.plant.arma.KernelPultEntity;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.entity.EntityRegister;

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
		PVZEntityDamageSource source = PVZEntityDamageSource.butter(this, this.getThrower());
		if(this.getThrower() instanceof KernelPultEntity) {
			source.addEffect(((KernelPultEntity) this.getThrower()).getButterEffect());
		}
		target.hurt(source, this.attackDamage);
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.6F, 0.6F);
	}
	
}
