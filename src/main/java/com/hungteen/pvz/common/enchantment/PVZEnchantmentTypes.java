package com.hungteen.pvz.common.enchantment;

import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import com.hungteen.pvz.common.misc.tag.PVZItemTags;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.ShovelItem;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-02-06 14:24
 **/
public class PVZEnchantmentTypes {

    public static final EnchantmentType SUMMON_CARD = EnchantmentType.create("summon_card", (item) -> {
        return item instanceof SummonCardItem;
    });

    public static final EnchantmentType ENTITY_CARD = EnchantmentType.create("entity_card", (item) -> {
        if(item instanceof PlantCardItem) {
            return ! ((PlantCardItem) item).plantType.getPlantBlock().isPresent() && ! ((PlantCardItem) item).plantType.isOuterPlant();
        }
        return false;
    });

    public static final EnchantmentType PLANT_OR_OUTER_CARD = EnchantmentType.create("plant_or_outer_card", (item) -> {
        if(item instanceof PlantCardItem) {
            return ! ((PlantCardItem) item).plantType.getPlantBlock().isPresent();
        }
        return false;
    });

    public static final EnchantmentType NO_OUTER_PLANT_CARD = EnchantmentType.create("no_outer_plant_card", (item) -> {
        if(item instanceof PlantCardItem) {
            return ! ((PlantCardItem) item).plantType.isOuterPlant();
        }
        return false;
    });

    public static final EnchantmentType PLANT_CARD = EnchantmentType.create("plant_card", (item) -> {
        return item instanceof PlantCardItem;
    });

    public static final EnchantmentType SHOVEL = EnchantmentType.create("shovel", (item) -> {
        return item instanceof ShovelItem;
    });

    public static final EnchantmentType REACH = EnchantmentType.create("reach", (item) -> {
        return item.is(PVZItemTags.REACH_ITEMS);
    });

    public static EnchantmentType[] getPVZEnchantmentTypes(){
        return new EnchantmentType[]{SUMMON_CARD, ENTITY_CARD, PLANT_OR_OUTER_CARD, NO_OUTER_PLANT_CARD, PLANT_CARD, SHOVEL, REACH};
    }

}
