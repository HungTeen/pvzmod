package com.hungteen.pvz.common.enchantment;

import java.util.Random;

import com.hungteen.pvz.register.EnchantmentRegister;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class BreakOutEnchantment extends PVZEnchantment{

	public BreakOutEnchantment() {
		super(Rarity.VERY_RARE, EnchantmentRegister.PLANT_CARD, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
		this.isTradeable = false;
	}

	public static boolean canBreakOut(Random rand, ItemStack stack) {
		return rand.nextFloat() * 100 < BreakOutEnchantment.getPlantBreakOutChance(stack);
	}
	
	private static float getPlantBreakOutChance(ItemStack stack) {
		final int lvl = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegister.BREAK_OUT.get(), stack);
		return lvl == 0 ? 0F : 2.5F * (lvl + 1);
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return enchantmentLevel * 25;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel) {
		return getMinCost(enchantmentLevel) + 10;
	}
	
	@Override
	public int getMaxLevel() {
		return 4;
	}
	
}
