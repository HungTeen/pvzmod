package com.hungteen.pvz.common.impl.plant;

import com.hungteen.pvz.common.core.ICoolDown;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlantUtil;

public class PlantCardCD {

	public static final ICoolDown SUPER_FAST = lvl -> {
		return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 160, 120);
	};
	
	public static final ICoolDown HUGE_FAST = lvl -> {
		return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 200, 160);
	};
	
	public static final ICoolDown VERY_FAST = lvl -> {
		return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 240, 200);
	};
	
	public static final ICoolDown FAST = lvl -> {
		return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 300, 240);
	};
	
	public static final ICoolDown LITTLE_FAST = lvl -> {
		return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 400, 320);
	};
	
	public static final ICoolDown NORMAL = lvl -> {
		return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 500, 420);
	};
	
	public static final ICoolDown LITTLE_SLOW = lvl -> {
		return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 640, 540);
	};
	
	public static final ICoolDown SLOW = lvl -> {
		return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 840, 720);
	};
	
	public static final ICoolDown VERY_SLOW = lvl -> {
		return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 1200, 1000);
	};
	
	public static final ICoolDown HUGE_SLOW = lvl -> {
		return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 2400, 2000);
	};
	
	public static final ICoolDown SUPER_SLOW = lvl -> {
		return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 4800, 4000);
	};
	
}
