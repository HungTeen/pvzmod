package com.hungteen.pvzmod.entities.zombies.grassday;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.ZombieUtil;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityPoleZombie extends EntityZombieBase{

	public EntityPoleZombie(World worldIn) {
		super(worldIn);
		this.setSize(0.8f, 2.2f);
	}

	@Override
	protected void setSmallSize() {
		this.setSize(0.3f, 0.5f);
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.BIT_FAST);
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.POLE_ZOMBIE;
	}

	@Override
	public float getLife() {
		return 50;
	}
}
