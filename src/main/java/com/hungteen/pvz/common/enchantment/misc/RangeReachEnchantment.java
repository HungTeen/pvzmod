package com.hungteen.pvz.common.enchantment.misc;

import com.hungteen.pvz.common.enchantment.EnchantmentRegister;
import com.hungteen.pvz.common.enchantment.PVZEnchantment;
import com.hungteen.pvz.common.enchantment.PVZEnchantmentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class RangeReachEnchantment extends PVZEnchantment {

    public RangeReachEnchantment() {
        super(Rarity.UNCOMMON, PVZEnchantmentTypes.REACH, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
    }

    public static float getReachDistance(ItemStack stack, float range){
        final int lvl = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegister.RANGE_REACH.get(), stack);
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
