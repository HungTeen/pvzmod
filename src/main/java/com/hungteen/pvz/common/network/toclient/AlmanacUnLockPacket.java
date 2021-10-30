package com.hungteen.pvz.common.network.toclient;

import com.hungteen.pvz.client.cache.ClientPlayerResources;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class AlmanacUnLockPacket {
	private int type;
	private boolean num;

	public AlmanacUnLockPacket(int type, boolean num) {
		this.type = type;
		this.num = num;
	}

	public AlmanacUnLockPacket(PacketBuffer buffer) {
		this.type = buffer.readInt();
		this.num = buffer.readBoolean();
	}

	public void encode(PacketBuffer buffer) {
		buffer.writeInt(this.type);
		buffer.writeBoolean(this.num);
	}

	public static class Handler {
		public static void onMessage(AlmanacUnLockPacket message, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> {
				ClientPlayerResources.setAlmanacUnLocked(message.type, message.num);
			});
			ctx.get().setPacketHandled(true);
		}
	}
	
}
