package com.hungteen.pvz.block.special;

import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.tileentity.SunConverterTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class SunConverterBlock extends Block {

	protected static final VoxelShape AABB = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);
	
	public SunConverterBlock() {
		super(Block.Properties.from(BlockRegister.STEEL_BLOCK.get()).notSolid());
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new SunConverterTileEntity();
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (! worldIn.isRemote && handIn == Hand.MAIN_HAND) {
		    SunConverterTileEntity te = (SunConverterTileEntity) worldIn.getTileEntity(pos);
		    NetworkHooks.openGui((ServerPlayerEntity) player, te, pos);
		}
		return ActionResultType.SUCCESS;
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB;
	}
	
	@Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
	
}
