package com.hungteen.pvz.advancement.trigger;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.advancement.predicate.AmountPredicate;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
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
	public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
		return new EntityEffectAmountTrigger.Instance(EntityPredicate.deserialize(json.get("entity")), AmountPredicate.deserialize(json.get("amount")));
	}

	public void trigger(ServerPlayerEntity player, Entity entity, int amount) {
		this.func_227070_a_(player.getAdvancements(), (instance) -> {
			return instance.test(player, entity, amount);
		});
	}

	public static class Instance extends CriterionInstance {
		
		private final EntityPredicate entity;
		private final AmountPredicate amount;

		public Instance(EntityPredicate entity, AmountPredicate amount) {
			super(ID);
			this.entity = entity;
			this.amount = amount;
		}

		public boolean test(ServerPlayerEntity player, Entity entity, int amount) {
			return this.entity.test(player, entity) && this.amount.test(player, amount);
		}

		public JsonElement serialize() {
			JsonObject jsonobject = new JsonObject();
			jsonobject.add("entity", this.entity.serialize());
			jsonobject.add("amount", this.amount.serialize());
			return jsonobject;
		}
	}

}
