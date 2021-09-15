package com.hungteen.pvz.common.enchantment.card;

import com.hungteen.pvz.common.enchantment.EnchantmentRegister;
import com.hungteen.pvz.common.enchantment.PVZEnchantment;

import net.minecraft.inventory.EquipmentSlotType;

public class ImmediateCDEnchantment extends PVZEnchantment {

	public ImmediateCDEnchantment() {
		super(Rarity.UNCOMMON, EnchantmentRegister.SUMMON_CARD, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return enchantmentLevel * 20 - 15;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return this.getMinCost(enchantmentLevel) + 15;
	}
	
	@Override
	public int getMaxLevel() {
		return 3;
	}
	
}
