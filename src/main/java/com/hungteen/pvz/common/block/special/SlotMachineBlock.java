package com.hungteen.pvz.common.block.special;

import com.hungteen.pvz.common.block.AbstractFacingBlock;
import com.hungteen.pvz.common.datapack.LotteryTypeLoader;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.common.tileentity.SlotMachineTileEntity;
import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

public class SlotMachineBlock extends AbstractFacingBlock {

	public SlotMachineBlock() {
		super(Properties.copy(Blocks.GOLD_BLOCK));
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) {
		if (!worldIn.isClientSide && handIn == Hand.MAIN_HAND) {
			SlotMachineTileEntity te = (SlotMachineTileEntity) worldIn.getBlockEntity(pos);
			if (te.getLotteryType() != null) {
				NetworkHooks.openGui((ServerPlayerEntity) player, te, pos);
			}
		}
		return ActionResultType.SUCCESS;
	}

	public void setPlacedBy(World p_180633_1_, BlockPos p_180633_2_, BlockState p_180633_3_, LivingEntity p_180633_4_,
			ItemStack p_180633_5_) {
		if (p_180633_5_.hasCustomHoverName()) {
			TileEntity tileentity = p_180633_1_.getBlockEntity(p_180633_2_);
			if (tileentity instanceof SlotMachineTileEntity) {
				((SlotMachineTileEntity) tileentity).setCustomName(p_180633_5_.getHoverName());
			}
		}

	}

	@Override
	public void fillItemCategory(ItemGroup itemGroup, NonNullList<ItemStack> itemStacks) {
		if (itemGroup.equals(PVZItemGroups.PVZ_USEFUL)) {
			LotteryTypeLoader.LOTTERIES.forEach((res, lottery) -> {
				final ItemStack stack = new ItemStack(this);
				setResourceTag(stack, res);
				itemStacks.add(stack);
			});
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader iBlockReader,
			List<ITextComponent> iTextComponents, ITooltipFlag iTooltipFlag) {
		super.appendHoverText(stack, iBlockReader, iTextComponents, iTooltipFlag);
		final String res = getResourceTag(stack).toString();
		iTextComponents.add(new StringTextComponent(res).withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
	}

	public static void setResourceTag(ItemStack stack, ResourceLocation res) {
		final CompoundNBT nbt = stack.getOrCreateTagElement(StringUtil.TE_TAG);
		nbt.putString("lottery_type", res.toString());
	}

	public static ResourceLocation getResourceTag(ItemStack stack) {
		return new ResourceLocation(stack.getOrCreateTagElement(StringUtil.TE_TAG).getString("lottery_type"));
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new SlotMachineTileEntity();
	}

	public ItemStack getCloneItemStack(IBlockReader p_185473_1_, BlockPos p_185473_2_, BlockState p_185473_3_) {
		@SuppressWarnings("deprecation")
		final ItemStack itemstack = super.getCloneItemStack(p_185473_1_, p_185473_2_, p_185473_3_);
		SlotMachineTileEntity blockEntity = (SlotMachineTileEntity) p_185473_1_.getBlockEntity(p_185473_2_);
		CompoundNBT compoundnbt = blockEntity.save(new CompoundNBT());
		if (!compoundnbt.isEmpty()) {
			itemstack.addTagElement(StringUtil.TE_TAG, compoundnbt);
		}

		return itemstack;
	}

}
