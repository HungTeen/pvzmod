package com.hungteen.pvz.common.block;

import com.hungteen.pvz.common.impl.misc.EssenceType;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class EssenceOreBlock extends Block{

	public final EssenceType essence;
	
	public EssenceOreBlock(EssenceType e) {
		super(Block.Properties.copy(Blocks.DIAMOND_ORE)
				.strength(9, 9)
				.harvestTool(ToolType.PICKAXE)
				.requiresCorrectToolForDrops()
				.lightLevel(i -> 10)
				.harvestLevel(3));
		this.essence = e;
	}
	
	@Override
	public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
		return MathHelper.nextInt(this.RANDOM, 1, 2);
	}

}
