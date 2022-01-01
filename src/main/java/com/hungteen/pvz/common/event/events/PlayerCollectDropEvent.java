package com.hungteen.pvz.common.event.events;

import com.hungteen.pvz.common.entity.misc.drop.SunEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

public class PlayerCollectDropEvent extends PlayerEvent{

	public PlayerCollectDropEvent(PlayerEntity player) {
		super(player);
	}

	/**
     * This event is fired after the player collect sun(collide or use sun collector), but before the player has been added sun num.
     * It can be cancelled, and no further processing will be done.
     */
    @Cancelable
	public static class PlayerCollectSunEvent extends PlayerCollectDropEvent {
		
		protected final SunEntity sun;
		
		public PlayerCollectSunEvent(PlayerEntity player, SunEntity sun) {
			super(player);
			this.sun = sun;
		}

		public SunEntity getSunEntity() {
			return this.sun;
		}
	}
	
}
