package com.hungteen.pvz.network;

import java.util.function.Supplier;

import com.hungteen.pvz.capability.player.ClientPlayerResources;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class PlayerStatsPacket{

	private int type;
	private int data;
	
	public PlayerStatsPacket(int x,int y) {
		this.type=x;
		this.data=y;
//		PVZMod.LOGGER.debug(type+" x "+data);
	}
	
	public PlayerStatsPacket(PacketBuffer buffer) {
		this.type=buffer.readInt();
		this.data=buffer.readInt();
	}

	public void encode(PacketBuffer buffer) {
		buffer.writeInt(this.type);
		buffer.writeInt(this.data);
	}

	public static class Handler {
		public static void onMessage(PlayerStatsPacket message, Supplier<NetworkEvent.Context> ctx) {
//			PVZMod.LOGGER.debug(message.type+" y "+message.data);
		    ctx.get().enqueueWork(()->{
			    ClientPlayerResources.setPlayerData(message.type,message.data);
//			    PVZMod.LOGGER.debug(message.type+" z "+message.data);
		    });
		    ctx.get().setPacketHandled(true);
	    }
	}
}
