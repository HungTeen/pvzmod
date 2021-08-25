package com.hungteen.pvz.common.impl.plant;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.model.entity.plant.appease.GatlingPeaModel;
import com.hungteen.pvz.client.model.entity.plant.appease.PeaShooterModel;
import com.hungteen.pvz.client.model.entity.plant.appease.RepeaterModel;
import com.hungteen.pvz.client.model.entity.plant.appease.SplitPeaModel;
import com.hungteen.pvz.client.model.entity.plant.appease.StarFruitModel;
import com.hungteen.pvz.client.model.entity.plant.appease.ThreePeaterModel;
import com.hungteen.pvz.client.model.entity.plant.arma.CabbagePultModel;
import com.hungteen.pvz.client.model.entity.plant.arma.KernelPultModel;
import com.hungteen.pvz.client.model.entity.plant.arma.MelonPultModel;
import com.hungteen.pvz.client.model.entity.plant.assist.BloverModel;
import com.hungteen.pvz.client.model.entity.plant.assist.FlowerPotModel;
import com.hungteen.pvz.client.model.entity.plant.assist.GoldMagnetModel;
import com.hungteen.pvz.client.model.entity.plant.assist.GraveBusterModel;
import com.hungteen.pvz.client.model.entity.plant.assist.LilyPadModel;
import com.hungteen.pvz.client.model.entity.plant.assist.MagnetShroomModel;
import com.hungteen.pvz.client.model.entity.plant.defence.GarlicModel;
import com.hungteen.pvz.client.model.entity.plant.defence.PumpkinModel;
import com.hungteen.pvz.client.model.entity.plant.defence.TallNutModel;
import com.hungteen.pvz.client.model.entity.plant.defence.WallNutModel;
import com.hungteen.pvz.client.model.entity.plant.enforce.ChomperModel;
import com.hungteen.pvz.client.model.entity.plant.enforce.SquashModel;
import com.hungteen.pvz.client.model.entity.plant.enforce.TangleKelpModel;
import com.hungteen.pvz.client.model.entity.plant.enforce.UmbrellaLeafModel;
import com.hungteen.pvz.client.model.entity.plant.explosion.CherryBombModel;
import com.hungteen.pvz.client.model.entity.plant.explosion.CobCannonModel;
import com.hungteen.pvz.client.model.entity.plant.explosion.DoomShroomModel;
import com.hungteen.pvz.client.model.entity.plant.explosion.PotatoMineModel;
import com.hungteen.pvz.client.model.entity.plant.flame.JalapenoModel;
import com.hungteen.pvz.client.model.entity.plant.flame.TorchWoodModel;
import com.hungteen.pvz.client.model.entity.plant.ice.IceShroomModel;
import com.hungteen.pvz.client.model.entity.plant.ice.SnowPeaModel;
import com.hungteen.pvz.client.model.entity.plant.ice.WinterMelonModel;
import com.hungteen.pvz.client.model.entity.plant.light.PlanternModel;
import com.hungteen.pvz.client.model.entity.plant.light.SunFlowerModel;
import com.hungteen.pvz.client.model.entity.plant.light.SunShroomModel;
import com.hungteen.pvz.client.model.entity.plant.light.TwinSunFlowerModel;
import com.hungteen.pvz.client.model.entity.plant.magic.CoffeeBeanModel;
import com.hungteen.pvz.client.model.entity.plant.magic.HypnoShroomModel;
import com.hungteen.pvz.client.model.entity.plant.magic.ImitaterModel;
import com.hungteen.pvz.client.model.entity.plant.magic.MariGoldModel;
import com.hungteen.pvz.client.model.entity.plant.spear.CactusModel;
import com.hungteen.pvz.client.model.entity.plant.spear.CatTailModel;
import com.hungteen.pvz.client.model.entity.plant.spear.SpikeRockModel;
import com.hungteen.pvz.client.model.entity.plant.spear.SpikeWeedModel;
import com.hungteen.pvz.client.model.entity.plant.toxic.FumeShroomModel;
import com.hungteen.pvz.client.model.entity.plant.toxic.GloomShroomModel;
import com.hungteen.pvz.client.model.entity.plant.toxic.PuffShroomModel;
import com.hungteen.pvz.client.model.entity.plant.toxic.ScaredyShroomModel;
import com.hungteen.pvz.client.model.entity.plant.toxic.SeaShroomModel;
import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.impl.Essences;
import com.hungteen.pvz.common.impl.Placements;
import com.hungteen.pvz.common.impl.Ranks;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ItemRegister;

public final class PVZPlants extends PlantType {

	private static final List<PlantType> LIST = new ArrayList<>();
	
	/*
	 * grass day.
	 */
	
	public static final PlantType PEA_SHOOTER = new PVZPlants("pea_shooter", new PlantFeatures()
			.cost(100).cd(PlantCardCD.HUGE_FAST).rank(Ranks.GRAY).essence(Essences.APPEASE)
			.entityType(() -> EntityRegister.PEA_SHOOTER.get())
			.summonCard(() -> ItemRegister.PEA_SHOOTER_CARD.get())
			.enjoyCard(() -> ItemRegister.PEA_SHOOTER_ENJOY_CARD.get())
			.plantModel(() -> PeaShooterModel::new).scale(1F)
	);
	
	public static final PlantType SUN_FLOWER = new PVZPlants("sun_flower", new PlantFeatures()
			.cost(50).cd(PlantCardCD.HUGE_FAST).rank(Ranks.GRAY).essence(Essences.LIGHT)
			.entityType(() -> EntityRegister.SUN_FLOWER.get())
			.summonCard(() -> ItemRegister.SUN_FLOWER_CARD.get())
			.enjoyCard(() -> ItemRegister.SUN_FLOWER_ENJOY_CARD.get())
			.plantModel(() -> SunFlowerModel::new).scale(0.5F)
			.upgradeTo(() -> PVZPlants.TWIN_SUNFLOWER)
	);
	
	public static final PlantType CHERRY_BOMB = new PVZPlants("cherry_bomb", new PlantFeatures()
			.cost(150).cd(PlantCardCD.HUGE_SLOW).rank(Ranks.BLUE).essence(Essences.EXPLOSION)
			.entityType(() -> EntityRegister.CHERRY_BOMB.get())
			.summonCard(() -> ItemRegister.CHERRY_BOMB_CARD.get())
			.enjoyCard(() -> ItemRegister.CHERRY_BOMB_ENJOY_CARD.get())
			.plantModel(() -> CherryBombModel::new).scale(0.5F)
	);
	
	public static final PlantType WALL_NUT = new PVZPlants("wall_nut", new PlantFeatures()
			.cost(50).cd(PlantCardCD.SLOW).rank(Ranks.WHITE).essence(Essences.DEFENCE)
			.entityType(() -> EntityRegister.WALL_NUT.get())
			.summonCard(() -> ItemRegister.WALL_NUT_CARD.get())
			.enjoyCard(() -> ItemRegister.WALL_NUT_ENJOY_CARD.get())
			.plantModel(() -> WallNutModel::new).scale(0.4F)
			.upgradeTo(() -> PVZPlants.GIANT_WALL_NUT)
	);
	
	public static final PlantType POTATO_MINE = new PVZPlants("potato_mine", new PlantFeatures()
			.cost(25).cd(PlantCardCD.LITTLE_SLOW).rank(Ranks.WHITE).essence(Essences.EXPLOSION)
			.entityType(() -> EntityRegister.POTATO_MINE.get())
			.summonCard(() -> ItemRegister.POTATO_MINE_CARD.get())
			.enjoyCard(() -> ItemRegister.POTATO_MINE_ENJOY_CARD.get())
			.plantModel(() -> PotatoMineModel::new).scale(0.6F)
			.placement(Placements.STABLE)
	);
	
	public static final PlantType SNOW_PEA = new PVZPlants("snow_pea", new PlantFeatures()
			.cost(175).cd(PlantCardCD.VERY_FAST).rank(Ranks.GREEN).essence(Essences.ICE)
			.entityType(() -> EntityRegister.SNOW_PEA.get())
			.summonCard(() -> ItemRegister.SNOW_PEA_CARD.get())
			.enjoyCard(() -> ItemRegister.SNOW_PEA_ENJOY_CARD.get())
			.plantModel(() -> SnowPeaModel::new).scale(1F)
	);
	
	public static final PlantType CHOMPER = new PVZPlants("chomper", new PlantFeatures()
			.cost(150).cd(PlantCardCD.NORMAL).rank(Ranks.BLUE).essence(Essences.ENFORCE)
			.entityType(() -> EntityRegister.CHOMPER.get())
			.summonCard(() -> ItemRegister.CHOMPER_CARD.get())
			.enjoyCard(() -> ItemRegister.CHOMPER_ENJOY_CARD.get())
			.plantModel(() -> ChomperModel::new).scale(0.85F)
	);
	
	public static final PlantType REPEATER = new PVZPlants("repeater", new PlantFeatures()
			.cost(200).cd(PlantCardCD.VERY_FAST).rank(Ranks.GREEN).essence(Essences.APPEASE)
			.entityType(() -> EntityRegister.REPEATER.get())
			.summonCard(() -> ItemRegister.REPEATER_CARD.get())
			.enjoyCard(() -> ItemRegister.REPEATER_ENJOY_CARD.get())
			.plantModel(() -> RepeaterModel::new).scale(1F)
			.upgradeTo(() -> PVZPlants.GATLING_PEA)
	);
	
	/*
	 * grass night.
	 */
	
	public static final PlantType PUFF_SHROOM = new PVZPlants("puff_shroom", new PlantFeatures().isShroomPlant()
			.cost(1).cd(PlantCardCD.VERY_FAST).rank(Ranks.WHITE).essence(Essences.TOXIC)
			.entityType(() -> EntityRegister.PUFF_SHROOM.get())
			.summonCard(() -> ItemRegister.PUFF_SHROOM_CARD.get())
			.enjoyCard(() -> ItemRegister.PUFF_SHROOM_ENJOY_CARD.get())
			.plantModel(() -> PuffShroomModel::new).scale(0.6F)
	);
	
	public static final PlantType SUN_SHROOM = new PVZPlants("sun_shroom", new PlantFeatures().isShroomPlant()
			.cost(25).cd(PlantCardCD.VERY_FAST).rank(Ranks.WHITE).essence(Essences.LIGHT)
			.entityType(() -> EntityRegister.SUN_SHROOM.get())
			.summonCard(() -> ItemRegister.SUN_SHROOM_CARD.get())
			.enjoyCard(() -> ItemRegister.SUN_SHROOM_ENJOY_CARD.get())
			.plantModel(() -> SunShroomModel::new).scale(0.4F)
	);
	
	public static final PlantType FUME_SHROOM = new PVZPlants("fume_shroom", new PlantFeatures().isShroomPlant()
			.cost(100).cd(PlantCardCD.LITTLE_FAST).rank(Ranks.GREEN).essence(Essences.TOXIC)
			.entityType(() -> EntityRegister.FUME_SHROOM.get())
			.summonCard(() -> ItemRegister.FUME_SHROOM_CARD.get())
			.enjoyCard(() -> ItemRegister.FUME_SHROOM_ENJOY_CARD.get())
			.plantModel(() -> FumeShroomModel::new).scale(0.9F)
			.upgradeTo(() -> PVZPlants.GLOOM_SHROOM)
	);
	
	public static final PlantType GRAVE_BUSTER = new PVZPlants("grave_buster", new PlantFeatures()
			.cost(75).cd(PlantCardCD.HUGE_FAST).rank(Ranks.WHITE).essence(Essences.ASSIST)
			.entityType(() -> EntityRegister.GRAVE_BUSTER.get())
			.summonCard(() -> ItemRegister.GRAVE_BUSTER_CARD.get())
			.enjoyCard(() -> ItemRegister.GRAVE_BUSTER_ENJOY_CARD.get())
			.plantModel(() -> GraveBusterModel::new).scale(1F)
	);
	
	public static final PlantType HYPNO_SHROOM = new PVZPlants("hypno_shroom", new PlantFeatures().isShroomPlant()
			.cost(75).cd(PlantCardCD.LITTLE_SLOW).rank(Ranks.BLUE).essence(Essences.MAGIC)
			.entityType(() -> EntityRegister.HYPNO_SHROOM.get())
			.summonCard(() -> ItemRegister.HYPNO_SHROOM_CARD.get())
			.enjoyCard(() -> ItemRegister.HYPNO_SHROOM_ENJOY_CARD.get())
			.plantModel(() -> HypnoShroomModel::new).scale(0.6F)
	);
	
	public static final PlantType SCAREDY_SHROOM = new PVZPlants("scaredy_shroom", new PlantFeatures().isShroomPlant()
			.cost(25).cd(PlantCardCD.VERY_FAST).rank(Ranks.WHITE).essence(Essences.TOXIC)
			.entityType(() -> EntityRegister.SCAREDY_SHROOM.get())
			.summonCard(() -> ItemRegister.SCAREDY_SHROOM_CARD.get())
			.enjoyCard(() -> ItemRegister.SCAREDY_SHROOM_ENJOY_CARD.get())
			.plantModel(() -> ScaredyShroomModel::new).scale(0.7F)
	);
	
	public static final PlantType ICE_SHROOM = new PVZPlants("ice_shroom", new PlantFeatures().isShroomPlant()
			.cost(75).cd(PlantCardCD.SLOW).rank(Ranks.BLUE).essence(Essences.ICE)
			.entityType(() -> EntityRegister.ICE_SHROOM.get())
			.summonCard(() -> ItemRegister.ICE_SHROOM_CARD.get())
			.enjoyCard(() -> ItemRegister.ICE_SHROOM_ENJOY_CARD.get())
			.plantModel(() -> IceShroomModel::new).scale(0.82F)
	);
	
	public static final PlantType DOOM_SHROOM = new PVZPlants("doom_shroom", new PlantFeatures().isShroomPlant()
			.cost(225).cd(PlantCardCD.SUPER_SLOW).rank(Ranks.GOLD).essence(Essences.EXPLOSION)
			.entityType(() -> EntityRegister.DOOM_SHROOM.get())
			.summonCard(() -> ItemRegister.DOOM_SHROOM_CARD.get())
			.enjoyCard(() -> ItemRegister.DOOM_SHROOM_ENJOY_CARD.get())
			.plantModel(() -> DoomShroomModel::new).scale(1F)
	);
	
	/*
	 * pool day.
	 */
	public static final PlantType LILY_PAD = new PVZPlants("lily_pad", new PlantFeatures().isBlockPlant().isWaterPlant()
			.cost(25).cd(PlantCardCD.SUPER_FAST).rank(Ranks.GRAY).essence(Essences.ASSIST).level(1)
			.entityType(() -> EntityRegister.LILY_PAD.get())
			.summonCard(() -> ItemRegister.LILY_PAD_CARD.get())
			.enjoyCard(() -> ItemRegister.LILY_PAD_ENJOY_CARD.get())
			.plantModel(() -> LilyPadModel::new).scale(1F)
			.upgradeTo(() -> PVZPlants.CAT_TAIL)
	);
	
	public static final PlantType SQUASH = new PVZPlants("squash", new PlantFeatures()
			.cost(50).cd(PlantCardCD.LITTLE_SLOW).rank(Ranks.GREEN).essence(Essences.ENFORCE)
			.entityType(() -> EntityRegister.SQUASH.get())
			.summonCard(() -> ItemRegister.SQUASH_CARD.get())
			.enjoyCard(() -> ItemRegister.SQUASH_ENJOY_CARD.get())
			.plantModel(() -> SquashModel::new).scale(0.5F)
	);
	
	public static final PlantType THREE_PEATER = new PVZPlants("three_peater", new PlantFeatures()
			.cost(300).cd(PlantCardCD.LITTLE_FAST).rank(Ranks.BLUE).essence(Essences.APPEASE)
			.entityType(() -> EntityRegister.THREE_PEATER.get())
			.summonCard(() -> ItemRegister.THREE_PEATER_CARD.get())
			.enjoyCard(() -> ItemRegister.THREE_PEATER_ENJOY_CARD.get())
			.plantModel(() -> ThreePeaterModel::new).scale(0.98F)
	);
	
	public static final PlantType TANGLE_KELP = new PVZPlants("tangle_kelp", new PlantFeatures().isWaterPlant()
			.cost(25).cd(PlantCardCD.LITTLE_SLOW).rank(Ranks.WHITE).essence(Essences.ENFORCE)
			.entityType(() -> EntityRegister.TANGLE_KELP.get())
			.summonCard(() -> ItemRegister.TANGLE_KELP_CARD.get())
			.enjoyCard(() -> ItemRegister.TANGLE_KELP_ENJOY_CARD.get())
			.plantModel(() -> TangleKelpModel::new).scale(0.6F)
	);
	
	public static final PlantType JALAPENO = new PVZPlants("jalapeno", new PlantFeatures()
			.cost(175).cd(PlantCardCD.HUGE_SLOW).rank(Ranks.BLUE).essence(Essences.FLAME)
			.entityType(() -> EntityRegister.JALAPENO.get())
			.summonCard(() -> ItemRegister.JALAPENO_CARD.get())
			.enjoyCard(() -> ItemRegister.JALAPENO_ENJOY_CARD.get())
			.plantModel(() -> JalapenoModel::new).scale(0.5F)
	);
	
	public static final PlantType SPIKE_WEED = new PVZPlants("spike_weed", new PlantFeatures()
			.cost(100).cd(PlantCardCD.LITTLE_FAST).rank(Ranks.WHITE).essence(Essences.SPEAR)
			.entityType(() -> EntityRegister.SPIKE_WEED.get())
			.summonCard(() -> ItemRegister.SPIKE_WEED_CARD.get())
			.enjoyCard(() -> ItemRegister.SPIKE_WEED_ENJOY_CARD.get())
			.plantModel(() -> SpikeWeedModel::new).scale(0.5F)
			.placement(Placements.STABLE)
			.upgradeTo(() -> PVZPlants.SPIKE_ROCK)
	);
	
	public static final PlantType TORCH_WOOD = new PVZPlants("torch_wood", new PlantFeatures()
			.cost(200).cd(PlantCardCD.NORMAL).rank(Ranks.GREEN).essence(Essences.FLAME)
			.entityType(() -> EntityRegister.TORCH_WOOD.get())
			.summonCard(() -> ItemRegister.TORCH_WOOD_CARD.get())
			.enjoyCard(() -> ItemRegister.TORCH_WOOD_ENJOY_CARD.get())
			.plantModel(() -> TorchWoodModel::new).scale(0.5F)
	);
	
	public static final PlantType TALL_NUT = new PVZPlants("tall_nut", new PlantFeatures()
			.cost(125).cd(PlantCardCD.VERY_SLOW).rank(Ranks.BLUE).essence(Essences.DEFENCE)
			.entityType(() -> EntityRegister.TALL_NUT.get())
			.summonCard(() -> ItemRegister.TALL_NUT_CARD.get())
			.enjoyCard(() -> ItemRegister.TALL_NUT_ENJOY_CARD.get())
			.plantModel(() -> TallNutModel::new).scale(0.4F)
	);
	
	/*
	 * pool night.
	 */
	public static final PlantType SEA_SHROOM = new PVZPlants("sea_shroom", new PlantFeatures().isShroomPlant().isWaterPlant()
			.cost(1).cd(PlantCardCD.FAST).rank(Ranks.WHITE).essence(Essences.TOXIC)
			.entityType(() -> EntityRegister.SEA_SHROOM.get())
			.summonCard(() -> ItemRegister.SEA_SHROOM_CARD.get())
			.enjoyCard(() -> ItemRegister.SEA_SHROOM_ENJOY_CARD.get())
			.plantModel(() -> SeaShroomModel::new).scale(0.6F)
	);
	
	public static final PlantType PLANTERN = new PVZPlants("plantern", new PlantFeatures()
			.cost(75).cd(PlantCardCD.FAST).rank(Ranks.WHITE).essence(Essences.LIGHT)
			.entityType(() -> EntityRegister.PLANTERN.get())
			.summonCard(() -> ItemRegister.PLANTERN_CARD.get())
			.enjoyCard(() -> ItemRegister.PLANTERN_ENJOY_CARD.get())
			.plantModel(() -> PlanternModel::new).scale(0.7F)
	);
	
	public static final PlantType CACTUS = new PVZPlants("cactus", new PlantFeatures()
			.cost(125).cd(PlantCardCD.FAST).rank(Ranks.GREEN).essence(Essences.SPEAR)
			.entityType(() -> EntityRegister.CACTUS.get())
			.summonCard(() -> ItemRegister.CACTUS_CARD.get())
			.enjoyCard(() -> ItemRegister.CACTUS_ENJOY_CARD.get())
			.plantModel(() -> CactusModel::new).scale(1F)
			.placement(Placements.SAND)
	);
	
	public static final PlantType BLOVER = new PVZPlants("blover", new PlantFeatures()
			.cost(50).cd(PlantCardCD.FAST).rank(Ranks.GREEN).essence(Essences.ASSIST)
			.entityType(() -> EntityRegister.BLOVER.get())
			.summonCard(() -> ItemRegister.BLOVER_CARD.get())
			.enjoyCard(() -> ItemRegister.BLOVER_ENJOY_CARD.get())
			.plantModel(() -> BloverModel::new).scale(1.2F)
	);
	
	public static final PlantType SPLIT_PEA = new PVZPlants("split_pea", new PlantFeatures()
			.cost(125).cd(PlantCardCD.HUGE_FAST).rank(Ranks.WHITE).essence(Essences.APPEASE)
			.entityType(() -> EntityRegister.SPLIT_PEA.get())
			.summonCard(() -> ItemRegister.SPLIT_PEA_CARD.get())
			.enjoyCard(() -> ItemRegister.SPLIT_PEA_ENJOY_CARD.get())
			.plantModel(() -> SplitPeaModel::new).scale(1F)
	);
	
	public static final PlantType STAR_FRUIT = new PVZPlants("star_fruit", new PlantFeatures()
			.cost(150).cd(PlantCardCD.FAST).rank(Ranks.GREEN).essence(Essences.APPEASE)
			.entityType(() -> EntityRegister.STAR_FRUIT.get())
			.summonCard(() -> ItemRegister.STAR_FRUIT_CARD.get())
			.enjoyCard(() -> ItemRegister.STAR_FRUIT_ENJOY_CARD.get())
			.plantModel(() -> StarFruitModel::new).scale(0.82F)
	);
	
	public static final PlantType PUMPKIN = new PVZPlants("pumpkin", new PlantFeatures().isOuterPlant()
			.cost(125).cd(PlantCardCD.SLOW).rank(Ranks.GREEN).essence(Essences.DEFENCE).level(1)
			.entityType(() -> EntityRegister.PUMPKIN.get())
			.summonCard(() -> ItemRegister.PUMPKIN_CARD.get())
			.enjoyCard(() -> ItemRegister.PUMPKIN_ENJOY_CARD.get())
			.plantModel(() -> PumpkinModel::new).scale(0.6F)
	);
	
	public static final PlantType MAGNET_SHROOM = new PVZPlants("magnet_shroom", new PlantFeatures().isShroomPlant()
			.cost(100).cd(PlantCardCD.LITTLE_FAST).rank(Ranks.PURPLE).essence(Essences.ASSIST)
			.entityType(() -> EntityRegister.MAGNET_SHROOM.get())
			.summonCard(() -> ItemRegister.MAGNET_SHROOM_CARD.get())
			.enjoyCard(() -> ItemRegister.MAGNET_SHROOM_ENJOY_CARD.get())
			.plantModel(() -> MagnetShroomModel::new).scale(1.2F)
			.upgradeTo(() -> PVZPlants.GOLD_MAGNET)
	);
	
	/*
	 * roof.
	 */
	public static final PlantType CABBAGE_PULT = new PVZPlants("cabbage_pult", new PlantFeatures()
			.cost(100).cd(PlantCardCD.VERY_FAST).rank(Ranks.GRAY).essence(Essences.ARMA)
			.entityType(() -> EntityRegister.CABBAGE_PULT.get())
			.summonCard(() -> ItemRegister.CABBAGE_PULT_CARD.get())
			.enjoyCard(() -> ItemRegister.CABBAGE_PULT_ENJOY_CARD.get())
			.plantModel(() -> CabbagePultModel::new).scale(0.9F)
	);
	
	public static final PlantType FLOWER_POT = new PVZPlants("flower_pot", new PlantFeatures().isBlockPlant()
			.cost(25).cd(PlantCardCD.HUGE_FAST).rank(Ranks.GRAY).essence(Essences.ASSIST).level(1)
			.entityType(() -> EntityRegister.FLOWER_POT.get())
			.summonCard(() -> ItemRegister.FLOWER_POT_CARD.get())
			.enjoyCard(() -> ItemRegister.FLOWER_POT_ENJOY_CARD.get())
			.plantModel(() -> FlowerPotModel::new).scale(0.85F)
			.placement(Placements.ANY)
	);
	
	public static final PlantType KERNEL_PULT = new PVZPlants("kernel_pult", new PlantFeatures()
			.cost(100).cd(PlantCardCD.FAST).rank(Ranks.GREEN).essence(Essences.ARMA)
			.entityType(() -> EntityRegister.KERNEL_PULT.get())
			.summonCard(() -> ItemRegister.KERNEL_PULT_CARD.get())
			.enjoyCard(() -> ItemRegister.KERNEL_PULT_ENJOY_CARD.get())
			.plantModel(() -> KernelPultModel::new).scale(0.9F)
			.upgradeTo(() -> PVZPlants.COB_CANNON)
	);
	
	public static final PlantType COFFEE_BEAN = new PVZPlants("coffee_bean", new PlantFeatures()
			.cost(75).cd(PlantCardCD.FAST).rank(Ranks.GREEN).essence(Essences.MAGIC)
			.entityType(() -> EntityRegister.COFFEE_BEAN.get())
			.summonCard(() -> ItemRegister.COFFEE_BEAN_CARD.get())
			.enjoyCard(() -> ItemRegister.COFFEE_BEAN_ENJOY_CARD.get())
			.plantModel(() -> CoffeeBeanModel::new).scale(0.6F)
	);
	
	public static final PlantType GARLIC = new PVZPlants("garlic", new PlantFeatures()
			.cost(50).cd(PlantCardCD.FAST).rank(Ranks.WHITE).essence(Essences.DEFENCE)
			.entityType(() -> EntityRegister.GARLIC.get())
			.summonCard(() -> ItemRegister.GARLIC_CARD.get())
			.enjoyCard(() -> ItemRegister.GARLIC_ENJOY_CARD.get())
			.plantModel(() -> GarlicModel::new).scale(0.9F)
	);
	
	public static final PlantType UMBRELLA_LEAF = new PVZPlants("umbrella_leaf", new PlantFeatures()
			.cost(100).cd(PlantCardCD.LITTLE_FAST).rank(Ranks.GREEN).essence(Essences.ENFORCE)
			.entityType(() -> EntityRegister.UMBRELLA_LEAF.get())
			.summonCard(() -> ItemRegister.UMBRELLA_LEAF_CARD.get())
			.enjoyCard(() -> ItemRegister.UMBRELLA_LEAF_ENJOY_CARD.get())
			.plantModel(() -> UmbrellaLeafModel::new).scale(0.8F)
	);
	
	public static final PlantType MARIGOLD = new PVZPlants("marigold", new PlantFeatures()
			.cost(50).cd(PlantCardCD.NORMAL).rank(Ranks.PURPLE).essence(Essences.MAGIC)
			.entityType(() -> EntityRegister.MARIGOLD.get())
			.summonCard(() -> ItemRegister.MARIGOLD_CARD.get())
			.enjoyCard(() -> ItemRegister.MARIGOLD_ENJOY_CARD.get())
			.plantModel(() -> MariGoldModel::new).scale(0.5F)
	);
	
	public static final PlantType MELON_PULT = new PVZPlants("melon_pult", new PlantFeatures()
			.cost(325).cd(PlantCardCD.FAST).rank(Ranks.PURPLE).essence(Essences.ARMA)
			.entityType(() -> EntityRegister.MELON_PULT.get())
			.summonCard(() -> ItemRegister.MELON_PULT_CARD.get())
			.enjoyCard(() -> ItemRegister.MELON_PULT_ENJOY_CARD.get())
			.plantModel(() -> MelonPultModel::new).scale(0.9F)
			.upgradeTo(() -> PVZPlants.WINTER_MELON)
	);
	
	/*
	 * upgrade
	 */
	public static final PlantType GATLING_PEA = new PVZPlants("gatling_pea", new PlantFeatures()
			.cost(450).cd(PlantCardCD.VERY_SLOW).rank(Ranks.PURPLE).essence(Essences.APPEASE)
			.entityType(() -> EntityRegister.GATLING_PEA.get())
			.summonCard(() -> ItemRegister.GATLING_PEA_CARD.get())
			.enjoyCard(() -> ItemRegister.GATLING_PEA_ENJOY_CARD.get())
			.plantModel(() -> GatlingPeaModel::new).scale(0.5F)
			.upgradeFrom(() -> PVZPlants.REPEATER)
	);
	
	public static final PlantType TWIN_SUNFLOWER = new PVZPlants("twin_sunflower", new PlantFeatures()
			.cost(200).cd(PlantCardCD.VERY_SLOW).rank(Ranks.BLUE).essence(Essences.LIGHT)
			.entityType(() -> EntityRegister.TWIN_SUNFLOWER.get())
			.summonCard(() -> ItemRegister.TWIN_SUNFLOWER_CARD.get())
			.enjoyCard(() -> ItemRegister.TWIN_SUNFLOWER_ENJOY_CARD.get())
			.plantModel(() -> TwinSunFlowerModel::new).scale(0.45F)
			.upgradeFrom(() -> PVZPlants.SUN_FLOWER)
	);
	
	public static final PlantType GLOOM_SHROOM = new PVZPlants("gloom_shroom", new PlantFeatures().isShroomPlant()
			.cost(250).cd(PlantCardCD.VERY_SLOW).rank(Ranks.PURPLE).essence(Essences.TOXIC)
			.entityType(() -> EntityRegister.GLOOM_SHROOM.get())
			.summonCard(() -> ItemRegister.GLOOM_SHROOM_CARD.get())
			.enjoyCard(() -> ItemRegister.GLOOM_SHROOM_ENJOY_CARD.get())
			.plantModel(() -> GloomShroomModel::new).scale(1F)
			.upgradeFrom(() -> PVZPlants.FUME_SHROOM)
	);
	
	public static final PlantType CAT_TAIL = new PVZPlants("cat_tail", new PlantFeatures().isWaterPlant()
			.cost(300).cd(PlantCardCD.VERY_SLOW).rank(Ranks.GOLD).essence(Essences.SPEAR)
			.entityType(() -> EntityRegister.CAT_TAIL.get())
			.summonCard(() -> ItemRegister.CAT_TAIL_CARD.get())
			.enjoyCard(() -> ItemRegister.CAT_TAIL_ENJOY_CARD.get())
			.plantModel(() -> CatTailModel::new).scale(0.18F)
			.upgradeFrom(() -> PVZPlants.LILY_PAD)
	);
	
	public static final PlantType WINTER_MELON = new PVZPlants("winter_melon", new PlantFeatures()
			.cost(550).cd(PlantCardCD.VERY_SLOW).rank(Ranks.GOLD).essence(Essences.ICE)
			.entityType(() -> EntityRegister.WINTER_MELON.get())
			.summonCard(() -> ItemRegister.WINTER_MELON_CARD.get())
			.enjoyCard(() -> ItemRegister.WINTER_MELON_ENJOY_CARD.get())
			.plantModel(() -> WinterMelonModel::new).scale(0.9F)
			.upgradeFrom(() -> PVZPlants.MELON_PULT)
	);
	
	public static final PlantType GOLD_MAGNET = new PVZPlants("gold_magnet", new PlantFeatures()
			.cost(225).cd(PlantCardCD.VERY_SLOW).rank(Ranks.GOLD).essence(Essences.ASSIST)
			.entityType(() -> EntityRegister.GOLD_MAGNET.get())
			.summonCard(() -> ItemRegister.GOLD_MAGNET_CARD.get())
			.enjoyCard(() -> ItemRegister.GOLD_MAGNET_ENJOY_CARD.get())
			.plantModel(() -> GoldMagnetModel::new).scale(1.2F)
			.upgradeFrom(() -> PVZPlants.MAGNET_SHROOM)
	);
	
	public static final PlantType SPIKE_ROCK = new PVZPlants("spike_rock", new PlantFeatures()
			.cost(275).cd(PlantCardCD.VERY_SLOW).rank(Ranks.PURPLE).essence(Essences.SPEAR)
			.entityType(() -> EntityRegister.SPIKE_ROCK.get())
			.summonCard(() -> ItemRegister.SPIKE_ROCK_CARD.get())
			.enjoyCard(() -> ItemRegister.SPIKE_ROCK_ENJOY_CARD.get())
			.plantModel(() -> SpikeRockModel::new).scale(0.5F)
			.placement(Placements.STABLE)
			.upgradeFrom(() -> PVZPlants.SPIKE_WEED)
	);
	
	public static final PlantType COB_CANNON = new PVZPlants("cob_cannon", new PlantFeatures()
			.cost(700).cd(PlantCardCD.HUGE_SLOW).rank(Ranks.GOLD).essence(Essences.EXPLOSION)
			.entityType(() -> EntityRegister.COB_CANNON.get())
			.summonCard(() -> ItemRegister.COB_CANNON_CARD.get())
			.enjoyCard(() -> ItemRegister.COB_CANNON_ENJOY_CARD.get())
			.plantModel(() -> CobCannonModel::new).scale(1.1F)
			.placement(Placements.ANY)
			.upgradeFrom(() -> PVZPlants.KERNEL_PULT)
	);
	
	/*
	 * other
	 */
	
	public static final PlantType IMITATER = new PVZPlants("imitater", new PlantFeatures()
			.rank(Ranks.GOLD).essence(Essences.MAGIC).level(1)
			.entityType(() -> EntityRegister.IMITATER.get())
			.summonCard(() -> ItemRegister.IMITATER_CARD.get())
			.enjoyCard(() -> ItemRegister.IMITATER_ENJOY_CARD.get())
			.plantModel(() -> ImitaterModel::new).scale(0.9F)
			.placement(Placements.NONE)
	);
	
	public static final PlantType EXPLODE_O_NUT = new PVZPlants("explode_o_nut", new PlantFeatures()
			.cost(700).cd(PlantCardCD.HUGE_SLOW).rank(Ranks.PURPLE).essence(Essences.EXPLOSION)
			.entityType(() -> EntityRegister.EXPLODE_O_NUT.get())
			.summonCard(() -> ItemRegister.EXPLODE_O_NUT_CARD.get())
			.enjoyCard(() -> ItemRegister.EXPLODE_O_NUT_ENJOY_CARD.get())
			.plantModel(() -> WallNutModel::new).scale(0.4F)
	);
	
	public static final PlantType GIANT_WALL_NUT = new PVZPlants("giant_wall_nut", new PlantFeatures()
			.cost(250).cd(PlantCardCD.SUPER_SLOW).rank(Ranks.PURPLE).essence(Essences.DEFENCE)
			.entityType(() -> EntityRegister.GIANT_WALL_NUT.get())
			.summonCard(() -> ItemRegister.GIANT_WALL_NUT_CARD.get())
			.enjoyCard(() -> ItemRegister.GIANT_WALL_NUT_ENJOY_CARD.get())
			.plantModel(() -> WallNutModel::new).scale(1F)
			.upgradeFrom(() -> PVZPlants.WALL_NUT)
	);
	
	public static void register() {
		registerPlants(LIST);
	}
	
	private PVZPlants(String name, PlantFeatures features) {
		super(name, features);
		LIST.add(this);
	}
	
    @Override
	public int getSortPriority() {
		return 100;
	}
    
	@Override
	public String getCategoryName() {
		return "pvz";
	}

	@Override
	public String getModID() {
		return PVZMod.MOD_ID;
	}

}
