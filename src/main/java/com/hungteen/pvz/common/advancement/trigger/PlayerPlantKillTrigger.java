package com.hungteen.pvz.common.advancement.trigger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.DamageSourcePredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.EntityPredicate.AndPredicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class PlayerPlantKillTrigger extends AbstractCriterionTrigger<PlayerPlantKillTrigger.Instance> {

	private static final ResourceLocation ID = StringUtil.prefix("player_plant_kill");
	public static final PlayerPlantKillTrigger INSTANCE = new PlayerPlantKillTrigger();

	public ResourceLocation getId() {
		return ID;
	}

	/**
	 * Deserialize a ICriterionInstance of this trigger from the data in the JSON.
	 */
	@Override
	protected Instance createInstance(JsonObject json, AndPredicate player,
			ConditionArrayParser p_230241_3_) {
		return new PlayerPlantKillTrigger.Instance(player, EntityPredicate.fromJson(json.get("entity")), DamageSourcePredicate.fromJson(json.get("killing_blow")));
	}

	public void trigger(ServerPlayerEntity player, Entity entity, DamageSource source) {
		this.trigger(player, (instance) -> {
			return instance.test(player, entity, source);
		});
	}

	public static class Instance extends CriterionInstance {

		private final EntityPredicate entity;
		private final DamageSourcePredicate killingBlow;

		public Instance(EntityPredicate.AndPredicate player, EntityPredicate entity, DamageSourcePredicate killingBlow) {
			super(ID, player);
			this.entity = entity;
			this.killingBlow = killingBlow;
		}

		public boolean test(ServerPlayerEntity player, Entity entity, DamageSource source) {
			return !this.killingBlow.matches(player, source) ? false : this.entity.matches(player, entity);
		}

		public JsonElement func_200288_b() {
			JsonObject jsonobject = new JsonObject();
			jsonobject.add("entity", this.entity.serializeToJson());
			jsonobject.add("killing_blow", this.killingBlow.serializeToJson());
			return jsonobject;
		}
	}

}
