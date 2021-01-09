package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.item.PVZFoodItem;
import com.hungteen.pvz.item.PVZItemBase;
import com.hungteen.pvz.item.PVZItemTier;
import com.hungteen.pvz.item.PVZSpawnEggItem;
import com.hungteen.pvz.item.armor.BucketArmorItem;
import com.hungteen.pvz.item.armor.ConeArmorItem;
import com.hungteen.pvz.item.armor.FootballArmorItem;
import com.hungteen.pvz.item.armor.GigaArmorItem;
import com.hungteen.pvz.item.armor.PVZArmorMaterial;
import com.hungteen.pvz.item.misc.AlmanacItem;
import com.hungteen.pvz.item.misc.StrangeHelpItem;
import com.hungteen.pvz.item.tool.BobsleCarItem;
import com.hungteen.pvz.item.tool.PeaGunItem;
import com.hungteen.pvz.item.tool.ScreenDoorItem;
import com.hungteen.pvz.item.tool.SunCollectorItem;
import com.hungteen.pvz.item.tool.SunStorageSaplingItem;
import com.hungteen.pvz.item.tool.ZombieFlagItem;
import com.hungteen.pvz.item.tool.card.BlockPlantCardItem;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Plants;
import com.mojang.datafixers.util.Pair;

import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SoupItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegister {

    public static final DeferredRegister<Item> ITEMS =new DeferredRegister<>(ForgeRegistries.ITEMS, PVZMod.MOD_ID);
	
    //ingot essence
    public static final RegistryObject<Item> ORIGIN_ESSENCE = ITEMS.register("origin_essence", PVZItemBase::new);
    public static final RegistryObject<Item> APPEASE_ESSENCE = ITEMS.register("appease_essence", PVZItemBase::new);
    public static final RegistryObject<Item> LIGHT_ESSENCE = ITEMS.register("light_essence", PVZItemBase::new);
    public static final RegistryObject<Item> EXPLOSION_ESSENCE = ITEMS.register("explosion_essence", PVZItemBase::new);
    public static final RegistryObject<Item> DEFENCE_ESSENCE = ITEMS.register("defence_essence", PVZItemBase::new);
    public static final RegistryObject<Item> ICE_ESSENCE = ITEMS.register("ice_essence", PVZItemBase::new);
    public static final RegistryObject<Item> ENFORCE_ESSENCE = ITEMS.register("enforce_essence", PVZItemBase::new);
    public static final RegistryObject<Item> TOXIC_ESSENCE = ITEMS.register("toxic_essence", PVZItemBase::new);
    public static final RegistryObject<Item> ASSIST_ESSENCE = ITEMS.register("assist_essence", PVZItemBase::new);
    public static final RegistryObject<Item> MAGIC_ESSENCE = ITEMS.register("magic_essence", PVZItemBase::new);
    public static final RegistryObject<Item> FLAME_ESSENCE = ITEMS.register("flame_essence", PVZItemBase::new);
    public static final RegistryObject<Item> SPEAR_ESSENCE = ITEMS.register("spear_essence", PVZItemBase::new);
    public static final RegistryObject<Item> ARMA_ESSENCE = ITEMS.register("arma_essence", PVZItemBase::new);
    public static final RegistryObject<Item> ELECTRIC_ESSENCE = ITEMS.register("electric_essence", PVZItemBase::new);
    public static final RegistryObject<Item> SHADOW_ESSENCE = ITEMS.register("shadow_essence", PVZItemBase::new);
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", PVZItemBase::new);
    public static final RegistryObject<Item> AMETHYST_INGOT = ITEMS.register("amethyst_ingot", PVZItemBase::new);
    
    //card model
    public static final RegistryObject<Item> GRAY_CARD = ITEMS.register("gray_card", PVZItemBase::new);
    public static final RegistryObject<Item> WHITE_CARD = ITEMS.register("white_card", PVZItemBase::new);
    public static final RegistryObject<Item> GREEN_CARD = ITEMS.register("green_card", PVZItemBase::new);
    public static final RegistryObject<Item> BLUE_CARD = ITEMS.register("blue_card", PVZItemBase::new);
    public static final RegistryObject<Item> PURPLE_CARD = ITEMS.register("purple_card", PVZItemBase::new);
    public static final RegistryObject<Item> GOLD_CARD = ITEMS.register("gold_card", PVZItemBase::new);
    public static final RegistryObject<Item> RED_CARD = ITEMS.register("red_card", PVZItemBase::new);
    
    //plant card & card fragment
    public static final RegistryObject<PlantCardItem> PEA_SHOOTER_CARD = registerCard(Plants.PEA_SHOOTER, false);
    public static final RegistryObject<PlantCardItem> PEA_SHOOTER_ENJOY_CARD = registerCard(Plants.PEA_SHOOTER, true);
    public static final RegistryObject<PlantCardItem> SUN_FLOWER_CARD = registerCard(Plants.SUN_FLOWER, false);
    public static final RegistryObject<PlantCardItem> SUN_FLOWER_ENJOY_CARD = registerCard(Plants.SUN_FLOWER, true);
    public static final RegistryObject<PlantCardItem> CHERRY_BOMB_CARD = registerCard(Plants.CHERRY_BOMB, false);
    public static final RegistryObject<PlantCardItem> CHERRY_BOMB_ENJOY_CARD = registerCard(Plants.CHERRY_BOMB, true);
    public static final RegistryObject<PlantCardItem> WALL_NUT_CARD = registerCard(Plants.WALL_NUT, false);
    public static final RegistryObject<PlantCardItem> WALL_NUT_ENJOY_CARD = registerCard(Plants.WALL_NUT, true);
    public static final RegistryObject<PlantCardItem> POTATO_MINE_CARD = registerCard(Plants.POTATO_MINE, false);
    public static final RegistryObject<PlantCardItem> POTATO_MINE_ENJOY_CARD = registerCard(Plants.POTATO_MINE, true);
    public static final RegistryObject<PlantCardItem> SNOW_PEA_CARD = registerCard(Plants.SNOW_PEA, false);
    public static final RegistryObject<PlantCardItem> SNOW_PEA_ENJOY_CARD = registerCard(Plants.SNOW_PEA, true);
    public static final RegistryObject<PlantCardItem> CHOMPER_CARD = registerCard(Plants.CHOMPER, false);
    public static final RegistryObject<PlantCardItem> CHOMPER_ENJOY_CARD = registerCard(Plants.CHOMPER, true);
    public static final RegistryObject<PlantCardItem> REPEATER_CARD = registerCard(Plants.REPEATER, false);
    public static final RegistryObject<PlantCardItem> REPEATER_ENJOY_CARD = registerCard(Plants.REPEATER, true);
    public static final RegistryObject<PlantCardItem> PUFF_SHROOM_CARD = registerCard(Plants.PUFF_SHROOM, false);
    public static final RegistryObject<PlantCardItem> PUFF_SHROOM_ENJOY_CARD = registerCard(Plants.PUFF_SHROOM, true);
    public static final RegistryObject<PlantCardItem> SUN_SHROOM_CARD = registerCard(Plants.SUN_SHROOM, false);
    public static final RegistryObject<PlantCardItem> SUN_SHROOM_ENJOY_CARD = registerCard(Plants.SUN_SHROOM, true);
    public static final RegistryObject<PlantCardItem> FUME_SHROOM_CARD = registerCard(Plants.FUME_SHROOM, false);
    public static final RegistryObject<PlantCardItem> FUME_SHROOM_ENJOY_CARD = registerCard(Plants.FUME_SHROOM, true);
    public static final RegistryObject<PlantCardItem> GRAVE_BUSTER_CARD = registerCard(Plants.GRAVE_BUSTER, false);
    public static final RegistryObject<PlantCardItem> GRAVE_BUSTER_ENJOY_CARD = registerCard(Plants.GRAVE_BUSTER, true);
    public static final RegistryObject<PlantCardItem> HYPNO_SHROOM_CARD = registerCard(Plants.HYPNO_SHROOM, false);
    public static final RegistryObject<PlantCardItem> HYPNO_SHROOM_ENJOY_CARD = registerCard(Plants.HYPNO_SHROOM, true);
    public static final RegistryObject<PlantCardItem> SCAREDY_SHROOM_CARD = registerCard(Plants.SCAREDY_SHROOM, false);
    public static final RegistryObject<PlantCardItem> SCAREDY_SHROOM_ENJOY_CARD = registerCard(Plants.SCAREDY_SHROOM, true);
    public static final RegistryObject<PlantCardItem> ICE_SHROOM_CARD = registerCard(Plants.ICE_SHROOM, false);
    public static final RegistryObject<PlantCardItem> ICE_SHROOM_ENJOY_CARD = registerCard(Plants.ICE_SHROOM, true);
    public static final RegistryObject<PlantCardItem> DOOM_SHROOM_CARD = registerCard(Plants.DOOM_SHROOM, false);
    public static final RegistryObject<PlantCardItem> DOOM_SHROOM_ENJOY_CARD = registerCard(Plants.DOOM_SHROOM, true);
    public static final RegistryObject<PlantCardItem> LILY_PAD_CARD = ITEMS.register("lily_pad_card", ()->{return new BlockPlantCardItem(Plants.LILY_PAD,false);});
    public static final RegistryObject<PlantCardItem> LILY_PAD_ENJOY_CARD = ITEMS.register("lily_pad_enjoy_card", ()->{return new BlockPlantCardItem(Plants.LILY_PAD,true);});
    public static final RegistryObject<PlantCardItem> SQUASH_CARD = registerCard(Plants.SQUASH, false);
    public static final RegistryObject<PlantCardItem> SQUASH_ENJOY_CARD = registerCard(Plants.SQUASH, true);
    public static final RegistryObject<PlantCardItem> THREE_PEATER_CARD = registerCard(Plants.THREE_PEATER, false);
    public static final RegistryObject<PlantCardItem> THREE_PEATER_ENJOY_CARD = registerCard(Plants.THREE_PEATER, true);
    public static final RegistryObject<PlantCardItem> TANGLE_KELP_CARD = registerCard(Plants.TANGLE_KELP, false);
    public static final RegistryObject<PlantCardItem> TANGLE_KELP_ENJOY_CARD = registerCard(Plants.TANGLE_KELP, true);
    public static final RegistryObject<PlantCardItem> JALAPENO_CARD = registerCard(Plants.JALAPENO, false);
    public static final RegistryObject<PlantCardItem> JALAPENO_ENJOY_CARD = registerCard(Plants.JALAPENO, true);
    public static final RegistryObject<PlantCardItem> SPIKE_WEED_CARD = registerCard(Plants.SPIKE_WEED, false);
    public static final RegistryObject<PlantCardItem> SPIKE_WEED_ENJOY_CARD = registerCard(Plants.SPIKE_WEED, true);
    public static final RegistryObject<PlantCardItem> TORCH_WOOD_CARD = registerCard(Plants.TORCH_WOOD, false);
    public static final RegistryObject<PlantCardItem> TORCH_WOOD_ENJOY_CARD = registerCard(Plants.TORCH_WOOD, true);
    public static final RegistryObject<PlantCardItem> TALL_NUT_CARD = registerCard(Plants.TALL_NUT, false);
    public static final RegistryObject<PlantCardItem> TALL_NUT_ENJOY_CARD = registerCard(Plants.TALL_NUT, true);
    public static final RegistryObject<PlantCardItem> SEA_SHROOM_CARD = registerCard(Plants.SEA_SHROOM, false);
    public static final RegistryObject<PlantCardItem> SEA_SHROOM_ENJOY_CARD = registerCard(Plants.SEA_SHROOM, true);
    public static final RegistryObject<PlantCardItem> PLANTERN_CARD = registerCard(Plants.PLANTERN, false);
    public static final RegistryObject<PlantCardItem> PLANTERN_ENJOY_CARD = registerCard(Plants.PLANTERN, true);
    public static final RegistryObject<PlantCardItem> CACTUS_CARD = registerCard(Plants.CACTUS, false);
    public static final RegistryObject<PlantCardItem> CACTUS_ENJOY_CARD = registerCard(Plants.CACTUS, true);
    public static final RegistryObject<PlantCardItem> BLOVER_CARD = registerCard(Plants.BLOVER, false);
    public static final RegistryObject<PlantCardItem> BLOVER_ENJOY_CARD = registerCard(Plants.BLOVER, true);
    public static final RegistryObject<PlantCardItem> SPLIT_PEA_CARD = registerCard(Plants.SPLIT_PEA, false);
    public static final RegistryObject<PlantCardItem> SPLIT_PEA_ENJOY_CARD = registerCard(Plants.SPLIT_PEA, true);
    public static final RegistryObject<PlantCardItem> STAR_FRUIT_CARD = registerCard(Plants.STAR_FRUIT, false);
    public static final RegistryObject<PlantCardItem> STAR_FRUIT_ENJOY_CARD = registerCard(Plants.STAR_FRUIT, true);
    public static final RegistryObject<PlantCardItem> PUMPKIN_CARD = registerCard(Plants.PUMPKIN, false);
    public static final RegistryObject<PlantCardItem> PUMPKIN_ENJOY_CARD = registerCard(Plants.PUMPKIN, true);
    public static final RegistryObject<PlantCardItem> MAGNET_SHROOM_CARD = registerCard(Plants.MAGNET_SHROOM, false);
    public static final RegistryObject<PlantCardItem> MAGNET_SHROOM_ENJOY_CARD = registerCard(Plants.MAGNET_SHROOM, true);
    public static final RegistryObject<PlantCardItem> FLOWER_POT_CARD = ITEMS.register("flower_pot_card", () -> {return new BlockPlantCardItem(Plants.FLOWER_POT, false);});
    public static final RegistryObject<PlantCardItem> FLOWER_POT_ENJOY_CARD = ITEMS.register("flower_pot_enjoy_card", () -> {return new BlockPlantCardItem(Plants.FLOWER_POT, true);});
    public static final RegistryObject<PlantCardItem> COFFEE_BEAN_CARD = registerCard(Plants.COFFEE_BEAN, false);
    public static final RegistryObject<PlantCardItem> COFFEE_BEAN_ENJOY_CARD = registerCard(Plants.COFFEE_BEAN, true);
    public static final RegistryObject<PlantCardItem> MARIGOLD_CARD = registerCard(Plants.MARIGOLD, false);
    public static final RegistryObject<PlantCardItem> MARIGOLD_ENJOY_CARD = registerCard(Plants.MARIGOLD, true);
    public static final RegistryObject<PlantCardItem> GATLING_PEA_CARD = registerCard(Plants.GATLING_PEA, false);
    public static final RegistryObject<PlantCardItem> GATLING_PEA_ENJOY_CARD = registerCard(Plants.GATLING_PEA, true);
    public static final RegistryObject<PlantCardItem> TWIN_SUNFLOWER_CARD = registerCard(Plants.TWIN_SUNFLOWER, false);
    public static final RegistryObject<PlantCardItem> TWIN_SUNFLOWER_ENJOY_CARD = registerCard(Plants.TWIN_SUNFLOWER, true);
    public static final RegistryObject<PlantCardItem> GLOOM_SHROOM_CARD = registerCard(Plants.GLOOM_SHROOM, false);
    public static final RegistryObject<PlantCardItem> GLOOM_SHROOM_ENJOY_CARD = registerCard(Plants.GLOOM_SHROOM, true);
    public static final RegistryObject<PlantCardItem> CAT_TAIL_CARD = registerCard(Plants.CAT_TAIL, false);
    public static final RegistryObject<PlantCardItem> CAT_TAIL_ENJOY_CARD = registerCard(Plants.CAT_TAIL, true);
    public static final RegistryObject<PlantCardItem> WATER_GUARD_CARD = registerCard(Plants.WATER_GUARD, false);
    public static final RegistryObject<PlantCardItem> WATER_GUARD_ENJOY_CARD = registerCard(Plants.WATER_GUARD, true);
    public static final RegistryObject<PlantCardItem> STRANGE_CAT_CARD = registerCard(Plants.STRANGE_CAT, false);
    public static final RegistryObject<PlantCardItem> STRANGE_CAT_ENJOY_CARD = registerCard(Plants.STRANGE_CAT, true);
    public static final RegistryObject<PlantCardItem> ANGEL_STAR_FRUIT_CARD = registerCard(Plants.ANGEL_STAR_FRUIT, false);
    public static final RegistryObject<PlantCardItem> ANGEL_STAR_FRUIT_ENJOY_CARD = registerCard(Plants.ANGEL_STAR_FRUIT, true);
    
    //plants
	public static final RegistryObject<Item> PEA = ITEMS.register("pea",()-> new BlockItem(BlockRegister.PEA_PLANT.get(),new Item.Properties().group(GroupRegister.PVZ_MISC)));
	public static final RegistryObject<Item> NUT = ITEMS.register("nut", PVZItemBase::new);
	public static final RegistryObject<Item> SPORE = ITEMS.register("spore",()-> new BlockItem(BlockRegister.TOXIC_SHROOM.get(),new Item.Properties().group(GroupRegister.PVZ_MISC)));
	
	//material
	public static final RegistryObject<Item> SNOW_PEA = ITEMS.register("snow_pea", PVZItemBase::new);
	public static final RegistryObject<Item> FLAME_PEA = ITEMS.register("flame_pea", PVZItemBase::new);
	public static final RegistryObject<Item> BLUE_FLAME_PEA = ITEMS.register("blue_flame_pea", PVZItemBase::new);
	public static final RegistryObject<Item> SMALL_MEAT = ITEMS.register("small_meat", PVZItemBase::new);
	public static final RegistryObject<Item> PEPPER = ITEMS.register("pepper", PVZItemBase::new);
	
	//tool
	public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword", () -> new SwordItem(PVZItemTier.STEEL, 3, -2.4F, new Item.Properties().group(GroupRegister.PVZ_MISC)));
	public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel", () -> new ShovelItem(PVZItemTier.STEEL, 1.5F, -3.0F, new Item.Properties().group(GroupRegister.PVZ_MISC)));
	public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe", () -> new PickaxeItem(PVZItemTier.STEEL, 1, -2.8F, new Item.Properties().group(GroupRegister.PVZ_MISC)));
	public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe", () -> new AxeItem(PVZItemTier.STEEL, 6.0F, -3.1F, new Item.Properties().group(GroupRegister.PVZ_MISC)));
	public static final RegistryObject<Item> STEEL_HOE = ITEMS.register("steel_hoe", () -> new HoeItem(PVZItemTier.STEEL, -1.0F, new Item.Properties().group(GroupRegister.PVZ_MISC)));
	public static final RegistryObject<Item> ZOMBIE_FLAG = ITEMS.register("zombie_flag", ZombieFlagItem::new);
	public static final RegistryObject<Item> BOBSLE_CAR = ITEMS.register("bobsle_car", BobsleCarItem::new);
	public static final RegistryObject<Item> PEA_GUN = ITEMS.register("pea_gun", PeaGunItem::new);
	public static final RegistryObject<Item> SCREEN_DOOR = ITEMS.register("screen_door", ScreenDoorItem::new);
	public static final RegistryObject<Item> SUN_COLLECTOR = ITEMS.register("sun_collector", SunCollectorItem::new);
	public static final RegistryObject<Item> SUN_STORAGE_SAPLING = ITEMS.register("sun_storage_sapling", () -> new SunStorageSaplingItem(10000));
	
	//armor
	public static final RegistryObject<Item> CONE_HEAD = ITEMS.register("cone_head", ()->new ConeArmorItem(ArmorMaterial.LEATHER, EquipmentSlotType.HEAD));
	public static final RegistryObject<Item> BUCKET_HEAD = ITEMS.register("bucket_head", ()->new BucketArmorItem(ArmorMaterial.IRON, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> FOOTBALL_HELMET = ITEMS.register("football_helmet", ()->new FootballArmorItem(PVZArmorMaterial.FOOTBALL, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> FOOTBALL_CHESTPLATE = ITEMS.register("football_chestplate", ()->new FootballArmorItem(PVZArmorMaterial.FOOTBALL, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> FOOTBALL_LEGGINGS = ITEMS.register("football_leggings", ()->new FootballArmorItem(PVZArmorMaterial.FOOTBALL, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> FOOTBALL_BOOTS = ITEMS.register("football_boots", ()->new FootballArmorItem(PVZArmorMaterial.FOOTBALL, EquipmentSlotType.FEET));
    public static final RegistryObject<Item> GIGA_HELMET = ITEMS.register("giga_helmet", ()->new GigaArmorItem(PVZArmorMaterial.GIGA, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> GIGA_CHESTPLATE = ITEMS.register("giga_chestplate", ()->new GigaArmorItem(PVZArmorMaterial.GIGA, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> GIGA_LEGGINGS = ITEMS.register("giga_leggings", ()->new GigaArmorItem(PVZArmorMaterial.GIGA, EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> GIGA_BOOTS = ITEMS.register("giga_boots", ()->new GigaArmorItem(PVZArmorMaterial.GIGA, EquipmentSlotType.FEET));
    
    //misc
	public static final RegistryObject<Item> STRANGE_HELP = ITEMS.register("strange_help", StrangeHelpItem::new);
	public static final RegistryObject<Item> ALMANAC = ITEMS.register("almanac", AlmanacItem::new);
	public static final RegistryObject<Item> TIME_KEY_TO_DEEP = ITEMS.register("time_key_to_deep", PVZItemBase::new);
	public static final RegistryObject<Item> CAR_KEY = ITEMS.register("car_key", PVZItemBase::new);
	
	//food 
	public static final RegistryObject<Item> FAKE_BRAIN = ITEMS.register("fake_brain", ()->new PVZFoodItem(PVZFoodItem.FAKE_BRAIN));
	public static final RegistryObject<Item> REAL_BRAIN = ITEMS.register("real_brain", ()->new PVZFoodItem(PVZFoodItem.REAL_BRAIN));
	public static final RegistryObject<Item> CANDY = ITEMS.register("candy", ()->new PVZFoodItem(PVZFoodItem.CANDY));
	public static final RegistryObject<Item> CHOCOLATE = ITEMS.register("chocolate", ()->new PVZFoodItem(PVZFoodItem.CHOCOLATE));
	public static final RegistryObject<Item> PEA_SOUP = ITEMS.register("pea_soup", ()->new SoupItem(new Item.Properties().group(ItemGroup.FOOD).food(PVZFoodItem.PEA_SOUP)));
	public static final RegistryObject<Item> COOKED_BRAIN = ITEMS.register("cooked_brain", ()->new PVZFoodItem(PVZFoodItem.COOKED_BRAIN));
	
	//spawn egg
	//drop
	public static final RegistryObject<PVZSpawnEggItem> SUN_SPAWN_EGG = registerSpawnEgg("sun", EntityRegister.SUN, Colors.YELLOW, Colors.WHITE);
	public static final RegistryObject<PVZSpawnEggItem> COIN_SPAWN_EGG = registerSpawnEgg("coin", EntityRegister.COIN, Colors.ORANGE, Colors.GOLD);
	public static final RegistryObject<PVZSpawnEggItem> JEWEL_SPAWN_EGG = registerSpawnEgg("jewel", EntityRegister.JEWEL, Colors.LITTLE_AQUA, Colors.BLUE);
	public static final RegistryObject<PVZSpawnEggItem> ENERGY_SPAWN_EGG = registerSpawnEgg("energy", EntityRegister.ENERGY, Colors.GREEN, Colors.DARK_GREEN);
	
	//animal
	public static final RegistryObject<PVZSpawnEggItem> FOODIE_ZOMBIE_SPAWN_EGG = registerSpawnEgg("foodie_zombie", EntityRegister.FOODIE_ZOMBIE, Colors.ZOMBIE_SKIN, Colors.WHITE);
	
	//npc
	public static final RegistryObject<PVZSpawnEggItem> CRAZY_DAVE_SPAWN_EGG = registerSpawnEgg("crazy_dave", EntityRegister.CRAZY_DAVE, Colors.BROWN, Colors.SILVER);
	public static final RegistryObject<PVZSpawnEggItem> PANNEY_SPAWN_EGG = registerSpawnEgg("panney", EntityRegister.PANNEY, Colors.BLUE, Colors.RED);
	public static final RegistryObject<PVZSpawnEggItem> SUN_DAVE_SPAWN_EGG = registerSpawnEgg("sun_dave", EntityRegister.SUN_DAVE, Colors.BROWN, Colors.SILVER);
	
	//zombie
	public static final RegistryObject<PVZSpawnEggItem> NORMAL_ZOMBIE_SPAWN_EGG = registerSpawnEgg("normal_zombie", EntityRegister.NORMAL_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> FLAG_ZOMBIE_SPAWN_EGG = registerSpawnEgg("flag_zombie", EntityRegister.FLAG_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> CONEHEAD_ZOMBIE_SPAWN_EGG = registerSpawnEgg("conehead_zombie", EntityRegister.CONEHEAD_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> POLE_ZOMBIE_SPAWN_EGG = registerSpawnEgg("pole_zombie", EntityRegister.POLE_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> BUCKETHEAD_ZOMBIE_SPAWN_EGG = registerSpawnEgg("buckethead_zombie", EntityRegister.BUCKETHEAD_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> NEWSPAPER_ZOMBIE_SPAWN_EGG = registerSpawnEgg("newspaper_zombie", EntityRegister.NEWSPAPER_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> TOMB_STONE_SPAWN_EGG = registerSpawnEgg("tomb_stone", EntityRegister.TOMB_STONE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> SCREENDOOR_ZOMBIE_SPAWN_EGG = registerSpawnEgg("screendoor_zombie", EntityRegister.SCREENDOOR_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> FOOTBALL_ZOMBIE_SPAWN_EGG = registerSpawnEgg("football_zombie", EntityRegister.FOOTBALL_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> DANCING_ZOMBIE_SPAWN_EGG = registerSpawnEgg("dancing_zombie", EntityRegister.DANCING_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> BACKUP_DANCER_SPAWN_EGG = registerSpawnEgg("backup_dancer", EntityRegister.BACKUP_DANCER, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> GIGA_FOOTBALL_ZOMBIE_SPAWN_EGG = registerSpawnEgg("giga_football_zombie", EntityRegister.GIGA_FOOTBALL_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> OLD_ZOMBIE_SPAWN_EGG = registerSpawnEgg("old_zombie", EntityRegister.OLD_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> SUNDAY_EDITION_ZOMBIE_SPAWN_EGG = registerSpawnEgg("sunday_edition_zombie", EntityRegister.SUNDAY_EDITION_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> SNORKEL_ZOMBIE_SPAWN_EGG = registerSpawnEgg("snorkel_zombie", EntityRegister.SNORKEL_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> ZOMBONI_SPAWN_EGG = registerSpawnEgg("zomboni", EntityRegister.ZOMBONI, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> BOBSLE_TEAM_SPAWN_EGG = registerSpawnEgg("bobsle_team", EntityRegister.BOBSLE_TEAM, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> BOBSLE_ZOMBIE_SPAWN_EGG = registerSpawnEgg("bobsle_zombie", EntityRegister.BOBSLE_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> ZOMBIE_DOLPHIN_SPAWN_EGG = registerSpawnEgg("zombie_dolphin", EntityRegister.ZOMBIE_DOLPHIN, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> DOLPHIN_RIDER_SPAWN_EGG = registerSpawnEgg("dolphin_rider", EntityRegister.DOLPHIN_RIDER, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> DOLPHIN_RIDER_ZOMBIE_SPAWN_EGG = registerSpawnEgg("dolphin_rider_zombie", EntityRegister.DOLPHIN_RIDER_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> LAVA_ZOMBIE_SPAWN_EGG = registerSpawnEgg("lava_zombie", EntityRegister.LAVA_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> JACK_IN_BOX_ZOMBIE_SPAWN_EGG = registerSpawnEgg("jack_in_box_zombie", EntityRegister.JACK_IN_BOX_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> BALLOON_ZOMBIE_SPAWN_EGG = registerSpawnEgg("balloon_zombie", EntityRegister.BALLOON_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> DIGGER_ZOMBIE_SPAWN_EGG = registerSpawnEgg("digger_zombie", EntityRegister.DIGGER_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> POGO_ZOMBIE_SPAWN_EGG = registerSpawnEgg("pogo_zombie", EntityRegister.POGO_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> YETI_ZOMBIE_SPAWN_EGG = registerSpawnEgg("yeti_zombie", EntityRegister.YETI_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> PUMPKIN_ZOMBIE_SPAWN_EGG = registerSpawnEgg("pumpkin_zombie", EntityRegister.PUMPKIN_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> TRICK_ZOMBIE_SPAWN_EGG = registerSpawnEgg("trick_zombie", EntityRegister.TRICK_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> COFFIN_SPAWN_EGG = registerSpawnEgg("coffin", EntityRegister.COFFIN, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> MOURNER_ZOMBIE_SPAWN_EGG = registerSpawnEgg("mourner_zombie", EntityRegister.MOURNER_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> NOBLE_ZOMBIE_SPAWN_EGG = registerSpawnEgg("noble_zombie", EntityRegister.NOBLE_ZOMBIE, Colors.ZOMBIE_COLOR);
	
	//plant
	public static final RegistryObject<PVZSpawnEggItem> PEA_SHOOTER_SPAWN_EGG = registerSpawnEgg("pea_shooter", EntityRegister.PEA_SHOOTER, Colors.APPEASE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> SUN_FLOWER_SPAWN_EGG = registerSpawnEgg("sun_flower", EntityRegister.SUN_FLOWER, Colors.LIGHT_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> CHERRY_BOMB_SPAWN_EGG = registerSpawnEgg("cherry_bomb", EntityRegister.CHERRY_BOMB, Colors.EXPLOSION_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> WALL_NUT_SPAWN_EGG = registerSpawnEgg("wall_nut", EntityRegister.WALL_NUT, Colors.DEFENCE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> POTATO_MINE_SPAWN_EGG = registerSpawnEgg("potato_mine", EntityRegister.POTATO_MINE, Colors.EXPLOSION_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> SNOW_PEA_SPAWN_EGG = registerSpawnEgg("snow_pea", EntityRegister.SNOW_PEA, Colors.ICE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> CHOMPER_SPAWN_EGG = registerSpawnEgg("chomper", EntityRegister.CHOMPER, Colors.ENFORCE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> REPEATER_SPAWN_EGG = registerSpawnEgg("repeater", EntityRegister.REPEATER, Colors.APPEASE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> PUFF_SHROOM_SPAWN_EGG = registerSpawnEgg("puff_shroom", EntityRegister.PUFF_SHROOM, Colors.TOXIC_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> SUN_SHROOM_SPAWN_EGG = registerSpawnEgg("sun_shroom", EntityRegister.SUN_SHROOM, Colors.LIGHT_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> FUME_SHROOM_SPAWN_EGG = registerSpawnEgg("fume_shroom", EntityRegister.FUME_SHROOM, Colors.TOXIC_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> GRAVE_BUSTER_SPAWN_EGG = registerSpawnEgg("grave_buster", EntityRegister.GRAVE_BUSTER, Colors.ASSIST_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> HYPNO_SHROOM_SPAWN_EGG = registerSpawnEgg("hypno_shroom", EntityRegister.HYPNO_SHROOM, Colors.MAGIC_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> SCAREDY_SHROOM_SPAWN_EGG = registerSpawnEgg("scaredy_shroom", EntityRegister.SCAREDY_SHROOM, Colors.TOXIC_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> ICE_SHROOM_SPAWN_EGG = registerSpawnEgg("ice_shroom", EntityRegister.ICE_SHROOM, Colors.ICE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> DOOM_SHROOM_SPAWN_EGG = registerSpawnEgg("doom_shroom", EntityRegister.DOOM_SHROOM, Colors.EXPLOSION_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> SQUASH_SPAWN_EGG = registerSpawnEgg("squash", EntityRegister.SQUASH, Colors.ENFORCE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> THREE_PEATER_SPAWN_EGG = registerSpawnEgg("three_peater", EntityRegister.THREE_PEATER, Colors.APPEASE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> TANGLE_KELP_SPAWN_EGG = registerSpawnEgg("tangle_kelp", EntityRegister.TANGLE_KELP, Colors.ENFORCE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> JALAPENO_SPAWN_EGG = registerSpawnEgg("jalapeno", EntityRegister.JALAPENO, Colors.FLAME_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> SPIKE_WEED_SPAWN_EGG = registerSpawnEgg("spike_weed", EntityRegister.SPIKE_WEED, Colors.SPEAR_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> TORCH_WOOD_SPAWN_EGG = registerSpawnEgg("torch_wood", EntityRegister.TORCH_WOOD, Colors.FLAME_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> TALL_NUT_SPAWN_EGG = registerSpawnEgg("tall_nut", EntityRegister.TALL_NUT, Colors.DEFENCE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> SEA_SHROOM_SPAWN_EGG = registerSpawnEgg("sea_shroom", EntityRegister.SEA_SHROOM, Colors.TOXIC_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> PLANTERN_SPAWN_EGG = registerSpawnEgg("plantern", EntityRegister.PLANTERN, Colors.LIGHT_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> CACTUS_SPAWN_EGG = registerSpawnEgg("cactus", EntityRegister.CACTUS, Colors.SPEAR_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> BLOVER_SPAWN_EGG = registerSpawnEgg("blover", EntityRegister.BLOVER, Colors.ASSIST_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> SPLIT_PEA_SPAWN_EGG = registerSpawnEgg("split_pea", EntityRegister.SPLIT_PEA, Colors.APPEASE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> STAR_FRUIT_SPAWN_EGG = registerSpawnEgg("star_fruit", EntityRegister.STAR_FRUIT, Colors.APPEASE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> MAGNET_SHROOM_SPAWN_EGG = registerSpawnEgg("magnet_shroom", EntityRegister.MAGNET_SHROOM, Colors.ASSIST_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> COFFEE_BEAN_SPAWN_EGG = registerSpawnEgg("coffee_bean", EntityRegister.COFFEE_BEAN, Colors.MAGIC_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> MARIGOLD_SPAWN_EGG = registerSpawnEgg("marigold", EntityRegister.MARIGOLD, Colors.MAGIC_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> GATLING_PEA_SPAWN_EGG = registerSpawnEgg("gatling_pea", EntityRegister.GATLING_PEA, Colors.APPEASE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> TWIN_SUNFLOWER_SPAWN_EGG = registerSpawnEgg("twin_sunflower", EntityRegister.TWIN_SUNFLOWER, Colors.LIGHT_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> GLOOM_SHROOM_SPAWN_EGG = registerSpawnEgg("gloom_shroom", EntityRegister.GLOOM_SHROOM, Colors.TOXIC_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> CAT_TAIL_SPAWN_EGG = registerSpawnEgg("cat_tail", EntityRegister.CAT_TAIL, Colors.SPEAR_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> WATER_GUARD_SPAWN_EGG = registerSpawnEgg("water_guard", EntityRegister.WATER_GUARD, Colors.DEFENCE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> STRANGE_CAT_SPAWN_EGG = registerSpawnEgg("strange_cat", EntityRegister.STRANGE_CAT, Colors.MAGIC_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> ANGEL_STAR_FRUIT_SPAWN_EGG = registerSpawnEgg("angel_star_fruit", EntityRegister.ANGEL_STAR_FRUIT, Colors.APPEASE_COLOR);
	
	
	/**
	 * register spawn eggs
	 */
    private static RegistryObject<PVZSpawnEggItem> registerSpawnEgg(String name, RegistryObject<? extends EntityType<?>> entityType, int color1, int color2){
    	return ITEMS.register(name + "_spawn_egg", () -> new PVZSpawnEggItem(entityType, color1, color2, new Item.Properties().group(GroupRegister.PVZ_MISC)));
    }

    /**
	 * register spawn eggs
	 */
    private static RegistryObject<PVZSpawnEggItem> registerSpawnEgg(String name, RegistryObject<? extends EntityType<?>> entityType, Pair<Integer, Integer> color){
    	return ITEMS.register(name + "_spawn_egg", () -> new PVZSpawnEggItem(entityType, color.getFirst(), color.getSecond(), new Item.Properties().group(GroupRegister.PVZ_MISC)));
    }
    
    private static RegistryObject<PlantCardItem> registerCard(Plants plant, boolean is){
    	String name = plant.toString().toLowerCase();
    	if(is) name = name + "_enjoy";
    	name = name + "_card";
    	return ITEMS.register(name, () -> {return new PlantCardItem(plant, is);});
    }
}
