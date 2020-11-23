package com.hungteen.pvz.data.loot;

import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.util.ResourceLocation;

public class PVZLoot {

	//chest
	public static final ResourceLocation DAVE_VILLA_CHEST = getChestLootTable("dave_villa_chest");
	public static final ResourceLocation BUCKET_HOUSE_CHEST = getChestLootTable("bucket_house_chest");
	public static final ResourceLocation DOLPHIN_HOUSE_CHEST = getChestLootTable("dolphin_house_chest");
	public static final ResourceLocation GRAVE_YARD_CHEST = getChestLootTable("grave_yard_chest");
	
	//entity
	public static final ResourceLocation NORMAL_ZOMBIE = getEntityLootTable("normal_zombie");
	public static final ResourceLocation FLAG_ZOMBIE = getEntityLootTable("flag_zombie");
	public static final ResourceLocation CONEHEAD_ZOMBIE = getEntityLootTable("conehead_zombie");
	public static final ResourceLocation BUCKETHEAD_ZOMBIE = getEntityLootTable("buckethead_zombie");
	public static final ResourceLocation BOBSLE_TEAM = getEntityLootTable("bobsle_team");
	public static final ResourceLocation ZOMBIE_DOLPHIN = getEntityLootTable("zombie_dolphin");
	public static final ResourceLocation FOODIE_ZOMBIE = getEntityLootTable("foodie_zombie");
	public static final ResourceLocation LAVA_ZOMBIE = getEntityLootTable("lava_zombie");
	public static final ResourceLocation PUMPKIN_ZOMBIE = getEntityLootTable("pumpkin_zombie");
	
	public static ResourceLocation getChestLootTable(String name) {
		return StringUtil.prefix("chests/"+name);
	}
	
	public static ResourceLocation getEntityLootTable(String name) {
		return StringUtil.prefix("entities/"+name);
	}
}
