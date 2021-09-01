package com.hungteen.pvz.utils;

import net.minecraft.item.ItemStack;

public class ItemUtil {

	public static final int MELON_AMOUNT = 2;
	public static final int APPLE_AMOUNT = 4;
	public static final int BREAD_AMOUNT = 5;
	public static final int CHICKEN_AMOUNT = 6;
	public static final int PIG_AMOUNT = 8;
	
	public static final float COOKIE_SATURATION = 0.1f;
	public static final float APPLE_SATURATION = 0.3f;
	public static final float BREAD_SATURATION = 0.6f;
	public static final float PIG_SATURATION = 0.8f;
	public static final float GOLDEN_SATURATION = 1.2f;
	
	public static boolean canItemStackAddTo(ItemStack stack1, ItemStack stack2) {
		if(! stack1.sameItem(stack2)) return false;
		if(! ItemStack.tagMatches(stack1, stack2)) return false;
		return true;
	}
	
}
