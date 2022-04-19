package com.hungteen.pvz.api.types;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-15 13:17
 **/
public interface ICardType {

    String getName();

    Rarity getRarity();

    boolean isReplaceable();

    /**
     * get corresponding template card.
     */
    Item getTemplateCard();

    /**
     * get corresponding template card tag.
     */
    TagKey<Item> getCardTag();

    /**
     * get corresponding template card tag.
     */
    TagKey<Item> getMaterialTag();

}
