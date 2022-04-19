package com.hungteen.pvz.common.tag;

import com.hungteen.pvz.utils.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 21:27
 **/
public class PVZBlockTags {

    /* forge */
    public static final TagKey<Block> AMETHYST_ORES = forgeTag("ores/amethyst");
    public static final TagKey<Block> PLANT_SUIT_BLOCKS = forgeTag("plant_suit_block");

    /* pvz */
    //related to essence ores
    public static final TagKey<Block> ESSENCE_ORES = pvzTag("ores/essence");
    public static final TagKey<Block> GOLD_TILES = pvzTag("gold_tile");
    public static final TagKey<Block> DIRT_NO_GRASS = pvzTag("dirt_no_grass");
    public static final TagKey<Block> NUT_LOGS = pvzTag("nut_logs");

    private static TagKey<Block> pvzTag(String name){
        return BlockTags.create(Util.prefix(name));
    }

    private static TagKey<Block> forgeTag(String name){
        return BlockTags.create(Util.forgePrefix(name));
    }

}
