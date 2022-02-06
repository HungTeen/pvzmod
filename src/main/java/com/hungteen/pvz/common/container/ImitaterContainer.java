package com.hungteen.pvz.common.container;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.item.spawn.card.ImitaterCardItem;

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
		this.stack = this.player.getOffhandItem();
		if(stack.getItem() != ItemRegister.IMITATER_CARD.get()) {
			PVZMod.LOGGER.debug("ERROR OFFHAND ITEM !");
			return ;
		}
		this.backpack = ImitaterCardItem.getInventory(this.stack);
		this.addSlot(new Slot(backpack, 0, 80, 20) {
			@Override
			public boolean mayPlace(ItemStack stack) {
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
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index == 0) {
				if (! this.moveItemStackTo(itemstack1, 1, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (index < 28) {
				if(! moveItemStackTo(itemstack1, 0, 1, false)
						&& ! moveItemStackTo(itemstack1, 28, this.slots.size(), false)) {
					return ItemStack.EMPTY;
				}
			} else if(index < this.slots.size()){
				if (! this.moveItemStackTo(itemstack1, 0, 28, false)) {
					return ItemStack.EMPTY;
				}
			}
			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}
		return itemstack;
	}
	
	@Override
	public boolean stillValid(PlayerEntity playerIn) {
		if(playerIn.getOffhandItem().getItem() != ItemRegister.IMITATER_CARD.get()) {
			return false;
		}
		return true;
	}

}
