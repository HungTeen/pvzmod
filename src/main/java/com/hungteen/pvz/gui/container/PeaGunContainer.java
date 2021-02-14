package com.hungteen.pvz.gui.container;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.item.tool.PeaGunItem;
import com.hungteen.pvz.register.ContainerRegister;
import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class PeaGunContainer extends Container {

	private Inventory backpack;
	private final PlayerEntity player;
	private final ItemStack stack;
	
	public PeaGunContainer(int id, PlayerEntity player) {
		super(ContainerRegister.PEA_GUN.get(), id);
		this.player = player;
		this.stack = this.player.getHeldItemOffhand();
		if(stack.getItem() != ItemRegister.PEA_GUN.get()) {
			PVZMod.LOGGER.debug("ERROR OFFHAND ITEM !");
			return ;
		}
		backpack = PeaGunItem.getInventory(stack);
		
		this.addSlot(new Slot(backpack, 0, 80, 21));//special slots
		
		for(int i = 0; i < 3; ++ i) {
			for(int j = 0; j < 9; ++ j) {
				this.addSlot(new Slot(backpack, j + i * 9 + 1, 8 + 18 * j, 45 + 18 * i) {
					@Override
					public boolean isItemValid(ItemStack stack) {
						return stack.getItem() == ItemRegister.PEA.get()
								|| stack.getItem() == ItemRegister.SNOW_PEA.get() || stack.getItem() == ItemRegister.FLAME_PEA.get();
					}
				});
			}
		}
		
		for(int i = 0;i < 3; ++ i) {
			for(int j = 0; j < 9; ++ j) {
				this.addSlot(new Slot(player.inventory, j + i * 9 + 9, 8 + 18 * j, 105 + 18 * i));
			}
		}
		
		for(int i = 0; i < 9; ++ i) {
			this.addSlot(new Slot(player.inventory, i, 8 + 18 * i, 163));
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if(index == 0) {
				if (! this.mergeItemStack(itemstack1, 1, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (index <= PeaGunItem.PEA_GUN_SLOT_NUM) {
				if (! this.mergeItemStack(itemstack1, PeaGunItem.PEA_GUN_SLOT_NUM + 1, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (index <= PeaGunItem.PEA_GUN_SLOT_NUM + 27) {
				if(! mergeItemStack(itemstack1, 0, PeaGunItem.PEA_GUN_SLOT_NUM + 1, false)
						&& ! mergeItemStack(itemstack1, PeaGunItem.PEA_GUN_SLOT_NUM + 27 + 1, this.inventorySlots.size(), false)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (! this.mergeItemStack(itemstack1, 0, PeaGunItem.PEA_GUN_SLOT_NUM + 27 + 1, false)) {
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
		if(playerIn.getHeldItemOffhand().getItem() != ItemRegister.PEA_GUN.get()) {
			return false;
		}
		return true;
	}

}
