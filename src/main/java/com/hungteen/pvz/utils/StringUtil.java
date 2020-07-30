package com.hungteen.pvz.utils;

import com.hungteen.pvz.PVZMod;

import net.minecraft.util.ResourceLocation;

public class StringUtil {

	public static ResourceLocation prefix(String a)
	{
		return new ResourceLocation(PVZMod.MOD_ID,a);
	}
}
