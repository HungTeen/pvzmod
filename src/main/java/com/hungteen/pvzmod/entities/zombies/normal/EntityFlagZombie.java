package com.hungteen.pvzmod.entities.zombies.normal;

import com.hungteen.pvzmod.util.EntityUtil;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityFlagZombie extends EntityNormalZombie{

	public EntityFlagZombie(World worldIn) {
		super(worldIn);
	}

	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(19.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.FLAG_SPEED);
    }
}
