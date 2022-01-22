package com.hungteen.pvz.common.impl.plant;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.client.model.entity.plant.appease.AngelStarFruitModel;
import com.hungteen.pvz.client.model.entity.plant.enforce.BonkChoyModel;
import com.hungteen.pvz.client.model.entity.plant.explosion.BambooLordModel;
import com.hungteen.pvz.client.model.entity.plant.ice.IcebergLettuceModel;
import com.hungteen.pvz.client.model.entity.plant.light.GoldLeafModel;
import com.hungteen.pvz.common.impl.*;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.entity.EntityRegister;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class OtherPlants extends PlantType {
	
	private static final List<IPlantType> LIST = new ArrayList<>();
	
	public static final IPlantType ICEBERG_LETTUCE = new OtherPlants("iceberg_lettuce", new PlantFeatures()
			.cost(1).requiredLevel(5)
			.cd(CoolDowns.LITTLE_FAST).rank(RankTypes.GRAY).essence(EssenceTypes.ICE)
			.entityType(() -> EntityRegister.ICEBERG_LETTUCE.get())
			.summonCard(() -> ItemRegister.ICEBERG_LETTUCE_CARD.get())
			.enjoyCard(() -> ItemRegister.ICEBERG_LETTUCE_ENJOY_CARD.get())
			.plantModel(() -> IcebergLettuceModel::new).scale(0.5F)
			.commonSkill(Arrays.asList())
	);
	
	public static final IPlantType BONK_CHOY = new OtherPlants("bonk_choy", new PlantFeatures()
			.cost(175).requiredLevel(20)
			.cd(CoolDowns.LITTLE_FAST).rank(RankTypes.PURPLE).essence(EssenceTypes.ENFORCE)
			.entityType(() -> EntityRegister.BONK_CHOY.get())
			.summonCard(() -> ItemRegister.BONK_CHOY_CARD.get())
			.enjoyCard(() -> ItemRegister.BONK_CHOY_ENJOY_CARD.get())
			.plantModel(() -> BonkChoyModel::new).scale(0.9F)
			.commonSkill(Arrays.asList(SkillTypes.MORE_SWING_DAMAGE))
	);
	
	public static final IPlantType GOLD_LEAF = new OtherPlants("gold_leaf", new PlantFeatures()
			.cost(75).requiredLevel(70)
			.cd(CoolDowns.NORMAL).rank(RankTypes.PURPLE).essence(EssenceTypes.LIGHT)
			.entityType(() -> EntityRegister.GOLD_LEAF.get())
			.summonCard(() -> ItemRegister.GOLD_LEAF_CARD.get())
			.enjoyCard(() -> ItemRegister.GOLD_LEAF_ENJOY_CARD.get())
			.plantModel(() -> GoldLeafModel::new).scale(0.8F)
			.placement(Placements.GOLD)
			.cdSkill(Arrays.asList(SkillTypes.ADVANCE_GOLD))
	);
	
	public static final IPlantType ANGEL_STAR_FRUIT = new OtherPlants("angel_star_fruit", new PlantFeatures()
			.cost(225).requiredLevel(50)
			.cd(CoolDowns.LITTLE_FAST).rank(RankTypes.PURPLE).essence(EssenceTypes.APPEASE)
			.entityType(() -> EntityRegister.ANGEL_STAR_FRUIT.get())
			.summonCard(() -> ItemRegister.ANGEL_STAR_FRUIT_CARD.get())
			.enjoyCard(() -> ItemRegister.ANGEL_STAR_FRUIT_ENJOY_CARD.get())
			.plantModel(() -> AngelStarFruitModel::new).scale(0.8F)
			.commonSunSkill(Arrays.asList(SkillTypes.MORE_STAR_DAMAGE, SkillTypes.TEN_STARS))
	);
	
	public static final IPlantType BAMBOO_LORD = new OtherPlants("bamboo_lord", new PlantFeatures()
			.cost(75).requiredLevel(55)
			.cd(CoolDowns.NORMAL).rank(RankTypes.BLUE).essence(EssenceTypes.EXPLOSION)
			.entityType(() -> EntityRegister.BAMBOO_LORD.get())
			.summonCard(() -> ItemRegister.BAMBOO_LORD_CARD.get())
			.enjoyCard(() -> ItemRegister.BAMBOO_LORD_ENJOY_CARD.get())
			.plantModel(() -> BambooLordModel::new).scale(1F)
			.commonSkill(Arrays.asList(SkillTypes.SMALL_BOMB_DAMAGE))
	);
	
	public static void register() {
		PVZAPI.get().registerPlantTypes(LIST);
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
