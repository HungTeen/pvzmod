package com.hungteen.pvz.item.tool.card;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockPlantCardItem extends PlantCardItem{

	public BlockPlantCardItem(Plants plant, boolean isFragment) {
		super(plant, isFragment);
	}

	@Override
	public int getItemEnchantability() {
		return 10;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player, Hand handIn) {
		ItemStack itemstack = player.getHeldItem(handIn);
		if(plantType != Plants.LILY_PAD) {//this is for lily_pad placement
			return ActionResult.resultPass(itemstack);
		}
		RayTraceResult raytraceresult = rayTrace(worldIn, player, RayTraceContext.FluidMode.SOURCE_ONLY);
		if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
			return ActionResult.resultPass(itemstack);
		} else {
			if (raytraceresult.getType() == RayTraceResult.Type.BLOCK) {
				BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
				BlockPos blockpos = blockraytraceresult.getPos();
				Direction direction = blockraytraceresult.getFace();
				if (!worldIn.isBlockModifiable(player, blockpos)
						|| !player.canPlayerEdit(blockpos.offset(direction), direction, itemstack)) {
					return ActionResult.resultFail(itemstack);
				}
				BlockPos blockpos1 = blockpos.up();
				IFluidState ifluidstate = worldIn.getFluidState(blockpos);
				if (ifluidstate.getFluid() == Fluids.WATER && worldIn.isAirBlock(blockpos1)) {
					PlantCardItem.checkSunAndPlaceBlock(player, this, itemstack, blockpos1);
					return ActionResult.resultSuccess(itemstack);
				}
			}

			return ActionResult.resultFail(itemstack);
		}
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		if(this.plantType != Plants.FLOWER_POT) {
			return ActionResultType.PASS;
		}
		World world = context.getWorld();
		PlayerEntity player = context.getPlayer();
		Hand hand = context.getHand();
		ItemStack stack = player.getHeldItem(hand);
		BlockPos pos = context.getPos();
		if (world.isRemote) {
			return ActionResultType.SUCCESS;
		}
		if (context.getFace() == Direction.UP && world.isAirBlock(pos.up()) && world.getBlockState(pos).isSolid()) {// can plant here
			checkSunAndPlaceBlock(player, this, stack, pos.up());
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.FAIL;
	}
	
	public static BlockState getBlockState(PlayerEntity player, Plants plant) {
		switch(plant) {
		case LILY_PAD: return BlockRegister.LILY_PAD.get().getStateForPlacement(player);
		case FLOWER_POT: return BlockRegister.FLOWER_POT.get().getStateForPlacement(player);
		default:{
			PVZMod.LOGGER.debug("No such block plant!");
			return null;
		}
		}
	}
	
}
