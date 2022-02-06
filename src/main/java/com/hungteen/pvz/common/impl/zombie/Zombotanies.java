package com.hungteen.pvz.common.impl.zombie;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.client.model.entity.zombie.zombotany.*;
import com.hungteen.pvz.common.impl.RankTypes;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.misc.PVZLoot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Zombotanies extends ZombieType {

	private static final List<IZombieType> LIST = new ArrayList<>();
	
	/*
	 * zombotany.
	 */
	public static final ZombieType PEASHOOTER_ZOMBIE = new Zombotanies("peashooter_zombie", new ZombieFeatures()
		    .rank(RankTypes.GREEN).xp(8)
			.entityType(() -> EntityRegister.PEASHOOTER_ZOMBIE.get())
			.zombieModel(() -> PeaShooterZombieModel::new).scale(0.5F)
			.loot(PVZLoot.PEASHOOTER_ZOMBIE)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType WALLNUT_ZOMBIE = new Zombotanies("wallnut_zombie", new ZombieFeatures()
		    .rank(RankTypes.GREEN).xp(25)
			.entityType(() -> EntityRegister.WALLNUT_ZOMBIE.get())
			.zombieModel(() -> WallNutZombieModel::new).scale(0.5F)
			.loot(PVZLoot.WALLNUT_ZOMBIE)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType GATLINGPEA_ZOMBIE = new Zombotanies("gatlingpea_zombie", new ZombieFeatures()
		    .rank(RankTypes.PURPLE).xp(10)
			.entityType(() -> EntityRegister.GATLINGPEA_ZOMBIE.get())
			.zombieModel(() -> GatlingPeaZombieModel::new).scale(0.5F)
			.loot(PVZLoot.GATLINGPEA_ZOMBIE)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType SQUASH_ZOMBIE = new Zombotanies("squash_zombie", new ZombieFeatures()
		    .rank(RankTypes.BLUE).xp(10)
			.entityType(() -> EntityRegister.SQUASH_ZOMBIE.get())
			.zombieModel(() -> SquashZombieModel::new).scale(0.5F)
			.loot(PVZLoot.SQUASH_ZOMBIE)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType JALAPENO_ZOMBIE = new Zombotanies("jalapeno_zombie", new ZombieFeatures()
		    .rank(RankTypes.GOLD).xp(12)
			.entityType(() -> EntityRegister.JALAPENO_ZOMBIE.get())
			.zombieModel(() -> JalapenoZombieModel::new).scale(0.5F)
			.loot(PVZLoot.JALAPENO_ZOMBIE)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType TALLNUT_ZOMBIE = new Zombotanies("tallnut_zombie", new ZombieFeatures()
		    .rank(RankTypes.PURPLE).xp(36)
			.entityType(() -> EntityRegister.TALLNUT_ZOMBIE.get())
			.zombieModel(() -> TallNutZombieModel::new).scale(0.5F)
			.loot(PVZLoot.TALLNUT_ZOMBIE)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType PUMPKIN_ZOMBIE = new Zombotanies("pumpkin_zombie", new ZombieFeatures()
		    .rank(RankTypes.PURPLE).xp(33)
			.entityType(() -> EntityRegister.PUMPKIN_ZOMBIE.get())
			.zombieModel(() -> PumpkinZombieModel::new).scale(0.5F)
			.loot(PVZLoot.PUMPKIN_ZOMBIE)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static void register() {
		registerZombies(LIST);
	}
	
	private Zombotanies(String name, ZombieFeatures features) {
		super(name, features);
		LIST.add(this);
	}

	@Override
	public int getSortPriority() {
		return 50;
	}

	@Override
	public String getCategoryName() {
		return "zombotany";
	}

	@Override
	public String getModID() {
		return PVZMod.MOD_ID;
	}

}
