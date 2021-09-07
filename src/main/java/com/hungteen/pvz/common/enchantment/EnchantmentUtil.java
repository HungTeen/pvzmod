package com.hungteen.pvz.common.enchantment;

import com.hungteen.pvz.register.EnchantmentRegister;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class EnchantmentUtil {

	public static int getSunShovelAmount(ItemStack stack, int amount) {
		int lvl = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegister.SUN_SHOVEL.get(), stack);
		float percent = Math.min(1F, 0.1F * lvl);
		return MathHelper.floor(percent * amount);
	}
	
	public static int getRepairDamageByAmount(ItemStack stack, int amount) {
		int lvl = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegister.SUN_MENDING.get(), stack);
		int needSunEach = Math.max(5, 30 - 5 * lvl);
		return MathHelper.floor(amount * stack.getXpRepairRatio() / needSunEach);
	}
	
	public static int getSunCostByDamage(ItemStack stack, int damage) {
		int lvl = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegister.SUN_MENDING.get(), stack);
		int needSunEach = Math.max(5, 30 - 5 * lvl);
		return MathHelper.floor(damage * 1.0F / stack.getXpRepairRatio() * needSunEach);
	}
}
