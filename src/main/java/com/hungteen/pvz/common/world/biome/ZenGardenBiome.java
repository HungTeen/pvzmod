package com.hungteen.pvz.common.world.biome;

import com.hungteen.pvz.common.entity.PVZEntities;
import com.hungteen.pvz.common.world.feature.PVZFeatures;
import com.hungteen.pvz.utils.BiomeUtil;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-11 09:45
 **/
public class ZenGardenBiome {

    public static Biome getZenGardenBiome() {

        /*
        Special effect.
         */
        final BiomeSpecialEffects.Builder effectBuilder = new BiomeSpecialEffects.Builder()
                .waterColor(4159204)
                .waterFogColor(329011)
                .fogColor(12638463)
                .skyColor(BiomeUtil.getSkyColor(0.8F))
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS);

        /*
        Mob spawner.
         */
        final MobSpawnSettings.Builder mobBuilder = new MobSpawnSettings.Builder();
        mobBuilder.creatureGenerationProbability(0.07F);
        mobBuilder.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(PVZEntities.SUN.get(), 100, 1, 1));

        /*
        Decorations.
         */
        BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings.Builder();
//        .surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(genBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(genBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(genBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(genBuilder);
        BiomeDefaultFeatures.addDefaultSprings(genBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(genBuilder);
        BiomeDefaultFeatures.addDefaultOres(genBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(genBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(genBuilder);
        BiomeDefaultFeatures.addPlainGrass(genBuilder);
        BiomeDefaultFeatures.addPlainVegetation(genBuilder);
        BiomeDefaultFeatures.addDefaultFlowers(genBuilder);

//        genBuilder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_SUNFLOWER);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_PUMPKIN);
//        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PVZFeatures.PATCH_CHOMPER_PLANT);
//        genBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, PVZFeatures.TREES_NUT);

        /*

         */

        return PVZBiomes.makeBiome(Biome.BiomeCategory.PLAINS, Biome.Precipitation.RAIN, 0.8F, 0.4F,
                effectBuilder.build(), mobBuilder.build(), genBuilder.build());
    }

}
