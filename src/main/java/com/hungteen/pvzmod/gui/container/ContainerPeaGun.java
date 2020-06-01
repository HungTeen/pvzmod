package com.hungteen.pvzmod.gui.container;

import com.hungteen.pvzmod.items.weapons.ItemPeaGun;
import com.hungteen.pvzmod.registry.ItemRegister;
import com.hungteen.pvzmod.util.ItemUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerPeaGun extends Container{

	private IInventory backpack;
	private ItemStack stack;
	public ContainerPeaGun(EntityPlayer player,ItemStack stack) {
	
		backpack=new InventoryBasic("backpack", true, 28) {
			@Override
			public void markDirty() {
				super.markDirty();
//				onCraftMatrixChanged(this);
			}
		};
		if(!(stack.getItem() instanceof ItemPeaGun)) {
			System.out.println("ERROR ITEM");
			return ;
		}
		this.stack=stack;
		ItemUtil.restoreFromItemStack(this.stack,this.backpack);
		
		this.addSlotToContainer(new Slot(backpack,0,80,21));   //Ö÷²ÛÎ»
		for(int i=0;i<3;i++) {
			for(int j=0;j<9;j++) {
				this.addSlotToContainer(new Slot(backpack,j+i*9+1,8+18*j,45+18*i) {
					@Override
					public boolean isItemValid(ItemStack stack) {
						return stack.getItem()==ItemRegister.PEA||stack.getItem()==ItemRegister.SNOW_PEA;
					}
				});
			}
		}
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<9;j++) {
				this.addSlotToContainer(new Slot(player.inventory,j+i*9+9,8+18*j,105+18*i));
			}
		}
		
		for(int i=0;i<9;i++) {
			this.addSlotToContainer(new Slot(player.inventory,i,8+18*i,163));
		}
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory inventoryIn) {
		super.onCraftMatrixChanged(inventoryIn);
//		this.convertToItemStack();
	}
	
	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);
		ItemUtil.convertToItemStack(this.stack,this.backpack);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		Slot slot=this.inventorySlots.get(index);
		if(slot==null||!slot.getHasStack()) {
			return ItemStack.EMPTY;
		}
		ItemStack newStack = slot.getStack();
		ItemStack oldStack = newStack.copy();
		boolean isMerged=false;
		if(index>=1&&index<=27) {
			if(!mergeItemStack(newStack, 28, 64, true)) {
				return ItemStack.EMPTY;
			}
		}else if(index>=28&&index<=54) {
			if(mergeItemStack(newStack, 0, 28, false)) {
				isMerged=true;
			}
			else if(mergeItemStack(newStack, 55, 64, false)) {
				isMerged=true;
			}else {
				return ItemStack.EMPTY;
			}
		}else if(index>=55&&index<=63) {
			if(mergeItemStack(newStack, 0, 28, false)) {
				isMerged=true;
			}
			else if(mergeItemStack(newStack, 28, 55, false)) {
				isMerged=true;
			}else {
				return ItemStack.EMPTY;
			}
		}
		if(!isMerged) {
			return ItemStack.EMPTY;
		}
		if(newStack.isEmpty()) {
			slot.putStack(ItemStack.EMPTY);
		}
		else {
			slot.onSlotChanged();
		}
		return oldStack;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		if(playerIn.getHeldItemOffhand().getItem()!=ItemRegister.PEA_GUN) {
			ItemUtil.convertToItemStack(this.stack,this.backpack);
			return false;
		}
        return true;
	}

}
