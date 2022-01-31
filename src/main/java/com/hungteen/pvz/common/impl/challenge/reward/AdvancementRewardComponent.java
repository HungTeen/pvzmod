package com.hungteen.pvz.common.impl.challenge.reward;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.api.interfaces.IChallenge;
import com.hungteen.pvz.api.raid.IRewardComponent;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.entity.player.ServerPlayerEntity;

public class AdvancementRewardComponent implements IRewardComponent {

	public static final String NAME = "advancements";
	private AdvancementRewards reward = AdvancementRewards.EMPTY;
	
	@Override
	public void reward(ServerPlayerEntity player) {
		this.reward.grant(player);
	}

	@Override
	public void rewardGlobally(IChallenge challenge) {
	}

	@Override
	public void readJson(JsonElement json) {
		final JsonObject obj = json.getAsJsonObject();
		if(obj != null) {
			this.reward = AdvancementRewards.deserialize(obj);
		}
	}

}
