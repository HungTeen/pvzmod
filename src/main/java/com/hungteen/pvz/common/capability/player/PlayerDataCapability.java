package com.hungteen.pvz.common.capability.player;

import net.minecraft.entity.player.PlayerEntity;

public class PlayerDataCapability implements IPlayerDataCapability{

	private PlayerDataManager playerDataManager = null;
	
	@Override
	public void init(PlayerEntity pl) {
		if (playerDataManager == null) {
			playerDataManager = new PlayerDataManager(pl);
		}
	}

	@Override
	public PlayerDataManager getPlayerData() {
		return playerDataManager;
	}
}
