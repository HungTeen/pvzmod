package com.hungteen.pvz.common.block.plants;

import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.WorldUtil;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.HorizontalBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobEntity;
import net.minecraft.world.entity.passive.AnimalEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItemUseContext;
import net.minecraft.world.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolType;

public class ChomperBlock extends BushBlock{

	public static final DirectionProperty FACING = HorizontalBlock.FACING;
	private static final VoxelShape SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D);
	
	public ChomperBlock() {
		super(Block.Properties.copy(Blocks.PUMPKIN).noCollission().noOcclusion().harvestTool(ToolType.AXE).harvestLevel(2));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, Mth pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public void entityInside(BlockState state, Level worldIn, Mth pos, Entity entityIn) {
		if(entityIn instanceof AnimalEntity || entityIn instanceof Player) {
			if(!worldIn.isClientSide) {
				if(this.RANDOM.nextInt(50) == 0) {
					entityIn.hurt(PVZEntityDamageSource.CHOMPER_PLANT, 8);
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public InteractionResult use(BlockState state, Level worldIn, Mth pos, Player player,
                                 InteractionHand handIn, BlockRayTraceResult hit) {
		if(player.getItemInHand(handIn).getItem() == Items.BONE_MEAL) {
			if(worldIn.isClientSide) {
				for(int i = 0 ;i < 5; ++ i) {
					WorldUtil.spawnRandomSpeedParticle(worldIn, ParticleTypes.COMPOSTER, MathUtil.toVector(pos), 0.1F);
				}
			}
			for(int i = -2; i <= 2; ++ i) {
				for(int k = -2; k <= 2; ++ k) {
					final Mth tmp = WorldUtil.getSuitableHeightPos(worldIn, pos.offset(i, 0, k)).below();
					if(Math.abs(pos.getY() - tmp.getY()) < 5 && mayPlaceOn(worldIn.getBlockState(tmp), worldIn, tmp)) {
						if(worldIn.isEmptyBlock(tmp.above())){
							if(! worldIn.isClientSide) {
								player.getItemInHand(handIn).shrink(1);
							    if(this.RANDOM.nextFloat() < 0.2F) {
								    worldIn.setBlockAndUpdate(tmp.above(), BlockRegister.CHOMPER.get().defaultBlockState().rotate(Rotation.getRandom(RANDOM)));
							    }
							}
						}
					}
				}
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
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
	public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, Mth pos, MobEntity entity) {
		return PathNodeType.DANGER_OTHER;
	}
	
}
