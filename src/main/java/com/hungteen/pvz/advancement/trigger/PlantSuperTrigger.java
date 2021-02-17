package com.hungteen.pvz.advancement.trigger;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;

public class PlantSuperTrigger extends AbstractCriterionTrigger<PlantSuperTrigger.Instance> {

	private static final ResourceLocation ID = StringUtil.prefix("plant_super");
	public static final PlantSuperTrigger INSTANCE = new PlantSuperTrigger();
	
	public ResourceLocation getId() {
		return ID;
	}

	/**
	 * Deserialize a ICriterionInstance of this trigger from the data in the JSON.
	 */
	public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
		return new PlantSuperTrigger.Instance(EntityPredicate.deserialize(json.get("entity")));
	}

	public void trigger(ServerPlayerEntity player, Entity entity) {
		this.func_227070_a_(player.getAdvancements(), (instance) -> {
			return instance.test(player, entity);
		});
	}

	public static class Instance extends CriterionInstance {

		private final EntityPredicate entity;

		public Instance(EntityPredicate entity) {
			super(ID);
			this.entity = entity;
		}

		public boolean test(ServerPlayerEntity player, Entity entity) {
			return this.entity.test(player, entity);
		}

		public JsonElement serialize() {
			JsonObject jsonobject = new JsonObject();
			jsonobject.add("entity", this.entity.serialize());
			return jsonobject;
		}
	}

}