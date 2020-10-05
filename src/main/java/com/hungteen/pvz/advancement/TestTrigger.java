package com.hungteen.pvz.advancement;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.util.ResourceLocation;

public class TestTrigger implements ICriterionTrigger<ICriterionInstance>{

	@Override
	public ResourceLocation getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addListener(PlayerAdvancements playerAdvancementsIn, Listener<ICriterionInstance> listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(PlayerAdvancements playerAdvancementsIn, Listener<ICriterionInstance> listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ICriterionInstance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
		// TODO Auto-generated method stub
		return null;
	}

}
