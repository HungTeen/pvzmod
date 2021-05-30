package com.hungteen.pvz.common.capability.player;

import com.hungteen.pvz.common.capability.CapabilityHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerDataProvider implements ICapabilitySerializable<CompoundNBT> {
	
	private IPlayerDataCapability player_data_capability = CapabilityHandler.PLAYER_DATA_CAPABILITY.getDefaultInstance();

	
	public PlayerDataProvider(PlayerEntity player) {
		if(player!=null) {
		    player_data_capability.init(player);
		}
	}
	
	@Override
	public CompoundNBT serializeNBT() {
		return player_data_capability.getPlayerData().saveToNBT();
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		player_data_capability.getPlayerData().loadFromNBT(nbt);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return CapabilityHandler.PLAYER_DATA_CAPABILITY.orEmpty(cap, LazyOptional.of(()->player_data_capability));
	}
}

