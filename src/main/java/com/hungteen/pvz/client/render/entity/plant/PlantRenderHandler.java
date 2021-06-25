package com.hungteen.pvz.client.render.entity.plant;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.util.ResourceLocation;

public class PlantRenderHandler {

	public static final Map<Plants, ResourceLocation> PLANT_TEX = new EnumMap<>(Plants.class);
	public static final Map<Plants, Float> PLANT_SCALE = new EnumMap<>(Plants.class);
	
	public static final ResourceLocation PEA_SHOOTER_TEX = get(Plants.PEA_SHOOTER, 1F);
	public static final ResourceLocation SUN_FLOWER_TEX = get(Plants.SUN_FLOWER);
	public static final ResourceLocation CHERRY_BOMB_TEX = get(Plants.CHERRY_BOMB);
	public static final ResourceLocation WALL_NUT_TEX = get(Plants.WALL_NUT, 1F);
	public static final ResourceLocation POTATO_MINE_TEX = get(Plants.POTATO_MINE, 0.6F);
	public static final ResourceLocation SNOW_PEA_TEX = get(Plants.SNOW_PEA, 1F);
	public static final ResourceLocation CHOMPER_TEX = get(Plants.CHOMPER, 0.85F);
	public static final ResourceLocation REPEATER_TEX = get(Plants.REPEATER, 1F);
	
	public static final ResourceLocation PUFF_SHROOM_TEX = get(Plants.PUFF_SHROOM, 0.6F);
	public static final ResourceLocation SUN_SHROOM_TEX = get(Plants.SUN_SHROOM, 0.4F);
	public static final ResourceLocation FUME_SHROOM_TEX = get(Plants.FUME_SHROOM, 0.9F);
	public static final ResourceLocation GRAVE_BUSTER_TEX = get(Plants.GRAVE_BUSTER, 1F);
	public static final ResourceLocation HYPNO_SHROOM_TEX = get(Plants.HYPNO_SHROOM, 0.6F);
	public static final ResourceLocation SCAREDY_SHROOM_TEX = get(Plants.SCAREDY_SHROOM, 0.7F);
	public static final ResourceLocation ICE_SHROOM_TEX = get(Plants.ICE_SHROOM, 0.82F);
	public static final ResourceLocation DOOM_SHROOM_TEX = get(Plants.DOOM_SHROOM, 1F);
	
//	public static final ResourceLocation LILY_PAD_TEX = get(Plants.PUFF_SHROOM, 0.6F);
	public static final ResourceLocation SQUASH_TEX = get(Plants.SQUASH, 0.5F);
	public static final ResourceLocation THREE_PEATER_TEX = get(Plants.THREE_PEATER, 0.99F);
	public static final ResourceLocation TANGLE_KELP_TEX = get(Plants.TANGLE_KELP, 0.6F);
	public static final ResourceLocation JALAPENO_TEX = get(Plants.JALAPENO, 0.5F);
	public static final ResourceLocation SPIKE_WEED_TEX = get(Plants.SPIKE_WEED, 0.5F);
	public static final ResourceLocation TORCH_WOOD_TEX = get(Plants.TORCH_WOOD, 0.5F);
	public static final ResourceLocation TALL_NUT_TEX = get(Plants.TALL_NUT, 0.4F);
	
	public static final ResourceLocation SEA_SHROOM_TEX = get(Plants.SEA_SHROOM, 0.6F);
	public static final ResourceLocation PLANTERN_TEX = get(Plants.PLANTERN, 0.7F);
	public static final ResourceLocation CACTUS_TEX = get(Plants.FUME_SHROOM, 1F);
	public static final ResourceLocation BLOVER_TEX = get(Plants.BLOVER, 1.2F);
	public static final ResourceLocation SPLIT_PEA_TEX = get(Plants.SPLIT_PEA, 1F);
	public static final ResourceLocation STAR_FRUIT_TEX = get(Plants.STAR_FRUIT, 0.82F);
	public static final ResourceLocation PUMPKIN_TEX = get(Plants.PUMPKIN, 0.6F);
	public static final ResourceLocation MAGNET_SHROOM_TEX = get(Plants.MAGNET_SHROOM, 1.2F);
	
	public static final ResourceLocation CABBAGE_PULT_TEX = get(Plants.CABBAGE_PULT, 0.9F);
//	public static final ResourceLocation FLOWER_POT_TEX = get(Plants.SEA_SHROOM, 0.6F);
	public static final ResourceLocation KERNEL_PULT_TEX = get(Plants.KERNEL_PULT, 0.9F);
	public static final ResourceLocation COFFEE_BEAN_TEX = get(Plants.COFFEE_BEAN, 0.6F);
	public static final ResourceLocation GARLIC_TEX = get(Plants.GARLIC, 0.9F);
	public static final ResourceLocation UMBRELLA_LEAF_TEX = get(Plants.UMBRELLA_LEAF, 0.8F);
	public static final ResourceLocation MARIGOLD_TEX = get(Plants.MARIGOLD, 0.5F);
	public static final ResourceLocation MELON_PULT_TEX = get(Plants.MELON_PULT, 0.9F);
	
	public static final ResourceLocation GATLING_PEA_TEX = get(Plants.GATLING_PEA, 0.5F);
	public static final ResourceLocation TWIN_SUNFLOWER_TEX = get(Plants.TWIN_SUNFLOWER, 0.45F);
	public static final ResourceLocation GLOOM_SHROOM_TEX = get(Plants.GLOOM_SHROOM, 1F);
	public static final ResourceLocation CAT_TAIL_TEX = get(Plants.CAT_TAIL, 0.18F);
	public static final ResourceLocation WINTER_MELON_TEX = get(Plants.WINTER_MELON, 0.9F);
	public static final ResourceLocation GOLD_MAGNET_TEX = get(Plants.GOLD_MAGNET, 1.2F);
	public static final ResourceLocation SPIKE_ROCK = get(Plants.SPIKE_ROCK, 0.5F);
	public static final ResourceLocation COB_CANNON_TEX = get(Plants.COB_CANNON, 1.1F);
	public static final ResourceLocation IMITATER_TEX = get(Plants.IMITATER, 0.9F);
	
	public static final ResourceLocation WATER_GUARD_TEX = get(Plants.WATER_GUARD, 0.8F);
	public static final ResourceLocation STRANGE_CAT_TEX = get(Plants.STRANGE_CAT, 0.18F);
	public static final ResourceLocation ANGEL_STAR_FRUIT_TEX = get(Plants.ANGEL_STAR_FRUIT, 0.8F);
	public static final ResourceLocation GOLD_LEAF_TEX = get(Plants.GOLD_LEAF, 0.8F);
	public static final ResourceLocation EXPLODE_O_NUT_TEX = get(Plants.EXPLODE_O_NUT, 0.4F);
	public static final ResourceLocation GIANT_WALL_NUT_TEX = get(Plants.GIANT_WALL_NUT, 1F);
	public static final ResourceLocation BUTTER_PULT_TEX = get(Plants.BUTTER_PULT, 0.9F);
	public static final ResourceLocation BAMBOO_LORD_TEX = get(Plants.BAMBOO_LORD, 1F);
	public static final ResourceLocation ICEBERG_LETTUCE_TEX = get(Plants.ICEBERG_LETTUCE, 0.5F);
	public static final ResourceLocation BONK_CHOY_TEX = get(Plants.BONK_CHOY, 0.5F);
	
	private static ResourceLocation get(Plants plant) {
		return get(plant, 0.5F);
	}
	
	private static ResourceLocation get(Plants plant, float scale) {
		String sep = PlantUtil.getPlantEssenceType(plant).toString().toLowerCase();
		ResourceLocation res = StringUtil.prefix("textures/entity/plant/" + sep + "/" + plant.toString().toLowerCase() + ".png");
		put(plant, res, scale);
		return res;
	}
	
	public static Optional<ResourceLocation> getPlantTex(Plants plant) {
		return Optional.ofNullable(PLANT_TEX.get(plant));
	}
	
	/**
	 * get Plant initial scale.
	 */
	public static float getPlantScale(Plants plant) {
		return PLANT_SCALE.getOrDefault(plant, 0.5F);
	}
	
	private static void put(Plants plant, ResourceLocation res, float scale) {
		PLANT_TEX.put(plant, res);
		PLANT_SCALE.put(plant, scale);
	}
}
