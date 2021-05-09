package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeRegister {

	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, PVZMod.MOD_ID);
	
//	public static final RegistryObject<Biome> ZEN_GARDEN = BIOMES.register("zen_garden", () -> 
//	    new Biome.Builder()
//	        .biomeCategory(Category.PLAINS)
//	        .precipitation(Biome.RainType.RAIN)
//	        .biomeCategory(Biome.Category.PLAINS)
//	        .depth(0.125F)
//	        .scale(0.05F)
//	        .temperature(0.8F)
//	        .downfall(0.4F)
//	        .specialEffects(new BiomeAmbience.Builder().waterColor(4159204).waterFogColor(329011).build())
//	        .generationSettings(new BiomeGenerationSettings.Builder().surfaceBuilder(new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, SurfaceBuilder.CONFIG_GRASS)).build())
//	        .mobSpawnSettings(new MobSpawnInfo.Builder()
//	        		.addSpawn(EntityClassification.AMBIENT, new Spawn(EntityRegister.SUN.get(), 100, 1, 1))
//	    .build()
//	);
	
//    public static void addBiomes() {
//        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(ZEN_GARDEN.get(), 100));
//    }
//	
//	public static void addBiomeFeatures(){
//		for(Biome biome : ForgeRegistries.BIOMES.getValues()) {
//			if(biome instanceof PVZBiome) {
//				((PVZBiome) biome).addFeatures();
//				((PVZBiome) biome).addSpawns();
//			}
//			addStructureToBiome(biome);
//		}
//		for(Biome biome:BiomeUtil.PLAINS) {
//			biome.func_226711_a_(FeatureRegister.DAVE_VILLA.get().configured(IFeatureConfig.NONE));
//		}
//		for(Biome biome:BiomeUtil.OVER_LAND) {
//			biome.func_226711_a_(FeatureRegister.BUCKET_HOUSE.get().configured(IFeatureConfig.NONE));
//			biome.func_76747_a(EntityClassification.AMBIENT).add(new Biome.SpawnListEntry(EntityRegister.SUN.get(), 2 * PVZConfig.COMMON_CONFIG.WorldSettings.EntitySpawnSettings.SunSpawnWeight.get(), 1, 1));
//			biome.func_76747_a(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(EntityRegister.TOMB_STONE.get(), PVZConfig.COMMON_CONFIG.WorldSettings.EntitySpawnSettings.TombStoneSpawnWeight.get(), 1, 1));
//			biome.func_76747_a(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(EntityRegister.YETI_ZOMBIE.get(), 4 * PVZConfig.COMMON_CONFIG.WorldSettings.EntitySpawnSettings.YetiZombieSpawnWeight.get(), 1, 1));
//		}
//		for(Biome biome:BiomeUtil.OCEAN) {
//			biome.func_226711_a_(FeatureRegister.DOLPHIN_HOUSE.get().configured(IFeatureConfig.NONE));
//			biome.func_76747_a(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(EntityRegister.FOODIE_ZOMBIE.get(), PVZConfig.COMMON_CONFIG.WorldSettings.EntitySpawnSettings.FoodieZombieSpawnWeight.get(), 1, 2));
////			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(EntityRegister.ZOMBIE_DOLPHIN.get(),PVZConfig.COMMON_CONFIG.WorldSettings.EntitySpawnSettings.ZombieDolphinSpawnWeight.get(), 1,2));
//		}
//		for(Biome biome:BiomeUtil.NETHER) {
//			biome.func_76747_a(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(EntityRegister.LAVA_ZOMBIE.get(), PVZConfig.COMMON_CONFIG.WorldSettings.EntitySpawnSettings.LavaZombieSpawnWeight.get(), 1, 1));
//		}
//		for(Biome biome : BiomeUtil.TAIGA) {
//			biome.func_226711_a_(FeatureRegister.GRAVE_HOUSE.get().configured(IFeatureConfig.NONE));
//		}
//		for(Biome biome : BiomeUtil.DERSERT) {
//			biome.func_226711_a_(FeatureRegister.SUN_TEMPLE.get().configured(IFeatureConfig.NONE));
//		}
//		Biomes.SNOWY_TUNDRA.func_226711_a_(FeatureRegister.YETI_HOUSE.get().configured(IFeatureConfig.NONE));
//	}
//	
//	public static void addStructureToBiome(Biome biome) {
//		biome.func_203611_a(GenerationStage.Decoration.SURFACE_STRUCTURES, FeatureRegister.DAVE_VILLA.get().configured(IFeatureConfig.NONE).decorated(Placement.NOPE.configured(IPlacementConfig.NONE))); 
//	    biome.func_203611_a(GenerationStage.Decoration.SURFACE_STRUCTURES, FeatureRegister.BUCKET_HOUSE.get().configured(IFeatureConfig.NONE).decorated(Placement.NOPE.configured(IPlacementConfig.NONE))); 
//	    biome.func_203611_a(GenerationStage.Decoration.SURFACE_STRUCTURES, FeatureRegister.DOLPHIN_HOUSE.get().configured(IFeatureConfig.NONE).decorated(Placement.NOPE.configured(IPlacementConfig.NONE)));
//	    biome.func_203611_a(GenerationStage.Decoration.SURFACE_STRUCTURES, FeatureRegister.GRAVE_HOUSE.get().configured(IFeatureConfig.NONE).decorated(Placement.NOPE.configured(IPlacementConfig.NONE))); 
//	    biome.func_203611_a(GenerationStage.Decoration.SURFACE_STRUCTURES, FeatureRegister.SUN_TEMPLE.get().configured(IFeatureConfig.NONE).decorated(Placement.NOPE.configured(IPlacementConfig.NONE))); 
//	    biome.func_203611_a(GenerationStage.Decoration.SURFACE_STRUCTURES, FeatureRegister.YETI_HOUSE.get().configured(IFeatureConfig.NONE).decorated(Placement.NOPE.configured(IPlacementConfig.NONE))); 
//	}
	
}
