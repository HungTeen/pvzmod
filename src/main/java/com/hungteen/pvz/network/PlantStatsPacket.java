package com.hungteen.pvz.network;

import java.util.function.Supplier;

import com.hungteen.pvz.capabilities.player.PVZGuiTabPlayerData;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class PlantStatsPacket {

	private int type;
	private int lvl;
	private int xp;
	
	public PlantStatsPacket(int x,int y,int z) {
		this.type=x;
		this.lvl=y;
		this.xp=z;
	}
	
	public PlantStatsPacket(PacketBuffer buffer) {
		this.type=buffer.readInt();
		this.lvl=buffer.readInt();
		this.xp=buffer.readInt();
	}

	public void encode(PacketBuffer buffer) {
		buffer.writeInt(this.type);
		buffer.writeInt(this.lvl);
		buffer.writeInt(this.xp);
	}

	public static class Handler {
		public static void onMessage(PlantStatsPacket message, Supplier<NetworkEvent.Context> ctx) {
		    ctx.get().enqueueWork(()->{
			    PVZGuiTabPlayerData.setPlantData(message.type,message.lvl,message.xp);
		    });
		    ctx.get().setPacketHandled(true);
	    }
	}
}
