package com.hungteen.pvz.common.container;

import com.hungteen.pvz.common.tileentity.SlotMachineTileEntity;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.world.item.ItemStack;

public class SlotMachineContainer extends Container {

	public final SlotMachineTileEntity te;
	public final Player player;
	
	public SlotMachineContainer(int id, Player player, Mth pos) {
		super(ContainerRegister.SLOT_MACHINE.get(), id);
		this.player = player;
		this.te = (SlotMachineTileEntity) player.level.getBlockEntity(pos);
		if(this.te == null) {
			System.out.println("Error: Open Slot Machine GUI !");
			return ;
		}
		this.addDataSlots(this.te.array);
		this.te.setPlayer(player);
		for(int i = 0; i < 3; ++ i) {
			for(int j = 0; j < 9; ++ j) {
				this.addSlot(new Slot(player.inventory, j + i * 9 + 9, 8 + 18 * j, 145 + 18 * i));
			}
		}
		for(int i = 0; i < 9; ++ i) {
			this.addSlot(new Slot(player.inventory, i, 8 + 18 * i, 203));
		}
	}

	@Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
//		if (slot != null && slot.hasItem()) {
//			ItemStack itemstack1 = slot.getItem();
//			itemstack = itemstack1.copy();
//			if (index < 3) {
//				if (!this.moveItemStackTo(itemstack1, 3, this.slots.size(), true)) {
//					return ItemStack.EMPTY;
//				}
//			} else if (index < 3 + 27) {
//				if(!moveItemStackTo(itemstack1, 0, 3, false) && !moveItemStackTo(itemstack1, 3 + 27, this.slots.size(), false)) {
//					return ItemStack.EMPTY;
//				}
//			} else {
//				if (!this.moveItemStackTo(itemstack1, 0, 3 + 27, false)) {
//					return ItemStack.EMPTY;
//				}
//			}
//			if (itemstack1.isEmpty()) {
//				slot.set(ItemStack.EMPTY);
//			} else {
//				slot.setChanged();
//			}
//		}
//		return itemstack;
		return ItemStack.EMPTY;
    }
	
	@Override
	public boolean stillValid(Player playerIn) {
		return this.te.isUsableByPlayer(playerIn);
	}

}
