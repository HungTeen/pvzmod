package com.hungteen.pvz.common.enchantment.card.plantcard;

import com.hungteen.pvz.common.enchantment.EnchantmentRegister;
import com.hungteen.pvz.common.enchantment.PVZEnchantment;

import net.minecraft.inventory.EquipmentSlotType;

public class DenselyPlantEnchantment extends PVZEnchantment {

	public DenselyPlantEnchantment() {
		super(Rarity.VERY_RARE, EnchantmentRegister.NO_OUTER_PLANT_CARD, new EquipmentSlotType[] { EquipmentSlotType.OFFHAND, EquipmentSlotType.MAINHAND });
		this.isTradeable = false;
		this.isTreasureOnly = true;
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 40;
	}
    
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return 100;
	}
	
	@Override
	public int getMaxLevel() {
		return 2;
	}

}
