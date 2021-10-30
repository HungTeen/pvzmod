package com.hungteen.pvz.common.network;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.common.capability.CapabilityHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Optional;
import java.util.function.Supplier;

public class PAZPacket {

    private final PAZPacketTypes type;
    private final String id;
    private final int val;
    private final boolean is;

    public PAZPacket(PAZPacketTypes type, String id, int v, boolean is) {
        this.type = type;
        this.id = id;
        this.val = v;
        this.is = is;
    }

    public PAZPacket(PacketBuffer buffer) {
        this.type = PAZPacketTypes.values()[buffer.readInt()];
        this.id = buffer.readUtf();
        this.val = buffer.readInt();
        this.is = buffer.readBoolean();
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeInt(this.type.ordinal());
        buffer.writeUtf(this.id);
        buffer.writeInt(this.val);
        buffer.writeBoolean(this.is);
    }

    public static class Handler {
        public static void onMessage(PAZPacket message, Supplier<NetworkEvent.Context> ctx) {
            final Optional<IPAZType> paz = PVZAPI.get().getTypeByID(message.id);
            //TODO 整合与植物&僵尸有关的发包
            if(paz.isPresent()){
                PVZMod.LOGGER.error("PAZPacket : Can not get paz type for {} !", message.id);
            } else if(ctx.get().getDirection().getReceptionSide().isServer()){//server side.

            } else{//client side.
                switch (message.type){
                    case UNLOCK:{
                        ctx.get().enqueueWork(() -> {
                            Minecraft.getInstance().player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
                                l.getPlayerData().setPAZLocked(paz.get(), message.is);
                            });
                        });
                        break;
                    }
                }
            }
            ctx.get().setPacketHandled(true);
        }
    }

    public enum PAZPacketTypes{
        UNLOCK,
    }
}
