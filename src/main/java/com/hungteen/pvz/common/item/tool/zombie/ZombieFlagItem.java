package com.hungteen.pvz.common.item.tool.zombie;

import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.Effects;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class ZombieFlagItem extends Item{

	private static final int SUN_COST = 25;
	private static final int EFFECT_CD = 400;
	
	public ZombieFlagItem() {
		super(new Item.Properties().stacksTo(1).tab(PVZItemGroups.PVZ_USEFUL).durability(100));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(new TranslatableComponent("tooltip.pvz.zombie_flag").withStyle(ChatFormatting.AQUA));
		tooltip.add(new TranslatableComponent("tooltip.pvz.sun_cost", SUN_COST).withStyle(ChatFormatting.YELLOW));
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		if(PlayerUtil.getResource(playerIn, Resources.SUN_NUM) >= SUN_COST){
			if(! playerIn.level.isClientSide){
				PlayerUtil.addResource(playerIn, Resources.SUN_NUM, - SUN_COST);
				playerIn.addEffect(new MobEffectInstance(Effects.MOVEMENT_SPEED, EFFECT_CD, 1));
				if(PlayerUtil.isPlayerSurvival(playerIn)) {
					playerIn.getCooldowns().addCooldown(playerIn.getItemInHand(handIn).getItem(), EFFECT_CD + 80);
					playerIn.getItemInHand(handIn).hurtAndBreak(1, playerIn, p -> p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
				}
			}
			return InteractionResultHolder.success(playerIn.getItemInHand(handIn));
		}
		return InteractionResultHolder.fail(playerIn.getItemInHand(handIn));
	}
}
