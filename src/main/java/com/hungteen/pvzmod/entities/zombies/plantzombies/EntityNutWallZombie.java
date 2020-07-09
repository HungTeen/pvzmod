package com.hungteen.pvzmod.entities.zombies.plantzombies;

import com.hungteen.pvzmod.entities.zombies.grassday.EntityNormalZombie;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.world.World;

public class EntityNutWallZombie extends EntityNormalZombie{

	public EntityNutWallZombie(World world) {
		super(world);
		this.setSize(0.8f, 2.4f);
	}
	
	@Override
	protected void setSmallSize() {
		this.setSize(0.4f, 0.65f);
	}
	
	@Override
	public float getLife() {
		return 130;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.NUTWALL_ZOMBIE;
	}
}
