package com.hungteen.pvz.misc.tag;

import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class PVZItemTags {

	//minecraft
	public static final ITag.INamedTag<Item> ARROWS = mcTag("arrows");
	//forge
	public static final ITag.INamedTag<Item> PEAS = forgeTag("crops/pea");
	public static final ITag.INamedTag<Item> NUTS = forgeTag("crops/nut");
	public static final ITag.INamedTag<Item> CHILIPEPPERS = forgeTag("crops/chilipepper");
	public static final ITag.INamedTag<Item> CABBAGES = forgeTag("crops/cabbage");
	public static final ITag.INamedTag<Item> CABBAGE_SEEDS = forgeTag("seeds/cabbage");
	public static final ITag.INamedTag<Item> CORNS = forgeTag("crops/corn");
	public static final ITag.INamedTag<Item> CORN_SEEDS = forgeTag("seeds/corn");
	public static final ITag.INamedTag<Item> AMETHYST_INGOTS = forgeTag("ingots/amethyst");
	public static final ITag.INamedTag<Item> STEEL_INGOTS = forgeTag("ingots/steel");
	public static final ITag.INamedTag<Item> AMETHYST_ORES = forgeTag("ores/amethyst");
	
	//pvz
	public static final ITag.INamedTag<Item> PLANT_SUMMON_CARDS = pvzTag("cards/plant_summon_card");
	public static final ITag.INamedTag<Item> PLANT_ENJOY_CARDS = pvzTag("cards/plant_enjoy_card");
	public static final ITag.INamedTag<Item> PLANT_CARDS = pvzTag("cards/plant_card");
	public static final ITag.INamedTag<Item> TEMPLATE_CARDS = pvzTag("cards/template_card");
		
	private static ITag.INamedTag<Item> pvzTag(String name){
		return ItemTags.createOptional(StringUtil.prefix(name));
    }
	
	private static ITag.INamedTag<Item> forgeTag(String name){
        return ItemTags.createOptional(new ResourceLocation("forge", name));
    }
	
	private static ITag.INamedTag<Item> mcTag(String name){
        return ItemTags.createOptional(new ResourceLocation(name));
    }
	
}
