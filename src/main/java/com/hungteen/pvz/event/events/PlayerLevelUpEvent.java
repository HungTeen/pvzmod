package com.hungteen.pvz.event.events;

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

}
