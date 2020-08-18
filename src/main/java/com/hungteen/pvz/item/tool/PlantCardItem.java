package com.hungteen.pvz.item.tool;

import com.hungteen.pvz.item.enchant.SunReduceEnchantment;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

public class PlantCardItem extends SummonCardItem{

	protected final Plants plant;
	public PlantCardItem(Plants plant) {
		this.plant = plant;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		if(enchantment instanceof SunReduceEnchantment) return true;
		return false;
	}
	
	@Override
	public int getItemEnchantability(ItemStack stack) {
		return 20;
	}
	
	@Override
	public int getItemEnchantability() {
		return 20;
	}
}
