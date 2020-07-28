package com.hungteen.pvz;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hungteen.pvz.capabilities.CapabilityHandler;
import com.hungteen.pvz.network.PVZPacketHandler;
import com.hungteen.pvz.register.RegistryHandler;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(PVZMod.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PVZMod
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    // Mod ID
	public static final String MOD_ID = "pvz";
	
    public PVZMod() {
    	{//服务端配置文件
    		final Pair<PVZConfig.Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(PVZConfig.Common::new);
    		ModLoadingContext.get().registerConfig(Type.COMMON, specPair.getRight());
    		PVZConfig.COMMON_CONFIG=specPair.getLeft();
    	}
    	{//客户端配置文件s
    		final Pair<PVZConfig.Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(PVZConfig.Client::new);
    		ModLoadingContext.get().registerConfig(Type.CLIENT, specPair.getRight());
    		PVZConfig.CLIENT_CONFIG=specPair.getLeft();
    	}
    	RegistryHandler.init();
    }

    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent ev)
    {
    	CapabilityHandler.registerCapabilities();
    	PVZPacketHandler.init();
    }
    
    
}
