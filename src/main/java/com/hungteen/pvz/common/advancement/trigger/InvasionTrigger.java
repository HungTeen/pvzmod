package com.hungteen.pvz.common.advancement.trigger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate.AndPredicate;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.loot.ConditionArrayParser;
import net.minecraft.util.ResourceLocation;

public class InvasionTrigger extends AbstractCriterionTrigger<InvasionTrigger.Instance> {

    private static final ResourceLocation ID = StringUtil.prefix("invasion");
    public static final InvasionTrigger INSTANCE = new InvasionTrigger();

    public ResourceLocation getId() {
        return ID;
    }

    /**
     * Deserialize a ICriterionInstance of this trigger from the data in the JSON.
     */
    @Override
    protected Instance createInstance(JsonObject json, AndPredicate player, ConditionArrayParser p_230241_3_) {
        return new Instance(player);
    }

    public void trigger(ServerPlayerEntity player) {
        this.trigger(player, (instance) -> {
            return instance.test(player);
        });
    }

    public static class Instance extends CriterionInstance {

        public Instance(AndPredicate player) {
            super(ID, player);
        }

        public boolean test(ServerPlayerEntity player) {
            return true;
        }

        public JsonElement func_200288_b() {
            JsonObject jsonobject = new JsonObject();
            return jsonobject;
        }
    }

}