package com.hungteen.pvz.common.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.hungteen.pvz.utils.others.WeightList;

import net.minecraft.item.ItemStack;

/**
 * use to extend Plant Types. <br>
 * define your category, same category is allowed. <br>
 * if you wanna show in front of that category, then override one with higher priority. <br>
 */
public abstract class BundleType {

	private static final List<BundleType> BUNDLES = new ArrayList<>();
	protected final WeightList<PlantType> plantList;
	protected final WeightList<ZombieType> zombieList;
	protected final String bundleName;
	
	public BundleType(String name, BundleFeatures features) {
		this.bundleName = name;
		this.plantList = features.plantList;
		this.zombieList = features.zombieList;
		BUNDLES.add(this);
	}
	
	/**
	 * randomly get bundles' card.
	 */
	public ItemStack getEnjoyCard(Random rand) {
		if(! this.plantList.isEmpty()) {
			Optional<PlantType> type = this.plantList.getRandomItem(rand);
			if(type.isPresent() && type.get().getEnjoyCard().isPresent()) {
				return new ItemStack(type.get().getEnjoyCard().get());
			}
		}
		return ItemStack.EMPTY;
	}
	
	@Override
	public String toString() {
		return this.bundleName;
	}
	
	public static class BundleFeatures{
		protected final WeightList<PlantType> plantList = new WeightList<>();
		protected final WeightList<ZombieType> zombieList = new WeightList<>();
		
		/**
		 * add single plant with weight.
		 */
		public BundleFeatures addPlant(PlantType type, int weight) {
			this.plantList.addItem(type, weight);
			return this;
		}
		
		/**
		 * add single plant.
		 */
		public BundleFeatures addPlant(PlantType type) {
			return addPlant(type, 10);
		}
		
		/**
		 * add plants.
		 */
		public BundleFeatures addPlants(PlantType... types) {
			for(PlantType type : types) {
				addPlant(type);
			}
			return this;
		}
		
		/**
		 * add single zombie with weight.
		 */
		public BundleFeatures addZombie(ZombieType type, int weight) {
			this.zombieList.addItem(type, weight);
			return this;
		}
		
		/**
		 * add single zombie.
		 */
		public BundleFeatures addZombie(ZombieType type) {
			return addZombie(type, 10);
		}
		
	}
	
}
