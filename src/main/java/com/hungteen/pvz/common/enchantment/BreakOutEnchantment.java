package com.hungteen.pvz.common.enchantment;

import com.hungteen.pvz.register.EnchantmentRegister;

import net.minecraft.inventory.EquipmentSlotType;

public class BreakOutEnchantment extends PVZEnchantment{

	public BreakOutEnchantment() {
		super(Rarity.VERY_RARE, EnchantmentRegister.PLANT_CARD, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
		this.isTradeable = false;
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return enchantmentLevel * 15;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return getMinCost(enchantmentLevel) + 5;
	}
	
	@Override
	public int getMaxLevel() {
		return 4;
	}
	
}
