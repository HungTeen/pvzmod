package com.hungteen.pvz.common.tag;

import com.hungteen.pvz.utils.Util;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 21:27
 **/
public class PVZItemTags {

    /* minecraft */
    public static final TagKey<Item> ARROWS = mcTag("arrows");

    /* forge */
    public static final TagKey<Item> PEAS = forgeTag("crops/pea");
    public static final TagKey<Item> NUTS = forgeTag("crops/nut");
    public static final TagKey<Item> CHILIPEPPERS = forgeTag("crops/chilipepper");
    public static final TagKey<Item> CABBAGES = forgeTag("crops/cabbage");
    public static final TagKey<Item> CABBAGE_SEEDS = forgeTag("seeds/cabbage");
    public static final TagKey<Item> CORNS = forgeTag("crops/corn");
    public static final TagKey<Item> CORN_SEEDS = forgeTag("seeds/corn");
    public static final TagKey<Item> AMETHYST_INGOTS = forgeTag("ingots/amethyst");
    public static final TagKey<Item> AMETHYST_ORES = forgeTag("ores/amethyst");

    /* pvz */
    //cards.
    public static final TagKey<Item> ALL_CARDS = pvzTag("card/all_card");
    public static final TagKey<Item> PLANT_SUMMON_CARDS = pvzTag("card/plant_summon_card");
    public static final TagKey<Item> PLANT_ENJOY_CARDS = pvzTag("card/plant_enjoy_card");
    public static final TagKey<Item> PLANT_CARDS = pvzTag("card/plant_card");
    public static final TagKey<Item> TEMPLATE_CARDS = pvzTag("card/template_card");

    //essences.
    public static final TagKey<Item> ESSENCES = pvzTag("essences");
    public static final TagKey<Item> ESSENCE_ORES = pvzTag("ores/essence");

    //for enchantment tag.
    public static final TagKey<Item> REACH_ITEMS = pvzTag("reach_items");
    public static final TagKey<Item> COST_SUN_ITEMS = pvzTag("cost_sun_items");

    //special.
    public static final TagKey<Item> SHIELD_ITEMS = pvzTag("shield_items");
    public static final TagKey<Item> ARMOR_ITEMS = pvzTag("armor_items");
    public static final TagKey<Item> PEA_GUN_BULLETS = pvzTag("pea_gun_bullets");

    // Card materials.
    public static final TagKey<Item> WHITE_MATERIALS = pvzTag("card/white_materials");
    public static final TagKey<Item> GOLD_MATERIALS = pvzTag("card/gold_materials");
    public static final TagKey<Item> BLUE_MATERIALS = pvzTag("card/blue_materials");
    public static final TagKey<Item> BLACK_MATERIALS = pvzTag("card/black_materials");

    // Template card tags (use to achieve maga card).
    public static final TagKey<Item> SAPLING_CARDS = pvzTag("card/sapling_cards");
    public static final TagKey<Item> SAPLING_MATERIALS = pvzTag("card/sapling_materials");
    public static final TagKey<Item> NETHER_WART_CARDS = pvzTag("card/nether_wart_cards");
    public static final TagKey<Item> NETHER_WART_MATERIALS = pvzTag("card/nether_wart_materials");
    public static final TagKey<Item> CHORUS_FRUIT_CARDS = pvzTag("card/chorus_fruit_cards");
    public static final TagKey<Item> CHORUS_FRUIT_MATERIALS = pvzTag("card/chorus_fruit_materials");
    public static final TagKey<Item> UPGRADE_CARDS = pvzTag("card/upgrade_cards");
    public static final TagKey<Item> WISDOM_CARDS = pvzTag("card/wisdom_cards");

    private static TagKey<Item> pvzTag(String name){
        return ItemTags.create(Util.prefix(name));
    }

    private static TagKey<Item> forgeTag(String name){
        return ItemTags.create(Util.forgePrefix(name));
    }

    private static TagKey<Item> mcTag(String name){
        return ItemTags.create(Util.mcPrefix(name));
    }
}
