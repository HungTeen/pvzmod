package com.hungteen.pvz.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;

public class EssenceOreBlock extends Block{

	public EssenceOreBlock() {
		super(Block.Properties.copy(Blocks.DIAMOND_ORE).strength(9, 9).lightLevel(i -> 10).harvestLevel(3));
	}
	
	@Override
	public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
		return MathHelper.nextInt(this.RANDOM, 2, 5);
	}

}
