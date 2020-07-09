package com.hungteen.pvzmod.util;

import com.hungteen.pvzmod.entities.plants.fight.EntitySpikeRock;
import com.hungteen.pvzmod.entities.zombies.EntityZomBoss;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.entities.zombies.roof.EntityGargantuar;
import com.hungteen.pvzmod.util.enums.Ranks;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.entity.EntityLivingBase;

public class ZombieUtil {

	// zombies speed
	public static final float SLOW = 0.16f; // 伽刚特尔 慢
	public static final float BIT_SLOW = 0.18f; // 车子 比较慢
	public static final float LITTLE_SLOW = 0.19f; // 报纸 略慢
	public static final float NORMAL_SPEED = 0.20f; // 一般
	public static final float LITTLE_FAST = 0.22f; // 略快
	public static final float BIT_FAST = 0.24f; // 较快
	public static final float FAST = 0.27f; // 快
	public static final float VERY_FAST = 0.28f; // 很快

	// zombies attackDamage
	public static final float LITTLE_EAT = 4;       //较低
	public static final float EAT = 6;              //一般
	public static final float BIT_EAT = 8;          //略高
	public static final float BEAT = 10;            //较高 
	public static final float ANGRY_BEAT = 20;      //高
	public static final float VERY_ANGRY_BEAT = 40; //很高
	public static final float GIANT_HIT = 1000;     //粉碎
	public static final float CAR_DESTROY = 2000;   //致命

	// follow range
	public static final float ZOMBIE_FOLLOW_RANGE = 100;

	public static boolean checkCanZombieTarget(EntityZombieBase zombie, EntityLivingBase target) {
		if (target instanceof EntitySpikeRock) {
			if (zombie instanceof EntityGargantuar)
				return true;
			if (zombie instanceof EntityZomBoss)
				return true;
			return false;
		}
		return true;
	}
	
	public static Ranks getZombieRank(Zombies zombie)
	{
		switch(zombie)
		{
		case TOMB_STONE:
		case IMP:
		case TOOL:return Ranks.GRAY;
		case NORMAL_ZOMBIE:
		case FLAG_ZOMBIE:
		case BACKUP_DANCER:
		case BOBSLE_ZOMBIE:return Ranks.WHITE;
		case CONEHEAD_ZOMBIE:
		case POLE_ZOMBIE:
		case PAPER_ZOMBIE:
		case SNORKEL_ZOMBIE:
		case JACK_IN_BOX_ZOMBIE:
		case BALLON_ZOMBIE:
		case POGO_ZOMBIE:return Ranks.GREEN;
		case BUCKETHEAD_ZOMBIE:
		case SCREENDOOR_ZOMBIE:
		case DANCING_ZOMBIE:
		case OLD_ZOMBIE:
		case ZOMBONI:
		case BOBSLE:
		case DIGGER_ZOMBIE:
		case YETI_ZOMBIE:
		case CATAPULT_ZOMBIE:return Ranks.BLUE;
		case FOOTBALL_ZOMBIE:
		case SUNDAY_EDITION_ZOMBIE:
		case DOLPHIN_RIDER:
		case GARGANTUAR:return Ranks.PURPLE;
		case GIGA_FOOTBALL_ZOMBIE:
		case LAVA_ZOMBIE:
		case SAD_GARGANTUAR:return Ranks.GOLD;
		case ZOMBOSS:return Ranks.MEGA;
		default :{
			System.out.println("no rank!");
			return null;
		}
		}
		
	}
	
	public static int getZombieXp(Zombies zombie)
	{
		Ranks rank = getZombieRank(zombie);
			switch(rank) {
			case GRAY:return 2;
			case WHITE:return 3;
			case GREEN:return 5;
			case BLUE:return 8;
			case PURPLE:return 15;
			case GOLD:return 30;
			default:{
				if(zombie==Zombies.ZOMBOSS) {
					return 1000;
				}
			}
			}
		return 0;
	}
}
