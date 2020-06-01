package com.hungteen.pvzmod.entities.bullets;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.plants.base.EntityPultBase;
import com.hungteen.pvzmod.entities.plants.common.EntityKernelPult;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EntityKernel extends EntityPult{

	public EntityKernel(World worldIn) {
		super(worldIn);
		setSize(0.5f, 0.5f);
	}

	public EntityKernel(World world, EntityPultBase pult, Type down) {
		super(world,pult,down);
		setSize(0.5f, 0.5f);
	}
	
	@Override
	protected float getAttackDamage() {
		float damage=0;
		if(this.shooter instanceof EntityKernelPult) 
			damage=((EntityKernelPult)this.shooter).getAttackDamage();
		return damage;
	}

	@Override
	protected EntityPult getPultBullet(EntityPultBase pult) {
		return new EntityKernel(world,pult,Type.DOWN);
	}

}
