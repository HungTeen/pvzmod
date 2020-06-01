package com.hungteen.pvzmod.event;

import com.hungteen.pvzmod.util.Reference;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationEvents {

	@EventBusSubscriber(modid = Reference.MODID)
	public static class ConfigSyncHandler {
	    @SubscribeEvent
	    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
	        if (event.getModID().equals(Reference.MODID)) {
	            ConfigManager.sync(Reference.MODID, Config.Type.INSTANCE);
	        }
	    }
	}
}
