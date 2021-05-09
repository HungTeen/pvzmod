package com.hungteen.pvz.block.plants;

import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class PeaBlock extends CropsBlock{

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
    		Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), 
    		Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), 
    		Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), 
    		Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), 
    		Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), 
    		Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), 
    		Block.box(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D), 
    		Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
    };
	
    public PeaBlock(Properties builder) {
		super(builder);
	}

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
    	return SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
    }
    
	@SuppressWarnings("deprecation")
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if(! worldIn.isClientSide) {
			if(this.isMaxAge(state)) {
				worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemRegister.PEA.get(), 1)));
				worldIn.setBlockAndUpdate(pos, this.getStateForAge(0));
				return ActionResultType.SUCCESS;
			}
		}
		return super.use(state, worldIn, pos, player, handIn, hit);
	}
	
	@Override
	protected IItemProvider getBaseSeedId() {
		return ItemRegister.PEA.get();
	}
}
