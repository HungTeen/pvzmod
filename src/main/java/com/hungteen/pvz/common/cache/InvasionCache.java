package com.hungteen.pvz.common.cache;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.hungteen.pvz.common.entity.zombie.grassnight.TombStoneEntity;
import com.hungteen.pvz.utils.enums.Zombies;

public class InvasionCache {

	public static final Set<Zombies> ZOMBIE_INVADE_SET = new HashSet<>();
	
	/**
	 * {@link TombStoneEntity#summonZombie()}
	 */
	public static List<Zombies> getOrDefaultZombieList(List<Zombies> list){
		return ZOMBIE_INVADE_SET.isEmpty() ? list : ZOMBIE_INVADE_SET.stream().collect(Collectors.toList());
	}
}
