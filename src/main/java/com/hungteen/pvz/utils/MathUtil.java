package com.hungteen.pvz.utils;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class MathUtil {

	public static AxisAlignedBB getAABBWithPos(BlockPos pos, double len) {
		return new AxisAlignedBB(pos.getX() + 0.5D - len, pos.getY() + 0.5D - len, pos.getZ() - len, pos.getX() + 0.5D + len, pos.getY() + 0.5D + len, pos.getZ() + 0.5D + len);
	}
	
	public static double getPosDisToVec(BlockPos pos, Vec3d vec) {
		Vec3d now = new Vec3d(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
		return vec.distanceTo(now);
	}
}
