package com.hungteen.pvz.common.block.special;

import com.hungteen.pvz.common.tileentity.EssenceAltarTileEntity;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

public class EssenceAltarBlock extends Block {

	private static final VoxelShape AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);

	public EssenceAltarBlock() {
		super(Block.Properties.copy(Blocks.OBSIDIAN).lightLevel((state) -> {return 15;}));
	}
	
	@Override
	public InteractionResult use(BlockState state, Level worldIn, Mth pos, Player player,
								 InteractionHand handIn, BlockRayTraceResult hit) {
		if (!worldIn.isClientSide && handIn == InteractionHand.MAIN_HAND) {
			EssenceAltarTileEntity te = (EssenceAltarTileEntity) worldIn.getBlockEntity(pos);
			NetworkHooks.openGui((ServerPlayer) player, te, pos);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable IBlockReader iBlockReader, List<Component> textComponents, TooltipFlag tooltipFlag) {
		super.appendHoverText(itemStack, iBlockReader, textComponents, tooltipFlag);
		textComponents.add(new TranslatableComponent("tooltip.pvz.essence_altar").withStyle(ChatFormatting.GREEN));
	}

	@Override
	public void onRemove(BlockState state, Level worldIn, Mth pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			TileEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof EssenceAltarTileEntity) {
				EssenceAltarTileEntity te = (EssenceAltarTileEntity) worldIn.getBlockEntity(pos);
				for (int i = 0; i < te.handler.getSlots(); ++i) {
					InventoryHelper.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(),
							te.handler.getStackInSlot(i));
				}
			}
			super.onRemove(state, worldIn, pos, newState, isMoving);
		}
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new EssenceAltarTileEntity();
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, Mth pos, ISelectionContext context) {
		return AABB;
	}

}
