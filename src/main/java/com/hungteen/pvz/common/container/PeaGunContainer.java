package com.hungteen.pvz.common.container;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.item.tool.plant.PeaGunItem;
import com.hungteen.pvz.common.misc.tag.PVZItemTags;

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
		this.stack = this.player.getOffhandItem();
		if(stack.getItem() != ItemRegister.PEA_GUN.get()) {
			PVZMod.LOGGER.debug("ERROR OFFHAND ITEM !");
			return ;
		}
		backpack = PeaGunItem.getInventory(stack);
		
		//special slots
		this.addSlot(new Slot(backpack, 0, 80, 21) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return PeaGunItem.isValidMode(stack);
			}
		});
		
		for(int i = 0; i < 3; ++ i) {
			for(int j = 0; j < 9; ++ j) {
				this.addSlot(new Slot(backpack, j + i * 9 + 1, 8 + 18 * j, 45 + 18 * i) {
					@Override
					public boolean mayPlace(ItemStack stack) {
						return stack.getItem().is(PVZItemTags.PEA_GUN_BULLETS);
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
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if(index == 0) {
				if (! this.moveItemStackTo(itemstack1, 1, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (index <= PeaGunItem.PEA_GUN_SLOT_NUM) {
				if (! this.moveItemStackTo(itemstack1, PeaGunItem.PEA_GUN_SLOT_NUM + 1, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (index <= PeaGunItem.PEA_GUN_SLOT_NUM + 27) {
				if(! moveItemStackTo(itemstack1, 0, PeaGunItem.PEA_GUN_SLOT_NUM + 1, false)
						&& ! moveItemStackTo(itemstack1, PeaGunItem.PEA_GUN_SLOT_NUM + 27 + 1, this.slots.size(), false)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (! this.moveItemStackTo(itemstack1, 0, PeaGunItem.PEA_GUN_SLOT_NUM + 27 + 1, false)) {
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
		if(playerIn.getOffhandItem().getItem() != ItemRegister.PEA_GUN.get()) {
			return false;
		}
		return true;
	}

}
