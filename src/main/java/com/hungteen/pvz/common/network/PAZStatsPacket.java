package com.hungteen.pvz.common.network;

import java.util.Optional;
import java.util.function.Supplier;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.utils.PlayerUtil;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class PAZStatsPacket {

    private final PAZPacketTypes type;
    private final String id;
    private final int val1;
    private final int val2;
    private final boolean is;

    /**
     * used for paz point.
     */
    public PAZStatsPacket(PAZPacketTypes type, String id, int v1) {
        this.type = type;
        this.id = id;
        this.val1 = v1;
        this.val2 = 0;
        this.is = false;
    }

    public PAZStatsPacket(PAZPacketTypes type, String id, int v1, int v2) {
        this.type = type;
        this.id = id;
        this.val1 = v1;
        this.val2 = v2;
        this.is = false;
    }

    public PAZStatsPacket(PAZPacketTypes type, String id, boolean is) {
        this.type = type;
        this.id = id;
        this.val1 = 0;
        this.val2 = 0;
        this.is = is;
    }

    public PAZStatsPacket(PacketBuffer buffer) {
        this.type = PAZPacketTypes.values()[buffer.readInt()];
        this.id = buffer.readUtf();
        this.val1 = buffer.readInt();
        this.val2 = buffer.readInt();
        this.is = buffer.readBoolean();
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeInt(this.type.ordinal());
        buffer.writeUtf(this.id);
        buffer.writeInt(this.val1);
        buffer.writeInt(this.val2);
        buffer.writeBoolean(this.is);
    }

    public static class Handler {
        public static void onMessage(PAZStatsPacket message, Supplier<NetworkEvent.Context> ctx) {
            final Optional<IPAZType> paz = PVZAPI.get().getTypeByID(message.id);
            if(! paz.isPresent()){
                PVZMod.LOGGER.error("PAZPacket : Can not get paz type for {} !", message.id);
            } else if(ctx.get().getDirection().getReceptionSide().isServer()){//server side.

            } else{//client side.
                switch (message.type){
                    case UNLOCK:{
                        ctx.get().enqueueWork(() -> {
                            PlayerUtil.setPAZLock(PVZMod.PROXY.getPlayer(), paz.get(), message.is);
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
