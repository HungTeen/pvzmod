package com.hungteen.pvz.common.capability.challenge;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class RaiderDataStorage implements Capability.IStorage<IRaiderDataCapability>{
    @Override
    public INBT writeNBT(Capability<IRaiderDataCapability> capability, IRaiderDataCapability instance, Direction side) {
        return instance.SavetoNBT();
    }

    @Override
    public void readNBT(Capability<IRaiderDataCapability> capability, IRaiderDataCapability instance, Direction side, INBT nbt) {
        instance.LoadfromNBT((CompoundNBT) nbt);
    }
}
