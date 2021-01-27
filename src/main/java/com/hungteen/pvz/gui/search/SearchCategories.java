package com.hungteen.pvz.gui.search;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public enum SearchCategories {

	ALL, 
	PLANTS, 
	ZOMBIES,
	FUSION;
	
	public static ItemStack getRenderItemStack(SearchCategories category) {
		switch (category) {
		case ALL: return new ItemStack(Items.COMPASS);
		case PLANTS: return new ItemStack(ItemRegister.PEA_SHOOTER_CARD.get());
		case ZOMBIES: return new ItemStack(ItemRegister.BUCKET_HEAD.get());
		case FUSION: return new ItemStack(ItemRegister.THREE_PEATER_CARD.get());
		default:{
			PVZMod.LOGGER.debug("Category ERROR !");
			return ItemStack.EMPTY;
		}
		
		}
	}
}
