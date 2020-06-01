package com.hungteen.pvzmod.entities.bullets;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.plants.base.EntityPultBase;
import com.hungteen.pvzmod.entities.plants.common.EntityKernelPult;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityButter extends EntityPult{

	public EntityButter(World worldIn) {
		super(worldIn);
		setSize(0.7f, 0.7f);
	}
	
	public EntityButter(World worldIn, EntityLivingBase throwerIn,Type type)
    {
        super(worldIn, throwerIn, type);
        setSize(0.7f, 0.7f);
    }

	@Override
	protected float getAttackDamage() {
		float damage=0;
		if(this.shooter instanceof EntityKernelPult) 
			damage=((EntityKernelPult)this.shooter).getAttackDamage();
		return damage;
	}

	protected void performAttack(Entity target)
	{
		target.attackEntityFrom(PVZDamageSource.causeButterDamage(this, this.getThrower()), this.getAttackDamage());//damage
	}
	
	@Override
	protected EntityPult getPultBullet(EntityPultBase pult) {
		return new EntityButter(world, pult, Type.DOWN);
	}

}
