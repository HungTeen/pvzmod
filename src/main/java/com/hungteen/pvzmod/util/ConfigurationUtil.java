package com.hungteen.pvzmod.util;

import com.hungteen.pvzmod.client.gui.render.ResourcesRenderer;

import net.minecraftforge.common.config.Config;

public class ConfigurationUtil {

	@Config(modid = "pvz", type = Config.Type.INSTANCE, name = "pvz/main_config")
	@Config.LangKey("gui.pvzconfig.title")
	public static class MainConfig {
		@Config.Comment("Configure the global settings")
		@Config.LangKey("gui.pvzconfig.globalSettings")
		public static final SubCategoryGlobalSettings globalSettings= new SubCategoryGlobalSettings();//全局设置
		
		@Config.Comment("Configure random events that happen in the overworld each day")
		@Config.LangKey("gui.pvzconfig.overworldEvents")
		public static final SubCategoryOverworldEvents overworldEvents = new SubCategoryOverworldEvents();//主世界的特殊事件
		
		@Config.Comment("Configure damage settings")
		@Config.LangKey("gui.pvzconfig.damageSettings")
		public static final SubCategoryDamageSettings damageSettings =new SubCategoryDamageSettings();//伤害设置
		
		public static class SubCategoryGlobalSettings{
			@Config.Comment("Choose the global difficulty")
			@Config.LangKey("gui.pvzconfig.difficulty")
			public Difficulty globalDifficulty = Difficulty.NORMAL;//植物能否伤害植物
		}
		
		public static class SubCategoryDamageSettings{
			
			@Config.Comment("Can one hurt its friends by mistake.(eg:your PeaShooter's pea may hurt you by mistake")
			@Config.LangKey("gui.pvzconfig.canHurtFriendByMistake")
			public boolean canHurtFriendByAccident = false;//是否会误伤友军
			
			@Config.Comment("Plants can target and hurt the other players(expect its owner) when it's true)")
			@Config.LangKey("gui.pvzconfig.canPlantHurtPlayers")
			public boolean canPlantHurtOtherPlayers = false;//植物能否伤害其他玩家
			
			@Config.Comment("Can bullet pass through the entities that it can't hurt")
			@Config.LangKey("gui.pvzconfig.canBulletPassEntity")
			public boolean canBulletPassEntity = true;//子弹能否穿过不能伤害的实体
		}
		
		public static class SubCategoryOverworldEvents{
			
			@Config.Comment("Chance per day for the PlantZombie Day to occur. Value is represented as a chance of 1/n.")
			@Config.LangKey("gui.pvzconfig.plantZombieDayChance")
			@Config.RangeInt(min = 1, max = 1000000)
			public int plantZombieDayChance = 10;

			@Config.Comment("Chance per day for the SmallZombie Day to occur. Value is represented as a chance of 1/n.")
			@Config.LangKey("gui.pvzconfig.smallZombieDayChance")
			@Config.RangeInt(min = 1, max = 1000000)
			public int smallZombieDayChance = 15;
			
			@Config.Comment("Chance per day for the InvisZombie Day to occur. Value is represented as a chance of 1/n.")
			@Config.LangKey("gui.pvzconfig.smallZombieDayChance")
			@Config.RangeInt(min = 1, max = 1000000)
			public int invisZombieDayChance = 15;
			
			@Config.Comment("Chance per day for the Vase Day to occur. Value is represented as a chance of 1/n.")
			@Config.LangKey("gui.pvzconfig.vaseDayChance")
			@Config.RangeInt(min = 1, max = 1000000)
			public int vaseDayChance = 8;
			
		}
	}
	
	public enum Difficulty{
		EASY,
		NORMAL,
		HARD
	}
}
