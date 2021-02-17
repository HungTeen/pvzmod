package com.hungteen.pvz.advancement.trigger;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.DamageSourcePredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
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
	public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
		return new PlayerPlantKillTrigger.Instance(EntityPredicate.deserialize(json.get("entity")), DamageSourcePredicate.deserialize(json.get("killing_blow")));
	}

	public void trigger(ServerPlayerEntity player, Entity entity, DamageSource source) {
		this.func_227070_a_(player.getAdvancements(), (instance) -> {
			return instance.test(player, entity, source);
		});
	}

	public static class Instance extends CriterionInstance {

		private final EntityPredicate entity;
		private final DamageSourcePredicate killingBlow;

		public Instance(EntityPredicate entity, DamageSourcePredicate killingBlow) {
			super(ID);
			this.entity = entity;
			this.killingBlow = killingBlow;
		}

		public boolean test(ServerPlayerEntity player, Entity entity, DamageSource source) {
			return !this.killingBlow.test(player, source) ? false : this.entity.test(player, entity);
		}

		public JsonElement serialize() {
			JsonObject jsonobject = new JsonObject();
			jsonobject.add("entity", this.entity.serialize());
			jsonobject.add("killing_blow", this.killingBlow.serialize());
			return jsonobject;
		}
	}

}
