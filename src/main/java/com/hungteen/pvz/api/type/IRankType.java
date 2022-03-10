package com.hungteen.pvz.api.type;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public interface IRankType {

    /**
     * rarity in shop.
     */
    int getWeight();

    /**
     * how much does it value in shop.
     */
    int getPrice();

    /**
     * enchant point.
     */
    int getEnchantPoint();

    /**
     * get corresponding template card.
     */
    Item getTemplateCard();

    /**
     * get corresponding template card tag.
     */
    TagKey<Item> getCardTag();

    /**
     * get corresponding material item.
     */
    TagKey<Item> getMaterial();

}
