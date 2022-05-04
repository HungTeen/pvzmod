package com.hungteen.pvz.common.world.biome;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.world.feature.PVZOrePlacements;
import com.hungteen.pvz.common.world.feature.PVZPlacements;
import com.hungteen.pvz.common.world.spawn.SpawnRegister;
import com.hungteen.pvz.utils.BiomeUtil;
import com.hungteen.pvz.utils.Util;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-11 09:44
 **/
public class PVZBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, PVZMod.MOD_ID);

//    public static final RegistryObject<Biome> ZEN_GARDEN = BIOMES.register("zen_garden", ZenGardenBiome::getZenGardenBiome);

    public static final ResourceKey<Biome> ZEN_GARDEN = ResourceKey.create(Registry.BIOME_REGISTRY, Util.prefix("zen_garden"));

    /**
     * {@link PVZMod#setUp(FMLCommonSetupEvent)}
     */
    public static void registerBiomes() {
//        registerBiome(ZEN_GARDEN.get(), BiomeManager.BiomeType.WARM, 2000, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);
    }

    /**
     * fired event from main.
     * {@link PVZMod#PVZMod()}
     */
    public static void biomeModification(final BiomeLoadingEvent event) {
        Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
        if(biome != null) {//prevent crash with other mod.
            final ResourceKey<Biome> biomeKey = BiomeUtil.getKey(biome);
            if(biomeKey.equals(ZEN_GARDEN)){
                event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PVZOrePlacements.ORE_ORIGIN_UPPER_DOUBLE);
                event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PVZPlacements.TREES_NUT);
            } else {
//                StructureRegister.addStructureToBiome(event, biomeKey);
                SpawnRegister.addEntitySpawnToBiome(event, biomeKey);
                PVZOrePlacements.addOresToBiomes(event, biomeKey);
            }
        }
    }

    private static void registerBiome(final Biome biome, final BiomeManager.BiomeType biomeType, final int weight, final BiomeDictionary.Type... types) {
        BiomeDictionary.addTypes(BiomeUtil.getKey(biome), types);
        BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(BiomeUtil.getKey(biome), weight));
    }

    public static Biome makeBiome(Biome.BiomeCategory category, Biome.Precipitation rainType, float temperature, float downFall, BiomeSpecialEffects effect, MobSpawnSettings mobInfo, BiomeGenerationSettings generateSettings) {
        return new Biome.BiomeBuilder()
                .biomeCategory(category)
                .precipitation(rainType)
                .temperature(temperature)
                .downfall(downFall)
                .specialEffects(effect)
                .mobSpawnSettings(mobInfo)
                .generationSettings(generateSettings)
                .build();
    }

}
