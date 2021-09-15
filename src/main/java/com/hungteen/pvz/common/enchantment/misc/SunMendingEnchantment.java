package com.hungteen.pvz.common.enchantment.misc;

import com.hungteen.pvz.common.enchantment.PVZEnchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;

public class SunMendingEnchantment extends PVZEnchantment {

	public SunMendingEnchantment() {
		super(Rarity.VERY_RARE, EnchantmentType.BREAKABLE, EquipmentSlotType.values());
		this.isTradeable = false;
		this.isTreasureOnly = true;
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
		return 5;
	}
	
}
