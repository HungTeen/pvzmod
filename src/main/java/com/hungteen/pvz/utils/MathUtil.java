package com.hungteen.pvz.utils;

import java.util.Random;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public class MathUtil {

	public static int getProgressAverage(int now, int max, int from, int to) {
		if(now >= max) {
			return to;
		}
		return from + (now - 1) * (to - from) / max;
	}
	
	public static float getProgressAverage(int now, int max, float from, float to) {
		if(now >= max) {
			return to;
		}
		return from + (now - 1) * (to - from) / max;
	}
	
	/**
	 * gen random from min to max.
	 */
	public static int getRandomMinMax(Random rand, int min, int max) {
		return rand.nextInt(max - min + 1) + min;
	}
	
	/**
	 * get random from - range to range.
	 */
	public static int getRandomInRange(Random rand, int range) {
		return rand.nextInt(range << 1 | 1) - range;
	}
	
	public static AxisAlignedBB getAABBWithPos(BlockPos pos, double len) {
		return new AxisAlignedBB(pos.getX() + 0.5D - len, pos.getY() + 0.5D - len, pos.getZ() - len, pos.getX() + 0.5D + len, pos.getY() + 0.5D + len, pos.getZ() + 0.5D + len);
	}
	
	public static double getPosDisToVec(BlockPos pos, Vector3d vec) {
		final Vector3d now = new Vector3d(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
		return vec.distanceTo(now);
	}
	
	public static Vector3d getHorizontalNormalizedVec(Vector3d a, Vector3d b) {
		return new Vector3d(b.x - a.x, 0, b.z - a.z).normalize();
	}
	
	/**
	 * it ignores y position.
	 */
	public static BlockPos getRandomRangePos(Random rand, int range) {
		return getRandomRangePos(rand, 0, range);
	}
	
	/**
	 * it ignores y position.
	 */
	public static BlockPos getRandomRangePos(Random rand, int minR, int maxR) {
		final int x = (rand.nextInt(2) == 0 ? -1 : 1) * getRandomMinMax(rand, minR, maxR);
		final int z = (rand.nextInt(2) == 0 ? -1 : 1) * getRandomMinMax(rand, minR, maxR);
		return new BlockPos(x, 0, z);
	}
	
}
