package com.hungteen.pvzmod.registry;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.entities.bullets.EntityBall;
import com.hungteen.pvzmod.entities.bullets.EntityButter;
import com.hungteen.pvzmod.entities.bullets.EntityCabbage;
import com.hungteen.pvzmod.entities.bullets.EntityKernel;
import com.hungteen.pvzmod.entities.bullets.EntityMelon;
import com.hungteen.pvzmod.entities.bullets.EntityPea;
import com.hungteen.pvzmod.entities.bullets.EntityPotato;
import com.hungteen.pvzmod.entities.bullets.EntityThorn;
import com.hungteen.pvzmod.entities.drops.EntityCoin;
import com.hungteen.pvzmod.entities.drops.EntityPlantFood;
import com.hungteen.pvzmod.entities.drops.EntitySun;
import com.hungteen.pvzmod.entities.npcs.EntityCrazyDave;
import com.hungteen.pvzmod.entities.npcs.EntityHim;
import com.hungteen.pvzmod.entities.npcs.EntityPanney;
import com.hungteen.pvzmod.entities.npcs.EntityTreeMan;
import com.hungteen.pvzmod.entities.plants.common.EntityCabbagePult;
import com.hungteen.pvzmod.entities.plants.common.EntityDoubleShooter;
import com.hungteen.pvzmod.entities.plants.common.EntityGatlingPea;
import com.hungteen.pvzmod.entities.plants.common.EntityKernelPult;
import com.hungteen.pvzmod.entities.plants.common.EntityMelonPult;
import com.hungteen.pvzmod.entities.plants.common.EntityPeaShooter;
import com.hungteen.pvzmod.entities.plants.common.EntitySplitPea;
import com.hungteen.pvzmod.entities.plants.common.EntityThreePeater;
import com.hungteen.pvzmod.entities.plants.defence.EntityNutWall;
import com.hungteen.pvzmod.entities.plants.defence.EntityPumpkin;
import com.hungteen.pvzmod.entities.plants.defence.EntityTallNut;
import com.hungteen.pvzmod.entities.plants.electricity.EntityLightningRod;
import com.hungteen.pvzmod.entities.plants.explosion.EntityCherryBomb;
import com.hungteen.pvzmod.entities.plants.explosion.EntityPotatoMine;
import com.hungteen.pvzmod.entities.plants.fight.EntitySpikeRock;
import com.hungteen.pvzmod.entities.plants.fight.EntitySpikeWeed;
import com.hungteen.pvzmod.entities.plants.fight.EntitySquash;
import com.hungteen.pvzmod.entities.plants.fight.EntityTangleKelp;
import com.hungteen.pvzmod.entities.plants.flame.EntityJalapeno;
import com.hungteen.pvzmod.entities.plants.flame.EntityTorchWood;
import com.hungteen.pvzmod.entities.plants.ice.EntityIceShroom;
import com.hungteen.pvzmod.entities.plants.ice.EntityIcebergLettuce;
import com.hungteen.pvzmod.entities.plants.ice.EntitySnowPea;
import com.hungteen.pvzmod.entities.plants.ice.EntityWinterMelon;
import com.hungteen.pvzmod.entities.plants.light.EntityGoldLeaf;
import com.hungteen.pvzmod.entities.plants.light.EntitySunFlower;
import com.hungteen.pvzmod.entities.plants.light.EntitySunShroom;
import com.hungteen.pvzmod.entities.plants.light.EntityTwinSunFlower;
import com.hungteen.pvzmod.entities.plants.magic.EntityCatTail;
import com.hungteen.pvzmod.entities.plants.magic.EntityCoffeeBean;
import com.hungteen.pvzmod.entities.plants.magic.EntityHypnoShroom;
import com.hungteen.pvzmod.entities.plants.magic.EntityMariGold;
import com.hungteen.pvzmod.entities.plants.magic.EntityStrangeCat;
import com.hungteen.pvzmod.entities.special.EntityGardenRake;
import com.hungteen.pvzmod.entities.zombies.EntityZomBoss;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityBucketHeadZombie;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityConeHeadZombie;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityFlagZombie;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityNormalZombie;
import com.hungteen.pvzmod.entities.zombies.grassday.EntityPoleZombie;
import com.hungteen.pvzmod.entities.zombies.grassnight.EntityBackupDancer;
import com.hungteen.pvzmod.entities.zombies.grassnight.EntityDancingZombie;
import com.hungteen.pvzmod.entities.zombies.grassnight.EntityFootballZombie;
import com.hungteen.pvzmod.entities.zombies.grassnight.EntityGigaFootballZombie;
import com.hungteen.pvzmod.entities.zombies.grassnight.EntityOldZombie;
import com.hungteen.pvzmod.entities.zombies.grassnight.EntityPaperZombie;
import com.hungteen.pvzmod.entities.zombies.grassnight.EntityScreenDoorZombie;
import com.hungteen.pvzmod.entities.zombies.grassnight.EntitySundayEditionZombie;
import com.hungteen.pvzmod.entities.zombies.grassnight.EntityTombStone;
import com.hungteen.pvzmod.entities.zombies.plantzombies.EntityGatlingPeaZombie;
import com.hungteen.pvzmod.entities.zombies.plantzombies.EntityJalapenoZombie;
import com.hungteen.pvzmod.entities.zombies.plantzombies.EntityNutWallZombie;
import com.hungteen.pvzmod.entities.zombies.plantzombies.EntityPeaShooterZombie;
import com.hungteen.pvzmod.entities.zombies.plantzombies.EntitySquashZombie;
import com.hungteen.pvzmod.entities.zombies.plantzombies.EntityTallNutZombie;
import com.hungteen.pvzmod.entities.zombies.poolday.EntityBobsle;
import com.hungteen.pvzmod.entities.zombies.poolday.EntityBobsleZombie;
import com.hungteen.pvzmod.entities.zombies.poolday.EntityDolphinRider;
import com.hungteen.pvzmod.entities.zombies.poolday.EntityLavaZombie;
import com.hungteen.pvzmod.entities.zombies.poolday.EntitySnorkelZombie;
import com.hungteen.pvzmod.entities.zombies.poolday.EntityZomboni;
import com.hungteen.pvzmod.entities.zombies.poolnight.EntityBalloonZombie;
import com.hungteen.pvzmod.entities.zombies.poolnight.EntityDiggerZombie;
import com.hungteen.pvzmod.entities.zombies.poolnight.EntityJackInBoxZombie;
import com.hungteen.pvzmod.entities.zombies.poolnight.EntityPogoZombie;
import com.hungteen.pvzmod.entities.zombies.poolnight.EntityYetiZombie;
import com.hungteen.pvzmod.entities.zombies.roof.EntityCataPultZombie;
import com.hungteen.pvzmod.entities.zombies.roof.EntityGargantuar;
import com.hungteen.pvzmod.entities.zombies.roof.EntityImp;
import com.hungteen.pvzmod.entities.zombies.roof.EntitySadGargantuar;
import com.hungteen.pvzmod.entities.zombies.special.EntityBalloon;
import com.hungteen.pvzmod.entities.zombies.special.EntityDolphin;
import com.hungteen.pvzmod.entities.zombies.special.EntityDuckyTube;
import com.hungteen.pvzmod.entities.zombies.special.EntityElementBall;
import com.hungteen.pvzmod.entities.zombies.special.EntityJackOutBoxZombie;
import com.hungteen.pvzmod.render.entities.EntityTFSnowQueen;
import com.hungteen.pvzmod.util.Reference;
import com.hungteen.pvzmod.util.enums.Colors;
import com.hungteen.pvzmod.util.enums.Enums;
import com.hungteen.pvzmod.util.enums.Enums.RGBIntegers;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityRegister {

	public static int size=120;
//	public static final int ENTITY_HIM = size++;
	public static final int ENTITY_NORMALZOMBIE = size++;
	public static final int ENTITY_PEASHOOTER =size++;
	public static final int ENTITY_PEA = size++;
	public static final int ENTITY_DOUBLESHOOTER = size++;
	public static final int ENTITY_SUNFLOWER = size++;
	public static final int ENTITY_TREEMAN = size++;
	public static final int ENTITY_SUN = size++;
	public static final int ENTITY_NUTWALL =size++;
	public static final int ENTITY_POTATOMINE=size++;
	public static final int ENTITY_CONEHEADZOMBIE=size++;
	public static final int ENTITY_BUCKETHEADZOMBIE=size++;
	public static final int ENTITY_CABBAGEPULT=size++;
	public static final int ENTITY_CABBAGE =size++;
	public static final int ENTITY_COIN =size++;
	public static final int ENTITY_FLAGZOMBIE =size++;
	public static final int ENTITY_PLANTFOOD = size++;
//	public static final int ENTITY_NAGA = size++;
	public static final int ENTITY_POTATO =size++;
	public static final int ENTITY_SNOWPEA = size++;
	public static final int ENTITY_ICEBERG_LETTUCE =size++;
	public static final int ENTITY_SCREENDOOR_ZOMBIE =size++;
	public static final int ENTITY_TORCHWOOD = size++;
	public static final int ENTITY_THREEPEATER = size++;
	public static final int ENTITY_SPLITPEA = size++;
	public static final int ENTITY_FOOTBALLZOMBIE = size++;
	public static final int ENTITY_GIGAFOOTBALLZOMBIE=size++;
	public static final int ENTITY_JANAPENO=size++;
	public static final int ENTITY_JALAPENO_ZOMBIE = size++;
	public static final int ENTITY_CHERRYBOMB = size++;
	public static final int ENTITY_ICESHROOM = size++;
	public static final int ENTITY_CATTAIL = size++;
	public static final int ENTITY_THORN =size++;
	public static final int ENTITY_CRAZYDAVE = size++;
	public static final int ENTITY_POLEZOMBIE = size++;
	public static final int ENTITY_PEASHOOTER_ZOMBIE = size++;
	public static final int ENTITY_NUTWALL_ZOMBIE = size++;
	public static final int ENTITY_SPIKE_WEED = size++;
	public static final int ENTITY_SQUASH = size++;
	public static final int ENTITY_SUNSHROOM= size++;
	public static final int ENTITY_PAPERZOMBIE = size++;
	public static final int ENTITY_OLDZOMBIE = size++;
	public static final int ENTITY_SUNDAYEDITION_ZOMBIE = size++;
	public static final int ENTITY_DACINGZOMBIE = size++;
	public static final int ENTITY_BACKUPDANCER = size++;
	public static final int ENTITY_GOLDLEAF = size++;
	public static final int ENTITY_MARIGOLD = size++;
	public static final int ENTITY_ZOMBONI = size++;
	public static final int ENTITY_BOBSLE = size++;
	public static final int ENTITY_BOBSLEZOMBIE = size++;
	public static final int ENTITY_KERNELPULT = size++;
	public static final int ENTITY_BUTTER = size++;
	public static final int ENTITY_KERNEL = size++;
	public static final int ENTITY_SNORKELZOMBIE = size++;
	public static final int ENTITY_LAVAZOMBIE = size++;
	public static final int ENTITY_DUCKYTUBE = size++;
	public static final int ENTITY_GARDENRAKE = size++;
	public static final int ENTITY_TOMBSTONE = size++;
	public static final int ENTITY_LIGHTNINGROD = size++;
	public static final int ENTITY_DOLPHINRIDER = size++;
	public static final int ENTITY_DOLPHIN = size++;
	public static final int ENTITY_PUMPKIN = size++;
	public static final int ENTITY_TALLNUT = size++;
	public static final int ENTITY_TALLNUT_ZOMBIE = size++;
	public static final int ENTITY_TWIN_SUNFLOWER = size++;
	public static final int ENTITY_TANGLEKELP = size++;
	public static final int ENTITY_BALLOON_ZOMBIE = size++;
	public static final int ENTITY_BALLOON = size++;
	public static final int ENTITY_GARGANTUAR = size++;
	public static final int ENTITY_IMP = size++;
	public static final int ENTITY_SADGARGANTUAR = size++;
	public static final int ENTITY_SPIKEROCK = size++;
	public static final int ENTITY_ZOMBOSS = size++;
	public static final int ENTITY_ELEMENTBALL = size++;
	public static final int ENTITY_JACKINBOX_ZOMBIE = size++;
	public static final int ENTITY_JACKOUTBOX_ZOMBIE = size++;
	public static final int ENTITY_MELONPULT = size++;
	public static final int ENTITY_MELON = size++;
	public static final int ENTITY_GATLINGPEA = size++;
	public static final int ENTITY_WINTERMELON = size++;
	public static final int ENTITY_HYPNOSHROOM = size++;
	public static final int ENTITY_GATLINGPEA_ZOMBIE = size++;
	public static final int ENTITY_SQUASH_ZOMBIE = size++;
	public static final int ENTITY_COFFEEBEAN = size++;
	public static final int ENTITY_POGOZOMBIE = size++;
	public static final int ENTITY_YETIZOMBIE = size++;
	public static final int ENTITY_CATAPULTZOMBIE = size++;
	public static final int ENTITY_BALL = size++;
	public static final int ENTITY_STRANGECAT = size++;
	public static final int ENTITY_DIGGERZOMBIE = size++;
	public static final int ENTITY_PANNEY = size++;
	
	public static void registerEntities()
	{
		//zombies
//		registerEntity("him",EntityHim.class,ENTITY_HIM,50,Enums.RGBIntegers.RED,0);
		//1
		registerEntity("normal_zombie",EntityNormalZombie.class,ENTITY_NORMALZOMBIE,150,Enums.RGBIntegers.RED,Enums.RGBIntegers.DARK_LIME_GREEN);
		registerEntity("flag_zombie", EntityFlagZombie.class, ENTITY_FLAGZOMBIE, 150, Enums.RGBIntegers.RED,0);
		registerEntity("conehead_zombie",EntityConeHeadZombie.class,ENTITY_CONEHEADZOMBIE,150,Enums.RGBIntegers.RED,0);
		registerEntity("pole_zombie", EntityPoleZombie.class, ENTITY_POLEZOMBIE, 150, Enums.RGBIntegers.RED,0);
		registerEntity("buckethead_zombie",EntityBucketHeadZombie.class,ENTITY_BUCKETHEADZOMBIE,150,Enums.RGBIntegers.RED,0);
		//2
		registerEntity("tomb_stone",EntityTombStone.class,ENTITY_TOMBSTONE,150,Colors.DARK_BLUE,Colors.WHITE);
		registerEntity("paper_zombie",EntityPaperZombie.class,ENTITY_PAPERZOMBIE,150,Enums.RGBIntegers.PINK,Enums.RGBIntegers.DARK_GRAY);
		registerEntity("screendoor_zombie", EntityScreenDoorZombie.class,ENTITY_SCREENDOOR_ZOMBIE, 150, Enums.RGBIntegers.RED,0);
		registerEntity("football_zombie", EntityFootballZombie.class, ENTITY_FOOTBALLZOMBIE, 150, Enums.RGBIntegers.RED,Enums.RGBIntegers.WHITE);
		registerEntity("dacing_zombie", EntityDancingZombie.class, ENTITY_DACINGZOMBIE, 150, RGBIntegers.PURPLE, Enums.RGBIntegers.ELECTRIC_BLUE);
		registerEntity("backup_dancer", EntityBackupDancer.class, ENTITY_BACKUPDANCER, 150, RGBIntegers.PINK, Enums.RGBIntegers.GREEN);
		registerEntity("old_zombie",EntityOldZombie.class,ENTITY_OLDZOMBIE,150,Enums.RGBIntegers.PINK,Enums.RGBIntegers.DARK_GRAY);
		registerEntity("giga_football_zombie", EntityGigaFootballZombie.class, ENTITY_GIGAFOOTBALLZOMBIE, 150, 0, Enums.RGBIntegers.WHITE);
		registerEntity("sunday_edition_zombie",EntitySundayEditionZombie.class,ENTITY_SUNDAYEDITION_ZOMBIE,150,Enums.RGBIntegers.PINK,Enums.RGBIntegers.DARK_GRAY);
		//3
		registerEntity("ducky_tube", EntityDuckyTube.class, ENTITY_DUCKYTUBE, 150, Colors.YELLOW, Colors.ORANGE);
		registerEntity("snorkel_zombie", EntitySnorkelZombie.class, ENTITY_SNORKELZOMBIE, 150, Colors.TYRIAN_PURPLE, Colors.DARK_GREEN);
		registerEntity("zomboni", EntityZomboni.class, ENTITY_ZOMBONI, 150, Colors.WHITE, Colors.BLUE);
		registerEntity("bobsle", EntityBobsle.class, ENTITY_BOBSLE, 150, Colors.RED, Colors.RED_2);
		registerEntity("bobsle_zombie", EntityBobsleZombie.class, ENTITY_BOBSLEZOMBIE, 150, Colors.RED, Colors.RED_2);
		registerEntity("dolphin_rider", EntityDolphinRider.class, ENTITY_DOLPHINRIDER, 150, Colors.DARK_GRAY, Colors.RED);
		registerEntity("dolphin", EntityDolphin.class, ENTITY_DOLPHIN, 150, Colors.DOLPHIN_BLUE, Colors.BLUE_WHITE);
		registerEntity("lava_zombie",EntityLavaZombie.class,ENTITY_LAVAZOMBIE,150,Colors.BLACK,Colors.DARK_GREEN);
		//4
		registerEntity("jack_in_box_zombie", EntityJackInBoxZombie.class, ENTITY_JACKINBOX_ZOMBIE, 150, Colors.RED, Colors.BLACK);
		registerEntity("jack_out_box_zombie", EntityJackOutBoxZombie.class, ENTITY_JACKOUTBOX_ZOMBIE, 150, Colors.RED, Colors.BLACK);
		registerEntity("balloon",EntityBalloon.class,ENTITY_BALLOON,150,Colors.RED,Colors.BLACK);
		registerEntity("balloon_zombie",EntityBalloonZombie.class,ENTITY_BALLOON_ZOMBIE,150,Colors.RED,Colors.BLACK);
		registerEntity("digger_zombie",EntityDiggerZombie.class,ENTITY_DIGGERZOMBIE,150,Colors.RED,Colors.BLACK);
		registerEntity("pogo_zombie", EntityPogoZombie.class, ENTITY_POGOZOMBIE, 150, Colors.RED, Colors.BLACK);
		registerEntity("yeti_zombie", EntityYetiZombie.class, ENTITY_YETIZOMBIE, 150, Colors.RED, Colors.BLACK);
		//5
		registerEntity("catapult_zombie", EntityCataPultZombie.class, ENTITY_CATAPULTZOMBIE, 150, Colors.RED, Colors.BLACK);
		registerEntity("gargantuar", EntityGargantuar.class, ENTITY_GARGANTUAR, 150, Colors.RED, Colors.BLACK);
		registerEntity("imp", EntityImp.class, ENTITY_IMP, 150, Colors.RED, Colors.BLACK);
		registerEntity("sad_gargantuar", EntitySadGargantuar.class, ENTITY_SADGARGANTUAR, 150, Colors.RED, Colors.BLACK);
		registerEntity("zomboss", EntityZomBoss.class, ENTITY_ZOMBOSS, 150, Colors.RED, Colors.BLACK);
		
		//plant_zombie
		registerEntity("peashooter_zombie", EntityPeaShooterZombie.class, ENTITY_PEASHOOTER_ZOMBIE, 150, Enums.RGBIntegers.GREEN,0);
		registerEntity("nutwall_zombie", EntityNutWallZombie.class, ENTITY_NUTWALL_ZOMBIE, 150, Enums.RGBIntegers.GREEN,Enums.RGBIntegers.GOLD_YELLOW);
		registerEntity("tallnut_zombie",EntityTallNutZombie.class,ENTITY_TALLNUT_ZOMBIE,150,Colors.GREEN,Colors.BLACK);
		registerEntity("gatlingpea_zombie", EntityGatlingPeaZombie.class, ENTITY_GATLINGPEA_ZOMBIE, 150, Colors.RED, Colors.GREEN);
		registerEntity("squash_zombie", EntitySquashZombie.class, ENTITY_SQUASH_ZOMBIE, 150, Colors.RED, Colors.GREEN);
		registerEntity("jalapeno_zombie", EntityJalapenoZombie.class, ENTITY_JALAPENO_ZOMBIE, 150, Enums.RGBIntegers.RED,0);
		
		//npcs
		registerEntity("tree_man",EntityTreeMan.class,ENTITY_TREEMAN,50,0,Enums.RGBIntegers.WHITE);
		registerEntity("crazy_dave",EntityCrazyDave.class,ENTITY_CRAZYDAVE,50,0,Enums.RGBIntegers.WHITE);
//		registerEntity("naga",EntityTFSnowQueen.class,ENTITY_NAGA,150,0,0);
		registerEntity("panney", EntityPanney.class, ENTITY_PANNEY, 50, Colors.RED, Colors.BLUE_WHITE);
		
		//drops
		registerEntity("sun",EntitySun.class,ENTITY_SUN,50,Enums.RGBIntegers.YELLOW,Enums.RGBIntegers.WHITE);
		registerEntity("coin",EntityCoin.class,ENTITY_COIN,50,Enums.RGBIntegers.GOLD_YELLOW,0);
		registerEntity("plant_food", EntityPlantFood.class, ENTITY_PLANTFOOD, 50, 0,Enums.RGBIntegers.DARK_LIME_GREEN);
		
		//bullets
		registerBullet("pea",EntityPea.class,ENTITY_PEA,50);
		registerBullet("cabbage",EntityCabbage.class,ENTITY_CABBAGE,50);
		registerBullet("potato",EntityPotato.class,ENTITY_POTATO,50);
		registerBullet("thorn", EntityThorn.class, ENTITY_THORN, 50);
		registerBullet("butter",EntityButter.class,ENTITY_BUTTER,50);
		registerBullet("kernel",EntityKernel.class,ENTITY_KERNEL,50);
		registerBullet("element_ball",EntityElementBall.class,ENTITY_ELEMENTBALL,50);
		registerBullet("melon", EntityMelon.class, ENTITY_MELON, 50);
		registerBullet("ball", EntityBall.class, ENTITY_BALL, 50);
		
		//special
		registerEntity("garden_rake", EntityGardenRake.class, ENTITY_GARDENRAKE, 50, Colors.LITTLE_YELLOW, Colors.BLACK);
		
		//plants
		//1
		registerEntity("pea_shooter",EntityPeaShooter.class,ENTITY_PEASHOOTER,50,Colors.GREEN,Colors.GREEN);
		registerEntity("sun_flower",EntitySunFlower.class,ENTITY_SUNFLOWER,50,Colors.YELLOW,Colors.GREEN);
		registerEntity("cherry_bomb", EntityCherryBomb.class, ENTITY_CHERRYBOMB, 50, Colors.RED,Colors.GREEN);
		registerEntity("nut_wall",EntityNutWall.class,ENTITY_NUTWALL,50,Colors.NUT,Colors.ORANGE);
		registerEntity("potato_mine",EntityPotatoMine.class,ENTITY_POTATOMINE,50,Colors.POTATO,Colors.WHITE);
		registerEntity("snow_pea",EntitySnowPea.class,ENTITY_SNOWPEA,50,Colors.ICE,Colors.GREEN);
		registerEntity("double_shooter",EntityDoubleShooter.class,ENTITY_DOUBLESHOOTER,50,Colors.GREEN,Colors.GREEN);
		//2
		registerEntity("sun_shroom", EntitySunShroom.class, ENTITY_SUNSHROOM, 50, Colors.YELLOW,Colors.WHITE);
		registerEntity("hypno_shroom", EntityHypnoShroom.class, ENTITY_HYPNOSHROOM, 50, Colors.PINK, Colors.WHITE);
		registerEntity("ice_shroom", EntityIceShroom.class, ENTITY_ICESHROOM, 50, Colors.BLUE, Colors.WHITE);
		//3
		registerEntity("squash", EntitySquash.class, ENTITY_SQUASH, 50, Colors.SQUASH, Colors.GREEN);
	    registerEntity("three_peater",EntityThreePeater.class,ENTITY_THREEPEATER,50,Colors.GREEN,Colors.GREEN);
		registerEntity("tangle_kelp",EntityTangleKelp.class,ENTITY_TANGLEKELP,50,Colors.BLACK,Colors.GREEN);
	    registerEntity("jalapeno",EntityJalapeno.class,ENTITY_JANAPENO,50,Colors.RED,Colors.GREEN);
		registerEntity("spike_weed", EntitySpikeWeed.class, ENTITY_SPIKE_WEED, 50, Colors.DARK_GREEN, Colors.SILVER);
		registerEntity("torch_wood", EntityTorchWood.class, ENTITY_TORCHWOOD, 50, Colors.BROWN, Colors.YELLOW);
		registerEntity("tall_nut",EntityTallNut.class,ENTITY_TALLNUT,50,Colors.NUT,Colors.ORANGE);
		//4
		registerEntity("split_pea", EntitySplitPea.class,ENTITY_SPLITPEA, 50, Colors.GREEN,Colors.GREEN);
		registerEntity("pumpkin", EntityPumpkin.class,ENTITY_PUMPKIN, 50, Colors.ORANGE,Colors.BLACK);
		//5
		registerEntity("cabbage_pult",EntityCabbagePult.class,ENTITY_CABBAGEPULT,50,Colors.GREEN,Colors.GREEN);
		registerEntity("kernel_pult",EntityKernelPult.class,ENTITY_KERNELPULT,50,Colors.YELLOW,Colors.YELLOW_GREEN);
		registerEntity("coffee_bean", EntityCoffeeBean.class, ENTITY_COFFEEBEAN, 50, Colors.BROWN, Colors.LIT_PINK);
		registerEntity("marigold", EntityMariGold.class, ENTITY_MARIGOLD, 50, Colors.WHITE, Colors.YELLOW);
		registerEntity("melon_pult", EntityMelonPult.class, ENTITY_MELONPULT, 60, Colors.GREEN, Colors.DARK_GREEN);
		//up
		registerEntity("gatling_pea", EntityGatlingPea.class, ENTITY_GATLINGPEA, 50, Colors.GREEN, Colors.GREEN);
		registerEntity("twin_sunflower",EntityTwinSunFlower.class,ENTITY_TWIN_SUNFLOWER,50,Colors.YELLOW,Colors.GREEN);
		registerEntity("cat_tail", EntityCatTail.class, ENTITY_CATTAIL, 60, Colors.PINK, Colors.GREEN);
		registerEntity("winter_melon", EntityWinterMelon.class, ENTITY_WINTERMELON, 60, Colors.ICE, Colors.DARK_AQUA);
		registerEntity("spike_rock", EntitySpikeRock.class, ENTITY_SPIKEROCK, 50, Colors.ROCK,Colors.WHITE);
		//other
		registerEntity("iceberg_lettuce",EntityIcebergLettuce.class,ENTITY_ICEBERG_LETTUCE,50,Colors.BLUE_ICE,Colors.ICE);
		registerEntity("gold_leaf", EntityGoldLeaf.class, ENTITY_GOLDLEAF, 50, Colors.YELLOW,Colors.GOLD_YELLOW);
		//self_make
		registerEntity("lightning_rod",EntityLightningRod.class,ENTITY_LIGHTNINGROD,50,Colors.ELECTRIC_BLUE,Colors.GREEN);
		registerEntity("strange_cat",EntityStrangeCat.class,ENTITY_STRANGECAT,50,Colors.WHITE,Colors.BLACK);
	}
	
	private static void registerEntity(String name,Class<? extends Entity> entity,int id,int range,int color1,int color2)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID+":"+name), entity, name, id, Main.instance, range, 1, true, color1, color2);
	}
	
	private static void registerBullet(String name,Class<? extends Entity> entity,int id,int range)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID+":"+name), entity, name, id, Main.instance, range, 1, true);
	}
}
