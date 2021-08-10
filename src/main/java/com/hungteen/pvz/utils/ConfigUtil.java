package com.hungteen.pvz.utils;

import com.hungteen.pvz.PVZConfig;

public class ConfigUtil {

	public static boolean isTeamAttackEnable() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.TeamAttack.get();
	}
	
	public static boolean enableZombieDropParts() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.ZombieSetting.EnableZombieDropHands.get();
	}
	
	public static int getPlayerInitialGroup() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.PlayerInitialGroup.get();
	}
	
}
