package com.hungteen.pvz.common.advancement.predicate;

import javax.annotation.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.JSONUtils;

public class AmountPredicate {

	public static final AmountPredicate ANY = AmountPredicate.Builder.create().build();
	private final MinMaxBounds.IntBound amount;

	public AmountPredicate() {
		amount = MinMaxBounds.IntBound.ANY;
	}

	public AmountPredicate(MinMaxBounds.IntBound bound) {
		amount = bound;
	}

	public boolean test(ServerPlayerEntity player, int amount) {
		if(this == ANY) return true;
		return this.amount.matches(amount);
	}

	public static AmountPredicate deserialize(@Nullable JsonElement element) {
		if (element != null && !element.isJsonNull()) {
			JsonObject jsonobject = JSONUtils.convertToJsonObject(element, "amount");
			MinMaxBounds.IntBound bound = MinMaxBounds.IntBound.fromJson(jsonobject.get("amount"));
			return new AmountPredicate(bound);
		} else {
			return ANY;
		}
	}

	public JsonElement serialize() {
		if (this == ANY) {
			return JsonNull.INSTANCE;
		} else {
			JsonObject jsonobject = new JsonObject();
			jsonobject.add("dealt", this.amount.serializeToJson());
			return jsonobject;
		}
	}

	public static class Builder {
		private MinMaxBounds.IntBound dealt = MinMaxBounds.IntBound.ANY;

		public static AmountPredicate.Builder create() {
			return new AmountPredicate.Builder();
		}

		public AmountPredicate build() {
			return new AmountPredicate(this.dealt);
		}
	}

}
