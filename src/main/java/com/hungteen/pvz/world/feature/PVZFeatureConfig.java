package com.hungteen.pvz.world.feature;

import com.google.common.collect.ImmutableSet;
import com.hungteen.pvz.register.BlockRegister;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;

public class PVZFeatureConfig {

	public static final BlockClusterFeatureConfig CHOMPER_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegister.CHOMPER.get().getDefaultState()), new SimpleBlockPlacer())).tries(64).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK.getBlock())).func_227317_b_().build();
}
