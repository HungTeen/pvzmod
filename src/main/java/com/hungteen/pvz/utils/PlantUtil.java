package com.hungteen.pvz.utils;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.enums.Essences;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Ranks;

import net.minecraft.world.World;

public class PlantUtil {
                                                   //1  2  3  4  5  6   7   8   9   10  11  12  13   14   15   16   17   18   19   20
	public static final int[] GRAY_XP = new int[] {0,10,15,25,40,60,100,140,200,280,400,560,800,1250,1700,2250,3000,4000,5400,7500,999999999};
	public static final int[] WHITE_XP = new int[] {0,15,25,40,60,80,130,180,240,325,450,620,880,1400,1920,2500,3400,4500,6000,8400,999999999};
	public static final int[] GREEN_XP = new int[] {0,20,35,50,75,105,175,235,300,400,540,720,1000,1600,2250,3000,4000,5200,7000,9600,999999999};
	public static final int[] BLUE_XP = new int[] {0,25,50,70,100,135,200,270,350,450,600,800,1100,1800,2500,3400,4500,6000,8000,10800,999999999};
	public static final int[] PURPLE_XP = new int[] {0,30,60,80,105,150,225,300,400,520,700,960,1300,2100,3000,4200,5600,7200,9600,12500,999999999};
	public static final int[] GOLD_XP = new int[] {0,35,60,90,120,175,255,350,450,600,800,1080,1500,2500,3600,5000,6400,8100,10800,14000,999999999};
	public static final int[] MEGA_XP = new int[] {0,40,70,100,150,210,300,400,520,700,960,1300,1800,3000,4200,6000,7800,9600,12500,16000,999999999};
	public static final int CURRENT_PLANT_NUM = 16;
	public static final Plants [] WATER_PLANTS = new Plants[] {
			Plants.TANGLE_KELP,
			Plants.WATER_GUARD,
	};
	
	
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
		default:return 20;
		}
	}
	
	/**
	 * create plant entity by given plant 
	 */
	public static PVZPlantEntity getPlantEntity(World world, Plants plant){
		switch(plant) {
		case PEA_SHOOTER:return EntityRegister.PEA_SHOOTER.get().create(world);
		case SUN_FLOWER:return EntityRegister.SUN_FLOWER.get().create(world);
		case CHERRY_BOMB:return EntityRegister.CHERRY_BOMB.get().create(world);
		case WALL_NUT:return EntityRegister.WALL_NUT.get().create(world);
		case POTATO_MINE:return EntityRegister.POTATO_MINE.get().create(world);
		case SNOW_PEA:return EntityRegister.SNOW_PEA.get().create(world);
		case CHOMPER:return EntityRegister.CHOMPER.get().create(world);
		case REPEATER:return EntityRegister.REPEATER.get().create(world);
		case SQUASH:return EntityRegister.SQUASH.get().create(world);
		case THREE_PEATER:return EntityRegister.THREE_PEATER.get().create(world);
		case TANGLE_KELP:return EntityRegister.TANGLE_KELP.get().create(world);
		case JALAPENO:return EntityRegister.JALAPENO.get().create(world);
		case SPIKE_WEED:return EntityRegister.SPIKE_WEED.get().create(world);
		case TORCH_WOOD:return EntityRegister.TORCH_WOOD.get().create(world);
		case TALL_NUT:return EntityRegister.TALL_NUT.get().create(world);
//		case WATER_GUARD:return EntityRegister.WATER_GUARD.get().create(world);
		case PUFF_SHROOM:return EntityRegister.PUFF_SHROOM.get().create(world);
		default:{
			PVZMod.LOGGER.debug("No such plant entity!");
			return null;
		}
		}
	}
	
	/**
	 * get plant card sun cost
	 */
	public static int getPlantSunCost(Plants plant){
		switch(plant) {
		case PUFF_SHROOM:return 0;
		case POTATO_MINE:
		case LILY_PAD:
		case TANGLE_KELP:return 25;
		case SUN_FLOWER:
		case WALL_NUT:
		case SQUASH:return 50;
		case WATER_GUARD:return 75;
		case PEA_SHOOTER:
		case SPIKE_WEED:return 100;
		case TALL_NUT:return 125;
		case CHERRY_BOMB:
		case CHOMPER:return 150;
		case SNOW_PEA:
		case JALAPENO:
		case TORCH_WOOD:return 175;
		case REPEATER:return 200;
		case THREE_PEATER:return 325;
		default:{
			PVZMod.LOGGER.debug("plant get sun cost error!");
			return 0;
		}
		}
	}
	
	/**
	 * get plant card CD
	 */
	public static int getPlantCoolDownTime(Plants plant,int lvl){
		switch(plant) {
		case PUFF_SHROOM:return getPlantCoolDownTimeHugeFast(lvl);
		case PEA_SHOOTER:return getPlantCoolDownTimeVeryFast(lvl);
		case SUN_FLOWER:
		case SNOW_PEA:
		case REPEATER:return getPlantCoolDownTimeFast(lvl);
		case THREE_PEATER:
		case SPIKE_WEED:return getPlantCoolDownTimeLittleFast(lvl);
		case CHOMPER:
		case TORCH_WOOD:return getPlantCoolDownTimeNormal(lvl);
		case POTATO_MINE:
		case SQUASH:
		case WATER_GUARD:return getPlantCoolDownTimeLittleSlow(lvl);
		case WALL_NUT:
		case TANGLE_KELP:return getPlantCoolDownTimeSlow(lvl);
		case TALL_NUT:return getPlantCoolDownTimeVerySlow(lvl);
		case CHERRY_BOMB:
		case JALAPENO:return getPlantCoolDownTimeHugeSlow(lvl);
		case LILY_PAD:return 100;
		default:{
			PVZMod.LOGGER.debug("plant get cooldown time error!");
			return 0;
		}
		}
	}
	
	public static Ranks getPlantRankByName(Plants plant){
		switch(plant) {
		case PEA_SHOOTER:
		case SUN_FLOWER:
		case LILY_PAD:return Ranks.GRAY;
		case WALL_NUT:
		case POTATO_MINE:
		case TANGLE_KELP:
		case SPIKE_WEED:
		case PUFF_SHROOM:return Ranks.WHITE;
		case SNOW_PEA:
		case CHOMPER:
		case REPEATER:
		case SQUASH:
		case TORCH_WOOD:return Ranks.GREEN;
		case CHERRY_BOMB:
		case THREE_PEATER:
		case JALAPENO:
		case TALL_NUT:return Ranks.BLUE;
		default:{
			PVZMod.LOGGER.debug("plant get rank error!");
			return null;
		}
		}
	}
	
	public static Essences getPlantEssenceType(Plants plant){
		switch (plant) {
		case PEA_SHOOTER:
		case REPEATER:
		case THREE_PEATER:return Essences.APPEASE;
		case SUN_FLOWER:return Essences.LIGHT;
		case CHERRY_BOMB:
		case POTATO_MINE:return Essences.EXPLOSION;
		case WALL_NUT:
		case TALL_NUT:return Essences.DEFENCE;
		case SNOW_PEA:return Essences.ICE;
		case CHOMPER:
		case SQUASH:
		case TANGLE_KELP:return Essences.ENFORCE;
		case LILY_PAD:return Essences.ASSIST;
		case JALAPENO:
		case TORCH_WOOD:return Essences.FLAME;
		case SPIKE_WEED:return Essences.SPEAR;
		case PUFF_SHROOM:return Essences.TOXIC;
		default:{
			PVZMod.LOGGER.debug("plant get essence type error!");
			return null;
		}
		}
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
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 2400-100*now;
		}
		return 2400;
	}
	
}
