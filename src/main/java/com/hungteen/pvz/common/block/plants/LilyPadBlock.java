package com.hungteen.pvz.common.block.plants;


import com.hungteen.pvz.common.entity.plant.spear.CatTailEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.pool.ZombieDolphinEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class LilyPadBlock extends BushBlock {

	public static final DirectionProperty FACING = HorizontalBlock.FACING;
	protected static final VoxelShape LILY_PAD_AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 1.5D, 15.0D);

	public LilyPadBlock() {
		super(Block.Properties.copy(Blocks.LILY_PAD));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@SuppressWarnings("deprecation")
	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		super.entityInside(state, worldIn, pos, entityIn);
		if (worldIn instanceof ServerWorld) {
			if((entityIn instanceof PVZZombieEntity && !(entityIn instanceof ZombieDolphinEntity))) {
			    if(entityIn.isAlive() && ((PVZZombieEntity) entityIn).canBreakPlantBlock()) {
			        worldIn.destroyBlock(new BlockPos(pos), true, entityIn);
			    }
			} else if(entityIn instanceof CatTailEntity && ! ((CatTailEntity) entityIn).isImmuneToWeak()){
				 worldIn.destroyBlock(new BlockPos(pos), true, entityIn);
			}
		}
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return LILY_PAD_AABB;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected boolean mayPlaceOn(BlockState state, IBlockReader reader, BlockPos pos) {
	    return ! state.isAir(reader, pos);
	}

	public BlockState getStateForPlacement(PlayerEntity player) {
		if(player == null) return this.defaultBlockState();
		return this.defaultBlockState().setValue(FACING, player.getDirection().getOpposite());
	}
	
	public BlockState getStateForPlacement(Direction direction) {
		return this.defaultBlockState().setValue(FACING, direction);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		Direction direction = context.getHorizontalDirection().getOpposite();
		return this.defaultBlockState().setValue(FACING, direction);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
	
	@Override
	public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, MobEntity entity) {
		return PathNodeType.TRAPDOOR;
	}

}