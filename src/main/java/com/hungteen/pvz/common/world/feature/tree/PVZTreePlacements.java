package com.hungteen.pvz.common.world.feature.tree;

import com.hungteen.pvz.common.block.PVZBlocks;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-24 13:08
 *
 * see at {@link net.minecraft.data.worldgen.placement.TreePlacements}.
 **/
public class PVZTreePlacements {

    public static final Holder<PlacedFeature> NUT_TREE_CHECKED = PlacementUtils.register(
            "nut_tree_checked",
            PVZTreeFeatures.NUT_TREE,
            PlacementUtils.filteredByBlockSurvival(PVZBlocks.NUT_SAPLING.get())
    );


}
