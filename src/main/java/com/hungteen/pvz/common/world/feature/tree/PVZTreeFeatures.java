package com.hungteen.pvz.common.world.feature.tree;

import com.hungteen.pvz.common.block.PVZBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-24 12:52
 *
 * see default minecraft code at {@link net.minecraft.data.worldgen.features.TreeFeatures}.
 **/
public class PVZTreeFeatures {

    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> NUT_TREE = FeatureUtils.register("nut", Feature.TREE,
            new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(PVZBlocks.NUT_LOG.get()),
                    new StraightTrunkPlacer(5, 6, 3),
                    BlockStateProvider.simple(PVZBlocks.NUT_LEAVES.get()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 4),
                    new TwoLayersFeatureSize(1, 0, 2)
            ).build()
    );

}
