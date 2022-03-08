package com.hungteen.pvz.api.types;

import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public interface IRankType {

    int getWeight();

	int getValue();

	String getName();

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
	
	/**
	 * enchant point.
	 */
	int getEnchantPoint();
	
}
