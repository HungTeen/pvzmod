package com.hungteen.pvzmod.entities.zombies.plantzombies;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityNutWallZombie extends EntityZombieBase{

	public EntityNutWallZombie(World world) {
		super(world);
		this.setSize(0.8f, 2.4f);
	}
	
	@Override
	protected void setSmallSize() {
		this.setSize(0.4f, 0.65f);
	}
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(130.0D);
    }
}
