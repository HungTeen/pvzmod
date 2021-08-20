package com.hungteen.pvz.remove;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hungteen.pvz.common.world.invasion.OverworldInvasion;
import com.hungteen.pvz.utils.others.WeightList;

import net.minecraft.util.text.TranslationTextComponent;

public enum Zombies {
	// 1
	NORMAL_ZOMBIE(0, 10, 5), 
	FLAG_ZOMBIE(0, 0, 0), 
	CONEHEAD_ZOMBIE(2, 5, 5), 
	POLE_ZOMBIE(5, 4, 4), 
	BUCKETHEAD_ZOMBIE(8, 3, 3),
	// 2
	TOMB_STONE(0, 0, 0), 
	NEWSPAPER_ZOMBIE(2, 7, 5), 
	OLD_ZOMBIE(5, 5, 4), 
	SUNDAY_EDITION_ZOMBIE(24, 2, 1), 
	SCREENDOOR_ZOMBIE(3, 5, 4), 
	FOOTBALL_ZOMBIE(9, 3, 2),
	GIGA_FOOTBALL_ZOMBIE(20, 2, 2), 
	DANCING_ZOMBIE(12, 1, 2), 
	BACKUP_DANCER(0, 0, 0),
	// 3
	SNORKEL_ZOMBIE(1, 4, 4), 
	ZOMBONI(6, 4, 3), 
	BOBSLE_TEAM(7, 0, 1), 
	BOBSLE_ZOMBIE(0, 0, 0), 
	ZOMBIE_DOLPHIN(0, 0, 0), 
	DOLPHIN_RIDER(10, 0, 0), 
	DOLPHIN_RIDER_ZOMBIE(0, 0, 0),
	LAVA_ZOMBIE(16, 2, 2),
	// 4
	JACK_IN_BOX_ZOMBIE(6, 4, 3),
	BALLOON_ZOMBIE(10, 4, 3), 
	DIGGER_ZOMBIE(15, 3, 3), 
	POGO_ZOMBIE(3, 3, 3), 
	YETI_ZOMBIE(0, 0, 0),
	// 5
	BUNGEE_ZOMBIE(6, 3, 2),
	LADDER_ZOMBIE(3, 4, 3),
	CATAPULT_ZOMBIE(12, 4, 3),
	GARGANTUAR(20, 2, 2), 
	IMP(0, 5, 2), 
	SAD_GARGANTUAR(30, 1, 1),
	ZOMBOSS(0, 0, 0),
	// plant_zombie
	PEASHOOTER_ZOMBIE(0, 4, 2), 
	WALLNUT_ZOMBIE(0, 3, 2), 
	GATLINGPEA_ZOMBIE(0, 2, 2), 
	TALLNUT_ZOMBIE(0, 2, 2), 
	SQUASH_ZOMBIE(0, 2, 2), 
	JALAPENO_ZOMBIE(0, 2, 2), 
	//other
	PUMPKIN_ZOMBIE(6, 4, 1),
	TRICK_ZOMBIE(1, 6, 1), 
	COFFIN(0, 0, 0), 
	MOURNER_ZOMBIE(0, 0, 0), 
	NOBLE_ZOMBIE(0, 0, 0), 
	RA_ZOMBIE(0, 5, 0),
	GIGA_TOMB_STONE(0, 0, 0);

	public static final WeightList<Zombies> ZOMBIE_SPAWN_LIST = new WeightList<>();
	public static final List<Zombies> DEFAULT_ZOMBIES = Arrays.asList(
			Zombies.NORMAL_ZOMBIE, Zombies.CONEHEAD_ZOMBIE, Zombies.BUCKETHEAD_ZOMBIE,
			Zombies.SCREENDOOR_ZOMBIE, Zombies.NEWSPAPER_ZOMBIE
			);
	public static final Set<Zombies> BOSSES = new HashSet<>(Arrays.asList(
			ZOMBOSS, COFFIN, NOBLE_ZOMBIE
			));
	public final int difficulty;
	public final int spawnWeight;// wave spawn
	public final int chooseWeight;//random invasion choose
	
	static {
		//init random spawn weight list.
		int sum = 0;
		for(Zombies zombie : Zombies.values()) {
			if(zombie.chooseWeight > 0) {
				ZOMBIE_SPAWN_LIST.addItem(zombie, zombie.chooseWeight);
				sum += zombie.chooseWeight;
			}
		}
		ZOMBIE_SPAWN_LIST.setTotal(sum);
	}
	
	private Zombies(int day, int weight, int choose) {
		this.difficulty =  OverworldInvasion.DIFFICULTY_INC * day;
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
	
	public TranslationTextComponent getText() {
		return new TranslationTextComponent("entity.pvz." + this.toString().toLowerCase());
	}
	
}
