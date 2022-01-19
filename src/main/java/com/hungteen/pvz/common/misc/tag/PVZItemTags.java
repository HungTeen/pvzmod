package com.hungteen.pvz.common.misc.tag;

import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class PVZItemTags {

	//minecraft
	public static final INamedTag<Item> ARROWS = mcTag("arrows");
	//forge
	public static final INamedTag<Item> PEAS = forgeTag("crops/pea");
	public static final INamedTag<Item> NUTS = forgeTag("crops/nut");
	public static final INamedTag<Item> CHILIPEPPERS = forgeTag("crops/chilipepper");
	public static final INamedTag<Item> CABBAGES = forgeTag("crops/cabbage");
	public static final INamedTag<Item> CABBAGE_SEEDS = forgeTag("seeds/cabbage");
	public static final INamedTag<Item> CORNS = forgeTag("crops/corn");
	public static final INamedTag<Item> CORN_SEEDS = forgeTag("seeds/corn");
	public static final INamedTag<Item> AMETHYST_INGOTS = forgeTag("ingots/amethyst");
	public static final INamedTag<Item> AMETHYST_ORES = forgeTag("ores/amethyst");
	
	//pvz
	public static final INamedTag<Item> PLANT_SUMMON_CARDS = pvzTag("cards/plant_summon_card");
	public static final INamedTag<Item> PLANT_ENJOY_CARDS = pvzTag("cards/plant_enjoy_card");
	public static final INamedTag<Item> PLANT_CARDS = pvzTag("cards/plant_card");
	public static final INamedTag<Item> TEMPLATE_CARDS = pvzTag("cards/template_card");
	public static final INamedTag<Item> ESSENCE_ORES = pvzTag("ores/essence");
	public static final INamedTag<Item> PEA_GUN_BULLETS = pvzTag("pea_gun_bullets");
	public static final INamedTag<Item> REACH_ITEMS = pvzTag("reach_items");
	public static final INamedTag<Item> GRAY_MATERIALS = pvzTag("card/gray_materials");
	public static final INamedTag<Item> WHITE_MATERIALS = pvzTag("card/white_materials");
	public static final INamedTag<Item> GREEN_MATERIALS = pvzTag("card/green_materials");
	public static final INamedTag<Item> BLUE_MATERIALS = pvzTag("card/blue_materials");
	public static final INamedTag<Item> PURPLE_MATERIALS = pvzTag("card/purple_materials");
	public static final INamedTag<Item> GOLD_MATERIALS = pvzTag("card/gold_materials");
	public static final INamedTag<Item> RED_MATERIALS = pvzTag("card/red_materials");
	public static final INamedTag<Item> BLACK_MATERIALS = pvzTag("card/black_materials");
	public static final INamedTag<Item> GRAY_CARDS = pvzTag("card/gray_cards");
	public static final INamedTag<Item> WHITE_CARDS = pvzTag("card/white_cards");
	public static final INamedTag<Item> GREEN_CARDS = pvzTag("card/green_cards");
	public static final INamedTag<Item> BLUE_CARDS = pvzTag("card/blue_cards");
	public static final INamedTag<Item> PURPLE_CARDS = pvzTag("card/purple_cards");
	public static final INamedTag<Item> GOLD_CARDS = pvzTag("card/gold_cards");
	public static final INamedTag<Item> RED_CARDS = pvzTag("card/red_cards");
	public static final INamedTag<Item> BLACK_CARDS = pvzTag("card/black_cards");
		
	private static INamedTag<Item> pvzTag(String name){
		return ItemTags.createOptional(StringUtil.prefix(name));
    }
	
	private static INamedTag<Item> forgeTag(String name){
        return ItemTags.createOptional(new ResourceLocation("forge", name));
    }
	
	private static INamedTag<Item> mcTag(String name){
        return ItemTags.createOptional(new ResourceLocation(name));
    }
	
}
