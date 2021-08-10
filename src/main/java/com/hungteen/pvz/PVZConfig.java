package com.hungteen.pvz;


import net.minecraftforge.common.ForgeConfigSpec;

public class PVZConfig {

	public static Common COMMON_CONFIG;
	public static Client CLIENT_CONFIG;
	
	public static class Common{
		
		public Common(ForgeConfigSpec.Builder builder) {
			//World Settings
			builder.comment("Settings about world.").push("World Settings");
			{
				builder.comment("Settings about world invasion.").push("WorldInvasion Settings");
				{
					WorldSettings.WorldInvasionSettings.SafeDayLength = builder
							.comment("If you set to 5, then the first 5 days of the world will not have any zombie invasion event.")
						    .defineInRange("SafeDayLength", 5, 0, 10000);
					
					WorldSettings.WorldInvasionSettings.InvasionIntervalLength = builder
							.comment("The interval day length between each invasion.")
						    .defineInRange("InvasionIntervalLength", 1, 0, 10000);
					
					WorldSettings.WorldInvasionSettings.MaxSpawnWeightMultiple = builder
							.comment("Spawn Weight Multipler of Zombies when Invasion happens.(such as the origin spawn weight of normal zombie is 100, your set to 3, then the final spawn weight is 3 * 100 = 300)")
							.defineInRange("MaxSpawnWeightMultiple", 3, 1, 6);
					
					WorldSettings.WorldInvasionSettings.ShowEventMessages = builder
							.comment("If true, you will receive detail message about each event when zombie invasion happened.")
							.define("ShowEventMessages", true);
					
					WorldSettings.WorldInvasionSettings.EnableHugeWave = builder
							.comment("If true, players will be invaded by a huge wave of zombies in zombie invasion day")
							.define("EnableHugeWave", true);
					
					WorldSettings.WorldInvasionSettings.EventChanceSettings.BucketAttackChance = builder
							.comment("The weight to happen Bucket Invasion when it's a zombie attack day.")
							.defineInRange("BucketAttackChance", 100, 1, 100000);
						
					WorldSettings.WorldInvasionSettings.EventChanceSettings.WaterAttackChance = builder
							.comment("The weight to happen Water Invasion when it's a zombie attack day.")
							.defineInRange("WaterAttackChance", 80, 1, 100000);
						
					WorldSettings.WorldInvasionSettings.EventChanceSettings.HalloweenAttackChance = builder
							.comment("The weight to happen Halloween Invasion when it's a zombie attack day.")
							.defineInRange("HalloweenAttackChance", 30, 1, 100000);
					
					WorldSettings.WorldInvasionSettings.EventChanceSettings.NewspaperAttackChance = builder
							.comment("The weight to happen Newspaper Invasion when it's a zombie attack day.")
							.defineInRange("NewspaperAttackChance", 70, 1, 100000);
						
					WorldSettings.WorldInvasionSettings.EventChanceSettings.FootballAttackChance = builder
							.comment("The weight to happen Football Invasion when it's a zombie attack day.")
							.defineInRange("FootballAttackChance", 60, 1, 100000);
						
					WorldSettings.WorldInvasionSettings.EventChanceSettings.RandomAttackChance = builder
							.comment("The weight to happen Random Invasion when it's a zombie attack day.")
							.defineInRange("RandomAttackChance", 100, 1, 10000);
					
					WorldSettings.WorldInvasionSettings.EventChanceSettings.YetiAttackChance = builder
							.comment("The weight to happen Yeti Invasion when it's a zombie attack day.")
							.defineInRange("YetiAttackChance", 50, 1, 10000);
					
					WorldSettings.WorldInvasionSettings.EventChanceSettings.BungeeAttackChance = builder
							.comment("The weight to happen Bungee Invasion when it's a zombie attack day.")
							.defineInRange("BungeeAttackChance", 20, 1, 10000);
					
					WorldSettings.WorldInvasionSettings.EventChanceSettings.MetalAttackChance = builder
							.comment("The weight to happen Metal Invasion when it's a zombie attack day.")
							.defineInRange("MetalAttackChance", 30, 1, 10000);
					
					WorldSettings.WorldInvasionSettings.EventChanceSettings.RoofAttackChance = builder
							.comment("The weight to happen Roof Invasion when it's a zombie attack day.")
							.defineInRange("RoofAttackChance", 50, 1, 10000);
					
					WorldSettings.WorldInvasionSettings.EventChanceSettings.GiantAttackChance = builder
							.comment("The weight to happen Giant Invasion when it's a zombie attack day.")
							.defineInRange("GiantAttackChance", 20, 1, 10000);
					
					WorldSettings.WorldInvasionSettings.EventChanceSettings.ZombotanyAttackChance = builder
							.comment("The weight to happen Zombotany Invasion when it's a zombie attack day.")
							.defineInRange("ZombotanyAttackChance", 30, 1, 10000);
					
					WorldSettings.WorldInvasionSettings.EventChanceSettings.FogEventChance = builder
							.comment("The related value to happen Fog Event when it's a zombie attack day(If the value is x, then the chance is 1/x).")
							.defineInRange("FogEventChance", 12, 1, 10000);
					
					WorldSettings.WorldInvasionSettings.EventChanceSettings.MiniEventChance = builder
							.comment("The related value to happen Mini Event when it's a zombie attack day(If the value is x, then the chance is 1/x).")
							.defineInRange("MiniEventChance", 10, 1, 10000);
					
					WorldSettings.WorldInvasionSettings.EventChanceSettings.InvisEventChance = builder
							.comment("The related value to happen Invis Event when it's a zombie attack day(If the value is x, then the chance is 1/x).")
							.defineInRange("InvisEventChance", 15, 1, 10000);
					
				}
				builder.pop();
				
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
		
		public WorldSettings WorldSettings = new WorldSettings();
		public EntitySettings EntitySettings = new EntitySettings();
		public BlockSettings BlockSettings = new BlockSettings();
		public ItemSettings ItemSettings = new ItemSettings();
		
		public static class WorldSettings{
			public WorldInvasionSettings WorldInvasionSettings = new WorldInvasionSettings();
			public StructureSettings StructureSettings = new StructureSettings();
			public EntitySpawnSettings EntitySpawnSettings = new EntitySpawnSettings();
			
			public ForgeConfigSpec.BooleanValue CanSpawnDefaultMonster;
			public ForgeConfigSpec.BooleanValue GiveBeginnerReward;
			
			public static class WorldInvasionSettings{
				public EventChanceSettings EventChanceSettings = new EventChanceSettings();
				public ForgeConfigSpec.IntValue SafeDayLength;
				public ForgeConfigSpec.IntValue InvasionIntervalLength;
			    public ForgeConfigSpec.IntValue MaxSpawnWeightMultiple;
			    public ForgeConfigSpec.BooleanValue ShowEventMessages;
			    public ForgeConfigSpec.BooleanValue EnableHugeWave;
			    
			    public static class EventChanceSettings{
			    	public ForgeConfigSpec.IntValue BucketAttackChance;
			    	public ForgeConfigSpec.IntValue WaterAttackChance;
			    	public ForgeConfigSpec.IntValue HalloweenAttackChance;
			    	public ForgeConfigSpec.IntValue NewspaperAttackChance;
			    	public ForgeConfigSpec.IntValue FootballAttackChance;
			    	public ForgeConfigSpec.IntValue RandomAttackChance;
			    	public ForgeConfigSpec.IntValue FogEventChance;
			    	public ForgeConfigSpec.IntValue YetiAttackChance;
			    	public ForgeConfigSpec.IntValue BungeeAttackChance;
			    	public ForgeConfigSpec.IntValue MetalAttackChance;
			    	public ForgeConfigSpec.IntValue RoofAttackChance;
			    	public ForgeConfigSpec.IntValue GiantAttackChance;
			    	public ForgeConfigSpec.IntValue MiniEventChance;
			    	public ForgeConfigSpec.IntValue InvisEventChance;
			    	public ForgeConfigSpec.IntValue ZombotanyAttackChance;
			    }
			}
			
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
