package com.hungteen.pvz.common.enchantment.misc;

import com.hungteen.pvz.common.enchantment.PVZEnchantment;
import com.hungteen.pvz.common.enchantment.PVZEnchantmentTypes;
import net.minecraft.inventory.EquipmentSlotType;

public class SunShovelEnchantment extends PVZEnchantment {

	public SunShovelEnchantment() {
		super(Rarity.RARE, PVZEnchantmentTypes.SHOVEL, new EquipmentSlotType[] { EquipmentSlotType.OFFHAND, EquipmentSlotType.MAINHAND });
		this.isTradeable = false;
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
