package com.hungteen.pvz.common.block.special;

import com.hungteen.pvz.common.tileentity.FragmentSpliceTileEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

public class FragmentSpliceBlock extends Block {

	public FragmentSpliceBlock() {
		super(Block.Properties.copy(Blocks.IRON_BLOCK));
	}
	
	@Override
	public InteractionResult use(BlockState state, Level worldIn, Mth pos, Player player,
								 InteractionHand handIn, BlockRayTraceResult hit) {
		if (! worldIn.isClientSide && handIn == InteractionHand.MAIN_HAND) {
			FragmentSpliceTileEntity te = (FragmentSpliceTileEntity) worldIn.getBlockEntity(pos);
		    NetworkHooks.openGui((ServerPlayer) player, te, pos);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable IBlockReader iBlockReader, List<Component> textComponents, TooltipFlag tooltipFlag) {
		super.appendHoverText(itemStack, iBlockReader, textComponents, tooltipFlag);
		textComponents.add(new TranslatableComponent("tooltip.pvz.fragment_splice").withStyle(ChatFormatting.GREEN));
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new FragmentSpliceTileEntity();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState state, Level worldIn, Mth pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			TileEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof FragmentSpliceTileEntity) {
				FragmentSpliceTileEntity te = (FragmentSpliceTileEntity) worldIn.getBlockEntity(pos);
				for (int i = 0; i < te.handler.getSlots(); ++i) {
					InventoryHelper.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(),
							te.handler.getStackInSlot(i));
				}
			}
			super.onRemove(state, worldIn, pos, newState, isMoving);
		}
	}

}
