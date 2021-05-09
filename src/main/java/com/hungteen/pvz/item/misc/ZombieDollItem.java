package com.hungteen.pvz.item.misc;

import com.hungteen.pvz.register.GroupRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ZombieDollItem extends Item {

	public ZombieDollItem() {
		super(new Item.Properties().tab(GroupRegister.PVZ_MISC).stacksTo(1));
	}
	
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if(! worldIn.isClientSide && handIn == Hand.MAIN_HAND) {
			EntityUtil.playSound(playerIn, SoundRegister.ZOMBIE_SAY.get());
			return ActionResult.success(playerIn.getItemInHand(handIn));
		}
		return super.use(worldIn, playerIn, handIn);
	}

}
