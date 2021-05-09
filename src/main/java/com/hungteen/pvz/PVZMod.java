package com.hungteen.pvz;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hungteen.pvz.advancement.AdvancementHandler;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(PVZMod.MOD_ID)
@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class PVZMod
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    // Mod ID
	public static final String MOD_ID = "pvz";
	
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
    	PROXY.init();
    	RegistryHandler.register();
    	AdvancementHandler.init();
    }

	@SubscribeEvent
    public static void setupComplete(FMLLoadCompleteEvent event) {
        PROXY.postInit();
    }
    
}
