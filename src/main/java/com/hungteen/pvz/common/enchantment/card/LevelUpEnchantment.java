package com.hungteen.pvz.common.enchantment.card;

import com.hungteen.pvz.common.enchantment.EnchantmentRegister;
import com.hungteen.pvz.common.enchantment.PVZEnchantment;

import net.minecraft.inventory.EquipmentSlotType;

public class LevelUpEnchantment extends PVZEnchantment {

	public LevelUpEnchantment() {
		super(Rarity.RARE, EnchantmentRegister.SUMMON_CARD, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return enchantmentLevel * 15 - 5;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return this.getMinCost(enchantmentLevel) + 20;
	}
	
	@Override
	public int getMaxLevel() {
		return 4;
	}
	
}
