package com.hungteen.pvz.common.misc;

import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.util.ResourceLocation;

public class PVZLoot {

	//chest
	public static final ResourceLocation DAVE_VILLA_CHEST = getChestLootTable("dave_villa_chest");
	public static final ResourceLocation BUCKET_HOUSE_CHEST = getChestLootTable("bucket_house_chest");
	public static final ResourceLocation DOLPHIN_HOUSE_CHEST = getChestLootTable("dolphin_house_chest");
	public static final ResourceLocation GRAVE_YARD_CHEST = getChestLootTable("grave_yard_chest");
	public static final ResourceLocation YETI_HOUSE_CHEST = getChestLootTable("yeti_house_chest");
	public static final ResourceLocation SUN_TEMPLE_CHEST = getChestLootTable("sun_temple_chest");
	//entity
	public static final ResourceLocation NORMAL_ZOMBIE = getEntityLootTable("normal_zombie");
	public static final ResourceLocation FLAG_ZOMBIE = getEntityLootTable("flag_zombie");
	public static final ResourceLocation CONEHEAD_ZOMBIE = getEntityLootTable("conehead_zombie");
	public static final ResourceLocation BUCKETHEAD_ZOMBIE = getEntityLootTable("buckethead_zombie");
	public static final ResourceLocation SCREENDOOR_ZOMBIE = getEntityLootTable("screendoor_zombie");
	public static final ResourceLocation FOOTBALL_ZOMBIE = getEntityLootTable("football_zombie");
	public static final ResourceLocation GIGA_FOOTBALL_ZOMBIE = getEntityLootTable("giga_football_zombie");
	public static final ResourceLocation NOBLE_ZOMBIE = getEntityLootTable("noble_zombie");
	public static final ResourceLocation MOURNER_ZOMBIE = getEntityLootTable("mourner_zombie");
	public static final ResourceLocation BOBSLE_TEAM = getEntityLootTable("bobsle_team");
	public static final ResourceLocation ZOMBIE_DOLPHIN = getEntityLootTable("zombie_dolphin");
	public static final ResourceLocation FOODIE_ZOMBIE = getEntityLootTable("foodie_zombie");
	public static final ResourceLocation LAVA_ZOMBIE = getEntityLootTable("lava_zombie");
	public static final ResourceLocation PUMPKIN_ZOMBIE = getEntityLootTable("pumpkin_zombie");
	public static final ResourceLocation TRICK_ZOMBIE = getEntityLootTable("trick_zombie");
	public static final ResourceLocation JACK_IN_BOX_ZOMBIE = getEntityLootTable("jack_in_box_zombie");
	public static final ResourceLocation BALLOON_ZOMBIE = getEntityLootTable("balloon_zombie");
	public static final ResourceLocation DIGGER_ZOMBIE = getEntityLootTable("digger_zombie");
	public static final ResourceLocation BUNGEE_ZOMBIE = getEntityLootTable("bungee_zombie");
	public static final ResourceLocation LADDER_ZOMBIE = getEntityLootTable("ladder_zombie");
	public static final ResourceLocation GARGANTUAR = getEntityLootTable("gargantuar");
	public static final ResourceLocation GIGA_GARGANTUAR = getEntityLootTable("giga_gargantuar");
	public static final ResourceLocation EDGAR_090505 = getEntityLootTable("edgar_090505");
	public static final ResourceLocation EDGAR_090517 = getEntityLootTable("edgar_090517");
	public static final ResourceLocation PEASHOOTER_ZOMBIE = getEntityLootTable("peashooter_zombie");
	public static final ResourceLocation WALLNUT_ZOMBIE = getEntityLootTable("wallnut_zombie");
	public static final ResourceLocation GATLINGPEA_ZOMBIE = getEntityLootTable("gatlingpea_zombie");
	public static final ResourceLocation TALLNUT_ZOMBIE = getEntityLootTable("tallnut_zombie");
	public static final ResourceLocation SQUASH_ZOMBIE = getEntityLootTable("squash_zombie");
	public static final ResourceLocation JALAPENO_ZOMBIE = getEntityLootTable("jalapeno_zombie");
	public static final ResourceLocation RA_ZOMBIE = getEntityLootTable("ra_zombie");
	public static final ResourceLocation COFFIN = getEntityLootTable("coffin");
	
	public static ResourceLocation getChestLootTable(String name) {
		return StringUtil.prefix("chests/" + name);
	}
	
	public static ResourceLocation getEntityLootTable(String name) {
		return StringUtil.prefix("entities/" + name);
	}
	
	public static ResourceLocation getFishingLootTable(String name) {
		return StringUtil.prefix("gameplay/fishing/" + name);
	}
}
