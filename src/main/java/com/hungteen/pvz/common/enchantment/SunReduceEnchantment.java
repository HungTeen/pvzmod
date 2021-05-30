package com.hungteen.pvz.common.enchantment;

import com.hungteen.pvz.register.EnchantmentRegister;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class SunReduceEnchantment extends Enchantment{

	public SunReduceEnchantment() {
		super(Rarity.COMMON, EnchantmentRegister.SUMMON_CARD, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return enchantmentLevel * 8 + 1;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return this.getMinCost(enchantmentLevel) + 8;
	}
	
	@Override
	public int getMaxLevel() {
		return 10;
	}
}
