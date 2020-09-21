package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.item.PVZItemBase;
import com.hungteen.pvz.item.PVZSpawnEggItem;
import com.hungteen.pvz.item.armor.BucketArmorItem;
import com.hungteen.pvz.item.armor.ConeArmorItem;
import com.hungteen.pvz.item.misc.GuildBookItem;
import com.hungteen.pvz.item.misc.StrangeHelpItem;
import com.hungteen.pvz.item.tool.BobsleCarItem;
import com.hungteen.pvz.item.tool.ZombieFlagItem;
import com.hungteen.pvz.item.tool.card.BlockPlantCardItem;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Plants;
import com.mojang.datafixers.util.Pair;

import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
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
//    public static final RegistryObject<PlantCardItem> WATER_GUARD_CARD = registerCard(Plants.WATER_GUARD, false);
//    public static final RegistryObject<PlantCardItem> WATER_GUARD_ENJOY_CARD = registerCard(Plants.WATER_GUARD, true);

    //plants
	public static final RegistryObject<Item> PEA = ITEMS.register("pea",()-> new BlockItem(BlockRegister.PEA_PLANT.get(),new Item.Properties().group(GroupRegister.PVZ_MISC)));
	public static final RegistryObject<Item> NUT = ITEMS.register("nut", PVZItemBase::new);
	
	//material
	public static final RegistryObject<Item> SNOW_PEA = ITEMS.register("snow_pea", PVZItemBase::new);
	public static final RegistryObject<Item> FLAME_PEA = ITEMS.register("flame_pea", PVZItemBase::new);
	public static final RegistryObject<Item> BLUE_FLAME_PEA = ITEMS.register("blue_flame_pea", PVZItemBase::new);
	public static final RegistryObject<Item> SMALL_MEAT = ITEMS.register("small_meat", PVZItemBase::new);
	public static final RegistryObject<Item> FAKE_BRAIN = ITEMS.register("fake_brain", PVZItemBase::new);
	public static final RegistryObject<Item> REAL_BRAIN = ITEMS.register("real_brain", PVZItemBase::new);
	
	//tool
	public static final RegistryObject<Item> ZOMBIE_FLAG = ITEMS.register("zombie_flag", ZombieFlagItem::new);
	public static final RegistryObject<Item> BOBSLE_CAR = ITEMS.register("bobsle_car", BobsleCarItem::new);
	
	//armor
	public static final RegistryObject<Item> CONE_HEAD = ITEMS.register("cone_head", ()->new ConeArmorItem(ArmorMaterial.LEATHER, EquipmentSlotType.HEAD));
	public static final RegistryObject<Item> BUCKET_HEAD = ITEMS.register("bucket_head", ()->new BucketArmorItem(ArmorMaterial.IRON, EquipmentSlotType.HEAD));
    
	//misc
	public static final RegistryObject<Item> STRANGE_HELP = ITEMS.register("strange_help", StrangeHelpItem::new);
	public static final RegistryObject<Item> GUILD_BOOK = ITEMS.register("guild_book", GuildBookItem::new);
	
	//spawn egg
	//drop
	public static final RegistryObject<PVZSpawnEggItem> SUN_SPAWN_EGG = registerSpawnEgg("sun", EntityRegister.SUN, Colors.YELLOW, Colors.WHITE);
	public static final RegistryObject<PVZSpawnEggItem> COIN_SPAWN_EGG = registerSpawnEgg("coin", EntityRegister.COIN, Colors.ORANGE, Colors.GOLD);
	public static final RegistryObject<PVZSpawnEggItem> ENERGY_SPAWN_EGG = registerSpawnEgg("energy", EntityRegister.ENERGY, Colors.GREEN, Colors.DARK_GREEN);
	//misc
	public static final RegistryObject<PVZSpawnEggItem> SMALL_CHOMPER_SPAWN_EGG = registerSpawnEgg("small_chomper", EntityRegister.SMALL_CHOMPER, Colors.PEA_GREEN, Colors.PURPLE);
	//animal
	public static final RegistryObject<PVZSpawnEggItem> FOODIE_ZOMBIE_SPAWN_EGG = registerSpawnEgg("foodie_zombie", EntityRegister.FOODIE_ZOMBIE, Colors.ZOMBIE_SKIN, Colors.WHITE);
	//zombie
	public static final RegistryObject<PVZSpawnEggItem> NORMAL_ZOMBIE_SPAWN_EGG = registerSpawnEgg("normal_zombie", EntityRegister.NORMAL_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> FLAG_ZOMBIE_SPAWN_EGG = registerSpawnEgg("flag_zombie", EntityRegister.FLAG_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> CONEHEAD_ZOMBIE_SPAWN_EGG = registerSpawnEgg("conehead_zombie", EntityRegister.CONEHEAD_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> POLE_ZOMBIE_SPAWN_EGG = registerSpawnEgg("pole_zombie", EntityRegister.POLE_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> BUCKETHEAD_ZOMBIE_SPAWN_EGG = registerSpawnEgg("buckethead_zombie", EntityRegister.BUCKETHEAD_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> SNORKEL_ZOMBIE_SPAWN_EGG = registerSpawnEgg("snorkel_zombie", EntityRegister.SNORKEL_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> ZOMBONI_SPAWN_EGG = registerSpawnEgg("zomboni", EntityRegister.ZOMBONI, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> BOBSLE_TEAM_SPAWN_EGG = registerSpawnEgg("bobsle_team", EntityRegister.BOBSLE_TEAM, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> BOBSLE_ZOMBIE_SPAWN_EGG = registerSpawnEgg("bobsle_zombie", EntityRegister.BOBSLE_ZOMBIE, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> ZOMBIE_DOLPHIN_SPAWN_EGG = registerSpawnEgg("zombie_dolphin", EntityRegister.ZOMBIE_DOLPHIN, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> DOLPHIN_RIDER_SPAWN_EGG = registerSpawnEgg("dolphin_rider", EntityRegister.DOLPHIN_RIDER, Colors.ZOMBIE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> DOLPHIN_RIDER_ZOMBIE_SPAWN_EGG = registerSpawnEgg("dolphin_rider_zombie", EntityRegister.DOLPHIN_RIDER_ZOMBIE, Colors.ZOMBIE_COLOR);
	
	//plant
	public static final RegistryObject<PVZSpawnEggItem> PEA_SHOOTER_SPAWN_EGG = registerSpawnEgg("pea_shooter", EntityRegister.PEA_SHOOTER, Colors.APPEASE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> SUN_FLOWER_SPAWN_EGG = registerSpawnEgg("sun_flower", EntityRegister.SUN_FLOWER, Colors.LIGHT_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> CHERRY_BOMB_SPAWN_EGG = registerSpawnEgg("cherry_bomb", EntityRegister.CHERRY_BOMB, Colors.EXPLOSION_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> WALL_NUT_SPAWN_EGG = registerSpawnEgg("wall_nut", EntityRegister.WALL_NUT, Colors.DEFENCE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> POTATO_MINE_SPAWN_EGG = registerSpawnEgg("potato_mine", EntityRegister.POTATO_MINE, Colors.EXPLOSION_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> SNOW_PEA_SPAWN_EGG = registerSpawnEgg("snow_pea", EntityRegister.SNOW_PEA, Colors.ICE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> CHOMPER_SPAWN_EGG = registerSpawnEgg("chomper", EntityRegister.CHOMPER, Colors.ENFORCE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> REPEATER_SPAWN_EGG = registerSpawnEgg("repeater", EntityRegister.REPEATER, Colors.APPEASE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> SQUASH_SPAWN_EGG = registerSpawnEgg("squash", EntityRegister.SQUASH, Colors.ENFORCE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> THREE_PEATER_SPAWN_EGG = registerSpawnEgg("three_peater", EntityRegister.THREE_PEATER, Colors.APPEASE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> TANGLE_KELP_SPAWN_EGG = registerSpawnEgg("tangle_kelp", EntityRegister.TANGLE_KELP, Colors.ENFORCE_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> JALAPENO_SPAWN_EGG = registerSpawnEgg("jalapeno", EntityRegister.JALAPENO, Colors.FLAME_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> SPIKE_WEED_SPAWN_EGG = registerSpawnEgg("spike_weed", EntityRegister.SPIKE_WEED, Colors.SPEAR_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> TORCH_WOOD_SPAWN_EGG = registerSpawnEgg("torch_wood", EntityRegister.TORCH_WOOD, Colors.FLAME_COLOR);
	public static final RegistryObject<PVZSpawnEggItem> TALL_NUT_SPAWN_EGG = registerSpawnEgg("tall_nut", EntityRegister.TALL_NUT, Colors.DEFENCE_COLOR);
//	public static final RegistryObject<PVZSpawnEggItem> WATER_GUARD_SPAWN_EGG = registerSpawnEgg("water_guard", EntityRegister.WATER_GUARD, Colors.DEFENCE_COLOR);
	
	/**
	 * register spawn eggs
	 */
    private static RegistryObject<PVZSpawnEggItem> registerSpawnEgg(String name,RegistryObject<? extends EntityType<?>> entityType,int color1,int color2){
    	return ITEMS.register(name+"_spawn_egg", () -> new PVZSpawnEggItem(entityType, color1, color2, new Item.Properties().group(GroupRegister.PVZ_MISC)));
    }

    /**
	 * register spawn eggs
	 */
    private static RegistryObject<PVZSpawnEggItem> registerSpawnEgg(String name,RegistryObject<? extends EntityType<?>> entityType,Pair<Integer,Integer> color){
    	return ITEMS.register(name+"_spawn_egg", () -> new PVZSpawnEggItem(entityType, color.getFirst(), color.getSecond(), new Item.Properties().group(GroupRegister.PVZ_MISC)));
    }
    
    private static RegistryObject<PlantCardItem> registerCard(Plants plant,boolean is){
    	String name=plant.toString();
    	if(is) name=name+"_enjoy";
    	name=name+"_card";
    	return ITEMS.register(name, ()->{return new PlantCardItem(plant,is);});
    }
}
