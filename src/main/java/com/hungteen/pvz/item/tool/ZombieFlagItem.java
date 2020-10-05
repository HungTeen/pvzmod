package com.hungteen.pvz.item.tool;

import java.util.List;

import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.register.GroupRegister;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ZombieFlagItem extends Item{

	private static final int sunCost = 25;
	private static final int effectCD = 600;
	
	public ZombieFlagItem() {
		super(new Item.Properties().maxStackSize(1).group(GroupRegister.PVZ_MISC));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("tooltip.pvz.zombie_flag").applyTextStyle(TextFormatting.AQUA));
		tooltip.add(new TranslationTextComponent("tooltip.pvz.sun_cost").appendText(":"+sunCost).applyTextStyle(TextFormatting.YELLOW));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if(!playerIn.world.isRemote) {
			playerIn.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
				int num=l.getPlayerData().getPlayerStats().getPlayerStats(Resources.SUN_NUM);
				if(num>=sunCost) {
					l.getPlayerData().getPlayerStats().addPlayerStats(Resources.SUN_NUM, -sunCost);
					playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, effectCD, 1));
					playerIn.getCooldownTracker().setCooldown(playerIn.getHeldItem(handIn).getItem(), effectCD);
				}
			});
		}
		return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
	}
}
