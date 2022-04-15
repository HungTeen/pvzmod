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

    public static final IRankType WHITE = new RankType(
            "white",
            100,
            2,
            () -> PVZItemTags.WHITE_MATERIALS
    );

    public static final IRankType GOLD = new RankType(
            "gold",
            90,
            2,
            () -> PVZItemTags.GOLD_MATERIALS
    );

    public static final IRankType BLUE = new RankType(
            "blue",
            80,
            3,
            () -> PVZItemTags.BLUE_MATERIALS
    );

    public static final IRankType BLACK = new RankType(
            "black",
            70,
            5,
            () -> PVZItemTags.BLACK_MATERIALS
    );

    public static final IRankType MEGA = new RankType(
            "mega",
            50,
            10,
            () -> null
    );

    public static class RankType implements IRankType {

        private final String name;
        private final Supplier<TagKey<Item>> materialSuppiler;
        private final int weight;
        private final int price;

        public RankType(String name, int weight, int price, Supplier<TagKey<Item>> sup2) {
            this.name = name;
            this.weight = weight;
            this.price = price;
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
        public TagKey<Item> getMaterial() {
            return this.materialSuppiler.get();
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
