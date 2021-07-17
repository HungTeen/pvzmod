package com.hungteen.pvz.api.interfaces;

import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.enums.Zombies;

public interface IPVZZombie extends IHasOwner, IGroupEntity, ICanBeCharmed, ICanBeAttracted {
    
	float getLife();
	
	float getExtraLife();
	
	/**
	 * how many health does zombie has.
	 * {@link EntityUtil#getCurrentHealth(net.minecraft.entity.LivingEntity)}
	 */
	float getCurrentHealth();
	
	/**
	 * how many max health does zombie have currently.
	 * {@link EntityUtil#getCurrentMaxHealth(net.minecraft.entity.LivingEntity)}
	 */
	float getCurrentMaxHealth();
	
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
