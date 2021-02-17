package com.hungteen.pvz.advancement.trigger;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.advancement.predicate.AmountPredicate;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;

public class PlayerPlacePlantTrigger extends AbstractCriterionTrigger<PlayerPlacePlantTrigger.Instance> {

	private static final ResourceLocation ID = StringUtil.prefix("player_place_plant");
	public static final PlayerPlacePlantTrigger INSTANCE = new PlayerPlacePlantTrigger();
	
	public ResourceLocation getId() {
		return ID;
	}

	/**
	 * Deserialize a ICriterionInstance of this trigger from the data in the JSON.
	 */
	public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
		return new PlayerPlacePlantTrigger.Instance(AmountPredicate.deserialize(json.get("amount")));
	}

	public void trigger(ServerPlayerEntity player, int amount) {
		this.func_227070_a_(player.getAdvancements(), (instance) -> {
			return instance.test(player, amount);
		});
	}

	public static class Instance extends CriterionInstance {
		
		private final AmountPredicate amount;

		public Instance(AmountPredicate amount) {
			super(ID);
			this.amount = amount;
		}

		public boolean test(ServerPlayerEntity player, int amount) {
			return this.amount.test(player, amount);
		}

		public JsonElement serialize() {
			JsonObject jsonobject = new JsonObject();
			jsonobject.add("amount", this.amount.serialize());
			return jsonobject;
		}
	}

}