package com.hungteen.pvz.common.enchantment.card;

import com.hungteen.pvz.common.enchantment.EnchantmentRegister;
import com.hungteen.pvz.common.enchantment.PVZEnchantment;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.MathUtil;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class LevelUpEnchantment extends PVZEnchantment {

	public LevelUpEnchantment() {
		super(Rarity.RARE, EnchantmentRegister.SUMMON_CARD, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
	}

	public static void levelUp(PVZPlantEntity plantEntity, ItemStack stack) {
		if(plantEntity.getRandom().nextFloat() < 0.2F) {
			final int lvl = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegister.LEVEL_UP.get(), stack);
			final int min = lvl < 4 ? 0 : 1;
			final int max = lvl < 4 ? lvl : 3;
			final int level = MathUtil.getRandomMinMax(plantEntity.getRandom(), min, max);
			plantEntity.setPlantLvl(plantEntity.getPlantLvl() + level);
		}
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
