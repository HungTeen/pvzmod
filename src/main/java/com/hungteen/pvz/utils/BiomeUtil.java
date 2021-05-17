package com.hungteen.pvz.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.minecraft.world.biome.BiomeMaker;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class BiomeUtil {

//	public static final Biome[] ALL_BIOMES = new Biome[] {
//			Biomes.OCEAN, Biomes.FROZEN_OCEAN, Biomes.DEEP_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_FROZEN_OCEAN,
//			Biomes.WARM_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.COLD_OCEAN, Biomes.DEEP_WARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN,
//			Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS,
//			Biomes.DESERT, Biomes.DESERT_HILLS, Biomes.DESERT_LAKES,
//			Biomes.MOUNTAINS, Biomes.MOUNTAIN_EDGE,
//			Biomes.FOREST, Biomes.FLOWER_FOREST,
//			Biomes.TAIGA, Biomes.TAIGA_HILLS, Biomes.GIANT_TREE_TAIGA, Biomes.GIANT_TREE_TAIGA_HILLS, Biomes.TAIGA_MOUNTAINS, Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_SPRUCE_TAIGA_HILLS,
//			Biomes.SNOWY_TAIGA,Biomes.SNOWY_TAIGA_HILLS, Biomes.SNOWY_TAIGA_MOUNTAINS,
//			Biomes.SWAMP, Biomes.SWAMP_HILLS,
//			Biomes.RIVER, 
//			Biomes.field_76778_j, 
//			Biomes.THE_END, Biomes.SMALL_END_ISLANDS, Biomes.END_MIDLANDS, Biomes.END_HIGHLANDS, Biomes.END_BARRENS,
//			Biomes.FROZEN_RIVER, 
//			Biomes.SNOWY_TUNDRA, Biomes.SNOWY_MOUNTAINS,
//			Biomes.MUSHROOM_FIELDS, Biomes.MUSHROOM_FIELD_SHORE, 
//			Biomes.BEACH, Biomes.SNOWY_BEACH,
//			Biomes.WOODED_HILLS, Biomes.WOODED_MOUNTAINS,
//			Biomes.JUNGLE,Biomes.JUNGLE_HILLS, Biomes.JUNGLE_EDGE, Biomes.MODIFIED_JUNGLE, Biomes.MODIFIED_JUNGLE_EDGE,
//			Biomes.STONE_SHORE, 
//			Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.TALL_BIRCH_FOREST, Biomes.TALL_BIRCH_HILLS, 
//			Biomes.DARK_FOREST, Biomes.DARK_FOREST_HILLS,
//			Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.SHATTERED_SAVANNA, Biomes.SHATTERED_SAVANNA_PLATEAU,
//			Biomes.BADLANDS, Biomes.WOODED_BADLANDS_PLATEAU,Biomes.BADLANDS_PLATEAU, Biomes.ERODED_BADLANDS, Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU, Biomes.MODIFIED_BADLANDS_PLATEAU,
//			Biomes.THE_VOID,
//			Biomes.GRAVELLY_MOUNTAINS, Biomes.MODIFIED_GRAVELLY_MOUNTAINS,
//			Biomes.ICE_SPIKES,  
//			Biomes.BAMBOO_JUNGLE, Biomes.BAMBOO_JUNGLE_HILLS,
//			BiomeRegister.ZEN_GARDEN.get(),
//	};
//	
//	public static final Biome[] NETHER = new Biome[] {
//			Biomes.field_76778_j
//	};
//	
//	public static final Biome[] THE_END = new Biome[] {
//			Biomes.THE_END, Biomes.SMALL_END_ISLANDS, Biomes.END_MIDLANDS, Biomes.END_HIGHLANDS, Biomes.END_BARRENS,
//	};
//	
//	public static final Biome[] PLAINS = new Biome[] {
//			Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS
//	};
//
//	public static final Biome[] OVER_LAND = new Biome[] { Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS, Biomes.DESERT,
//			Biomes.DESERT_HILLS, Biomes.MOUNTAINS, Biomes.MOUNTAIN_EDGE, Biomes.FOREST, Biomes.FLOWER_FOREST,
//			Biomes.TAIGA, Biomes.TAIGA_HILLS, Biomes.GIANT_TREE_TAIGA, Biomes.GIANT_TREE_TAIGA_HILLS,
//			Biomes.TAIGA_MOUNTAINS, Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_SPRUCE_TAIGA_HILLS, Biomes.SNOWY_TAIGA,
//			Biomes.SNOWY_TAIGA_HILLS, Biomes.SNOWY_TAIGA_MOUNTAINS, Biomes.SWAMP, Biomes.SWAMP_HILLS,
//			Biomes.SNOWY_TUNDRA, Biomes.SNOWY_MOUNTAINS, Biomes.MUSHROOM_FIELDS, Biomes.MUSHROOM_FIELD_SHORE,
//			Biomes.BEACH, Biomes.SNOWY_BEACH, Biomes.WOODED_HILLS, Biomes.WOODED_MOUNTAINS, Biomes.JUNGLE,
//			Biomes.JUNGLE_HILLS, Biomes.JUNGLE_EDGE, Biomes.MODIFIED_JUNGLE, Biomes.MODIFIED_JUNGLE_EDGE,
//			Biomes.STONE_SHORE, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.TALL_BIRCH_FOREST,
//			Biomes.TALL_BIRCH_HILLS, Biomes.DARK_FOREST, Biomes.DARK_FOREST_HILLS, Biomes.SAVANNA,
//			Biomes.SAVANNA_PLATEAU, Biomes.SHATTERED_SAVANNA, Biomes.SHATTERED_SAVANNA_PLATEAU, Biomes.BADLANDS,
//			Biomes.WOODED_BADLANDS_PLATEAU, Biomes.BADLANDS_PLATEAU, Biomes.ERODED_BADLANDS,
//			Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU, Biomes.MODIFIED_BADLANDS_PLATEAU, Biomes.GRAVELLY_MOUNTAINS,
//			Biomes.MODIFIED_GRAVELLY_MOUNTAINS, Biomes.ICE_SPIKES, Biomes.BAMBOO_JUNGLE, Biomes.BAMBOO_JUNGLE_HILLS, 
//	};
//	
//	public static final Biome[] DERSERT = new Biome[] {
//			Biomes.DESERT, Biomes.DESERT_HILLS, Biomes.DESERT_LAKES, Biomes.BADLANDS, Biomes.WOODED_BADLANDS_PLATEAU,
//			Biomes.BADLANDS_PLATEAU, Biomes.ERODED_BADLANDS, Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU, Biomes.MODIFIED_BADLANDS_PLATEAU,
//	};
//	
//	public static final Biome[] OCEAN = new Biome[] {
//			Biomes.OCEAN, Biomes.FROZEN_OCEAN, Biomes.DEEP_OCEAN, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_FROZEN_OCEAN,
//			Biomes.WARM_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.COLD_OCEAN, Biomes.DEEP_WARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN,
//	};
//	
//	public static final Biome[] TAIGA = new Biome[] {
//			Biomes.TAIGA, Biomes.TAIGA_HILLS, Biomes.GIANT_TREE_TAIGA, Biomes.GIANT_TREE_TAIGA_HILLS, Biomes.TAIGA_MOUNTAINS,
//			Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_SPRUCE_TAIGA_HILLS,
//	};
	
	public static final Method GET_SKY_COLOR_WITH_TEMPERATURE_MODIFIER = ObfuscationReflectionHelper.findMethod(BiomeMaker.class, /* getSkyColorWithTemperatureModifier */ "func_244206_a", float.class);
	
	public static int getSkyColor(float temp) {
		final int skyColour;
		try {
			skyColour = (int) GET_SKY_COLOR_WITH_TEMPERATURE_MODIFIER.invoke(null, temp);
		} catch (final IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException("Error: Unable to get sky colour", e);
		}
		return skyColour;
	}
	
}
