package com.hungteen.pvz.api.interfaces;

import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.enums.Zombies;

public interface IPVZZombie extends IHasOwner, IGroupEntity, ICanCharm, ICanAttract {
    
	float getLife();
	
	Zombies getZombieEnumName();
	
	Ranks getZombieRank();
	
	int getZombieXp();
	
	int getAttackCD();
	
	boolean canBeButter();
	
	boolean canBeCharmed();
	
	boolean canBeFrozen();
	
	boolean canBeMini();
	
	boolean canBeInvis();
	
	boolean canBeCold();
	
	boolean canBeStealByBungee();
}
