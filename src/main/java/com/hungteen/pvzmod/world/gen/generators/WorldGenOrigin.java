package com.hungteen.pvzmod.world.gen.generators;

import java.util.Random;

import com.hungteen.pvzmod.registry.BlockRegister;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenOrigin extends WorldGenerator{

	public static final IBlockState ori=BlockRegister.THE_ORIGIN.getDefaultState();
	
	public WorldGenOrigin() {
		
	}
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		return false;
	}

}
