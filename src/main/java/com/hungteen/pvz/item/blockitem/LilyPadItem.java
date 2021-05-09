package com.hungteen.pvz.item.blockitem;

import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.GroupRegister;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.world.World;

public class LilyPadItem extends BlockItem {

	public LilyPadItem() {
		super(BlockRegister.LILY_PAD.get(),new Properties().tab(GroupRegister.PVZ_MISC));
	}

	@Override
	public ActionResultType useOn(ItemUseContext context) {
		return ActionResultType.PASS;
	}

//	@Override
//	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
//		ItemStack itemstack = playerIn.getItemInHand(handIn);
//		RayTraceResult raytraceresult = getPlayerPOVHitResult(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
//		if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
//			return ActionResult.pass(itemstack);
//		} else {
//			if (raytraceresult.getType() == RayTraceResult.Type.BLOCK) {
//				BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
//				BlockPos blockpos = blockraytraceresult.getBlockPos();
//				Direction direction = blockraytraceresult.getDirection();
//				if (!worldIn.mayInteract(playerIn, blockpos)
//						|| !playerIn.mayUseItemAt(blockpos.relative(direction), direction, itemstack)) {
//					return ActionResult.fail(itemstack);
//				}
//				BlockPos blockpos1 = blockpos.above();
//				FluidState ifluidstate = worldIn.getFluidState(blockpos);
//				if (ifluidstate.getType() == Fluids.WATER && worldIn.isEmptyBlock(blockpos1)) {
//					// special case for handling block placement with water lilies
//					net.minecraftforge.common.util.BlockSnapshot blocksnapshot = net.minecraftforge.common.util.BlockSnapshot
//							.getBlockSnapshot(worldIn, blockpos1);
//					worldIn.setBlock(blockpos1, BlockRegister.LILY_PAD.get().getStateForPlacement(playerIn), 11);
//					if (net.minecraftforge.event.ForgeEventFactory.onBlockPlace(playerIn, blocksnapshot,
//							net.minecraft.util.Direction.UP)) {
//						blocksnapshot.restore(true, false);
//						return ActionResult.fail(itemstack);
//					}
//
//					if (playerIn instanceof ServerPlayerEntity) {
//						CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) playerIn, blockpos1, itemstack);
//					}
//
//					if (!playerIn.abilities.instabuild) {
//						itemstack.shrink(1);
//					}
//
//					playerIn.awardStat(Stats.ITEM_USED.get(this));
//					worldIn.playSound(playerIn, blockpos, SoundEvents.LILY_PAD_PLACE, SoundCategory.BLOCKS, 1.0F,
//							1.0F);
//					return ActionResult.success(itemstack);
//				}
//			}
//
//			return ActionResult.fail(itemstack);
//		}
//	}
	
	public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
	      BlockRayTraceResult blockraytraceresult = getPlayerPOVHitResult(p_77659_1_, p_77659_2_, RayTraceContext.FluidMode.SOURCE_ONLY);
	      BlockRayTraceResult blockraytraceresult1 = blockraytraceresult.withPosition(blockraytraceresult.getBlockPos().above());
	      ActionResultType actionresulttype = super.useOn(new ItemUseContext(p_77659_2_, p_77659_3_, blockraytraceresult1));
	      return new ActionResult<>(actionresulttype, p_77659_2_.getItemInHand(p_77659_3_));
	   }

}
