package com.hungteen.pvz.common.advancement.trigger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.common.advancement.predicate.AmountPredicate;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.EntityPredicate.AndPredicate;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.util.ResourceLocation;

public class TreeLevelTrigger extends AbstractCriterionTrigger<TreeLevelTrigger.Instance> {

	private static final ResourceLocation ID = StringUtil.prefix("tree_level");
	public static final TreeLevelTrigger INSTANCE = new TreeLevelTrigger();
	
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
		return new Instance(player, amount);
	}

	public void trigger(ServerPlayerEntity player, int amount) {
		this.trigger(player, (instance) -> {
			return instance.test(player, amount);
		});
	}

	public static class Instance extends CriterionInstance {
		private final AmountPredicate amount;

		public Instance(EntityPredicate.AndPredicate player, AmountPredicate amount) {
			super(ID, player);
			this.amount = amount;
		}

		public boolean test(ServerPlayerEntity player, int amount) {
			return this.amount.test(player, amount);
		}

		public JsonElement func_200288_b() {
			JsonObject jsonobject = new JsonObject();
			jsonobject.add("amount", this.amount.serialize());
			return jsonobject;
		}
	}

}