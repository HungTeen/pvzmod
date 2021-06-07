package com.hungteen.pvz.utils.enums;

import net.minecraft.util.text.TranslationTextComponent;

public enum Resources {

	TREE_LVL(1, 100),
	TREE_XP(0, 0),//max no use.
	MONEY(0, 9999999),
	GEM_NUM(0, 9999999),
	SUN_NUM(0, 0),//max no use.
	ENERGY_NUM(0, 0),//max no use.
	MAX_ENERGY_NUM(1, 10),
	NO_FOG_TICK(- 9999999, 9999999),
	KILL_COUNT(0, 9999999),
	LOTTERY_CHANCE(0, 9999999),
	GROUP_TYPE(- 2, 2);
	
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
