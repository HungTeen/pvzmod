package com.hungteen.pvz.common.event.events;

import com.hungteen.pvz.api.types.IPAZType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerLevelUpEvent extends PlayerEvent {

	private final int currentLevel;
	
	public PlayerLevelUpEvent(PlayerEntity player, int lvl) {
		super(player);
		this.currentLevel = lvl;
	}
	
	public int getCurrentLevel() {
		return this.currentLevel;
	}
	
	public static class TreeLevelUpEvent extends PlayerLevelUpEvent {

		public TreeLevelUpEvent(PlayerEntity player, int lvl) {
			super(player, lvl);
		}
		
	}
	
	public static class PAZLevelUpEvent extends PlayerLevelUpEvent {

		public final IPAZType plant;
		
		public PAZLevelUpEvent(PlayerEntity player, IPAZType plant, int lvl) {
			super(player, lvl);
			this.plant = plant;
		}
		
	}

}
