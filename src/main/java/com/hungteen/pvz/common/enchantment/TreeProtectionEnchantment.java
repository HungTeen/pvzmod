package com.hungteen.pvz.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class TreeProtectionEnchantment extends Enchantment {

	public TreeProtectionEnchantment() {
		super(Rarity.UNCOMMON, EnchantmentType.ARMOR , new EquipmentSlotType[] {EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET});
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 40;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return 100;
	}
	
	@Override
	public int getMaxLevel() {
		return 1;
	}
	
}
