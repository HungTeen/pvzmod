package com.hungteen.pvz.world.feature.tree;

import java.util.Random;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class NutTree extends Tree{

	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random p_225546_1_,
			boolean p_225546_2_) {
		return null;
	}

//	public static final TreeFeatureConfig NUT_TREE_CONFIG = (new TreeFeatureConfig.Builder(
//			new SimpleBlockStateProvider(Blocks.OAK_LOG.defaultBlockState()), 
//			new SimpleBlockStateProvider(BlockRegister.NUT_LEAVES.get().defaultBlockState()), 
//			new BlobFoliagePlacer(2,0)).func_225569_d_(5).func_227354_b_(5).func_227360_i_(4).func_227352_a_()
//			.setSapling((IPlantable) BlockRegister.NUT_SAPLING.get())).build();
//	
//	@Override
//	protected ConfiguredFeature<TreeFeatureConfig, ?> getConfiguredFeature(Random randomIn, boolean p_225546_2_) {
//		return Feature.field_202301_A.configured(NUT_TREE_CONFIG);
//	}

}
