package com.hungteen.pvz.common.enchantment.card;

import com.hungteen.pvz.common.enchantment.PVZEnchantment;
import com.hungteen.pvz.common.enchantment.PVZEnchantmentCategories;
import com.hungteen.pvz.common.enchantment.PVZEnchantments;
import com.hungteen.pvz.utils.MathUtil;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.Random;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-28 13:43
 **/
public class SunReductionEnchantment extends PVZEnchantment {

    public SunReductionEnchantment() {
        super(Rarity.COMMON, PVZEnchantmentCategories.SUN_ITEMS, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    public static int getReductionCost(ItemStack stack, int cost) {
        final int lvl = EnchantmentHelper.getItemEnchantmentLevel(PVZEnchantments.SUN_REDUCTION.get(), stack);
        final int reduction = (cost < 100 ? 5 : cost < 200 ? 15 : cost < 500 ? 25 : cost < 1000 ? 35 : 50) * lvl;
        return Math.max(0, cost - reduction);
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return 20 * enchantmentLevel + 5;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return this.getMinCost(enchantmentLevel) + 30;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isShopOnly() {
        return true;
    }

    @Override
    public boolean isDiscoverable() {
        return true;
    }
}
