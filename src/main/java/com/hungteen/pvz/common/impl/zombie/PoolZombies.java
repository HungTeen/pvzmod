package com.hungteen.pvz.common.impl.zombie;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.client.model.entity.zombie.pool.*;
import com.hungteen.pvz.common.impl.RankTypes;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.misc.PVZLoot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PoolZombies extends ZombieType {

	private static final List<IZombieType> LIST = new ArrayList<>();
	
	/*
	 * day.
	 */
	public static final ZombieType SNORKEL_ZOMBIE = new PoolZombies("snorkel_zombie", new ZombieFeatures()
		    .rank(RankTypes.WHITE).xp(5)
			.entityType(() -> EntityRegister.SNORKEL_ZOMBIE.get())
			.zombieModel(() -> SnorkelZombieModel::new).scale(0.5F)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType ZOMBONI = new PoolZombies("zomboni", new ZombieFeatures()
		    .rank(RankTypes.BLUE).xp(32)
			.entityType(() -> EntityRegister.ZOMBONI.get())
			.zombieModel(() -> ZomboniModel::new).scale(0.5F)
			.commonSkill(Arrays.asList())
	);
	
	public static final ZombieType BOBSLE_TEAM = new PoolZombies("bobsle_team", new ZombieFeatures()
		    .rank(RankTypes.GREEN).xp(10)
			.entityType(() -> EntityRegister.BOBSLE_TEAM.get())
			.zombieModel(() -> BobsleTeamModel::new).scale(0.5F)
			.loot(PVZLoot.BOBSLE_TEAM)
			.commonSkill(Arrays.asList())
	);
	
	public static final ZombieType BOBSLE_ZOMBIE = new PoolZombies("bobsle_zombie", new ZombieFeatures()
		    .rank(RankTypes.WHITE).xp(2)
			.entityType(() -> EntityRegister.BOBSLE_ZOMBIE.get())
			.zombieModel(() -> BobsleZombieModel::new).scale(0.5F)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType DOLPHIN_RIDER = new PoolZombies("dolphin_rider", new ZombieFeatures()
		    .rank(RankTypes.GREEN).xp(24)
			.entityType(() -> EntityRegister.DOLPHIN_RIDER.get())
			.scale(0.5F)
			.commonSkill(Arrays.asList())
	);
	
	public static final ZombieType DOLPHIN_RIDER_ZOMBIE = new PoolZombies("dolphin_rider_zombie", new ZombieFeatures()
		    .rank(RankTypes.WHITE).xp(8)
			.entityType(() -> EntityRegister.DOLPHIN_RIDER_ZOMBIE.get())
			.zombieModel(() -> DolphinRiderZombieModel::new).scale(0.5F)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType ZOMBIE_DOLPHIN = new PoolZombies("zombie_dolphin", new ZombieFeatures()
		    .rank(RankTypes.GRAY).xp(2)
			.entityType(() -> EntityRegister.ZOMBIE_DOLPHIN.get())
			.scale(0.5F)
			.loot(PVZLoot.ZOMBIE_DOLPHIN)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType JACK_IN_BOX_ZOMBIE = new PoolZombies("jack_in_box_zombie", new ZombieFeatures()
		    .rank(RankTypes.GREEN).xp(16)
			.entityType(() -> EntityRegister.JACK_IN_BOX_ZOMBIE.get())
			.zombieModel(() -> JackInBoxZombieModel::new).scale(0.5F)
			.loot(PVZLoot.JACK_IN_BOX_ZOMBIE)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType BALLOON_ZOMBIE = new PoolZombies("balloon_zombie", new ZombieFeatures()
		    .rank(RankTypes.BLUE).xp(10)
			.entityType(() -> EntityRegister.BALLOON_ZOMBIE.get())
			.zombieModel(() -> BalloonZombieModel::new).scale(0.5F)
			.loot(PVZLoot.BALLOON_ZOMBIE)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType DIGGER_ZOMBIE = new PoolZombies("digger_zombie", new ZombieFeatures()
		    .rank(RankTypes.BLUE).xp(20)
			.entityType(() -> EntityRegister.DIGGER_ZOMBIE.get())
			.zombieModel(() -> DiggerZombieModel::new).scale(0.5F)
			.loot(PVZLoot.DIGGER_ZOMBIE)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType POGO_ZOMBIE = new PoolZombies("pogo_zombie", new ZombieFeatures()
		    .rank(RankTypes.GREEN).xp(18)
			.entityType(() -> EntityRegister.POGO_ZOMBIE.get())
			.zombieModel(() -> PogoZombieModel::new).scale(0.5F)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType YETI_ZOMBIE = new PoolZombies("yeti_zombie", new ZombieFeatures()
		    .rank(RankTypes.PURPLE).xp(36)
			.entityType(() -> EntityRegister.YETI_ZOMBIE.get())
			.zombieModel(() -> YetiZombieModel::new).scale(0.5F)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static void register() {
		registerZombies(LIST);
	}
	
	private PoolZombies(String name, ZombieFeatures features) {
		super(name, features);
		LIST.add(this);
	}

	@Override
	public int getSortPriority() {
		return 95;
	}

	@Override
	public String getCategoryName() {
		return "pool";
	}

	@Override
	public String getModID() {
		return PVZMod.MOD_ID;
	}

}
