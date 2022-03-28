package com.hungteen.pvz.common.item;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.PVZEntities;
import com.hungteen.pvz.common.impl.type.EssenceTypes;
import com.hungteen.pvz.common.impl.type.PAZTypes;
import com.hungteen.pvz.common.impl.type.RankTypes;
import com.hungteen.pvz.common.impl.type.plant.PlantType;
import com.hungteen.pvz.common.item.misc.EssenceItem;
import com.hungteen.pvz.common.item.misc.TemplateCardItem;
import com.hungteen.pvz.common.item.spawn.DropItemEgg;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.mojang.datafixers.util.Pair;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 22:33
 **/
public class PVZItems {

    /**
     * {@link PVZMod#deferredRegister(IEventBus)}
     */
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PVZMod.MOD_ID);

    /*
     * Items in Misc Tabs.
     */

    /* seeds & crops */
//    public static final RegistryObject<Item> PEA = ITEMS.register("pea",() -> new BlockItem(BlockRegister.PEA_PLANT.get(), new Item.Properties().tab(PVZItemGroups.PVZ_MISC)));
//    public static final RegistryObject<Item> NUT = ITEMS.register("nut", PVZMiscItem::new);
//    public static final RegistryObject<Item> SPORE = ITEMS.register("spore",()-> new BlockItem(BlockRegister.TOXIC_SHROOM.get(),new Item.Properties().tab(PVZItemGroups.PVZ_MISC)));
//    public static final RegistryObject<Item> CABBAGE_SEEDS = ITEMS.register("cabbage_seeds", () -> new BlockItem(BlockRegister.CABBAGE.get(), new Item.Properties().tab(PVZItemGroups.PVZ_MISC)));
//    public static final RegistryObject<Item> CORN_SEEDS = ITEMS.register("corn_seeds", () -> new BlockItem(BlockRegister.CORN.get(), new Item.Properties().tab(PVZItemGroups.PVZ_MISC)));

    /* materials */
//    public static final RegistryObject<Item> SNOW_PEA = ITEMS.register("snow_pea", PVZMiscItem::new);
//    public static final RegistryObject<Item> FLAME_PEA = ITEMS.register("flame_pea", PVZMiscItem::new);
//    public static final RegistryObject<Item> SMALL_MEAT = ITEMS.register("small_meat", PVZMiscItem::new);
//    public static final RegistryObject<Item> PEPPER = ITEMS.register("pepper", PVZMiscItem::new);
//    public static final RegistryObject<Item> FROZEN_MELON_SLICE = ITEMS.register("frozen_melon_slice", PVZMiscItem::new);

    /* essences */
    public static final RegistryObject<Item> ORIGIN_ESSENCE = ITEMS.register("origin_essence", () -> new EssenceItem(EssenceTypes.ORIGIN));
    public static final RegistryObject<Item> APPEASE_ESSENCE = ITEMS.register("appease_essence", () -> new EssenceItem(EssenceTypes.APPEASE));
    public static final RegistryObject<Item> LIGHT_ESSENCE = ITEMS.register("light_essence", () -> new EssenceItem(EssenceTypes.LIGHT));
    public static final RegistryObject<Item> EXPLOSION_ESSENCE = ITEMS.register("explosion_essence", () -> new EssenceItem(EssenceTypes.EXPLOSION));
    public static final RegistryObject<Item> DEFENCE_ESSENCE = ITEMS.register("defence_essence", () -> new EssenceItem(EssenceTypes.DEFENCE));
    public static final RegistryObject<Item> ICE_ESSENCE = ITEMS.register("ice_essence", () -> new EssenceItem(EssenceTypes.ICE));
    public static final RegistryObject<Item> ENFORCE_ESSENCE = ITEMS.register("enforce_essence", () -> new EssenceItem(EssenceTypes.ENFORCE));
//    public static final RegistryObject<Item> TOXIC_ESSENCE = ITEMS.register("toxic_essence", () -> new EssenceItem(EssenceTypes.TOXIC));
    public static final RegistryObject<Item> ASSIST_ESSENCE = ITEMS.register("assist_essence", () -> new EssenceItem(EssenceTypes.ASSIST));
    public static final RegistryObject<Item> MAGIC_ESSENCE = ITEMS.register("magic_essence", () -> new EssenceItem(EssenceTypes.MAGIC));
    public static final RegistryObject<Item> FLAME_ESSENCE = ITEMS.register("flame_essence", () -> new EssenceItem(EssenceTypes.FLAME));
    public static final RegistryObject<Item> SPEAR_ESSENCE = ITEMS.register("spear_essence", () -> new EssenceItem(EssenceTypes.SPEAR));
    public static final RegistryObject<Item> ARMA_ESSENCE = ITEMS.register("arma_essence", () -> new EssenceItem(EssenceTypes.ARMA));
//    public static final RegistryObject<Item> ELECTRIC_ESSENCE = ITEMS.register("electric_essence", () -> new EssenceItem(EssenceTypes.ELECTRIC));
//    public static final RegistryObject<Item> SHADOW_ESSENCE = ITEMS.register("shadow_essence", () -> new EssenceItem(EssenceTypes.SHADOW));

    /* ingots */
    public static final RegistryObject<Item> ORIGIN_INGOT = ITEMS.register("origin_ingot", PVZMiscItem::new);
    public static final RegistryObject<Item> AMETHYST_INGOT = ITEMS.register("amethyst_ingot", PVZMiscItem::new);

    /* card models */
    public static final RegistryObject<Item> GRAY_CARD = ITEMS.register("gray_card", () -> new TemplateCardItem(RankTypes.GRAY));
    public static final RegistryObject<Item> WHITE_CARD = ITEMS.register("white_card", () -> new TemplateCardItem(RankTypes.WHITE));
    public static final RegistryObject<Item> GREEN_CARD = ITEMS.register("green_card", () -> new TemplateCardItem(RankTypes.GREEN));
    public static final RegistryObject<Item> BLUE_CARD = ITEMS.register("blue_card", () -> new TemplateCardItem(RankTypes.BLUE));
    public static final RegistryObject<Item> PURPLE_CARD = ITEMS.register("purple_card", () -> new TemplateCardItem(RankTypes.PURPLE));
    public static final RegistryObject<Item> GOLD_CARD = ITEMS.register("gold_card", () -> new TemplateCardItem(RankTypes.GOLD));
    public static final RegistryObject<Item> RED_CARD = ITEMS.register("red_card", () -> new TemplateCardItem(RankTypes.RED));
    public static final RegistryObject<Item> BLACK_CARD = ITEMS.register("black_card", () -> new TemplateCardItem(RankTypes.BLACK));
    public static final RegistryObject<Item> MEGA_CARD = ITEMS.register("mega_card", () -> new TemplateCardItem(RankTypes.MEGA));

    /* spawn eggs */
//    public static final RegistryObject<DropItemEgg> SUN = registerSpawnEgg("sun", PVZEntities.SUN, Colors.YELLOW, Colors.WHITE);
    public static final RegistryObject<DropItemEgg> COPPER_COIN = ITEMS.register("copper_coin", () -> new DropItemEgg(() -> PVZEntities.COPPER_COIN.get()));
    public static final RegistryObject<DropItemEgg> SILVER_COIN = ITEMS.register("silver_coin", () -> new DropItemEgg(() -> PVZEntities.SILVER_COIN.get()));
    public static final RegistryObject<DropItemEgg> GOLD_COIN = ITEMS.register("gold_coin", () -> new DropItemEgg(() -> PVZEntities.GOLD_COIN.get()));
    public static final RegistryObject<DropItemEgg> JEWEL = ITEMS.register("jewel", () -> new DropItemEgg(() -> PVZEntities.JEWEL.get()));
    //    public static final RegistryObject<ForgeSpawnEggItem> JEWEL_SPAWN_EGG = registerSpawnEgg("jewel", PVZEntities.JEWEL, Colors.WHITE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> ENERGY_SPAWN_EGG = registerSpawnEgg("energy", EntityRegister.ENERGY, Colors.GREEN, Colors.DARK_GREEN);
//    public static final RegistryObject<PVZSpawnEggItem> FOODIE_ZOMBIE_SPAWN_EGG = registerSpawnEgg("foodie_zombie", EntityRegister.FOODIE_ZOMBIE, Colors.ZOMBIE_SKIN, Colors.WHITE);
//    public static final RegistryObject<PVZSpawnEggItem> CRAZY_DAVE_SPAWN_EGG = registerSpawnEgg("crazy_dave", EntityRegister.CRAZY_DAVE, Colors.BROWN, Colors.SILVER);
//    public static final RegistryObject<PVZSpawnEggItem> PANNEY_SPAWN_EGG = registerSpawnEgg("panney", EntityRegister.PANNEY, Colors.BLUE, Colors.RED);
//    public static final RegistryObject<PVZSpawnEggItem> SUN_DAVE_SPAWN_EGG = registerSpawnEgg("sun_dave", EntityRegister.SUN_DAVE, Colors.BROWN, Colors.SILVER);
//    public static final RegistryObject<PVZSpawnEggItem> NORMAL_ZOMBIE_SPAWN_EGG = registerSpawnEgg("normal_zombie", EntityRegister.NORMAL_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> FLAG_ZOMBIE_SPAWN_EGG = registerSpawnEgg("flag_zombie", EntityRegister.FLAG_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> CONEHEAD_ZOMBIE_SPAWN_EGG = registerSpawnEgg("conehead_zombie", EntityRegister.CONEHEAD_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> POLE_ZOMBIE_SPAWN_EGG = registerSpawnEgg("pole_zombie", EntityRegister.POLE_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> BUCKETHEAD_ZOMBIE_SPAWN_EGG = registerSpawnEgg("buckethead_zombie", EntityRegister.BUCKETHEAD_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> NEWSPAPER_ZOMBIE_SPAWN_EGG = registerSpawnEgg("newspaper_zombie", EntityRegister.NEWSPAPER_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> TOMB_STONE_SPAWN_EGG = registerSpawnEgg("tomb_stone", EntityRegister.TOMB_STONE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> SCREENDOOR_ZOMBIE_SPAWN_EGG = registerSpawnEgg("screendoor_zombie", EntityRegister.SCREENDOOR_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> FOOTBALL_ZOMBIE_SPAWN_EGG = registerSpawnEgg("football_zombie", EntityRegister.FOOTBALL_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> DANCING_ZOMBIE_SPAWN_EGG = registerSpawnEgg("dancing_zombie", EntityRegister.DANCING_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> BACKUP_DANCER_SPAWN_EGG = registerSpawnEgg("backup_dancer", EntityRegister.BACKUP_DANCER, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> GIGA_FOOTBALL_ZOMBIE_SPAWN_EGG = registerSpawnEgg("giga_football_zombie", EntityRegister.GIGA_FOOTBALL_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> OLD_ZOMBIE_SPAWN_EGG = registerSpawnEgg("old_zombie", EntityRegister.OLD_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> SUNDAY_EDITION_ZOMBIE_SPAWN_EGG = registerSpawnEgg("sunday_edition_zombie", EntityRegister.SUNDAY_EDITION_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> SNORKEL_ZOMBIE_SPAWN_EGG = registerSpawnEgg("snorkel_zombie", EntityRegister.SNORKEL_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> ZOMBONI_SPAWN_EGG = registerSpawnEgg("zomboni", EntityRegister.ZOMBONI, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> BOBSLE_TEAM_SPAWN_EGG = registerSpawnEgg("bobsle_team", EntityRegister.BOBSLE_TEAM, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> BOBSLE_ZOMBIE_SPAWN_EGG = registerSpawnEgg("bobsle_zombie", EntityRegister.BOBSLE_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> ZOMBIE_DOLPHIN_SPAWN_EGG = registerSpawnEgg("zombie_dolphin", EntityRegister.ZOMBIE_DOLPHIN, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> DOLPHIN_RIDER_SPAWN_EGG = registerSpawnEgg("dolphin_rider", EntityRegister.DOLPHIN_RIDER, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> DOLPHIN_RIDER_ZOMBIE_SPAWN_EGG = registerSpawnEgg("dolphin_rider_zombie", EntityRegister.DOLPHIN_RIDER_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> LAVA_ZOMBIE_SPAWN_EGG = registerSpawnEgg("lava_zombie", EntityRegister.LAVA_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> JACK_IN_BOX_ZOMBIE_SPAWN_EGG = registerSpawnEgg("jack_in_box_zombie", EntityRegister.JACK_IN_BOX_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> BALLOON_ZOMBIE_SPAWN_EGG = registerSpawnEgg("balloon_zombie", EntityRegister.BALLOON_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> DIGGER_ZOMBIE_SPAWN_EGG = registerSpawnEgg("digger_zombie", EntityRegister.DIGGER_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> POGO_ZOMBIE_SPAWN_EGG = registerSpawnEgg("pogo_zombie", EntityRegister.POGO_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> YETI_ZOMBIE_SPAWN_EGG = registerSpawnEgg("yeti_zombie", EntityRegister.YETI_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> BUNGEE_ZOMBIE_SPAWN_EGG = registerSpawnEgg("bungee_zombie", EntityRegister.BUNGEE_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> LADDER_ZOMBIE_SPAWN_EGG = registerSpawnEgg("ladder_zombie", EntityRegister.LADDER_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> CATAPULT_ZOMBIE_SPAWN_EGG = registerSpawnEgg("catapult_zombie", EntityRegister.CATAPULT_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> GARGANTUAR_SPAWN_EGG = registerSpawnEgg("gargantuar", EntityRegister.GARGANTUAR, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> IMP_SPAWN_EGG = registerSpawnEgg("imp", EntityRegister.IMP, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> GIGA_GARGANTUAR_SPAWN_EGG = registerSpawnEgg("giga_gargantuar", EntityRegister.GIGA_GARGANTUAR, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> EDGAR_090505_SPAWN_EGG = registerSpawnEgg("edgar_090505", EntityRegister.EDGAR_090505, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> EDGAR_090517_SPAWN_EGG = registerSpawnEgg("edgar_090517", EntityRegister.EDGAR_090517, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> PEASHOOTER_ZOMBIE_SPAWN_EGG = registerSpawnEgg("peashooter_zombie", EntityRegister.PEASHOOTER_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> WALLNUT_ZOMBIE_SPAWN_EGG = registerSpawnEgg("wallnut_zombie", EntityRegister.WALLNUT_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> GATLINGPEA_ZOMBIE_SPAWN_EGG = registerSpawnEgg("gatlingpea_zombie", EntityRegister.GATLINGPEA_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> TALLNUT_ZOMBIE_SPAWN_EGG = registerSpawnEgg("tallnut_zombie", EntityRegister.TALLNUT_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> SQUASH_ZOMBIE_SPAWN_EGG = registerSpawnEgg("squash_zombie", EntityRegister.SQUASH_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> JALAPENO_ZOMBIE_SPAWN_EGG = registerSpawnEgg("jalapeno_zombie", EntityRegister.JALAPENO_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> PUMPKIN_ZOMBIE_SPAWN_EGG = registerSpawnEgg("pumpkin_zombie", EntityRegister.PUMPKIN_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> TRICK_ZOMBIE_SPAWN_EGG = registerSpawnEgg("trick_zombie", EntityRegister.TRICK_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> COFFIN_SPAWN_EGG = registerSpawnEgg("coffin", EntityRegister.COFFIN, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> MOURNER_ZOMBIE_SPAWN_EGG = registerSpawnEgg("mourner_zombie", EntityRegister.MOURNER_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> NOBLE_ZOMBIE_SPAWN_EGG = registerSpawnEgg("noble_zombie", EntityRegister.NOBLE_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> RA_ZOMBIE_SPAWN_EGG = registerSpawnEgg("ra_zombie", EntityRegister.RA_ZOMBIE, Colors.ZOMBIE_COLOR);
//    public static final RegistryObject<PVZSpawnEggItem> GIGA_TOMB_STONE_SPAWN_EGG = registerSpawnEgg("giga_tomb_stone", EntityRegister.GIGA_TOMB_STONE, Colors.ZOMBIE_COLOR);

    /* misc */
//    public static final RegistryObject<Item> TIME_SOURCE = ITEMS.register("time_source", PVZMiscItem::new);

    /*
     * Items in Food Tab.
     */

//    public static final RegistryObject<Item> FAKE_BRAIN = ITEMS.register("fake_brain", ()->new PVZFoodItem(PVZFoodItem.FAKE_BRAIN));
//    public static final RegistryObject<Item> REAL_BRAIN = ITEMS.register("real_brain", ()->new PVZFoodItem(PVZFoodItem.REAL_BRAIN));
//    public static final RegistryObject<Item> CANDY = ITEMS.register("candy", ()->new PVZFoodItem(PVZFoodItem.CANDY));
//    public static final RegistryObject<Item> CHOCOLATE = ITEMS.register("chocolate", ()->new PVZFoodItem(PVZFoodItem.CHOCOLATE));
//    public static final RegistryObject<Item> PEA_SOUP = ITEMS.register("pea_soup", ()->new SoupItem(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(PVZFoodItem.PEA_SOUP).stacksTo(1)));
//    public static final RegistryObject<Item> COOKED_BRAIN = ITEMS.register("cooked_brain", ()->new PVZFoodItem(PVZFoodItem.COOKED_BRAIN));
//    public static final RegistryObject<Item> CABBAGE = ITEMS.register("cabbage", ()->new PVZFoodItem(PVZFoodItem.CABBAGE));
//    public static final RegistryObject<Item> CORN = ITEMS.register("corn", ()->new PVZFoodItem(PVZFoodItem.CORN));
//    public static final RegistryObject<Item> POP_CORN = ITEMS.register("pop_corn", ()->new PVZFoodItem(PVZFoodItem.POP_CORN));
//    public static final RegistryObject<Item> TACOS = ITEMS.register("tacos", ()->new PVZFoodItem(PVZFoodItem.TACOS));

    /*
     * Items in Tools Tab.
     */

    /* tools */
//    public static final RegistryObject<Item> ZOMBIE_FLAG = ITEMS.register("zombie_flag", ZombieFlagItem::new);
//    public static final RegistryObject<Item> SCREEN_DOOR = ITEMS.register("screen_door", ScreenDoorItem::new);
//    public static final RegistryObject<Item> BOBSLE_CAR = ITEMS.register("bobsle_car", BobsleCarItem::new);
//    public static final RegistryObject<Item> JACK_BOX = ITEMS.register("jack_box", JackBoxItem::new);
//    public static final RegistryObject<Item> BALLOON = ITEMS.register("balloon", BalloonItem::new);
//    public static final RegistryObject<Item> TARGET_ARROW = ITEMS.register("target_arrow", TargetArrowItem::new);
//    public static final RegistryObject<Item> WARNING_SIGN = ITEMS.register("warning_sign", () -> new SwordItem(ItemTier.IRON, 6, - 2.4F, new Item.Properties().tab(PVZItemGroups.PVZ_USEFUL).stacksTo(1)));
//    public static final RegistryObject<Item> POLE = ITEMS.register("pole", () -> new AxeItem(ItemTier.IRON, 8, - 3F, new Item.Properties().tab(PVZItemGroups.PVZ_USEFUL).stacksTo(1)));
//    public static final RegistryObject<Item> ORIGIN_SWORD = ITEMS.register("origin_sword", OriginSwordItem::new);
//    public static final RegistryObject<Item> ORIGIN_SHOVEL = ITEMS.register("origin_shovel", OriginShovelItem::new);
//    public static final RegistryObject<Item> ORIGIN_PICKAXE = ITEMS.register("origin_pickaxe", () -> new PickaxeItem(PVZItemTier.ORIGIN, 1, -2.8F, new Item.Properties().tab(PVZItemGroups.PVZ_USEFUL)));
//    public static final RegistryObject<Item> ORIGIN_AXE = ITEMS.register("origin_axe", () -> new AxeItem(PVZItemTier.ORIGIN, 6.0F, -3.1F, new Item.Properties().tab(PVZItemGroups.PVZ_USEFUL)));
//    public static final RegistryObject<Item> ORIGIN_HOE = ITEMS.register("origin_hoe", () -> new HoeItem(PVZItemTier.ORIGIN, -2, -1.0F, new Item.Properties().tab(PVZItemGroups.PVZ_USEFUL)));
//    public static final RegistryObject<Item> PEA_GUN = ITEMS.register("pea_gun", PeaGunItem::new);
//    public static final RegistryObject<Item> RESOURCE_COLLECTOR = ITEMS.register("resource_collector", ResourceCollectorItem::new);
//    public static final RegistryObject<Item> BOWLING_GLOVE = ITEMS.register("bowling_glove", BowlingGloveItem::new);
//    public static final RegistryObject<Item> SUN_STORAGE_SAPLING = ITEMS.register("sun_storage_sapling", () -> new SunStorageSaplingItem(10000));
//    public static final RegistryObject<Item> SMALL_SUN_STORAGE_SAPLING = ITEMS.register("sun_storage_sapling2", () -> new SunStorageSaplingItem(1000));
//    public static final RegistryObject<Item> LARGE_SUN_STORAGE_SAPLING = ITEMS.register("sun_storage_sapling3", () -> new SunStorageSaplingItem(100000));
//    public static final RegistryObject<Item> ONCE_SUN_STORAGE_SAPLING = ITEMS.register("sun_storage_sapling4", SunStorageSaplingItem::new);
//    public static final RegistryObject<Item> FIRE_CRACKER = ITEMS.register("fire_cracker", FireCrackerItem::new);
//    public static final RegistryObject<Item> ALMANAC = ITEMS.register("almanac", AlmanacItem::new);
//    public static final RegistryObject<Item> CARD_PACK = ITEMS.register("card_pack", CardPackItem::new);
//    public static final RegistryObject<Item> CAR_KEY = ITEMS.register("car_key", PVZToolItem::new);
//    public static final RegistryObject<Item> LAWN_MOWER = ITEMS.register("lawn_mower", LawnMowerItem::new);
//    public static final RegistryObject<Item> GARDEN_RAKE = ITEMS.register("garden_rake", GardenRakeItem::new);
//    public static final RegistryObject<Item> ZOMBIE_DOLL = ITEMS.register("zombie_doll", ZombieDollItem::new);
//    public static final RegistryObject<Item> EDGAR_DOLL = ITEMS.register("edgar_doll", EdgarDollItem::new);
//    public static final RegistryObject<Item> TIME_KEY_TO_DEEP = ITEMS.register("time_key_to_deep", TimeKeyItem::new);

    /* armor */
//    public static final RegistryObject<Item> CONE_HEAD = ITEMS.register("cone_head", () -> new ConeArmorItem(ArmorMaterial.LEATHER, EquipmentSlotType.HEAD));
//    public static final RegistryObject<Item> BUCKET_HEAD = ITEMS.register("bucket_head", () -> new BucketArmorItem(ArmorMaterial.IRON, EquipmentSlotType.HEAD));
//    public static final RegistryObject<Item> FOOTBALL_HELMET = ITEMS.register("football_helmet", () -> new FootballArmorItem(PVZArmorMaterial.FOOTBALL, EquipmentSlotType.HEAD));
//    public static final RegistryObject<Item> FOOTBALL_CHESTPLATE = ITEMS.register("football_chestplate", () -> new FootballArmorItem(PVZArmorMaterial.FOOTBALL, EquipmentSlotType.CHEST));
//    public static final RegistryObject<Item> FOOTBALL_LEGGINGS = ITEMS.register("football_leggings", () -> new FootballArmorItem(PVZArmorMaterial.FOOTBALL, EquipmentSlotType.LEGS));
//    public static final RegistryObject<Item> FOOTBALL_BOOTS = ITEMS.register("football_boots", () -> new FootballArmorItem(PVZArmorMaterial.FOOTBALL, EquipmentSlotType.FEET));
//    public static final RegistryObject<Item> GIGA_HELMET = ITEMS.register("giga_helmet", () -> new GigaArmorItem(PVZArmorMaterial.GIGA, EquipmentSlotType.HEAD));
//    public static final RegistryObject<Item> GIGA_CHESTPLATE = ITEMS.register("giga_chestplate", () -> new GigaArmorItem(PVZArmorMaterial.GIGA, EquipmentSlotType.CHEST));
//    public static final RegistryObject<Item> GIGA_LEGGINGS = ITEMS.register("giga_leggings", () -> new GigaArmorItem(PVZArmorMaterial.GIGA, EquipmentSlotType.LEGS));
//    public static final RegistryObject<Item> GIGA_BOOTS = ITEMS.register("giga_boots", () -> new GigaArmorItem(PVZArmorMaterial.GIGA, EquipmentSlotType.FEET));

    /* music disc */
//    public static final RegistryObject<Item> ZOMBIE_ON_YOUR_LAWN = ITEMS.register("zombie_on_your_lawn", () -> {return
//            new MusicDiscItem(0, () -> {
//                return SoundRegister.ZOMBIE_ON_YOUR_LAWN.get();
//            }, new Item.Properties().stacksTo(1).tab(PVZItemGroups.PVZ_USEFUL).rarity(Rarity.RARE));
//    });
//
//    public static final RegistryObject<Item> ZEN_GARDEN = ITEMS.register("zen_garden", () -> {return
//            new MusicDiscItem(0, () -> {
//                return SoundRegister.ZEN_GARDEN.get();
//            }, new Item.Properties().stacksTo(1).tab(PVZItemGroups.PVZ_USEFUL).rarity(Rarity.RARE));
//    });

    /*
    Challenge Tab.
     */

//    public static final RegistryObject<Item> CHALLENGE_ENVELOPE = ITEMS.register("challenge_envelope", ChallengeEnvelopeItem::new);

    /*
    Default MC Tabs.
     */
    public static final RegistryObject<Item> GRASS_CARP_BUCKET = ITEMS.register("grass_carp_bucket", () -> {
        return new MobBucketItem(() -> PVZEntities.GRASS_CARP.get(), () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_AXOLOTL, new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC));
    });

    /**
     * register card items.
     * {@link PVZMod#PVZMod()}
     */
    public static void registerCards(RegistryEvent.Register<Item> ev){
        PAZTypes.initPAZs();

        PVZAPI.get().getPAZTypes().forEach(type -> {
            if(type instanceof PlantType){
                final Item summonCard = new PlantCardItem((IPlantType) type, false).setRegistryName(type.getIdentity() + "_card");
                final Item enjoyCard = new PlantCardItem((IPlantType) type, true).setRegistryName(type.getIdentity() + "enjoy_card");
                //bind with type.
                ((PlantType) type).summonCard(() -> summonCard);
                ((PlantType) type).enjoyCard(() -> enjoyCard);
                //register item.
                ev.getRegistry().register(summonCard);
                ev.getRegistry().register(enjoyCard);
;           }
        });
    }

    /**
     * register spawn eggs
     */
    private static RegistryObject<ForgeSpawnEggItem> registerSpawnEgg(String name, RegistryObject<? extends EntityType<? extends Mob>> entityType, int color1, int color2){
        return ITEMS.register(name + "_spawn_egg", () -> new ForgeSpawnEggItem(entityType, color1, color2, new Item.Properties().tab(PVZItemTabs.PVZ_MISC)));
    }

    /**
     * register spawn eggs
     */
    private static RegistryObject<ForgeSpawnEggItem> registerSpawnEgg(String name, RegistryObject<? extends EntityType<? extends Mob>> entityType, Pair<Integer, Integer> color){
        return ITEMS.register(name + "_spawn_egg", () -> new ForgeSpawnEggItem(entityType, color.getFirst(), color.getSecond(), new Item.Properties().tab(PVZItemTabs.PVZ_MISC)));
    }

//    private static RegistryObject<PlantCardItem> registerCard(IPlantType plant, boolean is){
//        String name = plant.toString();
//        if(is) {
//            name = name + "_enjoy";
//        }
//        name = name + "_card";
//        return ITEMS.register(name, () -> new PlantCardItem(plant, is));
//    }
}
