package com.hungteen.pvz.common.container;

import com.hungteen.pvz.common.block.special.SunConverterBlock;
import com.hungteen.pvz.common.tileentity.SunConverterTileEntity;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class SunConverterContainer extends Container {

	@SuppressWarnings("unused")
	private final Player player;
	public final SunConverterTileEntity te;
	
	public SunConverterContainer(int id, Player player, Mth pos) {
		super(ContainerRegister.SUN_CONVERTER.get(), id);
		this.player = player;
		this.te = (SunConverterTileEntity) player.level.getBlockEntity(pos);
		if(this.te == null) {
			System.out.println("Error: Open Sun Converter GUI !");
			return ;
		}
		this.addDataSlots(this.te.array);
		for(int i = 0; i < 3; ++ i) {
			for(int j = 0; j < 3; ++ j) {
				this.addSlot(new SlotItemHandler(this.te.handler, i * 3 + j, 62 + 18 * j, 17 + 18 * i) {
					@Override
					public boolean mayPlace(ItemStack stack) {
						return SunConverterBlock.isValidItem(stack);
					}
				});
			}
		}
		for(int i = 0; i < 3; ++ i) {
			for(int j = 0; j < 9; ++ j) {
				this.addSlot(new Slot(player.inventory, j + i * 9 + 9, 8 + 18 * j, 84 + 18 * i));
			}
		}
		for(int i = 0; i < 9; ++ i) {
			this.addSlot(new Slot(player.inventory, i, 8 + 18 * i, 142));
		}
	}

	@Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < 9) {
				if (!this.moveItemStackTo(itemstack1, 9, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (index < 9 + 27) {
				if(!moveItemStackTo(itemstack1, 0, 9, false)
						&& !moveItemStackTo(itemstack1, 9 + 27, this.slots.size(), false)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (!this.moveItemStackTo(itemstack1, 0, 9 + 27, false)) {
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
	public boolean stillValid(Player playerIn) {
		return this.te.isUsableByPlayer(playerIn);
	}

}
