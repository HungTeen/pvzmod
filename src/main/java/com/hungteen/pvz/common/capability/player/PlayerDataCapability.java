package com.hungteen.pvz.common.capability.player;

import net.minecraft.world.entity.player.Player;

public class PlayerDataCapability implements IPlayerDataCapability{

	private PlayerDataManager playerDataManager = null;
	
	@Override
	public void init(Player pl) {
		if (playerDataManager == null) {
			playerDataManager = new PlayerDataManager(pl);
		}
	}

	@Override
	public PlayerDataManager getPlayerData() {
		return playerDataManager;
	}
}
