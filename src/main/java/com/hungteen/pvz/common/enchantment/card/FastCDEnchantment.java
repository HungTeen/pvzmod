package com.hungteen.pvz.common.enchantment.card;

import com.hungteen.pvz.common.enchantment.PVZEnchantment;
import com.hungteen.pvz.common.enchantment.PVZEnchantmentCategories;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-26 16:15
 **/
public class FastCDEnchantment extends PVZEnchantment {

    public static final int MAX_LEVEL = 5;

    public FastCDEnchantment() {
        super(Rarity.COMMON, PVZEnchantmentCategories.SUMMON_CARD, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return enchantmentLevel * 15 - 10;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return this.getMinCost(enchantmentLevel) + 25;
    }

    @Override
    public int getMaxLevel() {
        return MAX_LEVEL;
    }
}
