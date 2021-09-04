package com.hungteen.pvz.common.container.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;

public class ItemInventory extends Inventory {
	
	private static final String NAME = "BackPack";
	private final ItemStack stack;

	public ItemInventory(ItemStack stack, int size) {
		super(size);
		this.stack = stack;
		ListNBT list = new ListNBT();
		if(!stack.isEmpty() && stack.getOrCreateTag().contains(NAME)) {
			list = stack.getOrCreateTag().getList(NAME, Constants.NBT.TAG_COMPOUND);
		}
		for (int i = 0; i < size && i < list.size(); i++) {
			setItem(i, ItemStack.of(list.getCompound(i)));
		}
	}

	@Override
	public boolean stillValid(PlayerEntity player) {
		return !stack.isEmpty();
	}

	@Override
	public void setChanged() {
		super.setChanged();
		ListNBT list = new ListNBT();
		for (int i = 0; i < getContainerSize(); i++) {
			list.add(getItem(i).save(new CompoundNBT()));
		}
		this.stack.getOrCreateTag().put(NAME, list);
	}
}