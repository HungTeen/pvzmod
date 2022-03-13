package com.hungteen.pvz.common.network;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 16:44
 **/
public class PlayerStatsPacket {

    private int type;
    private int data;

    public PlayerStatsPacket(int x, int y) {
        this.type = x;
        this.data = y;
    }

    public PlayerStatsPacket(FriendlyByteBuf buffer) {
        this.type = buffer.readInt();
        this.data = buffer.readInt();
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.type);
        buffer.writeInt(this.data);
    }

    public static class Handler {
        public static void onMessage(PlayerStatsPacket message, Supplier<NetworkEvent.Context> ctx) {
            ctx.get().enqueueWork(() -> {
                PlayerUtil.setResource(PVZMod.PROXY.getPlayer(), Resources.values()[message.type], message.data);
            });
            ctx.get().setPacketHandled(true);
        }
    }

}
