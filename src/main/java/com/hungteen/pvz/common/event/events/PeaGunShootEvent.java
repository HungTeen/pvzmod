package com.hungteen.pvz.common.event.events;

import com.hungteen.pvz.api.types.IPlantType;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class PeaGunShootEvent extends Event {
	
	private final Player player;
	private final ItemStack stack;
	private final IPlantType mode;
	
	public PeaGunShootEvent(Player player, ItemStack stack, IPlantType mode) {
		this.player = player;
		this.stack = stack;
		this.mode = mode;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public IPlantType getMode() {
		return mode;
	}
	
	public ItemStack getStack() {
		return stack;
	}

}
