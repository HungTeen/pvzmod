package com.hungteen.pvz.common.world.biome;

import com.hungteen.pvz.common.world.feature.PVZFeatures;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.utils.BiomeUtil;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

public class ZenGardenBiome {

	public static Biome getZenGardenBiome() {
		//special effect
		final BiomeAmbience.Builder effectBuilder = new BiomeAmbience.Builder()
				.waterColor(4159204).waterFogColor(329011).fogColor(12638463)
				.skyColor(BiomeUtil.getSkyColor(0.8F)).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS);
		
		//mob spawner
		final MobSpawnInfo.Builder mobBuilder = new MobSpawnInfo.Builder();
		mobBuilder.setPlayerCanSpawn();
		mobBuilder.addSpawn(EntityClassification.AMBIENT, new Spawners(EntityRegister.SUN.get(), 100, 1, 1));
		
		//decoration
		BiomeGenerationSettings.Builder genBuilder = new BiomeGenerationSettings.Builder().surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
		DefaultBiomeFeatures.addDefaultCarvers(genBuilder);
		DefaultBiomeFeatures.addDefaultOverworldLandStructures(genBuilder);
		DefaultBiomeFeatures.addDefaultLakes(genBuilder);
		DefaultBiomeFeatures.addDefaultMonsterRoom(genBuilder);
		DefaultBiomeFeatures.addPlainGrass(genBuilder);
		DefaultBiomeFeatures.addPlainVegetation(genBuilder);
		DefaultBiomeFeatures.addDefaultOres(genBuilder);
		DefaultBiomeFeatures.addDefaultSoftDisks(genBuilder);
		DefaultBiomeFeatures.addDefaultUndergroundVariety(genBuilder);
		DefaultBiomeFeatures.addDefaultSprings(genBuilder);
		DefaultBiomeFeatures.addDefaultMushrooms(genBuilder);
		DefaultBiomeFeatures.addDefaultFlowers(genBuilder);
		DefaultBiomeFeatures.addSurfaceFreezing(genBuilder);
		
		genBuilder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_SUNFLOWER);
		genBuilder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_SUGAR_CANE);
		genBuilder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_PUMPKIN);
		genBuilder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, PVZFeatures.PATCH_CHOMPER_PLANT);
		genBuilder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, PVZFeatures.TREES_NUT);
		
		return BiomeRegister.makeBiome(Category.PLAINS, RainType.RAIN, 0.125F, 0.05F, 0.8F, 0.4F, 
				effectBuilder.build(), mobBuilder.build(), genBuilder.build());
	}
	
}
