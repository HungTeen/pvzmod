package com.hungteen.pvz.common.enchantment.card;

import java.util.Random;

import com.hungteen.pvz.common.enchantment.EnchantmentRegister;
import com.hungteen.pvz.common.enchantment.PVZEnchantment;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class ImmediateCDEnchantment extends PVZEnchantment {

	public ImmediateCDEnchantment() {
		super(Rarity.UNCOMMON, EnchantmentRegister.SUMMON_CARD, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}
	
	public static boolean canImmediateCD(ItemStack stack, Random rand) {
		final int lvl = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegister.IMMEDIATE_CD.get(), stack);
		final float chance = (lvl == 1 ? 0.05F : lvl == 2 ? 0.1F : 0.2F);
		return lvl > 0 && rand.nextFloat() < chance; 
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
