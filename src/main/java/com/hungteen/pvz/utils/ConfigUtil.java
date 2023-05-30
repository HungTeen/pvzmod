package com.hungteen.pvz.utils;

import com.hungteen.pvz.PVZConfig;

public class ConfigUtil {

	public static boolean isTeamAttackEnable() {
		return PVZConfig.COMMON_CONFIG.RuleSettings.TeamAttack.get();
	}

	public static boolean enableZombieDropParts() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.ZombieSetting.EnableZombieDropHands.get();
	}
	public static boolean jackinboxBreak() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.ZombieSetting.JackinboxBreak.get();
	}
	public static boolean immuineToDamage() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.ZombieSetting.ImmuneDamage.get();
	}

	public static boolean enableHugeWave(){
		return PVZConfig.COMMON_CONFIG.InvasionSettings.EnableHugeWave.get();
	}

	public static boolean scatteredInvasions(){
		return PVZConfig.COMMON_CONFIG.InvasionSettings.ScatterInvasions.get();
	}

	public static boolean isPlantMode(){
		return getPlayerInitialGroup() > 0;
	}

	public static int getPlayerInitialGroup() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.PlayerInitialGroup.get();
	}
	
	public static int getGenOriginOreChance() {
		return PVZConfig.COMMON_CONFIG.WorldSettings.GenOriginOreChance.get();
	}

	public static int getGenAmethystOreChance() {
		return PVZConfig.COMMON_CONFIG.WorldSettings.GenAmethystOreChance.get();
	}
	public static int getGenLunarStoneChance() {
		return PVZConfig.COMMON_CONFIG.WorldSettings.GenLunarStoneChance.get();
	}
	
	public static int getBaseSun() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.PlayerBaseSunAmount.get();
	}

	public static int getLimitPlantCount() {
		return PVZConfig.COMMON_CONFIG.RuleSettings.LimitPlantCount.get();
	}
	public static int despawnOwnedEntityRange() {
		return PVZConfig.COMMON_CONFIG.RuleSettings.DespawnOwnedEntityRange.get();
	}

	public static boolean needUnlockToPlant() {
		return PVZConfig.COMMON_CONFIG.RuleSettings.NeedUnlockToPlant.get();
	}
	public static boolean AllZombieGiveXP() {
		return PVZConfig.COMMON_CONFIG.RuleSettings.AllZombieGiveXP.get();
	}

	public static boolean renderSunBar() {
		return PVZConfig.CLIENT_CONFIG.OverlaySettings.RenderSunBar.get();
	}
	
	public static boolean renderMoneyBar() {
		return PVZConfig.CLIENT_CONFIG.OverlaySettings.RenderMoneyBar.get();
	}
	
	public static boolean renderGemBar() {
		return PVZConfig.CLIENT_CONFIG.OverlaySettings.RenderGemBar.get();
	}
	
	public static boolean renderTreeLevel() {
		return PVZConfig.CLIENT_CONFIG.OverlaySettings.RenderTreeLevel.get();
	}

	public static boolean isRaidEnable() {
		return PVZConfig.COMMON_CONFIG.ChallengeSettings.EnableChallenge.get();
	}

	public static int getRaidWaitTime() {
		return PVZConfig.COMMON_CONFIG.ChallengeSettings.ChallengeWaitTime.get();
	}

	public static int getRaidRange() {
		return PVZConfig.COMMON_CONFIG.ChallengeSettings.ChallengeRange.get();
	}

	public static int getLimitDamage(){
		return PVZConfig.COMMON_CONFIG.RuleSettings.MaxDamageLimit.get();
	}

	public static int getPlantMinimumTick(){
		return PVZConfig.COMMON_CONFIG.EntitySettings.PlantSetting.PlantMinimumLiveTick.get();
	}
}
