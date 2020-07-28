package com.hungteen.pvz.capabilities.player;

import net.minecraft.entity.player.PlayerEntity;

public interface IPlayerDataCapability{

	public void init(PlayerEntity pl);

	public PlayerDataManager getPlayerData();
}
