package com.hungteen.pvz.api.interfaces;

import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.enums.Zombies;

public interface IPVZZombie extends IHasOwner, IGroupEntity, ICanCharm {
    
	float getLife();
	
	Zombies getZombieEnumName();
	
	Ranks getZombieRank();
	
	int getZombieXp();
	
	int getAttackCD();
}
