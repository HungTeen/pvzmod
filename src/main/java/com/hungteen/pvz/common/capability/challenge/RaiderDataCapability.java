package com.hungteen.pvz.common.capability.challenge;
import net.minecraft.nbt.CompoundNBT;

public class RaiderDataCapability implements IRaiderDataCapability {

    public int challenge = 0;

    public void init(int ch){
        challenge = ch;
    }

    public CompoundNBT SavetoNBT(){
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("in_challenge",challenge);
        return nbt;
    }

    public void LoadfromNBT(CompoundNBT nbt){
        this.challenge = nbt.getInt("in_challenge");
    }

    @Override
    public int getChallengeID() {
        return challenge;
    }

    @Override
    public void setChallengeID(int ch) {
        challenge = ch;
    }


}
