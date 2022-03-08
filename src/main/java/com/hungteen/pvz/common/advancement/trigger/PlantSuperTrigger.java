package com.hungteen.pvz.common.advancement.trigger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.EntityPredicate.AndPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.loot.ConditionArrayParser;

public class PlantSuperTrigger extends AbstractCriterionTrigger<PlantSuperTrigger.Instance> {

	private static final ResourceLocation ID = StringUtil.prefix("plant_super");
	public static final PlantSuperTrigger INSTANCE = new PlantSuperTrigger();
	
	public ResourceLocation getId() {
		return ID;
	}

	/**
	 * Deserialize a ICriterionInstance of this trigger from the data in the JSON.
	 */
	@Override
	protected Instance createInstance(JsonObject json, AndPredicate player,
			ConditionArrayParser p_230241_3_) {
		return new PlantSuperTrigger.Instance(player, EntityPredicate.fromJson(json.get("entity")));
	}

	public void trigger(ServerPlayer player, Entity entity) {
		this.trigger(player, (instance) -> {
			return instance.test(player, entity);
		});
	}

	public static class Instance extends CriterionInstance {

		private final EntityPredicate entity;

		public Instance(EntityPredicate.AndPredicate player, EntityPredicate entity) {
			super(ID, player);
			this.entity = entity;
		}

		public boolean test(ServerPlayer player, Entity entity) {
			return this.entity.matches(player, entity);
		}

		public JsonElement func_200288_b() {
			JsonObject jsonobject = new JsonObject();
			jsonobject.add("entity", this.entity.serializeToJson());
			return jsonobject;
		}
	}

}