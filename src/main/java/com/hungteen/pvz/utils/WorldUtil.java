package com.hungteen.pvz.utils;

import net.minecraft.util.math.BlockPos;
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
	
	public static BlockPos getSuitableHeightPos(World world, BlockPos pos) {
		int y = world.getHeight(Type.MOTION_BLOCKING_NO_LEAVES, pos.getX(), pos.getZ());
		return new BlockPos(pos.getX(), y, pos.getZ());
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
