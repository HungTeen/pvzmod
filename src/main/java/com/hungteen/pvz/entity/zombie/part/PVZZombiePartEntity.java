package com.hungteen.pvz.entity.zombie.part;

import com.hungteen.pvz.entity.PVZMultiPartEntity;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class PVZZombiePartEntity extends PVZMultiPartEntity{

	public PVZZombiePartEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}
	
	public PVZZombiePartEntity(PVZZombieEntity owner, float sizeX, float sizeY) {
		super(EntityRegister.ZOMBIE_PART.get(), owner, sizeX, sizeY);
	}

	@Override
	public void tick() {
		super.tick();
		if(! world.isRemote && getZombie() != null) {
			float scale = (getZombie().isMiniZombie() ? 0.4F : 1F);
		    this.setPartWidth(this.MaxWidth * scale);
		    this.setPartHeight(this.MaxHeight * scale);
		}
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if(EntityUtil.isEntityValid(getOwner())) {
			return getOwner().attackEntityFrom(source, damage);
		}
		return false;
	}
	
	@Override
	public boolean shouldNotExist() {
		return ! EntityUtil.isEntityValid(getOwner());
	}
	
	public PVZZombieEntity getZombie() {
		LivingEntity owner = this.getOwner();
		if(owner == null) return null;
		if(! (owner instanceof PVZZombieEntity)) {
			System.out.println("Error : Wrong Owner Entity for Part !");
		}
		return (PVZZombieEntity) owner;
	}
	
}
