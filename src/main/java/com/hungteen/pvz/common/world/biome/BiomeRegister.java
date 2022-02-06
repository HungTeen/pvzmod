package com.hungteen.pvz.common.world.biome;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.RegistryHandler;
import com.hungteen.pvz.common.world.FeatureRegister;
import com.hungteen.pvz.common.world.feature.GenOres;
import com.hungteen.pvz.common.world.spawn.EntitySpawnRegister;
import com.hungteen.pvz.common.world.structure.StructureRegister;
import com.hungteen.pvz.utils.BiomeUtil;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
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

	public static final RegistryObject<Biome> ZEN_GARDEN = BIOMES.register("zen_garden", () -> ZenGardenBiome.getZenGardenBiome());
	
	/**
	 * {@link RegistryHandler#setUp(FMLCommonSetupEvent)}
	 */
    public static void registerBiomes(final FMLCommonSetupEvent ev) {
    	registerBiome(ZEN_GARDEN.get(), BiomeType.WARM, PVZConfig.COMMON_CONFIG.WorldSettings.GenZenGardenChance.get(), BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);
    }
    
    /**
     * fired event from main.
     * {@link PVZMod#PVZMod()}
     */
    public static void biomeModification(final BiomeLoadingEvent event) {
    	Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
		if(biome != null) {//prevent crash with other mod.
			if(biome.equals(BiomeRegister.ZEN_GARDEN.get())){
				event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, FeatureRegister.CONFIGURED_DOUBLE_ORIGIN_ORE);
			} else {
				final RegistryKey<Biome> biomeKey = BiomeUtil.getKey(biome);
			    StructureRegister.addStructureToBiome(event, biomeKey);
    	        EntitySpawnRegister.addEntitySpawnToBiome(event, biomeKey);
    	        GenOres.addOresToBiomes(event, biomeKey);
			}
    	}
    }
    
    private static void registerBiome(final Biome biome, final BiomeManager.BiomeType biomeType, final int weight, final BiomeDictionary.Type... types) {
    	BiomeDictionary.addTypes(BiomeUtil.getKey(biome), types);
		BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(BiomeUtil.getKey(biome), weight));
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
