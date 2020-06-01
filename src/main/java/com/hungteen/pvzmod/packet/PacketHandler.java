package com.hungteen.pvzmod.packet;

import com.hungteen.pvzmod.util.Reference;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

	public static final SimpleNetworkWrapper CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);

	public static void init() {
		int id = 0;
//		CHANNEL.registerMessage(PacketAnnihilateBlock.Handler.class, PacketAnnihilateBlock.class, id++, Side.CLIENT);
//		CHANNEL.registerMessage(PacketAreaProtection.Handler.class, PacketAreaProtection.class, id++, Side.CLIENT);
//		CHANNEL.registerMessage(PacketChangeBiome.Handler.class, PacketChangeBiome.class, id++, Side.CLIENT);
//		CHANNEL.registerMessage(PacketEnforceProgressionStatus.Handler.class, PacketEnforceProgressionStatus.class, id++, Side.CLIENT);
//		CHANNEL.registerMessage(PacketStructureProtection.Handler.class, PacketStructureProtection.class, id++, Side.CLIENT);
//		CHANNEL.registerMessage(PacketStructureProtectionClear.Handler.class, PacketStructureProtectionClear.class, id++, Side.CLIENT);
//		CHANNEL.registerMessage(PacketThrowPlayer.Handler.class, PacketThrowPlayer.class, id++, Side.CLIENT);
//		CHANNEL.registerMessage(PacketMagicMap.Handler.class, PacketMagicMap.class, id++, Side.CLIENT);
//		CHANNEL.registerMessage(PacketMazeMap.Handler.class, PacketMazeMap.class, id++, Side.CLIENT);
//		CHANNEL.registerMessage(PacketUpdateShield.Handler.class, PacketUpdateShield.class, id++, Side.CLIENT);
//		CHANNEL.registerMessage(PacketSetSkylightEnabled.Handler.class, PacketSetSkylightEnabled.class, id++, Side.CLIENT);
		CHANNEL.registerMessage(PacketSpawnEntityParticles.Handler.class, PacketSpawnEntityParticles.class, id++, Side.CLIENT);	
		CHANNEL.registerMessage(PacketPlayerData.Handler.class, PacketPlayerData.class, id++, Side.CLIENT);
		CHANNEL.registerMessage(PacketPlantLvlData.Handler.class, PacketPlantLvlData.class, id++, Side.CLIENT);
		CHANNEL.registerMessage(PacketGuiButton.Handler.class, PacketGuiButton.class, id++, Side.SERVER);
//		CHANNEL.registerMessage(PacketUncraftingGui.Handler.class, PacketUncraftingGui.class, id++, Side.SERVER);
	}
	
}
