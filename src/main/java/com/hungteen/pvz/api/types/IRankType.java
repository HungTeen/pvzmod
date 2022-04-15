package com.hungteen.pvz.api.types;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public interface IRankType {

    String getName();

    /**
     * rarity in shop.
     */
    int getWeight();

    /**
     * how much does it value in shop.
     */
    int getPrice();

    /**
     * get corresponding material item.
     */
    TagKey<Item> getMaterial();

}
