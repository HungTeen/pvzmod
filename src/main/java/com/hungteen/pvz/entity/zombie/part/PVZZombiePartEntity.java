package com.hungteen.pvz.entity.zombie.part;

import com.hungteen.pvz.entity.PVZMultiPartEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.register.EntityRegister;

import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class PVZZombiePartEntity extends PVZMultiPartEntity{

	protected PVZZombieEntity zombie;
	
	public PVZZombiePartEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}
	
	public PVZZombiePartEntity(PVZZombieEntity owner, float sizeX, float sizeY) {
		super(EntityRegister.ZOMBIE_PART.get(), owner, sizeX, sizeY);
		this.zombie = owner;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if(zombie != null) {
			return zombie.attackEntityFrom(source, damage);
		}
		return false;
	}
	
	@Override
	public boolean shouldNotExist() {
		return this.zombie == null || !this.zombie.isAlive();
	}
	
}
