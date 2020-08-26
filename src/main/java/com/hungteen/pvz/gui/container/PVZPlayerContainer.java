package com.hungteen.pvz.gui.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;

public class PVZPlayerContainer extends Container{

	protected PVZPlayerContainer(ContainerType<?> type, int id) {
		super(type, id);
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return false;
	}

}
