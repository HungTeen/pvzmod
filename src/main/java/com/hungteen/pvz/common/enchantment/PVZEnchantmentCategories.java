package com.hungteen.pvz.common.enchantment;

import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-26 16:26
 **/
public class PVZEnchantmentCategories {

    public static final EnchantmentCategory SUMMON_CARD = EnchantmentCategory.create("summon_card", (item) -> {
        return item instanceof SummonCardItem;
    });

}
