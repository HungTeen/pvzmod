package com.hungteen.pvz.common.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.world.invasion.InvasionManager;

import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID)
public class PVZServerEvents {

	@SubscribeEvent
    public static void serverInit(FMLServerStartingEvent ev) {
    	ServerLevel world = ev.getServer().getLevel(Level.OVERWORLD);
    	InvasionManager.syncStartInvasionCache(world);
    }
    
    @SubscribeEvent
    public static void serverShutDown(FMLServerStoppingEvent ev) {
    	ServerLevel world = ev.getServer().getLevel(Level.OVERWORLD);
    	InvasionManager.syncEndInvasionCache(world);
    }
    
}
