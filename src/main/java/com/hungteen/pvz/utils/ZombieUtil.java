package com.hungteen.pvz.utils;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.enums.Zombies;

public class ZombieUtil {

	// zombies speed
	public static final float VERY_SLOW = 0.16f; 
	public static final float SLOW = 0.18f;
	public static final float LITTLE_SLOW = 0.19f; 
	public static final float NORMAL_SPEED = 0.20f; 
	public static final float LITTLE_FAST = 0.22f;
	public static final float FAST = 0.24f; 
	public static final float VERY_FAST = 0.27f; 
	public static final float HUGE_FAST = 0.28f; 

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

	public static Ranks getZombieRank(Zombies zombie){
		switch(zombie) {
		case ZOMBIE_DOLPHIN:return Ranks.GRAY;
		case NORMAL_ZOMBIE:
		case FLAG_ZOMBIE:
		case SNORKEL_ZOMBIE:
		case BOBSLE_ZOMBIE:return Ranks.WHITE;
		case CONEHEAD_ZOMBIE:
		case POLE_ZOMBIE:
		case BUCKETHEAD_ZOMBIE:
		case ZOMBONI:
		case BOBSLE_TEAM:return Ranks.BLUE;
		default:{
			PVZMod.LOGGER.debug("Zombie get rank error");
			return null;
		}
		}
	}
	
	public static int getZombieXp(Zombies zombie){
		Ranks rank=getZombieRank(zombie);
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
