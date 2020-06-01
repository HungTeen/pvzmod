package com.hungteen.pvzmod.gui.container;

import com.hungteen.pvzmod.blocks.tileentities.TileEntityJuicer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerJuicer extends Container{

	private final TileEntityJuicer tileentity;
	private int burnTime,workTime,flowTime,progress,type,burnMaxTime;
	
	public ContainerJuicer(InventoryPlayer player,TileEntityJuicer te) {
		this.tileentity=te;
		IItemHandler handler=tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		
		this.addSlotToContainer(new SlotItemHandler(handler, 0, 26, 16));
//		this.addSlotToContainer(new SlotItemHandler(handler, 1, 26, 35));
		this.addSlotToContainer(new SlotItemHandler(handler, 1, 26, 54));
		this.addSlotToContainer(new SlotItemHandler(handler, 2, 142, 36));
		
		for(int i=0;i<3;i++) {
			for(int j=0;j<9;j++) {
				this.addSlotToContainer(new Slot(player,j+i*9+9,8+18*j,84+18*i));
			}
		}
		
		for(int i=0;i<9;i++) {
			this.addSlotToContainer(new Slot(player,i,8+18*i,142));
		}
	}
	
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for(int i=0;i<this.listeners.size();i++) {
			IContainerListener listener=this.listeners.get(i);
			if(this.burnTime!=this.tileentity.getField(0)) listener.sendWindowProperty(this,0, this.tileentity.getField(0));
			if(this.workTime!=this.tileentity.getField(1)) listener.sendWindowProperty(this,1, this.tileentity.getField(1));
			if(this.flowTime!=this.tileentity.getField(2)) listener.sendWindowProperty(this,2, this.tileentity.getField(2));
			if(this.progress!=this.tileentity.getField(3)) listener.sendWindowProperty(this,3, this.tileentity.getField(3));
			if(this.type!=this.tileentity.getField(4)) listener.sendWindowProperty(this,4, this.tileentity.getField(4));
			if(this.burnMaxTime!=this.tileentity.getField(5)) listener.sendWindowProperty(this,5, this.tileentity.getField(5));
		}
		this.burnTime=this.tileentity.getField(0);
		this.workTime=this.tileentity.getField(1);
		this.flowTime=this.tileentity.getField(2);
		this.progress=this.tileentity.getField(3);
		this.type=this.tileentity.getField(4);
		this.burnMaxTime=this.tileentity.getField(5);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		this.tileentity.setField(id, data);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tileentity.isUsableByPlayer(playerIn);
	}
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
		Slot slot=inventorySlots.get(index);
		if(slot==null||!slot.getHasStack()) {
			return ItemStack.EMPTY;
		}
		ItemStack newStack =slot.getStack();
		ItemStack oldStack =newStack.copy();
		boolean isMerged=false;
		if(index>=0&&index<=2) {
			if(!this.mergeItemStack(newStack, 3, 39, true)) {
				return ItemStack.EMPTY;
			}
		}
		else if(index>=3&&index<=29) {
			if(this.mergeItemStack(newStack, 0, 3, false)) {
				isMerged=true;
			}else if(this.mergeItemStack(newStack, 30, 39, false)) {
				isMerged=true;
			}else {
				return ItemStack.EMPTY;
			}
		}
		else if(index>=30&&index<=38) {
			if(this.mergeItemStack(newStack, 0, 3, false)) {
				isMerged=true;
			}else if(this.mergeItemStack(newStack, 0, 30, false)) {
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

}
