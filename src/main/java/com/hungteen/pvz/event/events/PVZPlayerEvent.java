package com.hungteen.pvz.event.events;

import com.hungteen.pvz.entity.drop.DropEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PVZPlayerEvent extends PlayerEvent {
	
	public PVZPlayerEvent(PlayerEntity player) {
		super(player);
	}

	public static class PlayerCollectDropEvent extends PVZPlayerEvent{

		private final DropEntity drop;
		
		public PlayerCollectDropEvent(PlayerEntity player, DropEntity drop) {
			super(player);
			this.drop = drop;
		}
		
		public DropEntity getDropEntity() {return this.drop;}
		
	}
	
}
