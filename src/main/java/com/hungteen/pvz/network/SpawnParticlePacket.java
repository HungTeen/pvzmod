package com.hungteen.pvz.network;

import java.util.function.Supplier;

import com.hungteen.pvz.register.ParticleRegister;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraftforge.fml.network.NetworkEvent;

public class SpawnParticlePacket {

	private int type;
	private double x;
	private double y;
	private double z;
	
	public SpawnParticlePacket(int type, double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = type;
	}
	
	public SpawnParticlePacket(PacketBuffer buffer) {
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
		@SuppressWarnings("resource")
		public static void onMessage(SpawnParticlePacket message, Supplier<NetworkEvent.Context> ctx) {
		    ctx.get().enqueueWork(()->{
		    	int id = message.type;
		    	IParticleData particle = null;
		    	if(id == 0) {
		    		particle = ParticleRegister.RED_BOMB.get();
		    	}
		    	if(particle != null) {
		    		Minecraft.getInstance().world.addParticle(particle, message.x, message.y, message.z, 0, 0, 0);
		    	}
		    });
		    ctx.get().setPacketHandled(true);
	    }
	}
	
}
