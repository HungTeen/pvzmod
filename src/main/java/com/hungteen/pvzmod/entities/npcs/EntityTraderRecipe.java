package com.hungteen.pvzmod.entities.npcs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.village.MerchantRecipe;

public class EntityTraderRecipe extends MerchantRecipe {
	public EntityTraderRecipe(NBTTagCompound tagCompound) {
		super(tagCompound);
	}

	public EntityTraderRecipe(ItemStack buy1, ItemStack buy2, ItemStack sell) {
		this(buy1, buy2, sell, 0, sell.isStackable() ? 5 : 1);
	}

	public EntityTraderRecipe(ItemStack buy1, ItemStack buy2, ItemStack sell, int defaultUses, int maxUses) {
		super(buy1, buy2, sell, defaultUses, maxUses);
	}

	public EntityTraderRecipe(ItemStack buy1, ItemStack sell) {
		this(buy1, ItemStack.EMPTY, sell);
	}

	public EntityTraderRecipe(ItemStack buy1, Item sellItem) {
		this(buy1, ItemStack.EMPTY, new ItemStack(sellItem));
	}
}