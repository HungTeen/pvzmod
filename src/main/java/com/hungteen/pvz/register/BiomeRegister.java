package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.biome.PVZBiome;
import com.hungteen.pvz.biome.ZenGardenBiome;
import com.hungteen.pvz.utils.BiomeUtil;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeRegister {

	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, PVZMod.MOD_ID);
	
	public static final RegistryObject<Biome> ZEN_GARDEN = BIOMES.register("zen_garden", ZenGardenBiome::new);
	
    public static void addBiomes() {
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(ZEN_GARDEN.get(), 100));
    }
	
	public static void addBiomeFeatures(){
		for(Biome biome:ForgeRegistries.BIOMES.getValues()) {
			if(biome instanceof PVZBiome) {
				((PVZBiome) biome).addFeatures();
				((PVZBiome) biome).addSpawns();
			}
			biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, FeatureRegister.DAVE_VILLA.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG))); 
		    biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, FeatureRegister.BUCKET_HOUSE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG))); 
		    biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, FeatureRegister.DOLPHIN_HOUSE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG))); 
		}
		for(Biome biome:BiomeUtil.PLAINS) {
			biome.addStructure(FeatureRegister.DAVE_VILLA.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
		}
		for(Biome biome:BiomeUtil.OVER_LAND) {
			biome.addStructure(FeatureRegister.BUCKET_HOUSE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
			biome.getSpawns(EntityClassification.AMBIENT).add(new Biome.SpawnListEntry(EntityRegister.SUN.get(),2*PVZConfig.COMMON_CONFIG.WorldSettings.EntitySpawnSettings.SunSpawnWeight.get(),1,1));
		}
		for(Biome biome:BiomeUtil.OCEAN) {
			biome.addStructure(FeatureRegister.DOLPHIN_HOUSE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
			biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(EntityRegister.FOODIE_ZOMBIE.get(),PVZConfig.COMMON_CONFIG.WorldSettings.EntitySpawnSettings.FoodieZombieSpawnWeight.get(),1,2));
//			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(EntityRegister.ZOMBIE_DOLPHIN.get(),PVZConfig.COMMON_CONFIG.WorldSettings.EntitySpawnSettings.ZombieDolphinSpawnWeight.get(),1,2));
		}
		for(Biome biome:BiomeUtil.NETHER) {
			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(EntityRegister.LAVA_ZOMBIE.get(),PVZConfig.COMMON_CONFIG.WorldSettings.EntitySpawnSettings.LavaZombieSpawnWeight.get(),1,1));
		}
	}
}
