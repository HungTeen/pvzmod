package com.hungteen.pvz.common.block.special;

import com.hungteen.pvz.common.enchantment.misc.SunMendingEnchantment;
import com.hungteen.pvz.common.item.tool.plant.SunStorageSaplingItem;
import com.hungteen.pvz.common.tileentity.SunConverterTileEntity;

import net.minecraft.ChatFormatting;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.Mth;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

public class SunConverterBlock extends Block {

	private static final VoxelShape AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);

	public SunConverterBlock() {
		super(Block.Properties.copy(Blocks.IRON_BLOCK));
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new SunConverterTileEntity();
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, Mth pos, Player player,
								 InteractionHand handIn, BlockRayTraceResult hit) {
		if (!worldIn.isClientSide && handIn == InteractionHand.MAIN_HAND) {
			SunConverterTileEntity te = (SunConverterTileEntity) worldIn.getBlockEntity(pos);
			NetworkHooks.openGui((ServerPlayer) player, te, pos);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void appendHoverText(ItemStack itemStack, @Nullable IBlockReader blockReader, List<Component> textComponents, TooltipFlag tooltipFlag) {
		super.appendHoverText(itemStack, blockReader, textComponents, tooltipFlag);
		textComponents.add(new TranslatableComponent("tooltip.pvz.sun_converter").withStyle(ChatFormatting.ITALIC));
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState state, Level worldIn, Mth pos, BlockState newState, boolean isMoving) {
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
	
	/**
	 * check can place in slots or not.
	 */
	public static boolean isValidItem(ItemStack stack) {
		return SunStorageSaplingItem.isNotOnceSapling(stack) || SunMendingEnchantment.getLevel(stack) > 0;
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, Mth pos, ISelectionContext context) {
		return AABB;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
}
