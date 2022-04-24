package com.hungteen.pvz.common.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-24 13:42
 *
 * see at {@link VegetationPlacements}.
 **/
public class PVZPlacements {

    public static final Holder<PlacedFeature> TREES_NUT = PlacementUtils.register(
            "trees_nut",
            PVZFeatures.TREES_NUT,
            VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1))
    );

}
