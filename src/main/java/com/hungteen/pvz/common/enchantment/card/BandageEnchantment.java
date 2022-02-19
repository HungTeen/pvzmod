package com.hungteen.pvz.common.enchantment.card;

import com.hungteen.pvz.common.enchantment.EnchantmentRegister;
import com.hungteen.pvz.common.enchantment.PVZEnchantment;
import com.hungteen.pvz.common.enchantment.PVZEnchantmentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class BandageEnchantment extends PVZEnchantment {

    public BandageEnchantment() {
        super(Rarity.UNCOMMON, PVZEnchantmentTypes.SUMMON_CARD, new EquipmentSlotType[] {EquipmentSlotType.MAINHAND});
    }

    public static float getHealPercent(ItemStack stack){
        final int lvl = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegister.CARD_HEAL.get(), stack);
        return 0.2F * (lvl + 1);
    }
    @Override
    public int getMinCost(int enchantmentLevel) {
        return enchantmentLevel * 10;
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
