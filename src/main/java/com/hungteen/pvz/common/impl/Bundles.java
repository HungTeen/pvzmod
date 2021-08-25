package com.hungteen.pvz.common.impl;

import java.util.Random;

import com.hungteen.pvz.common.core.BundleType;
import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.impl.plant.PVZPlants;

import net.minecraft.item.ItemStack;

public final class Bundles extends BundleType {

	public static final BundleType RANDOM_ALL = new RandomBundle("random_all", new BundleFeatures());
	
	public static final BundleType GRASS_DAY_PLANT = new Bundles("grass_day_plant", new BundleFeatures().addPlants(
			PVZPlants.PEA_SHOOTER, PVZPlants.SUN_FLOWER, PVZPlants.CHERRY_BOMB, PVZPlants.WALL_NUT, 
			PVZPlants.POTATO_MINE, PVZPlants.SNOW_PEA, PVZPlants.CHOMPER, PVZPlants.REPEATER	
	)); 
	
	public static final BundleType GRASS_NIGHT_PLANT = new Bundles("grass_night_plant", new BundleFeatures().addPlants(
			PVZPlants.PUFF_SHROOM, PVZPlants.SUN_SHROOM, PVZPlants.FUME_SHROOM, PVZPlants.GRAVE_BUSTER, 
			PVZPlants.HYPNO_SHROOM, PVZPlants.SCAREDY_SHROOM, PVZPlants.ICE_SHROOM, PVZPlants.DOOM_SHROOM
	)); 
	
	public static final BundleType POOL_DAY_PLANT = new Bundles("pool_day_plant", new BundleFeatures().addPlants(
			PVZPlants.LILY_PAD, PVZPlants.SQUASH, PVZPlants.THREE_PEATER, PVZPlants.TANGLE_KELP, 
			PVZPlants.JALAPENO, PVZPlants.SPIKE_WEED, PVZPlants.TORCH_WOOD, PVZPlants.TALL_NUT
	)); 
	
	public static final BundleType POOL_NIGHT_PLANT = new Bundles("pool_night_plant", new BundleFeatures().addPlants(
			PVZPlants.SEA_SHROOM, PVZPlants.PLANTERN, PVZPlants.CACTUS, PVZPlants.BLOVER, 
			PVZPlants.SPLIT_PEA, PVZPlants.STAR_FRUIT, PVZPlants.PUMPKIN, PVZPlants.MAGNET_SHROOM
	)); 
	
	public static final BundleType ROOF_PLANT = new Bundles("roof_plant", new BundleFeatures().addPlants(
			PVZPlants.CABBAGE_PULT, PVZPlants.FLOWER_POT, PVZPlants.KERNEL_PULT, PVZPlants.COFFEE_BEAN, 
			PVZPlants.GARLIC, PVZPlants.UMBRELLA_LEAF, PVZPlants.MARIGOLD, PVZPlants.MELON_PULT
	)); 
	
	public static final BundleType UPGRADE_PLANT = new Bundles("upgrade_plant", new BundleFeatures().addPlants(
			PVZPlants.GATLING_PEA, PVZPlants.TWIN_SUNFLOWER, PVZPlants.GLOOM_SHROOM, PVZPlants.CAT_TAIL, 
			PVZPlants.WINTER_MELON, PVZPlants.GOLD_MAGNET, PVZPlants.SPIKE_ROCK, PVZPlants.COB_CANNON
	)); 
	
	public Bundles(String name, BundleFeatures features) {
		super(name, features);
	}
	
	public static final class RandomBundle extends BundleType {

		public RandomBundle(String name, BundleFeatures features) {
			super(name, features);
		}
		
		@Override
		public ItemStack getEnjoyCard(Random rand) {
			final int pos = rand.nextInt(PlantType.size());
			PlantType plant = PlantType.getPlants().get(pos);
			if(plant.getEnjoyCard().isPresent()) {
				return new ItemStack(plant.getEnjoyCard().get());
			}
			return ItemStack.EMPTY;
		}
		
	}
	
}
