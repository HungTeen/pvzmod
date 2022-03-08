package com.hungteen.pvz.common.item.tool;

import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class ZombieDollItem extends Item {

	public ZombieDollItem() {
		super(new Item.Properties().tab(PVZItemGroups.PVZ_USEFUL).stacksTo(1));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> textComponents, TooltipFlag tooltipFlag) {
		super.appendHoverText(stack, world, textComponents, tooltipFlag);
		textComponents.add(new TranslatableComponent("tooltip.pvz.zombie_doll"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		if(handIn == InteractionHand.MAIN_HAND) {
			if(! worldIn.isClientSide){
				EntityUtil.playSound(playerIn, SoundRegister.ZOMBIE_GROAN.get());
			}
			return InteractionResultHolder.success(playerIn.getItemInHand(handIn));
		}
		return super.use(worldIn, playerIn, handIn);
	}

}
