package com.hungteen.pvz;

import com.hungteen.pvz.client.ClientProxy;
import com.hungteen.pvz.common.CommonProxy;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.registry.RegistryHandler;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 19:57
 **/
@Mod(PVZMod.MOD_ID)
@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PVZMod {

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    // Mod ID.
    public static final String MOD_ID = "pvz";
    // Mod Version.
    public static final String MOD_VERSION = "0.6.1";
    // Proxy of Server and Client.
    public static CommonProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public PVZMod() {
//        {
//            final Pair<PVZConfig.Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(PVZConfig.Common::new);
//            ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, specPair.getRight());
//            PVZConfig.COMMON_CONFIG = specPair.getLeft();
//        }
//        {
//            final Pair<PVZConfig.Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(PVZConfig.Client::new);
//            ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, specPair.getRight());
//            PVZConfig.CLIENT_CONFIG = specPair.getLeft();
//        }

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
//        RegistryHandler.deferredRegister(modBus);

        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
//        forgeBus.addListener(EventPriority.NORMAL, GenStructures::addDimensionalSpacing);
//        forgeBus.addListener(EventPriority.HIGH, BiomeRegister::biomeModification);
//        forgeBus.addListener(EventPriority.NORMAL, PVZDataPackManager::addReloadListenerEvent);
//
//        AdvancementHandler.init();
//        RegistryHandler.coreRegister();

        PROXY.init();
    }

    @SubscribeEvent
    public static void setUpComplete(FMLLoadCompleteEvent event) {
        PROXY.postInit();
    }

    @SubscribeEvent
    public static void setUp(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            PROXY.setUp();
//            RegistryHandler.setUp(event);
//            OriginBlock.updateRadiationMap();
        });
    }

    @SubscribeEvent
    public static void setUpClient(FMLClientSetupEvent event) {
        PROXY.setUpClient();
    }

}
