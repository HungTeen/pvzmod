package com.hungteen.pvz.common.tag;

import com.hungteen.pvz.utils.Util;
import net.minecraft.resources.ResourceLocation;
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
    public static final TagKey<Item> ESSENCES = pvzTag("essences");
    public static final TagKey<Item> PLANT_SUMMON_CARDS = pvzTag("cards/plant_summon_card");
    public static final TagKey<Item> PLANT_ENJOY_CARDS = pvzTag("cards/plant_enjoy_card");
    public static final TagKey<Item> PLANT_CARDS = pvzTag("cards/plant_card");
    public static final TagKey<Item> TEMPLATE_CARDS = pvzTag("cards/template_card");
    public static final TagKey<Item> ESSENCE_ORES = pvzTag("ores/essence");
    public static final TagKey<Item> PEA_GUN_BULLETS = pvzTag("pea_gun_bullets");
    public static final TagKey<Item> REACH_ITEMS = pvzTag("reach_items");
    public static final TagKey<Item> GRAY_MATERIALS = pvzTag("card/gray_materials");
    public static final TagKey<Item> WHITE_MATERIALS = pvzTag("card/white_materials");
    public static final TagKey<Item> GREEN_MATERIALS = pvzTag("card/green_materials");
    public static final TagKey<Item> BLUE_MATERIALS = pvzTag("card/blue_materials");
    public static final TagKey<Item> PURPLE_MATERIALS = pvzTag("card/purple_materials");
    public static final TagKey<Item> GOLD_MATERIALS = pvzTag("card/gold_materials");
    public static final TagKey<Item> RED_MATERIALS = pvzTag("card/red_materials");
    public static final TagKey<Item> BLACK_MATERIALS = pvzTag("card/black_materials");
    public static final TagKey<Item> GRAY_CARDS = pvzTag("card/gray_cards");
    public static final TagKey<Item> WHITE_CARDS = pvzTag("card/white_cards");
    public static final TagKey<Item> GREEN_CARDS = pvzTag("card/green_cards");
    public static final TagKey<Item> BLUE_CARDS = pvzTag("card/blue_cards");
    public static final TagKey<Item> PURPLE_CARDS = pvzTag("card/purple_cards");
    public static final TagKey<Item> GOLD_CARDS = pvzTag("card/gold_cards");
    public static final TagKey<Item> RED_CARDS = pvzTag("card/red_cards");
    public static final TagKey<Item> BLACK_CARDS = pvzTag("card/black_cards");

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
