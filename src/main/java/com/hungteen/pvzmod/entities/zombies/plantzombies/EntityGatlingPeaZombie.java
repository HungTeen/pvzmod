package com.hungteen.pvzmod.entities.zombies.plantzombies;

import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.world.World;

public class EntityGatlingPeaZombie extends EntityPeaShooterZombie{

	public EntityGatlingPeaZombie(World worldIn) {
		super(worldIn);
	}

	@Override
	protected int getShootNum() {
		return 4;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.GATLINGPEA_ZOMBIE;
	}
}
