package com.hungteen.pvz.common.item.tool;

import java.util.List;

import com.hungteen.pvz.common.capability.CapabilityHandler;
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
		super(new Item.Properties().stacksTo(1).tab(GroupRegister.PVZ_MISC));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("tooltip.pvz.zombie_flag").withStyle(TextFormatting.AQUA));
		tooltip.add(new TranslationTextComponent("tooltip.pvz.sun_cost").append(":"+sunCost).withStyle(TextFormatting.YELLOW));
	}
	
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if(!playerIn.level.isClientSide) {
			playerIn.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
				int num=l.getPlayerData().getPlayerStats().getPlayerStats(Resources.SUN_NUM);
				if(num>=sunCost) {
					l.getPlayerData().getPlayerStats().addPlayerStats(Resources.SUN_NUM, -sunCost);
					playerIn.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, effectCD, 1));
					playerIn.getCooldowns().addCooldown(playerIn.getItemInHand(handIn).getItem(), effectCD);
				}
			});
		}
		return ActionResult.success(playerIn.getItemInHand(handIn));
	}
}
