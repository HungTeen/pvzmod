package com.hungteen.pvz.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.world.invasion.OverworldInvasion;

import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID)
public class PVZWorldEvents {

	@SubscribeEvent
	public static void onWorldTick(TickEvent.WorldTickEvent ev) {
		if (ev.phase != TickEvent.Phase.END || ev.world.isClientSide) {
			return;
		}
		if(ev.world.dimension() == World.OVERWORLD) {
			OverworldInvasion.tick(ev);
		}
	}
	
}
