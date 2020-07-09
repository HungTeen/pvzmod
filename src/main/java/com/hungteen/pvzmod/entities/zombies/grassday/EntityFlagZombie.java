package com.hungteen.pvzmod.entities.zombies.grassday;

import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.ZombieUtil;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityFlagZombie extends EntityNormalZombie{

	public EntityFlagZombie(World worldIn) {
		super(worldIn);
	}

	@Override
	public float getLife() {
		return 19;
	}
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.LITTLE_FAST);
    }
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.FLAG_ZOMBIE;
	}
}
