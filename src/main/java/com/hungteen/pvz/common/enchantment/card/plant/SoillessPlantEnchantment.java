package com.hungteen.pvz.common.enchantment.card.plant;

import com.hungteen.pvz.common.enchantment.PVZEnchantment;
import com.hungteen.pvz.common.enchantment.PVZEnchantmentCategories;
import com.hungteen.pvz.common.enchantment.PVZEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-28 14:28
 **/
public class SoillessPlantEnchantment extends PVZEnchantment {

    public SoillessPlantEnchantment() {
        super(Rarity.VERY_RARE, PVZEnchantmentCategories.PLANT_CARD, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    public static boolean isSoilless(ItemStack stack) {
        return EnchantmentHelper.getItemEnchantmentLevel(PVZEnchantments.SOILLESS_PLANT.get(), stack) > 0;
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
        return 1;
    }

    @Override
    public boolean isShopOnly() {
        return true;
    }
}
