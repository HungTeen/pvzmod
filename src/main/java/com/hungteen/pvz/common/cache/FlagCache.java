package com.hungteen.pvz.common.cache;

import com.hungteen.pvz.common.entity.zombie.grass.TombStoneEntity;
import com.hungteen.pvz.common.event.PVZServerEvents;
import com.hungteen.pvz.common.world.data.PVZFlagData;

import net.minecraft.world.server.ServerWorld;

public class FlagCache {

	public static boolean isEdgar090505Defeated = false;
	
	/**
	 * only run when world server start.
	 * {@link PVZServerEvents#serverInit(net.minecraftforge.fml.event.server.FMLServerStartingEvent)}
	 */
	public static void syncStartFlagCache(ServerWorld world) {
		PVZFlagData data = PVZFlagData.getGlobalFlagData(world);
		isEdgar090505Defeated = data.isZombossDefeated();
	}
	
	/**
	 * only run when world server shut down.
	 * {@link PVZServerEvents#serverShutDown(net.minecraftforge.fml.event.server.FMLServerStoppingEvent)}
	 */
	public static void syncEndFlagCache(ServerWorld world) {
	}
	
	/**
	 * {@link TombStoneEntity#summonZombie()}
	 */
	public static boolean isEdgar090505Defeated(){
		return isEdgar090505Defeated;
	}
	
}
