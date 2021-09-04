package com.hungteen.pvz.common.network.toserver;

import java.util.function.Supplier;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class UpdateMotionPacket {

	private int type;
	private double x;
	private double y;
	private double z;
	
	public UpdateMotionPacket(int type, double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = type;
	}
	
	public UpdateMotionPacket(PacketBuffer buffer) {
		this.type = buffer.readInt();
		this.x = buffer.readDouble();
		this.y = buffer.readDouble();
		this.z = buffer.readDouble();
	}

	public void encode(PacketBuffer buffer) {
		buffer.writeInt(this.type);
		buffer.writeDouble(x);
		buffer.writeDouble(y);
		buffer.writeDouble(z);
	}

	public static class Handler {
		public static void onMessage(UpdateMotionPacket message, Supplier<NetworkEvent.Context> ctx) {
			final ServerPlayerEntity player = ctx.get().getSender();
			ctx.get().enqueueWork(()->{
		    	Entity entity = player.level.getEntity(message.type);
		    	if(entity != null) {
		    		entity.setDeltaMovement(message.x, message.y, message.z);
		    	}
		    });
		    ctx.get().setPacketHandled(true);
	    }
	}
	
}
