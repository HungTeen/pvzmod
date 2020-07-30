package com.hungteen.pvz;


import net.minecraftforge.common.ForgeConfigSpec;

public class PVZConfig {

	public static Common COMMON_CONFIG;
	public static Client CLIENT_CONFIG;
	private static final String CONFIG_TRANSLATE = PVZMod.MOD_ID+".config.";
	
	public static class Common
	{
		public Common(ForgeConfigSpec.Builder builder) {
			builder.comment("Settings about blocks.")
			.push("Block Settings");
			{
				BLOCK_SETTINGS.originBlockEffectChance = builder
						.translation(CONFIG_TRANSLATE+"origin_block")
						.comment("About the chance you got essence_ore from origin_block.the bigger the value is,the lower chance you get.(more specificly 1/x)")
						.worldRestart()
						.defineInRange("origin_chacne", 5, 1, 100);
			}
			builder.pop();
			builder.comment("Settings about entities.").push("Entity Settings");
			{
				builder.comment("The Max live time for drops like sun.").push("DropLiveTime");
				{
					ENTITY_SETTINGS.dropLiveTick.sunLiveTick = builder
							.translation(CONFIG_TRANSLATE+"sun_live_tick")
							.comment("how many ticks can the sun entity live")
						    .worldRestart()
							.defineInRange("sunLiveTick", 300, 1, 1200);
					ENTITY_SETTINGS.dropLiveTick.coinLiveTick = builder
							.translation(CONFIG_TRANSLATE+"coin_live_tick")
							.comment("how many ticks can the coin entity live")
						    .worldRestart()
							.defineInRange("coinLiveTick", 300, 1, 1200);
					ENTITY_SETTINGS.dropLiveTick.energyLiveTick = builder
							.translation(CONFIG_TRANSLATE+"energy_live_tick")
							.comment("how many ticks can the energy entity live")
						    .worldRestart()
							.defineInRange("energyLiveTick", 400, 1, 1200);
				}
				builder.pop();
			}
			builder.pop();
		}
		
		public BlockSettings BLOCK_SETTINGS = new BlockSettings();
		
		public static class BlockSettings
		{
			public ForgeConfigSpec.IntValue originBlockEffectChance;
		}
		
		public EntitySettings ENTITY_SETTINGS = new EntitySettings();
		
		public static class EntitySettings
		{
			public DropLiveTick dropLiveTick = new DropLiveTick();
			public static class DropLiveTick
			{
				public ForgeConfigSpec.IntValue sunLiveTick;
				public ForgeConfigSpec.IntValue coinLiveTick;
				public ForgeConfigSpec.IntValue energyLiveTick;
			}
		}
	}
	
	public static class Client
	{
		public Client(ForgeConfigSpec.Builder builder) {
		}
	}
}
