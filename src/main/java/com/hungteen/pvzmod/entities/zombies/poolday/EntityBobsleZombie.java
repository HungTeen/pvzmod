package com.hungteen.pvzmod.entities.zombies.poolday;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.world.World;

public class EntityBobsleZombie extends EntityZombieBase{

	public EntityBobsleZombie(World worldIn) {
		super(worldIn);
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.BOBSLE_ZOMBIE;
	}

	@Override
	public float getLife() {
		return 20;
	}

}
