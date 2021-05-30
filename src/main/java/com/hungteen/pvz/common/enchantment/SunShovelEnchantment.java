package com.hungteen.pvz.common.enchantment;

import com.hungteen.pvz.register.EnchantmentRegister;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class SunShovelEnchantment extends Enchantment {

	public SunShovelEnchantment() {
		super(Rarity.RARE, EnchantmentRegister.SHOVEL, new EquipmentSlotType[] { EquipmentSlotType.OFFHAND, EquipmentSlotType.MAINHAND });
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
	public int getMaxLevel() {
		return 5;
	}

}
