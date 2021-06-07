package com.hungteen.pvz.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class PVZEnchantment extends Enchantment {

	protected boolean isTradeable = true;
	protected boolean isCurse = false;
	protected boolean isTreasureOnly = false;
	
	public PVZEnchantment(Rarity p_i46731_1_, EnchantmentType p_i46731_2_, EquipmentSlotType[] p_i46731_3_) {
		super(p_i46731_1_, p_i46731_2_, p_i46731_3_);
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
