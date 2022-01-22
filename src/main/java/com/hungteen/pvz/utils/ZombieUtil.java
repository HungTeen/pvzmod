package com.hungteen.pvz.utils;

import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.grass.DancingZombieEntity;
import com.hungteen.pvz.common.impl.zombie.GrassZombies;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZombieUtil {
	
	public static final List<IZombieType> DEFAULT_ZOMBIES = new ArrayList<>(Arrays.asList(
			GrassZombies.NORMAL_ZOMBIE, GrassZombies.CONEHEAD_ZOMBIE, GrassZombies.BUCKETHEAD_ZOMBIE, 
			GrassZombies.SCREENDOOR_ZOMBIE, GrassZombies.NEWSPAPER_ZOMBIE));
	
    // move speed
	public static final float WALK_SUPER_SLOW = 0.15F;
	public static final float WALK_HUGE_SLOW = 0.16F; 
	public static final float WALK_VERY_SLOW = 0.17F; 
	public static final float WALK_SLOW = 0.18F;
	public static final float WALK_LITTLE_SLOW = 0.19F; 
	public static final float WALK_NORMAL = 0.20F; 
	public static final float WALK_LITTLE_FAST = 0.225F;
	public static final float WALK_FAST = 0.25F; 
	public static final float WALK_VERY_FAST = 0.275F; 
	public static final float WALK_HUGE_FAST = 0.3F; 
	public static final float WALK_SUPER_FAST = 0.325F; 
	public static final float WATER_FAST = 0.8F;
	public static final float FLY_FAST = 0.5F;

	// attack damage
	public static final float HUGE_LOW = 3;
	public static final float VERY_LOW = 4;
	public static final float LOW = 5;
	public static final float LITTLE_LOW = 7;
	public static final float NORMAL_DAMAGE = 10;
	public static final float LITTLE_HIGH = 15;
	public static final float HIGH = 20;
	public static final float VERY_HIGH = 30;
	public static final float HUGE_HIGH = 50;
	public static final float SUPER_HIGH = 100;

	// range
	public static final float CLOSE_TARGET_RANGE = 40;
	public static final float LITTLE_CLOSE_TARGET_RANGE = 50;
	public static final float NORMAL_TARGET_RANGE = 60;
	public static final float LITTLE_FAR_TARGET_RANGE = 70;
	public static final float LOW_TARGET_HEIGHT = 20;
	public static final float NORMAL_TARGET_HEIGHT = 30;
	public static final float LITTLE_HIGH_TARGET_HEIGHT = 40;
	public static final float HIGH_TARGET_HEIGHT = 50;
	
	/**
	 * copy some data to new zombie ,which is necessary to implement.
	 * {@link DancingZombieEntity#summonEmptyDancers()}
	 */
	public static void copySummonZombieData(PVZZombieEntity old, PVZZombieEntity now) {
		now.setCharmed(old.isCharmed());
		now.setMiniZombie(old.isMiniZombie());
	}
	
	public static void onZombieSpawn(PVZZombieEntity old, PVZZombieEntity now, BlockPos pos) {
		ZombieUtil.copySummonZombieData(old, now);
		EntityUtil.onEntitySpawn(old.level, now, pos);
	}
	
//	public static int caculateZombieLevel(PVZZombieEntity zombie) {
//		final int difficulty = InvasionManager.getInvasionDifficulty() - 100;
//		final int maxLevel = PVZConfig.COMMON_CONFIG.EntitySettings.ZombieSetting.ZombieMaxLevel.get();
//		final int minLvl = MathHelper.clamp(difficulty / 50 + 1, 1, maxLevel);
//		final int maxLvl = MathHelper.clamp(difficulty / 30 + 2, 1, maxLevel);
//		return MathUtil.getRandomMinMax(zombie.getRandom(), minLvl, maxLvl);
//	}
	
}
