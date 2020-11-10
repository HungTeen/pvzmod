package com.hungteen.pvz.utils;

import java.util.HashMap;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.enums.CDs;
import com.hungteen.pvz.utils.enums.Essences;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Ranks;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;

public class PlantUtil {
	
	public static final HashMap<Plants, Integer> PLANT_SUN_COST = new HashMap<>();
	public static final HashMap<Plants, CDs> PLANT_CD = new HashMap<>();
	public static final HashMap<Plants, Essences> PLANT_ESSENCE = new HashMap<>();
	public static final HashMap<Plants, Ranks> PLANT_RANK = new HashMap<>();
	public static final HashMap<Plants, RegistryObject<? extends EntityType<? extends PVZPlantEntity>>> PLANT_ENTITY = new HashMap<>();
                                                   //1  2  3  4  5  6   7   8   9   10  11  12  13   14   15   16   17   18   19   20
	public static final int[] GRAY_XP = new int[] {0,10,15,25,40,60,100,140,200,280,400,560,800,1250,1700,2250,3000,4000,5400,7500,999999999};
	public static final int[] WHITE_XP = new int[] {0,15,25,40,60,80,130,180,240,325,450,620,880,1400,1920,2500,3400,4500,6000,8400,999999999};
	public static final int[] GREEN_XP = new int[] {0,20,35,50,75,105,175,235,300,400,540,720,1000,1600,2250,3000,4000,5200,7000,9600,999999999};
	public static final int[] BLUE_XP = new int[] {0,25,50,70,100,135,200,270,350,450,600,800,1100,1800,2500,3400,4500,6000,8000,10800,999999999};
	public static final int[] PURPLE_XP = new int[] {0,30,60,80,105,150,225,300,400,520,700,960,1300,2100,3000,4200,5600,7200,9600,12500,999999999};
	public static final int[] GOLD_XP = new int[] {0,35,60,90,120,175,255,350,450,600,800,1080,1500,2500,3600,5000,6400,8100,10800,14000,999999999};
	public static final int[] MEGA_XP = new int[] {0,40,70,100,150,210,300,400,520,700,960,1300,1800,3000,4200,6000,7800,9600,12500,16000,999999999};
	public static final int CURRENT_PLANT_NUM = 24;
	public static final float PUMPKIN_LIFE = 400;
	public static final float PUMPKIN_SUPER_LIFE = 400;
	
	static {
		//Plant sun Cost 
		PLANT_SUN_COST.put(Plants.PUFF_SHROOM, 0);
		PLANT_SUN_COST.put(Plants.POTATO_MINE, 25);
		PLANT_SUN_COST.put(Plants.LILY_PAD, 25);
		PLANT_SUN_COST.put(Plants.TANGLE_KELP, 25);
		PLANT_SUN_COST.put(Plants.SUN_SHROOM, 25);
		PLANT_SUN_COST.put(Plants.SCAREDY_SHROOM, 25);
		PLANT_SUN_COST.put(Plants.SUN_FLOWER, 50);
		PLANT_SUN_COST.put(Plants.WALL_NUT, 50);
		PLANT_SUN_COST.put(Plants.SQUASH, 50);
		PLANT_SUN_COST.put(Plants.GRAVE_BUSTER, 75);
		PLANT_SUN_COST.put(Plants.HYPNO_SHROOM, 75);
		PLANT_SUN_COST.put(Plants.ICE_SHROOM, 75);
		PLANT_SUN_COST.put(Plants.PEA_SHOOTER, 100);
		PLANT_SUN_COST.put(Plants.SPIKE_WEED, 100);
		PLANT_SUN_COST.put(Plants.FUME_SHROOM, 100);
		PLANT_SUN_COST.put(Plants.TALL_NUT, 125);
		PLANT_SUN_COST.put(Plants.DOOM_SHROOM, 125);
		PLANT_SUN_COST.put(Plants.PUMPKIN, 125);
		PLANT_SUN_COST.put(Plants.CHERRY_BOMB, 150);
		PLANT_SUN_COST.put(Plants.CHOMPER, 150);
		PLANT_SUN_COST.put(Plants.SNOW_PEA, 175);
		PLANT_SUN_COST.put(Plants.JALAPENO, 175);
		PLANT_SUN_COST.put(Plants.TORCH_WOOD, 175);
		PLANT_SUN_COST.put(Plants.REPEATER, 200);
		PLANT_SUN_COST.put(Plants.THREE_PEATER, 300);
		
		//Plant CD
		PLANT_CD.put(Plants.PUFF_SHROOM, CDs.HUGE_FAST);
		
		PLANT_CD.put(Plants.PEA_SHOOTER, CDs.VERY_FAST);
		PLANT_CD.put(Plants.GRAVE_BUSTER, CDs.VERY_FAST);
		PLANT_CD.put(Plants.SCAREDY_SHROOM, CDs.VERY_FAST);
		
		PLANT_CD.put(Plants.SUN_FLOWER, CDs.FAST);
		PLANT_CD.put(Plants.SNOW_PEA, CDs.FAST);
		PLANT_CD.put(Plants.REPEATER, CDs.FAST);
		PLANT_CD.put(Plants.SUN_SHROOM, CDs.FAST);
		
		PLANT_CD.put(Plants.THREE_PEATER, CDs.LITTLE_FAST);
		PLANT_CD.put(Plants.SPIKE_WEED, CDs.LITTLE_FAST);
		PLANT_CD.put(Plants.FUME_SHROOM, CDs.LITTLE_FAST);
		
		PLANT_CD.put(Plants.CHOMPER, CDs.NORMAL);
		PLANT_CD.put(Plants.TORCH_WOOD, CDs.NORMAL);
		
		PLANT_CD.put(Plants.POTATO_MINE, CDs.LITTLE_SLOW);
		PLANT_CD.put(Plants.SQUASH, CDs.LITTLE_SLOW);
		PLANT_CD.put(Plants.HYPNO_SHROOM, CDs.LITTLE_SLOW);
		
		PLANT_CD.put(Plants.WALL_NUT, CDs.SLOW);
		PLANT_CD.put(Plants.TANGLE_KELP, CDs.SLOW);
		PLANT_CD.put(Plants.ICE_SHROOM, CDs.SLOW);
		PLANT_CD.put(Plants.PUMPKIN, CDs.SLOW);
		
		PLANT_CD.put(Plants.TALL_NUT, CDs.VERY_SLOW);
		
		PLANT_CD.put(Plants.CHERRY_BOMB, CDs.HUGE_SLOW);
		PLANT_CD.put(Plants.JALAPENO, CDs.HUGE_SLOW);
		
		PLANT_CD.put(Plants.DOOM_SHROOM, CDs.SUPER_SLOW);
		
		PLANT_CD.put(Plants.LILY_PAD, CDs.OTHER);
		
		//Plant rank
		PLANT_RANK.put(Plants.PEA_SHOOTER, Ranks.GRAY);
		PLANT_RANK.put(Plants.SUN_FLOWER, Ranks.GRAY);
		PLANT_RANK.put(Plants.LILY_PAD, Ranks.GRAY);
		PLANT_RANK.put(Plants.SCAREDY_SHROOM, Ranks.GRAY);
		
		PLANT_RANK.put(Plants.WALL_NUT, Ranks.WHITE);
		PLANT_RANK.put(Plants.POTATO_MINE, Ranks.WHITE);
		PLANT_RANK.put(Plants.TANGLE_KELP, Ranks.WHITE);
		PLANT_RANK.put(Plants.SPIKE_WEED, Ranks.WHITE);
		PLANT_RANK.put(Plants.PUFF_SHROOM, Ranks.WHITE);
		PLANT_RANK.put(Plants.SUN_SHROOM, Ranks.WHITE);
		PLANT_RANK.put(Plants.GRAVE_BUSTER, Ranks.WHITE);
		
		PLANT_RANK.put(Plants.SNOW_PEA, Ranks.GREEN);
		PLANT_RANK.put(Plants.REPEATER, Ranks.GREEN);
		PLANT_RANK.put(Plants.SQUASH, Ranks.GREEN);
		PLANT_RANK.put(Plants.TORCH_WOOD, Ranks.GREEN);
		PLANT_RANK.put(Plants.FUME_SHROOM, Ranks.GREEN);
		PLANT_RANK.put(Plants.PUMPKIN, Ranks.GREEN);
//		PLANT_RANK.put(Plants.COFFEE_BEAN, Ranks.GREEN);
		
		PLANT_RANK.put(Plants.CHERRY_BOMB, Ranks.BLUE);
		PLANT_RANK.put(Plants.CHOMPER, Ranks.BLUE);
		PLANT_RANK.put(Plants.THREE_PEATER, Ranks.BLUE);
		PLANT_RANK.put(Plants.JALAPENO, Ranks.BLUE);
		PLANT_RANK.put(Plants.TALL_NUT, Ranks.BLUE);
		PLANT_RANK.put(Plants.HYPNO_SHROOM, Ranks.BLUE);
		PLANT_RANK.put(Plants.ICE_SHROOM, Ranks.BLUE);
		
		PLANT_RANK.put(Plants.GATLING_PEA, Ranks.PURPLE);
		
		PLANT_RANK.put(Plants.DOOM_SHROOM, Ranks.GOLD);
		
		//Plant essence
		PLANT_ESSENCE.put(Plants.PEA_SHOOTER, Essences.APPEASE);
		PLANT_ESSENCE.put(Plants.REPEATER, Essences.APPEASE);
		PLANT_ESSENCE.put(Plants.THREE_PEATER, Essences.APPEASE);
		PLANT_ESSENCE.put(Plants.SUN_FLOWER, Essences.LIGHT);
		PLANT_ESSENCE.put(Plants.SUN_SHROOM, Essences.LIGHT);
		PLANT_ESSENCE.put(Plants.CHERRY_BOMB, Essences.EXPLOSION);
		PLANT_ESSENCE.put(Plants.POTATO_MINE, Essences.EXPLOSION);
		PLANT_ESSENCE.put(Plants.WALL_NUT, Essences.DEFENCE);
		PLANT_ESSENCE.put(Plants.TALL_NUT, Essences.DEFENCE);
		PLANT_ESSENCE.put(Plants.PUMPKIN, Essences.DEFENCE);
		PLANT_ESSENCE.put(Plants.SNOW_PEA, Essences.ICE);
		PLANT_ESSENCE.put(Plants.ICE_SHROOM, Essences.ICE);
		PLANT_ESSENCE.put(Plants.CHOMPER, Essences.ENFORCE);
		PLANT_ESSENCE.put(Plants.SQUASH, Essences.ENFORCE);
		PLANT_ESSENCE.put(Plants.TANGLE_KELP, Essences.ENFORCE);
		PLANT_ESSENCE.put(Plants.PUFF_SHROOM, Essences.TOXIC);
		PLANT_ESSENCE.put(Plants.FUME_SHROOM, Essences.TOXIC);
		PLANT_ESSENCE.put(Plants.SCAREDY_SHROOM, Essences.TOXIC);
		PLANT_ESSENCE.put(Plants.GRAVE_BUSTER, Essences.ASSIST);
		PLANT_ESSENCE.put(Plants.LILY_PAD, Essences.ASSIST);
		PLANT_ESSENCE.put(Plants.HYPNO_SHROOM, Essences.MAGIC);
		PLANT_ESSENCE.put(Plants.JALAPENO, Essences.FLAME);
		PLANT_ESSENCE.put(Plants.TORCH_WOOD, Essences.FLAME);
		PLANT_ESSENCE.put(Plants.SPIKE_WEED, Essences.SPEAR);
		
		//plant entity
		PLANT_ENTITY.put(Plants.PEA_SHOOTER, EntityRegister.PEA_SHOOTER);
		PLANT_ENTITY.put(Plants.SUN_FLOWER, EntityRegister.SUN_FLOWER);
		PLANT_ENTITY.put(Plants.CHERRY_BOMB, EntityRegister.CHERRY_BOMB);
		PLANT_ENTITY.put(Plants.WALL_NUT, EntityRegister.WALL_NUT);
		PLANT_ENTITY.put(Plants.POTATO_MINE, EntityRegister.POTATO_MINE);
		PLANT_ENTITY.put(Plants.SNOW_PEA, EntityRegister.SNOW_PEA);
		PLANT_ENTITY.put(Plants.CHOMPER, EntityRegister.CHOMPER);
		PLANT_ENTITY.put(Plants.REPEATER, EntityRegister.REPEATER);
		PLANT_ENTITY.put(Plants.PUFF_SHROOM, EntityRegister.PUFF_SHROOM);
		PLANT_ENTITY.put(Plants.SUN_SHROOM, EntityRegister.SUN_SHROOM);
		PLANT_ENTITY.put(Plants.FUME_SHROOM, EntityRegister.FUME_SHROOM);
		PLANT_ENTITY.put(Plants.GRAVE_BUSTER, EntityRegister.GRAVE_BUSTER);
		PLANT_ENTITY.put(Plants.HYPNO_SHROOM, EntityRegister.HYPNO_SHROOM);
		PLANT_ENTITY.put(Plants.SCAREDY_SHROOM, EntityRegister.SCAREDY_SHROOM);
		PLANT_ENTITY.put(Plants.ICE_SHROOM, EntityRegister.ICE_SHROOM);
		PLANT_ENTITY.put(Plants.DOOM_SHROOM, EntityRegister.DOOM_SHROOM);
		PLANT_ENTITY.put(Plants.SQUASH, EntityRegister.SQUASH);
		PLANT_ENTITY.put(Plants.THREE_PEATER, EntityRegister.THREE_PEATER);
		PLANT_ENTITY.put(Plants.TANGLE_KELP, EntityRegister.TANGLE_KELP);
		PLANT_ENTITY.put(Plants.JALAPENO, EntityRegister.JALAPENO);
		PLANT_ENTITY.put(Plants.SPIKE_WEED, EntityRegister.SPIKE_WEED);
		PLANT_ENTITY.put(Plants.TORCH_WOOD, EntityRegister.TORCH_WOOD);
		PLANT_ENTITY.put(Plants.TALL_NUT, EntityRegister.TALL_NUT);
		PLANT_ENTITY.put(Plants.COFFEE_BEAN, EntityRegister.COFFEE_BEAN);
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
//		switch(plant) {
//		case PEA_SHOOTER:return EntityRegister.PEA_SHOOTER.get().create(world);
//		case SUN_FLOWER:return EntityRegister.SUN_FLOWER.get().create(world);
//		case CHERRY_BOMB:return EntityRegister.CHERRY_BOMB.get().create(world);
//		case WALL_NUT:return EntityRegister.WALL_NUT.get().create(world);
//		case POTATO_MINE:return EntityRegister.POTATO_MINE.get().create(world);
//		case SNOW_PEA:return EntityRegister.SNOW_PEA.get().create(world);
//		case CHOMPER:return EntityRegister.CHOMPER.get().create(world);
//		case REPEATER:return EntityRegister.REPEATER.get().create(world);
//		case SQUASH:return EntityRegister.SQUASH.get().create(world);
//		case THREE_PEATER:return EntityRegister.THREE_PEATER.get().create(world);
//		case TANGLE_KELP:return EntityRegister.TANGLE_KELP.get().create(world);
//		case JALAPENO:return EntityRegister.JALAPENO.get().create(world);
//		case SPIKE_WEED:return EntityRegister.SPIKE_WEED.get().create(world);
//		case TORCH_WOOD:return EntityRegister.TORCH_WOOD.get().create(world);
//		case TALL_NUT:return EntityRegister.TALL_NUT.get().create(world);
////		case WATER_GUARD:return EntityRegister.WATER_GUARD.get().create(world);
//		case PUFF_SHROOM:return EntityRegister.PUFF_SHROOM.get().create(world);
//		case SUN_SHROOM:return EntityRegister.SUN_SHROOM.get().create(world);
//		case FUME_SHROOM:return EntityRegister.FUME_SHROOM.get().create(world);
//		case GRAVE_BUSTER:return EntityRegister.GRAVE_BUSTER.get().create(world);
//		case HYPNO_SHROOM:return EntityRegister.HYPNO_SHROOM.get().create(world);
//		case SCAREDY_SHROOM:return EntityRegister.SCAREDY_SHROOM.get().create(world);
//		case ICE_SHROOM:return EntityRegister.ICE_SHROOM.get().create(world);
//		case DOOM_SHROOM:return EntityRegister.DOOM_SHROOM.get().create(world);
//		default:{
//			PVZMod.LOGGER.debug("No such plant entity!");
//			return null;
//		}
//		}
		if(PLANT_ENTITY.containsKey(plant)) {
			return PLANT_ENTITY.get(plant).get().create(world);
		}
		PVZMod.LOGGER.debug("plant get entity error");
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
