package com.hungteen.pvz.common.impl.zombie;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.client.model.entity.zombie.roof.CatapultZombieModel;
import com.hungteen.pvz.client.model.entity.zombie.roof.Edgar090505Model;
import com.hungteen.pvz.client.model.entity.zombie.roof.GargantuarModel;
import com.hungteen.pvz.client.model.entity.zombie.roof.ImpModel;
import com.hungteen.pvz.client.model.entity.zombie.roof.LadderZombieModel;
import com.hungteen.pvz.common.impl.Ranks;
import com.hungteen.pvz.common.impl.ZombieType;
import com.hungteen.pvz.register.EntityRegister;

public final class RoofZombies extends ZombieType {

	private static final List<IZombieType> LIST = new ArrayList<>();
	
	/*
	 * roof.
	 */
	public static final ZombieType BUNGEE_ZOMBIE = new RoofZombies("bungee_zombie", new ZombieFeatures()
		    .rank(Ranks.GREEN).xp(20)
			.entityType(() -> EntityRegister.BUNGEE_ZOMBIE.get())
			.scale(0.5F)
	);
	
	public static final ZombieType LADDER_ZOMBIE = new RoofZombies("ladder_zombie", new ZombieFeatures()
		    .rank(Ranks.GREEN).xp(15)
			.entityType(() -> EntityRegister.LADDER_ZOMBIE.get())
			.zombieModel(() -> LadderZombieModel::new).scale(0.5F)
	);
	
	public static final ZombieType CATAPULT_ZOMBIE = new RoofZombies("catapult_zombie", new ZombieFeatures()
		    .rank(Ranks.BLUE).xp(32)
			.entityType(() -> EntityRegister.CATAPULT_ZOMBIE.get())
			.zombieModel(() -> CatapultZombieModel::new).scale(0.5F)
	);
	
	public static final ZombieType GARGANTUAR = new RoofZombies("gargantuar", new ZombieFeatures()
		    .rank(Ranks.PURPLE).xp(40)
			.entityType(() -> EntityRegister.GARGANTUAR.get())
			.zombieModel(() -> GargantuarModel::new).scale(0.75F)
	);
	
	public static final ZombieType IMP = new RoofZombies("imp", new ZombieFeatures()
		    .rank(Ranks.GRAY).xp(2)
			.entityType(() -> EntityRegister.IMP.get())
			.zombieModel(() -> ImpModel::new).scale(0.5F)
	);
	
	public static final ZombieType GIGA_GARGANTUAR = new RoofZombies("giga_gargantuar", new ZombieFeatures()
		    .rank(Ranks.GOLD).xp(55)
			.entityType(() -> EntityRegister.GIGA_GARGANTUAR.get())
			.zombieModel(() -> GargantuarModel::new).scale(0.8F)
	);
	
	public static final ZombieType EDGAR_090505 = new RoofZombies("edgar_090505", new ZombieFeatures()
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
