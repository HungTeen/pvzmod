package com.hungteen.pvz.utils.enums;

public enum Zombies {
	// 1
	NORMAL_ZOMBIE(10, 5), 
	FLAG_ZOMBIE(0, 0), 
	CONEHEAD_ZOMBIE(5, 5), 
	POLE_ZOMBIE(4, 4), 
	BUCKETHEAD_ZOMBIE(2, 2),
	// 2
	TOMB_STONE(0, 0), 
	NEWSPAPER_ZOMBIE(7, 5), 
	OLD_ZOMBIE(4, 3), 
	SUNDAY_EDITION_ZOMBIE(1, 1), 
	SCREENDOOR_ZOMBIE(5, 4), 
	FOOTBALL_ZOMBIE(2, 2),
	GIGA_FOOTBALL_ZOMBIE(1, 1), 
	DANCING_ZOMBIE(1, 2), 
	BACKUP_DANCER(0, 0),
	// 3
	SNORKEL_ZOMBIE(4, 5), 
	ZOMBONI(4, 3), 
	BOBSLE_TEAM(0, 0), 
	BOBSLE_ZOMBIE(0, 0), 
	ZOMBIE_DOLPHIN(0, 0), 
	DOLPHIN_RIDER(0, 0), 
	DOLPHIN_RIDER_ZOMBIE(0, 0),
	LAVA_ZOMBIE(1, 1),
	// 4
	JACK_IN_BOX_ZOMBIE(5, 3), 
	BALLOON_ZOMBIE(4, 3), 
	DIGGER_ZOMBIE(3, 2), 
	POGO_ZOMBIE(3, 3), 
	YETI_ZOMBIE(0, 0),
	// 5
//	CATAPULT_ZOMBIE, GARGANTUAR, IMP, SAD_GARGANTUAR,
	// boss
//	ZOMBOSS,
	// plant_zombie
//	PEASHOOTER_ZOMBIE, NUTWALL_ZOMBIE, GATLINGPEA_ZOMBIE, TALLNUT_ZOMBIE, SQUASH_ZOMBIE, JALAPENO_ZOMBIE, 
	//other
	PUMPKIN_ZOMBIE(4, 0),
	TRICK_ZOMBIE(8, 0), 
	COFFIN(0, 0), 
	MOURNER_ZOMBIE(0, 0), 
	NOBLE_ZOMBIE(0, 0), 
	RA_ZOMBIE(5, 0);

	public final int spawnWeight;// wave spawn
	public final int chooseWeight;//random invasion choose
	
	private Zombies(int weight, int choose) {
		this.spawnWeight = weight;
		this.chooseWeight = choose;
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
