package com.hungteen.pvz.remove;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.impl.plant.PVZPlants;

import net.minecraft.item.ItemStack;

public enum Bundles {

	GRASS_DAY(PVZPlants.PEA_SHOOTER, PVZPlants.SUN_FLOWER, PVZPlants.CHERRY_BOMB, PVZPlants.WALL_NUT, PVZPlants.POTATO_MINE, PVZPlants.SNOW_PEA, PVZPlants.CHOMPER, PVZPlants.REPEATER),
	GRASS_NIGHT(PVZPlants.PUFF_SHROOM, PVZPlants.SUN_SHROOM, PVZPlants.FUME_SHROOM, PVZPlants.GRAVE_BUSTER, PVZPlants.HYPNO_SHROOM, PVZPlants.SCAREDY_SHROOM, PVZPlants.ICE_SHROOM, PVZPlants.DOOM_SHROOM),
	POOL_DAY(PVZPlants.LILY_PAD, PVZPlants.SQUASH, PVZPlants.THREE_PEATER, PVZPlants.TANGLE_KELP, PVZPlants.JALAPENO, PVZPlants.SPIKE_WEED, PVZPlants.TORCH_WOOD, PVZPlants.TALL_NUT),
	POOL_NIGHT(PVZPlants.SEA_SHROOM, PVZPlants.PLANTERN, PVZPlants.CACTUS, PVZPlants.BLOVER, PVZPlants.SPLIT_PEA, PVZPlants.STAR_FRUIT, PVZPlants.PUMPKIN, PVZPlants.MAGNET_SHROOM),
	ROOF(PVZPlants.CABBAGE_PULT, PVZPlants.FLOWER_POT, PVZPlants.KERNEL_PULT, PVZPlants.COFFEE_BEAN, PVZPlants.GARLIC, PVZPlants.UMBRELLA_LEAF, PVZPlants.MARIGOLD, PVZPlants.MELON_PULT),
	UPGRADE(PVZPlants.GATLING_PEA, PVZPlants.TWIN_SUNFLOWER, PVZPlants.GLOOM_SHROOM, PVZPlants.CAT_TAIL, PVZPlants.WINTER_MELON, PVZPlants.GOLD_MAGNET, PVZPlants.SPIKE_ROCK),
	RANDOM;
	
	private final List<PlantType> list;
	
	private Bundles() {
		list = new ArrayList<>();
	}
	
	private Bundles(PlantType ...plants) {
		list = Arrays.asList(plants);
	}
	
	public ItemStack getRandomBundle() {
		if(this == RANDOM) return getRandomAllBundle();
		if(list.size() == 0) return ItemStack.EMPTY;
		Random rand = new Random();
		int pos = rand.nextInt(list.size());
		PlantType plant = list.get(pos);
		return new ItemStack(plant.getEnjoyCard().get());
	}
	
	public static ItemStack getRandomAllBundle() {
		Random rand = new Random();
		int pos = rand.nextInt(PlantType.size());
		PlantType plant = PlantType.getPlants().get(pos);
		return new ItemStack(plant.getEnjoyCard().get());
	}
	
}
