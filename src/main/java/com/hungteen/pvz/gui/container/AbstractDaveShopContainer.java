package com.hungteen.pvz.gui.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public abstract class AbstractDaveShopContainer extends Container{

	protected final PlayerEntity player;
	protected Inventory output = new Inventory(1);
	
	public AbstractDaveShopContainer(ContainerType<? extends Container> type, int id, PlayerEntity player) {
		super(type, id);
		this.player = player;
		this.addSlot(new Slot(output, 0, 171, 86) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return false;
			}
		});
		for(int i = 0;i < 3; ++ i) {
			for(int j = 0; j < 9; ++ j) {
				this.addSlot(new Slot(player.inventory, j + i * 9 + 9, 117 + 18 * j, 113 + 18 * i));
			}
		}
		for(int i = 0;i < 9; ++ i) {
			this.addSlot(new Slot(player.inventory, i, 117 + 18 * i, 171));
		}
	}
	
	public boolean canClickBuyButton() {
		return this.output.getStackInSlot(0).isEmpty();
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index == 0) {
				if (!this.mergeItemStack(itemstack1, 1, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (index < 28) {
				if(!mergeItemStack(itemstack1, 28, this.inventorySlots.size(), false)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (!this.mergeItemStack(itemstack1, 1, 28, false)) {
					return ItemStack.EMPTY;
				}
			}
			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}
		return itemstack;
	}
	
}