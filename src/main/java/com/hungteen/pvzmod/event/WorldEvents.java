package com.hungteen.pvzmod.event;

import com.hungteen.pvzmod.registry.PotionRegister;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class WorldEvents {
	
//	@SubscribeEvent
//	public void pvzPotionActive(TickEvent.PlayerTickEvent ev)
//	{
//		boolean isActive=false;
//		if(ev.player.isPotionActive(PotionRegister.COLD_EFFECT)) isActive=true;
//	}
	
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent ev) {
		if (ev.phase != TickEvent.Phase.END || ev.world.isRemote)
			return;
		int dim = ev.world.provider.getDimension();
        //System.out.println(""+dim);
		if (dim == 0) {
//			OverworldEvents.doTickCheck(ev);
		}
	}
}
