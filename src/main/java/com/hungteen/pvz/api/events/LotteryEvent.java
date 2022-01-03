package com.hungteen.pvz.api.events;

import com.hungteen.pvz.common.tileentity.SlotMachineTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Event;

import java.util.Optional;

public class LotteryEvent extends Event {

    private final SlotMachineTileEntity te;
    private final SlotMachineTileEntity.SlotType slotType;
    private final Optional<PlayerEntity> opt;
    private final int num;

    public LotteryEvent(SlotMachineTileEntity te, PlayerEntity player, SlotMachineTileEntity.SlotType slotType, int num){
        this.te = te;
        this.slotType = slotType;
        this.opt = Optional.ofNullable(player);
        this.num = num;
    }

    public SlotMachineTileEntity getTe() {
        return te;
    }

    public SlotMachineTileEntity.SlotType getSlotType() {
        return slotType;
    }

    public Optional<PlayerEntity> getOptPlayer() {
        return opt;
    }

    public int getNum() {
        return num;
    }
}
