package com.hungteen.pvz.common.world.feature;

import com.hungteen.pvz.PVZMod;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-11 10:24
 **/
public class PVZFeatures {

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
