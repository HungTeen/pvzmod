package com.hungteen.pvz.common.item.blockitem;

import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.item.PVZItemGroups;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.world.level.Level;

public class LilyPadItem extends BlockItem {

	public LilyPadItem() {
		super(BlockRegister.LILY_PAD.get(),new Properties().tab(PVZItemGroups.PVZ_MISC));
	}

	@Override
	public InteractionResult useOn(ItemUseContext context) {
		return InteractionResult.PASS;
	}
	
	public InteractionResultHolder<ItemStack> use(Level p_77659_1_, Player p_77659_2_, InteractionHand p_77659_3_) {
	      BlockRayTraceResult blockraytraceresult = getPlayerPOVHitResult(p_77659_1_, p_77659_2_, RayTraceContext.FluidMode.SOURCE_ONLY);
	      BlockRayTraceResult blockraytraceresult1 = blockraytraceresult.withPosition(blockraytraceresult.getBlockPos().above());
	      InteractionResult actionresulttype = super.useOn(new ItemUseContext(p_77659_2_, p_77659_3_, blockraytraceresult1));
	      return new InteractionResultHolder<>(actionresulttype, p_77659_2_.getItemInHand(p_77659_3_));
	   }

}
