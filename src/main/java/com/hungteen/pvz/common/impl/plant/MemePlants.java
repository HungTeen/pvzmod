package com.hungteen.pvz.common.impl.plant;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.model.entity.plant.magic.StrangeCatModel;
import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.impl.Essences;
import com.hungteen.pvz.common.impl.Placements;
import com.hungteen.pvz.common.impl.Ranks;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ItemRegister;

public final class MemePlants extends PlantType {
	
	private static final List<PlantType> LIST = new ArrayList<>();
	
	public static final PlantType STRANGE_CAT = new MemePlants("strange_cat", new PlantFeatures()
			.cost(325).cd(PlantCardCD.HUGE_SLOW).rank(Ranks.PURPLE).essence(Essences.MAGIC)
			.entityType(() -> EntityRegister.STRANGE_CAT.get())
			.summonCard(() -> ItemRegister.STRANGE_CAT_CARD.get())
			.enjoyCard(() -> ItemRegister.STRANGE_CAT_ENJOY_CARD.get())
			.plantModel(() -> StrangeCatModel::new).scale(0.18F)
			.placement(Placements.ANY)
	);
	
	public static void register() {
		registerPlants(LIST);
	}
	
	private MemePlants(String name, PlantFeatures features) {
		super(name, features);
		LIST.add(this);
	}
	
    @Override
	public int getSortPriority() {
		return 80;
	}
    
	@Override
	public String getCategoryName() {
		return "meme";
	}

	@Override
	public String getModID() {
		return PVZMod.MOD_ID;
	}
}
