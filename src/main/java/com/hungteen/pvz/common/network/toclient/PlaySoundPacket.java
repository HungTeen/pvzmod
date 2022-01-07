package com.hungteen.pvz.common.network.toclient;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class PlaySoundPacket {

	private String type;
	
	public PlaySoundPacket(String type) {
		this.type = type;
	}
	
	public PlaySoundPacket(PacketBuffer buffer) {
		this.type = buffer.readUtf();
	}

	public void encode(PacketBuffer buffer) {
		buffer.writeUtf(type);
	}

	public static class Handler {
		@SuppressWarnings("resource")
		public static void onMessage(PlaySoundPacket message, Supplier<NetworkEvent.Context> ctx) {
		    ctx.get().enqueueWork(()->{
		    	SoundEvent sound = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(message.type));
		    	if(sound != null) {
		    		Minecraft.getInstance().player.playSound(sound, 1F, 1F);
		    	}
		    });
		    ctx.get().setPacketHandled(true);
	    }
	}
}