package com.hungteen.pvz.common.container;

import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.item.tool.plant.CardPackItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class CardPackContainer extends PVZContainer {

	private Inventory backpack;
	private ItemStackHandler cardBar;
	private final PlayerEntity player;
	private final ItemStack stack;
	private final int slotNum = 0;
	
	public CardPackContainer(int id, PlayerEntity player) {
		super(ContainerRegister.CARD_PACK.get(), id);
		this.player = player;
		this.stack = player.getMainHandItem();
		this.backpack = CardPackItem.getInventory(this.stack);
//		this.slotNum = Resources.SLOT_NUM.max;
//		this.cardBar = new CardPackItemHandler(this.player, this.slotNum);
		
		/* summon card hot bar */
//		for(int i = 0; i < this.slotNum; ++ i) {
//			this.addSlot(new SlotItemHandler(this.cardBar, i, 19 + 18 * i, 21){
//				@Override
//				public boolean mayPlace(ItemStack stack) {
//					return CardPackItem.isValidItemStack(stack);
//				}
//			});
//		}
		
		/* back pack */
		for(int i = 0; i < 4; ++ i) {
			for(int j = 0; j < 9; ++ j) {
				this.addSlot(new Slot(this.backpack, j + i * 9, 19 + 18 * j, 53 + 18 * i) {
					@Override
					public boolean mayPlace(ItemStack stack) {
						return CardPackItem.isValidItemStack(stack);
					}
				});
			}
		}
		
		/* player inventory */
		this.addInventoryAndHotBar(this.player, 19, 139);
		
	}
	
	@Override
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if(index < this.slotNum) {
				if (! this.moveItemStackTo(itemstack1, this.slotNum, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (index < this.slotNum + CardPackItem.SLOT_NUM) {
				if (! this.moveItemStackTo(itemstack1, 0, this.slotNum, false) 
						&& ! this.moveItemStackTo(itemstack1, this.slotNum + CardPackItem.SLOT_NUM, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (index < this.slotNum + CardPackItem.SLOT_NUM + 27) {
				if(! moveItemStackTo(itemstack1, 0, this.slotNum + CardPackItem.SLOT_NUM, false)
						&& ! moveItemStackTo(itemstack1, this.slotNum + CardPackItem.SLOT_NUM + 27, this.slots.size(), false)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (! this.moveItemStackTo(itemstack1, 0, this.slotNum + CardPackItem.SLOT_NUM + 27, false)) {
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
		return player.getMainHandItem().getItem().equals(ItemRegister.CARD_PACK.get());
	}

}
