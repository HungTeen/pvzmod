package com.hungteen.pvz.network;

import java.util.function.Supplier;

import com.hungteen.pvz.capability.player.ClientPlayerResources;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class OtherStatsPacket{

	private int type;
	private int pos;
	private int data;
	
	public OtherStatsPacket(int x, int y, int z) {
		this.type = x;
		this.pos = y;
		this.data = z;
	}
	
	public OtherStatsPacket(PacketBuffer buffer) {
		this.type = buffer.readInt();
		this.pos = buffer.readInt();
		this.data = buffer.readInt();
	}

	public void encode(PacketBuffer buffer) {
		buffer.writeInt(this.type);
		buffer.writeInt(this.pos);
		buffer.writeInt(this.data);
	}

	public static class Handler {
		public static void onMessage(OtherStatsPacket message, Supplier<NetworkEvent.Context> ctx) {
		    ctx.get().enqueueWork(() -> {
			    if(message.type == 0) {
			    	if(message.pos == -1) {
			    		ClientPlayerResources.updateGoodTick = message.data;
			    	} else {
			    		ClientPlayerResources.mysteryGoods[message.pos] = message.data;
			    	}
			    }
		    });
		    ctx.get().setPacketHandled(true);
	    }
	}
}
