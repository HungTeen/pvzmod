package com.hungteen.pvzmod.capability.storage;

import javax.annotation.Nullable;

import com.hungteen.pvzmod.capability.interfaces.ICapabilityPVZPlayer;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class PVZPlayerStorage implements Capability.IStorage<ICapabilityPVZPlayer> {
	@Nullable
	@Override
	public NBTBase writeNBT(Capability<ICapabilityPVZPlayer> capability, ICapabilityPVZPlayer instance, EnumFacing side) {
		return instance.getPlayerData().saveToNBT();
	}

	@Override
	public void readNBT(Capability<ICapabilityPVZPlayer> capability, ICapabilityPVZPlayer instance, EnumFacing side, NBTBase nbt) {
		if (nbt instanceof NBTTagCompound)
			instance.getPlayerData().loadFromNBT((NBTTagCompound)nbt);
	}
}