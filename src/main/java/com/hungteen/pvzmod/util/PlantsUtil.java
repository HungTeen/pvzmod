package com.hungteen.pvzmod.util;

import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.entities.plants.common.EntityDoubleShooter;
import com.hungteen.pvzmod.entities.plants.common.EntityGatlingPea;
import com.hungteen.pvzmod.entities.plants.common.EntityMelonPult;
import com.hungteen.pvzmod.entities.plants.fight.EntitySpikeRock;
import com.hungteen.pvzmod.entities.plants.fight.EntitySpikeWeed;
import com.hungteen.pvzmod.entities.plants.ice.EntityIceShroom;
import com.hungteen.pvzmod.entities.plants.ice.EntityIcebergLettuce;
import com.hungteen.pvzmod.entities.plants.ice.EntitySnowPea;
import com.hungteen.pvzmod.entities.plants.ice.EntityWinterMelon;
import com.hungteen.pvzmod.entities.plants.light.EntitySunFlower;
import com.hungteen.pvzmod.entities.plants.light.EntityTwinSunFlower;
import com.hungteen.pvzmod.registry.BlockRegister;
import com.hungteen.pvzmod.registry.ItemRegister;
import com.hungteen.pvzmod.registry.PotionRegister;
import com.hungteen.pvzmod.util.enums.Ranks;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class PlantsUtil {

	public static Block[] blocks = new Block[]{Blocks.GRASS,BlockRegister.FLOWER_POT,BlockRegister.GOLDENTILE1,BlockRegister.GOLDENTILE2,BlockRegister.GOLDENTILE3};
                                       //1  2 3  4  5  6  7  8   9   10  11  12  13  14  15  16  17  18  19  20
    public static final int[] lvlXP= {0,10,20,30,40,50,60,80,100,120,140,160,180,200,250,300,350,400,450,500,100000000}; 
    
    /**
     * 检查植物能否在适应其脚下的方块
     * 不合适的话会持续掉血
     */
	public static boolean checkCanPlantLiveHere(EntityPlantBase plant,boolean isGardenPlant)
	{
		Plants plantType=plant.getPlantEnumName();
		BlockPos posTop=new BlockPos(plant);
		BlockPos posDown=posTop.add(0, -1, 0);
		Block topBlock=plant.world.getBlockState(posTop).getBlock();
		Block downBlock=plant.world.getBlockState(posDown).getBlock();
		if(downBlock==Blocks.AIR) return true;//空气所以植物均可存活
		if(isGardenPlant) {//花园植物只能种在金花盆上!记得更新
			return downBlock==BlockRegister.FLOWER_POT;
		}
		if(plantType==Plants.CAT_TAIL) {//猫扑只能种在睡莲上
			return topBlock==BlockRegister.LILY_PAD;
		}else if(plantType==Plants.LIGHTLING_ROD) {//避雷草还可以种在电之精华矿上，不然他是自杀型？
			if(downBlock==BlockRegister.ELECTRICITY_ORE) return true;
		}
		for(Block b:blocks) {
			if(b == downBlock) return true;
		}
		if(topBlock==BlockRegister.LILY_PAD) return true;//可以种在莲叶上
		return false;
	}
	
	/**
	 * 植物枚举名称的国际化
	 */
	public static String getPlantName(Plants plant)
	{
		switch(plant) {
		case PEA_SHOOTER:return new TextComponentTranslation("text.pea_shooter.name").getFormattedText();
		case SUN_FLOWER:return new TextComponentTranslation("text.sun_flower.name").getFormattedText();
		case NUT_WALL:return new TextComponentTranslation("text.nut_wall.name").getFormattedText();
		case CHERRY_BOMB:return new TextComponentTranslation("text.cherry_bomb.name").getFormattedText();
		case POTATO_MINE:return new TextComponentTranslation("text.potato_mine.name").getFormattedText();
		case SNOW_PEA:return new TextComponentTranslation("text.snow_pea.name").getFormattedText();
		case DOUBLE_SHOOTER:return new TextComponentTranslation("text.double_shooter.name").getFormattedText();
		case HYPNO_SHROOM:return new TextComponentTranslation("text.hypno_shroom.name").getFormattedText();
		case ICE_SHROOM:return new TextComponentTranslation("text.ice_shroom.name").getFormattedText();
		case LILY_PAD:return new TextComponentTranslation("text.lily_pad.name").getFormattedText();
		case SQUASH:return new TextComponentTranslation("text.squash.name").getFormattedText();
		case THREE_PEATER:return new TextComponentTranslation("text.three_peater.name").getFormattedText();
		case TANGLE_KELP:return new TextComponentTranslation("text.tangle_kelp.name").getFormattedText();
		case JALAPENO:return new TextComponentTranslation("text.jalapeno.name").getFormattedText();
		case SPIKE_WEED:return new TextComponentTranslation("text.spike_weed.name").getFormattedText();
		case TORCH_WOOD:return new TextComponentTranslation("text.torch_wood.name").getFormattedText();
		case TALL_NUT:return new TextComponentTranslation("text.tall_nut.name").getFormattedText();
		case SPLIT_PEA:return new TextComponentTranslation("text.split_pea.name").getFormattedText();
		case PUMPKIN:return new TextComponentTranslation("text.pumpkin.name").getFormattedText();
		case CABBAGE_PULT:return new TextComponentTranslation("text.cabbage_pult.name").getFormattedText();
		case FLOWER_POT:return new TextComponentTranslation("text.flower_pot.name").getFormattedText();
		case KERNEL_PULT:return new TextComponentTranslation("text.kernel_pult.name").getFormattedText();
		case COFFEE_BEAN:return new TextComponentTranslation("text.coffee_bean.name").getFormattedText();
		case MARIGOLD:return new TextComponentTranslation("text.marigold.name").getFormattedText();
		case MELON_PULT:return new TextComponentTranslation("text.melon_pult.name").getFormattedText();
		case GATLING_PEA:return new TextComponentTranslation("text.gatling_pea.name").getFormattedText();
		case TWIN_SUNFLOWER:return new TextComponentTranslation("text.twin_sunflower.name").getFormattedText();
		case CAT_TAIL:return new TextComponentTranslation("text.cat_tail.name").getFormattedText();
		case WINTER_MELON:return new TextComponentTranslation("text.winter_melon.name").getFormattedText();
		case SPIKE_ROCK:return new TextComponentTranslation("text.spike_rock.name").getFormattedText();
		case ICEBERG_LETTUCE:return new TextComponentTranslation("text.iceberg_lettuce.name").getFormattedText();
		case GOLD_LEAF:return new TextComponentTranslation("text.gold_leaf.name").getFormattedText();
		case LIGHTLING_ROD:return new TextComponentTranslation("text.lightning_rod.name").getFormattedText();
		case STRANGE_CAT:return new TextComponentTranslation("text.strange_cat.name").getFormattedText();
		default:{
			System.out.println("WRONG PLANT TYPE");
			return "";
		}
		}
	}
	
	/**
	 * 通过植物枚举名获得其对应的植物召唤卡
	 */
	public static Item getItemFromPlant(Plants plantType)
	{
		switch(plantType) {
		case PEA_SHOOTER: return ItemRegister.PEA_SHOOTER_CARD;
		case SUN_FLOWER: return ItemRegister.SUN_FLOWER_CARD;
		case CHERRY_BOMB: return ItemRegister.CHERRY_BOMB_CARD;
		case NUT_WALL: return ItemRegister.NUT_WALL_CARD;
		case POTATO_MINE: return ItemRegister.POTATO_MINE_CARD;
		case SNOW_PEA: return ItemRegister.SNOW_PEA_CARD;
		case DOUBLE_SHOOTER: return ItemRegister.DOUBLE_SHOOTER_CARD;
		case HYPNO_SHROOM: return ItemRegister.HYPNO_SHROOM_CARD;
		case ICE_SHROOM: return ItemRegister.ICE_SHROOM_CARD;
		case LILY_PAD: return ItemRegister.LILY_PAD_CARD;
		case SQUASH: return ItemRegister.SQUAHS_CARD;
		case THREE_PEATER: return ItemRegister.THREE_PEATER_CARD;
		case TANGLE_KELP: return ItemRegister.TANGLE_KELP_CARD;
		case JALAPENO: return ItemRegister.JALAPENO_CARD;
		case SPIKE_WEED: return ItemRegister.SPIKE_WEED_CARD;
		case TORCH_WOOD: return ItemRegister.TORCH_WOOD_CARD;
		case TALL_NUT: return ItemRegister.TALL_NUT_CARD;
		case SPLIT_PEA:return ItemRegister.SPLIT_PEA_CARD;
		case PUMPKIN:return ItemRegister.PUMPKIN_CARD;
		case CABBAGE_PULT:return ItemRegister.CABBAGE_PULT_CARD;
		case FLOWER_POT:return ItemRegister.FLOWER_POT_CARD;
		case KERNEL_PULT:return ItemRegister.KERNEL_PULT_CARD;
		case COFFEE_BEAN:return ItemRegister.COFFEE_BEAN_CARD;
		case MARIGOLD:return ItemRegister.MARIGOLD_CARD;
		case MELON_PULT:return ItemRegister.MELON_PULT_CARD;
		case GATLING_PEA:return ItemRegister.GATLING_PEA_CARD;
		case TWIN_SUNFLOWER:return ItemRegister.TWIN_SUNFLOWER_CARD;
		case CAT_TAIL:return ItemRegister.CAT_TAIL_CARD;
		case WINTER_MELON:return ItemRegister.WINTER_MELON_CARD;
		case SPIKE_ROCK:return ItemRegister.SPIKE_ROCK_CARD;
		case ICEBERG_LETTUCE:return ItemRegister.ICEBERG_LETTUCE_CARD;
		case GOLD_LEAF:return ItemRegister.GOLD_LEAF_CARD;
		case LIGHTLING_ROD:return ItemRegister.LIGHTNING_ROD_CARD;
		case STRANGE_CAT:return ItemRegister.STRANGE_CAT_CARD;
		default:{
			System.out.println("WRONG PLANT TYPE");
			return null;
		}
		}
	}
	
	/**
	 * 返回该植物的最高等级
	 * 可能后续会有大于20级的植物
	 */
	public static int getPlantMaxLvl(Plants plant)
	{
		switch(plant) {
		case LILY_PAD:
		case FLOWER_POT:return 1;
		default:return 20;
		}
	}
	
	/**
	 * 获取当前等级升到下一级所需的经验
	 */
	public static int getPlantLvlUpXp(int lvl)
	{
		return lvlXP[lvl];
	}
	
	/**
	 * 获取植物最大生命值
	 * 大部分植物都是一样的
	 * 坚果类特判
	 */
	public static float getPlantMaxHealth(Plants plant,int lvl)
	{
		switch(plant) {
		case NUT_WALL:{
			if(lvl<=19) return 350f+lvl*50f;
			else if(lvl<=20) return 800;
			return 400f;
		}
		case LILY_PAD:
		case FLOWER_POT:return 0;
		case TALL_NUT:{
			if(lvl<=19) return 750f+lvl*50f;
			else if(lvl<=20) return 1800;
			return 800f;
		}
		case PUMPKIN:{
			if(lvl<=20) {
				int now=(lvl-1)/2;
				return 350+50*now;
			}
			return 350;
		}
		default:{
		    if(lvl>=13) return 90f;
		    return 25f+5f*lvl;
		}
		}
	}
	
	/**
	 * 检查这个植物是否为升级植物
	 * 猫扑也是，但其升级植物不是实体
	 */
	public static boolean checkUpgradePlant(Plants plant)
	{
		switch(plant) {
		case GATLING_PEA:
		case TWIN_SUNFLOWER:
		case WINTER_MELON:
		case SPIKE_ROCK:return true;
		default:return false;
		}
	}
	
	/**
	 * 获取对应升级植物的实例
	 */
	public static EntityPlantBase getEntityForUpgrade(World world,Entity entity,Plants plant)
	{
		if(plant==Plants.GATLING_PEA&&entity instanceof EntityDoubleShooter) return new EntityGatlingPea(world);
		if(plant==Plants.TWIN_SUNFLOWER&&entity instanceof EntitySunFlower) return new EntityTwinSunFlower(world);
		if(plant==Plants.WINTER_MELON&&entity instanceof EntityMelonPult) return new EntityWinterMelon(world);
		if(plant==Plants.SPIKE_ROCK&&entity instanceof EntitySpikeWeed) return new EntitySpikeRock(world);
		return null;
	}
	
	/**
	 * 获得当前植物的品质
	 * 1 - 7 对应 灰 白 绿 蓝 紫 金 红
	 */
	public static Ranks getPlantRank(Plants plant)
	{
		switch(plant) {
		case PEA_SHOOTER:
		case SUN_FLOWER:
		case LILY_PAD:
		case CABBAGE_PULT:
		case FLOWER_POT:
		case KERNEL_PULT:return Ranks.GRAY;
		case NUT_WALL:
		case POTATO_MINE:
		case TANGLE_KELP:
		case SPIKE_WEED:
		case SPLIT_PEA:
		case PUMPKIN:
		case ICEBERG_LETTUCE:return Ranks.WHITE;
		case SNOW_PEA:
		case DOUBLE_SHOOTER:
		case SQUASH:
		case TORCH_WOOD:
		case COFFEE_BEAN:
		case MARIGOLD:
		case LIGHTLING_ROD:return Ranks.GREEN;
		case CHERRY_BOMB:
		case HYPNO_SHROOM:
		case ICE_SHROOM:
		case THREE_PEATER:
		case JALAPENO:
		case TALL_NUT:
		case TWIN_SUNFLOWER:
		case CAT_TAIL:return Ranks.BLUE;
		case MELON_PULT:
		case GATLING_PEA:
		case SPIKE_ROCK:
		case GOLD_LEAF:
		case STRANGE_CAT:return Ranks.PURPLE;
		case WINTER_MELON:return Ranks.GOLD;
		default:{
			System.out.println("plant type error");
			return null;
		}
		}
	}
	
	/**
	 * 获得当前植物的冷却时间
	 */
	public static int getPlantCoolDownTime(Plants plant,int lvl)
	{
		switch(plant) {
		case PEA_SHOOTER:
		case SPLIT_PEA:
		case CABBAGE_PULT:
		case KERNEL_PULT:return getPlantCoolDownTimeVeryFast(lvl);
		case SUN_FLOWER:
		case SNOW_PEA:
		case DOUBLE_SHOOTER:
		case SPIKE_WEED:return getPlantCoolDownTimeFast(lvl);
		case THREE_PEATER:
		case MELON_PULT:return getPlantCoolDownTimeLittleFast(lvl);
		case POTATO_MINE: 
		case HYPNO_SHROOM:
		case SQUASH:
		case MARIGOLD:
		case ICEBERG_LETTUCE:
		case LIGHTLING_ROD:return getPlantCoolDownTimeLittleSlow(lvl);
		case TORCH_WOOD:
		case COFFEE_BEAN:
		case GOLD_LEAF:return getPlantCoolDownTimeNormal(lvl);
		case NUT_WALL:
		case ICE_SHROOM:
		case TANGLE_KELP:
		case JALAPENO:
		case PUMPKIN:return getPlantCoolDownTimeSlow(lvl);
		case TALL_NUT:
		case GATLING_PEA:
		case TWIN_SUNFLOWER:
		case CAT_TAIL:
		case WINTER_MELON:
		case SPIKE_ROCK:return getPlantCoolDownTimeVerySlow(lvl);
		case CHERRY_BOMB:
		case STRANGE_CAT:return getPlantCoolDownTimeHugeSlow(lvl);
		case LILY_PAD:
		case FLOWER_POT:return 100;
		default:return 1;
		}
	}
	
	/**
	 * 获取当前植物的阳光消耗
	 */
	public static int getPlantSunCost(Plants plant)
	{
		switch(plant) {
		case ICEBERG_LETTUCE:return 0;
		case POTATO_MINE:
		case LILY_PAD:
		case TANGLE_KELP:
		case FLOWER_POT:return 25;
		case SUN_FLOWER:
		case NUT_WALL:
		case MARIGOLD:return 50;
		case ICE_SHROOM:
		case SQUASH:
		case COFFEE_BEAN:
		case GOLD_LEAF:return 75;
		case PEA_SHOOTER:
		case PUMPKIN:
		case CABBAGE_PULT:
		case KERNEL_PULT:return 100;
		case HYPNO_SHROOM:
		case SPIKE_WEED:
		case TALL_NUT:
		case SPLIT_PEA:return 125;
		case CHERRY_BOMB:
		case TWIN_SUNFLOWER:
		case LIGHTLING_ROD:return 150;
		case SNOW_PEA:
		case JALAPENO:return 175;
		case DOUBLE_SHOOTER:
		case WINTER_MELON:return 200;
		case TORCH_WOOD:
		case STRANGE_CAT:return 225;
		case GATLING_PEA:return 250;
		case CAT_TAIL:
		case SPIKE_ROCK:return 275;
		case MELON_PULT:return 300;
		case THREE_PEATER:return 325;
		default:return 0;
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
	
	public static void copyPlantData(EntityPlantBase oldPlant,EntityPlantBase newPlant)
	{
		newPlant.setOwnerName(oldPlant.getOwnerName());
		newPlant.setIsCharmed(oldPlant.getIsCharmed());
		newPlant.setPlantLvl(oldPlant.getPlantLvl());
	}
	
	public static PotionEffect getColdPotionEffect(EntityPlantBase plant)
	{
		if (plant instanceof EntitySnowPea) {//寒冰射手
			return ((EntitySnowPea) plant).getColdPotionEffect();
		}
		if(plant instanceof EntityWinterMelon) {//冰西瓜
			return ((EntityWinterMelon) plant).getColdPotionEffect();
		}
		if (plant instanceof EntityIcebergLettuce) {//冰冻生菜
			return ((EntityIcebergLettuce) plant).getColdEffect();
		}
		if(plant instanceof EntityIceShroom) {
			return ((EntityIceShroom) plant).getColdPotionEffect();
		}
		return null;
	}
	
	public static PotionEffect getFrozenPotionEffect(EntityPlantBase plant)
	{
		if(plant instanceof EntityIcebergLettuce) {
			return ((EntityIcebergLettuce) plant).getFrozenEffect();
		}
		if(plant instanceof EntityIceShroom) {
			return ((EntityIceShroom) plant).getFrozenPotionEffect();
		}
		return null;
	}
}
