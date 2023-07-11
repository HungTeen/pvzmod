package com.hungteen.pvz.common.world.spawn;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class SpawnChecker {

	/**
	 * no need to check invasion spawn list.
	 * {@link EntitySpawnRegister#registerEntitySpawns(net.minecraftforge.event.RegistryEvent.Register)}
	 */
	public static boolean canZombieSpawn(EntityType<? extends PVZZombieEntity> zombieType, IWorld worldIn,
			SpawnReason reason, BlockPos pos, Random rand) {
		return checkSpawn(zombieType, worldIn, reason, pos, rand);
	}

	public static boolean canLavaZombieSpawn(EntityType<? extends PVZZombieEntity> zombieType, IWorld worldIn,
										 SpawnReason reason, BlockPos pos, Random rand) {
		return worldIn.getDifficulty() == Difficulty.HARD && checkSpawn(zombieType, worldIn, reason, pos, rand);
	}

	public static boolean canTombStoneSpawn(EntityType<? extends PVZZombieEntity> zombieType, IWorld worldIn,
										 SpawnReason reason, BlockPos pos, Random rand) {
		return (int) worldIn.dayTime() % 24000 > 14000 && checkSpawn(zombieType, worldIn, reason, pos, rand);
	}
	
//	/**
//	 * is not natural spawn or in invasion spawn list.
//	 * {@link EntitySpawnRegister#registerEntitySpawns(net.minecraftforge.event.RegistryEvent.Register)}
//	 */
//	public static boolean canGroundInvasionZombieSpawn(EntityType<? extends PVZZombieEntity> zombieType, IWorld worldIn,
//			SpawnReason reason, BlockPos pos, Random rand) {
//		return checkSpawn(zombieType, worldIn, reason, pos, rand);
//	}
//
//	/**
//	 * no need to check invasion spawn list.
//	 * {@link EntitySpawnRegister#registerEntitySpawns(net.minecraftforge.event.RegistryEvent.Register)}
//	 */
//	public static boolean canNightZombieSpawn(EntityType<? extends PVZZombieEntity> zombieType, IWorld worldIn,
//			SpawnReason reason, BlockPos pos, Random rand) {
//		return (worldIn instanceof ServerWorld && ! ((ServerWorld) worldIn).isDay()) && checkSpawn(zombieType, worldIn, reason, pos, rand);
//	}
	
//	/**
//	 * is not natural spawn or in invasion spawn list. <br>
//	 * can spawn in water face. <br>
//	 * {@link EntitySpawnRegister#registerEntitySpawns(net.minecraftforge.event.RegistryEvent.Register)}
//	 */
//	public static boolean canWaterInvasionZombieSpawn(EntityType<? extends PVZZombieEntity> zombieType, IWorld worldIn,
//			SpawnReason reason, BlockPos pos, Random rand) {
//		return checkLightAndDifficulty(worldIn, pos)
//				&& (reason == SpawnReason.SPAWNER || isInWater(worldIn, pos));
//	}
	
//	/**
//	 * is not natural spawn or in invasion spawn list. <br>
//	 * can spawn in not so high Sky. <br>
//	 * {@link EntitySpawnRegister#registerEntitySpawns(net.minecraftforge.event.RegistryEvent.Register)}
//	 */
//	public static boolean canSkyInvasionZombieSpawn(EntityType<? extends PVZZombieEntity> zombieType, IWorld worldIn,
//			SpawnReason reason, BlockPos pos, Random rand) {
//		return checkLightAndDifficulty(worldIn, pos) && (reason == SpawnReason.SPAWNER || canSeeSky(worldIn, pos));
//	}
	
	/**
	 * is not natural spawn or in invasion spawn list or is in thundering. <br>
	 * can spawn in not so high Sky. <br>
	 * {@link EntitySpawnRegister#registerEntitySpawns(net.minecraftforge.event.RegistryEvent.Register)}
	 */
	public static boolean canYetiSpawn(EntityType<? extends PVZZombieEntity> zombieType, IWorld worldIn,
			SpawnReason reason, BlockPos pos, Random rand) {
		if(worldIn instanceof ServerWorld && canZombieSpawn(zombieType, worldIn, reason, pos, rand)) {
			return ((ServerWorld) worldIn).isThundering() && ! ((ServerWorld) worldIn).isDay() && rand.nextInt(3) == 0;
		}
		return false;
	}
	
	private static boolean checkSpawn(EntityType<? extends CreatureEntity> zombieType, IWorld worldIn, SpawnReason reason, BlockPos pos, Random rand) {
		return isDarkEnough(worldIn, pos) && worldIn.getDifficulty() != Difficulty.PEACEFUL && MobEntity.checkMobSpawnRules(zombieType, worldIn, reason, pos, rand);
	}
//
//	private static boolean isInWater(IWorld world, BlockPos pos) {
//		return world.getFluidState(pos.below()).is(FluidTags.WATER);
//	}
//
//	private static boolean canSeeSky(IWorld world, BlockPos pos) {
//	      return world.canSeeSky(pos);
//	}
//
//	private static boolean checkLightAndDifficulty(IWorld worldIn, BlockPos pos) {
//		return worldIn.getDifficulty() != Difficulty.PEACEFUL && isDarkEnough(worldIn, pos);
//	}
	
	private static boolean isDarkEnough(IWorld worldIn, BlockPos pos) {
		return worldIn.getBrightness(LightType.BLOCK, pos) < 7;
	}
	
}
