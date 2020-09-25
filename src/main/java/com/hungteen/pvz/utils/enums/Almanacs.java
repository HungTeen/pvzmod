package com.hungteen.pvz.utils.enums;

import com.hungteen.pvz.utils.PlantUtil;

import net.minecraft.util.text.TranslationTextComponent;

public enum Almanacs {

	/**
	 * Plants
	 */
	// 1-1 ~ 1-10
	PEA_SHOOTER, SUN_FLOWER, CHERRY_BOMB, WALL_NUT, POTATO_MINE, SNOW_PEA, CHOMPER, REPEATER,
	// 2-1 ~ 2-10
//	PULF_SHROOM, SUN_SHROOM, FUME_SHROOM, GRAVE_VINE, HYPNO_SHROOM, SCAREDY_SHROOM, ICE_SHROOM, DOOM_SHROOM,
	// 3-1 ~ 3-10
	LILY_PAD, SQUASH, THREE_PEATER, TANGLE_KELP, JALAPENO, SPIKE_WEED, TORCH_WOOD, TALL_NUT,
	// 4-1 ~ 4-10
//	SEA_SHROOM, PLANTERN, CACTUS, BLOVER, SPLIT_PEA, STAR_FRUIT, PUMPKIN, MAGNET_SHROOM,
	// 5-1 5-10
//	CABBAGE_PULT, FLOWER_POT, KERNEL_PULT, COFFEE_BEAN, GARLIC, UMBRELLA_LEAF, MARIGOLD, MELON_PULT,
	// upgrate
//	GATLING_PEA, TWIN_SUNFLOWER, GLOOM_SHROOM, CAT_TAIL, WINTER_MELON, GOLD_MAGNET, SPIKE_ROCK, COB_CANNON, IMITATER,
	// pvz2
//	ICEBERG_LETTUCE, GOLD_LEAF,
	// other
//	WATER_GUARD, LIGHTLING_ROD, STRANGE_CAT,

	/**
	 * Zombies
	 */
	// 1
//	NORMAL_ZOMBIE, FLAG_ZOMBIE, CONEHEAD_ZOMBIE, POLE_ZOMBIE, BUCKETHEAD_ZOMBIE,
	// 2
//	TOMB_STONE, PAPER_ZOMBIE, OLD_ZOMBIE, SUNDAY_EDITION_ZOMBIE, SCREENDOOR_ZOMBIE, FOOTBALL_ZOMBIE,
//	GIGA_FOOTBALL_ZOMBIE, DANCING_ZOMBIE, BACKUP_DANCER,
	// 3
//	SNORKEL_ZOMBIE, ZOMBONI, BOBSLE_TEAM, DOLPHIN_RIDER, LAVA_ZOMBIE,
	// 4
//	JACK_IN_BOX_ZOMBIE, BALLON_ZOMBIE, DIGGER_ZOMBIE, POGO_ZOMBIE, YETI_ZOMBIE,
	// 5
//	CATAPULT_ZOMBIE, GARGANTUAR, IMP, SAD_GARGANTUAR,
	// boss
//	ZOMBOSS,
	// plant_zombie
//	PEASHOOTER_ZOMBIE, NUTWALL_ZOMBIE, GATLINGPEA_ZOMBIE, TALLNUT_ZOMBIE, SQUASH_ZOMBIE, JALAPENO_ZOMBIE
	;
	
	public static boolean isPlant(Almanacs a) {
		return a.ordinal()<PlantUtil.CURRENT_PLANT_NUM;
	}
	
	public static String getAlmanacName(Almanacs a) {
		if(a==LILY_PAD) return new TranslationTextComponent("block.pvz."+a.toString().toLowerCase()).getFormattedText();
	    return new TranslationTextComponent("entity.pvz."+a.toString().toLowerCase()).getFormattedText();
	}
	
	public static Almanacs getAlmanacByName(String name) {
		for (Almanacs a : Almanacs.values()) {
			if (name.equals(a.toString().toLowerCase())) {
				return a;
			}
		}
		return null;
	}
	
	public static enum Categories{
		ALL, PLANTS, ZOMBIES;
	}

}
