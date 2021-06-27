package com.hungteen.pvz.utils;

import net.minecraft.util.math.MathHelper;

public class AnimationUtil {

	private static final float PI = 3.1415926535F;
	private static final float ANGLE_TO = PI / 180;
	
	public static float upDown(int x, int t, float scale) {
		final float sita = PI / t;
		return MathHelper.abs(MathHelper.sin(sita * x)) * scale;
	}
	
	public static float upDownUpDown(int x, int t, float scale) {
		final float sita = 2 * PI / t;
		return MathHelper.sin(sita * x) * scale;
	}
	
	public static float up(int x, int t, float scale) {
		final float sita = PI / 2 / t;
		return MathHelper.sin(sita * x) * scale;
	}
	
	public static float down(int x, int t, float scale) {
		final float sita = PI / 2 / t;
		return MathHelper.cos(sita * x) * scale;
	}
	
	public static float getUpDown(int x, int t, float maxAngle) {
		return upDown(x, t, maxAngle * ANGLE_TO);
	}
	
	public static float getUpDownUpDown(int x, int t, float maxAngle) {
		return upDownUpDown(x, t, maxAngle * ANGLE_TO);
	}
	
	public static float getUp(int x, int t, float maxAngle) {
		return up(x, t, maxAngle * ANGLE_TO);
	}
	
	public static float getDown(int x, int t, float maxAngle) {
		return down(x, t, maxAngle * ANGLE_TO);
	}
	
	public static float byDegree(float degree) {
		return PI / 180F * degree;
	}
	
}
