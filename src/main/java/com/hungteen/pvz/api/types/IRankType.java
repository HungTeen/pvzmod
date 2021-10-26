package com.hungteen.pvz.api.types;

import net.minecraft.item.Item;

public interface IRankType {

	/**
	 * get corresponding template card.
	 */
	Item getTemplateCard();
	
	/**
	 * get corresponding material item.
	 */
	Item getMaterial();
	
	/**
	 * enchant point.
	 */
	int getEnchantPoint();
	
}
