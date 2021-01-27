package com.hungteen.pvz.block.special;

import com.hungteen.pvz.block.AbstractFacingBlock;
import com.hungteen.pvz.tileentity.SlotMachineTileEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class SlotMachineBlock extends AbstractFacingBlock {

	public SlotMachineBlock() {
		super(Properties.from(Blocks.GOLD_BLOCK));
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if (! worldIn.isRemote && handIn == Hand.MAIN_HAND) {
			SlotMachineTileEntity te = (SlotMachineTileEntity) worldIn.getTileEntity(pos);
			NetworkHooks.openGui((ServerPlayerEntity) player, te, pos);
		}
		return ActionResultType.SUCCESS;
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new SlotMachineTileEntity();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof SlotMachineTileEntity) {
				SlotMachineTileEntity te = (SlotMachineTileEntity) worldIn.getTileEntity(pos);
				for (int i = 0; i < te.handler.getSlots(); ++i) {
					InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(),
							te.handler.getStackInSlot(i));
				}
			}
			super.onReplaced(state, worldIn, pos, newState, isMoving);
		}
	}

}
