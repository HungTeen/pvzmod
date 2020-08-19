package com.hungteen.pvz.event;

import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;


public class OverWorldEvent {

	public static void tick(TickEvent.WorldTickEvent ev) {
		World world = ev.world;
		long totalTime = world.getDayTime();
		int time=(int) (totalTime%24000);
		switch(time) {
		case 1000:{
			
		}
		case 23900:{
			
		}
		}
	}
}
