package com.hungteen.pvzmod.entities.zombies.day;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.EntityUtil;

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
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.POLE_SPEED);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50f);
	}
}
