package com.hungteen.pvz.item.tool.card;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.capabilities.CapabilityHandler;
import com.hungteen.pvz.capabilities.player.PlayerDataManager;
import com.hungteen.pvz.event.events.SummonCardUseEvent;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

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
		if(plant!=Plants.LILY_PAD) {//this is for lily_pad placement
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
				BlockState blockstate = worldIn.getBlockState(blockpos);
				Material material = blockstate.getMaterial();
				IFluidState ifluidstate = worldIn.getFluidState(blockpos);
				if ((ifluidstate.getFluid() == Fluids.WATER || material == Material.ICE) && worldIn.isAirBlock(blockpos1)) {
					player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
						PlayerDataManager manager = l.getPlayerData();
						int num=manager.getPlayerStats().getPlayerStats(Resources.SUN_NUM);
						int sunCost=getSunCost(itemstack);
						if(num>=sunCost) {//sun is enough
						    BlockState state = getBlockState();
						    if(state==null) {
						    	return;
						    }
						    MinecraftForge.EVENT_BUS.post(new SummonCardUseEvent(player, itemstack));
							l.getPlayerData().getPlayerStats().addPlayerStats(Resources.SUN_NUM, -sunCost);
							int lvl=manager.getPlantStats().getPlantLevel(plant);
							if(this.isEnjoyCard) {
								lvl=1;
								itemstack.shrink(1);
							}else {
								
							}
							worldIn.setBlockState(blockpos1, state, 11);
							player.getCooldownTracker().setCooldown(itemstack.getItem(), PlantUtil.getPlantCoolDownTime(plant, lvl));
						    if (player instanceof ServerPlayerEntity) {
						        CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) player, blockpos1, itemstack);
					        }
					        player.addStat(Stats.ITEM_USED.get(this));
					        worldIn.playSound(player, blockpos, SoundEvents.BLOCK_LILY_PAD_PLACE, SoundCategory.BLOCKS, 1.0F,1.0F);
						}
					});
					return ActionResult.resultSuccess(itemstack);
				}
			}

			return ActionResult.resultFail(itemstack);
		}
	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		return ActionResultType.PASS;
	}
	
	public BlockState getBlockState() {
		switch(plant) {
		case LILY_PAD:return BlockRegister.LILY_PAD.get().getDefaultState();
		default:{
			PVZMod.LOGGER.debug("No such block plant!");
			return null;
		}
		}
	}
	
}
