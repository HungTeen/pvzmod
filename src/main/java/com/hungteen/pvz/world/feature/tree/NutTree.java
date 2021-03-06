package com.hungteen.pvz.world.feature.tree;

import java.util.Random;

import com.hungteen.pvz.world.feature.PVZFeatures;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class NutTree extends Tree{

	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random p_225546_1_,
			boolean p_225546_2_) {
		return PVZFeatures.NUT_TREE;
	}

}
