package com.hungteen.pvz.gui.inventory;

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
//		System.out.println(list.size());
		for (int i = 0; i < size && i < list.size(); i++) {
			setInventorySlotContents(i, ItemStack.read(list.getCompound(i)));
		}
	}

	@Override
	public boolean isUsableByPlayer(PlayerEntity player) {
		return !stack.isEmpty();
	}

	@Override
	public void markDirty() {
		super.markDirty();
		ListNBT list = new ListNBT();
		for (int i = 0; i < getSizeInventory(); i++) {
			list.add(getStackInSlot(i).write(new CompoundNBT()));
		}
		this.stack.getOrCreateTag().put(NAME, list);
	}
}