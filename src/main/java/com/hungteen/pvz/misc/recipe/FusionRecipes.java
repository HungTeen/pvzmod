package com.hungteen.pvz.misc.recipe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.hungteen.pvz.gui.search.SearchOption;
import com.hungteen.pvz.utils.enums.Plants;
import com.mojang.datafixers.util.Pair;

public enum FusionRecipes {

	REPEATER(Arrays.asList(Plants.PEA_SHOOTER, Plants.PEA_SHOOTER), 
			Arrays.asList(Pair.of(Plants.REPEATER, 90))),
	PUFF_SEA_SHROOM(Arrays.asList(Plants.FUME_SHROOM, Plants.SCAREDY_SHROOM),
			Arrays.asList(Pair.of(Plants.PUFF_SHROOM, 30), Pair.of(Plants.SEA_SHROOM, 60))),
	THREE_PEATER(Arrays.asList(Plants.PEA_SHOOTER, Plants.PEA_SHOOTER, Plants.PEA_SHOOTER),
			Arrays.asList(Pair.of(Plants.THREE_PEATER, 80))),
	GIANT_TALL_NUT(Arrays.asList(Plants.WALL_NUT, Plants.WALL_NUT),
			Arrays.asList(Pair.of(Plants.TALL_NUT, 80), Pair.of(Plants.GIANT_WALL_NUT, 5))),
	SPLIT_PEA(Arrays.asList(Plants.PEA_SHOOTER, Plants.REPEATER), 
			Arrays.asList(Pair.of(Plants.SPLIT_PEA, 90))),
	WATER_GUARD(Arrays.asList(Plants.LILY_PAD, Plants.WALL_NUT), 
			Arrays.asList(Pair.of(Plants.WATER_GUARD, 90))),
	EXPLODE_O_NUT(Arrays.asList(Plants.CHERRY_BOMB, Plants.WALL_NUT), 
			Arrays.asList(Pair.of(Plants.EXPLODE_O_NUT, 80)));
	
    public static final Map<Plants, FusionRecipes> PLANT_FUSION_RECIPE = new HashMap<>();
	public final List<Plants> requirePlants;
	public final List<Pair<Plants, Integer>> resultPlants;

	static {
		for(FusionRecipes recipe : FusionRecipes.values()) {
			for(Pair<Plants, Integer> p : recipe.resultPlants) {
				PLANT_FUSION_RECIPE.put(p.getFirst(), recipe);
			}
		}
	}
	
	public static boolean isOptionHasFusionRecipe(SearchOption option) {
		if(! option.isPlant()) return false;
		Plants plant = option.getPlant().get();
		return PLANT_FUSION_RECIPE.containsKey(plant);
	}
	
	public static Optional<FusionRecipes> getFusionRecipe(Plants plant){
		if(PLANT_FUSION_RECIPE.containsKey(plant)) {
			return Optional.of(PLANT_FUSION_RECIPE.get(plant));
		}
		System.out.println("ERROR : No Fusion Recipe For Plant !");
		return Optional.empty();
	}
	
	private FusionRecipes(List<Plants> plants, List<Pair<Plants, Integer>> results) {
		this.requirePlants = plants;
		this.resultPlants = results;
	}
}