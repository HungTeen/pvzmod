package com.hungteen.pvz.block.special;

import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.tileentity.SunConverterTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
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

	protected static final VoxelShape AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);

	public SunConverterBlock() {
		super(Block.Properties.copy(BlockRegister.STEEL_BLOCK.get()).noOcclusion());
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new SunConverterTileEntity();
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (!worldIn.isClientSide && handIn == Hand.MAIN_HAND) {
			SunConverterTileEntity te = (SunConverterTileEntity) worldIn.getBlockEntity(pos);
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

	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			TileEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof SunConverterTileEntity) {
				SunConverterTileEntity te = (SunConverterTileEntity) worldIn.getBlockEntity(pos);
				for (int i = 0; i < te.handler.getSlots(); ++i) {
					InventoryHelper.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(),
							te.handler.getStackInSlot(i));
				}
			}
			super.onRemove(state, worldIn, pos, newState, isMoving);
		}
	}
	
}
