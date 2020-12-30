package com.hungteen.pvz.enchantment;

import com.hungteen.pvz.register.EnchantmentRegister;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class BreakOutEnchantment extends Enchantment{

	public BreakOutEnchantment() {
		super(Rarity.VERY_RARE, EnchantmentRegister.PLANT_CARD, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return enchantmentLevel * 15;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return getMinEnchantability(enchantmentLevel) + 5;
	}
	
	@Override
	public int getMaxLevel() {
		return 4;
	}
	
}
