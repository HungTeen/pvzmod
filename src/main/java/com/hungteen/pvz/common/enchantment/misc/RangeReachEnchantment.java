package com.hungteen.pvz.common.enchantment.misc;

import com.hungteen.pvz.common.enchantment.PVZEnchantment;
import com.hungteen.pvz.common.enchantment.PVZEnchantmentCategories;
import com.hungteen.pvz.common.enchantment.PVZEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-23 10:44
 **/
public class RangeReachEnchantment extends PVZEnchantment {

    public RangeReachEnchantment() {
        super(Rarity.UNCOMMON, PVZEnchantmentCategories.REACH, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    public static float getReachDistance(ItemStack stack, float range){
        final int lvl = EnchantmentHelper.getItemEnchantmentLevel(PVZEnchantments.RANGE_REACH.get(), stack);
        return range + range * (lvl * 0.2F);
    }

    @Override
    public int getMinCost(int enchantmentLevel) {
        return 10 * enchantmentLevel;
    }

    @Override
    public int getMaxCost(int enchantmentLevel) {
        return 20 + getMinCost(enchantmentLevel);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }


}
