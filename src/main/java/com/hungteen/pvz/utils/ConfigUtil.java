package com.hungteen.pvz.utils;

import com.hungteen.pvz.PVZConfig;

public class ConfigUtil {

	public static boolean isTeamAttackEnable() {
		return PVZConfig.COMMON_CONFIG.RuleSettings.TeamAttack.get();
	}
	
	public static boolean enableZombieDropParts() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.ZombieSetting.EnableZombieDropHands.get();
	}
	
	public static int getPlayerInitialGroup() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.PlayerInitialGroup.get();
	}
	
	public static int getIncDifficulty() {
		return PVZConfig.COMMON_CONFIG.InvasionSettings.IncDifficulty.get();
	}
	
	public static int getSpawnMultiple() {
		return PVZConfig.COMMON_CONFIG.InvasionSettings.MaxSpawnWeightMultiple.get();
	}
	
	public static int getGenOriginOreChance() {
		return PVZConfig.COMMON_CONFIG.WorldSettings.GenOriginOreChance.get();
	}
	
	public static int getGenAmethystOreChance() {
		return PVZConfig.COMMON_CONFIG.WorldSettings.GenAmethystOreChance.get();
	}
	
	public static int getBaseSun() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.PlayerBaseSunAmount.get();
	}
	
}
