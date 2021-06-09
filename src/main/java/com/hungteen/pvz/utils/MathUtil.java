package com.hungteen.pvz.utils;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public class MathUtil {

	public static AxisAlignedBB getAABBWithPos(BlockPos pos, double len) {
		return new AxisAlignedBB(pos.getX() + 0.5D - len, pos.getY() + 0.5D - len, pos.getZ() - len, pos.getX() + 0.5D + len, pos.getY() + 0.5D + len, pos.getZ() + 0.5D + len);
	}
	
	public static double getPosDisToVec(BlockPos pos, Vector3d vec) {
		Vector3d now = new Vector3d(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
		return vec.distanceTo(now);
	}
	
	public static Vector3d getHorizontalNormalizedVec(Vector3d a, Vector3d b) {
		return new Vector3d(a.x - a.x, 0, b.z - a.z).normalize();
	}
	
}
