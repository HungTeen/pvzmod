package com.hungteen.pvz.common.item.blockitem;

import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.item.PVZItemGroups;

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
		super(BlockRegister.LILY_PAD.get(),new Properties().tab(PVZItemGroups.PVZ_MISC));
	}

	@Override
	public ActionResultType useOn(ItemUseContext context) {
		return ActionResultType.PASS;
	}
	
	public ActionResult<ItemStack> use(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {
	      BlockRayTraceResult blockraytraceresult = getPlayerPOVHitResult(p_77659_1_, p_77659_2_, RayTraceContext.FluidMode.SOURCE_ONLY);
	      BlockRayTraceResult blockraytraceresult1 = blockraytraceresult.withPosition(blockraytraceresult.getBlockPos().above());
	      ActionResultType actionresulttype = super.useOn(new ItemUseContext(p_77659_2_, p_77659_3_, blockraytraceresult1));
	      return new ActionResult<>(actionresulttype, p_77659_2_.getItemInHand(p_77659_3_));
	   }

}
