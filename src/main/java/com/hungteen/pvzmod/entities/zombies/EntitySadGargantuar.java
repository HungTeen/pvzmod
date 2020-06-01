package com.hungteen.pvzmod.entities.zombies;

import com.hungteen.pvzmod.util.EntityUtil;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntitySadGargantuar extends EntityGargantuar{

	public EntitySadGargantuar(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(600);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(EntityUtil.ZOMBIE_GIANT_DAMAGE);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.SLOW_WALK);
	}
}
