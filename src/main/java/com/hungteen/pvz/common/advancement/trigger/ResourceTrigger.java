package com.hungteen.pvz.common.advancement.trigger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hungteen.pvz.common.advancement.predicate.AmountPredicate;
import com.hungteen.pvz.common.advancement.predicate.StringPredicate;
import com.hungteen.pvz.utils.Util;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 16:50
 **/
public class ResourceTrigger extends SimpleCriterionTrigger<ResourceTrigger.Instance> {

    private static final ResourceLocation ID = Util.prefix("sun_amount");
    public static final ResourceTrigger INSTANCE = new ResourceTrigger();

    public ResourceLocation getId() {
        return ID;
    }

    /**
     * Deserialize a ICriterionInstance of this trigger from the data in the JSON.
     */
    @Override
    protected Instance createInstance(JsonObject json, EntityPredicate.Composite player, DeserializationContext context) {
        AmountPredicate amount = AmountPredicate.deserialize(json.get("amount"));
        StringPredicate type = StringPredicate.deserialize(json.get("type"));
        return new Instance(player, amount, type);
    }

    public void trigger(ServerPlayer player, Resources resource, int amount) {
        this.trigger(player, (instance) -> {
            return instance.test(player, resource, amount);
        });
    }

    public static class Instance extends AbstractCriterionTriggerInstance {

        private final AmountPredicate amount;
        private final StringPredicate type;

        public Instance(EntityPredicate.Composite player, AmountPredicate amount, StringPredicate type) {
            super(ID, player);
            this.amount = amount;
            this.type = type;
        }

        public boolean test(ServerPlayer player, Resources resource, int amount) {
            return this.amount.test(player, amount) && this.type.test(player, resource.toString().toLowerCase());
        }

        @Override
        public JsonObject serializeToJson(SerializationContext context) {
            JsonObject jsonObject = super.serializeToJson(context);
            jsonObject.add("amount", this.amount.serialize());
            jsonObject.add("type", this.type.serialize());
            return jsonObject;
        }

    }
}
