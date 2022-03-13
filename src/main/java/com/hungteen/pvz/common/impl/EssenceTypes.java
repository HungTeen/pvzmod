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
            () -> PVZItems.ORIGIN_ESSENCE.get(),
            () -> PVZBlocks.ORIGIN_ORE.get(),
            () -> null,
            null
    );

    public static final IEssenceType APPEASE = new EssenceType(
            "appease",
            () -> PVZItems.APPEASE_ESSENCE.get(),
            () -> PVZBlocks.APPEASE_ORE.get(),
            () -> Blocks.GRASS_BLOCK,
            PVZBlockTags.TO_APPEASE_ORES
    );

    public static final IEssenceType LIGHT = new EssenceType(
            "light",
            () -> PVZItems.LIGHT_ESSENCE.get(),
            () -> PVZBlocks.LIGHT_ORE.get(),
            () -> Blocks.GLOWSTONE,
            PVZBlockTags.TO_LIGHT_ORES
    );

    public static final IEssenceType EXPLOSION = new EssenceType(
            "explosion",
            () -> PVZItems.EXPLOSION_ESSENCE.get(),
            () -> PVZBlocks.EXPLOSION_ORE.get(),
            () -> Blocks.REDSTONE_BLOCK,
            PVZBlockTags.TO_EXPLOSION_ORES
    );

    public static final IEssenceType DEFENCE = new EssenceType(
            "defence",
            () -> PVZItems.DEFENCE_ESSENCE.get(),
            () -> PVZBlocks.DEFENCE_ORE.get(),
            () -> Blocks.GRANITE,
            PVZBlockTags.TO_DEFENCE_ORES
    );

    public static final IEssenceType ICE = new EssenceType(
            "ice",
            () -> PVZItems.ICE_ESSENCE.get(),
            () -> PVZBlocks.ICE_ORE.get(),
            () -> Blocks.BLUE_ICE,
            PVZBlockTags.TO_ICE_ORES
    );

    public static final IEssenceType ENFORCE = new EssenceType(
            "enforce",
            () -> PVZItems.ENFORCE_ESSENCE.get(),
            () -> PVZBlocks.ENFORCE_ORE.get(),
            () -> Blocks.ANDESITE,
            PVZBlockTags.TO_ENFORCE_ORES
    );

    public static final IEssenceType TOXIC = new EssenceType(
            "toxic",
            () -> PVZItems.TOXIC_ESSENCE.get(),
            () -> PVZBlocks.TOXIC_ORE.get(),
            () -> null,
            null
    );

    public static final IEssenceType ASSIST = new EssenceType(
            "assist",
            () -> PVZItems.ASSIST_ESSENCE.get(),
            () -> PVZBlocks.ASSIST_ORE.get(),
            () -> Blocks.DIORITE,
            PVZBlockTags.TO_ASSIST_ORES
    );

    public static final IEssenceType MAGIC = new EssenceType(
            "magic",
            () -> PVZItems.MAGIC_ESSENCE.get(),
            () -> PVZBlocks.MAGIC_ORE.get(),
            () -> Blocks.SOUL_SAND,
            PVZBlockTags.TO_MAGIC_ORES
    );

    public static final IEssenceType FLAME = new EssenceType(
            "flame",
            () -> PVZItems.FLAME_ESSENCE.get(),
            () -> PVZBlocks.FLAME_ORE.get(),
            () -> Blocks.MAGMA_BLOCK,
            PVZBlockTags.TO_FLAME_ORES
    );

    public static final IEssenceType SPEAR = new EssenceType(
            "spear",
            () -> PVZItems.SPEAR_ESSENCE.get(),
            () -> PVZBlocks.SPEAR_ORE.get(),
            () -> Blocks.GRAVEL,
            PVZBlockTags.TO_SPEAR_ORES
    );

    public static final IEssenceType ARMA = new EssenceType(
            "arma",
            () -> PVZItems.ARMA_ESSENCE.get(),
            () -> PVZBlocks.ARMA_ORE.get(),
            () -> Blocks.SANDSTONE,
            PVZBlockTags.TO_ARMA_ORES
    );

    public static final IEssenceType ELECTRIC = new EssenceType(
            "electric",
            () -> PVZItems.ELECTRIC_ESSENCE.get(),
            () -> PVZBlocks.ELECTRIC_ORE.get(),
            () -> null,
            null
    );

    public static final IEssenceType SHADOW = new EssenceType(
            "shadow",
            () -> PVZItems.SHADOW_ESSENCE.get(),
            () -> PVZBlocks.SHADOW_ORE.get(),
            () -> null,
            null
    );

    public static class EssenceType implements IEssenceType {

        private final Supplier<Item> itemSupplier;
        private final Supplier<Block> oreSupplier;
        private final Supplier<Block> blockSupplier;
        private final TagKey<Block> essenceTag;
        private final String essenceName;

        public EssenceType(@Nonnull String name, @Nonnull Supplier<Item> itemSup, @Nonnull Supplier<Block> oreSup, @Nonnull Supplier<Block> blockSup, TagKey<Block> tag) {
            this.essenceName = name;
            this.itemSupplier = itemSup;
            this.oreSupplier = oreSup;
            this.blockSupplier = blockSup;
            this.essenceTag = tag;
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
        public Block getEssenceOre() {
            return this.oreSupplier.get();
        }

        @Override
        public Optional<Block> getRadiationBlock() {
            return Optional.ofNullable(this.blockSupplier.get());
        }

        public Optional<TagKey<Block>> getRadiationBlockTag() {
            return Optional.ofNullable(this.essenceTag);
        }

        @Override
        public String toString() {
            return this.essenceName;
        }
    }
}
