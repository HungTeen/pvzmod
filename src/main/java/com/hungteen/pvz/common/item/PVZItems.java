package com.hungteen.pvz.common.item;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.sound.PVZSounds;
import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.common.entity.PVZEntities;
import com.hungteen.pvz.common.impl.type.CardTypes;
import com.hungteen.pvz.common.impl.type.EssenceTypes;
import com.hungteen.pvz.common.impl.type.PAZTypes;
import com.hungteen.pvz.common.impl.type.plant.PlantType;
import com.hungteen.pvz.common.impl.type.zombie.ZombieType;
import com.hungteen.pvz.common.item.misc.EssenceItem;
import com.hungteen.pvz.common.item.misc.TemplateCardItem;
import com.hungteen.pvz.common.item.spawn.DropItemEgg;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.common.item.tool.OriginShovelItem;
import com.hungteen.pvz.common.item.tool.OriginSwordItem;
import com.hungteen.pvz.utils.enums.Colors;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.*;
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
    public static final RegistryObject<Item> PEA = ITEMS.register("pea",() -> new ItemNameBlockItem(PVZBlocks.PEA.get(), new Item.Properties().tab(PVZItemTabs.PVZ_MISC)));
    public static final RegistryObject<Item> NUT = ITEMS.register("nut", PVZMiscItem::new);
//    public static final RegistryObject<Item> SPORE = ITEMS.register("spore",()-> new BlockItem(BlockRegister.TOXIC_SHROOM.get(),new Item.Properties().tab(PVZItemGroups.PVZ_MISC)));
    public static final RegistryObject<Item> CABBAGE_SEEDS = ITEMS.register("cabbage_seeds", () -> new ItemNameBlockItem(PVZBlocks.CABBAGE.get(), new Item.Properties().tab(PVZItemTabs.PVZ_MISC)));
    public static final RegistryObject<Item> CORN_SEEDS = ITEMS.register("corn_seeds", () -> new ItemNameBlockItem(PVZBlocks.CORN.get(), new Item.Properties().tab(PVZItemTabs.PVZ_MISC)));

    /* materials */
    public static final RegistryObject<Item> SNOW_PEA = ITEMS.register("snow_pea", PVZMiscItem::new);
    public static final RegistryObject<Item> FLAME_PEA = ITEMS.register("flame_pea", PVZMiscItem::new);
    public static final RegistryObject<Item> SMALL_MEAT = ITEMS.register("small_meat", PVZMiscItem::new);
    public static final RegistryObject<Item> PEPPER = ITEMS.register("pepper", PVZMiscItem::new);
    public static final RegistryObject<Item> FROZEN_MELON_SLICE = ITEMS.register("frozen_melon_slice", PVZMiscItem::new);

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
    public static final RegistryObject<Item> SAPLING_CARD = ITEMS.register("sapling_card", () -> new TemplateCardItem(CardTypes.SAPLING));
    public static final RegistryObject<Item> NETHER_WART_CARD = ITEMS.register("nether_wart_card", () -> new TemplateCardItem(CardTypes.NETHER_WART));
    public static final RegistryObject<Item> CHORUS_FRUIT_CARD = ITEMS.register("chorus_fruit_card", () -> new TemplateCardItem(CardTypes.CHORUS_FRUIT));
    public static final RegistryObject<Item> UPGRADE_CARD = ITEMS.register("upgrade_card", () -> new TemplateCardItem(CardTypes.UPGRADE));
    public static final RegistryObject<Item> WISDOM_CARD = ITEMS.register("wisdom_card", () -> new TemplateCardItem(CardTypes.WISDOM));
    public static final RegistryObject<Item> MEGA_CARD = ITEMS.register("mega_card", PVZMiscItem::new);

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

    /* decoration */
    //TODO Custom Sign, fix Edit bug.
    public static final RegistryObject<Item> NUT_SIGN = ITEMS.register("nut_sign", () -> new SignItem((new Item.Properties()).stacksTo(16).tab(PVZItemTabs.PVZ_BLOCK), PVZBlocks.NUT_SIGN.get(), PVZBlocks.NUT_WALL_SIGN.get()));

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
     * Items in Transport Tab.
     */
//    public static final RegistryObject<Item> NUT_BOAT = ITEMS.register("nut_boat", () -> new BoatItem(new Item.Properties()).stacksTo(16).tab(PVZItemTabs.PVZ_BLOCK), PVZBlocks.NUT_SIGN.get(), PVZBlocks.NUT_WALL_SIGN.get()));

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
    public static final RegistryObject<Item> ORIGIN_SWORD = ITEMS.register("origin_sword", OriginSwordItem::new);
    public static final RegistryObject<Item> ORIGIN_SHOVEL = ITEMS.register("origin_shovel", OriginShovelItem::new);
    public static final RegistryObject<Item> ORIGIN_PICKAXE = ITEMS.register("origin_pickaxe", () -> new PickaxeItem(PVZTiers.ORIGIN, 1, -2.8F, new Item.Properties().tab(PVZItemTabs.PVZ_USEFUL)));
    public static final RegistryObject<Item> ORIGIN_AXE = ITEMS.register("origin_axe", () -> new AxeItem(PVZTiers.ORIGIN, 6.0F, -3.1F, new Item.Properties().tab(PVZItemTabs.PVZ_USEFUL)));
    public static final RegistryObject<Item> ORIGIN_HOE = ITEMS.register("origin_hoe", () -> new HoeItem(PVZTiers.ORIGIN, -2, -1.0F, new Item.Properties().tab(PVZItemTabs.PVZ_USEFUL)));
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
    public static final RegistryObject<Item> ZOMBIE_ON_YOUR_LAWN = ITEMS.register("zombie_on_your_lawn", () -> {return
            new RecordItem(0, () -> {
                return PVZSounds.ZOMBIE_ON_YOUR_LAWN.get();
            }, new Item.Properties().stacksTo(1).tab(PVZItemTabs.PVZ_USEFUL).rarity(Rarity.RARE));
    });

    public static final RegistryObject<Item> ZEN_GARDEN = ITEMS.register("zen_garden", () -> {return
            new RecordItem(0, () -> {
                return PVZSounds.ZEN_GARDEN.get();
            }, new Item.Properties().stacksTo(1).tab(PVZItemTabs.PVZ_USEFUL).rarity(Rarity.RARE));
    });

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
            //register plant summon cards.
            if(type instanceof PlantType){
                final Item summonCard = new PlantCardItem((IPlantType) type, false).setRegistryName(type.getIdentity() + "_card");
                //bind with type.
                ((PlantType) type).summonCard(() -> summonCard);
                //register item.
                ev.getRegistry().register(summonCard);
;           }
            //register zombie spawn eggs.
            if(type instanceof ZombieType){
                final Item spawnEgg = new ForgeSpawnEggItem(
                        () -> type.getEntityType().get(),
                        Colors.ZOMBIE_SKIN,
                        Colors.BLACK,
                        new Item.Properties().tab(PVZItemTabs.PVZ_MISC)
                ).setRegistryName(type.getIdentity() + "_spawn_egg");
                ev.getRegistry().register(spawnEgg);
            }
        });

        PVZAPI.get().getPAZTypes().forEach(type -> {
            //register plant enjoy cards.
            if(type instanceof PlantType){
                final Item enjoyCard = new PlantCardItem((IPlantType) type, true).setRegistryName(type.getIdentity() + "_enjoy_card");
                //bind with type.
                ((PlantType) type).enjoyCard(() -> enjoyCard);
                //register item.
                ev.getRegistry().register(enjoyCard);
             }
        });
    }

    /**
     * register spawn eggs
     */
    private static RegistryObject<ForgeSpawnEggItem> registerSpawnEgg(String name, RegistryObject<? extends EntityType<? extends Mob>> entityType, int color1, int color2){
        return ITEMS.register(name + "_spawn_egg", () -> new ForgeSpawnEggItem(entityType, color1, color2, new Item.Properties().tab(PVZItemTabs.PVZ_MISC)));
    }

//    /**
//     * register spawn eggs
//     */
//    private static RegistryObject<ForgeSpawnEggItem> registerSpawnEgg(String name, RegistryObject<? extends EntityType<? extends Mob>> entityType, Pair<Integer, Integer> color){
//        return ITEMS.register(name + "_spawn_egg", () -> new ForgeSpawnEggItem(entityType, color.getFirst(), color.getSecond(), new Item.Properties().tab(PVZItemTabs.PVZ_MISC)));
//    }

}
