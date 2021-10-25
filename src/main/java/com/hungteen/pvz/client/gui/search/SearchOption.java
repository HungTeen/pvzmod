package com.hungteen.pvz.client.gui.search;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.common.impl.PlantType;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.*;

public class SearchOption {
	public static final List<SearchOption> OPTION = new ArrayList<>();
	public static final Map<SearchOption, Integer> OPTION_MAP = new HashMap<>();
	static {
		putAlamanac(new SearchOption());
		for(IPlantType plant : PlantType.getPlants()) {
			putAlamanac(new SearchOption(plant));
		}
	}
	
	private static void putAlamanac(SearchOption a) {
		OPTION_MAP.put(a, OPTION.size());
		OPTION.add(a);
	}
	
	private Optional<IPlantType> plant = Optional.empty();
	private Optional<IZombieType> zombie = Optional.empty();
	
	public SearchOption() {
	}
	
	public SearchOption(IPlantType plant) {
		this.plant = Optional.of(plant);
	}
	
	public SearchOption(IZombieType zombie) {
		this.zombie = Optional.of(zombie);
	}
	
	public static SearchOption get() {
		return OPTION.get(0);
	}
	
	public static SearchOption get(IPlantType plant) {
		return OPTION.get(plant.getId() + 1);
	}
	
	public static SearchOption get(IZombieType zombie) {
		return OPTION.get(PlantType.size() + zombie.getId() + 1);
	}
	
	@SuppressWarnings("resource")
	public static ITextComponent getOptionName(SearchOption a) {
		if(a.isPlayer()) return Minecraft.getInstance().player.getName();
		if(a.isPlant()) {
			final IPlantType plant = a.getPlant().get();
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
	
	public Optional<IPlantType> getPlant() {
		return this.plant;
	}
	
	public Optional<IZombieType> getZombie() {
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
