package com.hungteen.pvz.utils;

import java.util.HashMap;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.enums.Zombies;

public class ZombieUtil {

	public static final HashMap<Zombies, Ranks> ZOMBIE_RANK = new HashMap<>();
	
	// zombies speed
	public static final float VERY_SLOW = 0.16f; 
	public static final float SLOW = 0.18f;
	public static final float LITTLE_SLOW = 0.19f; 
	public static final float NORMAL_SPEED = 0.20f; 
	public static final float LITTLE_FAST = 0.22f;
	public static final float FAST = 0.24f; 
	public static final float VERY_FAST = 0.27f; 
	public static final float HUGE_FAST = 0.28f; 
	public static final float WATER_FAST = 0.8f;

	// zombies attackDamage
	public static final float VERY_LOW = 4; 
	public static final float LOW = 6;
	public static final float LITTLE_LOW = 8;
	public static final float NORMAL_DAMAGE = 10; 
	public static final float LITTLE_HIGH = 20; 
	public static final float HIGH = 30; 
	public static final float VERY_HIGH = 50;
	public static final float HUGE_HIGH = 100; 
	public static final float GIANT_HIT = 1000; 
	public static final float BOSS_HIT = 2000; 

	// follow range
	public static final float ZOMBIE_FOLLOW_RANGE = 100;
	
	static{
		//init zombie - > rank 
		//gray
		ZOMBIE_RANK.put(Zombies.ZOMBIE_DOLPHIN, Ranks.GRAY);
		ZOMBIE_RANK.put(Zombies.TOMB_STONE, Ranks.GRAY);
		//white
		ZOMBIE_RANK.put(Zombies.NORMAL_ZOMBIE, Ranks.WHITE);
		ZOMBIE_RANK.put(Zombies.FLAG_ZOMBIE, Ranks.WHITE);
		ZOMBIE_RANK.put(Zombies.SNORKEL_ZOMBIE, Ranks.WHITE);
		ZOMBIE_RANK.put(Zombies.BOBSLE_ZOMBIE, Ranks.WHITE);
		ZOMBIE_RANK.put(Zombies.DOLPHIN_RIDER_ZOMBIE, Ranks.WHITE);
		ZOMBIE_RANK.put(Zombies.NEWSPAPER_ZOMBIE, Ranks.WHITE);
		//green
		ZOMBIE_RANK.put(Zombies.CONEHEAD_ZOMBIE, Ranks.GREEN);
		ZOMBIE_RANK.put(Zombies.POLE_ZOMBIE, Ranks.GREEN);
		//blue
		ZOMBIE_RANK.put(Zombies.BUCKETHEAD_ZOMBIE, Ranks.BLUE);
		ZOMBIE_RANK.put(Zombies.ZOMBONI, Ranks.BLUE);
		ZOMBIE_RANK.put(Zombies.BOBSLE_TEAM, Ranks.BLUE);
		ZOMBIE_RANK.put(Zombies.DOLPHIN_RIDER, Ranks.BLUE);
		//purple
		ZOMBIE_RANK.put(Zombies.LAVA_ZOMBIE, Ranks.PURPLE);
		//gold
		//boss
	}

	public static Ranks getZombieRank(Zombies zombie){
		if(ZOMBIE_RANK.containsKey(zombie)) {
			return ZOMBIE_RANK.get(zombie);
		}
		PVZMod.LOGGER.debug("Zombie get rank error");
		return null;
	}
	
	public static int getZombieXp(Zombies zombie){
		Ranks rank = getZombieRank(zombie);
		switch(rank) {
		case GRAY:return 1;
		case WHITE:return 2;
		case GREEN:return 3;
		case BLUE:return 5;
		case PURPLE:return 8;
		case GOLD:return 15;
		case MEGA:return 50;
		default:return 0;
		}
	}
	
}
