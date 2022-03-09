package com.hungteen.pvz.utils;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Comparator;
import java.util.List;
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

}
