package com.hungteen.pvz.common.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.hungteen.pvz.common.impl.Bundles;
import com.mojang.datafixers.util.Pair;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

/**
 * use to extend Plant Types. <br>
 * define your category, same category is allowed. <br>
 * if you wanna show in front of that category, then override one with higher priority. <br>
 */
public abstract class InvasionType {

	private static final List<InvasionType> INVASION_EVENTS = new ArrayList<>();
	private static final List<Pair<InvasionType, Integer>> SPAWN_EVENTS = new ArrayList<>();
	private static final List<InvasionType> ASSIST_EVENTS = new ArrayList<>();
	protected final List<ZombieType> spawnZombies;
	protected final List<PlantType> spawnPlants;
	protected final String invasionName;
	protected final int chance;
	protected final BundleType bonusbundle;
	protected final TextFormatting displayColor;
	/* some event has limit to be available, such as zombotany invasion need kill Edgar-090505 */
	protected final Function<World, Boolean> isAvailable;
	
	public InvasionType(String name, InvasionFeatures features) {
		this.invasionName = name;
		this.chance = features.chance;
		this.spawnZombies = features.spawnZombies;
		this.spawnPlants = features.spawnPlants;
		this.bonusbundle = features.bonusbundle;
		this.displayColor = features.displayColor;
		this.isAvailable = features.isAvailable;
		if(this.isAssistEvent()) {//assist event.
			ASSIST_EVENTS.add(this);
		} else {
			SPAWN_EVENTS.add(Pair.of(this, this.chance));
		}
		INVASION_EVENTS.add(this);
	}
	
	public static List<InvasionType> getInvasionEvents(){
		return INVASION_EVENTS;
	}
	
	public static List<Pair<InvasionType, Integer>> getAvailableSpawnEvents(World world) {
		return SPAWN_EVENTS.stream().filter(p -> p.getFirst().isAvailable.apply(world)).collect(Collectors.toList());
	}
	
	public static List<InvasionType> getAvailableAssistEvents(World world) {
		return ASSIST_EVENTS.stream().filter(type -> type.isAvailable.apply(world)).collect(Collectors.toList());
	}
	
	public List<ZombieType> getSpawnZombies(World world){
		return this.spawnZombies;
	}
	
	public boolean isAssistEvent() {
		return this.spawnZombies.isEmpty() && this.spawnPlants.isEmpty();
	}
	
	public int getChance() {
		return this.chance;
	}
	
	public BundleType getBundle() {
		return this.bonusbundle;
	}
	
	public ITextComponent getEventText() {
		return new TranslationTextComponent("event.pvz." + this.toString()).withStyle(this.displayColor);
	}
	
	@Override
	public String toString() {
		return this.invasionName;
	}
	
	public String getIdentity() {
		return this.getModID() + ":" + this.toString();
	}
	
	/**
	 * specific mod id.
	 */
	public abstract String getModID();
	
	public static class InvasionFeatures{
		protected final List<ZombieType> spawnZombies = new ArrayList<>();
		protected final List<PlantType> spawnPlants = new ArrayList<>();
		protected int chance = 10;
		protected BundleType bonusbundle = Bundles.RANDOM_ALL;
		protected TextFormatting displayColor = TextFormatting.WHITE;
		protected Function<World, Boolean> isAvailable = world -> true;
		
		/** 
		 * add single plant.
		 */
		public InvasionFeatures addPlant(PlantType type) {
			this.spawnPlants.add(type);
			return this;
		}
		
		/**
		 *  add single zombie.
		 */
		public InvasionFeatures addZombie(ZombieType type) {
			this.spawnZombies.add(type);
			return this;
		}
		
		/**
		 * set invasion bonus.
		 */
		public InvasionFeatures bundle(BundleType type) {
			this.bonusbundle = type;
			return this;
		}
		
		/**
		 * set choose chance.
		 */
		public InvasionFeatures chance(int chance) {
			this.chance = chance;
			return this;
		}
		
		/**
		 * set display color of information.
		 */
		public InvasionFeatures color(TextFormatting color) {
			this.displayColor = color;
			return this;
		}
		
		/**
		 * set available condition.
		 */
		public InvasionFeatures available(Function<World, Boolean> fuc) {
			this.isAvailable = fuc;
			return this;
		}
		
	}
	
}
