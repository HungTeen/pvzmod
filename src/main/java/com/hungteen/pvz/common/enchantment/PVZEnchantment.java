package com.hungteen.pvz.common.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-26 16:15
 *
 **/
public class PVZEnchantment extends Enchantment {

    public PVZEnchantment(Rarity rarity, EnchantmentCategory enchant, EquipmentSlot[] slotTypes) {
        super(rarity, enchant, slotTypes);
    }

    /**
     * can get the enchantment by trade with villagers.
     */
    @Override
    public boolean isTradeable() {
        return super.isTradeable() && ! isShopOnly();
    }

    /**
     * can enchant from enchantment table.
     */
    @Override
    public boolean isTreasureOnly() {
        return super.isTreasureOnly() || isShopOnly();
    }

    /**
     * can find in chest.
     */
    @Override
    public boolean isDiscoverable() {
        return super.isDiscoverable() && ! isShopOnly();
    }

    /**
     * can only buy from pvz shop.
     */
    public boolean isShopOnly(){
        return false;
    }
}
