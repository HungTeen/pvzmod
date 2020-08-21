package com.hungteen.pvz.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class EnergyTransferEnchantment extends Enchantment{

	public EnergyTransferEnchantment() {
		super(Rarity.VERY_RARE, EnchantmentType.WEAPON, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 30;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return 100;
	}
	
	@Override
	public int getMaxLevel() {
		return 1;
	}

}
