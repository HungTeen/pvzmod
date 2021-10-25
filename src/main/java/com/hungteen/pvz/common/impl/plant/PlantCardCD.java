package com.hungteen.pvz.common.impl.plant;

import com.hungteen.pvz.api.types.ICoolDown;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlantUtil;

public class PlantCardCD {

	public static final ICoolDown SUPER_FAST = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.super_fast";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 160, 120);
		}
		
	};
	
	public static final ICoolDown HUGE_FAST = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.huge_fast";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 200, 160);
		}
		
	};
	
	public static final ICoolDown VERY_FAST = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.very_fast";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 240, 200);
		}
		
	};
	
	public static final ICoolDown FAST = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.fast";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 300, 240);
		}
		
	};
	
	public static final ICoolDown LITTLE_FAST = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.little_fast";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 400, 320);
		}
		
	};
	
	public static final ICoolDown NORMAL = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.normal";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 500, 420);
		}
		
	};
	
	public static final ICoolDown LITTLE_SLOW = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.little_slow";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 640, 540);
		}
		
	};
	
	public static final ICoolDown SLOW = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.slow";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 840, 720);
		}
		
	};
	
	public static final ICoolDown VERY_SLOW = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.very_slow";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 1200, 1000);
		}
		
	};
	
	public static final ICoolDown HUGE_SLOW = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.huge_slow";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 2400, 2000);
		}
		
	};
	
	public static final ICoolDown SUPER_SLOW = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.super_slow";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getProgressAverage(lvl, PlantUtil.MAX_PLANT_LEVEL, 4800, 4000);
		}
		
	};
	
}
