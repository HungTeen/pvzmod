package com.hungteen.pvz.common.advancement.trigger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.common.advancement.predicate.AmountPredicate;
import com.hungteen.pvz.common.advancement.predicate.StringPredicate;
import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate.AndPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.loot.ConditionArrayParser;

public class SlotMachineTrigger extends AbstractCriterionTrigger<SlotMachineTrigger.Instance> {

	private static final ResourceLocation ID = StringUtil.prefix("slot_machine");
	public static final SlotMachineTrigger INSTANCE = new SlotMachineTrigger();
	
	public ResourceLocation getId() {
		return ID;
	}

	/**
	 * Deserialize a ICriterionInstance of this trigger from the data in the JSON.
	 */
	@Override
	protected Instance createInstance(JsonObject json, AndPredicate player,
			ConditionArrayParser p_230241_3_) {
		AmountPredicate amount = AmountPredicate.deserialize(json.get("amount"));
		StringPredicate type = StringPredicate.deserialize(json.get("type"));
		return new Instance(player, amount, type);
	}

	public void trigger(ServerPlayer player, int amount, String slotType) {
		this.trigger(player, (instance) -> {
			return instance.test(player, amount, slotType);
		});
	}

	public static class Instance extends CriterionInstance {
		private final AmountPredicate amount;
		private final StringPredicate type;

		public Instance(AndPredicate player, AmountPredicate amount, StringPredicate type) {
			super(ID, player);
			this.amount = amount;
			this.type = type;
		}

		public boolean test(ServerPlayer player, int amount, String type) {
			return this.amount.test(player, amount) && this.type.test(player, type);
		}

		public JsonElement func_200288_b() {
			JsonObject jsonobject = new JsonObject();
			jsonobject.add("amount", this.amount.serialize());
			jsonobject.addProperty("type", this.type.serialize());
			return jsonobject;
		}
	}

}
