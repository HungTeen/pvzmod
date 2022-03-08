package com.hungteen.pvz.common.block.plants;

import com.hungteen.pvz.common.item.ItemRegister;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BlockState;
import net.minecraft.world.level.block.CropsBlock;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.level.Level;

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
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, Mth pos, ISelectionContext context) {
    	return SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
    }
    
	@SuppressWarnings("deprecation")
	@Override
	public InteractionResult use(BlockState state, Level worldIn, Mth pos, Player player,
                                 InteractionHand handIn, BlockRayTraceResult hit) {
		if(! worldIn.isClientSide) {
			if(this.isMaxAge(state)) {
				worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemRegister.PEA.get(), 1)));
				worldIn.setBlockAndUpdate(pos, this.getStateForAge(0));
				return InteractionResult.SUCCESS;
			}
		}
		return super.use(state, worldIn, pos, player, handIn, hit);
	}
	
	@Override
	protected IItemProvider getBaseSeedId() {
		return ItemRegister.PEA.get();
	}
}
