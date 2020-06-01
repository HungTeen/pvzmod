package com.hungteen.pvzmod.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerPlantBase extends Container{

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

}
