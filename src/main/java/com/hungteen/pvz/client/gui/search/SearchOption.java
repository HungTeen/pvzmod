package com.hungteen.pvz.client.gui.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.remove.Zombies;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class SearchOption {
	public static final List<SearchOption> OPTION = new ArrayList<>();
	public static final Map<SearchOption, Integer> OPTION_MAP = new HashMap<>();
	static {
		putAlamanac(new SearchOption());
		for(PlantType plant : PlantType.getPlants()) {
			putAlamanac(new SearchOption(plant));
		}
	}
	
	private static void putAlamanac(SearchOption a) {
		OPTION_MAP.put(a, OPTION.size());
		OPTION.add(a);
	}
	
	private Optional<PlantType> plant = Optional.empty();
	private Optional<Zombies> zombie = Optional.empty();
	
	public SearchOption() {
	}
	
	public SearchOption(PlantType plant) {
		this.plant = Optional.of(plant);
	}
	
	public SearchOption(Zombies zombie) {
		this.zombie = Optional.of(zombie);
	}
	
	public static SearchOption get() {
		return OPTION.get(0);
	}
	
	public static SearchOption get(PlantType plant) {
		return OPTION.get(plant.getId() + 1);
	}
	
	public static SearchOption get(Zombies zombie) {
		return OPTION.get(PlantType.size() + zombie.ordinal());
	}
	
	@SuppressWarnings("resource")
	public static ITextComponent getOptionName(SearchOption a) {
		if(a.isPlayer()) return Minecraft.getInstance().player.getName();
		if(a.isPlant()) {
			PlantType plant = a.getPlant().get();
			return new TranslationTextComponent("entity.pvz." + plant.toString().toLowerCase());
		}
	    return new TranslationTextComponent("entity.pvz." + a.toString().toLowerCase());
	}
	
	public static ItemStack getItemStackByOption(SearchOption a) {
		if(a.isPlayer()) {
			return new ItemStack(Items.PLAYER_HEAD);
		}
		if(a.isPlant() && a.getPlant().get().getSummonCard().isPresent()) {
			return new ItemStack(a.getPlant().get().getSummonCard().get());
		}
		return ItemStack.EMPTY;
	}
	
	public static List<SearchOption> getSearchOptionsByCategory(SearchCategories category) {
		List<SearchOption> list = new ArrayList<>();
//		SearchOption.OPTION.forEach((a) -> {
//			if(category == SearchCategories.ALL) list.add(a);
//			if(category == SearchCategories.FUSION && FusionRecipes.isOptionHasFusionRecipe(a)) list.add(a);
//			else if(a.isPlant() && category == SearchCategories.PLANTS) list.add(a);
//			else if(a.isZombie() && category == SearchCategories.ZOMBIES) list.add(a);
//			
//		});
		return list;
	}
	
	public Optional<PlantType> getPlant() {
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
		return OPTION_MAP.get(this);
	}
	
	public boolean isPlayer() {
		return ! this.isPlant() && ! this.isZombie();
	}
	
}
