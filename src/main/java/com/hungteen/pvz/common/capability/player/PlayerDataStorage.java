package com.hungteen.pvz.common.capability.player;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class PlayerDataStorage implements Capability.IStorage<IPlayerDataCapability> {

	@Override
	public CompoundTag writeNBT(Capability<IPlayerDataCapability> capability, IPlayerDataCapability instance, Direction side) {
		return instance.getPlayerData().saveToNBT();
	}

	@Override
	public void readNBT(Capability<IPlayerDataCapability> capability, IPlayerDataCapability instance, Direction side,
			INBT nbt) {
		if(nbt instanceof CompoundTag) {
			instance.getPlayerData().loadFromNBT((CompoundTag) nbt);
		}
	}
}