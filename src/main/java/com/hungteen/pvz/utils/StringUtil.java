package com.hungteen.pvz.utils;

import com.hungteen.pvz.PVZMod;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class StringUtil {

	public static final StringTextComponent EMPTY = new StringTextComponent("");
	public static final String ARMOR_PREFIX = PVZMod.MOD_ID + ":textures/models/armor/";
	
	public static ResourceLocation prefix(String a) {
		return new ResourceLocation(PVZMod.MOD_ID, a);
	}

	public static void drawScaledString(MatrixStack stack, FontRenderer render, String string, int x, int y, int color, float scale) {
		stack.pushPose();
		stack.scale(scale, scale, scale);
		render.draw(stack, string, x / scale, y / scale, color);
		stack.popPose();
	}
	
	public static void drawCenteredString(MatrixStack stack, FontRenderer render, String string, int x, int y, int color) {
		int width = render.width(string);
		render.draw(stack, string, x - width / 2, y, color);
	}

	public static void drawCenteredScaledString(MatrixStack stack, FontRenderer render, String string, int x, int y, int color,
			float scale) {
		int width = render.width(string);
		stack.pushPose();
		stack.scale(scale, scale, scale);
		render.draw(stack, string, (x - width / 2 * scale) / scale, y / scale, color);
		stack.popPose();
	}
	
}
