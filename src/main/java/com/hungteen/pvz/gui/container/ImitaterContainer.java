package com.hungteen.pvz.gui.container;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.item.tool.card.ImitaterCardItem;
import com.hungteen.pvz.register.ContainerRegister;
import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class ImitaterContainer extends Container {

	private Inventory backpack;
	private final PlayerEntity player;
	private final ItemStack stack;
	
	public ImitaterContainer(int id, PlayerEntity player) {
		super(ContainerRegister.IMITATER.get(), id);
		this.player = player;
		this.stack = this.player.getHeldItemOffhand();
		if(stack.getItem() != ItemRegister.IMITATER_CARD.get()) {
			PVZMod.LOGGER.debug("ERROR OFFHAND ITEM !");
			return ;
		}
		backpack = ImitaterCardItem.getInventory(stack);
		this.addSlot(new Slot(backpack, 0, 80, 20) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return ImitaterCardItem.isValidImitateSlot(stack);
			}
		});//special slots
		for(int i = 0; i < 3; ++ i) {
			for(int j = 0; j < 9; ++ j) {
				this.addSlot(new Slot(player.inventory, j + i * 9 + 9, 8 + 18 * j, 51 + 18 * i));
			}
		}
		for(int i = 0; i < 9; ++ i) {
			this.addSlot(new Slot(player.inventory, i, 8 + 18 * i, 109));
		}
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index == 0) {
				if (! this.mergeItemStack(itemstack1, 1, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (index < 28) {
				if(! mergeItemStack(itemstack1, 0, 1, false)
						&& ! mergeItemStack(itemstack1, 28, this.inventorySlots.size(), false)) {
					return ItemStack.EMPTY;
				}
			} else if(index < this.inventorySlots.size()){
				if (! this.mergeItemStack(itemstack1, 0, 28, false)) {
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
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		if(playerIn.getHeldItemOffhand().getItem() != ItemRegister.IMITATER_CARD.get()) {
			return false;
		}
		return true;
	}

}
