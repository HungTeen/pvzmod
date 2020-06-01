package com.hungteen.pvzmod.capability.provider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.hungteen.pvzmod.capability.interfaces.ICapabilityPVZPlayer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class PVZPlayerProvider implements ICapabilitySerializable<NBTTagCompound> {
	@CapabilityInject(ICapabilityPVZPlayer.class)
	public static Capability<ICapabilityPVZPlayer> PVZ_PLAYER = null;
	private ICapabilityPVZPlayer instance = PVZ_PLAYER.getDefaultInstance();

	public PVZPlayerProvider(EntityPlayer pl) {
		if (instance != null) {
			instance.init(pl);
		}
	}

	@Override
	public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == PVZ_PLAYER;
	}

	@Nullable
	@Override
	public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
		return capability == PVZ_PLAYER ? PVZ_PLAYER.<T>cast(instance) : null;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		
		return instance.getPlayerData().saveToNBT();
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		instance.getPlayerData().loadFromNBT(nbt);
	}
}

