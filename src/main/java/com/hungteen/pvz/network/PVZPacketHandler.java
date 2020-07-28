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
		CHANNEL.registerMessage(id++,PacketPlayerStats.class, PacketPlayerStats::encode,PacketPlayerStats::new,PacketPlayerStats.Handler::onMessage);
//		CHANNEL.registerMessage(PacketSpawnEntityParticles.Handler.class, PacketSpawnEntityParticles.class, id++, Side.CLIENT);	
//		CHANNEL.registerMessage(PacketPlayerStats.Handler.class, PacketPlayerStats.class, id++, Side.CLIENT);
//		CHANNEL.registerMessage(PacketPlantLvlData.Handler.class, PacketPlantLvlData.class, id++, Side.CLIENT);
//		CHANNEL.registerMessage(PacketGuiButton.Handler.class, PacketGuiButton.class, id++, Side.SERVER);
//		CHANNEL.registerMessage(PacketUncraftingGui.Handler.class, PacketUncraftingGui.class, id++, Side.SERVER);
	}
	
}
