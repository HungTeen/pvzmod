package com.hungteen.pvz.gui.container;

import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.capability.player.PlayerDataManager;
import com.hungteen.pvz.item.tool.card.SummonCardItem;
import com.hungteen.pvz.register.ContainerRegister;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class PlayerInventoryContainer extends Container {

	private static final int SLOT_NUM_PER_PAGE = 54;
//	private static final int DATA_SIZE = 1;
	private final PlayerEntity player;
	public int currentPage;
//	private IIntArray inventoryData = new IntArray(DATA_SIZE);

	public PlayerInventoryContainer(int id, PlayerEntity player) {
		super(ContainerRegister.PLAYER_INVENTORY.get(), id);
		this.player = player;
		this.currentPage = 1;

//		this.player.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent((l)->{
//			for (int i = 0; i < 6; i++) {
//				for (int j = 0; j < 9; j++) {
//					int cnt = i * 9 + j;
//						this.addSlot(new SlotItemHandler(l, cnt, 24 + j * 18, 29 + i * 18));
//				}
//			}
//		});
		this.player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
			PlayerDataManager.PlayerStats stats = l.getPlayerData().getPlayerStats();
//			System.out.println(stats.getPlayerStats(Resources.SLOT_NUM));
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 9; j++) {
					int cnt = i * 9 + j;
					if (stats.getPlayerStats(Resources.SLOT_NUM) < cnt) {
//						this.addSlot(new LockedSlot(stats.getInventory(), cnt, 24 + j * 18, 29 + i * 18));
					}else {
//						this.addSlot(new CardSlot(stats.getInventory(), cnt, 24 + j * 18, 29 + i * 18));
					}
				}
			}
//			if (!player.world.isRemote && stats.getPlayerStats(Resources.SLOT_NUM) < SLOT_NUM_PER_PAGE) {
//				int left = SLOT_NUM_PER_PAGE - stats.getPlayerStats(Resources.SLOT_NUM);
//				this.inventoryData.set(0, left / 9);
//			}
		});

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlot(new Slot(this.player.inventory, j + i * 9 + 9, 24 + 18 * j, 159 + 18 * i));
			}
		}

		for (int i = 0; i < 9; i++) {
			this.addSlot(new Slot(this.player.inventory, i, 24 + 18 * i, 217));
		}

//		this.trackIntArray(inventoryData);
	}

	public void onPageChange() {
		this.updateSlots();
	}

//	public int getLockRow() {
//		return this.inventoryData.get(0);
//	}

	private void updateSlots() {
		this.player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
			PlayerDataManager.PlayerStats stats = l.getPlayerData().getPlayerStats();
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 9; j++) {
					int pos = (this.currentPage - 1) * SLOT_NUM_PER_PAGE + 9 * i + j;
					if (stats.getPlayerStats(Resources.SLOT_NUM) < pos) {
//						this.inventorySlots.set(i * 9 + j,
//								new LockedSlot(stats.getInventory(), pos, 24 + j * 18, 29 + i * 18));
					}
					else {
//						this.inventorySlots.set(i * 9 + j,
//								new CardSlot(stats.getInventory(), pos, 24 + j * 18, 29 + i * 18));
					}
				}
			}
//			if (stats.getPlayerStats(Resources.SLOT_NUM) < SLOT_NUM_PER_PAGE * this.currentPage) {
//				int left = SLOT_NUM_PER_PAGE * this.currentPage - stats.getPlayerStats(Resources.SLOT_NUM);
//				this.inventoryData.set(0, MathHelper.clamp(left / 9, 0, 6));
//			}
		});
	}

	@Override
	public boolean stillValid(PlayerEntity playerIn) {
		return true;
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < 54 ) {// in valid card slots
				if (!this.moveItemStackTo(itemstack1, 54, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if(index < 81){
				if (!this.moveItemStackTo(itemstack1, 0, 54, false)&&!this.moveItemStackTo(itemstack1, 81, 90, false)) {
					return ItemStack.EMPTY;
				}
			} else if(index < 90) {
				if (!this.moveItemStackTo(itemstack1, 0, 81, false)) {
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

	static class CardSlot extends Slot {

		public CardSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return stack.getItem() instanceof SummonCardItem;
		}
	}

	static class LockedSlot extends Slot {

		public LockedSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
			super(inventoryIn, index, xPosition, yPosition);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			System.out.println("no !");
			return false;
		}
	}
}
