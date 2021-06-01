package com.hungteen.pvz.api.interfaces;

import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.enums.Zombies;

public interface IPVZZombie {
    
	float getLife();
	
	/**
	 * use to check target of zombies
	 */
	boolean canAttackSpike();
	
	Zombies getZombieEnumName();
	
	Ranks getZombieRank();
	
	int getZombieXp();
	
	int getAttackCD();
}
