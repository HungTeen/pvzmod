package com.hungteen.pvz.entity.zombie.part;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;

import net.minecraft.entity.EntityType;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class NewspaperPartEntity extends PVZZombiePartEntity{

	public NewspaperPartEntity(EntityType<?> entityTypeIn, World worldIn) {
		super(entityTypeIn, worldIn);
	}
	
	public NewspaperPartEntity(EntityType<?> entityTypeIn, PVZZombieEntity owner, float sizeX, float sizeY) {
		super(entityTypeIn, owner, sizeX, sizeY);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if(source instanceof PVZDamageSource) {
			((PVZDamageSource) source).setDefended(true);
		}
		return super.attackEntityFrom(source, damage / 2);
	}

}
