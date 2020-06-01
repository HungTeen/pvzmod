package com.hungteen.pvzmod.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerTrade extends Container{

	private ItemStackHandler items = new ItemStackHandler(4);
	
	public ContainerTrade(EntityPlayer player)
	{
		super();
        this.addSlotToContainer(new SlotItemHandler(items, 0, 35,52));
        this.addSlotToContainer(new SlotItemHandler(items, 1, 61,52));
        this.addSlotToContainer(new SlotItemHandler(items, 2, 117,52));
        
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 85 + i * 18));
            }
        }
        
        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
        }
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        return null;
    }

}

