package com.hungteen.pvz.common.enchantment.card;

import com.hungteen.pvz.common.enchantment.PVZEnchantment;
import com.hungteen.pvz.common.enchantment.PVZEnchantmentCategories;
import com.hungteen.pvz.common.enchantment.PVZEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.Random;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-26 16:15
 **/
public class ImmediateCDEnchantment extends PVZEnchantment {

    public ImmediateCDEnchantment() {
        super(Rarity.RARE, PVZEnchantmentCategories.SUMMON_CARD, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    public static boolean canImmediateCD(ItemStack stack, Random rand) {
        final int lvl = EnchantmentHelper.getItemEnchantmentLevel(PVZEnchantments.IMMEDIATE_CD.get(), stack);
        final float chance = (lvl == 1 ? 0.08F : lvl == 2 ? 0.15F : 0.3F);
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

    @Override
    public boolean isTradeable() {
        return false;
    }
}
