package com.hungteen.pvz.api.types;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;

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
	ITag.INamedTag<Item> getCardTag();
	
	/**
	 * get corresponding material item.
	 */
	ITag.INamedTag<Item> getMaterial();
	
	/**
	 * enchant point.
	 */
	int getEnchantPoint();
	
}
