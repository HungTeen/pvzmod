package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.world.biome.ZenGardenBiome;
import com.hungteen.pvz.common.world.gen.GenOres;
import com.hungteen.pvz.utils.BiomeUtil;

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
    	registerBiome(ZEN_GARDEN.get(), BiomeType.WARM, 100, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.OVERWORLD);
    }
    
    private static void registerBiome(final Biome biome, final BiomeManager.BiomeType biomeType, final int weight, final BiomeDictionary.Type... types) {
    	BiomeDictionary.addTypes(BiomeUtil.getKey(biome), types);
		BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(BiomeUtil.getKey(biome), weight));
    }
    
    public static void biomeModification(final BiomeLoadingEvent event) {
    	StructureRegister.addStructureToBiome(event);
    	EntitySpawnRegister.addEntitySpawnToBiome(event);
    	GenOres.addOresToBiomes(event);
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
