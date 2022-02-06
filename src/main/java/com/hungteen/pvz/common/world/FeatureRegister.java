package com.hungteen.pvz.common.world;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.world.feature.GenOres;
import com.hungteen.pvz.common.world.structure.StructureRegister;
import com.hungteen.pvz.utils.ConfigUtil;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FeatureRegister {

	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES,
			PVZMod.MOD_ID);

	public static ConfiguredFeature<?, ?> CONFIGURED_AMETHYST_ORE;
	public static ConfiguredFeature<?, ?> CONFIGURED_ORIGIN_ORE;
	public static ConfiguredFeature<?, ?> CONFIGURED_DOUBLE_ORIGIN_ORE;
	
	/**
	 * for set up Configured Features. <br>
	 * Ensure this is run after the Feature DeferredRegister in ModFeatures.
	 */
	@SubscribeEvent(priority = EventPriority.LOW)
	public static void setupConfiguredFeatures(final RegistryEvent.Register<Feature<?>> event) {
//		StructureRegister.setupStructures();
		setupOres();
	}

	/**
	 * for set up Configured Features. <br>
	 * Ensure this is run after the Feature DeferredRegister in ModFeatures.
	 */
	@SubscribeEvent(priority = EventPriority.LOW)
	public static void setupStructures(final RegistryEvent.Register<Structure<?>> event) {
		StructureRegister.setupStructures();
//		setupOres();
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
