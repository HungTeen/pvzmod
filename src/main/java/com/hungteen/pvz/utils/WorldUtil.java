package com.hungteen.pvz.utils;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class WorldUtil {

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
