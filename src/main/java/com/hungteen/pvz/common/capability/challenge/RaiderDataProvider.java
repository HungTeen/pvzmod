package com.hungteen.pvz.common.capability.challenge;

import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.capability.player.IPlayerDataCapability;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RaiderDataProvider implements ICapabilitySerializable<CompoundNBT> {

    private final IRaiderDataCapability capability = CapabilityHandler.RAIDER_DATA_CAPABILITY.getDefaultInstance();

    public RaiderDataProvider(int ch) {
        capability.init(ch);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return CapabilityHandler.RAIDER_DATA_CAPABILITY.orEmpty(cap, LazyOptional.of(()->capability));
    }

    @Override
    public CompoundNBT serializeNBT() {
        return capability.SavetoNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        capability.LoadfromNBT(nbt);
    }
}
