package com.hungteen.pvz.utils.enums;

import com.hungteen.pvz.common.capability.player.PlayerDataManager;
import com.hungteen.pvz.utils.ConfigUtil;

import net.minecraft.util.text.TranslationTextComponent;

public enum Resources {

	/* player tree maxLevel */
	TREE_LVL(1, 200),
	/* xp of player tree maxLevel */
	TREE_XP(0, 0),
	/* money, common currency */
	MONEY(0, 1000000),
	/* jewel, special currency */
	GEM_NUM(0, 1000000),
	/* sun amount, maximum is limited by tree maxLevel*/
	SUN_NUM(0, 0),
	/* max plant food amount */
	MAX_ENERGY_NUM(1, 10),
	/* plant food amount */
	ENERGY_NUM(0, 0),
	/* the tick to control fog display */
//	NO_FOG_TICK(- 9999999, 9999999),
	/* kill zombie count */
//	KILL_COUNT(0, 9999999),
	/* the chance to use slot machine */
	LOTTERY_CHANCE(0, 9999999),
	/* the group of player */
	GROUP_TYPE(- 2, 2),
	/* card slot */
	SLOT_NUM(1, 9),
	/* mission use */
	MISSION_FINISH_TIME(0, 9999999),
	MISSION_TYPE(0, 3),
	MISSION_STAGE(0, 4),
	MISSION_VALUE(0, 9999999)
	;
	
	/**
	 * {@link PlayerDataManager#PlayerDataManager(net.minecraft.entity.player.PlayerEntity)}
	 */
	public static int getInitialValue(Resources res) {
		switch (res) {
		case SUN_NUM: return 50;
		case LOTTERY_CHANCE: return 10;
		case GROUP_TYPE: return ConfigUtil.getPlayerInitialGroup();
//		case NO_FOG_TICK: return 0;
		default: return res.min;
		}
	}
	
	public static final int INF = 9999999;
	public final int min;
	public final int max;
	
	private Resources(int min, int max) {
		this.min = min;
		this.max = max;
	}
	
	public TranslationTextComponent getText() {
		return new TranslationTextComponent("resource.pvz." + this.toString().toLowerCase());
	}
}
