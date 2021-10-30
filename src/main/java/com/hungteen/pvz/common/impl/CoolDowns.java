package com.hungteen.pvz.common.impl;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.ICoolDown;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlantUtil;

import javax.annotation.Nullable;
import java.util.*;

public abstract class CoolDowns implements ICoolDown {

	private static final List<ICoolDown> LIST = new ArrayList<>();
	private static final Map<String, ICoolDown> MAP = new HashMap<>();

	public static final ICoolDown DEFAULT = new ICoolDown() {

		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.default";
		}

		@Override
		public int getCD(int lvl) {
			return 100;
		}

	};

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

	public static void registerCD(ICoolDown cd){
		if(! MAP.containsKey(cd.toString())){
			MAP.put(cd.toString(), cd);
		} else{
			PVZMod.LOGGER.warn("CD Register : Duplicate CoolDown Type.");
		}
	}

	public static void registerCDs(Collection<ICoolDown> cds){
		cds.forEach(cd -> registerCD(cd));
	}

	public static void register(){
		PVZAPI.get().registerCDs(LIST);
	}

	@Nullable
	public static ICoolDown getCDByName(String name){
		return MAP.getOrDefault(name, null);
	}

	private final String name;

	protected CoolDowns(String name){
		this.name = name;
		LIST.add(this);
	}

	@Override
	public String toString() {
		return name;
	}
}
