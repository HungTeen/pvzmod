package com.hungteen.pvz.api.raid;

import com.google.gson.JsonElement;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;

public interface IRewardComponent {

	void reward(ServerPlayerEntity player);
	
	void rewardGlobally(World world);
	
	/**
	 * make sure constructer has no argument, 
	 * and use this method to initiate instance.
	 */
	void readJson(JsonElement json);
}
