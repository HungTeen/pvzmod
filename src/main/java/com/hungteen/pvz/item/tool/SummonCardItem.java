package com.hungteen.pvz.item.tool;

import com.hungteen.pvz.register.GroupRegister;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class SummonCardItem extends Item{

	public SummonCardItem() {
		super(new Properties().group(GroupRegister.PVZ_GROUP).maxStackSize(1));
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}
}
