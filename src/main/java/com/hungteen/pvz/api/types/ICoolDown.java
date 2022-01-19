package com.hungteen.pvz.api.types;

import com.hungteen.pvz.common.impl.plant.PlantType;

/**
 * use by the cool down of summon card. <br>
 * {@link PlantType}
 */
public interface ICoolDown {

	/**
	 * get cool down by max level.
	 */
	int getCD(int lvl);
	
	String getTranslateKey();
	
}
