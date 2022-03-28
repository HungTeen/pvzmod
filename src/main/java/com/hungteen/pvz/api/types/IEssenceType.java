package com.hungteen.pvz.api.types;

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
     * corresponding essence item.
     */
    Item getEssenceItem();

}

