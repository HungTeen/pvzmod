package com.hungteen.pvz.utils;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Ranks;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

public class PlantUtil {

	public static boolean checkCanPlantLiveHere(PVZPlantEntity plant)
	{
		BlockPos pos=plant.getPosition().add(0, -1, 0);
		Block block = plant.world.getBlockState(pos).getBlock();
		Plants p = plant.getPlantEnumName();
		if(plant.getIsGardenPlant()) {
			
		}else {
			switch(p) {
			default:{
				if(block==Blocks.GRASS_BLOCK) return true;
			}
			}
		}
		return false;
	}
	
	/**
	 * get plant card sun cost
	 */
	public static int getPlantSunCost(Plants plant)
	{
		switch(plant) {
		case SUN_FLOWER:return 50;
		case PEA_SHOOTER:return 100;
		default:return 0;
		}
	}
	
	/**
	 * get plant card CD
	 */
	public static int getPlantCoolDownTime(Plants plant,int lvl)
	{
		switch(plant) {
		case PEA_SHOOTER:return getPlantCoolDownTimeVeryFast(lvl);
		case SUN_FLOWER:return getPlantCoolDownTimeFast(lvl);
		default:return 0;
		}
	}
	
	public static int getPlantMaxHealth(Plants plant,int lvl)
	{
		switch(plant) {
		default:{
			if(lvl<=14) return 30+(lvl-1)/2*5;
			else if(lvl<=20) return 60+(lvl-14)*5;
		}
		}
		PVZMod.LOGGER.debug("plant get max health error!");
		return 20;
	}
	
	public static Ranks getPlantRankByName(Plants plant)
	{
		switch(plant) {
		case PEA_SHOOTER:
		case SUN_FLOWER:return Ranks.GRAY;
		default:return null;
		}
	}
	
	/**
	 * 8s-5s配置
	 */
	public static int getPlantCoolDownTimeHugeFast(int lvl)
	{
		//8 7 6 5
		if(lvl<=20) {
			int now=(lvl-1)/5;
			return 160-20*now;
		}
		return 160;
	}
	
	/**
	 * 豌豆射手 卷心菜10s-8s配置
	 */
	public static int getPlantCoolDownTimeVeryFast(int lvl)
	{
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 200-10*now;
		}
		return 200;
	}
	
	/**
	 * 向日葵 双枪射手15s-12s配置
	 */
	public static int getPlantCoolDownTimeFast(int lvl)
	{
		if(lvl<=20) {
			int now=(lvl-1)/5;
			return 300-20*now;
		}
		return 300;
	}
	
	/**
	 * 西瓜投手 三线20s-16s配置
	 */
	public static int getPlantCoolDownTimeLittleFast(int lvl)
	{
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 400-20*now;
		}
		return 400;
	}
	
	/**
	 * 火炬树桩 25s-21s配置
	 */
	public static int getPlantCoolDownTimeNormal(int lvl)
	{
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 500-20*now;
		}
		return 500;
	}
	
	/**
	 * 火爆辣椒 寒冰菇 32s-28s配置
	 */
	public static int getPlantCoolDownTimeLittleSlow(int lvl)
	{
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 640-20*now;
		}
		return 640;
	}
	
	/**
	 * 坚果墙 土豆雷 海草40s-32s配置
	 */
	public static int getPlantCoolDownTimeSlow(int lvl)
	{
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 800-40*now;
		}
		return 800;
	}
	
	/**
	 * 高级植物 60s-50s配置
	 */
	public static int getPlantCoolDownTimeVerySlow(int lvl)
	{
		if(lvl<=18) {
			int now=(lvl-1)/2;
			return 1200-20*now;
		}
		else if(lvl<=20) return 1000;
		return 1200;
	}
	
	/**
	 * 爆炸植物 120s-100s配置
	 */
	public static int getPlantCoolDownTimeHugeSlow(int lvl)
	{
		if(lvl<=20) {
			int now=(lvl-1)/4;
			return 2400-100*now;
		}
		return 2400;
	}
}
