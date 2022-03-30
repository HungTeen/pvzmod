package com.hungteen.pvz.common.impl.type;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
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
 * @create: 2022-03-09 20:48
 **/
public class RankTypes {

    private static final List<IRankType> RANK_TYPES = new ArrayList<>();

//    public static final IRankType GRAY = new RankType(
//            "gray",
//            15,
//            100,
//            1,
//            () -> PVZItems.GRAY_CARD.get(),
//            () -> PVZItemTags.GRAY_CARDS,
//            () -> PVZItemTags.GRAY_MATERIALS
//    );

    public static final IRankType WHITE = new RankType(
            "white",
            14,
            100,
            1,
            Rarity.COMMON,
            () -> PVZItems.WHITE_CARD.get(),
            () -> PVZItemTags.WHITE_CARDS,
            () -> PVZItemTags.WHITE_MATERIALS
    );

    public static final IRankType GREEN = new RankType(
            "green",
            13,
            95,
            2,
            PVZRarities.UNUSUAL,
            () -> PVZItems.GREEN_CARD.get(),
            () -> PVZItemTags.GREEN_CARDS,
            () -> PVZItemTags.GREEN_MATERIALS
    );

    public static final IRankType BLUE = new RankType(
            "blue",
            12,
            90,
            2,
            Rarity.RARE,
            () -> PVZItems.BLUE_CARD.get(),
            () -> PVZItemTags.BLUE_CARDS,
            () -> PVZItemTags.BLUE_MATERIALS
    );

    public static final IRankType PURPLE = new RankType(
            "purple",
            11,
            85,
            2,
            Rarity.EPIC,
            () -> PVZItems.PURPLE_CARD.get(),
            () -> PVZItemTags.PURPLE_CARDS,
            () -> PVZItemTags.PURPLE_MATERIALS
    );

//    public static final IRankType GOLD = new RankType(
//            "gold",
//            10,
//            80,
//            3,
//            () -> PVZItems.GOLD_CARD.get(),
//            () -> PVZItemTags.GOLD_CARDS,
//            () -> PVZItemTags.GOLD_MATERIALS
//    );
//
//    public static final IRankType RED = new RankType(
//            "red",
//            9,
//            75,
//            3,
//            () -> PVZItems.RED_CARD.get(),
//            () -> PVZItemTags.RED_CARDS,
//            () -> PVZItemTags.RED_MATERIALS
//    );
//
//    public static final IRankType BLACK = new RankType(
//            "black",
//            8,
//            70,
//            4,
//            () -> PVZItems.BLACK_CARD.get(),
//            () -> PVZItemTags.BLACK_CARDS,
//            () -> PVZItemTags.BLACK_MATERIALS
//    );

    public static final IRankType MEGA = new RankType(
            "mega",
            5,
            50,
            10,
            PVZRarities.MEGA,
            () -> PVZItems.MEGA_CARD.get(),
            () -> null,
            () -> null
    );

    public static class RankType implements IRankType {

        private final String name;
        private final Supplier<Item> cardSuppiler;
        private final Supplier<TagKey<Item>> cardTagSuppiler;
        private final Supplier<TagKey<Item>> materialSuppiler;
        private final Rarity rarity;
        private final int enchantPoint;
        private final int weight;
        private final int price;

        public RankType(String name, int enchantPoint, int weight, int price, Rarity rarity, Supplier<Item> sup, Supplier<TagKey<Item>> sup1, Supplier<TagKey<Item>> sup2) {
            this.name = name;
            this.enchantPoint = enchantPoint;
            this.weight = weight;
            this.price = price;
            this.rarity = rarity;
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
        public Rarity getRarity() {
            return rarity;
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
        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

}
