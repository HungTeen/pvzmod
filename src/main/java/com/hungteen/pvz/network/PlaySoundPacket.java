package com.hungteen.pvz.network;

import java.util.function.Supplier;

import com.hungteen.pvz.register.SoundRegister;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.network.NetworkEvent;

public class PlaySoundPacket {

	private int type;
	
	public PlaySoundPacket(int x) {
		this.type = x;
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
		    	int id = message.type;
		    	SoundEvent sound = null;
		    	if(id == 0) {
		    		sound = SoundRegister.SUN_PICK.get();
		    	} else if(id == 1) {
		    		sound = SoundEvents.SNOW_GOLEM_SHOOT;
		    	} else if(id == 2) {
		    		sound = SoundRegister.HUGE_WAVE.get();
		    	} else if(id == 3) {
		    		sound = SoundRegister.WARN.get();
		    	} else if(id == 4) {
		    		sound = SoundRegister.WIN_MUSIC.get();
		    	} else if(id == 5) {
		    		sound = SoundRegister.LOSE_MUSIC.get();
		    	} else if(id == 6) {
		    		sound = SoundRegister.COIN_PICK.get();
		    	} else if(id == 7) {
		    		sound = SoundRegister.JEWEL_PICK.get();
		    	} else if(id == 8) {
		    		sound = SoundRegister.SLOT_MACHINE.get();
		    	} else if(id == 9) {
		    		sound = SoundRegister.PLANT_GROW.get();
		    	} else if(id == 10) {
		    		sound = SoundRegister.JACK_SAY.get();
		    	}
		    	if(sound != null) {
		    		Minecraft.getInstance().player.playSound(sound, 1f, 1f);
		    	}
		    });
		    ctx.get().setPacketHandled(true);
	    }
	}
}