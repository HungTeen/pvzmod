package com.hungteen.pvz.misc.tag;

import com.hungteen.pvz.PVZMod;

import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class PVZItemTags {

	//forge
	public static final Tag<Item> PEAS = forgeTag("crops/pea");
	public static final Tag<Item> NUTS = forgeTag("crops/nut");
	public static final Tag<Item> CHILIPEPPERS = forgeTag("crops/chilipepper");
	public static final Tag<Item> CABBAGES = forgeTag("crops/cabbage");
	public static final Tag<Item> CABBAGE_SEEDS = forgeTag("seeds/cabbage");
	public static final Tag<Item> CORN = forgeTag("crops/corn");
	public static final Tag<Item> CORN_SEEDS = forgeTag("seeds/corn");
	public static final Tag<Item> AMETHYST_INGOTS = forgeTag("ingots/amethyst");
	public static final Tag<Item> STEEL_INGOTS = forgeTag("ingots/steel");
	public static final Tag<Item> AMETHYST_ORES = forgeTag("ores/amethyst");
	
	//pvz
	public static final Tag<Item> PLANT_SUMMON_CARDS = pvzTag("cards/plant_summon_card");
	public static final Tag<Item> PLANT_ENJOY_CARDS = pvzTag("cards/plant_enjoy_card");
	public static final Tag<Item> PLANT_CARDS = pvzTag("cards/plant_card");
	public static final Tag<Item> TEMPLATE_CARDS = pvzTag("cards/template_card");
		
	private static Tag<Item> pvzTag(String name){
        return new ItemTags.Wrapper(new ResourceLocation(PVZMod.MOD_ID, name));
    }
	
	private static Tag<Item> forgeTag(String name){
        return new ItemTags.Wrapper(new ResourceLocation("forge", name));
    }
}
