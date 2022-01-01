package com.hungteen.pvz.common.network.toclient;

import java.util.function.Supplier;

import com.hungteen.pvz.common.misc.sound.PVZSounds;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class PlaySoundPacket {

	private int type;
	
	public PlaySoundPacket(PVZSounds sound) {
		this.type = sound.ordinal();
	}
	
	public PlaySoundPacket(PacketBuffer buffer) {
		this.type = buffer.readInt();
	}

	public void encode(PacketBuffer buffer) {
		buffer.writeInt(this.type);
	}

	public static class Handler {
		@SuppressWarnings("resource")
		public static void onMessage(PlaySoundPacket message, Supplier<NetworkEvent.Context> ctx) {
		    ctx.get().enqueueWork(()->{
		    	Minecraft.getInstance().player.playSound(PVZSounds.values()[message.type].get(), 1F, 1F);
		    });
		    ctx.get().setPacketHandled(true);
	    }
	}
}