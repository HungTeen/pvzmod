package com.hungteen.pvzmod.capability.handler;

import com.hungteen.pvzmod.capability.data.PlayerDataManager;
import com.hungteen.pvzmod.capability.interfaces.ICapabilityPVZPlayer;

import net.minecraft.entity.player.EntityPlayer;

public class PVZPlayerCapability implements ICapabilityPVZPlayer{

	private PlayerDataManager playerDataManager = null;
	
	@Override
	public void init(EntityPlayer pl) {
		if (playerDataManager == null) {
			playerDataManager = new PlayerDataManager(pl);
		}
	}

	@Override
	public PlayerDataManager getPlayerData() {
		return playerDataManager;
	}

}
