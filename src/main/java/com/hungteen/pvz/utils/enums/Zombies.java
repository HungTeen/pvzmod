package com.hungteen.pvz.utils.enums;

public enum Zombies {
	// 1
	NORMAL_ZOMBIE(10), 
	FLAG_ZOMBIE(0), 
	CONEHEAD_ZOMBIE(5), 
	POLE_ZOMBIE(4), 
	BUCKETHEAD_ZOMBIE(2),
	// 2
	TOMB_STONE(0), 
	NEWSPAPER_ZOMBIE(7), 
	OLD_ZOMBIE(4), 
	SUNDAY_EDITION_ZOMBIE(1), 
	SCREENDOOR_ZOMBIE(5), 
	FOOTBALL_ZOMBIE(2),
	GIGA_FOOTBALL_ZOMBIE(1), 
	DANCING_ZOMBIE(1), 
	BACKUP_DANCER(0),
	// 3
	SNORKEL_ZOMBIE(8), 
	ZOMBONI(4), 
	BOBSLE_TEAM(0), 
	BOBSLE_ZOMBIE(0), 
	ZOMBIE_DOLPHIN(0), 
	DOLPHIN_RIDER(0), 
	DOLPHIN_RIDER_ZOMBIE(0),
	LAVA_ZOMBIE(1),
	// 4
	JACK_IN_BOX_ZOMBIE(5), 
	BALLOON_ZOMBIE(4), 
	DIGGER_ZOMBIE(3), 
	POGO_ZOMBIE(3), 
	YETI_ZOMBIE(0),
	// 5
//	CATAPULT_ZOMBIE, GARGANTUAR, IMP, SAD_GARGANTUAR,
	// boss
//	ZOMBOSS,
	// plant_zombie
//	PEASHOOTER_ZOMBIE, NUTWALL_ZOMBIE, GATLINGPEA_ZOMBIE, TALLNUT_ZOMBIE, SQUASH_ZOMBIE, JALAPENO_ZOMBIE, 
	//other
	PUMPKIN_ZOMBIE(4),
	TRICK_ZOMBIE(8), 
	COFFIN(0), 
	MOURNER_ZOMBIE(0), 
	NOBLE_ZOMBIE(0), 
	RA_ZOMBIE(5);

	public final int spawnWeight;
	
	private Zombies(int weight) {
		this.spawnWeight = weight;
	}
	
	public static Zombies getZombieByName(String name) {
		for (Zombies zombie : Zombies.values()) {
			if (name.equals(zombie.toString().toLowerCase())) {
				return zombie;
			}
		}
		return null;
	}
	
}
