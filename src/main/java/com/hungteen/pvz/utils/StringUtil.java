package com.hungteen.pvz.utils;

import com.hungteen.pvz.PVZMod;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

public class StringUtil {

	public static final String ARMOR_PREFIX = PVZMod.MOD_ID+":textures/models/armor/";
	
	public static ResourceLocation prefix(String a)
	{
		return new ResourceLocation(PVZMod.MOD_ID,a);
	}
	
	public static void drawCenteredString(FontRenderer render,String string,int x,int y,int color)
	{
		int width=render.getStringWidth(string);
		render.drawString(string, x-width/2, y, color);
	}
	
	@SuppressWarnings("deprecation")
	public static void drawCenteredScaledString(FontRenderer render,String string,int x,int y,int color,float scale)
	{
		int width=render.getStringWidth(string);
		GlStateManager.pushMatrix();
		GlStateManager.scaled(scale, scale, scale);
		render.drawString(string, (x-width/2*scale)/scale, y/scale, color);
		GlStateManager.popMatrix();
	}
}
