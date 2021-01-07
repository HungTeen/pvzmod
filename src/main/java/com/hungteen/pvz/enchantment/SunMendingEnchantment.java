package com.hungteen.pvz.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class SunMendingEnchantment extends Enchantment {

	public SunMendingEnchantment() {
		super(Rarity.VERY_RARE, EnchantmentType.BREAKABLE, EquipmentSlotType.values());
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return 25;
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 50;
	}

	public boolean isTreasureEnchantment() {
		return true;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}
	
}
