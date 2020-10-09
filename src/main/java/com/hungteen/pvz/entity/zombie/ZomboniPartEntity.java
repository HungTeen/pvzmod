package com.hungteen.pvz.entity.zombie;

import com.hungteen.pvz.entity.PVZMultiPartEntity;
import com.hungteen.pvz.entity.zombie.poolday.ZomboniEntity;
import com.hungteen.pvz.register.EntityRegister;

import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ZomboniPartEntity extends PVZMultiPartEntity{

	private ZomboniEntity zomboni;
	
	public ZomboniPartEntity(EntityType<?> entityTypeIn, World world) {
		super(entityTypeIn, world);
	}
	
	public ZomboniPartEntity(ZomboniEntity owner, float sizeX, float sizeY) {
		super(EntityRegister.ZOMBONI_PART.get(), owner, sizeX, sizeY);
		this.zomboni = owner;
	}
	
	@Override
	public void tick() {
		super.tick();
		if(this.zomboni != null) {
			this.setPosition(this.zomboni.getPosX(), this.zomboni.getPosY() + 2, this.zomboni.getPosZ());
		}
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if(zomboni != null) {
			return zomboni.attackEntityFrom(source, damage);
		}
		return false;
	}
	
	@Override
	public boolean shouldNotExist() {
		return this.zomboni == null || !this.zomboni.isAlive();
	}

}
