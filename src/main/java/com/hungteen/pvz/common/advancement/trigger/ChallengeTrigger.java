package com.hungteen.pvz.common.advancement.trigger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.common.advancement.predicate.StringPredicate;
import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.EntityPredicate.AndPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.loot.ConditionArrayParser;

public class ChallengeTrigger extends AbstractCriterionTrigger<ChallengeTrigger.Instance> {

    private static final ResourceLocation ID = StringUtil.prefix("challenge");
    public static final ChallengeTrigger INSTANCE = new ChallengeTrigger();

    public ResourceLocation getId() {
        return ID;
    }

    /**
     * Deserialize a ICriterionInstance of this trigger from the data in the JSON.
     */
    @Override
    protected Instance createInstance(JsonObject json, AndPredicate player, ConditionArrayParser p_230241_3_) {
        StringPredicate type = StringPredicate.deserialize(json.get("type"));
        return new Instance(player, type);
    }

    public void trigger(ServerPlayer player, String s) {
        this.trigger(player, (instance) -> {
            return instance.test(player, s);
        });
    }

    public static class Instance extends CriterionInstance {
        private final StringPredicate type;

        public Instance(AndPredicate player, StringPredicate res) {
            super(ID, player);
            this.type = res;
        }

        public boolean test(ServerPlayer player, String s) {
            return this.type.test(player, s);
        }

        public JsonElement func_200288_b() {
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("type", this.type.serialize());
            return jsonobject;
        }
    }

}