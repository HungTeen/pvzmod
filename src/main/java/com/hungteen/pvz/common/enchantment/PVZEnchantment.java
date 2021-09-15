package com.hungteen.pvz.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class PVZEnchantment extends Enchantment {

	protected boolean isTradeable = true;
	protected boolean isCurse = false;
	protected boolean isTreasureOnly = false;
	
	public PVZEnchantment(Rarity rarity, EnchantmentType enchant, EquipmentSlotType[] slotTypes) {
		super(rarity, enchant, slotTypes);
	}
	
	@Override
	public boolean isTradeable() {
		return this.isTradeable;
	}
	
	@Override
	public boolean isCurse() {
		return this.isCurse;
	}
	
	@Override
	public boolean isTreasureOnly() {
		return this.isTreasureOnly;
	}

}
