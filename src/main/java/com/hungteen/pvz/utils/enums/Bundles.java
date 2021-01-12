package com.hungteen.pvz.utils.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.hungteen.pvz.utils.PlantUtil;

import net.minecraft.item.ItemStack;

public enum Bundles {

	GRASS_DAY(Plants.PEA_SHOOTER, Plants.SUN_FLOWER, Plants.CHERRY_BOMB, Plants.WALL_NUT, Plants.POTATO_MINE, Plants.SNOW_PEA, Plants.CHOMPER, Plants.REPEATER),
	GRASS_NIGHT(Plants.PUFF_SHROOM, Plants.SUN_SHROOM, Plants.FUME_SHROOM, Plants.GRAVE_BUSTER, Plants.HYPNO_SHROOM, Plants.SCAREDY_SHROOM, Plants.ICE_SHROOM, Plants.DOOM_SHROOM),
	POOL_DAY(Plants.LILY_PAD, Plants.SQUASH, Plants.THREE_PEATER, Plants.TANGLE_KELP, Plants.JALAPENO, Plants.SPIKE_WEED, Plants.TORCH_WOOD, Plants.TALL_NUT),
	POOL_NIGHT(Plants.SEA_SHROOM, Plants.PLANTERN, Plants.CACTUS, Plants.BLOVER, Plants.SPLIT_PEA, Plants.STAR_FRUIT, Plants.PUMPKIN, Plants.MAGNET_SHROOM),
	RANDOM;
	
	private final List<Plants> list;
	
	private Bundles() {
		list = new ArrayList<>();
	}
	
	private Bundles(Plants ...plants) {
		list = Arrays.asList(plants);
	}
	
	public ItemStack getRandomBundle() {
		if(this == RANDOM) return getRandomAllBundle();
		if(list.size() == 0) return ItemStack.EMPTY;
		Random rand = new Random();
		int pos = rand.nextInt(list.size());
		Plants plant = list.get(pos);
		return new ItemStack(PlantUtil.getPlantEnjoyCard(plant));
	}
	
	public static ItemStack getRandomAllBundle() {
		Random rand = new Random();
		int pos = rand.nextInt(Plants.values().length);
		Plants plant = Plants.values()[pos];
		return new ItemStack(PlantUtil.getPlantEnjoyCard(plant));
	}
	
}
