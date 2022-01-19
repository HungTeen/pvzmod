package com.hungteen.pvz.common.entity.zombie.part;

import java.util.Optional;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.entity.PVZMultiPartEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.EntityRegister;
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

	/**
	 * when owner is mini zombie, then the part need shrink size.
	 */
	public void onOwnerBeMini(PVZZombieEntity zombie) {
		float scale = zombie.isMiniZombie() ? 0.4F : 1F;
		this.setPartWidth(this.MaxWidth * scale);
	    this.setPartHeight(this.MaxHeight * scale);
	}
	
	@Override
	public boolean hurt(DamageSource source, float damage) {
		if(EntityUtil.isEntityValid(getOwner())) {
			return getOwner().hurt(source, damage);
		}
		return false;
	}
	
	public Optional<PVZZombieEntity> getZombie() {
		LivingEntity owner = this.getOwner();
		if(! (owner instanceof PVZZombieEntity)) {
			PVZMod.LOGGER.warn("Wrong Owner Entity for Zombie's Part !");
			return null;
		}
		return Optional.ofNullable((PVZZombieEntity) owner);
	}
	
}
