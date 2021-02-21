package com.hungteen.pvz.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.command.PVZCommandHandler;
import com.hungteen.pvz.register.EntitySpawnRegister;

import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID)
public class PVZServerEvents {

	@SubscribeEvent
    public static void serverInit(FMLServerStartingEvent ev) {
		PVZCommandHandler.init(ev);
    	ServerWorld world = ev.getServer().getWorld(DimensionType.OVERWORLD);
    	EntitySpawnRegister.addGameEventSpawns(world);
    }
    
    @SubscribeEvent
    public static void serverShutDown(FMLServerStoppingEvent ev) {
    	ServerWorld world = ev.getServer().getWorld(DimensionType.OVERWORLD);
    	EntitySpawnRegister.removeGameEventSpawns(world);
    }
    
}
