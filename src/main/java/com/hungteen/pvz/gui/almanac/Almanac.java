package com.hungteen.pvz.gui.almanac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.TranslationTextComponent;

public class Almanac {

	public static final List<Almanac> ALMANACS = new ArrayList<>();
	public static final Map<Almanac, Integer> ALMANAC_MAP = new HashMap<>();
	static {
		putAlamanac(new Almanac());
		for(Plants plant : Plants.values()) {
			putAlamanac(new Almanac(plant));
		}
	}
	
	private static void putAlamanac(Almanac a) {
		ALMANAC_MAP.put(a, ALMANACS.size());
		ALMANACS.add(a);
	}
	
	private Optional<Plants> plant = Optional.empty();
	private Optional<Zombies> zombie = Optional.empty();
	
	public Almanac() {
	}
	
	public Almanac(Plants plant) {
		this.plant = Optional.of(plant);
	}
	
	public Almanac(Zombies zombie) {
		this.zombie = Optional.of(zombie);
	}
	
	public static Almanac get() {
		return ALMANACS.get(0);
	}
	
	public static Almanac get(Plants plant) {
		return ALMANACS.get(plant.ordinal() + 1);
	}
	
	public static Almanac get(Zombies zombie) {
		return ALMANACS.get(Plants.values().length + zombie.ordinal());
	}
	
	@SuppressWarnings("resource")
	public static String getAlmanacName(Almanac a) {
		if(a.isPlayer()) return Minecraft.getInstance().player.getName().getFormattedText();
		if(a.isPlant()) {
			Plants plant = a.getPlant().get();
			return new TranslationTextComponent("entity.pvz." + plant.toString().toLowerCase()).getFormattedText();
		}
	    return new TranslationTextComponent("entity.pvz." + a.toString().toLowerCase()).getFormattedText();
	}
	
	public static ItemStack getItemStackByAlmanac(Almanac a) {
		if(a.isPlayer()) return new ItemStack(Items.PLAYER_HEAD);
		if(a.isPlant()) return new ItemStack(PlantUtil.getPlantSummonCard(a.getPlant().get()));
		return ItemStack.EMPTY;
	}
	
	public Optional<Plants> getPlant() {
		return this.plant;
	}
	
	public Optional<Zombies> getZombie() {
		return this.zombie;
	}
	
	public boolean isPlant() {
		return this.plant.isPresent();
	}
	
	public boolean isZombie() {
		return this.zombie.isPresent();
	}
	
	public int ordinal() {
		return ALMANAC_MAP.get(this);
	}
	
	public boolean isPlayer() {
		return ! this.isPlant() && ! this.isZombie();
	}
	
	public static enum Categories{
		ALL, PLANTS, ZOMBIES;
	}

}
