package com.hungteen.pvz.enchantment;

import com.hungteen.pvz.register.EnchantmentRegister;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class SunReduceEnchantment extends Enchantment{

	public SunReduceEnchantment() {
		super(Rarity.COMMON, EnchantmentRegister.SUMMON_CARD, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return enchantmentLevel*5+1;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return this.getMinEnchantability(enchantmentLevel)+5;
	}
	
	@Override
	public int getMaxLevel() {
		return 10;
	}
}
