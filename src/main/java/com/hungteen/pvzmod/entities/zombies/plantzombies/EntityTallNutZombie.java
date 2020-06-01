package com.hungteen.pvzmod.entities.zombies.plantzombies;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.EntityUtil;

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
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.SLOW_WALK);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(280.0D);
    }
	
}
