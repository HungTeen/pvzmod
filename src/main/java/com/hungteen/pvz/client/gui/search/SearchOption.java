package com.hungteen.pvz.client.gui.search;

import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPAZType;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.api.types.IZombieType;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchOption {

	private static final List<SearchOption> OPTIONS = new ArrayList<>();
	private static final Map<SearchOption, Integer> OPTION_MAP = new HashMap<>();

	static {
		PVZAPI.get().getPAZs().forEach(type -> {
			if(type.getSummonCard().isPresent()){
				putAlmanac(new SearchOption(type));
			}
		});
	}
	
	private static void putAlmanac(SearchOption a) {
		OPTION_MAP.put(a, OPTIONS.size());
		OPTIONS.add(a);
	}

	public static SearchOption get(IPAZType type) {
		return OPTIONS.get(type.getId());
	}

	public static ItemStack getItemStackByOption(SearchOption a) {
		if(a.getType().getSummonCard().isPresent()){
			return new ItemStack(a.getType().getSummonCard().get());
		}
		return ItemStack.EMPTY;
	}

	/**
	 * filter options for specific category. {@link OptionPage#getCurrentList(CategoryToggleWidget.SearchCategories)}
	 */
	public static List<SearchOption> getSearchOptionsByCategory(CategoryToggleWidget.SearchCategories category) {
		List<SearchOption> list = new ArrayList<>();
		SearchOption.OPTIONS.forEach((a) -> {
			if(category == CategoryToggleWidget.SearchCategories.ALL){
				list.add(a);
			} else if(a.isPlantType() && category == CategoryToggleWidget.SearchCategories.PLANTS){
				list.add(a);
			} else if(a.isZombieType() && category == CategoryToggleWidget.SearchCategories.ZOMBIES){
				list.add(a);
			}
		});
		return list;
	}

	private final IPAZType type;
	public SearchOption(IPAZType type) {
		this.type = type;
	}

	public IPAZType getType() {
		return this.type;
	}
	
	public boolean isPlantType() {
		return this.type instanceof IPlantType;
	}
	
	public boolean isZombieType() {
		return this.type instanceof IZombieType;
	}
	
	public int ordinal() {
		return OPTION_MAP.get(this);
	}
	
}
