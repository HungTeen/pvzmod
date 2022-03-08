package com.hungteen.pvz.common.event;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.world.invasion.InvasionManager;
import com.hungteen.pvz.common.world.challenge.ChallengeManager;

import net.minecraft.world.level.Level;
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
		ChallengeManager.tickChallenges(ev.world);
		if(ev.world.dimension() == Level.OVERWORLD) {
			InvasionManager.tick(ev);
		}
	}
	
}
