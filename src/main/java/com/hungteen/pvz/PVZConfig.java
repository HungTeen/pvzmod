package com.hungteen.pvz;


import net.minecraftforge.common.ForgeConfigSpec;

public class PVZConfig {

	public static Common COMMON_CONFIG;
	public static Client CLIENT_CONFIG;
	
	public static class Common{
		
		public Common(ForgeConfigSpec.Builder builder) {
			/* Invasion Settings */
			builder.comment("Settings about invasion.").push("Invasion Settings");
			{
				builder.comment("Settings about invasion events.").push("Invasion Events Settings");
				{
					InvasionSettings.BucketInvasionChance = builder
							.comment("The weight to happen Bucket Invasion when it's a zombie attack day.")
							.defineInRange("BucketInvasionChance", 100, 1, 1000000);
						
					InvasionSettings.WaterInvasionChance = builder
							.comment("The weight to happen Water Invasion when it's a zombie attack day.")
							.defineInRange("WaterInvasionChance", 90, 1, 1000000);
						
					InvasionSettings.HalloweenInvasionChance = builder
							.comment("The weight to happen Halloween Invasion when it's a zombie attack day.")
							.defineInRange("HalloweenInvasionChance", 40, 1, 1000000);
					
					InvasionSettings.NewspaperInvasionChance = builder
							.comment("The weight to happen Newspaper Invasion when it's a zombie attack day.")
							.defineInRange("NewspaperInvasionChance", 90, 1, 1000000);
						
					InvasionSettings.FootballInvasionChance = builder
							.comment("The weight to happen Football Invasion when it's a zombie attack day.")
							.defineInRange("FootballInvasionChance", 90, 1, 1000000);
						
					InvasionSettings.RandomInvasionChance = builder
							.comment("The weight to happen Random Invasion when it's a zombie attack day.")
							.defineInRange("RandomInvasionChance", 100, 1, 1000000);
					
					InvasionSettings.YetiInvasionChance = builder
							.comment("The weight to happen Yeti Invasion when it's a zombie attack day.")
							.defineInRange("YetiInvasionChance", 80, 1, 1000000);
					
					InvasionSettings.BungeeInvasionChance = builder
							.comment("The weight to happen Bungee Invasion when it's a zombie attack day.")
							.defineInRange("BungeeInvasionChance", 60, 1, 1000000);
					
					InvasionSettings.MetalInvasionChance = builder
							.comment("The weight to happen Metal Invasion when it's a zombie attack day.")
							.defineInRange("MetalInvasionChance", 70, 1, 1000000);
					
					InvasionSettings.RoofInvasionChance = builder
							.comment("The weight to happen Roof Invasion when it's a zombie attack day.")
							.defineInRange("RoofInvasionChance", 85, 1, 1000000);
					
					InvasionSettings.GiantInvasionChance = builder
							.comment("The weight to happen Giant Invasion when it's a zombie attack day.")
							.defineInRange("GiantInvasionChance", 66, 1, 1000000);
					
					InvasionSettings.Zombotany1InvasionChance = builder
							.comment("The weight to happen Zombotany I Invasion when it's a zombie attack day.")
							.defineInRange("Zombotany1AttackChance", 100, 1, 1000000);
					
					InvasionSettings.Zombotany2InvasionChance = builder
							.comment("The weight to happen Zombotany II Invasion when it's a zombie attack day.")
							.defineInRange("Zombotany2AttackChance", 75, 1, 1000000);
					
					InvasionSettings.FogInvasionChance = builder
							.comment("The related value to happen Fog Event when it's a zombie attack day(If the value is x, then the chance is 1/x).")
							.defineInRange("FogEventChance", 12, 1, 1000000);
					
					InvasionSettings.MiniInvasionChance = builder
							.comment("The related value to happen Mini Event when it's a zombie attack day(If the value is x, then the chance is 1/x).")
							.defineInRange("MiniEventChance", 10, 1, 1000000);
					
					InvasionSettings.InvisInvasionChance = builder
							.comment("The related value to happen Invis Event when it's a zombie attack day(If the value is x, then the chance is 1/x).")
							.defineInRange("InvisEventChance", 10, 1, 1000000);
				}
				builder.pop();
				
				builder.comment("Settings about others.").push("Invasion Other Settings");
				{
					InvasionSettings.SafeDayLength = builder
							.comment("If you set to 5, then the first 5 days of the world will not have any zombie invasion event.")
						    .defineInRange("SafeDayLength", 5, 0, 1000000);
					
					InvasionSettings.InvasionIntervalLength = builder
							.comment("The interval day length between each invasion.")
						    .defineInRange("InvasionIntervalLength", 1, 0, 1000000);
					
					InvasionSettings.MaxSpawnWeightMultiple = builder
							.comment("Spawn Weight Multipler of Zombies when Invasion happens.(such as the origin spawn weight of normal zombie is 100, your set to 3, then the final spawn weight is 3 * 100 = 300)")
							.defineInRange("MaxSpawnWeightMultiple", 3, 1, 6);
					
					InvasionSettings.ShowEventMessages = builder
							.comment("If true, you will receive detail message about each event when zombie invasion happened.")
							.define("ShowEventMessages", true);
					
					InvasionSettings.EnableHugeWave = builder
							.comment("If true, players will be invaded by a huge wave of zombies in zombie invasion day")
							.define("EnableHugeWave", true);
					
					InvasionSettings.LimitByDifficulty = builder
							.comment("If true, zombies will spawn with difficulty's incresing, stronger zombies only invade when difficulty is enough high.")
							.define("LimitByDifficulty", true);
					
					InvasionSettings.IncDifficulty = builder
							.comment("The inc of difficulty when invasion happen.")
							.defineInRange("IncDifficulty", 10, 1, 100);
					
					InvasionSettings.DecDifficulty = builder
							.comment("how many difficulty will dec when player killed by zombies.")
							.defineInRange("DecDifficulty", 3, 1, 100);
					
					InvasionSettings.LevelNeedDifficulty = builder
							.comment("how many difficulty needed to spawn high level zombie.")
							.defineInRange("LevelNeedDifficulty", 1000, 0, 1000000);
					
				}
				builder.pop();
			}
			builder.pop();
			
			//World Settings
			builder.comment("Settings about world.").push("World Settings");
			{
				
				builder.comment("Settings about the structure gen.").push("Structure Settings");
				{
					WorldSettings.StructureSettings.DaveVillaDistance = builder
							.comment("the distance value between dave villa.")
							.defineInRange("DaveVillaDistance", 40, 1, 1000);
					
					WorldSettings.StructureSettings.BucketHouseDistance = builder
							.comment("the distance value between bucket house.")
							.defineInRange("BucketHouseDistance", 32, 1, 1000);
					
					WorldSettings.StructureSettings.DolphinHouseDistance = builder
							.comment("the distance value between dolphin house.")
							.defineInRange("DolphinHouseDistance", 32, 1, 1000);
					
					WorldSettings.StructureSettings.GraveHouseDistance = builder
							.comment("the distance value between grave house.")
							.defineInRange("GraveHouseDistance", 28, 1, 1000);
					
					WorldSettings.StructureSettings.SunTempleDistance = builder
							.comment("the distance value between sun temple.")
							.defineInRange("SunTempleDistance", 40, 1, 1000);
					
					WorldSettings.StructureSettings.YetiHouseDistance = builder
							.comment("the distance value between yeti house.")
							.defineInRange("YetiHouseDistance", 28, 1, 1000);
				}
				builder.pop();
				
				builder.comment("The Spawn Weight of entity.").push("EntitySpawnWeight");
				{
					WorldSettings.EntitySpawnSettings.SunSpawnWeight = builder
							.comment("spawn weight of Sun.")
							.defineInRange("SunSpawnWeight", 50, 1, 200);
					WorldSettings.EntitySpawnSettings.ZombieDolphinSpawnWeight = builder
							.comment("spawn weight of ZombieDolphin.")
							.defineInRange("ZombieDolphinSpawnWeight", 1, 1, 200);
					WorldSettings.EntitySpawnSettings.FoodieZombieSpawnWeight = builder
							.comment("spawn weight of FoodieZombie.")
							.defineInRange("FoodieZombieSpawnWeight", 1, 1, 200);
					WorldSettings.EntitySpawnSettings.LavaZombieSpawnWeight = builder
							.comment("spawn weight of LavaZombie at nether.")
							.defineInRange("LavaZombieSpawnWeight", 15, 1, 200);
					WorldSettings.EntitySpawnSettings.GigaTombStoneSpawnWeight = builder
							.comment("spawn weight of GigaTombStone at night in overworld.")
							.defineInRange("GigaTombStoneSpawnWeight", 5, 1, 200);
					WorldSettings.EntitySpawnSettings.YetiZombieSpawnWeight = builder
							.comment("spawn weight of YetiZombie in overworld.")
							.defineInRange("YetiZombieSpawnWeight", 1, 1, 200);
				}
				builder.pop();
				
				builder.comment("Other World Settings").push("Other World Settings");
				{
					WorldSettings.CanSpawnDefaultMonster = builder
							.comment("if false,there will have no monster of default mc spawn in overworld.")
							.define("CanSpawnDefaultMonster", true);
					WorldSettings.GiveBeginnerReward = builder
							.comment("If you set it true, you will get some basic plantcards when you first join world.")
							.define("GiveBeginnerReward", false);
				}
				builder.pop();
				
			}
			builder.pop();
			
			//Entity Settings
			builder.comment("Settings about entities.").push("Entity Settings");
			{
				EntitySettings.TeamAttack = builder
                        .comment("if true,when plant's owner is in a team,the plant will attack the entity from other team(include players).")
                        .define("PlantAttackTeam", false);
				
				EntitySettings.PlayerInitialGroup = builder
						.comment("Player Initial Group When they join the world for the first time.(-2 means other monsters, -1 means zombies, 0 means neutral creatures, 1 means plants and 2 means other guards.")
						.defineInRange("PlayerInitialGroup", 1, -2, 2);

				builder.comment("Settings about zombies.").push("Zombie Settings");
				{
					EntitySettings.ZombieSetting.ZombieSuperChance = builder
						    .comment("the spawn chance of zombie with plant energy(the bigger,the more chance it spawn).")
						    .defineInRange("ZombieSuperChance", 1, 0, 40);
				    EntitySettings.ZombieSetting.ZombieSunChance = builder
						    .comment("the spawn chance of zombie with sun layer(the bigger,the more chance it spawn).")
						    .defineInRange("ZombieSunChance", 1, 0, 40);
				    EntitySettings.ZombieSetting.ZombieDropMultiper = builder
						    .comment("the drop chance of coin when zombie die(the bigger,the less chance it spawn).")
						    .defineInRange("ZombieDropMultiper", 10, 3, 100);
				    EntitySettings.ZombieSetting.EnableZombieDropHands = builder
						    .comment("enable zombies to drop hands and heads when they got hurt(turn false to disable).")
						    .define("EnableZombieDropHands", true);
				    EntitySettings.ZombieSetting.ZombieMaxLevel = builder
						    .comment("it can limit zombie's max level.")
						    .defineInRange("ZombieMaxLevel", 20, 1, 20);
				}
				builder.pop();
				
				builder.comment("Settings about plants.").push("Plant Settings");
				{
					EntitySettings.PlantSetting.StrangeCatCount = builder
						    .comment("the max number StrangeCats can copy themselves in a range of 20 * 20.")
						    .defineInRange("StrangeCatCount", 10, 0, 100);
				    
				}
				builder.pop();
				
				builder.comment("The Max live time for Entity like sun.").push("EntityLiveTime");
				{
					EntitySettings.EntityLiveTick.SunLiveTick = builder
							.comment("how many ticks can the sun entity live.")
							.defineInRange("SunLiveTick", 400, 1, 1200);
					EntitySettings.EntityLiveTick.CoinLiveTick = builder
							.comment("how many ticks can the coin entity live.")
							.defineInRange("CoinLiveTick", 400, 1, 1200);
					EntitySettings.EntityLiveTick.JewelLiveTick = builder
							.comment("how many ticks can the jewel entity live.")
							.defineInRange("JewelLiveTick", 500, 1, 1200);
					EntitySettings.EntityLiveTick.EnergyLiveTick = builder
							.comment("how many ticks can the energy entity live.")
							.defineInRange("EnergyLiveTick", 400, 1, 1200);
					EntitySettings.EntityLiveTick.YetiLiveTick = builder
							.comment("how many ticks can yeti entity live(how long will it stay).")
							.worldRestart()
							.defineInRange("YetiLiveTick", 2400, 1, 1000000);
					EntitySettings.EntityLiveTick.BowlingLiveTick = builder
							.comment("how many ticks can bowling entity live.")
							.worldRestart()
							.defineInRange("BowlingLiveTick", 300, 1, 1000000);
					EntitySettings.EntityLiveTick.LawnMowerLiveTick = builder
							.comment("how many ticks can lawnmower entity live.")
							.worldRestart()
							.defineInRange("LawnMowerLiveTick", 200, 1, 1000000);
					EntitySettings.EntityLiveTick.ElementBallLiveTick = builder
							.comment("how many ticks can element ball entity live.")
							.worldRestart()
							.defineInRange("ElementBallLiveTick", 600, 1, 1000000);
				}
				builder.pop();
			}
			builder.pop();
			//Block Settings 
			builder.comment("Settings about blocks.").push("Block Settings");
			{
				BlockSettings.OriginBlockEffectChance = builder
						.comment("About the chance you got essence_ore from origin_block.the bigger the value is,the lower chance you get(more specificly 1/x).")
						.defineInRange("OriginChance", 4, 1, 100);
				BlockSettings.ChomperGrowChance = builder
						.comment("The chance when you use bone meal to grow chomper,the bigger the less chance.")
						.defineInRange("ChomperGrow", 20, 5, 100);
				BlockSettings.SaplingTurnOriginChance = builder
						.comment("The chance when sapling turn to origin ore,the bigger the less chance")
						.defineInRange("SaplingTurnOrigin", 6, 2, 100);
				builder.comment("Setting about break blocks.").push("Break Block Setting");
				{
					BlockSettings.BreakBlock.PeaDropChance = builder
						.comment("the drop chance of pea when you break grass.the bigger the value is,the lower chance you get.(more specificly 1/x)")
						.defineInRange("DropPeaChance", 16, 1, 1000);
					BlockSettings.BreakBlock.CabbageDropChance = builder
							.comment("the drop chance of cabbage when you break grass.the bigger the value is,the lower chance you get.(more specificly 1/x)")
							.defineInRange("DropCabbageChance", 32, 1, 1000);
				}
				builder.pop();
			}
			builder.pop();
			//Item Settings
			builder.comment("Settings about items.").push("Item Settings");
			{
				ItemSettings.JackBoxSurpriseChance = builder
						.comment("The chance when player got a surprise when use jack box.the bigger the value is,the lower chance you get.(more specificly 1/x)")
						.defineInRange("JackBoxSurpriseChance", 10, 1, 1000000);
			}
			builder.pop();
		}
		
		public InvasionSettings InvasionSettings = new InvasionSettings();
		public WorldSettings WorldSettings = new WorldSettings();
		public EntitySettings EntitySettings = new EntitySettings();
		public BlockSettings BlockSettings = new BlockSettings();
		public ItemSettings ItemSettings = new ItemSettings();
		
		public static class InvasionSettings{
			/* misc */
			public ForgeConfigSpec.IntValue SafeDayLength;
			public ForgeConfigSpec.IntValue InvasionIntervalLength;
		    public ForgeConfigSpec.IntValue MaxSpawnWeightMultiple;
		    public ForgeConfigSpec.BooleanValue ShowEventMessages;
		    public ForgeConfigSpec.BooleanValue EnableHugeWave;
		    public ForgeConfigSpec.BooleanValue LimitByDifficulty;
		    public ForgeConfigSpec.IntValue IncDifficulty;
		    public ForgeConfigSpec.IntValue DecDifficulty;
		    public ForgeConfigSpec.IntValue LevelNeedDifficulty;
		    
		    /* invasion event chance */
		    public ForgeConfigSpec.IntValue BucketInvasionChance;
		    public ForgeConfigSpec.IntValue WaterInvasionChance;
		    public ForgeConfigSpec.IntValue HalloweenInvasionChance;
		    public ForgeConfigSpec.IntValue NewspaperInvasionChance;
		    public ForgeConfigSpec.IntValue FootballInvasionChance;
		    public ForgeConfigSpec.IntValue RandomInvasionChance;
		    public ForgeConfigSpec.IntValue FogInvasionChance;
		    public ForgeConfigSpec.IntValue YetiInvasionChance;
		    public ForgeConfigSpec.IntValue BungeeInvasionChance;
		    public ForgeConfigSpec.IntValue MetalInvasionChance;
		    public ForgeConfigSpec.IntValue RoofInvasionChance;
		    public ForgeConfigSpec.IntValue GiantInvasionChance;
		    public ForgeConfigSpec.IntValue MiniInvasionChance;
		    public ForgeConfigSpec.IntValue InvisInvasionChance;
		    public ForgeConfigSpec.IntValue Zombotany1InvasionChance;
		    public ForgeConfigSpec.IntValue Zombotany2InvasionChance;
		}
		
		public static class WorldSettings{
			public StructureSettings StructureSettings = new StructureSettings();
			public EntitySpawnSettings EntitySpawnSettings = new EntitySpawnSettings();
			
			public ForgeConfigSpec.BooleanValue CanSpawnDefaultMonster;
			public ForgeConfigSpec.BooleanValue GiveBeginnerReward;
			
			public static class StructureSettings{
				public ForgeConfigSpec.IntValue DaveVillaDistance;
			    public ForgeConfigSpec.IntValue BucketHouseDistance;
			    public ForgeConfigSpec.IntValue DolphinHouseDistance;
			    public ForgeConfigSpec.IntValue GraveHouseDistance;
			    public ForgeConfigSpec.IntValue SunTempleDistance;
			    public ForgeConfigSpec.IntValue YetiHouseDistance;
			}
			
			public static class EntitySpawnSettings{
				public ForgeConfigSpec.IntValue SunSpawnWeight;
				public ForgeConfigSpec.IntValue ZombieDolphinSpawnWeight;
				public ForgeConfigSpec.IntValue FoodieZombieSpawnWeight;
				public ForgeConfigSpec.IntValue LavaZombieSpawnWeight;
				public ForgeConfigSpec.IntValue GigaTombStoneSpawnWeight;
				public ForgeConfigSpec.IntValue YetiZombieSpawnWeight;
			}
		}
		
		public static class EntitySettings{
			
			public ForgeConfigSpec.BooleanValue TeamAttack;
			
			public ForgeConfigSpec.IntValue PlayerInitialGroup;
			
			public EntityLiveTick EntityLiveTick = new EntityLiveTick();
			public ZombieSetting ZombieSetting = new ZombieSetting();
			public PlantSetting PlantSetting = new PlantSetting();
			
			public static class ZombieSetting{
				public ForgeConfigSpec.IntValue ZombieSuperChance;
			    public ForgeConfigSpec.IntValue ZombieSunChance;
			    public ForgeConfigSpec.IntValue ZombieDropMultiper;
			    public ForgeConfigSpec.BooleanValue EnableZombieDropHands;
			    public ForgeConfigSpec.IntValue ZombieMaxLevel;
			}
			
			public static class PlantSetting{
				public ForgeConfigSpec.IntValue StrangeCatCount;
				public ForgeConfigSpec.IntValue PlantMaxLevel;
			}
			
			public static class EntityLiveTick{
				public ForgeConfigSpec.IntValue SunLiveTick;
				public ForgeConfigSpec.IntValue CoinLiveTick;
				public ForgeConfigSpec.IntValue JewelLiveTick;
				public ForgeConfigSpec.IntValue EnergyLiveTick;
				public ForgeConfigSpec.IntValue YetiLiveTick;
				public ForgeConfigSpec.IntValue BowlingLiveTick;
				public ForgeConfigSpec.IntValue LawnMowerLiveTick;
				public ForgeConfigSpec.IntValue ElementBallLiveTick;
			}
			
		}
		
		public static class BlockSettings{
			public ForgeConfigSpec.IntValue OriginBlockEffectChance;
			public ForgeConfigSpec.IntValue ChomperGrowChance;
			public ForgeConfigSpec.IntValue SaplingTurnOriginChance;
			
			public BreakBlock BreakBlock = new BreakBlock();
			public static class BreakBlock{
				public ForgeConfigSpec.IntValue PeaDropChance;
				public ForgeConfigSpec.IntValue CabbageDropChance;
			}
		}
		
		public static class ItemSettings {
			public ForgeConfigSpec.IntValue JackBoxSurpriseChance;
		}
		
	}
	
	public static class Client{
		
		public Client(ForgeConfigSpec.Builder builder) {
			builder.comment("Player Resource Bar Settings").push("Resource Render Settings");
			{
				ResourceRender.RenderSunNumBar = builder
						.comment("Should Render SunNumBar")
						.define("RenderSunNumBar", true);
				ResourceRender.RenderEnergyNumBar = builder
						.comment("Should Render EnergyNumBar")
						.define("RenderEnergyNumBar", true);
				ResourceRender.RenderMoneyBar = builder
						.comment("Should Render MoneyBar")
						.define("RenderMoneyBar", true);
				ResourceRender.RenderGemBar = builder
						.comment("Should Render GemBar")
						.define("RenderGemBar", true);
			}
			builder.pop();
			
			builder.comment("Render about Invasion").push("Invasion Render Settings");
			{
				InvasionRender.RenderInvasionProgress = builder
						.comment("Should Render Invasion Progress")
						.define("RenderInvasionProgress", true);
			}
			builder.pop();
			
			builder.comment("Render about Environment").push("Environment Render Settings");
			{
				EnvironmentRender.RenderFog = builder
						.comment("Should Render Fog Overlay")
						.define("RenderFog", true);
			}
			builder.pop();
			
			builder.comment("Other Render Settings").push("Other Render Settings");
			{
				OtherSettings.ShowPVZMainMenu = builder
						.comment("show pvz main menu")
						.define("ShowPVZMainMenu", true);
			}
			builder.pop();
		}
		
		public OtherSettings OtherSettings = new OtherSettings();
		public ResourceRender ResourceRender = new ResourceRender();
		public EnvironmentRender EnvironmentRender = new EnvironmentRender();
		public InvasionRender InvasionRender = new InvasionRender();
		
		public static class ResourceRender{
			public ForgeConfigSpec.BooleanValue RenderSunNumBar;
			public ForgeConfigSpec.BooleanValue RenderEnergyNumBar;
			public ForgeConfigSpec.BooleanValue RenderMoneyBar;
			public ForgeConfigSpec.BooleanValue RenderGemBar;
		}
		
		public static class InvasionRender {
			public ForgeConfigSpec.BooleanValue RenderInvasionProgress;
		}
		
		public static class EnvironmentRender{
			public ForgeConfigSpec.BooleanValue RenderFog;
		}
		
		public static class OtherSettings{
			public ForgeConfigSpec.BooleanValue ShowPVZMainMenu;
		}
	}
}
