package com.hungteen.pvz.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.hungteen.pvz.PVZMod;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class StringUtil {

	public static final StringTextComponent EMPTY = new StringTextComponent("");
	public static final String PATCHOULI = "patchouli";
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
	
	public static String getRandomLangText(Minecraft mc, Random rand, String name) {
		if(mc == null) {
			mc = Minecraft.getInstance();
		}
		List<String> texts = StringUtil.getLangTextList(mc, name);
		return texts.get(rand.nextInt(texts.size()));
	}
	
	/**
	 * get lang from resource.
	 */
	public static List<String> getLangTextList(Minecraft mc, String name) {
		try (IResource iresource = StringUtil.getTxtResource(mc, name);
				BufferedReader bufferedreader = new BufferedReader(
						new InputStreamReader(iresource.getInputStream(), StandardCharsets.UTF_8));) {
			List<String> list = bufferedreader.lines().map(String::trim).filter((p_215277_0_) -> {
				return p_215277_0_.hashCode() != 125780783;
			}).collect(Collectors.toList());
			return list;
		} catch (IOException var36) {
			return Collections.emptyList();
		}
	}
	
	public static IResource getTxtResource(Minecraft mc, String name) {
		ResourceLocation fileLoc = StringUtil.prefix("lang/others/" + mc.options.languageCode + "/" + name + ".txt");
        ResourceLocation backupLoc = StringUtil.prefix("lang/others/en_us/" + name + ".txt");
        IResource resource = null;
        try {
            resource = mc.getResourceManager().getResource(fileLoc);
        } catch (IOException e) {
            try {
                resource = mc.getResourceManager().getResource(backupLoc);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return resource;
	}
	
}
