package com.hungteen.pvz;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hungteen.pvz.register.EntitySpawnRegister;
import com.hungteen.pvz.register.RegistryHandler;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(PVZMod.MOD_ID)
@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID)
public class PVZMod
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    // Mod ID
	public static final String MOD_ID = "pvz";
	
	public static CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
	
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
    }

    @SubscribeEvent
    public static void serverInit(FMLServerStartingEvent ev) {
    	ServerWorld world = ev.getServer().getWorld(DimensionType.OVERWORLD);
    	EntitySpawnRegister.updateEventSpawns(world);
    }
    
    @SubscribeEvent
    public static void serverShutDown(FMLServerStoppingEvent ev) {
//    	System.out.println("shut down!!!");
    	ServerWorld world = ev.getServer().getWorld(DimensionType.OVERWORLD);
    	EntitySpawnRegister.removeEventSpawns(world);
    }
    
    @SubscribeEvent
    public static void setupComplete(FMLLoadCompleteEvent event) {
        PROXY.postInit();
    }
    
}
