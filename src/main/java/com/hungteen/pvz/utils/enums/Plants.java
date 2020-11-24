package com.hungteen.pvz.utils.enums;

public enum Plants {
	//grass day
	PEA_SHOOTER,
	SUN_FLOWER,
	CHERRY_BOMB,
	WALL_NUT,
	POTATO_MINE,
	SNOW_PEA,
	CHOMPER,
	REPEATER,
	//grass night
	PUFF_SHROOM,
	SUN_SHROOM,
	FUME_SHROOM,
	GRAVE_BUSTER,
	HYPNO_SHROOM,
	SCAREDY_SHROOM,
	ICE_SHROOM,
	DOOM_SHROOM,
	//pool day
	LILY_PAD,
	SQUASH,
	THREE_PEATER,
	TANGLE_KELP,
	JALAPENO,
	SPIKE_WEED,
	TORCH_WOOD,
	TALL_NUT,
	//pool night
//	SEA_SHROOM,
//	PLANTERN, 
//	CACTUS,
//	BLOVER,
//	SPLIT_PEA,
//	STAR_FRUIT,
	PUMPKIN,
//	MAGNET_SHROOM,
	//roof 
//	CABBAGE_PULT,
//	FLOWER_POT, 
//	KERNEL_PULT,
	COFFEE_BEAN,
//	GARLIC, 
//	UMBRELLA_LEAF, 
	MARIGOLD, 
//	MELON_PULT,
	//upgrade
	GATLING_PEA,
	TWIN_SUNFLOWER,
//	GLOOM_SHROOM,
//	CAT_TAIL,
//	WINTER_MELON,
//	GOLD_MAGNET, 
//	SPIKE_ROCK,
//	COB_CANNON,
//	IMITATER,
	// other
	WATER_GUARD;

	public static final Plants[] BLOCK_PLANTS = new Plants[] {LILY_PAD};
	public static final Plants[] WATER_PLANTS = new Plants[] {TANGLE_KELP, WATER_GUARD};
	public static final Plants[] OUTER_PLANTS = new Plants[] {PUMPKIN};
	public static final Plants[] UPGRADE_PLANTS = new Plants[] {GATLING_PEA, TWIN_SUNFLOWER};
	
	public static Plants getPlantByName(String name) {
		for (Plants plant : Plants.values()) {
			if (name.equals(plant.toString().toLowerCase())) {
				return plant;
			}
		}
		return null;
	}
	
	public static boolean isEntityPlant(Plants p) {
		return ! isBlockPlant(p) && ! isOuterPlant(p);
	}
	
	public static boolean isBlockPlant(Plants p) {
		for(Plants plant : BLOCK_PLANTS) {
			if(p == plant) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isWaterPlant(Plants p) {
		for(Plants plant : WATER_PLANTS) {
			if(p == plant) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isOuterPlant(Plants p) {
		for(Plants plant : OUTER_PLANTS) {
			if(p == plant) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isUpgradePlant(Plants p) {
		for(Plants plant : UPGRADE_PLANTS) {
			if(p == plant) {
				return true;
			}
		}
		return false;
	}

}
