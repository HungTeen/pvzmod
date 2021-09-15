package com.hungteen.pvz.common.item.tool.zombie;

import java.util.List;

import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.item.PVZItemGroups;
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

public class BalloonItem extends Item {

	private static final int sunCost = 25;
	private static final int effectCD = 200;
	
	public BalloonItem() {
		super(new Item.Properties().stacksTo(1).tab(PVZItemGroups.PVZ_MISC));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("tooltip.pvz.balloon").withStyle(TextFormatting.AQUA));
		tooltip.add(new TranslationTextComponent("tooltip.pvz.sun_cost").append(":" + sunCost).withStyle(TextFormatting.YELLOW));
	}
	
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if(! playerIn.level.isClientSide) {
			playerIn.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l) -> {
				int num = l.getPlayerData().getResource(Resources.SUN_NUM);
				if(num >= sunCost) {
					l.getPlayerData().addResource(Resources.SUN_NUM, - sunCost);
					playerIn.addEffect(new EffectInstance(Effects.SLOW_FALLING, effectCD, 10));
					playerIn.getCooldowns().addCooldown(playerIn.getItemInHand(handIn).getItem(), effectCD);
				}
			});
		}
		return ActionResult.success(playerIn.getItemInHand(handIn));
	}

}
