package com.hungteen.pvz.utils;

import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

import java.util.Random;

public class MathUtil {

	public static int getProgressByDif(int segLen, int dif, int now, int max, int from, int to) {
		return MathHelper.floor(getProgressByDif(segLen, dif, now, max, from, to * 1F));
	}
	
	public static float getProgressByDif(int segLen, float dif, int now, int max, float from, float to) {
		if(now + segLen > max) {
			return to;
		}
		return from + (now - 1) / segLen * dif;
	}
	
	/**
	 * get average amount in (from, to). <br>
	 * the value at pos 1 is from. <br>
	 * the value at pos max is to.
	 */
	public static int getProgressAverage(int now, int max, int from, int to) {
		return MathHelper.floor(getProgressAverage(now, max, from, to * 1F));
	}
	
	/**
	 * {@link PlantShooterEntity#getAttackDamage()}
	 */
	public static float getProgressAverage(int now, int max, float from, float to) {
		return getProgressByDif(1, (to - from) / max, now, max, from, to);
	}

	/**
	 * from 0 to len, value varies in (from, to).
	 */
	public static int getIncreaseAverage(int now, int len, int from, int to){
		return from + (to - from) * now / len;
	}

	public static float getIncreaseAverage(int now, int len, float from, float to){
		return from + (to - from) * now / len;
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
	
	/**
	 * get random from - 1 to 1.
	 */
	public static float getRandomFloat(Random rand) {
		return (rand.nextFloat() - 0.5F) * 2;
	}
	
	public static AxisAlignedBB getAABBWithPos(BlockPos pos, double len) {
		return new AxisAlignedBB(pos.getX() + 0.5D - len, pos.getY() + 0.5D - len, pos.getZ() - len, pos.getX() + 0.5D + len, pos.getY() + 0.5D + len, pos.getZ() + 0.5D + len);
	}
	
	public static double getPosDisToVec(BlockPos pos, Vector3d vec) {
		final Vector3d now = new Vector3d(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
		return vec.distanceTo(now);
	}
	
	public static Vector3d getHorizontalNormalizedVec(Vector3d a, Vector3d b) {
		return getHorizontalVec(a, b).normalize();
	}
	
	public static Vector3d getHorizontalVec(Vector3d a, Vector3d b) {
		return new Vector3d(b.x - a.x, 0, b.z - a.z);
	}
	
	public static Vector3d toVector(BlockPos pos) {
		return new Vector3d(pos.getX(), pos.getY(), pos.getZ());
	}
	
	public static Vector3d getVector3dBy2(double x, double z) {
		return new Vector3d(x, 0, z);
	}
	
	/**
	 * use for render bar.
	 */
	public static int getBarLen(int num, int maxNum, int maxLen) {
		final int percent = num * maxLen / maxNum;
		if(percent == 0 && num != 0) {
			return 1;
		} else if(percent == maxLen && num != maxNum) {
			return maxLen - 1;
		}
		return percent;
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
	
	public static boolean randDouble(Random rand, double value) {
		return rand.nextDouble() < value;
	}

	public static boolean isInArea(int x, int y, int posX, int posY, int xLen, int yLen){
		return x >= posX && x <= posX + xLen && y >= posY && y <= posY + yLen;
	}
	
}
