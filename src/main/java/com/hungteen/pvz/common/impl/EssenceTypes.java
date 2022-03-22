package com.hungteen.pvz.common.impl;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IEssenceType;
import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.common.item.PVZItems;
import com.hungteen.pvz.common.tag.PVZBlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 22:43
 **/
public class EssenceTypes {

    private static final List<IEssenceType> ESSENCE_TYPES = new ArrayList<>();

    public static final IEssenceType ORIGIN = new EssenceType(
            "origin",
            () -> PVZItems.ORIGIN_ESSENCE.get()
    );

    public static final IEssenceType APPEASE = new EssenceType(
            "appease",
            () -> PVZItems.APPEASE_ESSENCE.get()
    );

    public static final IEssenceType LIGHT = new EssenceType(
            "light",
            () -> PVZItems.LIGHT_ESSENCE.get()
    );

    public static final IEssenceType EXPLOSION = new EssenceType(
            "explosion",
            () -> PVZItems.EXPLOSION_ESSENCE.get()
    );

    public static final IEssenceType DEFENCE = new EssenceType(
            "defence",
            () -> PVZItems.DEFENCE_ESSENCE.get()
    );

    public static final IEssenceType ICE = new EssenceType(
            "ice",
            () -> PVZItems.ICE_ESSENCE.get()
    );

    public static final IEssenceType ENFORCE = new EssenceType(
            "enforce",
            () -> PVZItems.ENFORCE_ESSENCE.get()
    );

//    public static final IEssenceType TOXIC = new EssenceType(
//            "toxic",
//            () -> PVZItems.TOXIC_ESSENCE.get()
//    );

    public static final IEssenceType ASSIST = new EssenceType(
            "assist",
            () -> PVZItems.ASSIST_ESSENCE.get()
    );

    public static final IEssenceType MAGIC = new EssenceType(
            "magic",
            () -> PVZItems.MAGIC_ESSENCE.get()
    );

    public static final IEssenceType FLAME = new EssenceType(
            "flame",
            () -> PVZItems.FLAME_ESSENCE.get()
    );

    public static final IEssenceType SPEAR = new EssenceType(
            "spear",
            () -> PVZItems.SPEAR_ESSENCE.get()
    );

    public static final IEssenceType ARMA = new EssenceType(
            "arma",
            () -> PVZItems.ARMA_ESSENCE.get()
    );

//    public static final IEssenceType ELECTRIC = new EssenceType(
//            "electric",
//            () -> PVZItems.ELECTRIC_ESSENCE.get()
//    );
//
//    public static final IEssenceType SHADOW = new EssenceType(
//            "shadow",
//            () -> PVZItems.SHADOW_ESSENCE.get()
//    );

    public static class EssenceType implements IEssenceType {

        private final Supplier<Item> itemSupplier;
        private final String essenceName;

        public EssenceType(@Nonnull String name, @Nonnull Supplier<Item> itemSup) {
            this.essenceName = name;
            this.itemSupplier = itemSup;
            ESSENCE_TYPES.add(this);
        }

        /**
         * {@link PVZMod#coreRegister()}
         */
        public static void register() {
            System.out.println(ESSENCE_TYPES.size());
            ESSENCE_TYPES.forEach(e -> PVZAPI.get().registerEssenceType(e));
        }

        public Item getEssenceItem() {
            return this.itemSupplier.get();
        }

        @Override
        public String toString() {
            return this.essenceName;
        }
    }
}
