package com.hungteen.pvz.common.capability.player;

import com.hungteen.pvz.common.capability.CapabilityHandler;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerDataProvider implements ICapabilitySerializable<CompoundTag> {
	
	private IPlayerDataCapability player_data_capability = CapabilityHandler.PLAYER_DATA_CAPABILITY.getDefaultInstance();

	
	public PlayerDataProvider(Player player) {
		if(player!=null) {
		    player_data_capability.init(player);
		}
	}
	
	@Override
	public CompoundTag serializeNBT() {
		return player_data_capability.getPlayerData().saveToNBT();
	}

	@Override
	public void deserializeNBT(CompoundTag nbt) {
		player_data_capability.getPlayerData().loadFromNBT(nbt);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return CapabilityHandler.PLAYER_DATA_CAPABILITY.orEmpty(cap, LazyOptional.of(()->player_data_capability));
	}
}

