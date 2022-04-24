package com.hungteen.pvz.common.enchantment;

import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import com.hungteen.pvz.common.tag.PVZItemTags;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-26 16:26
 **/
public class PVZEnchantmentCategories {

    public static final EnchantmentCategory SUN_ITEMS = EnchantmentCategory.create("sun_items", (item) -> {
        return item.builtInRegistryHolder().is(PVZItemTags.COST_SUN_ITEMS);
    });

    public static final EnchantmentCategory SUMMON_CARD = EnchantmentCategory.create("summon_card", (item) -> {
        return item instanceof SummonCardItem;
    });

    public static final EnchantmentCategory PLANT_CARD = EnchantmentCategory.create("plant_card", (item) -> {
        return item instanceof PlantCardItem;
    });

    public static final EnchantmentCategory REACH = EnchantmentCategory.create("reach", (item) -> {
        return item.builtInRegistryHolder().is(PVZItemTags.REACH_ITEMS);
    });

}
