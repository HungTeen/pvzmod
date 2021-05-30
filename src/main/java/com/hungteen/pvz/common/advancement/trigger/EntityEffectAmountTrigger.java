package com.hungteen.pvz.common.advancement.trigger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.common.advancement.predicate.AmountPredicate;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.EntityPredicate.AndPredicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.util.ResourceLocation;

public class EntityEffectAmountTrigger extends AbstractCriterionTrigger<EntityEffectAmountTrigger.Instance> {

	private static final ResourceLocation ID = StringUtil.prefix("entity_effect_amount");
	public static final EntityEffectAmountTrigger INSTANCE = new EntityEffectAmountTrigger();
	
	public ResourceLocation getId() {
		return ID;
	}

	/**
	 * Deserialize a ICriterionInstance of this trigger from the data in the JSON.
	 */
	@Override
	protected Instance createInstance(JsonObject json, AndPredicate player,
			ConditionArrayParser p_230241_3_) {
		return new EntityEffectAmountTrigger.Instance(player, EntityPredicate.fromJson(json.get("entity")), AmountPredicate.deserialize(json.get("amount")));
	}

	public void trigger(ServerPlayerEntity player, Entity entity, int amount) {
		this.trigger(player, (instance) -> {
			return instance.test(player, entity, amount);
		});
	}

	public static class Instance extends CriterionInstance {
		
		private final EntityPredicate entity;
		private final AmountPredicate amount;

		public Instance(EntityPredicate.AndPredicate player, EntityPredicate entity, AmountPredicate amount) {
			super(ID, player);
			this.entity = entity;
			this.amount = amount;
		}

		public boolean test(ServerPlayerEntity player, Entity entity, int amount) {
			return this.entity.matches(player, entity) && this.amount.test(player, amount);
		}

		public JsonElement func_200288_b() {
			JsonObject jsonobject = new JsonObject();
			jsonobject.add("entity", this.entity.serializeToJson());
			jsonobject.add("amount", this.amount.serialize());
			return jsonobject;
		}
	}

}
