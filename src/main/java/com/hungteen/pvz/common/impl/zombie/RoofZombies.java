package com.hungteen.pvz.common.impl.zombie;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.client.model.entity.zombie.roof.*;
import com.hungteen.pvz.common.impl.RankTypes;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.misc.PVZLoot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class RoofZombies extends ZombieType {

	private static final List<IZombieType> LIST = new ArrayList<>();
	
	/*
	 * roof.
	 */
	public static final ZombieType BUNGEE_ZOMBIE = new RoofZombies("bungee_zombie", new ZombieFeatures()
		    .rank(RankTypes.GREEN).xp(20)
			.entityType(() -> EntityRegister.BUNGEE_ZOMBIE.get())
			.scale(0.5F)
			.loot(PVZLoot.BUNGEE_ZOMBIE)
			.commonSkill(Arrays.asList())
	);
	
	public static final ZombieType LADDER_ZOMBIE = new RoofZombies("ladder_zombie", new ZombieFeatures()
		    .rank(RankTypes.GREEN).xp(15)
			.entityType(() -> EntityRegister.LADDER_ZOMBIE.get())
			.zombieModel(() -> LadderZombieModel::new).scale(0.5F)
			.loot(PVZLoot.LADDER_ZOMBIE)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType CATAPULT_ZOMBIE = new RoofZombies("catapult_zombie", new ZombieFeatures()
		    .rank(RankTypes.BLUE).xp(32)
			.entityType(() -> EntityRegister.CATAPULT_ZOMBIE.get())
			.zombieModel(() -> CatapultZombieModel::new).scale(0.5F)
			.commonSkill(Arrays.asList())
	);
	
	public static final ZombieType GARGANTUAR = new RoofZombies("gargantuar", new ZombieFeatures()
		    .rank(RankTypes.PURPLE).xp(40)
			.entityType(() -> EntityRegister.GARGANTUAR.get())
			.zombieModel(() -> GargantuarModel::new).scale(0.75F)
			.loot(PVZLoot.GARGANTUAR)
			.commonSkill(Arrays.asList())
	);
	
	public static final ZombieType IMP = new RoofZombies("imp", new ZombieFeatures()
		    .rank(RankTypes.GRAY).xp(2)
			.entityType(() -> EntityRegister.IMP.get())
			.zombieModel(() -> ImpModel::new).scale(0.5F)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType GIGA_GARGANTUAR = new RoofZombies("giga_gargantuar", new ZombieFeatures()
		    .rank(RankTypes.RED).xp(55)
			.entityType(() -> EntityRegister.GIGA_GARGANTUAR.get())
			.zombieModel(() -> GargantuarModel::new).scale(0.8F)
			.loot(PVZLoot.GIGA_GARGANTUAR)
			.commonSkill(Arrays.asList())
	);
	
	public static final ZombieType EDGAR_090505 = new RoofZombies("edgar_090505", new ZombieFeatures()
		    .rank(RankTypes.MEGA).xp(1000)
			.entityType(() -> EntityRegister.EDGAR_090505.get())
			.zombieModel(() -> EdgarRobotModel::new).scale(1.6F)
			.loot(PVZLoot.EDGAR_090505)
			.commonSkill(Arrays.asList())
	);

	public static final ZombieType EDGAR_090517 = new RoofZombies("edgar_090517", new ZombieFeatures()
			.rank(RankTypes.MEGA).xp(2000)
			.entityType(() -> EntityRegister.EDGAR_090517.get())
			.zombieModel(() -> EdgarRobotModel::new).scale(2F)
			.loot(PVZLoot.EDGAR_090517)
			.commonSkill(Arrays.asList())
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
