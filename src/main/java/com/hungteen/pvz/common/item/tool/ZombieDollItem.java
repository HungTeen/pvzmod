package com.hungteen.pvz.common.item.tool;

import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ZombieDollItem extends Item {

	public ZombieDollItem() {
		super(new Item.Properties().tab(PVZItemGroups.PVZ_USEFUL).stacksTo(1));
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> textComponents, ITooltipFlag tooltipFlag) {
		super.appendHoverText(stack, world, textComponents, tooltipFlag);
		textComponents.add(new TranslationTextComponent("tooltip.pvz.zombie_doll"));
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if(handIn == Hand.MAIN_HAND) {
			if(! worldIn.isClientSide){
				EntityUtil.playSound(playerIn, SoundRegister.ZOMBIE_GROAN.get());
			}
			return ActionResult.success(playerIn.getItemInHand(handIn));
		}
		return super.use(worldIn, playerIn, handIn);
	}

}
