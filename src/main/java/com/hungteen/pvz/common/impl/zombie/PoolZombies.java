package com.hungteen.pvz.common.impl.zombie;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.client.model.entity.zombie.pool.*;
import com.hungteen.pvz.common.impl.Ranks;
import com.hungteen.pvz.common.impl.ZombieType;
import com.hungteen.pvz.register.EntityRegister;

import java.util.ArrayList;
import java.util.List;

public final class PoolZombies extends ZombieType {

	private static final List<IZombieType> LIST = new ArrayList<>();
	
	/*
	 * day.
	 */
	public static final ZombieType SNORKEL_ZOMBIE = new PoolZombies("snorkel_zombie", new ZombieFeatures()
			.difficulty(1).invasionWeight(100).waveWeight(60)
		    .rank(Ranks.WHITE).xp(5)
			.entityType(() -> EntityRegister.SNORKEL_ZOMBIE.get())
			.zombieModel(() -> SnorkelZombieModel::new).scale(0.5F)
	);
	
	public static final ZombieType ZOMBONI = new PoolZombies("zomboni", new ZombieFeatures()
			.difficulty(12).invasionWeight(9).waveWeight(40)
		    .rank(Ranks.BLUE).xp(32)
			.entityType(() -> EntityRegister.ZOMBONI.get())
			.zombieModel(() -> ZomboniModel::new).scale(0.5F)
	);
	
	public static final ZombieType BOBSLE_TEAM = new PoolZombies("bobsle_team", new ZombieFeatures()
			.difficulty(12).invasionWeight(0).waveWeight(50)
		    .rank(Ranks.GREEN).xp(10)
			.entityType(() -> EntityRegister.BOBSLE_TEAM.get())
			.zombieModel(() -> BobsleTeamModel::new).scale(0.5F)
	);
	
	public static final ZombieType BOBSLE_ZOMBIE = new PoolZombies("bobsle_zombie", new ZombieFeatures()
			.difficulty(0).invasionWeight(0).waveWeight(0)
		    .rank(Ranks.WHITE).xp(2)
			.entityType(() -> EntityRegister.BOBSLE_ZOMBIE.get())
			.zombieModel(() -> BobsleZombieModel::new).scale(0.5F)
	);
	
	public static final ZombieType DOLPHIN_RIDER = new PoolZombies("dolphin_rider", new ZombieFeatures()
			.difficulty(4).invasionWeight(80).waveWeight(0)
		    .rank(Ranks.GREEN).xp(24)
			.entityType(() -> EntityRegister.DOLPHIN_RIDER.get())
			.scale(0.5F)
	);
	
	public static final ZombieType DOLPHIN_RIDER_ZOMBIE = new PoolZombies("dolphin_rider_zombie", new ZombieFeatures()
			.difficulty(0).invasionWeight(0).waveWeight(0)
		    .rank(Ranks.WHITE).xp(8)
			.entityType(() -> EntityRegister.DOLPHIN_RIDER_ZOMBIE.get())
			.zombieModel(() -> DolphinRiderZombieModel::new).scale(0.5F)
	);
	
	public static final ZombieType ZOMBIE_DOLPHIN = new PoolZombies("zombie_dolphin", new ZombieFeatures()
			.difficulty(0).invasionWeight(0).waveWeight(0)
		    .rank(Ranks.GRAY).xp(2)
			.entityType(() -> EntityRegister.ZOMBIE_DOLPHIN.get())
			.scale(0.5F)
	);
	
	public static final ZombieType JACK_IN_BOX_ZOMBIE = new PoolZombies("jack_in_box_zombie", new ZombieFeatures()
			.difficulty(5).invasionWeight(80).waveWeight(40)
		    .rank(Ranks.GREEN).xp(16)
			.entityType(() -> EntityRegister.JACK_IN_BOX_ZOMBIE.get())
			.zombieModel(() -> JackInBoxZombieModel::new).scale(0.5F)
	);
	
	public static final ZombieType BALLOON_ZOMBIE = new PoolZombies("balloon_zombie", new ZombieFeatures()
			.difficulty(8).invasionWeight(90).waveWeight(40)
		    .rank(Ranks.BLUE).xp(10)
			.entityType(() -> EntityRegister.BALLOON_ZOMBIE.get())
			.zombieModel(() -> BalloonZombieModel::new).scale(0.5F)
	);
	
	public static final ZombieType DIGGER_ZOMBIE = new PoolZombies("digger_zombie", new ZombieFeatures()
			.difficulty(18).invasionWeight(90).waveWeight(40)
		    .rank(Ranks.BLUE).xp(20)
			.entityType(() -> EntityRegister.DIGGER_ZOMBIE.get())
			.zombieModel(() -> DiggerZombieModel::new).scale(0.5F)
	);
	
	public static final ZombieType POGO_ZOMBIE = new PoolZombies("pogo_zombie", new ZombieFeatures()
			.difficulty(7).invasionWeight(80).waveWeight(50)
		    .rank(Ranks.GREEN).xp(18)
			.entityType(() -> EntityRegister.POGO_ZOMBIE.get())
			.zombieModel(() -> PogoZombieModel::new).scale(0.5F)
	);
	
	public static final ZombieType YETI_ZOMBIE = new PoolZombies("yeti_zombie", new ZombieFeatures()
			.difficulty(0).invasionWeight(0).waveWeight(0)
		    .rank(Ranks.BLUE).xp(36)
			.entityType(() -> EntityRegister.YETI_ZOMBIE.get())
			.zombieModel(() -> YetiZombieModel::new).scale(0.5F)
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
