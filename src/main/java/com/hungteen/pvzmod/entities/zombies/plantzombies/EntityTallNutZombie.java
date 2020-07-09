package com.hungteen.pvzmod.entities.zombies.plantzombies;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityTallNutZombie extends EntityZombieBase{

	public EntityTallNutZombie(World worldIn) {
		super(worldIn);
		this.setSize(1f, 3f);
	}

	@Override
	protected void setSmallSize() {
		this.setSize(0.4f, 1f);
	}
	
	@Override
	public float getLife() {
		return 280;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.TALLNUT_ZOMBIE;
	}
	
}
