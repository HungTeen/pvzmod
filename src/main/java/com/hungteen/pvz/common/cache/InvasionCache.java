package com.hungteen.pvz.common.cache;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.hungteen.pvz.common.core.ZombieType;
import com.hungteen.pvz.common.entity.zombie.grass.TombStoneEntity;
import com.hungteen.pvz.common.event.PVZServerEvents;
import com.hungteen.pvz.common.world.data.PVZInvasionData;
import com.hungteen.pvz.common.world.invasion.OverworldInvasion;

import net.minecraft.world.server.ServerWorld;

public class InvasionCache {

	public static final Set<ZombieType> ZOMBIE_INVADE_SET = new HashSet<>();
	public static int InvasionDifficulty = 0;
	
	/**
	 * only run when world server start.
	 * {@link PVZServerEvents#serverInit(net.minecraftforge.fml.event.server.FMLServerStartingEvent)}
	 */
	public static void syncStartInvasionCache(ServerWorld world) {
		OverworldInvasion.syncStartSpawnList(world);
		PVZInvasionData data = PVZInvasionData.getOverWorldInvasionData(world);
		data.addCurrentDifficulty(0);//sync difficulty.
	}
	
	/**
	 * only run when world server shut down.
	 * {@link PVZServerEvents#serverShutDown(net.minecraftforge.fml.event.server.FMLServerStoppingEvent)}
	 */
	public static void syncEndInvasionCache(ServerWorld world) {
		OverworldInvasion.syncEndSpawnList(world);
	}
	
	/**
	 * {@link TombStoneEntity#summonZombie()}
	 */
	public static List<ZombieType> getOrDefaultZombieList(List<ZombieType> list){
		return ZOMBIE_INVADE_SET.isEmpty() ? list : ZOMBIE_INVADE_SET.stream().collect(Collectors.toList());
	}
	
	public static int getInvasionDifficulty() {
		return InvasionDifficulty;
	}
}
