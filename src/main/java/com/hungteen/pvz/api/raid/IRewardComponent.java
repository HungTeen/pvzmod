package com.hungteen.pvz.api.raid;

import com.google.gson.JsonElement;
import com.hungteen.pvz.api.interfaces.IChallenge;
import net.minecraft.server.level.ServerPlayer;

public interface IRewardComponent {

	void reward(ServerPlayer player);

	void rewardGlobally(IChallenge challenge);
	
	/**
	 * make sure constructer has no argument, 
	 * and use this method to initiate instance.
	 */
	void readJson(JsonElement json);
}
