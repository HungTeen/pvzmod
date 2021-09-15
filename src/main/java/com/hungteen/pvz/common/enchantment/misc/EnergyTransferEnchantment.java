package com.hungteen.pvz.common.enchantment.misc;

import com.hungteen.pvz.common.enchantment.PVZEnchantment;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class EnergyTransferEnchantment extends PVZEnchantment{

	public EnergyTransferEnchantment() {
		super(Rarity.UNCOMMON, EnchantmentType.WEAPON, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 30;
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
