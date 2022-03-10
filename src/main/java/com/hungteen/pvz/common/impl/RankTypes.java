package com.hungteen.pvz.common.impl;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.type.IRankType;
import com.hungteen.pvz.common.item.PVZItems;
import com.hungteen.pvz.common.tag.PVZItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-09 20:48
 **/
public class RankTypes {

    public static final IRankType GRAY = new RankType(
            "gray",
            15,
            100,
            1,
            () -> PVZItems.GRAY_CARD.get(),
            () -> PVZItemTags.GRAY_CARDS,
            () -> PVZItemTags.GRAY_MATERIALS
    );

    public static final IRankType WHITE = new RankType(
            "white",
            14,
            100,
            1,
            () -> PVZItems.WHITE_CARD.get(),
            () -> PVZItemTags.WHITE_CARDS,
            () -> PVZItemTags.WHITE_MATERIALS
    );

    public static final IRankType GREEN = new RankType(
            "green",
            13,
            95,
            2,
            () -> PVZItems.GREEN_CARD.get(),
            () -> PVZItemTags.GREEN_CARDS,
            () -> PVZItemTags.GREEN_MATERIALS
    );

    public static final IRankType BLUE = new RankType(
            "blue",
            12,
            90,
            2,
            () -> PVZItems.BLUE_CARD.get(),
            () -> PVZItemTags.BLUE_CARDS,
            () -> PVZItemTags.BLUE_MATERIALS
    );

    public static final IRankType PURPLE = new RankType(
            "purple",
            11,
            85,
            2,
            () -> PVZItems.PURPLE_CARD.get(),
            () -> PVZItemTags.PURPLE_CARDS,
            () -> PVZItemTags.PURPLE_MATERIALS
    );

    public static final IRankType GOLD = new RankType(
            "gold",
            10,
            80,
            3,
            () -> PVZItems.GOLD_CARD.get(),
            () -> PVZItemTags.GOLD_CARDS,
            () -> PVZItemTags.GOLD_MATERIALS
    );

    public static final IRankType RED = new RankType(
            "red",
            9,
            75,
            3,
            () -> PVZItems.RED_CARD.get(),
            () -> PVZItemTags.RED_CARDS,
            () -> PVZItemTags.RED_MATERIALS
    );

    public static final IRankType BLACK = new RankType(
            "black",
            8,
            70,
            4,
            () -> PVZItems.BLACK_CARD.get(),
            () -> PVZItemTags.BLACK_CARDS,
            () -> PVZItemTags.BLACK_MATERIALS
    );

    public static final IRankType MEGA = new RankType(
            "mega",
            5,
            50,
            10,
            () -> PVZItems.MEGA_CARD.get(),
            () -> null,
            () -> null
    );

    public static class RankType implements IRankType {

        private static final List<IRankType> RANK_TYPES = new ArrayList<>();
        private final String name;
        private final Supplier<Item> cardSuppiler;
        private final Supplier<TagKey<Item>> cardTagSuppiler;
        private final Supplier<TagKey<Item>> materialSuppiler;
        private final int enchantPoint;
        private final int weight;
        private final int price;

        public RankType(String name, int enchantPoint, int weight, int price, Supplier<Item> sup, Supplier<TagKey<Item>> sup1, Supplier<TagKey<Item>> sup2) {
            this.name = name;
            this.enchantPoint = enchantPoint;
            this.weight = weight;
            this.price = price;
            this.cardSuppiler = sup;
            this.cardTagSuppiler = sup1;
            this.materialSuppiler = sup2;
            RANK_TYPES.add(this);
        }

        /**
         * {@link PVZMod#coreRegister()}
         */
        public static void register(){
            RANK_TYPES.forEach(rank -> PVZAPI.get().registerRankType(rank));
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
        public TagKey<Item> getMaterial() {
            return this.materialSuppiler.get();
        }

        @Override
        public int getEnchantPoint() {
            return this.enchantPoint;
        }

        @Override
        public int getWeight() {
            return weight;
        }

        @Override
        public int getPrice() {
            return this.price;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

}
