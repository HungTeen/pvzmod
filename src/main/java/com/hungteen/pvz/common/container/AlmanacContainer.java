package com.hungteen.pvz.common.container;

import com.hungteen.pvz.common.item.ItemRegister;

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
	public boolean stillValid(PlayerEntity playerIn) {
		return playerIn.getMainHandItem().getItem()==ItemRegister.ALMANAC.get()
				|| playerIn.getOffhandItem().getItem()==ItemRegister.ALMANAC.get();
	}

	@Override
	public boolean isCraftSlot(Slot slot) {
		return false;
	}

}
