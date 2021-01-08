package com.hungteen.pvz.gui.container;

import com.hungteen.pvz.item.tool.SunStorageSaplingItem;
import com.hungteen.pvz.register.ContainerRegister;
import com.hungteen.pvz.tileentity.SunConverterTileEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.SlotItemHandler;

public class SunConverterContainer extends Container {

	@SuppressWarnings("unused")
	private final PlayerEntity player;
	public final SunConverterTileEntity te;
	
	public SunConverterContainer(int id, PlayerEntity player, BlockPos pos) {
		super(ContainerRegister.SUN_CONVERTER.get(), id);
		this.player = player;
		this.te = (SunConverterTileEntity) player.world.getTileEntity(pos);
		if(this.te == null) {
			System.out.println("Error: Open Sun Converter GUI !");
			return ;
		}
		this.trackIntArray(this.te.array);
		for(int i = 0; i < 3; ++ i) {
			for(int j = 0; j < 9; ++ j) {
				this.addSlot(new Slot(player.inventory, j + i * 9 + 9, 8 + 18 * j, 84 + 18 * i));
			}
		}
		for(int i = 0; i < 9; ++ i) {
			this.addSlot(new Slot(player.inventory, i, 8 + 18 * i, 142));
		}
		for(int i = 0; i < 3; ++ i) {
			for(int j = 0; j < 3; ++ j) {
				this.addSlot(new SlotItemHandler(this.te.handler, i * 3 + j, 62 + 18 * j, 17 + 18 * i) {
					@Override
					public boolean isItemValid(ItemStack stack) {
						return stack.getItem() instanceof SunStorageSaplingItem;
					}
				});
			}
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
