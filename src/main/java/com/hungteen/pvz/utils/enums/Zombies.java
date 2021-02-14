package com.hungteen.pvz.utils.enums;

public enum Zombies {
	// 1
	NORMAL_ZOMBIE(10, 5), 
	FLAG_ZOMBIE(0, 0), 
	CONEHEAD_ZOMBIE(5, 5), 
	POLE_ZOMBIE(4, 4), 
	BUCKETHEAD_ZOMBIE(3, 3),
	// 2
	TOMB_STONE(0, 0), 
	NEWSPAPER_ZOMBIE(7, 5), 
	OLD_ZOMBIE(5, 4), 
	SUNDAY_EDITION_ZOMBIE(2, 1), 
	SCREENDOOR_ZOMBIE(5, 4), 
	FOOTBALL_ZOMBIE(3, 2),
	GIGA_FOOTBALL_ZOMBIE(2, 2), 
	DANCING_ZOMBIE(1, 2), 
	BACKUP_DANCER(0, 0),
	// 3
	SNORKEL_ZOMBIE(4, 4), 
	ZOMBONI(4, 3), 
	BOBSLE_TEAM(0, 1), 
	BOBSLE_ZOMBIE(0, 0), 
	ZOMBIE_DOLPHIN(0, 0), 
	DOLPHIN_RIDER(0, 0), 
	DOLPHIN_RIDER_ZOMBIE(0, 0),
	LAVA_ZOMBIE(2, 2),
	// 4
	JACK_IN_BOX_ZOMBIE(4, 3), 
	BALLOON_ZOMBIE(4, 3), 
	DIGGER_ZOMBIE(3, 3), 
	POGO_ZOMBIE(3, 3), 
	YETI_ZOMBIE(0, 0),
	// 5
	BUNGEE_ZOMBIE(3, 2),
	LADDER_ZOMBIE(4, 3),
	CATAPULT_ZOMBIE(4, 3),
	GARGANTUAR(2, 2), 
	IMP(5, 2), 
	SAD_GARGANTUAR(1, 1),
	ZOMBOSS(0, 0),
	// plant_zombie
	PEASHOOTER_ZOMBIE(4, 2), 
	WALLNUT_ZOMBIE(3, 2), 
	GATLINGPEA_ZOMBIE(2, 2), 
	TALLNUT_ZOMBIE(2, 2), 
	SQUASH_ZOMBIE(2, 2), 
	JALAPENO_ZOMBIE(2, 2), 
	//other
	PUMPKIN_ZOMBIE(4, 1),
	TRICK_ZOMBIE(6, 1), 
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
