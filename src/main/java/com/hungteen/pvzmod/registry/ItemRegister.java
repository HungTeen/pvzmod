package com.hungteen.pvzmod.registry;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvzmod.items.ItemBase;
import com.hungteen.pvzmod.items.armors.ArmorBase;
import com.hungteen.pvzmod.items.armors.ArmorModel;
import com.hungteen.pvzmod.items.food.ItemJuiceBase;
import com.hungteen.pvzmod.items.material.ItemGlassCup;
import com.hungteen.pvzmod.items.plants.ItemPea;
import com.hungteen.pvzmod.items.tools.ItemDaveShovel;
import com.hungteen.pvzmod.items.tools.ItemSummonCard;
import com.hungteen.pvzmod.items.tools.ToolAxe;
import com.hungteen.pvzmod.items.tools.ToolHoe;
import com.hungteen.pvzmod.items.tools.ToolPickaxe;
import com.hungteen.pvzmod.items.tools.ToolShovel;
import com.hungteen.pvzmod.items.tools.ToolSword;
import com.hungteen.pvzmod.items.weapons.ItemPeaGun;
import com.hungteen.pvzmod.util.Reference;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemRegister {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	public static final List<ItemSummonCard> CARDS = new ArrayList<ItemSummonCard>();
	
	//Materials
	public static final ToolMaterial STEEL_TOOL = EnumHelper.addToolMaterial("steel_tool", 3, 600, 9, 3, 3);
	public static final ArmorMaterial STEEL_ARMOR = EnumHelper.addArmorMaterial("steel_armor", Reference.MODID+":steel", 25, new int[]{3, 7, 7, 3}, 7, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
	public static final ArmorMaterial BARRIER_ARMOR =EnumHelper.addArmorMaterial("barrier_armor", Reference.MODID+":barrier", 10, new int[]{6, 1, 1, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0f);
	
	//Items
	public static final Item COAL_NUGGET = new ItemBase("coal_nugget",CreativeTabRegister.MATERIAL_TAB);
	public static final Item STEEL_INGOT = new ItemBase("steel_ingot",CreativeTabRegister.MATERIAL_TAB);
	public static final Item ALUMINUM_INGOT = new ItemBase("aluminum_ingot",CreativeTabRegister.MATERIAL_TAB);
	public static final Item ORIGIN_ELEMENT = new ItemBase("origin_element",CreativeTabRegister.MATERIAL_TAB);
	public static final Item COMMON_ELEMENT = new ItemBase("common_element",CreativeTabRegister.MATERIAL_TAB);
	public static final Item ELECTRICITY_ELEMENT = new ItemBase("electricity_element",CreativeTabRegister.MATERIAL_TAB);
	public static final Item FLAME_ELEMENT = new ItemBase("flame_element",CreativeTabRegister.MATERIAL_TAB);
	public static final Item ICE_ELEMENT = new ItemBase("ice_element",CreativeTabRegister.MATERIAL_TAB);
	public static final Item LIGHT_ELEMENT = new ItemBase("light_element",CreativeTabRegister.MATERIAL_TAB);
	public static final Item DARKNESS_ELEMENT = new ItemBase("darkness_element",CreativeTabRegister.MATERIAL_TAB);
	public static final Item MAGIC_ELEMENT = new ItemBase("magic_element",CreativeTabRegister.MATERIAL_TAB);
	public static final Item EXPLOSION_ELEMENT = new ItemBase("explosion_element",CreativeTabRegister.MATERIAL_TAB);
	public static final Item DEFENCE_ELEMENT = new ItemBase("defence_element",CreativeTabRegister.MATERIAL_TAB);
	public static final Item FIGHT_ELEMENT = new ItemBase("fight_element",CreativeTabRegister.MATERIAL_TAB);
	
//	public static final Item JEWEL = new ItemBase("jewel");
//	public static final Item GOLD_COIN = new ItemBase("gold_coin");
//	public static final Item SILVER_COIN = new ItemBase("silver_coin");
//	public static final Item COPPER_COIN = new ItemBase("copper_coin");
	
	//plants
	public static final Item PEA = new ItemPea("pea",CreativeTabRegister.CROP_TAB);
	public static final Item SNOW_PEA = new ItemBase("snow_pea",CreativeTabRegister.MATERIAL_TAB);
	public static final Item FIRE_PEA = new ItemBase("fire_pea",CreativeTabRegister.MATERIAL_TAB);
	public static final Item BLUE_FIRE_PEA = new ItemBase("blue_fire_pea",CreativeTabRegister.MATERIAL_TAB);
	public static final Item NUT = new ItemBase("nut",CreativeTabRegister.CROP_TAB);
	public static final Item CABBAGE =new ItemBase("cabbage",CreativeTabRegister.CROP_TAB);
	public static final Item THORN = new ItemBase("thorn",CreativeTabRegister.MATERIAL_TAB);
	public static final Item KERNEL = new ItemBase("kernel",CreativeTabRegister.MATERIAL_TAB);
	public static final Item BUTTER = new ItemBase("butter",CreativeTabRegister.MATERIAL_TAB);
	public static final Item MELON = new ItemBase("melon",CreativeTabRegister.MATERIAL_TAB);
	public static final Item WINTER_MELON = new ItemBase("winter_melon",CreativeTabRegister.MATERIAL_TAB);
	public static final Item BALL = new ItemBase("ball",CreativeTabRegister.MATERIAL_TAB);
	
	//Cards
	public static final Item GRAY_CARD = new ItemBase("gray_card",CreativeTabRegister.MATERIAL_TAB);
	public static final Item WHITE_CARD = new ItemBase("white_card",CreativeTabRegister.MATERIAL_TAB);
	public static final Item GREEN_CARD = new ItemBase("green_card",CreativeTabRegister.MATERIAL_TAB);
	public static final Item BLUE_CARD = new ItemBase("blue_card",CreativeTabRegister.MATERIAL_TAB);
	public static final Item PURPLE_CARD = new ItemBase("purple_card",CreativeTabRegister.MATERIAL_TAB);
	public static final Item GOLD_CARD = new ItemBase("gold_card",CreativeTabRegister.MATERIAL_TAB);
	public static final Item RED_CARD = new ItemBase("red_card",CreativeTabRegister.MATERIAL_TAB);
	//1-1
	public static final Item PEA_SHOOTER_CARD = new ItemSummonCard("pea_shooter_card",Plants.PEA_SHOOTER);
	public static final Item SUN_FLOWER_CARD = new ItemSummonCard("sun_flower_card",Plants.SUN_FLOWER);
	public static final Item CHERRY_BOMB_CARD = new ItemSummonCard("cherry_bomb_card",Plants.CHERRY_BOMB);
	public static final Item NUT_WALL_CARD = new ItemSummonCard("nut_wall_card",Plants.NUT_WALL);
	public static final Item POTATO_MINE_CARD = new ItemSummonCard("potato_mine_card",Plants.POTATO_MINE);
	public static final Item SNOW_PEA_CARD = new ItemSummonCard("snow_pea_card",Plants.SNOW_PEA);
	public static final Item DOUBLE_SHOOTER_CARD = new ItemSummonCard("double_shooter_card",Plants.DOUBLE_SHOOTER);
	//2-1
	public static final Item HYPNO_SHROOM_CARD = new ItemSummonCard("hypno_shroom_card",Plants.HYPNO_SHROOM);
	public static final Item ICE_SHROOM_CARD = new ItemSummonCard("ice_shroom_card",Plants.ICE_SHROOM);
	//3-1
	public static final Item LILY_PAD_CARD = new ItemSummonCard("lily_pad_card", Plants.LILY_PAD);
	public static final Item SQUAHS_CARD = new ItemSummonCard("squash_card", Plants.SQUASH);
	public static final Item THREE_PEATER_CARD = new ItemSummonCard("three_peater_card",Plants.THREE_PEATER);
	public static final Item TANGLE_KELP_CARD = new ItemSummonCard("tangle_kelp_card", Plants.TANGLE_KELP);
	public static final Item JALAPENO_CARD = new ItemSummonCard("jalapeno_card",Plants.JALAPENO);
	public static final Item SPIKE_WEED_CARD = new ItemSummonCard("spike_weed_card", Plants.SPIKE_WEED);
	public static final Item TORCH_WOOD_CARD = new ItemSummonCard("torch_wood_card",Plants.TORCH_WOOD);
	public static final Item TALL_NUT_CARD = new ItemSummonCard("tall_nut_card",Plants.TALL_NUT);
	//4-1
	public static final Item SPLIT_PEA_CARD = new ItemSummonCard("split_pea_card",Plants.SPLIT_PEA);
	public static final Item PUMPKIN_CARD = new ItemSummonCard("pumpkin_card",Plants.PUMPKIN);
	//5-1
	public static final Item CABBAGE_PULT_CARD = new ItemSummonCard("cabbage_pult_card",Plants.CABBAGE_PULT);
	public static final Item FLOWER_POT_CARD = new ItemSummonCard("flower_pot_card",Plants.FLOWER_POT);
	public static final Item KERNEL_PULT_CARD = new ItemSummonCard("kernel_pult_card",Plants.KERNEL_PULT);
	public static final Item COFFEE_BEAN_CARD = new ItemSummonCard("coffee_bean_card",Plants.COFFEE_BEAN);
	public static final Item MARIGOLD_CARD = new ItemSummonCard("marigold_card",Plants.MARIGOLD);
	public static final Item MELON_PULT_CARD = new ItemSummonCard("melon_pult_card",Plants.MELON_PULT);
	//upgrade
	public static final Item GATLING_PEA_CARD = new ItemSummonCard("gatling_pea_card",Plants.GATLING_PEA);
	public static final Item TWIN_SUNFLOWER_CARD = new ItemSummonCard("twin_sunflower_card",Plants.TWIN_SUNFLOWER);
	public static final Item CAT_TAIL_CARD = new ItemSummonCard("cat_tail_card",Plants.CAT_TAIL);
	public static final Item WINTER_MELON_CARD = new ItemSummonCard("winter_melon_card",Plants.WINTER_MELON);
	public static final Item SPIKE_ROCK_CARD = new ItemSummonCard("spike_rock_card",Plants.SPIKE_ROCK);
	//else
	public static final Item ICEBERG_LETTUCE_CARD = new ItemSummonCard("iceberg_lettuce_card",Plants.ICEBERG_LETTUCE);
	public static final Item GOLD_LEAF_CARD = new ItemSummonCard("gold_leaf_card",Plants.GOLD_LEAF);
	public static final Item LIGHTNING_ROD_CARD = new ItemSummonCard("lightning_rod_card",Plants.LIGHTLING_ROD);
	public static final Item STRANGE_CAT_CARD = new ItemSummonCard("strange_cat_card",Plants.STRANGE_CAT);
	//self-make
	
	//foods
	public static final Item GLASS_CUP = new ItemGlassCup("glass_cup",CreativeTabRegister.MATERIAL_TAB);
	public static final Item APPLE_JUICE = new ItemJuiceBase("apple_juice",6,0.25f,false);
	public static final Item MELON_JUICE = new ItemJuiceBase("melon_juice",4,0.25f,false);
	
	//Tools
	public static final Item STEEL_SWORD = new ToolSword("steel_sword",STEEL_TOOL);
	public static final Item STEEL_PICKAXE = new ToolPickaxe("steel_pickaxe",STEEL_TOOL);
	public static final Item STEEL_AXE = new ToolAxe("steel_axe",STEEL_TOOL);
	public static final Item STEEL_HOE = new ToolHoe("steel_hoe",STEEL_TOOL);
	public static final Item STEEL_SHOVEL = new ToolShovel("steel_shovel",STEEL_TOOL);
	public static final Item DAVE_SHOVEL = new ItemDaveShovel("dave_shovel", ToolMaterial.STONE);
	public static final Item PEA_GUN = new ItemPeaGun("pea_gun");
	public static final Item CAR_KEY = new ItemBase("car_key",CreativeTabRegister.TOOL_TAB);
	
	//armors
	public static final Item STEEL_HELMET = new ArmorBase("steel_helmet",STEEL_ARMOR,1,EntityEquipmentSlot.HEAD);
	public static final Item STEEL_CHESTPLATE = new ArmorBase("steel_chestplate",STEEL_ARMOR,1,EntityEquipmentSlot.CHEST);
	public static final Item STEEL_LEGGINGS = new ArmorBase("steel_leggings",STEEL_ARMOR,2,EntityEquipmentSlot.LEGS);
	public static final Item STEEL_BOOTS = new ArmorBase("steel_boots",STEEL_ARMOR,1,EntityEquipmentSlot.FEET);
	
	public static final Item BARRIER_HELMET = new ArmorModel("barrier_helmet",CreativeTabRegister.ARMOR_TAB,BARRIER_ARMOR,EntityEquipmentSlot.HEAD);
	
//	public static final Item HHH = new ItemBase("test",CreativeTabRegister.MATERIAL_TAB);
}
