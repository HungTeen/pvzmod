package com.hungteen.pvzmod.util;

import com.hungteen.pvzmod.entities.plants.fight.EntitySpikeRock;
import com.hungteen.pvzmod.entities.zombies.EntityGargantuar;
import com.hungteen.pvzmod.entities.zombies.EntityZomBoss;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;

import net.minecraft.entity.EntityLivingBase;

public class ZombieUtil {

	public static boolean checkCanZombieTarget(EntityZombieBase zombie,EntityLivingBase target)
	{
		if(target instanceof EntitySpikeRock) {
		    if(zombie instanceof EntityGargantuar) return true;
		    if(zombie instanceof EntityZomBoss) return true;
		    return false;
		}
		return true;
	}
}
