package com.hungteen.pvz.gui.container;

import com.hungteen.pvz.register.ContainerRegister;
import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Slot;

public class AlmanacContainer extends AbstractOptionContainer {

	@SuppressWarnings("unused")
	private final PlayerEntity player;
	
	public AlmanacContainer(int id, PlayerEntity player) {
		super(ContainerRegister.ALMANAC.get(), id);
		this.player = player;
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return playerIn.getHeldItemMainhand().getItem()==ItemRegister.ALMANAC.get()
				|| playerIn.getHeldItemOffhand().getItem()==ItemRegister.ALMANAC.get();
	}

	@Override
	public boolean isCraftSlot(Slot slot) {
		return false;
	}

}
