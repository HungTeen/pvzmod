package com.hungteen.pvz.common.event.events;

import com.hungteen.pvz.common.core.PlantType;

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
	
	public static class PlantLevelUpEvent extends PlayerLevelUpEvent {

		public final PlantType plant;
		
		public PlantLevelUpEvent(PlayerEntity player, PlantType plant, int lvl) {
			super(player, lvl);
			this.plant = plant;
		}
		
	}

}
