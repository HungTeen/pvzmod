package com.hungteen.pvz.common.block.special;

import com.hungteen.pvz.common.block.AbstractFacingBlock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.MobEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.Mth;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class LanternBlock extends AbstractFacingBlock {

	private static final VoxelShape SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 24.0D, 13.0D);
	
	public LanternBlock() {
		super(Block.Properties.copy(Blocks.LANTERN).strength(5, 9).lightLevel(i -> 14).noOcclusion());
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, Mth pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, Mth pos, MobEntity entity) {
		return PathNodeType.FENCE;
	}
	
}
