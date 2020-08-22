package com.hungteen.pvz;


import net.minecraftforge.common.ForgeConfigSpec;

public class PVZConfig {

	public static Common COMMON_CONFIG;
	public static Client CLIENT_CONFIG;
	private static final String CONFIG_TRANSLATE = "config."+PVZMod.MOD_ID+".";
	
	public static class Common
	{
		public Common(ForgeConfigSpec.Builder builder) {
			builder.comment("Settings about blocks.").push("Block Settings");
			{
				BLOCK_SETTINGS.originBlockEffectChance = builder
						.translation(CONFIG_TRANSLATE+"origin_block")
						.comment("About the chance you got essence_ore from origin_block.the bigger the value is,the lower chance you get.(more specificly 1/x)")
						.worldRestart()
						.defineInRange("originChance", 5, 1, 100);
				BLOCK_SETTINGS.chomperGrowChance = builder
						.translation(CONFIG_TRANSLATE+"chomper_grow")
						.comment("The chance when you use bone meal to grow chomper,the bigger the less chance.")
						.worldRestart()
						.defineInRange("chomperGrow", 20, 5, 100);
				builder.comment("Setting about break blocks.").push("Break Block Setting");
				{
					BLOCK_SETTINGS.breakBlock.peaDropChance = builder
						.translation(CONFIG_TRANSLATE+"pea_drop_chance")
						.comment("the drop chance of pea when you break grass.the bigger the value is,the lower chance you get.(more specificly 1/x)")
						.worldRestart()
						.defineInRange("dropPeaChance", 16, 1, 1000);
				}
				builder.pop();
			}
			builder.pop();
			
			builder.comment("Settings about entities.").push("Entity Settings");
			{
				ENTITY_SETTINGS.canPlantAttackOtherTeam = builder
						.translation(CONFIG_TRANSLATE+"plant_attack_team")
                        .comment("if true,when plant's owner is in a team,the plant will attack the entity from other team(include player)")
                        .worldRestart()
                        .define("plantAttackTeam", false);
				ENTITY_SETTINGS.zombieSuperChance = builder
						.translation(CONFIG_TRANSLATE+"zombie_super_chance")
						.comment("the spawn chance of zombie with plant energy.(the bigger,the more chance it spawn)")
						.worldRestart()
						.defineInRange("zombieSuperChance", 1, 0, 5);
				
				builder.comment("The Max live time for Entity like sun.").push("EntityLiveTime");
				{
					ENTITY_SETTINGS.entityLiveTick.sunLiveTick = builder
							.translation(CONFIG_TRANSLATE+"sun_live_tick")
							.comment("how many ticks can the sun entity live")
						    .worldRestart()
							.defineInRange("sunLiveTick", 300, 1, 1200);
					ENTITY_SETTINGS.entityLiveTick.coinLiveTick = builder
							.translation(CONFIG_TRANSLATE+"coin_live_tick")
							.comment("how many ticks can the coin entity live")
						    .worldRestart()
							.defineInRange("coinLiveTick", 300, 1, 1200);
					ENTITY_SETTINGS.entityLiveTick.energyLiveTick = builder
							.translation(CONFIG_TRANSLATE+"energy_live_tick")
							.comment("how many ticks can the energy entity live")
						    .worldRestart()
							.defineInRange("energyLiveTick", 400, 1, 1200);
					ENTITY_SETTINGS.entityLiveTick.bulletLiveTick = builder
							.translation(CONFIG_TRANSLATE+"bullet_live_tick")
							.comment("how many ticks can bullet entity live.(don't be so small or so big)")
							.worldRestart()
							.defineInRange("bulletLiveTick", 150, 100, 1000);
				}
				builder.pop();
			}
			builder.pop();
			
			builder.comment("Settings about world.").push("World Settings");
			{
				builder.comment("Settings about world event.").push("WorldEvent Settings");
				{
					WORLD_SETTINGS.worldEventSettings.SafeDayLength = builder
						    .translation(CONFIG_TRANSLATE+"safe_day_length")
						    .comment("If you set to x,then the first x day of the world will not have any zombie attack event.")
						    .worldRestart()
						    .defineInRange("SafeDayLength", 3, 0, 100);
					
					WORLD_SETTINGS.worldEventSettings.ZombieWaveChance = builder
						    .translation(CONFIG_TRANSLATE+"zombie_attack_chance")
						    .comment("The chance related to zombie wave event. the bigger the more chance it has.(chance/100)")
						    .worldRestart()
						    .defineInRange("ZombieAttackChance", 40, 0, 100);
				}
				builder.pop();
				builder.comment("Settings about the overworld gen.").push("OverWorld Settings");
				{
					WORLD_SETTINGS.overWorldSettings.DaveVillaDistance = builder
							.translation(CONFIG_TRANSLATE+"dave_villa_distance")
							.comment("the distance value between dave villa.")
							.worldRestart()
							.defineInRange("DaveVillaDistance", 32, 1, 100);
					
					WORLD_SETTINGS.overWorldSettings.BucketHouseDistance = builder
							.translation(CONFIG_TRANSLATE+"bucket_house_distance")
							.comment("the distance value between bucket house.")
							.worldRestart()
							.defineInRange("BucketHouseDistance", 28, 1, 100);
				}
				builder.pop();
			}
			builder.pop();
		}
		
		public BlockSettings BLOCK_SETTINGS = new BlockSettings();
		
		public static class BlockSettings{
			public ForgeConfigSpec.IntValue originBlockEffectChance;
			public ForgeConfigSpec.IntValue chomperGrowChance;
			public BreakBlock breakBlock = new BreakBlock();
			public static class BreakBlock{
				public ForgeConfigSpec.IntValue peaDropChance;
			}
		}
		
		public EntitySettings ENTITY_SETTINGS = new EntitySettings();
		
		public static class EntitySettings{
			public ForgeConfigSpec.BooleanValue canPlantAttackOtherTeam;
			public ForgeConfigSpec.IntValue zombieSuperChance;
			
			public EntityLiveTick entityLiveTick = new EntityLiveTick();
			public static class EntityLiveTick{
				public ForgeConfigSpec.IntValue sunLiveTick;
				public ForgeConfigSpec.IntValue coinLiveTick;
				public ForgeConfigSpec.IntValue energyLiveTick;
				public ForgeConfigSpec.IntValue bulletLiveTick;
			}
			
		}
		
		public WorldSettings WORLD_SETTINGS = new WorldSettings();
		
		public static class WorldSettings{
			public WorldEventSettings worldEventSettings = new WorldEventSettings();
			public OverWorldSettings overWorldSettings = new OverWorldSettings();
				
			public static class WorldEventSettings{
				public ForgeConfigSpec.IntValue SafeDayLength;
			    public ForgeConfigSpec.IntValue ZombieWaveChance;
			}
			
			public static class OverWorldSettings{
				public ForgeConfigSpec.IntValue DaveVillaDistance;
			    public ForgeConfigSpec.IntValue BucketHouseDistance;
			}
		}
	}
	
	public static class Client
	{
		public Client(ForgeConfigSpec.Builder builder) {
		}
	}
}
