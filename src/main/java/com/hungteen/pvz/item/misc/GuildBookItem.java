package com.hungteen.pvz.item.misc;

import com.hungteen.pvz.gui.PVZGuildBookScreen;
import com.hungteen.pvz.register.GroupRegister;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class GuildBookItem extends Item{

	public GuildBookItem() {
		super(new Properties().group(GroupRegister.PVZ_MISC).maxStackSize(1));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if(worldIn.isRemote) {
			DistExecutor.runWhenOn(Dist.CLIENT, ()->()->{
				Minecraft.getInstance().displayGuiScreen(new PVZGuildBookScreen());
			});
		}
		return ActionResult.resultPass(playerIn.getHeldItem(handIn));
	}
	
}
