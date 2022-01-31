package com.hungteen.pvz.api.raid;

import com.google.gson.JsonElement;
import com.hungteen.pvz.api.interfaces.IChallenge;
import net.minecraft.entity.player.ServerPlayerEntity;

public interface IRewardComponent {

	void reward(ServerPlayerEntity player);

	void rewardGlobally(IChallenge challenge);
	
	/**
	 * make sure constructer has no argument, 
	 * and use this method to initiate instance.
	 */
	void readJson(JsonElement json);
}
