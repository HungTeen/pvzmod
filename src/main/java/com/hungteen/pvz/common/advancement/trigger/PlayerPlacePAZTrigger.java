package com.hungteen.pvz.common.advancement.trigger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.common.advancement.predicate.StringPredicate;
import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.EntityPredicate.AndPredicate;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.util.ResourceLocation;

public class PlayerPlacePAZTrigger extends AbstractCriterionTrigger<PlayerPlacePAZTrigger.Instance> {

	private static final ResourceLocation ID = StringUtil.prefix("player_place_paz");
	public static final PlayerPlacePAZTrigger INSTANCE = new PlayerPlacePAZTrigger();
	
	public ResourceLocation getId() {
		return ID;
	}

	/**
	 * Deserialize a ICriterionInstance of this trigger from the data in the JSON.
	 */
	@Override
	protected Instance createInstance(JsonObject json, AndPredicate player,
			ConditionArrayParser p_230241_3_) {
		return new PlayerPlacePAZTrigger.Instance(player, StringPredicate.deserialize(json.get("place_type")), StringPredicate.deserialize(json.get("paz_type")));
	}

	public void trigger(ServerPlayerEntity player, String placeType, String pazType) {
		this.trigger(player, (instance) -> {
			return instance.test(player, placeType, pazType);
		});
	}

	public static class Instance extends CriterionInstance {
		
		private final StringPredicate placeType;
		private final StringPredicate pazType;

		public Instance(EntityPredicate.AndPredicate player, StringPredicate placeType, StringPredicate pazType) {
			super(ID, player);
			this.placeType = placeType;
			this.pazType = pazType;
		}

		public boolean test(ServerPlayerEntity player, String placeType, String pazType) {
			return this.placeType.test(player, placeType) && this.pazType.test(player, pazType);
		}

		public JsonElement func_200288_b() {
			JsonObject jsonobject = new JsonObject();
			jsonobject.addProperty("place_type", this.placeType.serialize());
			jsonobject.addProperty("paz_type", this.pazType.serialize());
			return jsonobject;
		}
	}

	public enum PlaceTypes{
		PLANT,
		UPGRADE,
		ZOMBIE,
	}

}