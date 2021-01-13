package com.hungteen.pvz.gui.container;

import com.hungteen.pvz.register.ContainerRegister;
import com.hungteen.pvz.tileentity.SlotMachineTileEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.SlotItemHandler;

public class SlotMachineContainer extends Container {

	public final SlotMachineTileEntity te;
	public final PlayerEntity player;
	
	public SlotMachineContainer(int id, PlayerEntity player, BlockPos pos) {
		super(ContainerRegister.SLOT_MACHINE.get(), id);
		this.player = player;
		this.te = (SlotMachineTileEntity) player.world.getTileEntity(pos);
		if(this.te == null) {
			System.out.println("Error: Open Slot Machine GUI !");
			return ;
		}
		this.trackIntArray(this.te.array);
		this.te.setPlayer(player);
		for(int i = 0; i < 3; ++ i) {
			this.addSlot(new SlotItemHandler(this.te.handler, i, 62 + 18 * i, 96) {
				@Override
				public boolean isItemValid(ItemStack stack) {
					return false;
				}
			});
		}
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
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        return ItemStack.EMPTY;
    }
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return this.te.isUsableByPlayer(playerIn);
	}

}
