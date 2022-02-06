package com.hungteen.pvz.common.impl.zombie;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.client.model.entity.zombie.grass.*;
import com.hungteen.pvz.common.impl.RankTypes;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.misc.PVZLoot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class GrassZombies extends ZombieType {

	private static final List<IZombieType> LIST = new ArrayList<>();
	
	/*
	 * day.
	 */
	public static final ZombieType NORMAL_ZOMBIE = new GrassZombies("normal_zombie", new ZombieFeatures()
			.rank(RankTypes.WHITE).xp(5)
			.entityType(() -> EntityRegister.NORMAL_ZOMBIE.get())
			.zombieModel(() -> NormalZombieModel::new).scale(0.5F)
			.loot(PVZLoot.NORMAL_ZOMBIE)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType FLAG_ZOMBIE = new GrassZombies("flag_zombie", new ZombieFeatures()
		    .rank(RankTypes.WHITE).xp(10)
			.entityType(() -> EntityRegister.FLAG_ZOMBIE.get())
			.zombieModel(() -> FlagZombieModel::new).scale(0.5F)
			.loot(PVZLoot.FLAG_ZOMBIE)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType CONEHEAD_ZOMBIE = new GrassZombies("conehead_zombie", new ZombieFeatures()
		    .rank(RankTypes.GREEN).xp(20)
			.entityType(() -> EntityRegister.CONEHEAD_ZOMBIE.get())
			.zombieModel(() -> ConeHeadZombieModel::new).scale(0.5F)
			.loot(PVZLoot.CONEHEAD_ZOMBIE)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType POLE_ZOMBIE = new GrassZombies("pole_zombie", new ZombieFeatures()
		    .rank(RankTypes.GREEN).xp(25)
			.entityType(() -> EntityRegister.POLE_ZOMBIE.get())
			.zombieModel(() -> PoleZombieModel::new).scale(0.5F)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType BUCKETHEAD_ZOMBIE = new GrassZombies("buckethead_zombie", new ZombieFeatures()
		    .rank(RankTypes.BLUE).xp(30)
			.entityType(() -> EntityRegister.BUCKETHEAD_ZOMBIE.get())
			.zombieModel(() -> BucketHeadZombieModel::new).scale(0.5F)
			.loot(PVZLoot.BUCKETHEAD_ZOMBIE)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType NEWSPAPER_ZOMBIE = new GrassZombies("newspaper_zombie", new ZombieFeatures()
		    .rank(RankTypes.WHITE).xp(8)
			.entityType(() -> EntityRegister.NEWSPAPER_ZOMBIE.get())
			.zombieModel(() -> NewspaperZombieModel::new).scale(0.5F)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType TOMB_STONE = new GrassZombies("tomb_stone", new ZombieFeatures()
		    .rank(RankTypes.GRAY).xp(1)
			.entityType(() -> EntityRegister.TOMB_STONE.get())
			.scale(0.5F)
	);
	
	public static final ZombieType SCREENDOOR_ZOMBIE = new GrassZombies("screendoor_zombie", new ZombieFeatures()
		    .rank(RankTypes.GREEN).xp(10)
			.entityType(() -> EntityRegister.SCREENDOOR_ZOMBIE.get())
			.zombieModel(() -> ScreenDoorZombieModel::new).scale(0.5F)
			.loot(PVZLoot.SCREENDOOR_ZOMBIE)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType FOOTBALL_ZOMBIE = new GrassZombies("football_zombie", new ZombieFeatures()
		    .rank(RankTypes.BLUE).xp(32)
			.entityType(() -> EntityRegister.FOOTBALL_ZOMBIE.get())
			.zombieModel(() -> FootballZombieModel::new).scale(0.5F)
			.loot(PVZLoot.FOOTBALL_ZOMBIE)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType DANCING_ZOMBIE = new GrassZombies("dancing_zombie", new ZombieFeatures()
		    .rank(RankTypes.BLUE).xp(20)
			.entityType(() -> EntityRegister.DANCING_ZOMBIE.get())
			.zombieModel(() -> DancingZombieModel::new).scale(0.5F)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType BACKUP_DANCER = new GrassZombies("backup_dancer", new ZombieFeatures()
		    .rank(RankTypes.WHITE).xp(2)
			.entityType(() -> EntityRegister.BACKUP_DANCER.get())
			.zombieModel(() -> BackupDancerModel::new).scale(0.5F)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType GIGA_FOOTBALL_ZOMBIE = new GrassZombies("giga_football_zombie", new ZombieFeatures()
		    .rank(RankTypes.GOLD).xp(40)
			.entityType(() -> EntityRegister.GIGA_FOOTBALL_ZOMBIE.get())
			.zombieModel(() -> GigaFootballZombieModel::new).scale(0.5F)
			.loot(PVZLoot.GIGA_FOOTBALL_ZOMBIE)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType OLD_ZOMBIE = new GrassZombies("old_zombie", new ZombieFeatures()
		    .rank(RankTypes.BLUE).xp(25)
			.entityType(() -> EntityRegister.OLD_ZOMBIE.get())
			.zombieModel(() -> OldZombieModel::new).scale(0.5F)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static final ZombieType SUNDAY_EDITION_ZOMBIE = new GrassZombies("sunday_edition_zombie", new ZombieFeatures()
		    .rank(RankTypes.GOLD).xp(50)
			.entityType(() -> EntityRegister.SUNDAY_EDITION_ZOMBIE.get())
			.zombieModel(() -> SundayEditionZombieModel::new).scale(0.5F)
			.eatCommonSkill(Arrays.asList())
	);
	
	public static void register() {
		registerZombies(LIST);
	}
	
	private GrassZombies(String name, ZombieFeatures features) {
		super(name, features);
		LIST.add(this);
	}

	@Override
	public int getSortPriority() {
		return 100;
	}

	@Override
	public String getCategoryName() {
		return "grass";
	}

	@Override
	public String getModID() {
		return PVZMod.MOD_ID;
	}

}
