package com.hungteen.pvz.common.advancement.trigger;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonObject;
import com.hungteen.pvz.common.advancement.predicate.AmountPredicate;
import com.hungteen.pvz.common.advancement.predicate.StringPredicate;
import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import com.hungteen.pvz.utils.Util;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.ItemLike;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-19 19:05
 **/
public class SummonCardUseTrigger extends SimpleCriterionTrigger<SummonCardUseTrigger.Instance> {

    private static final ResourceLocation ID = Util.prefix("use_summon_card");
    public static final SummonCardUseTrigger INSTANCE = new SummonCardUseTrigger();

    public ResourceLocation getId() {
        return ID;
    }

    /**
     * Deserialize a ICriterionInstance of this trigger from the data in the JSON.
     */
    @Override
    protected SummonCardUseTrigger.Instance createInstance(JsonObject json, EntityPredicate.Composite player, DeserializationContext context) {
        ItemPredicate holdingItem = ItemPredicate.fromJson(json.get("holding_item"));
        ItemPredicate realItem = ItemPredicate.fromJson(json.get("real_item"));
        return new SummonCardUseTrigger.Instance(player, holdingItem, realItem);
    }

    public void trigger(ServerPlayer player, ItemStack holdingStack, ItemStack realStack) {
        this.trigger(player, (instance) -> {
            return instance.test(player, holdingStack, realStack);
        });
    }

    public static class Instance extends AbstractCriterionTriggerInstance {

        private final ItemPredicate holdingItem;
        private final ItemPredicate realItem;

        public Instance(EntityPredicate.Composite player, ItemPredicate holdingItem, ItemPredicate realItem) {
            super(ID, player);
            this.holdingItem = holdingItem;
            this.realItem = realItem;
        }

        public boolean test(ServerPlayer player, ItemStack holdingStack, ItemStack realStack) {
            return this.holdingItem.matches(holdingStack) && this.realItem.matches(realStack);
        }

        public static SummonCardUseTrigger.Instance usedCard() {
            return new SummonCardUseTrigger.Instance(EntityPredicate.Composite.ANY, ItemPredicate.ANY, ItemPredicate.ANY);
        }

        public static SummonCardUseTrigger.Instance usedCard(SummonCardItem holdItem, SummonCardItem realItem) {
            return new SummonCardUseTrigger.Instance(EntityPredicate.Composite.ANY,
                    new ItemPredicate(null, ImmutableSet.of(holdItem.asItem()), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, EnchantmentPredicate.NONE, EnchantmentPredicate.NONE, (Potion)null, NbtPredicate.ANY),
                    new ItemPredicate(null, ImmutableSet.of(realItem.asItem()), MinMaxBounds.Ints.ANY, MinMaxBounds.Ints.ANY, EnchantmentPredicate.NONE, EnchantmentPredicate.NONE, (Potion)null, NbtPredicate.ANY)
            );
        }

        @Override
        public JsonObject serializeToJson(SerializationContext context) {
            JsonObject jsonObject = super.serializeToJson(context);
            jsonObject.add("holding_item", this.holdingItem.serializeToJson());
            jsonObject.add("real_item", this.realItem.serializeToJson());
            return jsonObject;
        }

    }
}
