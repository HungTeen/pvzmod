package com.hungteen.pvzmod.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.EntityUtil;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityYetiZombie extends EntityZombieBase{

	public EntityYetiZombie(World worldIn) {
		super(worldIn);
		this.setSize(1.5f, 3f);
	}
	
	@Override
	protected void setSmallSize() {
		this.setSize(0.7f, 1f);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(135);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.SLOW_WALK);
	}
}
