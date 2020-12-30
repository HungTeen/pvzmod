package com.hungteen.pvz.enchantment;

import com.hungteen.pvz.register.EnchantmentRegister;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class SunShovelEnchantment extends Enchantment {

	public SunShovelEnchantment() {
		super(Rarity.RARE, EnchantmentRegister.SHOVEL, new EquipmentSlotType[] { EquipmentSlotType.OFFHAND, EquipmentSlotType.MAINHAND });
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return 25;
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 50;
	}

	public boolean isTreasureEnchantment() {
		return true;
	}

	@Override
	public int getMaxLevel() {
		return 4;
	}

}
