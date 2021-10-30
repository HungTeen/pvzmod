package com.hungteen.pvz.common.impl.zombie;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.client.model.entity.zombie.roof.*;
import com.hungteen.pvz.common.impl.Ranks;
import com.hungteen.pvz.common.impl.ZombieType;
import com.hungteen.pvz.register.EntityRegister;

import java.util.ArrayList;
import java.util.List;

public final class RoofZombies extends ZombieType {

	private static final List<IZombieType> LIST = new ArrayList<>();
	
	/*
	 * roof.
	 */
	public static final ZombieType BUNGEE_ZOMBIE = new RoofZombies("bungee_zombie", new ZombieFeatures()
			.difficulty(13).invasionWeight(80).waveWeight(50)
		    .rank(Ranks.GREEN).xp(20)
			.entityType(() -> EntityRegister.BUNGEE_ZOMBIE.get())
			.scale(0.5F)
	);
	
	public static final ZombieType LADDER_ZOMBIE = new RoofZombies("ladder_zombie", new ZombieFeatures()
			.difficulty(6).invasionWeight(90).waveWeight(50)
		    .rank(Ranks.GREEN).xp(15)
			.entityType(() -> EntityRegister.LADDER_ZOMBIE.get())
			.zombieModel(() -> LadderZombieModel::new).scale(0.5F)
	);
	
	public static final ZombieType CATAPULT_ZOMBIE = new RoofZombies("catapult_zombie", new ZombieFeatures()
			.difficulty(16).invasionWeight(85).waveWeight(40)
		    .rank(Ranks.BLUE).xp(32)
			.entityType(() -> EntityRegister.CATAPULT_ZOMBIE.get())
			.zombieModel(() -> CatapultZombieModel::new).scale(0.5F)
	);
	
	public static final ZombieType GARGANTUAR = new RoofZombies("gargantuar", new ZombieFeatures()
			.difficulty(21).invasionWeight(85).waveWeight(35)
		    .rank(Ranks.PURPLE).xp(40)
			.entityType(() -> EntityRegister.GARGANTUAR.get())
			.zombieModel(() -> GargantuarModel::new).scale(0.75F)
	);
	
	public static final ZombieType IMP = new RoofZombies("imp", new ZombieFeatures()
			.difficulty(2).invasionWeight(70).waveWeight(0)
		    .rank(Ranks.GRAY).xp(2)
			.entityType(() -> EntityRegister.IMP.get())
			.zombieModel(() -> ImpModel::new).scale(0.5F)
	);
	
	public static final ZombieType GIGA_GARGANTUAR = new RoofZombies("giga_gargantuar", new ZombieFeatures()
			.difficulty(33).invasionWeight(80).waveWeight(20)
		    .rank(Ranks.GOLD).xp(55)
			.entityType(() -> EntityRegister.GIGA_GARGANTUAR.get())
			.zombieModel(() -> GargantuarModel::new).scale(0.8F)
	);
	
	public static final ZombieType EDGAR_090505 = new RoofZombies("edgar_090505", new ZombieFeatures()
			.difficulty(0).invasionWeight(0).waveWeight(0)
		    .rank(Ranks.BOSS).xp(1000)
			.entityType(() -> EntityRegister.EDGAR_090505.get())
			.zombieModel(() -> Edgar090505Model::new).scale(2F)
	);
	
	public static void register() {
		registerZombies(LIST);
	}
	
	private RoofZombies(String name, ZombieFeatures features) {
		super(name, features);
		LIST.add(this);
	}

	@Override
	public int getSortPriority() {
		return 90;
	}

	@Override
	public String getCategoryName() {
		return "roof";
	}

	@Override
	public String getModID() {
		return PVZMod.MOD_ID;
	}

}
