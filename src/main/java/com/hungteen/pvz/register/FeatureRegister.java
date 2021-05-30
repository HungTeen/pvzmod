package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.world.gen.GenOres;

import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FeatureRegister {

	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES,
			PVZMod.MOD_ID);

	public static ConfiguredFeature<?, ?> CONFIGURED_AMETHYST_ORE;
	
	/**
	 * called at RegistryHandler for set up Configured Features.
	 */
	public static void setupConfiguredFeatures() {
		StructureRegister.setupStructures();
		setupOres();
	}
	
	public static void setupOres() {
		CONFIGURED_AMETHYST_ORE = Feature.ORE.configured(new OreFeatureConfig(
				GenOres.FillerBlockType.END_STONE, BlockRegister.AMETHYST_ORE.get().defaultBlockState(), 4))
				.range(128).count(15).squared();
	}
	
}
