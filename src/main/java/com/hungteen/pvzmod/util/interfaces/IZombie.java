package com.hungteen.pvzmod.util.interfaces;

import com.hungteen.pvzmod.util.enums.Ranks;
import com.hungteen.pvzmod.util.enums.Zombies;

public interface IZombie {

	Zombies getZombieEnumName();
	
	float getLife();
	
	Ranks getZombieRank();
	
	int getZombieXp();
}
