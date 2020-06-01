package com.hungteen.pvzmod.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;

import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityImp extends EntityZombieBase{

	public EntityImp(World worldIn) {
		super(worldIn);
		this.setSize(0.5f, 1f);
	}

	@Override
	public boolean getIsInvulnerable() {
		return this.isRiding();
	}
	
}
