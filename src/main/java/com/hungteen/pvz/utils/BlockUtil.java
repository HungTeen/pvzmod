package com.hungteen.pvz.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-09 10:44
 **/
public class BlockUtil {

    /**
     * get predicate blocks.
     */
    public static List<Block> getFilterBlocks(Predicate<Block> predicate) {
        return ForgeRegistries.BLOCKS.getValues().stream()
                .filter(predicate)
                .sorted(Comparator.comparing(ForgeRegistries.BLOCKS::getKey))
                .toList();
    }

    public static Optional<Block> getBlock(ResourceLocation resourceLocation) {
        return Optional.ofNullable(ForgeRegistries.BLOCKS.getValue(resourceLocation));
    }

    /**
     * get predicate blocks.
     */
    public static List<Block> getTagBlocks(TagKey<Block> tagKey) {
        return getFilterBlocks(b -> b.builtInRegistryHolder().is(tagKey));
    }

    /**
     * get expand collide box.
     */
    public static AABB getAABB(BlockPos pos, double w, double h) {
        return new AABB(pos.getX() - w, pos.getY() - h, pos.getZ() - w, pos.getX() + w, pos.getY() + h, pos.getZ() + w);
    }

}
