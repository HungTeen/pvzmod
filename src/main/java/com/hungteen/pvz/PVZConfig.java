package com.hungteen.pvz;


import net.minecraftforge.common.ForgeConfigSpec;

public class PVZConfig {

	public static Common COMMON_CONFIG;
	public static Client CLIENT_CONFIG;
	private static final String CONFIG_TRANSLATE = PVZMod.MOD_ID+".config.";
	
	public static class Common
	{
		public Common(ForgeConfigSpec.Builder builder) {
			builder.comment("Settings that affect the whole mod.")
			.push("Global Settings");
			{
				GLOBALSETTINGS.pvzDifficulty = builder
						.translation(CONFIG_TRANSLATE+"difficulty")
						.comment("set the difficulty of pvzmod(1=easy,2=normal,3=hard,4=crazy).")
						.worldRestart()
						.defineInRange("difficulty", 2, 1, 4);
			}
		}
		
		public GlobalSettings GLOBALSETTINGS = new GlobalSettings();
		
		public static class GlobalSettings
		{
			public ForgeConfigSpec.IntValue pvzDifficulty;
		}
	}
	
	public static class Client
	{
		public Client(ForgeConfigSpec.Builder builder) {
		}
	}
	
	public static int getPVZDifficulty()
	{
		return COMMON_CONFIG.GLOBALSETTINGS.pvzDifficulty.get().intValue();
	}
}
