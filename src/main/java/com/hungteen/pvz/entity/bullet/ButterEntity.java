package com.hungteen.pvz.entity.bullet;

import com.hungteen.pvz.entity.plant.arma.KernelPultEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;

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
	
	public ButterEntity(EntityType<?> type, World worldIn, LivingEntity shooter) {
		super(type, worldIn, shooter);
	}

	protected void dealDamage(Entity target) {
		PVZDamageSource source = PVZDamageSource.causeButterDamage(this, this.getThrower());
		if(this.getThrower() instanceof KernelPultEntity) {
			source.addEffect(((KernelPultEntity) this.getThrower()).getButterEffect());
		}
		target.attackEntityFrom(source, this.getButterDamage());
	}
	
	private float getButterDamage() {
		float damage = 0;
		if(this.getThrower() instanceof KernelPultEntity) {
			damage += ((KernelPultEntity) this.getThrower()).getButterDamage();
		}
		return damage;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.6F, 0.6F);
	}
	
}
