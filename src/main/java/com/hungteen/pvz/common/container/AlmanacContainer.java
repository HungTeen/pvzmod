package com.hungteen.pvz.common.container;

import com.hungteen.pvz.common.item.ItemRegister;

import net.minecraft.world.entity.player.Player;
import net.minecraft.inventory.container.Slot;

public class AlmanacContainer extends AbstractOptionContainer {

	@SuppressWarnings("unused")
	private final Player player;
	
	public AlmanacContainer(int id, Player player) {
		super(ContainerRegister.ALMANAC.get(), id);
		this.player = player;
	}

	@Override
	public boolean stillValid(Player playerIn) {
		return playerIn.getMainHandItem().getItem()==ItemRegister.ALMANAC.get()
				|| playerIn.getOffhandItem().getItem()==ItemRegister.ALMANAC.get();
	}

	@Override
	public boolean isCraftSlot(Slot slot) {
		return false;
	}

}
