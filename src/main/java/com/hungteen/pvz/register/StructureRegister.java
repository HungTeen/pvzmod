package com.hungteen.pvz.register;

import java.util.Arrays;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.structure.zombie.BucketHouseComponents;
import com.hungteen.pvz.structure.zombie.BucketHouseStructure;
import com.hungteen.pvz.structure.zombie.DolphinHouseComponents;
import com.hungteen.pvz.structure.zombie.DolphinHouseStructure;
import com.hungteen.pvz.structure.zombie.GraveHouseComponents;
import com.hungteen.pvz.structure.zombie.GraveHouseStructure;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class StructureRegister {
	
	public static final DeferredRegister<Structure<?>> STRUCTURE_FEATURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, PVZMod.MOD_ID);
	
	public static final RegistryObject<Structure<NoFeatureConfig>> BUCKET_HOUSE = STRUCTURE_FEATURES.register("bucket_house", () -> new BucketHouseStructure(NoFeatureConfig.CODEC));
	public static StructureFeature<?, ?> CONFIGURED_BUCKET_HOUSE;
	public static final RegistryObject<Structure<NoFeatureConfig>> DOLPHIN_HOUSE = STRUCTURE_FEATURES.register("dolphin_house", () -> new DolphinHouseStructure(NoFeatureConfig.CODEC));
	public static StructureFeature<?, ?> CONFIGURED_DOLPHIN_HOUSE;
	public static final RegistryObject<Structure<NoFeatureConfig>> GRAVE_HOUSE = STRUCTURE_FEATURES.register("grave_house", () -> new GraveHouseStructure(NoFeatureConfig.CODEC));
	public static StructureFeature<?, ?> CONFIGURED_GRAVE_HOUSE;
	
	public static IStructurePieceType DAVE_VILLA;
	public static IStructurePieceType BUCKET_HOUSE_PIECE;
	public static IStructurePieceType DOLPHIN_HOUSE_PIECE;
	public static IStructurePieceType GRAVE_HOUSE_PIECE;
	public static IStructurePieceType SUN_TEMPLE;
	public static IStructurePieceType YETI_HOUSE;
	
	/**
	 * I hate my trash codes.
	 */
	public static void setupStructures() {
		{
			int dis = PVZConfig.COMMON_CONFIG.WorldSettings.StructureSettings.BucketHouseDistance.get();
			addStructure(BUCKET_HOUSE.get(), new StructureSeparationSettings(dis, dis / 2, 998244353));
		    BUCKET_HOUSE_PIECE = Registry.register(Registry.STRUCTURE_PIECE, "bucket_house", BucketHouseComponents.BucketHouseComponent::new);
		    CONFIGURED_BUCKET_HOUSE = BUCKET_HOUSE.get().configured(IFeatureConfig.NONE);
		    Registry.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, StringUtil.prefix("configured_bucket_house"), CONFIGURED_BUCKET_HOUSE);
	    	FlatGenerationSettings.STRUCTURE_FEATURES.put(BUCKET_HOUSE.get(), CONFIGURED_BUCKET_HOUSE);
		}
		{
			int dis = PVZConfig.COMMON_CONFIG.WorldSettings.StructureSettings.DolphinHouseDistance.get();
			addStructure(DOLPHIN_HOUSE.get(), new StructureSeparationSettings(dis, dis / 2, 165745799));
		    DOLPHIN_HOUSE_PIECE = Registry.register(Registry.STRUCTURE_PIECE, "dolphin_house", DolphinHouseComponents.DolphinHouseComponent::new);
		    CONFIGURED_DOLPHIN_HOUSE = DOLPHIN_HOUSE.get().configured(IFeatureConfig.NONE);
		    Registry.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, StringUtil.prefix("configured_dolphin_house"), CONFIGURED_DOLPHIN_HOUSE);
	    	FlatGenerationSettings.STRUCTURE_FEATURES.put(DOLPHIN_HOUSE.get(), CONFIGURED_DOLPHIN_HOUSE);
		}
		{
			int dis = PVZConfig.COMMON_CONFIG.WorldSettings.StructureSettings.GraveHouseDistance.get();
			addStructure(GRAVE_HOUSE.get(), new StructureSeparationSettings(dis, dis / 2, 165745797));
		    GRAVE_HOUSE_PIECE = Registry.register(Registry.STRUCTURE_PIECE, "grave_house", GraveHouseComponents.GraveHouseComponent::new);
		    CONFIGURED_GRAVE_HOUSE = GRAVE_HOUSE.get().configured(IFeatureConfig.NONE);
		    Registry.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, StringUtil.prefix("configured_grave_house"), CONFIGURED_GRAVE_HOUSE);
	    	FlatGenerationSettings.STRUCTURE_FEATURES.put(GRAVE_HOUSE.get(), CONFIGURED_GRAVE_HOUSE);
		}
	}
	
	/**
	 * used in genStructure
	 */
	public static void addStructuresToMap(ServerWorld server, Map<Structure<?>, StructureSeparationSettings> tempMap) {
		Arrays.asList(
				StructureRegister.BUCKET_HOUSE, StructureRegister.DOLPHIN_HOUSE, StructureRegister.GRAVE_HOUSE
				).forEach(l -> {
					tempMap.putIfAbsent(l.get(), DimensionStructuresSettings.DEFAULTS.get(l.get()));
				});
	}
	
	public static <F extends Structure<NoFeatureConfig>> void addStructure(F structure, StructureSeparationSettings settings) {
		Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);
		DimensionStructuresSettings.DEFAULTS =
                ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
                        .putAll(DimensionStructuresSettings.DEFAULTS)
                        .put(structure, settings)
                        .build();
	}
	
}
