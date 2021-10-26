package com.hungteen.pvz.api.types;

import java.util.Random;

import net.minecraft.item.ItemStack;

public interface IBundleType {

	/**
	 * randomly get bundles' card.
	 */
	ItemStack getEnjoyCard(Random rand);
	
	String toString();
	
}
