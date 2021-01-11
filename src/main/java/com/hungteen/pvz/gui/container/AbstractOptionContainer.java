package com.hungteen.pvz.gui.container;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;

public abstract class AbstractOptionContainer extends Container {

	protected AbstractOptionContainer(ContainerType<?> type, int id) {
		super(type, id);
	}

	public abstract boolean isCraftSlot(Slot slot);
	
	public abstract boolean clearCraftSlots();
	
}
