package com.hungteen.pvzmod.entities.zombies.plantzombies;

import net.minecraft.world.World;

public class EntityGatlingPeaZombie extends EntityPeaShooterZombie{

	public EntityGatlingPeaZombie(World worldIn) {
		super(worldIn);
	}

	@Override
	protected int getShootNum() {
		return 4;
	}
}
