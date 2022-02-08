package com.hungteen.pvz.common.item.tool.zombie;

import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.utils.PlayerUtil;
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

import java.util.List;

public class ZombieFlagItem extends Item{

	private static final int SUN_COST = 25;
	private static final int EFFECT_CD = 400;
	
	public ZombieFlagItem() {
		super(new Item.Properties().stacksTo(1).tab(PVZItemGroups.PVZ_USEFUL).durability(100));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("tooltip.pvz.zombie_flag").withStyle(TextFormatting.AQUA));
		tooltip.add(new TranslationTextComponent("tooltip.pvz.sun_cost", SUN_COST).withStyle(TextFormatting.YELLOW));
	}
	
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if(PlayerUtil.getResource(playerIn, Resources.SUN_NUM) >= SUN_COST){
			if(! playerIn.level.isClientSide){
				PlayerUtil.addResource(playerIn, Resources.SUN_NUM, - SUN_COST);
				playerIn.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, EFFECT_CD, 1));
				if(PlayerUtil.isPlayerSurvival(playerIn)) {
					playerIn.getCooldowns().addCooldown(playerIn.getItemInHand(handIn).getItem(), EFFECT_CD + 80);
					playerIn.getItemInHand(handIn).hurtAndBreak(1, playerIn, p -> p.broadcastBreakEvent(Hand.MAIN_HAND));
				}
			}
			return ActionResult.success(playerIn.getItemInHand(handIn));
		}
		return ActionResult.fail(playerIn.getItemInHand(handIn));
	}
}
