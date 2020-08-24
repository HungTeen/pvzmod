package com.hungteen.pvz;


import net.minecraftforge.common.ForgeConfigSpec;

public class PVZConfig {

	public static Common COMMON_CONFIG;
	public static Client CLIENT_CONFIG;
	private static final String CONFIG_TRANSLATE = "config."+PVZMod.MOD_ID+".";
	
	public static class Common
	{
		public Common(ForgeConfigSpec.Builder builder) {
			//World Settings
			builder.comment("Settings about world.").push("World Settings");
			{
				builder.comment("Settings about world event.").push("WorldEvent Settings");
				{
					WorldSettings.WorldEventSettings.SafeDayLength = builder
						    .translation(CONFIG_TRANSLATE+"safe_day_length")
						    .comment("If you set to x,then the first x day of the world will not have any zombie attack event.")
						    .worldRestart()
						    .defineInRange("SafeDayLength", 3, 0, 100);
					
					WorldSettings.WorldEventSettings.ZombieAttackChance = builder
						    .translation(CONFIG_TRANSLATE+"zombie_attack_chance")
						    .comment("The chance related to zombie wave event. the bigger the more chance it has.(chance/100)")
						    .worldRestart()
						    .defineInRange("ZombieAttackChance", 40, 0, 100);
				}
				builder.pop();
				builder.comment("Settings about the overworld gen.").push("OverWorld Settings");
				{
					WorldSettings.OverWorldSettings.DaveVillaDistance = builder
							.translation(CONFIG_TRANSLATE+"dave_villa_distance")
							.comment("the distance value between dave villa.")
							.worldRestart()
							.defineInRange("DaveVillaDistance", 40, 1, 100);
					
					WorldSettings.OverWorldSettings.BucketHouseDistance = builder
							.translation(CONFIG_TRANSLATE+"bucket_house_distance")
							.comment("the distance value between bucket house.")
							.worldRestart()
							.defineInRange("BucketHouseDistance", 32, 1, 100);
				}
				builder.pop();
			}
			builder.pop();
			//Entity Settings
			builder.comment("Settings about entities.").push("Entity Settings");
			{
				EntitySettings.CanPlantAttackOtherTeam = builder
						.translation(CONFIG_TRANSLATE+"plant_attack_team")
                        .comment("if true,when plant's owner is in a team,the plant will attack the entity from other team(include player)")
                        .worldRestart()
                        .define("PlantAttackTeam", false);
				EntitySettings.ZombieSuperChance = builder
						.translation(CONFIG_TRANSLATE+"zombie_super_chance")
						.comment("the spawn chance of zombie with plant energy.(the bigger,the more chance it spawn)")
						.worldRestart()
						.defineInRange("ZombieSuperChance", 1, 0, 50);
				EntitySettings.CanSpawnDefaultMonster = builder
						.translation(CONFIG_TRANSLATE+"can_spawn_default_monster")
						.comment("if false,there will have no monster of default mc spawn in overworld.")
						.worldRestart()
						.define("CanSpawnDefaultMonster", true);
				
				builder.comment("The Max live time for Entity like sun.").push("EntityLiveTime");
				{
					EntitySettings.EntityLiveTick.SunLiveTick = builder
							.translation(CONFIG_TRANSLATE+"sun_live_tick")
							.comment("how many ticks can the sun entity live")
						    .worldRestart()
							.defineInRange("SunLiveTick", 300, 1, 1200);
					EntitySettings.EntityLiveTick.CoinLiveTick = builder
							.translation(CONFIG_TRANSLATE+"coin_live_tick")
							.comment("how many ticks can the coin entity live")
						    .worldRestart()
							.defineInRange("CoinLiveTick", 300, 1, 1200);
					EntitySettings.EntityLiveTick.EnergyLiveTick = builder
							.translation(CONFIG_TRANSLATE+"energy_live_tick")
							.comment("how many ticks can the energy entity live")
						    .worldRestart()
							.defineInRange("EnergyLiveTick", 400, 1, 1200);
					EntitySettings.EntityLiveTick.BulletLiveTick = builder
							.translation(CONFIG_TRANSLATE+"bullet_live_tick")
							.comment("how many ticks can bullet entity live.(don't be so small or so big)")
							.worldRestart()
							.defineInRange("BulletLiveTick", 150, 100, 1000);
				}
				builder.pop();
				builder.comment("The Spawn Weight of entity").push("EntitySpawnWeight");
				{
					EntitySettings.EntitySpawnWeight.SunSpawnWeight = builder
							.translation(CONFIG_TRANSLATE+"sun_spawn_weight")
							.comment("spawn weight of Sun")
							.worldRestart()
							.defineInRange("SunSpawnWeight", 50, 1, 200);
					EntitySettings.EntitySpawnWeight.NormalZombieSpawnWeight = builder
							.translation(CONFIG_TRANSLATE+"normal_zombie_spawn_weight")
							.comment("spawn weight of NormalZombie")
							.worldRestart()
							.defineInRange("NormalZombieSpawnWeight", 60, 1, 200);
					EntitySettings.EntitySpawnWeight.FlagZombieSpawnWeight = builder
							.translation(CONFIG_TRANSLATE+"flag_zombie_spawn_weight")
							.comment("spawn weight of FlagZombie")
							.worldRestart()
							.defineInRange("FlagZombieSpawnWeight", 2, 1, 200);
					EntitySettings.EntitySpawnWeight.ConeHeadZombieSpawnWeight = builder
							.translation(CONFIG_TRANSLATE+"conehead_zombie_spawn_weight")
							.comment("spawn weight of ConeHeadZombie")
							.worldRestart()
							.defineInRange("ConeHeadZombieSpawnWeight", 20, 1, 200);
					EntitySettings.EntitySpawnWeight.PoleZombieSpawnWeight = builder
							.translation(CONFIG_TRANSLATE+"pole_zombie_spawn_weight")
							.comment("spawn weight of PoleZombie")
							.worldRestart()
							.defineInRange("PoleZombieSpawnWeight", 20, 1, 200);
					EntitySettings.EntitySpawnWeight.BucketHeadZombieSpawnWeight = builder
							.translation(CONFIG_TRANSLATE+"buckethead_zombie_spawn_weight")
							.comment("spawn weight of BucketHeadZombie")
							.worldRestart()
							.defineInRange("BucketHeadZombieSpawnWeight", 4, 1, 200);
				}
				builder.pop();
				builder.comment("The chance of special item drop").push("ItemDropChance");
				{
					EntitySettings.EntityDropItem.ZombieFlagDropChance = builder
							.translation(CONFIG_TRANSLATE+"zombie_flag_drop_chance")
                            .comment("Zombie Flag Drop Chance,the bigger the less chance,(1/x)")
                            .worldRestart()
                            .defineInRange("ZombieFlagDropChance", 50, 1, 1000);
					EntitySettings.EntityDropItem.ConeHeadDropChance = builder
							.translation(CONFIG_TRANSLATE+"conehead_drop_chance")
                            .comment("ConeHead Drop Chance,the bigger the less chance,(1/x)")
                            .worldRestart()
                            .defineInRange("ConeHeadDropChance", 30, 1, 1000);
					EntitySettings.EntityDropItem.BucketHeadDropChance = builder
							.translation(CONFIG_TRANSLATE+"buckethead_drop_chance")
                            .comment("BucketHead Drop Chance,the bigger the less chance,(1/x)")
                            .worldRestart()
                            .defineInRange("BucketHeadDropChance", 40, 1, 1000);
				}
				builder.pop();
			}
			builder.pop();
			//Block Settings 
			builder.comment("Settings about blocks.").push("Block Settings");
			{
				BlockSettings.OriginBlockEffectChance = builder
						.translation(CONFIG_TRANSLATE+"origin_block")
						.comment("About the chance you got essence_ore from origin_block.the bigger the value is,the lower chance you get.(more specificly 1/x)")
						.worldRestart()
						.defineInRange("OriginChance", 5, 1, 100);
				BlockSettings.ChomperGrowChance = builder
						.translation(CONFIG_TRANSLATE+"chomper_grow")
						.comment("The chance when you use bone meal to grow chomper,the bigger the less chance.")
						.worldRestart()
						.defineInRange("ChomperGrow", 20, 5, 100);
				builder.comment("Setting about break blocks.").push("Break Block Setting");
				{
					BlockSettings.BreakBlock.PeaDropChance = builder
						.translation(CONFIG_TRANSLATE+"pea_drop_chance")
						.comment("the drop chance of pea when you break grass.the bigger the value is,the lower chance you get.(more specificly 1/x)")
						.worldRestart()
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
			public OverWorldSettings OverWorldSettings = new OverWorldSettings();
				
			public static class WorldEventSettings{
				public ForgeConfigSpec.IntValue SafeDayLength;
			    public ForgeConfigSpec.IntValue ZombieAttackChance;
			}
			
			public static class OverWorldSettings{
				public ForgeConfigSpec.IntValue DaveVillaDistance;
			    public ForgeConfigSpec.IntValue BucketHouseDistance;
			}
		}
		
		public static class EntitySettings{
			public ForgeConfigSpec.BooleanValue CanPlantAttackOtherTeam;
			public ForgeConfigSpec.IntValue ZombieSuperChance;
			public ForgeConfigSpec.BooleanValue CanSpawnDefaultMonster;
			
			public EntityLiveTick EntityLiveTick = new EntityLiveTick();
			public EntitySpawnWeight EntitySpawnWeight = new EntitySpawnWeight();
			public EntityDropItem EntityDropItem = new EntityDropItem();
			
			public static class EntityDropItem{
				public ForgeConfigSpec.IntValue ZombieFlagDropChance;
				public ForgeConfigSpec.IntValue ConeHeadDropChance;
				public ForgeConfigSpec.IntValue BucketHeadDropChance;
			}
			
			public static class EntitySpawnWeight{
				public ForgeConfigSpec.IntValue SunSpawnWeight;
				//zombie
				public ForgeConfigSpec.IntValue NormalZombieSpawnWeight;
				public ForgeConfigSpec.IntValue FlagZombieSpawnWeight;
				public ForgeConfigSpec.IntValue ConeHeadZombieSpawnWeight;
				public ForgeConfigSpec.IntValue PoleZombieSpawnWeight;
				public ForgeConfigSpec.IntValue BucketHeadZombieSpawnWeight;
			}
			
			public static class EntityLiveTick{
				public ForgeConfigSpec.IntValue SunLiveTick;
				public ForgeConfigSpec.IntValue CoinLiveTick;
				public ForgeConfigSpec.IntValue EnergyLiveTick;
				public ForgeConfigSpec.IntValue BulletLiveTick;
			}
			
		}
		
		public static class BlockSettings{
			public ForgeConfigSpec.IntValue OriginBlockEffectChance;
			public ForgeConfigSpec.IntValue ChomperGrowChance;
			public BreakBlock BreakBlock = new BreakBlock();
			public static class BreakBlock{
				public ForgeConfigSpec.IntValue PeaDropChance;
			}
		}
		
	}
	
	public static class Client
	{
		public Client(ForgeConfigSpec.Builder builder) {
		}
	}
}
