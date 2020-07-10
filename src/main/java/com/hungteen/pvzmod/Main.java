package com.hungteen.pvzmod;

import com.hungteen.pvzmod.event.OverworldEvents;
import com.hungteen.pvzmod.registry.EntitySpawnRegister;
import com.hungteen.pvzmod.registry.RegistryHandler;
import com.hungteen.pvzmod.registry.TileEntityRegister;
import com.hungteen.pvzmod.util.Reference;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MODID,
     name=Reference.NAME,
     version=Reference.VERSION,
     acceptedMinecraftVersions = Reference.ACCEPTED_VERSION
)
public class Main {
	
	@Instance(Reference.MODID)
	public static Main instance;
	
	@SidedProxy(clientSide=Reference.CLIENT_PROXY,serverSide=Reference.COMMON_PROXY)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		proxy.preInit(event);
		new TileEntityRegister(event);
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		proxy.init(event);
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}
	
	@EventHandler
	public static void serverInit(FMLServerStartingEvent event)
	{
		RegistryHandler.serverRegistries(event);
		EntitySpawnRegister.updateEntitySpawn(event.getServer().getWorld(0));
	}
}
