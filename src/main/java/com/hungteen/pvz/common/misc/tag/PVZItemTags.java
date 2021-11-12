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
