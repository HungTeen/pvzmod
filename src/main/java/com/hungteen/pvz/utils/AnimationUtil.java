package com.hungteen.pvz.utils;

import net.minecraft.util.math.MathHelper;

public class AnimationUtil {

	private static final float PI = 3.1415926535F;
	
	public static float getUpDown(int x, int t, int maxAngle) {
		float sita = PI / t;
		return MathHelper.abs(MathHelper.sin(sita * x)) * maxAngle / 180 * PI;
	}
	
	public static float getUpDownUpDown(int x, int t, int maxAngle) {
		float sita = 2 * PI / t;
		return MathHelper.sin(sita * x) * maxAngle / 180 * PI;
	}
	
	public static float getUp(int x, int t, int maxAngle) {
		float sita = PI / 2 / t;
		return MathHelper.sin(sita * x) * maxAngle / 180 * PI;
	}
	
	public static float getDown(int x, int t, int maxAngle) {
		float sita = PI / 2 / t;
		return MathHelper.cos(sita * x) * maxAngle / 180 * PI;
	}
	
	public static float byDegree(float degree) {
		return PI / 180F * degree;
	}
	
}
