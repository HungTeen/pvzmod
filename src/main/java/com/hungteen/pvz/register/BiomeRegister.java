package com.hungteen.pvz.register;

import java.util.Objects;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.world.biome.ZenGardenBiome;

import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeRegister {

	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, PVZMod.MOD_ID);

	public static final RegistryObject<Biome> ZEN_GARDEN = BIOMES.register("zen_garden", () -> {return ZenGardenBiome.getZenGardenBiome();});
	
    public static void registerBiomes(final FMLCommonSetupEvent ev) {
    	ev.enqueueWork(() -> {
    		registerBiome(ZEN_GARDEN.get(), BiomeType.WARM, 100, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.OVERWORLD);
    	});
    }
    
    private static void registerBiome(final Biome biome, final BiomeManager.BiomeType biomeType, final int weight, final BiomeDictionary.Type... types) {
    	BiomeDictionary.addTypes(getKey(biome), types);
		BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(getKey(biome), weight));
    }
    
    private static RegistryKey<Biome> getKey(final Biome biome) {
		return RegistryKey.create(ForgeRegistries.Keys.BIOMES, Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(biome), "PVZ Biome registry name was null"));
	}
    
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
	
	/**
     * This is the event you will use to add anything to any biome.
     * This includes spawns, changing the biome's looks, messing with its surfacebuilders,
     * adding carvers, spawning new features... etc
     *
     * Here, we will use this to add our structure to all biomes.
     */
    public static void biomeModification(final BiomeLoadingEvent event) {
    	Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
    	event.getGeneration().addStructureStart(StructureRegister.CONFIGURED_BUCKET_HOUSE);
    	event.getGeneration().addStructureStart(StructureRegister.CONFIGURED_DOLPHIN_HOUSE);
    	event.getGeneration().addStructureStart(StructureRegister.CONFIGURED_GRAVE_HOUSE);
    }
    
	public static Biome makeBiome(Category category, RainType rainType, float depth, float scale, float temperature, float downFall, BiomeAmbience effect, MobSpawnInfo mobInfo, BiomeGenerationSettings generateSettings) {
		return new Biome.Builder()
				.biomeCategory(category)
				.precipitation(rainType)
				.depth(depth)
		        .scale(scale)
		        .temperature(temperature)
		        .downfall(downFall)
		        .specialEffects(effect)
		        .mobSpawnSettings(mobInfo)
		        .generationSettings(generateSettings)
				.build();
	}
	
}
