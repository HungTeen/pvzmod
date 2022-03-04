package com.hungteen.pvz.common.world.feature;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.hungteen.pvz.common.block.BlockRegister;

import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class PVZFeatures {

	public static final BlockClusterFeatureConfig CHOMPER_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegister.CHOMPER.get().defaultBlockState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock())).noProjection().build();
	public static final BlockClusterFeatureConfig ORIGIN_ORE_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegister.ORIGIN_ORE.get().defaultBlockState()), new SimpleBlockPlacer())).xspread(2).zspread(2).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock())).noProjection().build();
	public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> NUT_TREE = Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(
			new SimpleBlockStateProvider(BlockRegister.NUT_LOG.get().defaultBlockState()),
			new SimpleBlockStateProvider(BlockRegister.NUT_LEAVES.get().defaultBlockState()),
			new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3), 
			new StraightTrunkPlacer(5, 5, 4), 
			new TwoLayerFeature(1, 0, 1)))
			.ignoreVines()
			.build());
	
	public static final ConfiguredFeature<?, ?> PATCH_CHOMPER_PLANT = register("patch_chomper_plant", Feature.RANDOM_PATCH.configured(CHOMPER_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(32));
	public static final ConfiguredFeature<?, ?> TREES_NUT = register("nut_tree", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(NUT_TREE.weighted(1F)), NUT_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.05F, 1))));
	
	private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String p_243968_0_,
			ConfiguredFeature<FC, ?> p_243968_1_) {
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, p_243968_0_, p_243968_1_);
	}
	
}