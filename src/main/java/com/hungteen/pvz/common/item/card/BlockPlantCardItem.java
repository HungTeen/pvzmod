package com.hungteen.pvz.common.item.card;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
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
	public int getEnchantmentValue() {
		return 10;
	}
	
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand handIn) {
		ItemStack itemstack = player.getItemInHand(handIn);
		if(plantType != Plants.LILY_PAD) {//this is for lily_pad placement
			return ActionResult.pass(itemstack);
		}
		RayTraceResult raytraceresult = getPlayerPOVHitResult(worldIn, player, RayTraceContext.FluidMode.SOURCE_ONLY);
		if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
			return ActionResult.pass(itemstack);
		} else {
			if (raytraceresult.getType() == RayTraceResult.Type.BLOCK) {
				BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult) raytraceresult;
				BlockPos blockpos = blockraytraceresult.getBlockPos();
				Direction direction = blockraytraceresult.getDirection();
				if (!worldIn.mayInteract(player, blockpos)
						|| !player.mayUseItemAt(blockpos.relative(direction), direction, itemstack)) {
					return ActionResult.fail(itemstack);
				}
				BlockPos blockpos1 = blockpos.above();
				FluidState ifluidstate = worldIn.getFluidState(blockpos);
				if (ifluidstate.getType() == Fluids.WATER && worldIn.isEmptyBlock(blockpos1)) {
					if(! worldIn.isClientSide) {
						PlantCardItem.checkSunAndPlaceBlock(player, this, itemstack, blockpos1);
					}
					return ActionResult.success(itemstack);
				}
			}
			return ActionResult.fail(itemstack);
		}
	}
	
	@Override
	public ActionResultType useOn(ItemUseContext context) {
		if(this.plantType != Plants.FLOWER_POT) {
			return ActionResultType.PASS;
		}
		World world = context.getLevel();
		PlayerEntity player = context.getPlayer();
		Hand hand = context.getHand();
		ItemStack stack = player.getItemInHand(hand);
		BlockPos pos = context.getClickedPos();
		if (world.isClientSide) {
			return ActionResultType.SUCCESS;
		}
		if (context.getClickedFace() == Direction.UP) {
			if(world.getBlockState(pos).canBeReplaced(new BlockItemUseContext(context))) {
				checkSunAndPlaceBlock(player, this, stack, pos);
				return ActionResultType.SUCCESS;
			} else if(world.isEmptyBlock(pos.above()) && world.getBlockState(pos).canOcclude()) {// can plant here
			    checkSunAndPlaceBlock(player, this, stack, pos.above());
			    return ActionResultType.SUCCESS;
			}
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
	
	public static BlockState getBlockState(Direction direction, Plants plant) {
		switch(plant) {
		case LILY_PAD: return BlockRegister.LILY_PAD.get().getStateForPlacement(direction);
		case FLOWER_POT: return BlockRegister.FLOWER_POT.get().getStateForPlacement(direction);
		default:{
			PVZMod.LOGGER.debug("No such block plant!");
			return null;
		}
		}
	}
	
}
