package com.hungteen.pvz.common.impl.plant;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.model.entity.plant.appease.AngelStarFruitModel;
import com.hungteen.pvz.client.model.entity.plant.enforce.BonkChoyModel;
import com.hungteen.pvz.client.model.entity.plant.explosion.BambooLordModel;
import com.hungteen.pvz.client.model.entity.plant.ice.IcebergLettuceModel;
import com.hungteen.pvz.client.model.entity.plant.light.GoldLeafModel;
import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.impl.Essences;
import com.hungteen.pvz.common.impl.Placements;
import com.hungteen.pvz.common.impl.Ranks;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ItemRegister;

public final class OtherPlants extends PlantType {
	
	private static final List<PlantType> LIST = new ArrayList<>();
	
	public static final PlantType ICEBERG_LETTUCE = new OtherPlants("iceberg_lettuce", new PlantFeatures()
			.cost(1).cd(PlantCardCD.LITTLE_FAST).rank(Ranks.WHITE).essence(Essences.ICE)
			.entityType(() -> EntityRegister.ICEBERG_LETTUCE.get())
			.summonCard(() -> ItemRegister.ICEBERG_LETTUCE_CARD.get())
			.enjoyCard(() -> ItemRegister.ICEBERG_LETTUCE_ENJOY_CARD.get())
			.plantModel(() -> IcebergLettuceModel::new).scale(0.5F)
	);
	
	public static final PlantType BONK_CHOY = new OtherPlants("bonk_choy", new PlantFeatures()
			.cost(175).cd(PlantCardCD.LITTLE_FAST).rank(Ranks.BLUE).essence(Essences.ENFORCE)
			.entityType(() -> EntityRegister.BONK_CHOY.get())
			.summonCard(() -> ItemRegister.BONK_CHOY_CARD.get())
			.enjoyCard(() -> ItemRegister.BONK_CHOY_ENJOY_CARD.get())
			.plantModel(() -> BonkChoyModel::new).scale(0.9F)
	);
	
	public static final PlantType GOLD_LEAF = new OtherPlants("gold_leaf", new PlantFeatures()
			.cost(75).cd(PlantCardCD.NORMAL).rank(Ranks.BLUE).essence(Essences.LIGHT)
			.entityType(() -> EntityRegister.GOLD_LEAF.get())
			.summonCard(() -> ItemRegister.GOLD_LEAF_CARD.get())
			.enjoyCard(() -> ItemRegister.GOLD_LEAF_ENJOY_CARD.get())
			.plantModel(() -> GoldLeafModel::new).scale(0.8F)
			.placement(Placements.GOLD)
	);
	
	public static final PlantType ANGEL_STAR_FRUIT = new OtherPlants("angel_star_fruit", new PlantFeatures()
			.cost(225).cd(PlantCardCD.LITTLE_FAST).rank(Ranks.PURPLE).essence(Essences.APPEASE)
			.entityType(() -> EntityRegister.ANGEL_STAR_FRUIT.get())
			.summonCard(() -> ItemRegister.ANGEL_STAR_FRUIT_CARD.get())
			.enjoyCard(() -> ItemRegister.ANGEL_STAR_FRUIT_ENJOY_CARD.get())
			.plantModel(() -> AngelStarFruitModel::new).scale(0.8F)
	);
	
	public static final PlantType BAMBOO_LORD = new OtherPlants("bamboo_lord", new PlantFeatures()
			.cost(75).cd(PlantCardCD.NORMAL).rank(Ranks.BLUE).essence(Essences.EXPLOSION)
			.entityType(() -> EntityRegister.BAMBOO_LORD.get())
			.summonCard(() -> ItemRegister.BAMBOO_LORD_CARD.get())
			.enjoyCard(() -> ItemRegister.BAMBOO_LORD_ENJOY_CARD.get())
			.plantModel(() -> BambooLordModel::new).scale(1F)
	);
	
	public static void register() {
		registerPlants(LIST);
	}
	
	private OtherPlants(String name, PlantFeatures features) {
		super(name, features);
		LIST.add(this);
	}
	
    @Override
	public int getSortPriority() {
		return 50;
	}
    
	@Override
	public String getCategoryName() {
		return "other";
	}

	@Override
	public String getModID() {
		return PVZMod.MOD_ID;
	}
}
