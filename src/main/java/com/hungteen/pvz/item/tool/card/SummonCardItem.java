package com.hungteen.pvz.item.tool.card;

import java.util.List;

import com.hungteen.pvz.enchantment.EnchantmentUtil;
import com.hungteen.pvz.register.GroupRegister;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public abstract class SummonCardItem extends Item{

	public final boolean isEnjoyCard;
	
	public SummonCardItem(boolean isEnjoyCard) {
		this(new Properties().group(GroupRegister.PVZ_CARD).maxStackSize(isEnjoyCard ? 16 : 1), isEnjoyCard);
	}
	
	public SummonCardItem(Properties properties, boolean isEnjoyCard) {
		super(properties);
		this.isEnjoyCard = isEnjoyCard;
	}
	 
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		if(this.isEnjoyCard) return false;//enjoy card have no enchant
		return super.canApplyAtEnchantingTable(stack, enchantment);
	}
	
	/**
	 * get final sun cost.
	 */
	public static int getItemStackSunCost(ItemStack stack) {
		if(stack.getItem() instanceof ImitaterCardItem) {
			return ((ImitaterCardItem) stack.getItem()).getImitateSunCost(stack);
		}
		if(stack.getItem() instanceof PlantCardItem) {
			Plants plantType = ((PlantCardItem) stack.getItem()).plantType;
			int cost = PlantUtil.getPlantSunCost(plantType);
			return Math.max(cost - EnchantmentUtil.getSunReduceNum(stack), 0);
		}
		return 0;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("tooltip.pvz.summon_card").applyTextStyle(TextFormatting.GREEN));
		tooltip.add(new TranslationTextComponent("tooltip.pvz.sun_cost").appendText(":" + getItemStackSunCost(stack)).applyTextStyle(TextFormatting.YELLOW));
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
	public int getItemEnchantability() {
		return 20;//0 ~ 45
	}
	
}
