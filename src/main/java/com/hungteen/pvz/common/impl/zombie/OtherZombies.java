package com.hungteen.pvz.common.impl.zombie;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.client.model.entity.zombie.other.RaZombieModel;
import com.hungteen.pvz.common.impl.RankTypes;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.misc.PVZLoot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class OtherZombies extends ZombieType {

	private static final List<IZombieType> LIST = new ArrayList<>();
	
	/*
	 * egypt.
	 */
	public static final ZombieType RA_ZOMBIE = new OtherZombies("ra_zombie", new ZombieFeatures()
		    .rank(RankTypes.GREEN).xp(10)
			.entityType(() -> EntityRegister.RA_ZOMBIE.get())
			.zombieModel(() -> RaZombieModel::new).scale(0.5F)
			.loot(PVZLoot.RA_ZOMBIE)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static void register() {
		registerZombies(LIST);
	}
	
	private OtherZombies(String name, ZombieFeatures features) {
		super(name, features);
		LIST.add(this);
	}

	@Override
	public int getSortPriority() {
		return 70;
	}

	@Override
	public String getCategoryName() {
		return "other";
	}

	@Override
	public String getModID() {
		return PVZMod.MOD_ID;
	}

}
