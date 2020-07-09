package com.hungteen.pvzmod.entities.zombies.roof;

import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.ZombieUtil;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntitySadGargantuar extends EntityGargantuar{

	public EntitySadGargantuar(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.BIT_SLOW);
	}
	
	@Override
	public float getLife() {
		return 600;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.SAD_GARGANTUAR;
	}
}
