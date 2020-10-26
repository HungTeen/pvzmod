package com.hungteen.pvz;


import net.minecraftforge.common.ForgeConfigSpec;

public class PVZConfig {

	public static Common COMMON_CONFIG;
	public static Client CLIENT_CONFIG;
//	private static final String CONFIG_TRANSLATE = "config."+PVZMod.MOD_ID+".";
	
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
						    .defineInRange("ZombieAttackChance", 40, 0, 100);
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
				}
				builder.pop();
				builder.comment("Other World Settings").push("Other World Settings");
				{
					WorldSettings.CanSpawnDefaultMonster = builder
							.comment("if false,there will have no monster of default mc spawn in overworld.")
							.define("CanSpawnDefaultMonster", true);
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
				EntitySettings.DoomRange = builder
						.comment("The width range when doom shroom explosion will destroy,0 means no destroy.")
						.defineInRange("DoomRange", 3, 0, 10);
				builder.comment("The Max live time for Entity like sun.").push("EntityLiveTime");
				{
					EntitySettings.EntityLiveTick.SunLiveTick = builder
							.comment("how many ticks can the sun entity live.")
							.defineInRange("SunLiveTick", 400, 1, 1200);
					EntitySettings.EntityLiveTick.CoinLiveTick = builder
							.comment("how many ticks can the coin entity live(include gem).")
							.defineInRange("CoinLiveTick", 400, 1, 1200);
					EntitySettings.EntityLiveTick.EnergyLiveTick = builder
							.comment("how many ticks can the energy entity live.")
							.defineInRange("EnergyLiveTick", 400, 1, 1200);
					EntitySettings.EntityLiveTick.BulletLiveTick = builder
							.comment("how many ticks can bullet entity live.")
							.defineInRange("BulletLiveTick", 150, 100, 1000);
					EntitySettings.EntityLiveTick.PlantLiveTick = builder
							.comment("how many ticks can plant entity live.")
							.worldRestart()
							.defineInRange("PlantLiveTick", 24000, 1, 1000000);
				}
				builder.pop();
			}
			builder.pop();
			//Block Settings 
			builder.comment("Settings about blocks.").push("Block Settings");
			{
				BlockSettings.OriginBlockEffectChance = builder
						.comment("About the chance you got essence_ore from origin_block.the bigger the value is,the lower chance you get(more specificly 1/x).")
						.defineInRange("OriginChance", 5, 1, 100);
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
				}
				builder.pop();
			}
			builder.pop();
			
		}
		
		public WorldSettings WorldSettings = new WorldSettings();
		public EntitySettings EntitySettings = new EntitySettings();
		public BlockSettings BlockSettings = new BlockSettings();
		
		public static class WorldSettings{
			public WorldEventSettings WorldEventSettings = new WorldEventSettings();
			public StructureSettings StructureSettings = new StructureSettings();
			public EntitySpawnSettings EntitySpawnSettings = new EntitySpawnSettings();
			
			public ForgeConfigSpec.BooleanValue CanSpawnDefaultMonster;
			
			public static class WorldEventSettings{
				public ForgeConfigSpec.IntValue SafeDayLength;
			    public ForgeConfigSpec.IntValue ZombieAttackChance;
			}
			
			public static class StructureSettings{
				public ForgeConfigSpec.IntValue DaveVillaDistance;
			    public ForgeConfigSpec.IntValue BucketHouseDistance;
			    public ForgeConfigSpec.IntValue DolphinHouseDistance;
			}
			
			public static class EntitySpawnSettings{
				public ForgeConfigSpec.IntValue SunSpawnWeight;
				public ForgeConfigSpec.IntValue ZombieDolphinSpawnWeight;
				public ForgeConfigSpec.IntValue FoodieZombieSpawnWeight;
				public ForgeConfigSpec.IntValue LavaZombieSpawnWeight;
			}
		}
		
		public static class EntitySettings{
			public ForgeConfigSpec.BooleanValue TeamAttack;
			public ForgeConfigSpec.IntValue ZombieSuperChance;
			public ForgeConfigSpec.IntValue DoomRange;
			
			public EntityLiveTick EntityLiveTick = new EntityLiveTick();
			
			public static class EntityLiveTick{
				public ForgeConfigSpec.IntValue SunLiveTick;
				public ForgeConfigSpec.IntValue CoinLiveTick;
				public ForgeConfigSpec.IntValue EnergyLiveTick;
				public ForgeConfigSpec.IntValue BulletLiveTick;
				public ForgeConfigSpec.IntValue PlantLiveTick;
			}
			
		}
		
		public static class BlockSettings{
			public ForgeConfigSpec.IntValue OriginBlockEffectChance;
			public ForgeConfigSpec.IntValue ChomperGrowChance;
			public ForgeConfigSpec.IntValue SaplingTurnOriginChance;
			
			public BreakBlock BreakBlock = new BreakBlock();
			public static class BreakBlock{
				public ForgeConfigSpec.IntValue PeaDropChance;
			}
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
		public static class ResourceRender{
			public ForgeConfigSpec.BooleanValue RenderSunNumBar;
			public ForgeConfigSpec.BooleanValue RenderEnergyNumBar;
			public ForgeConfigSpec.BooleanValue RenderMoneyBar;
		}
		
		public static class OtherSettings{
			public ForgeConfigSpec.BooleanValue ShowPVZMainMenu;
		}
	}
}
