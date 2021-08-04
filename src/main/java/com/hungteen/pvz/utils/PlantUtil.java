package com.hungteen.pvz.utils;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import javax.annotation.Nullable;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.item.card.PlantCardItem;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.enums.CDs;
import com.hungteen.pvz.utils.enums.Essences;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Ranks;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;

public class PlantUtil {
	
	public static final EnumMap<Plants, Integer> PLANT_SUN_COST = new EnumMap<>(Plants.class);
	public static final EnumMap<Plants, CDs> PLANT_CD = new EnumMap<>(Plants.class);
	public static final EnumMap<Plants, Essences> PLANT_ESSENCE = new EnumMap<>(Plants.class);
	public static final EnumMap<Plants, Ranks> PLANT_RANK = new EnumMap<>(Plants.class);
	public static final EnumMap<Plants, RegistryObject<? extends EntityType<? extends PVZPlantEntity>>> PLANT_ENTITY = new EnumMap<>(Plants.class);
	public static final EnumMap<Plants, RegistryObject<? extends PlantCardItem>> PLANT_SUMMON_CARD = new EnumMap<>(Plants.class);
	public static final EnumMap<Plants, RegistryObject<? extends PlantCardItem>> PLANT_ENJOY_CARD = new EnumMap<>(Plants.class);
                                                   //1  2  3  4  5  6   7   8   9   10  11  12  13   14   15   16   17   18   19   20
//	public static final int[] GRAY_XP = new int[] {0,10,15,25,40,60,100,140,200,280,400,560,800,1250,1700,2250,3000,4000,5400,7500,999999999};
//	public static final int[] WHITE_XP = new int[] {0,15,25,40,60,80,130,180,240,325,450,620,880,1400,1920,2500,3400,4500,6000,8400,999999999};
//	public static final int[] GREEN_XP = new int[] {0,20,35,50,75,105,175,235,300,400,540,720,1000,1600,2250,3000,4000,5200,7000,9600,999999999};
//	public static final int[] BLUE_XP = new int[] {0,25,50,70,100,135,200,270,350,450,600,800,1100,1800,2500,3400,4500,6000,8000,10800,999999999};
//	public static final int[] PURPLE_XP = new int[] {0,30,60,80,105,150,225,300,400,520,700,960,1300,2100,3000,4200,5600,7200,9600,12500,999999999};
//	public static final int[] GOLD_XP = new int[] {0,35,60,90,120,175,255,350,450,600,800,1080,1500,2500,3600,5000,6400,8100,10800,14000,999999999};
//	public static final int[] MEGA_XP = new int[] {0,40,70,100,150,210,300,400,520,700,960,1300,1800,3000,4200,6000,7800,9600,12500,16000,999999999};
	public static int CURRENT_PLANT_NUM = 0;
	public static final int MAX_PLANT_LEVEL = 20;
	public static final float PUMPKIN_LIFE = 400;
	public static final float PUMPKIN_SUPER_LIFE = 400;
	
	static {
		putPlantInfoToMap(Plants.PEA_SHOOTER, 100, CDs.HUGE_FAST, Ranks.GRAY, Essences.APPEASE, EntityRegister.PEA_SHOOTER, ItemRegister.PEA_SHOOTER_CARD, ItemRegister.PEA_SHOOTER_ENJOY_CARD);
		putPlantInfoToMap(Plants.SUN_FLOWER, 50, CDs.HUGE_FAST, Ranks.GRAY, Essences.LIGHT, EntityRegister.SUN_FLOWER, ItemRegister.SUN_FLOWER_CARD, ItemRegister.SUN_FLOWER_ENJOY_CARD);
		putPlantInfoToMap(Plants.CHERRY_BOMB, 150, CDs.HUGE_SLOW, Ranks.BLUE, Essences.EXPLOSION, EntityRegister.CHERRY_BOMB, ItemRegister.CHERRY_BOMB_CARD, ItemRegister.CHERRY_BOMB_ENJOY_CARD);
		putPlantInfoToMap(Plants.WALL_NUT, 50, CDs.SLOW, Ranks.WHITE, Essences.DEFENCE, EntityRegister.WALL_NUT, ItemRegister.WALL_NUT_CARD, ItemRegister.WALL_NUT_ENJOY_CARD);
		putPlantInfoToMap(Plants.POTATO_MINE, 25, CDs.LITTLE_SLOW, Ranks.WHITE, Essences.EXPLOSION, EntityRegister.POTATO_MINE, ItemRegister.POTATO_MINE_CARD, ItemRegister.POTATO_MINE_ENJOY_CARD);
		putPlantInfoToMap(Plants.SNOW_PEA, 175, CDs.VERY_FAST, Ranks.GREEN, Essences.ICE, EntityRegister.SNOW_PEA, ItemRegister.SNOW_PEA_CARD, ItemRegister.SNOW_PEA_ENJOY_CARD);
		putPlantInfoToMap(Plants.CHOMPER, 150, CDs.NORMAL, Ranks.BLUE, Essences.ENFORCE, EntityRegister.CHOMPER, ItemRegister.CHOMPER_CARD, ItemRegister.CHOMPER_ENJOY_CARD);
		putPlantInfoToMap(Plants.REPEATER, 200, CDs.VERY_FAST, Ranks.GREEN, Essences.APPEASE, EntityRegister.REPEATER, ItemRegister.REPEATER_CARD, ItemRegister.REPEATER_ENJOY_CARD);
		putPlantInfoToMap(Plants.PUFF_SHROOM, 0, CDs.VERY_FAST, Ranks.WHITE, Essences.TOXIC, EntityRegister.PUFF_SHROOM, ItemRegister.PUFF_SHROOM_CARD, ItemRegister.PUFF_SHROOM_ENJOY_CARD);
		putPlantInfoToMap(Plants.SUN_SHROOM, 25, CDs.HUGE_FAST, Ranks.WHITE, Essences.LIGHT, EntityRegister.SUN_SHROOM, ItemRegister.SUN_SHROOM_CARD, ItemRegister.SUN_SHROOM_ENJOY_CARD);
		putPlantInfoToMap(Plants.FUME_SHROOM, 100, CDs.LITTLE_FAST, Ranks.GREEN, Essences.TOXIC, EntityRegister.FUME_SHROOM, ItemRegister.FUME_SHROOM_CARD, ItemRegister.FUME_SHROOM_ENJOY_CARD);
		putPlantInfoToMap(Plants.GRAVE_BUSTER, 75, CDs.VERY_FAST, Ranks.WHITE, Essences.ASSIST, EntityRegister.GRAVE_BUSTER, ItemRegister.GRAVE_BUSTER_CARD, ItemRegister.GRAVE_BUSTER_ENJOY_CARD);
		putPlantInfoToMap(Plants.HYPNO_SHROOM, 75, CDs.SLOW, Ranks.BLUE, Essences.MAGIC, EntityRegister.HYPNO_SHROOM, ItemRegister.HYPNO_SHROOM_CARD, ItemRegister.HYPNO_SHROOM_ENJOY_CARD);
		putPlantInfoToMap(Plants.SCAREDY_SHROOM, 25, CDs.FAST, Ranks.GRAY, Essences.TOXIC, EntityRegister.SCAREDY_SHROOM, ItemRegister.SCAREDY_SHROOM_CARD, ItemRegister.SCAREDY_SHROOM_ENJOY_CARD);
		putPlantInfoToMap(Plants.ICE_SHROOM, 75, CDs.VERY_SLOW, Ranks.BLUE, Essences.ICE, EntityRegister.ICE_SHROOM, ItemRegister.ICE_SHROOM_CARD, ItemRegister.ICE_SHROOM_ENJOY_CARD);
		putPlantInfoToMap(Plants.DOOM_SHROOM, 250, CDs.SUPER_SLOW, Ranks.GOLD, Essences.EXPLOSION, EntityRegister.DOOM_SHROOM, ItemRegister.DOOM_SHROOM_CARD, ItemRegister.DOOM_SHROOM_ENJOY_CARD);
		putPlantInfoToMap(Plants.LILY_PAD, 25, CDs.SUPER_FAST, Ranks.GRAY, Essences.ASSIST, EntityRegister.LILY_PAD, ItemRegister.LILY_PAD_CARD, ItemRegister.LILY_PAD_ENJOY_CARD);
		putPlantInfoToMap(Plants.SQUASH, 50, CDs.LITTLE_SLOW, Ranks.GREEN, Essences.ENFORCE, EntityRegister.SQUASH, ItemRegister.SQUASH_CARD, ItemRegister.SQUASH_ENJOY_CARD);
		putPlantInfoToMap(Plants.THREE_PEATER, 300, CDs.FAST, Ranks.BLUE, Essences.APPEASE, EntityRegister.THREE_PEATER, ItemRegister.THREE_PEATER_CARD, ItemRegister.THREE_PEATER_ENJOY_CARD);
		putPlantInfoToMap(Plants.TANGLE_KELP, 25, CDs.LITTLE_SLOW, Ranks.WHITE, Essences.ENFORCE, EntityRegister.TANGLE_KELP, ItemRegister.TANGLE_KELP_CARD, ItemRegister.TANGLE_KELP_ENJOY_CARD);
		putPlantInfoToMap(Plants.JALAPENO, 175, CDs.HUGE_SLOW, Ranks.BLUE, Essences.FLAME, EntityRegister.JALAPENO, ItemRegister.JALAPENO_CARD, ItemRegister.JALAPENO_ENJOY_CARD);
		putPlantInfoToMap(Plants.SPIKE_WEED, 100, CDs.LITTLE_FAST, Ranks.WHITE, Essences.SPEAR, EntityRegister.SPIKE_WEED, ItemRegister.SPIKE_WEED_CARD, ItemRegister.SPIKE_WEED_ENJOY_CARD);
		putPlantInfoToMap(Plants.TORCH_WOOD, 175, CDs.NORMAL, Ranks.GREEN, Essences.FLAME, EntityRegister.TORCH_WOOD, ItemRegister.TORCH_WOOD_CARD, ItemRegister.TORCH_WOOD_ENJOY_CARD);
		putPlantInfoToMap(Plants.TALL_NUT, 125, CDs.VERY_SLOW, Ranks.BLUE, Essences.DEFENCE, EntityRegister.TALL_NUT, ItemRegister.TALL_NUT_CARD, ItemRegister.TALL_NUT_ENJOY_CARD);
		putPlantInfoToMap(Plants.SEA_SHROOM, 0, CDs.LITTLE_FAST, Ranks.GRAY, Essences.TOXIC, EntityRegister.SEA_SHROOM, ItemRegister.SEA_SHROOM_CARD, ItemRegister.SEA_SHROOM_ENJOY_CARD);
		putPlantInfoToMap(Plants.PLANTERN, 75, CDs.FAST, Ranks.WHITE, Essences.LIGHT, EntityRegister.PLANTERN, ItemRegister.PLANTERN_CARD, ItemRegister.PLANTERN_ENJOY_CARD);
		putPlantInfoToMap(Plants.CACTUS, 125, CDs.FAST, Ranks.GREEN, Essences.SPEAR, EntityRegister.CACTUS, ItemRegister.CACTUS_CARD, ItemRegister.CACTUS_ENJOY_CARD);
		putPlantInfoToMap(Plants.BLOVER, 50, CDs.LITTLE_FAST, Ranks.GREEN, Essences.ASSIST, EntityRegister.BLOVER, ItemRegister.BLOVER_CARD, ItemRegister.BLOVER_ENJOY_CARD);
		putPlantInfoToMap(Plants.SPLIT_PEA, 125, CDs.VERY_FAST, Ranks.WHITE, Essences.APPEASE, EntityRegister.SPLIT_PEA, ItemRegister.SPLIT_PEA_CARD, ItemRegister.SPLIT_PEA_ENJOY_CARD);
		putPlantInfoToMap(Plants.STAR_FRUIT, 150, CDs.FAST, Ranks.GREEN, Essences.APPEASE, EntityRegister.STAR_FRUIT, ItemRegister.STAR_FRUIT_CARD, ItemRegister.STAR_FRUIT_ENJOY_CARD);
		putPlantInfoToMap(Plants.PUMPKIN, 125, CDs.SLOW, Ranks.GREEN, Essences.DEFENCE, EntityRegister.PUMPKIN, ItemRegister.PUMPKIN_CARD, ItemRegister.PUMPKIN_ENJOY_CARD);
		putPlantInfoToMap(Plants.MAGNET_SHROOM, 100, CDs.LITTLE_FAST, Ranks.PURPLE, Essences.ASSIST, EntityRegister.MAGNET_SHROOM, ItemRegister.MAGNET_SHROOM_CARD, ItemRegister.MAGNET_SHROOM_ENJOY_CARD);
		putPlantInfoToMap(Plants.CABBAGE_PULT, 100, CDs.VERY_FAST, Ranks.GRAY, Essences.ARMA, EntityRegister.CABBAGE_PULT, ItemRegister.CABBAGE_PULT_CARD, ItemRegister.CABBAGE_PULT_ENJOY_CARD);
		putPlantInfoToMap(Plants.FLOWER_POT, 25, CDs.HUGE_FAST, Ranks.GRAY, Essences.ASSIST, null, ItemRegister.FLOWER_POT_CARD, ItemRegister.FLOWER_POT_ENJOY_CARD);
		putPlantInfoToMap(Plants.KERNEL_PULT, 100, CDs.FAST, Ranks.GREEN, Essences.ARMA, EntityRegister.KERNEL_PULT, ItemRegister.KERNEL_PULT_CARD, ItemRegister.KERNEL_PULT_ENJOY_CARD);
		putPlantInfoToMap(Plants.COFFEE_BEAN, 75, CDs.FAST, Ranks.GREEN, Essences.MAGIC, EntityRegister.COFFEE_BEAN, ItemRegister.COFFEE_BEAN_CARD, ItemRegister.COFFEE_BEAN_ENJOY_CARD);
		putPlantInfoToMap(Plants.GARLIC, 50, CDs.NORMAL, Ranks.WHITE, Essences.DEFENCE, EntityRegister.GARLIC, ItemRegister.GARLIC_CARD, ItemRegister.GARLIC_ENJOY_CARD);
		putPlantInfoToMap(Plants.UMBRELLA_LEAF, 100, CDs.NORMAL, Ranks.GREEN, Essences.ENFORCE, EntityRegister.UMBRELLA_LEAF, ItemRegister.UMBRELLA_LEAF_CARD, ItemRegister.UMBRELLA_LEAF_ENJOY_CARD);
		putPlantInfoToMap(Plants.MARIGOLD, 50, CDs.LITTLE_SLOW, Ranks.PURPLE, Essences.MAGIC, EntityRegister.MARIGOLD, ItemRegister.MARIGOLD_CARD, ItemRegister.MARIGOLD_ENJOY_CARD);
		putPlantInfoToMap(Plants.MELON_PULT, 325, CDs.FAST, Ranks.PURPLE, Essences.ARMA, EntityRegister.MELON_PULT, ItemRegister.MELON_PULT_CARD, ItemRegister.MELON_PULT_ENJOY_CARD);
		putPlantInfoToMap(Plants.GATLING_PEA, 250, CDs.VERY_SLOW, Ranks.PURPLE, Essences.APPEASE, EntityRegister.GATLING_PEA, ItemRegister.GATLING_PEA_CARD, ItemRegister.GATLING_PEA_ENJOY_CARD);
		putPlantInfoToMap(Plants.TWIN_SUNFLOWER, 150, CDs.VERY_SLOW, Ranks.BLUE, Essences.LIGHT, EntityRegister.TWIN_SUNFLOWER, ItemRegister.TWIN_SUNFLOWER_CARD, ItemRegister.TWIN_SUNFLOWER_ENJOY_CARD);
		putPlantInfoToMap(Plants.GLOOM_SHROOM, 175, CDs.VERY_SLOW, Ranks.GOLD, Essences.TOXIC, EntityRegister.GLOOM_SHROOM, ItemRegister.GLOOM_SHROOM_CARD, ItemRegister.GLOOM_SHROOM_ENJOY_CARD);
		putPlantInfoToMap(Plants.CAT_TAIL, 275, CDs.VERY_SLOW, Ranks.GOLD, Essences.SPEAR, EntityRegister.CAT_TAIL, ItemRegister.CAT_TAIL_CARD, ItemRegister.CAT_TAIL_ENJOY_CARD);
		putPlantInfoToMap(Plants.WINTER_MELON, 225, CDs.VERY_SLOW, Ranks.GOLD, Essences.ICE, EntityRegister.WINTER_MELON, ItemRegister.WINTER_MELON_CARD, ItemRegister.WINTER_MELON_ENJOY_CARD);
		putPlantInfoToMap(Plants.GOLD_MAGNET, 100, CDs.VERY_SLOW, Ranks.WHITE, Essences.ASSIST, EntityRegister.GOLD_MAGNET, ItemRegister.GOLD_MAGNET_CARD, ItemRegister.GOLD_MAGNET_ENJOY_CARD);
		putPlantInfoToMap(Plants.SPIKE_ROCK, 150, CDs.VERY_SLOW, Ranks.PURPLE, Essences.SPEAR, EntityRegister.SPIKE_ROCK, ItemRegister.SPIKE_ROCK_CARD, ItemRegister.SPIKE_ROCK_ENJOY_CARD);
		putPlantInfoToMap(Plants.COB_CANNON, 500, CDs.HUGE_SLOW, Ranks.GOLD, Essences.EXPLOSION, EntityRegister.COB_CANNON, ItemRegister.COB_CANNON_CARD, ItemRegister.COB_CANNON_ENJOY_CARD);
		putPlantInfoToMap(Plants.IMITATER, 0, CDs.FAST, Ranks.GOLD, Essences.MAGIC, EntityRegister.IMITATER, ItemRegister.IMITATER_CARD, ItemRegister.IMITATER_ENJOY_CARD);
		putPlantInfoToMap(Plants.WATER_GUARD, 50, CDs.LITTLE_SLOW, Ranks.GRAY, Essences.DEFENCE, EntityRegister.WATER_GUARD, ItemRegister.WATER_GUARD_CARD, ItemRegister.WATER_GUARD_ENJOY_CARD);
		putPlantInfoToMap(Plants.STRANGE_CAT, 325, CDs.HUGE_SLOW, Ranks.PURPLE, Essences.MAGIC, EntityRegister.STRANGE_CAT, ItemRegister.STRANGE_CAT_CARD, ItemRegister.STRANGE_CAT_ENJOY_CARD);
		putPlantInfoToMap(Plants.GOLD_LEAF, 75, CDs.NORMAL, Ranks.PURPLE, Essences.LIGHT, EntityRegister.GOLD_LEAF, ItemRegister.GOLD_LEAF_CARD, ItemRegister.GOLD_LEAF_ENJOY_CARD);
		putPlantInfoToMap(Plants.ANGEL_STAR_FRUIT, 225, CDs.LITTLE_FAST, Ranks.PURPLE, Essences.APPEASE, EntityRegister.ANGEL_STAR_FRUIT, ItemRegister.ANGEL_STAR_FRUIT_CARD, ItemRegister.ANGEL_STAR_FRUIT_ENJOY_CARD);
		putPlantInfoToMap(Plants.EXPLODE_O_NUT, 150, CDs.HUGE_SLOW, Ranks.PURPLE, Essences.EXPLOSION, EntityRegister.EXPLODE_O_NUT, ItemRegister.EXPLODE_O_NUT_CARD, ItemRegister.EXPLODE_O_NUT_ENJOY_CARD);
		putPlantInfoToMap(Plants.GIANT_WALL_NUT, 200, CDs.SUPER_SLOW, Ranks.PURPLE, Essences.DEFENCE, EntityRegister.GIANT_WALL_NUT, ItemRegister.GIANT_WALL_NUT_CARD, ItemRegister.GIANT_WALL_NUT_ENJOY_CARD);
		putPlantInfoToMap(Plants.BUTTER_PULT, 275, CDs.NORMAL, Ranks.GOLD, Essences.ARMA, EntityRegister.BUTTER_PULT, ItemRegister.BUTTER_PULT_CARD, ItemRegister.BUTTER_PULT_ENJOY_CARD);
		putPlantInfoToMap(Plants.BAMBOO_LORD, 75, CDs.NORMAL, Ranks.BLUE, Essences.EXPLOSION, EntityRegister.BAMBOO_LORD, ItemRegister.BAMBOO_LORD_CARD, ItemRegister.BAMBOO_LORD_ENJOY_CARD);
		putPlantInfoToMap(Plants.ICEBERG_LETTUCE, 0, CDs.LITTLE_FAST, Ranks.WHITE, Essences.ICE, EntityRegister.ICEBERG_LETTUCE, ItemRegister.ICEBERG_LETTUCE_CARD, ItemRegister.ICEBERG_LETTUCE_ENJOY_CARD);
		putPlantInfoToMap(Plants.BONK_CHOY, 175, CDs.LITTLE_FAST, Ranks.BLUE, Essences.ENFORCE, EntityRegister.BONK_CHOY, ItemRegister.BONK_CHOY_CARD, ItemRegister.BONK_CHOY_ENJOY_CARD);
		
	}
	
	/**
	 * init plant map that must after RegistryObject present.
	 */
	public static void initPlantMap() {
		
	}
	
	public static void putPlantInfoToMap(Plants plant,final int cost, CDs cd, Ranks rank, Essences essence, RegistryObject<? extends EntityType<? extends PVZPlantEntity>> type, RegistryObject<? extends PlantCardItem> summonCard, RegistryObject<? extends PlantCardItem> enjoyCard) {
		PLANT_SUN_COST.put(plant, cost);
		PLANT_CD.put(plant, cd);
		PLANT_RANK.put(plant, rank);
		PLANT_ESSENCE.put(plant, essence);
		PLANT_ENTITY.put(plant, type);
		PLANT_SUMMON_CARD.put(plant, summonCard);
		PLANT_ENJOY_CARD.put(plant, enjoyCard);
		++ CURRENT_PLANT_NUM;
	}
	
	public static List<Block> getPlantSuitBlock(Plants plant){
		List<Block> list = new ArrayList<>();
		list.add(BlockRegister.GOLD_TILE1.get());
		list.add(BlockRegister.GOLD_TILE2.get());
		list.add(BlockRegister.GOLD_TILE3.get());
		if(plant == Plants.GOLD_LEAF) {
			list.add(Blocks.GOLD_BLOCK);
			return list;
		}
		list.add(Blocks.GRASS_BLOCK);
		list.add(BlockRegister.LILY_PAD.get());
		list.add(BlockRegister.FLOWER_POT.get());
		if(plant.isShroomPlant) {
			list.add(Blocks.MYCELIUM);
		}
		if(plant == Plants.CACTUS) {
			list.add(Blocks.SAND);
		}
		return list;
	}
	
	public static int getPlantLevelUpXp(Plants plant, int lvl){
		if(lvl == getPlantMaxLvl(plant)) return 999999999;
		if(lvl <= 18) {
			int now = (lvl - 1) / 2;
			return 10 + 5 * now;
		}
		return 60;
	}
	
	public static int getPlantMaxLvl(Plants plant){
		switch(plant) {
		case LILY_PAD:
		case PUMPKIN:
		case FLOWER_POT:
		case IMITATER: return 1;
		default: return MAX_PLANT_LEVEL;
		}
	}
	
	/**
	 * create plant entity by given plant 
	 */
	public static PVZPlantEntity getPlantEntity(World world, Plants plant){
		if(PLANT_ENTITY.containsKey(plant) && PLANT_ENTITY.get(plant) != null) {
			return PLANT_ENTITY.get(plant).get().create(world);
		}
		PVZMod.LOGGER.debug("plant get entity error");
		return null;
	}
	
	/**
	 * get plant entityType by given plant 
	 */
	@Nullable
	public static EntityType<? extends PVZPlantEntity> getPlantEntityType(Plants plant){
		try {
			return PLANT_ENTITY.get(plant).get();
		} catch (final NullPointerException e) {
			if(! Plants.isEntityPlant(plant)) return null;
			throw new NullPointerException("Error: plant get entityType !");
		}
	}
	
	/**
	 * get plant card sun cost
	 */
	public static int getPlantSunCost(Plants plant){
		if(PLANT_SUN_COST.containsKey(plant)) {
			return PLANT_SUN_COST.get(plant);
		}
		PVZMod.LOGGER.debug("plant get sun cost error!");
		return 9999;
	}
	
	/**
	 * get plant card CD
	 */
	public static int getPlantCoolDownTime(Plants plant,int lvl){
		if(PLANT_CD.containsKey(plant)) {
			CDs cd = PLANT_CD.get(plant);
			int max = -1, min = -1;
			switch(cd) {
			case SUPER_FAST:{
				max = 160;
				min = 120;
				break;
			}
			case HUGE_FAST:{
				max = 200;
				min = 160;
				break;
			}
			case VERY_FAST:{
				max = 240;
				min = 200;
				break;
			}
			case FAST:{
				max = 300;
				min = 240;
				break;
			}
			case LITTLE_FAST:{
				max = 400;
				min = 320;
				break;
			}
			case NORMAL:{
				max = 500;
				min = 420;
				break;
			}
			case LITTLE_SLOW:{
				max = 640;
				min = 540;
				break;
			}
			case SLOW:{
				max = 840;
				min = 720;
				break;
			}
			case VERY_SLOW:{
				max = 1200;
				min = 1040;
				break;
			}
			case HUGE_SLOW:{
				max = 2400;
				min = 2000;
				break;
			}
			case SUPER_SLOW:{
				max = 6000;
				min = 4800;
				break;
			}
			}
			if(min == -1 || max == -1 || max < min) {
				PVZMod.LOGGER.debug("ERROR CD value.");
				return 100;
			}
			if(lvl <= 19) {
				return max - (max - min) / 20 * (lvl - 1);
			}
			return min;
		}
		PVZMod.LOGGER.debug("plant get cooldown time error!");
		return 0;
	}
	
	/**
	 * get the rank of plant
	 */
	public static Ranks getPlantRankByName(Plants plant){
		if(PLANT_RANK.containsKey(plant)) {
			return PLANT_RANK.get(plant);
		}
		PVZMod.LOGGER.debug("plant get rank error!");
		return Ranks.GRAY;
	}
	
	/**
	 * get the essence type of plant
	 */
	public static Essences getPlantEssenceType(Plants plant){
		if(PLANT_ESSENCE.containsKey(plant)) {
			return PLANT_ESSENCE.get(plant);
		}
		PVZMod.LOGGER.fatal("plant get essence type error!");
		return Essences.APPEASE;
	}
	
	public static PlantCardItem getPlantSummonCard(Plants plant) {
		if(PLANT_SUMMON_CARD.containsKey(plant)) {
			return PLANT_SUMMON_CARD.get(plant).get();
		}
		PVZMod.LOGGER.debug("plant get summon card error!");
		return null;
	}
	
	public static PlantCardItem getPlantEnjoyCard(Plants plant) {
		if(PLANT_ENJOY_CARD.containsKey(plant)) {
			return PLANT_ENJOY_CARD.get(plant).get();
		}
		PVZMod.LOGGER.debug("plant get enjoy card error!");
		return null;
	}
	
	public static int getPlantAverageProgress(PVZPlantEntity plant, int from, int to) {
		return MathUtil.getProgressAverage(plant.getPlantLvl(), MAX_PLANT_LEVEL, from, to);
	}
	
	public static float getPlantAverageProgress(PVZPlantEntity plant, float from, float to) {
		return MathUtil.getProgressAverage(plant.getPlantLvl(), MAX_PLANT_LEVEL, from, to);
	}
	
	/**
	 * copy data from p1 to p2
	 */
	public static void copyPlantData(PVZPlantEntity p2, PVZPlantEntity p1) {
		p2.setPlantLvl(p1.getPlantLvl());
		p2.setCharmed(p1.isCharmed());
		p2.setOwnerUUID(p1.getOwnerUUID().orElse(null));
	}
	
}
