package com.hungteen.pvzmod.entities.zombies.poolnight;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.ZombieUtil;
import com.hungteen.pvzmod.util.enums.Zombies;

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
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.LITTLE_SLOW);
	}
	
	@Override
	public float getLife() {
		return 140;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.YETI_ZOMBIE;
	}
}
