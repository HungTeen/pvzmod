package com.hungteen.pvz.block.plants;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.BlockRegister;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class ChomperBlock extends BushBlock{

	public static final DirectionProperty FACING = HorizontalBlock.FACING;
	private static final VoxelShape SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D);
	
	public ChomperBlock() {
		super(Block.Properties.copy(Blocks.PUMPKIN).noCollission().noOcclusion().harvestTool(ToolType.AXE).harvestLevel(2));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if(entityIn instanceof AnimalEntity||entityIn instanceof PlayerEntity) {
			if(!worldIn.isClientSide) {
				if(this.RANDOM.nextInt(50)==0) {
					entityIn.hurt(PVZDamageSource.CHOMPER_PLANT, 12);
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if(player.getItemInHand(handIn).getItem()==Items.BONE_MEAL) {
			for(int i=-2;i<=2;i++) {
				if(worldIn.isClientSide) break;
				for(int j=-1;j<=1;j++) {
					for(int k=-2;k<=2;k++) {
						BlockPos tmp = pos.offset(i, j, k);
						if(mayPlaceOn(worldIn.getBlockState(tmp), worldIn, tmp)) {
							if(worldIn.isEmptyBlock(tmp.above())){
								int chance=PVZConfig.COMMON_CONFIG.BlockSettings.ChomperGrowChance.get();
								if(this.RANDOM.nextInt(chance)==0) {
									worldIn.setBlockAndUpdate(tmp.above(), BlockRegister.CHOMPER.get().defaultBlockState().rotate(Rotation.getRandom(RANDOM)));
								}
							}
						}
					}
				}
			}
			
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
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
		return PathNodeType.DANGER_OTHER;
	}
	
}
