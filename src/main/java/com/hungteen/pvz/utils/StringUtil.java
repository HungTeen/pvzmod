package com.hungteen.pvz.utils;

import com.hungteen.pvz.PVZMod;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public class StringUtil {

	public static final String ARMOR_PREFIX = PVZMod.MOD_ID + ":textures/models/armor/";

	public static ResourceLocation prefix(String a) {
		return new ResourceLocation(PVZMod.MOD_ID, a);
	}

	public static void drawScaledString(FontRenderer render, String string, int x, int y, int color, float scale) {
		RenderSystem.pushMatrix();
		RenderSystem.scaled(scale, scale, scale);
		render.drawString(string, x / scale, y / scale, color);
		RenderSystem.popMatrix();
	}
	
	public static void drawCenteredString(FontRenderer render, String string, int x, int y, int color) {
		int width = render.getStringWidth(string);
		render.drawString(string, x - width / 2, y, color);
	}

	public static void drawCenteredScaledString(FontRenderer render, String string, int x, int y, int color,
			float scale) {
		int width = render.getStringWidth(string);
		RenderSystem.pushMatrix();
		RenderSystem.scaled(scale, scale, scale);
		render.drawString(string, (x - width / 2 * scale) / scale, y / scale, color);
		RenderSystem.popMatrix();
	}

}
