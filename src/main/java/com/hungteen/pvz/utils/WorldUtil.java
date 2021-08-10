package com.hungteen.pvz.utils;

import com.hungteen.pvz.common.entity.misc.ZombieHandEntity;

import net.minecraft.particles.IParticleData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap.Type;

public class WorldUtil {

	/**
	 * @param pos center position.
	 * @param minR minimum distance.
	 * @param maxR maximum distance.
	 * @return result position.
	 */
	public static BlockPos getSuitableHeightRandomPos(World world, BlockPos pos, int minR, int maxR) {
		BlockPos offset = MathUtil.getRandomRangePos(world.random, minR, maxR);
		return getSuitableHeightPos(world, pos.offset(offset.getX(), 0, offset.getZ()));
	}
	
	public static BlockPos getSuitableHeightRandomPos(World world, BlockPos pos, int maxR) {
		return getSuitableHeightRandomPos(world, pos, 0, maxR);
	}
	
	/**
	 * {@link ZombieHandEntity#spawnRangeZombieHands(World, com.hungteen.pvz.common.entity.zombie.PVZZombieEntity, int)}
	 */
	public static BlockPos getSuitableHeightPos(World world, BlockPos pos) {
		int y = world.getHeight(Type.MOTION_BLOCKING_NO_LEAVES, pos.getX(), pos.getZ());
		return new BlockPos(pos.getX(), y, pos.getZ());
	}
	
	/**
	 * Spawn Random speed Particle at pos.
	 * {@link EntityUtil#spawnStaticParticle(net.minecraft.entity.Entity, IParticleData)}
	 */
	public static void spawnRandomSpeedParticle(World world, IParticleData type, Vector3d pos, float speed) {
		spawnRandomSpeedParticle(world, type, pos, speed, speed);
	}
	
	/**
	 * Spawn Random speed Particle at pos.
	 */
	public static void spawnRandomSpeedParticle(World world, IParticleData type, Vector3d pos, float horizontalSpeed, float verticalSpeed) {
		final float speedX = (world.random.nextFloat() - 0.5F) * horizontalSpeed * 2;
		final float speedY = (world.random.nextFloat() - 0.5F) * verticalSpeed * 2;
		final float speedZ = (world.random.nextFloat() - 0.5F) * horizontalSpeed * 2;
		world.addParticle(type, pos.x, pos.y, pos.z, speedX, speedY, speedZ);
	}
	
	@SuppressWarnings("deprecation")
	public static int calculateGenHeight(IWorld worldIn, int x, int z){
		int y = worldIn.getMaxBuildHeight();
		boolean foundGround = false;
		while(!foundGround && y-- >= worldIn.getSeaLevel()-1){
			foundGround = worldIn.getBlockState(new BlockPos(x,y,z)).canOcclude();
		}
		return y;
	}
	
}
