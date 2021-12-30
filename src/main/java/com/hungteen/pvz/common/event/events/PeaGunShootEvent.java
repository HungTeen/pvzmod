package com.hungteen.pvz.common.event.events;

import com.hungteen.pvz.api.types.IPlantType;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class PeaGunShootEvent extends Event {
	
	private final PlayerEntity player;
	private final ItemStack stack;
	private final IPlantType mode;
	
	public PeaGunShootEvent(PlayerEntity player, ItemStack stack, IPlantType mode) {
		this.player = player;
		this.stack = stack;
		this.mode = mode;
	}
	
	public PlayerEntity getPlayer() {
		return player;
	}
	
	public IPlantType getMode() {
		return mode;
	}
	
	public ItemStack getStack() {
		return stack;
	}

}
