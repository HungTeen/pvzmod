package com.hungteen.pvz.common.capability.player;

import com.hungteen.pvz.common.capability.CapabilityHandler;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.CallbackI;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 12:43
 **/
public class PVZPlayerCapProvider implements ICapabilitySerializable<CompoundTag> {

    public static Capability<IPVZPlayerCapability> PVZ_PLAYER_CAP = CapabilityManager.get(new CapabilityToken<IPVZPlayerCapability>() {});

    private IPVZPlayerCapability pvzPlayerCap;
    private LazyOptional<IPVZPlayerCapability> opt = LazyOptional.of(this::create);

    public PVZPlayerCapProvider(Player player){
        this.opt.ifPresent(cap -> cap.init(player));
    }

    private IPVZPlayerCapability create(){
        if(pvzPlayerCap == null){
            pvzPlayerCap = new PVZPlayerCap();
        }
        return pvzPlayerCap;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return this.getCapability(cap);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap == PVZ_PLAYER_CAP){
            return opt.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return pvzPlayerCap.get().saveToNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        pvzPlayerCap.get().loadFromNBT(nbt);
    }

}
