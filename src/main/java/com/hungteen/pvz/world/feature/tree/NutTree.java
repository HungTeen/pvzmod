package com.hungteen.pvz.world.feature.tree;

import java.util.Random;

import com.hungteen.pvz.register.BlockRegister;

import net.minecraft.block.Blocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraftforge.common.IPlantable;

public class NutTree extends Tree{

	public static final TreeFeatureConfig NUT_TREE_CONFIG = (new TreeFeatureConfig.Builder(
			new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()), 
			new SimpleBlockStateProvider(BlockRegister.NUT_LEAVES.get().getDefaultState()), 
			new BlobFoliagePlacer(2,0)).baseHeight(5).heightRandA(5).foliageHeight(4).ignoreVines()
			.setSapling((IPlantable) BlockRegister.NUT_SAPLING.get())).build();
	
	@Override
	protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
		return Feature.NORMAL_TREE.withConfiguration(NUT_TREE_CONFIG);
	}

}
