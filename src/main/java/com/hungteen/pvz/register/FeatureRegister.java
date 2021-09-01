package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.RegistryHandler;
import com.hungteen.pvz.common.world.gen.GenOres;
import com.hungteen.pvz.utils.ConfigUtil;

import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FeatureRegister {

	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES,
			PVZMod.MOD_ID);

	public static ConfiguredFeature<?, ?> CONFIGURED_AMETHYST_ORE;
	public static ConfiguredFeature<?, ?> CONFIGURED_ORIGIN_ORE;
	public static ConfiguredFeature<?, ?> CONFIGURED_DOUBLE_ORIGIN_ORE;
	
	/**
	 * called at RegistryHandler for set up Configured Features.
	 * {@link RegistryHandler#setUp(net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent)}
	 */
	public static void setupConfiguredFeatures() {
		StructureRegister.setupStructures();
		setupOres();
	}
	
	private static void setupOres() {
		CONFIGURED_AMETHYST_ORE = Feature.ORE.configured(new OreFeatureConfig(
				GenOres.FillerBlockType.END_STONE, BlockRegister.AMETHYST_ORE.get().defaultBlockState(), 4))
				.range(128).count(ConfigUtil.getGenAmethystOreChance()).squared();
		
		CONFIGURED_ORIGIN_ORE = Feature.ORE.configured(new OreFeatureConfig(
				GenOres.FillerBlockType.GRASS, BlockRegister.ORIGIN_ORE.get().defaultBlockState(), 4))
				.range(200).count(ConfigUtil.getGenOriginOreChance()).squared();
		
		CONFIGURED_DOUBLE_ORIGIN_ORE = Feature.ORE.configured(new OreFeatureConfig(
				GenOres.FillerBlockType.GRASS, BlockRegister.ORIGIN_ORE.get().defaultBlockState(), 4))
				.range(200).count(ConfigUtil.getGenOriginOreChance() * 2).squared();
	}
	
}
