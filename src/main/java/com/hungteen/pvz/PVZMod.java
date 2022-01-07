package com.hungteen.pvz;

import com.hungteen.pvz.client.ClientProxy;
import com.hungteen.pvz.common.CommonProxy;
import com.hungteen.pvz.common.advancement.AdvancementHandler;
import com.hungteen.pvz.common.block.cubes.OriginBlock;
import com.hungteen.pvz.common.datapack.DataPackRegister;
import com.hungteen.pvz.common.world.gen.GenStructures;
import com.hungteen.pvz.common.world.raid.CRaidRegister;
import com.hungteen.pvz.register.BiomeRegister;
import com.hungteen.pvz.register.CoreRegister;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(PVZMod.MOD_ID)
@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class PVZMod {

    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    // Mod ID
	public static final String MOD_ID = "pvz";
	// Mod Version
	public static final String MOD_VERSION = "0.6";
	// Proxy of Server and Client.
	public static CommonProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
	
    public PVZMod() {
    	{
    		final Pair<PVZConfig.Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(PVZConfig.Common::new);
    		ModLoadingContext.get().registerConfig(Type.COMMON, specPair.getRight());
    		PVZConfig.COMMON_CONFIG = specPair.getLeft();
    	}
    	{
    		final Pair<PVZConfig.Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(PVZConfig.Client::new);
    		ModLoadingContext.get().registerConfig(Type.CLIENT, specPair.getRight());
    		PVZConfig.CLIENT_CONFIG = specPair.getLeft();
    	}
    	
    	IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
    	RegistryHandler.deferredRegister(modBus);
    	
    	IEventBus forgeBus = MinecraftForge.EVENT_BUS;
    	forgeBus.addListener(EventPriority.NORMAL, GenStructures::addDimensionalSpacing);
    	forgeBus.addListener(EventPriority.HIGH, BiomeRegister::biomeModification);
		forgeBus.addListener(EventPriority.NORMAL, DataPackRegister::addReloadListenerEvent);
    	
    	AdvancementHandler.init();
    	CoreRegister.register();
		CRaidRegister.register();

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
            RegistryHandler.setUp(event);
			OriginBlock.updateRadiationMap();
		});
    }
	
	@SubscribeEvent
	public static void setUpClient(FMLClientSetupEvent event) {
        PROXY.setUpClient();
    }
	
}
