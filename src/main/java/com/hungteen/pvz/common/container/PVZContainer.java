package com.hungteen.pvz.common.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;

public abstract class PVZContainer extends Container {

	protected PVZContainer(ContainerType<?> type, int id) {
		super(type, id);
	}
	
	/**
	 * default offset.
	 */
	public void addInventoryAndHotBar(PlayerEntity player, int leftX, int leftY) {
		this.addPlayerInventory(player, leftX, leftY);
		this.addPlayerHotBar(player, leftX, leftY + 58);
	}
	
	public void addPlayerInventory(PlayerEntity player, int leftX, int leftY) {
		for(int i = 0; i < 3; ++ i) {
			for(int j = 0; j < 9; ++ j) {
				this.addSlot(new Slot(player.inventory, j + i * 9 + 9, leftX + 18 * j, leftY + 18 * i));
			}
		}
	}
	
	public void addPlayerHotBar(PlayerEntity player, int leftX, int leftY) {
		for(int i = 0; i < 9; ++ i) {
			this.addSlot(new Slot(player.inventory, i, leftX + 18 * i, leftY));
		}
	}

}
