package com.hungteen.pvz.api.type;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 21:26
 **/

import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 20:45
 *
 * essence type of plant & zombie. <br>
 * every kind of essence item has an ore. <br>
 * most ores come from radiation.
 */
public interface IEssenceType {

    /**
     * tags contain blocks which can interact with {@link OriginBlock} to be radiated.
     */
    Optional<TagKey<Block>> getRadiationBlockTag();

    /**
     * corresponding block that can be radiated to essence ore.<br>
     * players can modify the block tag to add or delete corresponding blocks. <br>
     * used in tag data gen.
     */
    Optional<Block> getRadiationBlock();

    /**
     * corresponding essence item.
     */
    Item getEssenceItem();

    /**
     * corresponding essence ore.
     */
    Block getEssenceOre();

}

