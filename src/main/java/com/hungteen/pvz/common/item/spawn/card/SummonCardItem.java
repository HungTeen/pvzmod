package com.hungteen.pvz.common.item.spawn.card;

import java.util.List;

import com.hungteen.pvz.api.types.ICoolDown;
import com.hungteen.pvz.common.item.PVZItemGroups;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public abstract class SummonCardItem extends Item{

	protected static final ITextComponent SUN_ERROR = new TranslationTextComponent("help.pvz.sun").withStyle(TextFormatting.RED);
	protected static final ITextComponent CD_ERROR = new TranslationTextComponent("help.pvz.cd").withStyle(TextFormatting.RED);
	protected static final ITextComponent LOCK_ERROR = new TranslationTextComponent("help.pvz.lock").withStyle(TextFormatting.RED);
	public final boolean isEnjoyCard;
	
	public SummonCardItem(boolean isEnjoyCard) {
		this(new Properties().tab(PVZItemGroups.PVZ_PLANT_CARD).stacksTo(isEnjoyCard ? 16 : 1), isEnjoyCard);
	}
	
	public SummonCardItem(Properties properties, boolean isEnjoyCard) {
		super(properties);
		this.isEnjoyCard = isEnjoyCard;
	}
	 
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return ! this.isEnjoyCard && super.canApplyAtEnchantingTable(stack, enchantment);//enjoy card have no enchant
	}
	
	/**
	 * get base sun cost.
	 * {@link #appendHoverText(ItemStack, World, List, ITooltipFlag)}
	 */
	public static int getCardSunCost(ItemStack stack) {
		if(stack.getItem() instanceof PlantCardItem) {
			return ((PlantCardItem) stack.getItem()).getBasisSunCost(stack);
		}
		return 1;
	}
	
	/**
	 * get base card cd.
	 * {@link #appendHoverText(ItemStack, World, List, ITooltipFlag)}
	 */
	public static ICoolDown getCardCoolDown(ItemStack stack) {
		if(stack.getItem() instanceof PlantCardItem) {
			return ((PlantCardItem) stack.getItem()).getBasisCoolDown(stack);
		}
		return ICoolDown.DEFAULT;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("tooltip.pvz.card_sun_cost", getCardSunCost(stack)).withStyle(TextFormatting.YELLOW));
		tooltip.add(new TranslationTextComponent("tooltip.pvz.card_cd", new TranslationTextComponent(getCardCoolDown(stack).getTranslateKey()).getString()).withStyle(TextFormatting.AQUA));
	}
	
	@Override
	public boolean isEnchantable(ItemStack stack) {
		return this.getItemStackLimit(stack) == 1 && ! this.isEnjoyCard;
	}
	
	@Override
	public int getItemEnchantability(ItemStack stack) {
		return 20;
	}
	
	@Override
	public int getEnchantmentValue() {
		return 20;//0 ~ 45
	}
	
}
