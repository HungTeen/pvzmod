package com.hungteen.pvz.utils;

import com.hungteen.pvz.common.world.biome.PVZBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-11 10:04
 **/
public class BiomeUtil {

    public static final Set<Biome> OVERWORLD_LAND = new HashSet<>();
    public static final Set<Biome> OVERWORLD_DESERT = new HashSet<>();
    public static final Set<Biome> OVERWORLD_OCEAN = new HashSet<>();
    public static final Set<Biome> OVERWORLD_PLAIN = new HashSet<>();
    public static final Set<Biome> OVERWORLD_CONIFEROUS = new HashSet<>();//taiga
    public static final Set<Biome> OVERWORLD_SNOW_LAND = new HashSet<>();
    public static final Set<Biome> OVERWORLD_FOREST = new HashSet<>();
    public static final Set<Biome> NETHER = new HashSet<>();
    public static final Set<Biome> THE_END = new HashSet<>();

    public static void initBiomeSet() {
        for(Biome biome : ForgeRegistries.BIOMES) {
            ResourceKey<Biome> biomeKey = getKey(biome);
            if(biomeKey.equals(PVZBiomes.ZEN_GARDEN)) continue;//zen garden will not be add
            if(isOverworld(biomeKey)) {
                if(isLand(biomeKey)) {
                    OVERWORLD_LAND.add(biome);
                    if(isSnowy(biomeKey)) {
                        OVERWORLD_SNOW_LAND.add(biome);
                    }
                }
                if(isDesert(biomeKey)) {
                    OVERWORLD_DESERT.add(biome);
                }
                if(isOcean(biomeKey)) {
                    OVERWORLD_OCEAN.add(biome);
                }
                if(isPlain(biomeKey)) {
                    OVERWORLD_PLAIN.add(biome);
                }
                if(isConiferous(biomeKey)) {
                    OVERWORLD_CONIFEROUS.add(biome);
                }
                if(isForest(biomeKey)) {
                    OVERWORLD_FOREST.add(biome);
                }
            }
            if(isNether(biomeKey)) {
                NETHER.add(biome);
            }
            if(isTheEnd(biomeKey)) {
                THE_END.add(biome);
            }
        }
    }

    public static boolean isLand(ResourceKey<Biome> biomeKey) {
        return ! BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.WATER);
    }

    public static boolean isOcean(ResourceKey<Biome> biomeKey) {
        return BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.OCEAN);
    }

    public static boolean isDesert(ResourceKey<Biome> biomeKey) {
        return BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SANDY);
    }

    public static boolean isPlain(ResourceKey<Biome> biomeKey) {
        return BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.PLAINS);
    }

    public static boolean isConiferous(ResourceKey<Biome> biomeKey) {
        return BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.CONIFEROUS);
    }

    public static boolean isSnowy(ResourceKey<Biome> biomeKey) {
        return BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.SNOWY);
    }

    public static boolean isForest(ResourceKey<Biome> biomeKey) {
        return BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.FOREST);
    }

    public static boolean isOverworld(ResourceKey<Biome> biomeKey) {
        return BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.OVERWORLD);
    }

    public static boolean isNether(ResourceKey<Biome> biomeKey) {
        return BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.NETHER);
    }

    public static boolean isNetherWaste(ResourceKey<Biome> biomeKey) {
        return biomeKey.equals(Biomes.NETHER_WASTES);
    }

    public static boolean isTheEnd(ResourceKey<Biome> biomeKey) {
        return BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.END);
    }


    public static int getSkyColor(float p_194844_) {
        float $$1 = p_194844_ / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    public static ResourceKey<Biome> getKey(final Biome biome) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOMES, Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(biome), "Biome registry name was null"));
    }

}
