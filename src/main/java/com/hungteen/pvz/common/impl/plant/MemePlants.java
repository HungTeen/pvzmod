package com.hungteen.pvz.common.impl.plant;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.client.model.entity.plant.magic.StrangeCatModel;
import com.hungteen.pvz.common.impl.EssenceType;
import com.hungteen.pvz.common.impl.Placements;
import com.hungteen.pvz.common.impl.PlantType;
import com.hungteen.pvz.common.impl.Ranks;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ItemRegister;

import java.util.ArrayList;
import java.util.List;

public final class MemePlants extends PlantType {
	
	private static final List<IPlantType> LIST = new ArrayList<>();
	
	public static final IPlantType STRANGE_CAT = new MemePlants("strange_cat", new PlantFeatures()
			.cost(300).level(20).limitLevel(45)
			.cd(PlantCardCD.HUGE_SLOW).rank(Ranks.PURPLE).essence(EssenceType.MAGIC)
			.entityType(EntityRegister.STRANGE_CAT::get)
			.summonCard(ItemRegister.STRANGE_CAT_CARD::get)
			.enjoyCard(ItemRegister.STRANGE_CAT_ENJOY_CARD::get)
			.plantModel(() -> StrangeCatModel::new).scale(0.18F)
			.placement(Placements.ANY)
	);
	
	public static void register() {
		PVZAPI.get().registerPlantTypes(LIST);
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
