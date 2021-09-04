package com.hungteen.pvz.common.item.misc;

import com.hungteen.pvz.common.item.PVZItemGroups;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class GuildBookItem extends Item{

	public GuildBookItem() {
		super(new Properties().tab(PVZItemGroups.PVZ_MISC).stacksTo(1));
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
//		if(worldIn.isClientSide) {
//			DistExecutor.runWhenOn(Dist.CLIENT, ()->()->{
//				Minecraft.getInstance().setScreen(new PVZGuildBookScreen());
//			});
//		}
		return ActionResult.pass(playerIn.getItemInHand(handIn));
	}
	
}
