package com.hungteen.pvz.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class SunMendingEnchantment extends Enchantment {

	public SunMendingEnchantment() {
		super(Rarity.VERY_RARE, EnchantmentType.BREAKABLE, EquipmentSlotType.values());
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return this.getMinCost(enchantmentLevel) + 10;
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return 10 * enchantmentLevel + 20;
	}

	public boolean isTreasureOnly() {
		return true;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}
	
}
