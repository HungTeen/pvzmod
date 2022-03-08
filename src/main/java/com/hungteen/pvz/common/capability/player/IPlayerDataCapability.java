package com.hungteen.pvz.common.capability.player;

import net.minecraft.world.entity.player.Player;

public interface IPlayerDataCapability{

	public void init(Player pl);

	public PlayerDataManager getPlayerData();
}
