package com.hungteen.pvz.network;

import java.util.function.Supplier;

import com.hungteen.pvz.register.SoundRegister;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.network.NetworkEvent;

public class PlaySoundPacket {

	private int type;
	
	public PlaySoundPacket(int x) {
		this.type=x;
	}
	
	public PlaySoundPacket(PacketBuffer buffer) {
		this.type=buffer.readInt();
	}

	public void encode(PacketBuffer buffer) {
		buffer.writeInt(this.type);
	}

	public static class Handler {
		@SuppressWarnings("resource")
		public static void onMessage(PlaySoundPacket message, Supplier<NetworkEvent.Context> ctx) {
		    ctx.get().enqueueWork(()->{
		    	int id = message.type;
		    	SoundEvent sound = null;
		    	if(id == 0) {
		    		sound = SoundRegister.SUN_PICK.get();
		    	}
		    	if(sound != null) {
		    		Minecraft.getInstance().player.playSound(sound, 1f, 1f);
		    	}
		    	
		    });
		    ctx.get().setPacketHandled(true);
	    }
	}
}