package com.hungteen.pvz.utils.enums;

import javax.annotation.Nullable;

public class Enums {

	public enum Guis{
		TRADE(1);
		public int guiId;
		Guis(int id)
		{
			guiId=id;
		}
		
		@Nullable
		public static Guis getById(final int id) {
			switch(id) {
			case 1: return TRADE;
			default:return null;
			}
		}
	}
	
	public static final class RGBIntegers {
		public static final int BLACK = 0;
		public static final int BLUE = 255;
		public static final int BONE = 16448150;
		public static final int BRIGHT_TURQUOISE = 58861;
		public static final int BROWN = 9593401;
		public static final int CYAN = 65535;
		public static final int DARK_GRAY = 1973526;
		public static final int DARK_LIME_GREEN = 39168;
		public static final int DARK_VIOLET = 8519858;
		public static final int DE_YORK = 8699004;
		public static final int DEEP_PINK = 16711794;
		public static final int ELECTRIC_BLUE = 8191999;
		public static final int ELECTRIC_LIME = 13622528;
		public static final int GOLD = 13413376;
		public static final int GOLD_YELLOW = 16768768;
		public static final int GOLDEN_POPPY = 14073088;
		public static final int GREEN = 65280;
		public static final int HELIOTROPE = 12732927;
		public static final int IRIS_BLUE = 44504;
		public static final int LAVENDER_BLUSH = 16769787;
		public static final int LIGHT_CORAL = 15698295;
		public static final int MANGO_TANGO = 14509824;
		public static final int MISTY_ROSE = 16771304;
		public static final int OLIVE = 5924864;
		public static final int ORANGE = 14653696;
		public static final int PIGMENT_GREEN = 37698;
		public static final int PINK = 16738740;
		public static final int PURPLE = 12665047;
		public static final int RED = 16711680;
		public static final int RED_2 = 15007744;
		public static final int SILVER = 11908533;
		public static final int TANGERINE_YELLOW = 15257600;
		public static final int TOXIC_GREEN = 3368448;
		public static final int TYRIAN_PURPLE = 7012434;
		public static final int WHITE = 16777215;
		public static final int YELLOW = 16776960;
		public static final int YELLOW_2 = 16248576;
	}
}
