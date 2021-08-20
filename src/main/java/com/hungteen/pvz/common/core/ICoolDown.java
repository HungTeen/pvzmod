package com.hungteen.pvz.common.core;

/**
 * use by the cool down of summon card. <br>
 * {@link PlantType}
 */
public interface ICoolDown {
	
	public static final ICoolDown DEFAULT = lvl -> {
		return 100;
	};
	
	/**
	 * get cool down time by level.
	 */
	int getCD(int lvl);
}
