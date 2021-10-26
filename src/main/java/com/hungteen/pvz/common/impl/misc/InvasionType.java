package com.hungteen.pvz.common.impl.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.hungteen.pvz.api.types.IBundleType;
import com.hungteen.pvz.api.types.IInvasionType;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.common.impl.Bundles;
import com.mojang.datafixers.util.Pair;

import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

/**
 * use to extend Plant Types. <br>
 * define your category, same category is allowed. <br>
 * if you wanna show in front of that category, then override one with higher priority. <br>
 */
public abstract class InvasionType implements IInvasionType {

	private static final List<IInvasionType> INVASION_EVENTS = new ArrayList<>();
	private static final List<Pair<IInvasionType, Integer>> SPAWN_EVENTS = new ArrayList<>();
	private static final List<IInvasionType> ASSIST_EVENTS = new ArrayList<>();
	protected final List<IZombieType> spawnZombies;
	protected final List<IPlantType> spawnPlants;
	protected final String invasionName;
	protected final int chance;
	protected final IBundleType bonusbundle;
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
	}
	
	public static void registerInvasions(List<IInvasionType> types) {
		types.forEach(type -> registerInvasion(type));
	}
	
	private static void registerInvasion(IInvasionType type) {
		if(type.isAssistEvent()) {//assist event.
			ASSIST_EVENTS.add(type);
		} else {
			SPAWN_EVENTS.add(Pair.of(type, type.getChance()));
		}
		INVASION_EVENTS.add(type);
	}
	
	public static List<IInvasionType> getInvasionEvents(){
		return INVASION_EVENTS;
	}
	
	public static List<Pair<IInvasionType, Integer>> getAvailableSpawnEvents(World world) {
		return SPAWN_EVENTS.stream().filter(p -> p.getFirst().isAvailable(world)).collect(Collectors.toList());
	}
	
	public static List<IInvasionType> getAvailableAssistEvents(World world) {
		return ASSIST_EVENTS.stream().filter(type -> type.isAvailable(world)).collect(Collectors.toList());
	}
	
	@Override
	public List<IZombieType> getSpawnZombies(World world){
		return this.spawnZombies;
	}
	
	@Override
	public boolean isAvailable(World world) {
		return this.isAvailable.apply(world);
	}
	
	@Override
	public boolean isAssistEvent() {
		return this.spawnZombies.isEmpty() && this.spawnPlants.isEmpty();
	}
	
	@Override
	public int getChance() {
		return this.chance;
	}
	
	@Override
	public IBundleType getBundle() {
		return this.bonusbundle;
	}
	
	@Override
	public IFormattableTextComponent getText() {
		return new TranslationTextComponent("event.pvz." + this.toString()).withStyle(this.displayColor);
	}
	
	@Override
	public String toString() {
		return this.invasionName;
	}
	
	@Override
	public String getIdentity() {
		return this.getModID() + ":" + this.toString();
	}
	
	public static class InvasionFeatures {
		
		protected final List<IZombieType> spawnZombies = new ArrayList<>();
		protected final List<IPlantType> spawnPlants = new ArrayList<>();
		protected int chance = 10;
		protected IBundleType bonusbundle = Bundles.RANDOM_ALL;
		protected TextFormatting displayColor = TextFormatting.WHITE;
		protected Function<World, Boolean> isAvailable = world -> true;
		
		/** 
		 * add single plant.
		 */
		public InvasionFeatures addPlant(IPlantType type) {
			this.spawnPlants.add(type);
			return this;
		}
		
		/**
		 *  add single zombie.
		 */
		public InvasionFeatures addZombie(IZombieType type) {
			this.spawnZombies.add(type);
			return this;
		}
		
		/**
		 * set invasion bonus.
		 */
		public InvasionFeatures bundle(IBundleType type) {
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
