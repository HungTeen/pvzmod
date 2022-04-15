package com.hungteen.pvz.common.impl.type;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.ICardType;
import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.common.item.PVZItems;
import com.hungteen.pvz.common.item.PVZRarities;
import com.hungteen.pvz.common.tag.PVZItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-15 13:18
 **/
public class CardTypes {

    private static final List<ICardType> CARD_TYPES = new ArrayList<>();

    /**
     * Overworld.
     */
    public static final ICardType SAPLING = new CardType(
            "sapling",
            true,
            Rarity.COMMON,
            () -> PVZItems.SAPLING_CARD.get(),
            () -> PVZItemTags.SAPLING_CARDS,
            () -> PVZItemTags.SAPLING_MATERIALS
    );

    /**
     * Nether.
     */
    public static final ICardType NETHER_WART = new CardType(
            "nether_wart",
            true,
            Rarity.COMMON,
            () -> PVZItems.NETHER_WART_CARD.get(),
            () -> PVZItemTags.NETHER_WART_CARDS,
            () -> PVZItemTags.NETHER_WART_MATERIALS
    );

    /**
     * The End.
     */
    public static final ICardType CHORUS_FRUIT = new CardType(
            "chorus_fruit",
            true,
            Rarity.COMMON,
            () -> PVZItems.CHORUS_FRUIT_CARD.get(),
            () -> PVZItemTags.CHORUS_FRUIT_CARDS,
            () -> PVZItemTags.CHORUS_FRUIT_MATERIALS
    );

    /**
     * The End.
     */
    public static final ICardType UPGRADE = new CardType(
            "upgrade",
            true,
            Rarity.EPIC,
            () -> PVZItems.UPGRADE_CARD.get(),
            () -> PVZItemTags.UPGRADE_CARDS,
            () -> null
    );

    /**
     * Wisdom Tree.
     */
    public static final ICardType WISDOM = new CardType(
            "wisdom",
            false,
            PVZRarities.WISDOM,
            () -> PVZItems.WISDOM_CARD.get(),
            () -> PVZItemTags.WISDOM_CARDS,
            () -> null
    );

    public static class CardType implements ICardType {

        private final String name;
        private final boolean canReplace;
        private final Supplier<Item> cardSuppiler;
        private final Supplier<TagKey<Item>> cardTagSuppiler;
        private final Supplier<TagKey<Item>> materialTagSuppiler;
        private final Rarity rarity;

        public CardType(String name, boolean canReplace, Rarity rarity, Supplier<Item> sup, Supplier<TagKey<Item>> sup1, Supplier<TagKey<Item>> sup2) {
            this.name = name;
            this.canReplace = canReplace;
            this.rarity = rarity;
            this.cardSuppiler = sup;
            this.cardTagSuppiler = sup1;
            this.materialTagSuppiler = sup2;
            CARD_TYPES.add(this);
        }

        /**
         * {@link PVZMod#coreRegister()}
         */
        public static void register(){
            CARD_TYPES.forEach(type -> PVZAPI.get().registerCardType(type));
        }

        @Override
        public Item getTemplateCard() {
            return this.cardSuppiler.get();
        }

        @Override
        public TagKey<Item> getCardTag() {
            return this.cardTagSuppiler.get();
        }

        @Override
        public TagKey<Item> getMaterialTag() {
            return this.materialTagSuppiler.get();
        }

        @Override
        public Rarity getRarity() {
            return rarity;
        }

        @Override
        public boolean isReplaceable() {
            return this.canReplace;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return this.getName();
        }
    }
}
