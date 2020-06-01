package com.hungteen.pvzmod.capability.interfaces;

import com.hungteen.pvzmod.capability.data.PlayerDataManager;

import net.minecraft.entity.player.EntityPlayer;

public interface ICapabilityPVZPlayer {

	public void init(EntityPlayer pl);

	public PlayerDataManager getPlayerData();
}
