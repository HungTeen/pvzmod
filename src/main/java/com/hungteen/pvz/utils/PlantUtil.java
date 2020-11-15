package com.hungteen.pvz.utils;

import java.util.EnumMap;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.enums.CDs;
import com.hungteen.pvz.utils.enums.Essences;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Ranks;

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
	public static final int[] GRAY_XP = new int[] {0,10,15,25,40,60,100,140,200,280,400,560,800,1250,1700,2250,3000,4000,5400,7500,999999999};
	public static final int[] WHITE_XP = new int[] {0,15,25,40,60,80,130,180,240,325,450,620,880,1400,1920,2500,3400,4500,6000,8400,999999999};
	public static final int[] GREEN_XP = new int[] {0,20,35,50,75,105,175,235,300,400,540,720,1000,1600,2250,3000,4000,5200,7000,9600,999999999};
	public static final int[] BLUE_XP = new int[] {0,25,50,70,100,135,200,270,350,450,600,800,1100,1800,2500,3400,4500,6000,8000,10800,999999999};
	public static final int[] PURPLE_XP = new int[] {0,30,60,80,105,150,225,300,400,520,700,960,1300,2100,3000,4200,5600,7200,9600,12500,999999999};
	public static final int[] GOLD_XP = new int[] {0,35,60,90,120,175,255,350,450,600,800,1080,1500,2500,3600,5000,6400,8100,10800,14000,999999999};
	public static final int[] MEGA_XP = new int[] {0,40,70,100,150,210,300,400,520,700,960,1300,1800,3000,4200,6000,7800,9600,12500,16000,999999999};
	public static int CURRENT_PLANT_NUM = 0;
	public static final float PUMPKIN_LIFE = 400;
	public static final float PUMPKIN_SUPER_LIFE = 400;
	
	static {
		putPlantInfoToMap(Plants.PEA_SHOOTER,100, CDs.VERY_FAST, Ranks.GRAY, Essences.APPEASE, EntityRegister.PEA_SHOOTER, ItemRegister.PEA_SHOOTER_CARD, ItemRegister.PEA_SHOOTER_ENJOY_CARD);
		putPlantInfoToMap(Plants.SUN_FLOWER,50, CDs.FAST, Ranks.GRAY, Essences.LIGHT, EntityRegister.SUN_FLOWER, ItemRegister.SUN_FLOWER_CARD, ItemRegister.SUN_FLOWER_ENJOY_CARD);
		putPlantInfoToMap(Plants.CHERRY_BOMB,150, CDs.HUGE_SLOW, Ranks.BLUE, Essences.EXPLOSION, EntityRegister.CHERRY_BOMB, ItemRegister.CHERRY_BOMB_CARD, ItemRegister.CHERRY_BOMB_ENJOY_CARD);
		putPlantInfoToMap(Plants.WALL_NUT,50, CDs.SLOW, Ranks.WHITE, Essences.DEFENCE, EntityRegister.WALL_NUT, ItemRegister.WALL_NUT_CARD, ItemRegister.WALL_NUT_ENJOY_CARD);
		putPlantInfoToMap(Plants.POTATO_MINE,25, CDs.LITTLE_SLOW, Ranks.WHITE, Essences.EXPLOSION, EntityRegister.POTATO_MINE, ItemRegister.POTATO_MINE_CARD, ItemRegister.POTATO_MINE_ENJOY_CARD);
		putPlantInfoToMap(Plants.SNOW_PEA,175, CDs.FAST, Ranks.GREEN, Essences.ICE, EntityRegister.SNOW_PEA, ItemRegister.SNOW_PEA_CARD, ItemRegister.SNOW_PEA_ENJOY_CARD);
		putPlantInfoToMap(Plants.CHOMPER,150, CDs.NORMAL, Ranks.BLUE, Essences.ENFORCE, EntityRegister.CHOMPER, ItemRegister.CHOMPER_CARD, ItemRegister.CHOMPER_ENJOY_CARD);
		putPlantInfoToMap(Plants.REPEATER,200, CDs.FAST, Ranks.GREEN, Essences.APPEASE, EntityRegister.REPEATER, ItemRegister.REPEATER_CARD, ItemRegister.REPEATER_ENJOY_CARD);
		putPlantInfoToMap(Plants.PUFF_SHROOM,0, CDs.HUGE_FAST, Ranks.WHITE, Essences.TOXIC, EntityRegister.PUFF_SHROOM, ItemRegister.PUFF_SHROOM_CARD, ItemRegister.PUFF_SHROOM_ENJOY_CARD);
		putPlantInfoToMap(Plants.SUN_SHROOM,25, CDs.FAST, Ranks.WHITE, Essences.LIGHT, EntityRegister.SUN_SHROOM, ItemRegister.SUN_SHROOM_CARD, ItemRegister.SUN_SHROOM_ENJOY_CARD);
		putPlantInfoToMap(Plants.FUME_SHROOM,100, CDs.LITTLE_FAST, Ranks.GREEN, Essences.TOXIC, EntityRegister.FUME_SHROOM, ItemRegister.FUME_SHROOM_CARD, ItemRegister.FUME_SHROOM_ENJOY_CARD);
		putPlantInfoToMap(Plants.GRAVE_BUSTER,75, CDs.VERY_FAST, Ranks.WHITE, Essences.ASSIST, EntityRegister.GRAVE_BUSTER, ItemRegister.GRAVE_BUSTER_CARD, ItemRegister.GRAVE_BUSTER_ENJOY_CARD);
		putPlantInfoToMap(Plants.HYPNO_SHROOM,75, CDs.LITTLE_SLOW, Ranks.BLUE, Essences.MAGIC, EntityRegister.HYPNO_SHROOM, ItemRegister.HYPNO_SHROOM_CARD, ItemRegister.HYPNO_SHROOM_ENJOY_CARD);
		putPlantInfoToMap(Plants.SCAREDY_SHROOM,25, CDs.VERY_FAST, Ranks.GRAY, Essences.TOXIC, EntityRegister.SCAREDY_SHROOM, ItemRegister.SCAREDY_SHROOM_CARD, ItemRegister.SCAREDY_SHROOM_ENJOY_CARD);
		putPlantInfoToMap(Plants.ICE_SHROOM,75, CDs.SLOW, Ranks.BLUE, Essences.ICE, EntityRegister.ICE_SHROOM, ItemRegister.ICE_SHROOM_CARD, ItemRegister.ICE_SHROOM_ENJOY_CARD);
		putPlantInfoToMap(Plants.DOOM_SHROOM,125, CDs.SUPER_SLOW, Ranks.GOLD, Essences.EXPLOSION, EntityRegister.DOOM_SHROOM, ItemRegister.DOOM_SHROOM_CARD, ItemRegister.DOOM_SHROOM_ENJOY_CARD);
		putPlantInfoToMap(Plants.LILY_PAD,25, CDs.OTHER, Ranks.GRAY, Essences.ASSIST, null, ItemRegister.LILY_PAD_CARD, ItemRegister.LILY_PAD_ENJOY_CARD);
		putPlantInfoToMap(Plants.SQUASH,50, CDs.LITTLE_SLOW, Ranks.GREEN, Essences.ENFORCE, EntityRegister.SQUASH, ItemRegister.SQUASH_CARD, ItemRegister.SQUASH_ENJOY_CARD);
		putPlantInfoToMap(Plants.THREE_PEATER,300, CDs.LITTLE_FAST, Ranks.BLUE, Essences.APPEASE, EntityRegister.THREE_PEATER, ItemRegister.THREE_PEATER_CARD, ItemRegister.THREE_PEATER_ENJOY_CARD);
		putPlantInfoToMap(Plants.TANGLE_KELP,25, CDs.SLOW, Ranks.WHITE, Essences.ENFORCE, EntityRegister.TANGLE_KELP, ItemRegister.TANGLE_KELP_CARD, ItemRegister.TANGLE_KELP_ENJOY_CARD);
		putPlantInfoToMap(Plants.JALAPENO,175, CDs.HUGE_SLOW, Ranks.BLUE, Essences.FLAME, EntityRegister.JALAPENO, ItemRegister.JALAPENO_CARD, ItemRegister.JALAPENO_ENJOY_CARD);
		putPlantInfoToMap(Plants.SPIKE_WEED,100, CDs.LITTLE_FAST, Ranks.WHITE, Essences.SPEAR, EntityRegister.SPIKE_WEED, ItemRegister.SPIKE_WEED_CARD, ItemRegister.SPIKE_WEED_ENJOY_CARD);
		putPlantInfoToMap(Plants.TORCH_WOOD,175, CDs.NORMAL, Ranks.GREEN, Essences.FLAME, EntityRegister.TORCH_WOOD, ItemRegister.TORCH_WOOD_CARD, ItemRegister.TORCH_WOOD_ENJOY_CARD);
		putPlantInfoToMap(Plants.TALL_NUT,125, CDs.VERY_SLOW, Ranks.BLUE, Essences.DEFENCE, EntityRegister.TALL_NUT, ItemRegister.TALL_NUT_CARD, ItemRegister.TALL_NUT_ENJOY_CARD);
		putPlantInfoToMap(Plants.PUMPKIN,125, CDs.SLOW, Ranks.GREEN, Essences.DEFENCE, null, ItemRegister.PUMPKIN_CARD, ItemRegister.PUMPKIN_ENJOY_CARD);
		putPlantInfoToMap(Plants.COFFEE_BEAN,75, CDs.FAST, Ranks.GREEN, Essences.MAGIC, EntityRegister.COFFEE_BEAN, ItemRegister.COFFEE_BEAN_CARD, ItemRegister.COFFEE_BEAN_ENJOY_CARD);
//		putPlantInfoToMap(Plants.PEA_SHOOTER, 100, CDs.VERY_FAST, Ranks.GRAY, Essences.APPEASE, EntityRegister.PEA_SHOOTER);
//		putPlantInfoToMap(Plants.SUN_FLOWER, 50, CDs.FAST, Ranks.GRAY, Essences.LIGHT, EntityRegister.SUN_FLOWER);
//		putPlantInfoToMap(Plants.CHERRY_BOMB, 150, CDs.HUGE_SLOW, Ranks.BLUE, Essences.EXPLOSION, EntityRegister.CHERRY_BOMB);
//		putPlantInfoToMap(Plants.WALL_NUT, 50, CDs.SLOW, Ranks.WHITE, Essences.DEFENCE, EntityRegister.WALL_NUT);
//		putPlantInfoToMap(Plants.POTATO_MINE, 25, CDs.LITTLE_SLOW, Ranks.WHITE, Essences.EXPLOSION, EntityRegister.POTATO_MINE);
//		putPlantInfoToMap(Plants.SNOW_PEA, 175, CDs.FAST, Ranks.GREEN, Essences.ICE, EntityRegister.SNOW_PEA);
//		putPlantInfoToMap(Plants.CHOMPER, 150, CDs.NORMAL, Ranks.BLUE, Essences.ENFORCE, EntityRegister.CHOMPER);
//		putPlantInfoToMap(Plants.REPEATER, 200, CDs.FAST, Ranks.GREEN, Essences.APPEASE, EntityRegister.REPEATER);
//		putPlantInfoToMap(Plants.PUFF_SHROOM, 0, CDs.HUGE_FAST, Ranks.WHITE, Essences.TOXIC, EntityRegister.PUFF_SHROOM);
//		putPlantInfoToMap(Plants.SUN_SHROOM, 25, CDs.FAST, Ranks.WHITE, Essences.LIGHT, EntityRegister.SUN_SHROOM);
//		putPlantInfoToMap(Plants.FUME_SHROOM, 100, CDs.LITTLE_FAST, Ranks.GREEN, Essences.TOXIC, EntityRegister.FUME_SHROOM);
//		putPlantInfoToMap(Plants.GRAVE_BUSTER, 75, CDs.VERY_FAST, Ranks.WHITE, Essences.ASSIST, EntityRegister.GRAVE_BUSTER);
//		putPlantInfoToMap(Plants.HYPNO_SHROOM, 75, CDs.LITTLE_SLOW, Ranks.BLUE, Essences.MAGIC, EntityRegister.HYPNO_SHROOM);
//		putPlantInfoToMap(Plants.SCAREDY_SHROOM, 25, CDs.VERY_FAST, Ranks.GRAY, Essences.TOXIC, EntityRegister.SCAREDY_SHROOM);
//		putPlantInfoToMap(Plants.ICE_SHROOM, 75, CDs.SLOW, Ranks.BLUE, Essences.ICE, EntityRegister.ICE_SHROOM);
//		putPlantInfoToMap(Plants.DOOM_SHROOM, 125, CDs.SUPER_SLOW, Ranks.GOLD, Essences.EXPLOSION, EntityRegister.DOOM_SHROOM);
//		putPlantInfoToMap(Plants.LILY_PAD, 25, CDs.OTHER, Ranks.GRAY, Essences.ASSIST, null);
//		putPlantInfoToMap(Plants.SQUASH, 50, CDs.LITTLE_SLOW, Ranks.GREEN, Essences.ENFORCE, EntityRegister.SQUASH);
//		putPlantInfoToMap(Plants.THREE_PEATER, 300, CDs.LITTLE_FAST, Ranks.BLUE, Essences.APPEASE, EntityRegister.THREE_PEATER);
//		putPlantInfoToMap(Plants.TANGLE_KELP, 25, CDs.SLOW, Ranks.WHITE, Essences.ENFORCE, EntityRegister.TANGLE_KELP);
//		putPlantInfoToMap(Plants.JALAPENO, 175, CDs.HUGE_SLOW, Ranks.BLUE, Essences.FLAME, EntityRegister.JALAPENO);
//		putPlantInfoToMap(Plants.SPIKE_WEED, 100, CDs.LITTLE_FAST, Ranks.WHITE, Essences.SPEAR, EntityRegister.SPIKE_WEED);
//		putPlantInfoToMap(Plants.TORCH_WOOD, 175, CDs.NORMAL, Ranks.GREEN, Essences.FLAME, EntityRegister.TORCH_WOOD);
//		putPlantInfoToMap(Plants.TALL_NUT, 125, CDs.VERY_SLOW, Ranks.BLUE, Essences.DEFENCE, EntityRegister.TALL_NUT);
//		putPlantInfoToMap(Plants.PUMPKIN, 125, CDs.SLOW, Ranks.GREEN, Essences.DEFENCE, null);
//		putPlantInfoToMap(Plants.COFFEE_BEAN, 75, CDs.FAST, Ranks.GREEN, Essences.MAGIC, EntityRegister.COFFEE_BEAN);
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
	
	public static void putPlantInfoToMap1(Plants plant,final int cost, CDs cd, Ranks rank, Essences essence, RegistryObject<? extends EntityType<? extends PVZPlantEntity>> type) {
		PLANT_SUN_COST.put(plant, cost);
		PLANT_CD.put(plant, cd);
		PLANT_RANK.put(plant, rank);
		PLANT_ESSENCE.put(plant, essence);
		PLANT_ENTITY.put(plant, type);
		++ CURRENT_PLANT_NUM;
	}
	
	public static int getPlantLevelUpXp(Plants plant,int lvl){
		Ranks rank=getPlantRankByName(plant);
		if(lvl==getPlantMaxLvl(plant)) return 999999999;
		switch(rank) {
		case GRAY:{
			if(lvl>=GRAY_XP.length) return 999999999;
			return GRAY_XP[lvl];
		}
		case WHITE:{
			if(lvl>=WHITE_XP.length) return 999999999;
			return WHITE_XP[lvl];
		}
		case GREEN:{
			if(lvl>=GREEN_XP.length) return 999999999;
			return GREEN_XP[lvl];
		}
		case BLUE:{
			if(lvl>=BLUE_XP.length) return 999999999;
			return BLUE_XP[lvl];
		}
		case PURPLE:{
			if(lvl>=PURPLE_XP.length) return 999999999;
			return PURPLE_XP[lvl];
		}
		case GOLD:{
			if(lvl>=GOLD_XP.length) return 999999999;
			return GOLD_XP[lvl];
		}
		default:{
			PVZMod.LOGGER.debug("plant get level up xp error!");
			return 0;
		}
		}
	}
	
	public static int getPlantMaxLvl(Plants plant){
		switch(plant) {
		case LILY_PAD:return 1;
		case PUMPKIN:return 1;
		default:return 20;
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
	public static EntityType<? extends PVZPlantEntity> getPlantEntityType(Plants plant){
		if(PLANT_ENTITY.containsKey(plant) && PLANT_ENTITY.get(plant) != null) {
			return PLANT_ENTITY.get(plant).get();
		}
		PVZMod.LOGGER.debug("plant get entityType error");
		return null;
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
			switch(cd) {
			case HUGE_FAST:return getPlantCoolDownTimeHugeFast(lvl);
			case VERY_FAST:return getPlantCoolDownTimeVeryFast(lvl);
			case FAST:return getPlantCoolDownTimeFast(lvl);
			case LITTLE_FAST:return getPlantCoolDownTimeLittleFast(lvl);
			case NORMAL:return getPlantCoolDownTimeNormal(lvl);
			case LITTLE_SLOW:return getPlantCoolDownTimeLittleSlow(lvl);
			case SLOW:return getPlantCoolDownTimeSlow(lvl);
			case VERY_SLOW:return getPlantCoolDownTimeVerySlow(lvl);
			case HUGE_SLOW:return getPlantCoolDownTimeHugeSlow(lvl);
			case SUPER_SLOW:return getPlantCoolDownTimeSuperSlow(lvl);
			case OTHER:{// 8s 
				return 160;
			}
			}
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
		PVZMod.LOGGER.debug("plant get essence type error!");
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
	
	/**
	 * copy data from p1 to p2
	 */
	public static void copyPlantData(PVZPlantEntity p2,PVZPlantEntity p1) {
		p2.setPlantLvl(p1.getPlantLvl());
		p2.setCharmed(p1.isCharmed());
		p2.setOwnerUUID(p1.getOwnerUUID());
	}
	
	/**
	 * 8s-5s
	 */
	public static int getPlantCoolDownTimeHugeFast(int lvl){
		//8 7 6 5
		if(lvl<=20) {
			int now=(lvl-1)/5;
			return 160-20*now;
		}
		return 160;
	}
	
	/**
	 * peashooter 10s-8s
	 */
	public static int getPlantCoolDownTimeVeryFast(int lvl){
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 200-10*now;
		}
		return 200;
	}
	
	/**
	 * sunflower 15s-12s
	 */
	public static int getPlantCoolDownTimeFast(int lvl){
		if(lvl<=20) {
			int now=(lvl-1)/5;
			return 300-20*now;
		}
		return 300;
	}
	
	/**
	 * threepeater 20s-16s
	 */
	public static int getPlantCoolDownTimeLittleFast(int lvl){
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 400-20*now;
		}
		return 400;
	}
	
	/**
	 * torch wood 25s-21s
	 */
	public static int getPlantCoolDownTimeNormal(int lvl){
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 500-20*now;
		}
		return 500;
	}
	
	/**
	 * jalapeno 32s-28s
	 */
	public static int getPlantCoolDownTimeLittleSlow(int lvl){
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 640-20*now;
		}
		return 640;
	}
	
	/**
	 * wall nut 40s-32s
	 */
	public static int getPlantCoolDownTimeSlow(int lvl){
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 800-40*now;
		}
		return 800;
	}
	
	/**
	 * advance plant 60s-50s
	 */
	public static int getPlantCoolDownTimeVerySlow(int lvl){
		if(lvl<=18) {
			int now=(lvl-1)/2;
			return 1200-20*now;
		}
		else if(lvl<=20) return 1000;
		return 1200;
	}
	
	/**
	 * explosion plant 120s-100s 
	 */
	public static int getPlantCoolDownTimeHugeSlow(int lvl){
		if(lvl <= 20) {
			int now = (lvl - 1) / 4;
			return 2400 - 100*now;
		}
		return 2400;
	}
	
	/**
	 * doom shroom 300s - 240s
	 */
	public static int getPlantCoolDownTimeSuperSlow(int lvl){
		if(lvl <= 20) {
			int now = (lvl - 1) / 4;
			return 6000 - 300 * now;
		}
		return 6000;
	}
	
}
