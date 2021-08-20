package com.hungteen.pvz.common.impl.plant;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.event.PVZRegisterEvent;
import com.hungteen.pvz.client.model.entity.plant.arma.KernelPultModel;
import com.hungteen.pvz.client.model.entity.plant.defence.WaterGuardModel;
import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.impl.Essences;
import com.hungteen.pvz.common.impl.ImplEvents;
import com.hungteen.pvz.common.impl.Ranks;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ItemRegister;

public final class CustomPlants extends PlantType {
	
	private static final List<PlantType> CUSTOM_PLANTS = new ArrayList<>();
	
	public static final PlantType WATER_GUARD = new CustomPlants("water_guard", new PlantFeatures().isWaterPlant()
			.cost(50).cd(PlantCardCD.LITTLE_SLOW).rank(Ranks.GRAY).essence(Essences.DEFENCE)
			.entityType(() -> EntityRegister.WATER_GUARD.get())
			.summonCard(() -> ItemRegister.WATER_GUARD_CARD.get())
			.enjoyCard(() -> ItemRegister.WATER_GUARD_ENJOY_CARD.get())
			.plantModel(() -> WaterGuardModel::new).scale(0.8F)
	);
	
	public static final PlantType BUTTER_PULT = new CustomPlants("butter_pult", new PlantFeatures()
			.cost(275).cd(PlantCardCD.NORMAL).rank(Ranks.GOLD).essence(Essences.ARMA)
			.entityType(() -> EntityRegister.BUTTER_PULT.get())
			.summonCard(() -> ItemRegister.BUTTER_PULT_CARD.get())
			.enjoyCard(() -> ItemRegister.BUTTER_PULT_ENJOY_CARD.get())
			.plantModel(() -> KernelPultModel::new).scale(0.9F)
	);
	
	private CustomPlants(String name, PlantFeatures features) {
		super(name, features);
		CUSTOM_PLANTS.add(this);
	}
	
	/**
	 * {@link ImplEvents#registerPlantTypes(PVZRegisterEvent)}
	 */
	public static void register() {
		PlantManager.registerPlants(CUSTOM_PLANTS);
	}
	
    @Override
	public int getSortPriority() {
		return 60;
	}
    
	@Override
	public String getCategoryName() {
		return "custom";
	}

	@Override
	public String getModID() {
		return PVZMod.MOD_ID;
	}
}
