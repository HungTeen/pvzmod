package com.hungteen.pvz.common.impl.misc;

import java.util.Optional;
import java.util.Random;

import com.hungteen.pvz.api.types.IBundleType;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.utils.others.WeightList;

import net.minecraft.item.ItemStack;

/**
 * use to extend Plant Types. <br>
 * define your category, same category is allowed. <br>
 * if you wanna show in front of that category, then override one with higher priority. <br>
 */
public abstract class BundleType implements IBundleType {

	protected final WeightList<IPlantType> plantList;
	protected final WeightList<IZombieType> zombieList;
	protected final String bundleName;
	
	public BundleType(String name, BundleFeatures features) {
		this.bundleName = name;
		this.plantList = features.plantList;
		this.zombieList = features.zombieList;
	}
	
	/**
	 * randomly get bundles' card.
	 */
	@Override
	public ItemStack getEnjoyCard(Random rand) {
		if(! this.plantList.isEmpty()) {
			final Optional<IPlantType> type = this.plantList.getRandomItem(rand);
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
		protected final WeightList<IPlantType> plantList = new WeightList<>();
		protected final WeightList<IZombieType> zombieList = new WeightList<>();
		
		/**
		 * add single plant with weight.
		 */
		public BundleFeatures addPlant(IPlantType type, int weight) {
			this.plantList.addItem(type, weight);
			return this;
		}
		
		/**
		 * add single plant.
		 */
		public BundleFeatures addPlant(IPlantType type) {
			return addPlant(type, 10);
		}
		
		/**
		 * add plants.
		 */
		public BundleFeatures addPlants(IPlantType... types) {
			for(IPlantType type : types) {
				addPlant(type);
			}
			return this;
		}
		
		/**
		 * add single zombie with weight.
		 */
		public BundleFeatures addZombie(IZombieType type, int weight) {
			this.zombieList.addItem(type, weight);
			return this;
		}
		
		/**
		 * add single zombie.
		 */
		public BundleFeatures addZombie(IZombieType type) {
			return addZombie(type, 10);
		}
		
	}
	
}
