package com.hungteen.pvz.common.world.feature;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.world.feature.tree.PVZTreePlacements;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.List;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-11 10:24
 *
 * see at {@link net.minecraft.data.worldgen.features.VegetationFeatures}.
 **/
public class PVZFeatures {

    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_NUT = FeatureUtils.register(
            "trees_nut",
            Feature.RANDOM_SELECTOR,
            new RandomFeatureConfiguration(
                    List.of(new WeightedPlacedFeature(TreePlacements.PINE_CHECKED, 0.33333334F)),
                    PVZTreePlacements.NUT_TREE_CHECKED
            )
    );


    /**
     * {@link PVZMod#setUp(FMLCommonSetupEvent)}
     */
    public static void registerFeatures(){
        PVZOrePlacements.registerOrePlacements();
    }

//    /**
//     * for set up Configured Features. <br>
//     * Ensure this is run after the Feature DeferredRegister in ModFeatures.
//     */
//    @SubscribeEvent(priority = EventPriority.LOW)
//    public static void setupStructures(final RegistryEvent.Register<Structure<?>> event) {
//        StructureRegister.setupStructures();
////		setupOres();
//    }

}
