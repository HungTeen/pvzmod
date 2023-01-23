package com.hungteen.pvz.common.capability.challenge;
import net.minecraft.nbt.CompoundNBT;

public interface IRaiderDataCapability {
    void init(int ch);
    CompoundNBT SavetoNBT();
    void LoadfromNBT(CompoundNBT nbt);
    int getChallengeID();
    void setChallengeID(int ch);
}
