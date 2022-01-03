package com.hungteen.pvz.common.network.toclient;

import com.hungteen.pvz.client.ClientProxy;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerStatsPacket{

	private int type;
	private int data;
	
	public PlayerStatsPacket(int x, int y) {
		this.type = x;
		this.data = y;
	}
	
	public PlayerStatsPacket(PacketBuffer buffer) {
		this.type = buffer.readInt();
		this.data = buffer.readInt();
	}

	public void encode(PacketBuffer buffer) {
		buffer.writeInt(this.type);
		buffer.writeInt(this.data);
	}

	public static class Handler {
		public static void onMessage(PlayerStatsPacket message, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> {
				PlayerUtil.setResource(ClientProxy.MC.player, Resources.values()[message.type], message.data);
			});
		    ctx.get().setPacketHandled(true);
	    }
	}
}
