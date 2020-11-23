package com.hungteen.pvz.gui.container;

import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.capability.player.PlayerDataManager;
import com.hungteen.pvz.register.ContainerRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.TradeUtil;
import com.hungteen.pvz.utils.TradeUtil.DaveGoods;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;

public class DaveShopContainer extends Container{

	private final PlayerEntity player;
	private Inventory output = new Inventory(1);
	
	public DaveShopContainer(int id, PlayerEntity player) {
		super(ContainerRegister.DAVE_SHOP.get(), id);
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
	
	public void buyGood(DaveGoods good) {
		if(good == DaveGoods.ENERGY) {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
				PlayerDataManager.PlayerStats stats = l.getPlayerData().getPlayerStats();
				stats.addPlayerStats(Resources.MONEY, - TradeUtil.getEnergyCost(stats.getPlayerStats(Resources.MAX_ENERGY_NUM)));
				stats.addPlayerStats(Resources.MAX_ENERGY_NUM, 1);
			});
		} else {
			player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
				PlayerDataManager.PlayerStats stats = l.getPlayerData().getPlayerStats();
				stats.addPlayerStats(Resources.MONEY, - TradeUtil.getGoodCost(good));
				this.output.setInventorySlotContents(0, new ItemStack(TradeUtil.getGoodItem(good)));
			});
		}
		this.player.world.playSound(null, this.player.getPosition(), SoundRegister.DAVE_BUY.get(), SoundCategory.AMBIENT, 1f, 1f);
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
