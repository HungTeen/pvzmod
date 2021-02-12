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
				builder.comment("Settings about world event.").push("WorldEvent Settings");
				{
					WorldSettings.WorldEventSettings.SafeDayLength = builder
							.comment("If you set to 3,then the first 3 day of the world will not have any zombie attack event.")
						    .defineInRange("SafeDayLength", 3, 0, 100);
					
					WorldSettings.WorldEventSettings.ZombieAttackChance = builder
						    .comment("The chance related to zombie attack event. the bigger the more chance it has(chance/100).")
						    .defineInRange("ZombieAttackChance", 60, 0, 100);
					
					WorldSettings.WorldEventSettings.SpawnWeightIncDuration = builder
							.comment("How many day will the spawn weight of zombie increase to max. If set to 0, then the initial spawn weight is max.")
							.defineInRange("SpawnWeightIncDuration", 100, 0, 100000);
					
					WorldSettings.WorldEventSettings.MaxSpawnWeightMultiple = builder
							.comment("How many times the final weight is increased to the initial spawn weight(If initial is 60, value is 3, then final is 3 * 60 = 180).")
							.defineInRange("MaxSpawnWeightMultiple", 3, 1, 6);
					
					WorldSettings.WorldEventSettings.ShowEventMessages = builder
							.comment("If true, you will receive detail message about each event when zombie invasion happened.")
							.define("ShowEventMessages", true);
					
					WorldSettings.WorldEventSettings.EnableHugeWave = builder
							.comment("If true, players will be invaded by a huge wave of zombies in zombie invasion day")
							.define("EnableHugeWave", true);
					
					WorldSettings.WorldEventSettings.EventChanceSettings.BucketAttackChance = builder
							.comment("The weight to happen Bucket Invasion when it's a zombie attack day.")
							.defineInRange("BucketAttackChance", 100, 0, 100000);
						
					WorldSettings.WorldEventSettings.EventChanceSettings.WaterAttackChance = builder
							.comment("The weight to happen Water Invasion when it's a zombie attack day.")
							.defineInRange("WaterAttackChance", 80, 0, 100000);
						
					WorldSettings.WorldEventSettings.EventChanceSettings.HalloweenAttackChance = builder
							.comment("The weight to happen Halloween Invasion when it's a zombie attack day.")
							.defineInRange("HalloweenAttackChance", 20, 0, 100000);
						
					WorldSettings.WorldEventSettings.EventChanceSettings.NewspaperAttackChance = builder
							.comment("The weight to happen Newspaper Invasion when it's a zombie attack day.")
							.defineInRange("NewspaperAttackChance", 50, 0, 100000);
						
					WorldSettings.WorldEventSettings.EventChanceSettings.FootballAttackChance = builder
							.comment("The weight to happen Football Invasion when it's a zombie attack day.")
							.defineInRange("FootballAttackChance", 50, 0, 100000);
						
					WorldSettings.WorldEventSettings.EventChanceSettings.RandomAttackChance = builder
							.comment("The weight to happen Random Invasion when it's a zombie attack day.")
							.defineInRange("RandomAttackChance", 70, 0, 10000);
					
					WorldSettings.WorldEventSettings.EventChanceSettings.YetiAttackChance = builder
							.comment("The weight to happen Yeti Invasion when it's a zombie attack day.")
							.defineInRange("YetiAttackChance", 20, 1, 10000);
					
					WorldSettings.WorldEventSettings.EventChanceSettings.BungeeAttackChance = builder
							.comment("The weight to happen Bungee Invasion when it's a zombie attack day.")
							.defineInRange("BungeeAttackChance", 40, 1, 10000);
					
					WorldSettings.WorldEventSettings.EventChanceSettings.MetalAttackChance = builder
							.comment("The weight to happen Metal Invasion when it's a zombie attack day.")
							.defineInRange("MetalAttackChance", 25, 1, 10000);
					
					WorldSettings.WorldEventSettings.EventChanceSettings.RoofAttackChance = builder
							.comment("The weight to happen Roof Invasion when it's a zombie attack day.")
							.defineInRange("RoofAttackChance", 50, 1, 10000);
					
					WorldSettings.WorldEventSettings.EventChanceSettings.GiantAttackChance = builder
							.comment("The weight to happen Giant Invasion when it's a zombie attack day.")
							.defineInRange("GiantAttackChance", 25, 1, 10000);
					
					WorldSettings.WorldEventSettings.EventChanceSettings.FogEventChance = builder
							.comment("The related value to happen Fog Event when it's a zombie attack day(If the value is x, then the chance is 1/x).")
							.defineInRange("FogEventChance", 12, 1, 10000);
					
					WorldSettings.WorldEventSettings.EventChanceSettings.MiniEventChance = builder
							.comment("The related value to happen Mini Event when it's a zombie attack day(If the value is x, then the chance is 1/x).")
							.defineInRange("MiniEventChance", 10, 1, 10000);
					
					WorldSettings.WorldEventSettings.EventChanceSettings.InvisEventChance = builder
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
					WorldSettings.EntitySpawnSettings.TombStoneSpawnWeight = builder
							.comment("spawn weight of TombStone at night in overworld when invasion.")
							.defineInRange("TombStoneSpawnWeight", 5, 1, 200);
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
				EntitySettings.ZombieSuperChance = builder
						.comment("the spawn chance of zombie with plant energy(the bigger,the more chance it spawn).")
						.defineInRange("ZombieSuperChance", 1, 0, 50);
				EntitySettings.ZombieSunChance = builder
						.comment("the spawn chance of zombie with sun layer(the bigger,the more chance it spawn).")
						.defineInRange("ZombieSunChance", 1, 0, 30);
				EntitySettings.DoomRange = builder
						.comment("The width range when doom shroom explosion will destroy,0 means no destroy.")
						.defineInRange("DoomRange", 3, 0, 10);
				EntitySettings.TrickZombieCharmChance = builder
						.comment("The value related to the chance to use candy charm TrickZombie(if set to x, the chance is 1/x).")
						.defineInRange("TrickZombieCharmChance", 3, 1, 100);
				EntitySettings.PlayerOriginGroup = builder
						.comment("The Group of player, 1 means u are in plant group(zombie will attack u), 0 means u are mid(no plants and zombies attack u), -1 means u are in zombie group(plant will attack u).")
						.defineInRange("PlayerOriginGroup", 1, -1, 1);
				EntitySettings.StrangeCatNameChance = builder
						.comment("The chance of strange cat to change its name.(1/x)")
						.defineInRange("TrickZombieCharmChance", 3, 1, 1000000);
				
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
					EntitySettings.EntityLiveTick.PlantLiveTick = builder
							.comment("how many ticks can plant entity live.")
							.worldRestart()
							.defineInRange("PlantLiveTick", 48000, 1, 1000000);
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
						.defineInRange("SaplingTurnOrigin", 8, 2, 100);
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
			public WorldEventSettings WorldEventSettings = new WorldEventSettings();
			public StructureSettings StructureSettings = new StructureSettings();
			public EntitySpawnSettings EntitySpawnSettings = new EntitySpawnSettings();
			
			public ForgeConfigSpec.BooleanValue CanSpawnDefaultMonster;
			public ForgeConfigSpec.BooleanValue GiveBeginnerReward;
			
			public static class WorldEventSettings{
				public EventChanceSettings EventChanceSettings = new EventChanceSettings();
				public ForgeConfigSpec.IntValue SafeDayLength;
			    public ForgeConfigSpec.IntValue ZombieAttackChance;
			    public ForgeConfigSpec.IntValue SpawnWeightIncDuration;
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
				public ForgeConfigSpec.IntValue TombStoneSpawnWeight;
				public ForgeConfigSpec.IntValue YetiZombieSpawnWeight;
			}
		}
		
		public static class EntitySettings{
			
			public ForgeConfigSpec.BooleanValue TeamAttack;
			public ForgeConfigSpec.IntValue ZombieSuperChance;
			public ForgeConfigSpec.IntValue ZombieSunChance;
			public ForgeConfigSpec.IntValue DoomRange;
			public ForgeConfigSpec.IntValue TrickZombieCharmChance;
			public ForgeConfigSpec.IntValue PlayerOriginGroup;
			public ForgeConfigSpec.IntValue StrangeCatNameChance;
			
			public EntityLiveTick EntityLiveTick = new EntityLiveTick();
			
			public static class EntityLiveTick{
				public ForgeConfigSpec.IntValue SunLiveTick;
				public ForgeConfigSpec.IntValue CoinLiveTick;
				public ForgeConfigSpec.IntValue JewelLiveTick;
				public ForgeConfigSpec.IntValue EnergyLiveTick;
				public ForgeConfigSpec.IntValue PlantLiveTick;
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
