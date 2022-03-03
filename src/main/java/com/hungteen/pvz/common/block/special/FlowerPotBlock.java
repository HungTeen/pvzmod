package com.hungteen.pvz.common.block.special;

import com.hungteen.pvz.common.block.AbstractFacingBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.MobEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class FlowerPotBlock extends AbstractFacingBlock {

	protected static final VoxelShape LILY_PAD_AABB = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 8.0D, 14.0D);
	
	public FlowerPotBlock() {
		super(Block.Properties.copy(Blocks.FLOWER_POT).strength(1F).noOcclusion());
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return LILY_PAD_AABB;
	}

	@Override
	public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, MobEntity entity) {
		return PathNodeType.BLOCKED;
	}

}
