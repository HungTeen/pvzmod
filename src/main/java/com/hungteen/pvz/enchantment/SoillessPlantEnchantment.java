package com.hungteen.pvz.enchantment;

import com.hungteen.pvz.register.EnchantmentRegister;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

public class SoillessPlantEnchantment extends Enchantment {

	public SoillessPlantEnchantment() {
		super(Rarity.VERY_RARE, EnchantmentRegister.PLANT_CARD, new EquipmentSlotType[] { EquipmentSlotType.OFFHAND, EquipmentSlotType.MAINHAND });
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return 40;
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 100;
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

}
