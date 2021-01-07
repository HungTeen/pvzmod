package com.hungteen.pvz.enchantment;

import com.hungteen.pvz.register.EnchantmentRegister;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class EnchantmentUtil {

	public static int getSunReduceNum(ItemStack stack){
		int lvl = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegister.SUN_COST_REDUCE.get(), stack);
		return 5 * lvl;
	}
	
	public static int getPlantBreakOutChance(ItemStack stack) {
		int lvl = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegister.BREAK_OUT.get(), stack);
		int num = lvl * 3 + 7;
		num = (lvl == 4 ? num + 1 : num);
		return (lvl == 0 ? 0 : num);
	}
	
	public static int getSunShovelAmount(ItemStack stack, int amount) {
		int lvl = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegister.SUN_SHOVEL.get(), stack);
		float percent = Math.min(1F, 0.1F * lvl);
		return MathHelper.floor(percent * amount);
	}
}
