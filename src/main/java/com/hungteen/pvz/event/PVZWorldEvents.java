package com.hungteen.pvz.event;

import com.hungteen.pvz.PVZMod;

import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID)
public class PVZWorldEvents {

	@SubscribeEvent
	public static void onWorldTick(TickEvent.WorldTickEvent ev) {
		if (ev.phase != TickEvent.Phase.END || ev.world.isRemote) {
			return;
		}
		DimensionType type = ev.world.getDimension().getDimension().getType();
		if(type==DimensionType.OVERWORLD) {
			OverWorldEvents.tick(ev);
		}
	}
	
}
