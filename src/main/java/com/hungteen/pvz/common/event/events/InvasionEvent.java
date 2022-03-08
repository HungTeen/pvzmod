package com.hungteen.pvz.common.event.events;

import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

public class InvasionEvent extends Event{

	private final Level world;
	
	public InvasionEvent(Level world) {
		this.world = world;
	}

	public Level getWorld() {
		return this.world;
	}
	
	/**
     * This event is fired after the player collect sun(collide or use sun collector), but before the player has been added sun num.
     * It can be cancelled, and no further processing will be done.
     */
    @Cancelable
	public static class InvasionStartEvent extends InvasionEvent {
		
		
		public InvasionStartEvent(Level world) {
			super(world);
		}

	}
	
}
