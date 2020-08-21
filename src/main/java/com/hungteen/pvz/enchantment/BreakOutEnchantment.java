package com.hungteen.pvz.enchantment;

import com.hungteen.pvz.register.EnchantmentRegister;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class BreakOutEnchantment extends Enchantment{

	public BreakOutEnchantment() {
		super(Rarity.RARE, EnchantmentRegister.PLANT_CARD, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return enchantmentLevel*10;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return getMaxEnchantability(enchantmentLevel)+5;
	}
	
	@Override
	public int getMaxLevel() {
		return 4;
	}
	
}
