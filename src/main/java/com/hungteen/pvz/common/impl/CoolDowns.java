package com.hungteen.pvz.common.impl;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.ICoolDown;
import com.hungteen.pvz.utils.MathUtil;

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
			return 50;
		}

	};

	//4s to 2.5s.
	public static final ICoolDown SUPER_FAST = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.super_fast";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getIncreaseAverage(lvl, SkillTypes.COOL_DOWN_LEVEL, 80, 50);
		}
		
	};

	//8s to 5s.
	public static final ICoolDown HUGE_FAST = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.huge_fast";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getIncreaseAverage(lvl, SkillTypes.COOL_DOWN_LEVEL, 160, 100);
		}
		
	};

	//15s to 10s.
	public static final ICoolDown VERY_FAST = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.very_fast";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getIncreaseAverage(lvl, SkillTypes.COOL_DOWN_LEVEL, 300, 200);
		}
		
	};

	//20s to 15s.
	public static final ICoolDown FAST = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.fast";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getIncreaseAverage(lvl, SkillTypes.COOL_DOWN_LEVEL, 400, 300);
		}
		
	};

	//25s to 18s.
	public static final ICoolDown LITTLE_FAST = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.little_fast";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getIncreaseAverage(lvl, SkillTypes.COOL_DOWN_LEVEL, 500, 360);
		}
		
	};

	//32s to 24s.
	public static final ICoolDown NORMAL = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.normal";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getIncreaseAverage(lvl, SkillTypes.COOL_DOWN_LEVEL, 640, 480);
		}
		
	};

	//40s to 30s.
	public static final ICoolDown LITTLE_SLOW = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.little_slow";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getIncreaseAverage(lvl, SkillTypes.COOL_DOWN_LEVEL, 800, 600);
		}
		
	};

	//50s to 36s.
	public static final ICoolDown SLOW = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.slow";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getIncreaseAverage(lvl, SkillTypes.COOL_DOWN_LEVEL, 1000, 720);
		}
		
	};

	//72s to 48s.
	public static final ICoolDown VERY_SLOW = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.very_slow";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getIncreaseAverage(lvl, SkillTypes.COOL_DOWN_LEVEL, 1440, 960);
		}
		
	};

	//120s to 80s.
	public static final ICoolDown HUGE_SLOW = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.huge_slow";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getIncreaseAverage(lvl, SkillTypes.COOL_DOWN_LEVEL, 2400, 1600);
		}
		
	};

	//240s to 160s
	public static final ICoolDown SUPER_SLOW = new ICoolDown() {
		
		@Override
		public String getTranslateKey() {
			return "misc.pvz.cd.super_slow";
		}
		
		@Override
		public int getCD(int lvl) {
			return MathUtil.getIncreaseAverage(lvl, SkillTypes.COOL_DOWN_LEVEL, 4800, 3200);
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
