package com.hungteen.pvz.network;

import com.hungteen.pvz.PVZMod;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PVZPacketHandler {

	private static final ResourceLocation CHANNEL_NAME = new ResourceLocation(PVZMod.MOD_ID+":networking");
	private static final String PROTOCOL_VERSION = "1.0";
	
	public static final SimpleChannel CHANNEL =NetworkRegistry.newSimpleChannel(
			CHANNEL_NAME, 
			() -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals, 
			PROTOCOL_VERSION::equals
	);

	public static void init() {
		int id = 0;
		CHANNEL.registerMessage(id++,PlayerStatsPacket.class, PlayerStatsPacket::encode,PlayerStatsPacket::new,PlayerStatsPacket.Handler::onMessage);
		CHANNEL.registerMessage(id++,PlantStatsPacket.class,PlantStatsPacket::encode,PlantStatsPacket::new,PlantStatsPacket.Handler::onMessage);
		CHANNEL.registerMessage(id++,OpenGuiPacket.class,OpenGuiPacket::encode,OpenGuiPacket::new,OpenGuiPacket.Handler::onMessage);
		CHANNEL.registerMessage(id++,ClickButtonPacket.class,ClickButtonPacket::encode,ClickButtonPacket::new,ClickButtonPacket.Handler::onMessage);
	}
	
}
