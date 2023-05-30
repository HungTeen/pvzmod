package com.hungteen.pvz.common.impl.plant;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.client.model.entity.plant.arma.KernelPultModel;
import com.hungteen.pvz.client.model.entity.plant.defence.WaterGuardModel;
import com.hungteen.pvz.common.impl.EssenceTypes;
import com.hungteen.pvz.common.impl.CoolDowns;
import com.hungteen.pvz.common.impl.RankTypes;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.entity.EntityRegister;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CustomPlants extends PlantType {
	
	private static final List<IPlantType> LIST = new ArrayList<>();
	
	public static final IPlantType WATER_GUARD = new CustomPlants("water_guard", new PlantFeatures().isWaterPlant()
			.cost(50).requiredLevel(4)
			.cd(CoolDowns.LITTLE_SLOW).rank(RankTypes.WHITE).essence(EssenceTypes.DEFENCE)
			.entityType(() -> EntityRegister.WATER_GUARD.get())
			.summonCard(() -> ItemRegister.WATER_GUARD_CARD.get())
			.enjoyCard(() -> ItemRegister.WATER_GUARD_ENJOY_CARD.get())
			.plantModel(() -> WaterGuardModel::new).scale(0.8F)
			.cdSkill(Arrays.asList(SkillTypes.MORE_GUARD_LIFE))
	);
	
	public static final IPlantType BUTTER_PULT = new CustomPlants("butter_pult", new PlantFeatures()
			.cost(275).requiredLevel(56)
			.cd(CoolDowns.NORMAL).rank(RankTypes.BLACK).essence(EssenceTypes.ARMA)
			.entityType(() -> EntityRegister.BUTTER_PULT.get())
			.summonCard(() -> ItemRegister.BUTTER_PULT_CARD.get())
			.enjoyCard(() -> ItemRegister.BUTTER_PULT_ENJOY_CARD.get())
			.plantModel(() -> KernelPultModel::new).scale(0.9F)
			.commonSunSkill(Arrays.asList(SkillTypes.MORE_BUTTER_DAMAGE))
	);
	
	public static void register() {
		PVZAPI.get().registerPlantTypes(LIST);
	}
	
	private CustomPlants(String name, PlantFeatures features) {
		super(name, features);
		LIST.add(this);
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
