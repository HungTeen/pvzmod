package com.hungteen.pvz.common.network.toserver;

import java.util.function.Supplier;

import com.hungteen.pvz.common.capability.CapabilityHandler;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class PVZMouseScrollPacket {

	private double data;
	
	public PVZMouseScrollPacket(double data) {
		this.data = data;
	}
	
	public PVZMouseScrollPacket(PacketBuffer buffer) {
		this.data = buffer.readDouble();
	}

	public void encode(PacketBuffer buffer) {
		buffer.writeDouble(this.data);
	}

	public static class Handler {
		public static void onMessage(PVZMouseScrollPacket message, Supplier<NetworkEvent.Context> ctx) {
		    ctx.get().enqueueWork(() -> {
		    	final ServerPlayerEntity player = ctx.get().getSender();
		    	player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
		    		if(message.data == 0) {
		    			l.getPlayerData().onSwitchCard();
		    		} else {
		    			l.getPlayerData().onScrollInventory(message.data);
		    		}
		    	});
		    });
		    ctx.get().setPacketHandled(true);
	    }
	}
	
}
