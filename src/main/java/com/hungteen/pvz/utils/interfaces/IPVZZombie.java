package com.hungteen.pvz.utils.interfaces;

import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.enums.Zombies;

public interface IPVZZombie {
    
	float getLife();
	
	Zombies getZombieEnumName();
	
	Ranks getZombieRank();
	
	int getZombieXp();
	
	int getAttackCD();
}
