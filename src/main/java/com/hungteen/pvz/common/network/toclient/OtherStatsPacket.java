package com.hungteen.pvz.common.network.toclient;

import java.util.function.Supplier;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.misc.PVZPacketTypes;
import com.hungteen.pvz.utils.PlayerUtil;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class OtherStatsPacket{

	private final PVZPacketTypes type;
	private int pos = 0;
	private int data = 0;
	private boolean flag = false;

	public OtherStatsPacket(PVZPacketTypes type, int pos, boolean flag) {
		this.type = type;
		this.pos = pos;
		this.flag = flag;
	}

	public OtherStatsPacket(PVZPacketTypes type, int pos, int data) {
		this.type = type;
		this.pos = pos;
		this.data = data;
	}
	
	public OtherStatsPacket(PacketBuffer buffer) {
		this.type = PVZPacketTypes.values()[buffer.readInt()];
		this.pos = buffer.readInt();
		this.data = buffer.readInt();
		this.flag = buffer.readBoolean();
	}

	public void encode(PacketBuffer buffer) {
		buffer.writeInt(this.type.ordinal());
		buffer.writeInt(this.pos);
		buffer.writeInt(this.data);
		buffer.writeBoolean(this.flag);
	}

	public static class Handler {
		public static void onMessage(OtherStatsPacket message, Supplier<NetworkEvent.Context> ctx) {
		    ctx.get().enqueueWork(() -> {
				switch (message.type){
					case WAVE:{
						if(message.pos < 0){
							PlayerUtil.getOptManager(PVZMod.PROXY.getPlayer()).ifPresent(l -> l.getInvasion().setTotalWaveCount(message.data));
						} else{
							PlayerUtil.getOptManager(PVZMod.PROXY.getPlayer()).ifPresent(l -> l.getInvasion().setWaveTime(message.pos, message.data));
						}
					}
					case WAVE_FLAG:{
						PlayerUtil.getOptManager(PVZMod.PROXY.getPlayer()).ifPresent(l -> l.getInvasion().setWaveTriggered(message.pos, message.flag));
					}
				}
//			    if(message.type == 0) {
//			    	if(message.pos == -1) {
//			    		ClientPlayerResources.updateGoodTick = message.data;
//			    	} else {
//			    		ClientPlayerResources.mysteryGoods[message.pos] = message.data;
//			    	}
//			    } else if(message.type == 1) {
//			    	if(message.pos == -1) {
//			    		ClientPlayerResources.totalWaveCount = message.data;
//			    	} else {
////			    		ClientPlayerResources.zombieWaveTime[message.pos] = message.data;
//			    	}
//			    } else if(message.type == 2) {
//			    	if(message.pos == 0) {
//			    		ClientPlayerResources.lightLevel = message.data;
//			    	}
//			    }
		    });
		    ctx.get().setPacketHandled(true);
	    }
	}
}
