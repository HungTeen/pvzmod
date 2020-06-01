package com.hungteen.pvzmod.util.interfaces;

import com.hungteen.pvzmod.util.enums.Plants;

public interface IPlant {
	
	/**
	 * 植物枚举的名字
	 */
	Plants getPlantEnumName();
	
	/**
	 * 植物大招时间
	 */
	int getSuperTimeLength();
	
	/**
	 * 植物卡冷却
	 */
	int getCoolDownTime();
	
	/**
	 * 阳光消耗
	 */
	int getSunCost();
	
	/**
	 * 血量
	 */
	float getLife();
}
