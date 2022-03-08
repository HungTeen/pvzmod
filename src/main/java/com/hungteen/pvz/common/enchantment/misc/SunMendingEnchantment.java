package com.hungteen.pvz.common.enchantment.misc;

import com.hungteen.pvz.common.enchantment.EnchantmentRegister;
import com.hungteen.pvz.common.enchantment.PVZEnchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public class SunMendingEnchantment extends PVZEnchantment {

	public SunMendingEnchantment() {
		super(Rarity.VERY_RARE, EnchantmentType.BREAKABLE, EquipmentSlotType.values());
		this.isTradeable = false;
		this.isTreasureOnly = true;
	}
	
	public static void repairItem(ItemStack stack, int amount) {
		if (! stack.isEmpty() && stack.isDamaged()) {
			final int lvl = getLevel(stack);
			final int needSunEach = Math.max(5, 30 - 5 * lvl);
			final int repairDamage = Math.min(stack.getDamageValue(), MathHelper.floor(amount * stack.getXpRepairRatio() / needSunEach));
            stack.setDamageValue(stack.getDamageValue() - repairDamage);
        }
	}
	
	public static int getLevel(ItemStack stack) {
		return EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegister.SUN_MENDING.get(), stack);
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return this.getMinCost(enchantmentLevel) + 10;
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 10 * enchantmentLevel + 20;
	}
	
	@Override
	protected boolean checkCompatibility(Enchantment enchant) {
		return super.checkCompatibility(enchant) || enchant == Enchantments.MENDING;
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}
	
}
