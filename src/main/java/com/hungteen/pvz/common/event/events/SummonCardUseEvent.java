package com.hungteen.pvz.common.event.events;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class SummonCardUseEvent extends PlayerEvent{

	protected final ItemStack heldStack;
	protected final ItemStack plantStack;
	
	public SummonCardUseEvent(Player player, ItemStack heldStack, ItemStack plantStack) {
		super(player);
		this.heldStack = heldStack;
		this.plantStack = plantStack;
	}

	public ItemStack getHeldStack() {
		return this.heldStack;
	}

	public ItemStack getPlantStack() {
		return plantStack;
	}
}
