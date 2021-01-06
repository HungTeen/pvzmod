package com.hungteen.pvz.event.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class SummonCardUseEvent extends PlayerEvent{

	protected ItemStack stack;
	
	public SummonCardUseEvent(PlayerEntity player, ItemStack stack) {
		super(player);
		this.stack = stack;
	}

	public ItemStack getItemStack() {
		return this.stack;
	}
	
}
