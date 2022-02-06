package com.hungteen.pvz.common.enchantment.card;

import com.hungteen.pvz.common.enchantment.PVZEnchantment;
import com.hungteen.pvz.common.enchantment.PVZEnchantmentTypes;
import net.minecraft.inventory.EquipmentSlotType;

public class CharmEnchantment extends PVZEnchantment{

	public CharmEnchantment() {
		super(Rarity.VERY_RARE, PVZEnchantmentTypes.ENTITY_CARD, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
		this.isTradeable = false;
		this.isTreasureOnly = true;
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
