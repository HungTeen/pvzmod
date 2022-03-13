package com.hungteen.pvz.common.world.biome;

import com.hungteen.pvz.utils.BiomeUtil;

import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
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
