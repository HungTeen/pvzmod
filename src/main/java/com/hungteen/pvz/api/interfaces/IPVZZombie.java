package com.hungteen.pvz.api.interfaces;

import com.hungteen.pvz.common.core.CardRank;
import com.hungteen.pvz.remove.Zombies;
import com.hungteen.pvz.utils.EntityUtil;

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
	
	CardRank getZombieRank();
	
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
