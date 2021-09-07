package com.hungteen.pvz.common.enchantment;

import com.hungteen.pvz.register.EnchantmentRegister;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class SunReduceEnchantment extends PVZEnchantment{

	public SunReduceEnchantment() {
		super(Rarity.COMMON, EnchantmentRegister.SUMMON_CARD, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}
	
	public static int getSunReduceNum(ItemStack stack){
		return 5 * EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegister.SUN_COST_REDUCE.get(), stack);
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return enchantmentLevel * 8 + 1;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return this.getMinCost(enchantmentLevel) + 8;
	}
	
	@Override
	public int getMaxLevel() {
		return 10;
	}
}
