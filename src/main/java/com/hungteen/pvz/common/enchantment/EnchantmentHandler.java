package com.hungteen.pvz.common.enchantment;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-28 14:17
 **/
public class EnchantmentHandler {

    public static int getQuickChargeCD(ItemStack stack, int cd){
        final int lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.QUICK_CHARGE, stack);
        if(lvl > 0){
            return (int) (cd * Math.max(0, 1 - lvl * 0.05F));
        }
        return cd;
    }

}
