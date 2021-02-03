package com.hungteen.pvz.entity.bullet;

import com.hungteen.pvz.entity.zombie.roof.CatapultZombieEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.world.World;

public class BallEntity extends PultBulletEntity {

	public BallEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}
	
	public BallEntity(World worldIn, LivingEntity living) {
		super(EntityRegister.BALL.get(), worldIn, living);
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.4F, 0.4F);
	}

	@Override
	protected void dealDamage(Entity target) {
		target.attackEntityFrom(PVZDamageSource.causeThrowDamage(this, this.getThrower()), this.attackDamage);
	}

	@Override
	protected float getAttackDamage() {
		if(this.getThrower() instanceof CatapultZombieEntity) return ((CatapultZombieEntity) this.getThrower()).getAttackDamage();
		return 2;
	}

}
