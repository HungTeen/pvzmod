package com.hungteen.pvzmod.util;

import net.minecraft.util.ResourceLocation;

public class Reference {
	
	//mod
	public static final String MODID = "pvz";
	public static final String NAME = "Plants vs Zombies";
	public static final String VERSION = "beta 0.7";
	public static final String ACCEPTED_VERSION = "[1.12.2]";
	public static final String CLIENT_PROXY = "com.hungteen.pvzmod.ClientProxy";
	public static final String COMMON_PROXY = "com.hungteen.pvzmod.CommonProxy";
	
	public static final ResourceLocation PARTICLE_DIR = new ResourceLocation(MODID, "textures/particles/particle1.png");
	public static final ResourceLocation EMPTY = new ResourceLocation(Reference.MODID + ":" + "textures/entity/defence/empty.png");
	
	public static final float OBSIDIAN_HARDNESS = 50f;
	public static final float ENDERCHEST_HARDNESS = 22.5f;
	public static final float DIAMONDBLOCK_HARDNESS = 5f;
	public static final float ORE_HARDNESS = 3f;
	public static final float CHEST_HARDNESS = 2.5f;
	public static final float STONE_HARDNESS = 1.5f;
	public static final float GRASS_HARDNESS = 0.6f;
	
	public static final float BEDROCK_RESISTANCE = 3600000f;
	public static final float OBSIDIAN_RESISTANCE = 1200f;
	public static final float ENDERCHEST_RESISTANCE = 600f;
	public static final float ENDERSTONE_RESISTANCE = 9f;
	public static final float DIAMONDBLOCK_RESISTANCE = 6f;
	public static final float ORE_RESISTANCE = 3f;
	public static final float WOOD_RESISTANCE = 2f;
	public static final float DIRT_RESISTANCE = 0.5f;
}
