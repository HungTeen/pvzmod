package com.hungteen.pvz.common.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.world.invasion.InvasionManager;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID)
public class PVZServerEvents {

	@SubscribeEvent
    public static void serverInit(FMLServerStartingEvent ev) {
    	ServerWorld world = ev.getServer().getLevel(World.OVERWORLD);
    	InvasionManager.syncStartInvasionCache(world);
    }
    
    @SubscribeEvent
    public static void serverShutDown(FMLServerStoppingEvent ev) {
    	ServerWorld world = ev.getServer().getLevel(World.OVERWORLD);
    	InvasionManager.syncEndInvasionCache(world);
    }
    
}
