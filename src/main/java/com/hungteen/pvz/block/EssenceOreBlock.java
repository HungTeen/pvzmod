package com.hungteen.pvz.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;

public class EssenceOreBlock extends Block{

	public EssenceOreBlock() {
		super(Block.Properties.from(Blocks.DIAMOND_ORE).hardnessAndResistance(9, 9).lightValue(10).harvestLevel(3));
	}
	
	@Override
	public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
		return MathHelper.nextInt(this.RANDOM, 2, 5);
	}

}
