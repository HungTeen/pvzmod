package com.hungteen.pvz.item.tool.card;

import java.util.List;

import com.hungteen.pvz.item.enchant.SunReduceEnchantment;
import com.hungteen.pvz.register.EnchantmentRegister;
import com.hungteen.pvz.register.GroupRegister;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public abstract class SummonCardItem extends Item{

	protected final boolean isFragment;//是不是碎片
	
	public SummonCardItem(boolean isFragment) {
		super(new Properties().group(GroupRegister.CARD_GROUP).maxStackSize(isFragment?64:1));
		this.isFragment=isFragment;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		if(this.isFragment) return false;//碎片没有附魔
		if(enchantment instanceof SunReduceEnchantment) return true;
		return super.canApplyAtEnchantingTable(stack, enchantment);
	}
	
	protected int getSunReduceNum(ItemStack stack)
	{
		int lvl=EnchantmentHelper.getEnchantmentLevel(EnchantmentRegister.SUN_COST_REDUCE.get(), stack);
		return 5*lvl;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("tooltip.pvz.summon_card").applyTextStyle(TextFormatting.GREEN));
		tooltip.add(new TranslationTextComponent("tooltip.pvz.sun_cost").appendText(":"+getSunCost(stack)).applyTextStyle(TextFormatting.YELLOW));
	}
	
	protected abstract int getSunCost(ItemStack stack);
	
	@Override
	public boolean isEnchantable(ItemStack stack) {
		return !this.isFragment;
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
