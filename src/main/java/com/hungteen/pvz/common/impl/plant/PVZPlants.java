package com.hungteen.pvz.common.impl.plant;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.client.model.entity.plant.appease.*;
import com.hungteen.pvz.client.model.entity.plant.arma.CabbagePultModel;
import com.hungteen.pvz.client.model.entity.plant.arma.KernelPultModel;
import com.hungteen.pvz.client.model.entity.plant.arma.MelonPultModel;
import com.hungteen.pvz.client.model.entity.plant.assist.*;
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
import com.hungteen.pvz.client.model.entity.plant.toxic.*;
import com.hungteen.pvz.common.entity.plant.defence.PumpkinEntity.PumpkinInfo;
import com.hungteen.pvz.common.impl.*;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ItemRegister;

import java.util.ArrayList;
import java.util.List;

public final class PVZPlants extends PlantType {

	private static final List<IPlantType> LIST = new ArrayList<>();
	
	/*
	 * grass day.
	 */
	
	public static final IPlantType PEA_SHOOTER = new PVZPlants("pea_shooter", new PlantFeatures()
			.cost(100).maxLevel(20).requiredLevel(1)
			.cd(CoolDowns.HUGE_FAST).rank(Ranks.GRAY).essence(EssenceType.APPEASE)
			.entityType(() -> EntityRegister.PEA_SHOOTER.get())
			.summonCard(() -> ItemRegister.PEA_SHOOTER_CARD.get())
			.enjoyCard(() -> ItemRegister.PEA_SHOOTER_ENJOY_CARD.get())
			.plantModel(() -> PeaShooterModel::new).scale(1F)
	);
	
	public static final IPlantType SUN_FLOWER = new PVZPlants("sun_flower", new PlantFeatures()
			.cost(50).maxLevel(20).requiredLevel(1)
			.cd(CoolDowns.HUGE_FAST).rank(Ranks.GRAY).essence(EssenceType.LIGHT)
			.entityType(() -> EntityRegister.SUN_FLOWER.get())
			.summonCard(() -> ItemRegister.SUN_FLOWER_CARD.get())
			.enjoyCard(() -> ItemRegister.SUN_FLOWER_ENJOY_CARD.get())
			.plantModel(() -> SunFlowerModel::new).scale(0.5F)
			.upgradeTo(() -> PVZPlants.TWIN_SUNFLOWER)
	);
	
	public static final IPlantType CHERRY_BOMB = new PVZPlants("cherry_bomb", new PlantFeatures()
			.cost(150).maxLevel(20).requiredLevel(35)
			.cd(CoolDowns.HUGE_SLOW).rank(Ranks.BLUE).essence(EssenceType.EXPLOSION)
			.entityType(() -> EntityRegister.CHERRY_BOMB.get())
			.summonCard(() -> ItemRegister.CHERRY_BOMB_CARD.get())
			.enjoyCard(() -> ItemRegister.CHERRY_BOMB_ENJOY_CARD.get())
			.plantModel(() -> CherryBombModel::new).scale(0.5F)
	);
	
	public static final IPlantType WALL_NUT = new PVZPlants("wall_nut", new PlantFeatures()
			.cost(50).maxLevel(20).requiredLevel(5)
			.cd(CoolDowns.SLOW).rank(Ranks.WHITE).essence(EssenceType.DEFENCE)
			.entityType(() -> EntityRegister.WALL_NUT.get())
			.summonCard(() -> ItemRegister.WALL_NUT_CARD.get())
			.enjoyCard(() -> ItemRegister.WALL_NUT_ENJOY_CARD.get())
			.plantModel(() -> WallNutModel::new).scale(0.4F)
			.upgradeTo(() -> PVZPlants.GIANT_WALL_NUT)
	);
	
	public static final IPlantType POTATO_MINE = new PVZPlants("potato_mine", new PlantFeatures()
			.cost(25).maxLevel(20).requiredLevel(10)
			.cd(CoolDowns.LITTLE_SLOW).rank(Ranks.WHITE).essence(EssenceType.EXPLOSION)
			.entityType(() -> EntityRegister.POTATO_MINE.get())
			.summonCard(() -> ItemRegister.POTATO_MINE_CARD.get())
			.enjoyCard(() -> ItemRegister.POTATO_MINE_ENJOY_CARD.get())
			.plantModel(() -> PotatoMineModel::new).scale(0.6F)
			.placement(Placements.STABLE)
	);
	
	public static final IPlantType SNOW_PEA = new PVZPlants("snow_pea", new PlantFeatures()
			.cost(175).maxLevel(20).requiredLevel(20)
			.cd(CoolDowns.VERY_FAST).rank(Ranks.GREEN).essence(EssenceType.ICE)
			.entityType(() -> EntityRegister.SNOW_PEA.get())
			.summonCard(() -> ItemRegister.SNOW_PEA_CARD.get())
			.enjoyCard(() -> ItemRegister.SNOW_PEA_ENJOY_CARD.get())
			.plantModel(() -> SnowPeaModel::new).scale(1F)
	);
	
	public static final IPlantType CHOMPER = new PVZPlants("chomper", new PlantFeatures()
			.cost(150).maxLevel(20).requiredLevel(40)
			.cd(CoolDowns.NORMAL).rank(Ranks.BLUE).essence(EssenceType.ENFORCE)
			.entityType(() -> EntityRegister.CHOMPER.get())
			.summonCard(() -> ItemRegister.CHOMPER_CARD.get())
			.enjoyCard(() -> ItemRegister.CHOMPER_ENJOY_CARD.get())
			.plantModel(() -> ChomperModel::new).scale(0.85F)
	);
	
	public static final IPlantType REPEATER = new PVZPlants("repeater", new PlantFeatures()
			.cost(225).maxLevel(20).requiredLevel(30)
			.cd(CoolDowns.VERY_FAST).rank(Ranks.GREEN).essence(EssenceType.APPEASE)
			.entityType(() -> EntityRegister.REPEATER.get())
			.summonCard(() -> ItemRegister.REPEATER_CARD.get())
			.enjoyCard(() -> ItemRegister.REPEATER_ENJOY_CARD.get())
			.plantModel(() -> RepeaterModel::new).scale(1F)
			.upgradeTo(() -> PVZPlants.GATLING_PEA)
	);
	
	/*
	 * grass night.
	 */
	
	public static final IPlantType PUFF_SHROOM = new PVZPlants("puff_shroom", new PlantFeatures().isShroomPlant()
			.cost(1).maxLevel(20).requiredLevel(15)
			.cd(CoolDowns.VERY_FAST).rank(Ranks.WHITE).essence(EssenceType.TOXIC)
			.entityType(() -> EntityRegister.PUFF_SHROOM.get())
			.summonCard(() -> ItemRegister.PUFF_SHROOM_CARD.get())
			.enjoyCard(() -> ItemRegister.PUFF_SHROOM_ENJOY_CARD.get())
			.plantModel(() -> PuffShroomModel::new).scale(0.6F)
	);
	
	public static final IPlantType SUN_SHROOM = new PVZPlants("sun_shroom", new PlantFeatures().isShroomPlant()
			.cost(25).maxLevel(20).requiredLevel(15)
			.cd(CoolDowns.VERY_FAST).rank(Ranks.WHITE).essence(EssenceType.LIGHT)
			.entityType(() -> EntityRegister.SUN_SHROOM.get())
			.summonCard(() -> ItemRegister.SUN_SHROOM_CARD.get())
			.enjoyCard(() -> ItemRegister.SUN_SHROOM_ENJOY_CARD.get())
			.plantModel(() -> SunShroomModel::new).scale(0.4F)
	);
	
	public static final IPlantType FUME_SHROOM = new PVZPlants("fume_shroom", new PlantFeatures().isShroomPlant()
			.cost(100).maxLevel(20).requiredLevel(40)
			.cd(CoolDowns.LITTLE_FAST).rank(Ranks.GREEN).essence(EssenceType.TOXIC)
			.entityType(() -> EntityRegister.FUME_SHROOM.get())
			.summonCard(() -> ItemRegister.FUME_SHROOM_CARD.get())
			.enjoyCard(() -> ItemRegister.FUME_SHROOM_ENJOY_CARD.get())
			.plantModel(() -> FumeShroomModel::new).scale(0.9F)
			.upgradeTo(() -> PVZPlants.GLOOM_SHROOM)
	);
	
	public static final IPlantType GRAVE_BUSTER = new PVZPlants("grave_buster", new PlantFeatures()
			.cost(75).maxLevel(20).requiredLevel(10)
			.cd(CoolDowns.HUGE_FAST).rank(Ranks.WHITE).essence(EssenceType.ASSIST)
			.entityType(() -> EntityRegister.GRAVE_BUSTER.get())
			.summonCard(() -> ItemRegister.GRAVE_BUSTER_CARD.get())
			.enjoyCard(() -> ItemRegister.GRAVE_BUSTER_ENJOY_CARD.get())
			.plantModel(() -> GraveBusterModel::new).scale(1F)
	);
	
	public static final IPlantType HYPNO_SHROOM = new PVZPlants("hypno_shroom", new PlantFeatures().isShroomPlant()
			.cost(75).maxLevel(20).requiredLevel(45)
			.cd(CoolDowns.LITTLE_SLOW).rank(Ranks.BLUE).essence(EssenceType.MAGIC)
			.entityType(() -> EntityRegister.HYPNO_SHROOM.get())
			.summonCard(() -> ItemRegister.HYPNO_SHROOM_CARD.get())
			.enjoyCard(() -> ItemRegister.HYPNO_SHROOM_ENJOY_CARD.get())
			.plantModel(() -> HypnoShroomModel::new).scale(0.6F)
	);
	
	public static final IPlantType SCAREDY_SHROOM = new PVZPlants("scaredy_shroom", new PlantFeatures().isShroomPlant()
			.cost(25).maxLevel(20).requiredLevel(25)
			.cd(CoolDowns.VERY_FAST).rank(Ranks.GRAY).essence(EssenceType.TOXIC)
			.entityType(() -> EntityRegister.SCAREDY_SHROOM.get())
			.summonCard(() -> ItemRegister.SCAREDY_SHROOM_CARD.get())
			.enjoyCard(() -> ItemRegister.SCAREDY_SHROOM_ENJOY_CARD.get())
			.plantModel(() -> ScaredyShroomModel::new).scale(0.7F)
	);
	
	public static final IPlantType ICE_SHROOM = new PVZPlants("ice_shroom", new PlantFeatures().isShroomPlant()
			.cost(75).maxLevel(20).requiredLevel(35)
			.cd(CoolDowns.SLOW).rank(Ranks.BLUE).essence(EssenceType.ICE)
			.entityType(() -> EntityRegister.ICE_SHROOM.get())
			.summonCard(() -> ItemRegister.ICE_SHROOM_CARD.get())
			.enjoyCard(() -> ItemRegister.ICE_SHROOM_ENJOY_CARD.get())
			.plantModel(() -> IceShroomModel::new).scale(0.82F)
	);
	
	public static final IPlantType DOOM_SHROOM = new PVZPlants("doom_shroom", new PlantFeatures().isShroomPlant()
			.cost(225).maxLevel(20).requiredLevel(65)
			.cd(CoolDowns.SUPER_SLOW).rank(Ranks.GOLD).essence(EssenceType.EXPLOSION)
			.entityType(() -> EntityRegister.DOOM_SHROOM.get())
			.summonCard(() -> ItemRegister.DOOM_SHROOM_CARD.get())
			.enjoyCard(() -> ItemRegister.DOOM_SHROOM_ENJOY_CARD.get())
			.plantModel(() -> DoomShroomModel::new).scale(1F)
	);
	
	/*
	 * pool day.
	 */
	public static final IPlantType LILY_PAD = new PVZPlants("lily_pad", new PlantFeatures().isWaterPlant()
			.cost(25).maxLevel(20).requiredLevel(10)
			.cd(CoolDowns.SUPER_FAST).rank(Ranks.GRAY).essence(EssenceType.ASSIST)
			.plantBlock(() -> BlockRegister.LILY_PAD.get())
			.entityType(() -> EntityRegister.LILY_PAD.get())
			.summonCard(() -> ItemRegister.LILY_PAD_CARD.get())
			.enjoyCard(() -> ItemRegister.LILY_PAD_ENJOY_CARD.get())
			.plantModel(() -> LilyPadModel::new).scale(1F)
			.upgradeTo(() -> PVZPlants.CAT_TAIL)
	);
	
	public static final IPlantType SQUASH = new PVZPlants("squash", new PlantFeatures()
			.cost(50).maxLevel(20).requiredLevel(35)
			.cd(CoolDowns.LITTLE_SLOW).rank(Ranks.GREEN).essence(EssenceType.ENFORCE)
			.entityType(() -> EntityRegister.SQUASH.get())
			.summonCard(() -> ItemRegister.SQUASH_CARD.get())
			.enjoyCard(() -> ItemRegister.SQUASH_ENJOY_CARD.get())
			.plantModel(() -> SquashModel::new).scale(0.5F)
	);
	
	public static final IPlantType THREE_PEATER = new PVZPlants("three_peater", new PlantFeatures()
			.cost(325).maxLevel(20).requiredLevel(40)
			.cd(CoolDowns.LITTLE_FAST).rank(Ranks.GREEN).essence(EssenceType.APPEASE)
			.entityType(() -> EntityRegister.THREE_PEATER.get())
			.summonCard(() -> ItemRegister.THREE_PEATER_CARD.get())
			.enjoyCard(() -> ItemRegister.THREE_PEATER_ENJOY_CARD.get())
			.plantModel(() -> ThreePeaterModel::new).scale(0.98F)
	);
	
	public static final IPlantType TANGLE_KELP = new PVZPlants("tangle_kelp", new PlantFeatures().isWaterPlant()
			.cost(25).maxLevel(20).requiredLevel(15)
			.cd(CoolDowns.LITTLE_SLOW).rank(Ranks.WHITE).essence(EssenceType.ENFORCE)
			.entityType(() -> EntityRegister.TANGLE_KELP.get())
			.summonCard(() -> ItemRegister.TANGLE_KELP_CARD.get())
			.enjoyCard(() -> ItemRegister.TANGLE_KELP_ENJOY_CARD.get())
			.plantModel(() -> TangleKelpModel::new).scale(0.6F)
	);
	
	public static final IPlantType JALAPENO = new PVZPlants("jalapeno", new PlantFeatures()
			.cost(175).maxLevel(20).requiredLevel(45)
			.cd(CoolDowns.HUGE_SLOW).rank(Ranks.BLUE).essence(EssenceType.FLAME)
			.entityType(() -> EntityRegister.JALAPENO.get())
			.summonCard(() -> ItemRegister.JALAPENO_CARD.get())
			.enjoyCard(() -> ItemRegister.JALAPENO_ENJOY_CARD.get())
			.plantModel(() -> JalapenoModel::new).scale(0.5F)
	);
	
	public static final IPlantType SPIKE_WEED = new PVZPlants("spike_weed", new PlantFeatures()
			.cost(100).maxLevel(20).requiredLevel(20)
			.cd(CoolDowns.LITTLE_FAST).rank(Ranks.WHITE).essence(EssenceType.SPEAR)
			.entityType(() -> EntityRegister.SPIKE_WEED.get())
			.summonCard(() -> ItemRegister.SPIKE_WEED_CARD.get())
			.enjoyCard(() -> ItemRegister.SPIKE_WEED_ENJOY_CARD.get())
			.plantModel(() -> SpikeWeedModel::new).scale(0.5F)
			.placement(Placements.STABLE)
			.upgradeTo(() -> PVZPlants.SPIKE_ROCK)
	);
	
	public static final IPlantType TORCH_WOOD = new PVZPlants("torch_wood", new PlantFeatures()
			.cost(200).maxLevel(20).requiredLevel(80)
			.cd(CoolDowns.NORMAL).rank(Ranks.PURPLE).essence(EssenceType.FLAME)
			.entityType(() -> EntityRegister.TORCH_WOOD.get())
			.summonCard(() -> ItemRegister.TORCH_WOOD_CARD.get())
			.enjoyCard(() -> ItemRegister.TORCH_WOOD_ENJOY_CARD.get())
			.plantModel(() -> TorchWoodModel::new).scale(0.5F)
	);
	
	public static final IPlantType TALL_NUT = new PVZPlants("tall_nut", new PlantFeatures()
			.cost(125).maxLevel(20).requiredLevel(30)
			.cd(CoolDowns.VERY_SLOW).rank(Ranks.BLUE).essence(EssenceType.DEFENCE)
			.entityType(() -> EntityRegister.TALL_NUT.get())
			.summonCard(() -> ItemRegister.TALL_NUT_CARD.get())
			.enjoyCard(() -> ItemRegister.TALL_NUT_ENJOY_CARD.get())
			.plantModel(() -> TallNutModel::new).scale(0.4F)
	);
	
	/*
	 * pool night.
	 */
	public static final IPlantType SEA_SHROOM = new PVZPlants("sea_shroom", new PlantFeatures().isShroomPlant().isWaterPlant()
			.cost(1).maxLevel(20).requiredLevel(5)
			.cd(CoolDowns.FAST).rank(Ranks.WHITE).essence(EssenceType.TOXIC)
			.entityType(() -> EntityRegister.SEA_SHROOM.get())
			.summonCard(() -> ItemRegister.SEA_SHROOM_CARD.get())
			.enjoyCard(() -> ItemRegister.SEA_SHROOM_ENJOY_CARD.get())
			.plantModel(() -> SeaShroomModel::new).scale(0.6F)
	);
	
	public static final IPlantType PLANTERN = new PVZPlants("plantern", new PlantFeatures()
			.cost(75).maxLevel(20).requiredLevel(5)
			.cd(CoolDowns.FAST).rank(Ranks.WHITE).essence(EssenceType.LIGHT)
			.entityType(() -> EntityRegister.PLANTERN.get())
			.summonCard(() -> ItemRegister.PLANTERN_CARD.get())
			.enjoyCard(() -> ItemRegister.PLANTERN_ENJOY_CARD.get())
			.plantModel(() -> PlanternModel::new).scale(0.7F)
	);
	
	public static final IPlantType CACTUS = new PVZPlants("cactus", new PlantFeatures()
			.cost(125).maxLevel(20).requiredLevel(15)
			.cd(CoolDowns.FAST).rank(Ranks.GREEN).essence(EssenceType.SPEAR)
			.entityType(() -> EntityRegister.CACTUS.get())
			.summonCard(() -> ItemRegister.CACTUS_CARD.get())
			.enjoyCard(() -> ItemRegister.CACTUS_ENJOY_CARD.get())
			.plantModel(() -> CactusModel::new).scale(1F)
			.placement(Placements.SAND)
	);
	
	public static final IPlantType BLOVER = new PVZPlants("blover", new PlantFeatures()
			.cost(50).maxLevel(20).requiredLevel(20).cd(CoolDowns.FAST).rank(Ranks.GREEN).essence(EssenceType.ASSIST)
			.entityType(() -> EntityRegister.BLOVER.get())
			.summonCard(() -> ItemRegister.BLOVER_CARD.get())
			.enjoyCard(() -> ItemRegister.BLOVER_ENJOY_CARD.get())
			.plantModel(() -> BloverModel::new).scale(1.2F)
	);
	
	public static final IPlantType SPLIT_PEA = new PVZPlants("split_pea", new PlantFeatures()
			.cost(125).maxLevel(20).requiredLevel(10)
			.cd(CoolDowns.HUGE_FAST).rank(Ranks.WHITE).essence(EssenceType.APPEASE)
			.entityType(() -> EntityRegister.SPLIT_PEA.get())
			.summonCard(() -> ItemRegister.SPLIT_PEA_CARD.get())
			.enjoyCard(() -> ItemRegister.SPLIT_PEA_ENJOY_CARD.get())
			.plantModel(() -> SplitPeaModel::new).scale(1F)
	);
	
	public static final IPlantType STAR_FRUIT = new PVZPlants("star_fruit", new PlantFeatures()
			.cost(150).maxLevel(20).requiredLevel(25)
			.cd(CoolDowns.FAST).rank(Ranks.GREEN).essence(EssenceType.APPEASE)
			.entityType(() -> EntityRegister.STAR_FRUIT.get())
			.summonCard(() -> ItemRegister.STAR_FRUIT_CARD.get())
			.enjoyCard(() -> ItemRegister.STAR_FRUIT_ENJOY_CARD.get())
			.plantModel(() -> StarFruitModel::new).scale(0.82F)
	);
	
	public static final IPlantType PUMPKIN = new PVZPlants("pumpkin", new PlantFeatures()
			.cost(125).maxLevel(20).requiredLevel(50)
			.cd(CoolDowns.SLOW).rank(Ranks.GREEN).essence(EssenceType.DEFENCE).maxLevel(1)
			.outerPlant(() -> new PumpkinInfo())
			.entityType(() -> EntityRegister.PUMPKIN.get())
			.summonCard(() -> ItemRegister.PUMPKIN_CARD.get())
			.enjoyCard(() -> ItemRegister.PUMPKIN_ENJOY_CARD.get())
			.plantModel(() -> PumpkinModel::new).scale(0.6F)
	);
	
	public static final IPlantType MAGNET_SHROOM = new PVZPlants("magnet_shroom", new PlantFeatures().isShroomPlant()
			.cost(100).maxLevel(20).requiredLevel(45)
			.cd(CoolDowns.LITTLE_FAST).rank(Ranks.BLUE).essence(EssenceType.ASSIST)
			.entityType(() -> EntityRegister.MAGNET_SHROOM.get())
			.summonCard(() -> ItemRegister.MAGNET_SHROOM_CARD.get())
			.enjoyCard(() -> ItemRegister.MAGNET_SHROOM_ENJOY_CARD.get())
			.plantModel(() -> MagnetShroomModel::new).scale(1.2F)
			.upgradeTo(() -> PVZPlants.GOLD_MAGNET)
	);
	
	/*
	 * roof.
	 */
	public static final IPlantType CABBAGE_PULT = new PVZPlants("cabbage_pult", new PlantFeatures()
			.cost(100).maxLevel(20).requiredLevel(20)
			.cd(CoolDowns.VERY_FAST).rank(Ranks.GRAY).essence(EssenceType.ARMA)
			.entityType(() -> EntityRegister.CABBAGE_PULT.get())
			.summonCard(() -> ItemRegister.CABBAGE_PULT_CARD.get())
			.enjoyCard(() -> ItemRegister.CABBAGE_PULT_ENJOY_CARD.get())
			.plantModel(() -> CabbagePultModel::new).scale(0.9F)
	);
	
	public static final IPlantType FLOWER_POT = new PVZPlants("flower_pot", new PlantFeatures()
			.cost(25).maxLevel(20).requiredLevel(40)
			.cd(CoolDowns.HUGE_FAST).rank(Ranks.GRAY).essence(EssenceType.ASSIST).maxLevel(1)
			.plantBlock(() -> BlockRegister.FLOWER_POT.get())
			.entityType(() -> EntityRegister.FLOWER_POT.get())
			.summonCard(() -> ItemRegister.FLOWER_POT_CARD.get())
			.enjoyCard(() -> ItemRegister.FLOWER_POT_ENJOY_CARD.get())
			.plantModel(() -> FlowerPotModel::new).scale(0.85F)
			.placement(Placements.ANY)
	);
	
	public static final IPlantType KERNEL_PULT = new PVZPlants("kernel_pult", new PlantFeatures()
			.cost(125).maxLevel(20).requiredLevel(35)
			.cd(CoolDowns.FAST).rank(Ranks.GREEN).essence(EssenceType.ARMA)
			.entityType(() -> EntityRegister.KERNEL_PULT.get())
			.summonCard(() -> ItemRegister.KERNEL_PULT_CARD.get())
			.enjoyCard(() -> ItemRegister.KERNEL_PULT_ENJOY_CARD.get())
			.plantModel(() -> KernelPultModel::new).scale(0.9F)
			.upgradeTo(() -> PVZPlants.COB_CANNON)
	);
	
	public static final IPlantType COFFEE_BEAN = new PVZPlants("coffee_bean", new PlantFeatures()
			.cost(75).maxLevel(20).requiredLevel(50)
			.cd(CoolDowns.FAST).rank(Ranks.GREEN).essence(EssenceType.MAGIC)
			.entityType(() -> EntityRegister.COFFEE_BEAN.get())
			.summonCard(() -> ItemRegister.COFFEE_BEAN_CARD.get())
			.enjoyCard(() -> ItemRegister.COFFEE_BEAN_ENJOY_CARD.get())
			.plantModel(() -> CoffeeBeanModel::new).scale(0.6F)
	);
	
	public static final IPlantType GARLIC = new PVZPlants("garlic", new PlantFeatures()
			.cost(50).maxLevel(20).requiredLevel(35)
			.cd(CoolDowns.FAST).rank(Ranks.WHITE).essence(EssenceType.DEFENCE)
			.entityType(() -> EntityRegister.GARLIC.get())
			.summonCard(() -> ItemRegister.GARLIC_CARD.get())
			.enjoyCard(() -> ItemRegister.GARLIC_ENJOY_CARD.get())
			.plantModel(() -> GarlicModel::new).scale(0.9F)
	);
	
	public static final IPlantType UMBRELLA_LEAF = new PVZPlants("umbrella_leaf", new PlantFeatures()
			.cost(100).maxLevel(20).requiredLevel(25)
			.cd(CoolDowns.LITTLE_FAST).rank(Ranks.GREEN).essence(EssenceType.ENFORCE)
			.entityType(() -> EntityRegister.UMBRELLA_LEAF.get())
			.summonCard(() -> ItemRegister.UMBRELLA_LEAF_CARD.get())
			.enjoyCard(() -> ItemRegister.UMBRELLA_LEAF_ENJOY_CARD.get())
			.plantModel(() -> UmbrellaLeafModel::new).scale(0.8F)
	);
	
	public static final IPlantType MARIGOLD = new PVZPlants("marigold", new PlantFeatures()
			.cost(50).maxLevel(20).requiredLevel(65)
			.cd(CoolDowns.NORMAL).rank(Ranks.PURPLE).essence(EssenceType.MAGIC)
			.entityType(() -> EntityRegister.MARIGOLD.get())
			.summonCard(() -> ItemRegister.MARIGOLD_CARD.get())
			.enjoyCard(() -> ItemRegister.MARIGOLD_ENJOY_CARD.get())
			.plantModel(() -> MariGoldModel::new).scale(0.5F)
	);
	
	public static final IPlantType MELON_PULT = new PVZPlants("melon_pult", new PlantFeatures()
			.cost(325).maxLevel(20).requiredLevel(50)
			.cd(CoolDowns.FAST).rank(Ranks.PURPLE).essence(EssenceType.ARMA)
			.entityType(() -> EntityRegister.MELON_PULT.get())
			.summonCard(() -> ItemRegister.MELON_PULT_CARD.get())
			.enjoyCard(() -> ItemRegister.MELON_PULT_ENJOY_CARD.get())
			.plantModel(() -> MelonPultModel::new).scale(0.9F)
			.upgradeTo(() -> PVZPlants.WINTER_MELON)
	);
	
	/*
	 * upgrade
	 */
	public static final IPlantType GATLING_PEA = new PVZPlants("gatling_pea", new PlantFeatures()
			.cost(450).maxLevel(20).requiredLevel(60)
			.cd(CoolDowns.VERY_SLOW).rank(Ranks.PURPLE).essence(EssenceType.APPEASE)
			.entityType(() -> EntityRegister.GATLING_PEA.get())
			.summonCard(() -> ItemRegister.GATLING_PEA_CARD.get())
			.enjoyCard(() -> ItemRegister.GATLING_PEA_ENJOY_CARD.get())
			.plantModel(() -> GatlingPeaModel::new).scale(0.5F)
			.upgradeFrom(() -> PVZPlants.REPEATER)
	);
	
	public static final IPlantType TWIN_SUNFLOWER = new PVZPlants("twin_sunflower", new PlantFeatures()
			.cost(200).maxLevel(20).requiredLevel(25)
			.cd(CoolDowns.VERY_SLOW).rank(Ranks.BLUE).essence(EssenceType.LIGHT)
			.entityType(() -> EntityRegister.TWIN_SUNFLOWER.get())
			.summonCard(() -> ItemRegister.TWIN_SUNFLOWER_CARD.get())
			.enjoyCard(() -> ItemRegister.TWIN_SUNFLOWER_ENJOY_CARD.get())
			.plantModel(() -> TwinSunFlowerModel::new).scale(0.45F)
			.upgradeFrom(() -> PVZPlants.SUN_FLOWER)
	);
	
	public static final IPlantType GLOOM_SHROOM = new PVZPlants("gloom_shroom", new PlantFeatures().isShroomPlant()
			.cost(250).maxLevel(20).requiredLevel(55)
			.cd(CoolDowns.VERY_SLOW).rank(Ranks.PURPLE).essence(EssenceType.TOXIC)
			.entityType(() -> EntityRegister.GLOOM_SHROOM.get())
			.summonCard(() -> ItemRegister.GLOOM_SHROOM_CARD.get())
			.enjoyCard(() -> ItemRegister.GLOOM_SHROOM_ENJOY_CARD.get())
			.plantModel(() -> GloomShroomModel::new).scale(1F)
			.upgradeFrom(() -> PVZPlants.FUME_SHROOM)
	);
	
	public static final IPlantType CAT_TAIL = new PVZPlants("cat_tail", new PlantFeatures().isWaterPlant()
			.cost(350).maxLevel(20).requiredLevel(70)
			.cd(CoolDowns.VERY_SLOW).rank(Ranks.GOLD).essence(EssenceType.SPEAR)
			.placement(Placements.LILY_PAD)
			.entityType(() -> EntityRegister.CAT_TAIL.get())
			.summonCard(() -> ItemRegister.CAT_TAIL_CARD.get())
			.enjoyCard(() -> ItemRegister.CAT_TAIL_ENJOY_CARD.get())
			.plantModel(() -> CatTailModel::new).scale(0.18F)
	);
	
	public static final IPlantType WINTER_MELON = new PVZPlants("winter_melon", new PlantFeatures()
			.cost(550).maxLevel(20).requiredLevel(65)
			.cd(CoolDowns.VERY_SLOW).rank(Ranks.GOLD).essence(EssenceType.ICE)
			.entityType(() -> EntityRegister.WINTER_MELON.get())
			.summonCard(() -> ItemRegister.WINTER_MELON_CARD.get())
			.enjoyCard(() -> ItemRegister.WINTER_MELON_ENJOY_CARD.get())
			.plantModel(() -> WinterMelonModel::new).scale(0.9F)
			.upgradeFrom(() -> PVZPlants.MELON_PULT)
	);
	
	public static final IPlantType GOLD_MAGNET = new PVZPlants("gold_magnet", new PlantFeatures()
			.cost(200).maxLevel(20).requiredLevel(55)
			.cd(CoolDowns.VERY_SLOW).rank(Ranks.WHITE).essence(EssenceType.ASSIST)
			.entityType(() -> EntityRegister.GOLD_MAGNET.get())
			.summonCard(() -> ItemRegister.GOLD_MAGNET_CARD.get())
			.enjoyCard(() -> ItemRegister.GOLD_MAGNET_ENJOY_CARD.get())
			.plantModel(() -> GoldMagnetModel::new).scale(1.2F)
			.upgradeFrom(() -> PVZPlants.MAGNET_SHROOM)
	);
	
	public static final IPlantType SPIKE_ROCK = new PVZPlants("spike_rock", new PlantFeatures()
			.cost(275).maxLevel(20).requiredLevel(55)
			.cd(CoolDowns.VERY_SLOW).rank(Ranks.PURPLE).essence(EssenceType.SPEAR)
			.entityType(() -> EntityRegister.SPIKE_ROCK.get())
			.summonCard(() -> ItemRegister.SPIKE_ROCK_CARD.get())
			.enjoyCard(() -> ItemRegister.SPIKE_ROCK_ENJOY_CARD.get())
			.plantModel(() -> SpikeRockModel::new).scale(0.5F)
			.placement(Placements.STABLE)
			.upgradeFrom(() -> PVZPlants.SPIKE_WEED)
	);
	
	public static final IPlantType COB_CANNON = new PVZPlants("cob_cannon", new PlantFeatures()
			.cost(700).maxLevel(20).requiredLevel(70)
			.cd(CoolDowns.HUGE_SLOW).rank(Ranks.GOLD).essence(EssenceType.EXPLOSION)
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
	
	public static final IPlantType IMITATER = new PVZPlants("imitater", new PlantFeatures()
			.maxLevel(1).requiredLevel(60)
			.rank(Ranks.GOLD).essence(EssenceType.MAGIC)
			.entityType(() -> EntityRegister.IMITATER.get())
			.summonCard(() -> ItemRegister.IMITATER_CARD.get())
			.enjoyCard(() -> ItemRegister.IMITATER_ENJOY_CARD.get())
			.plantModel(() -> ImitaterModel::new).scale(0.9F)
			.placement(Placements.NONE)
	);
	
	public static final IPlantType EXPLODE_O_NUT = new PVZPlants("explode_o_nut", new PlantFeatures()
			.cost(200).maxLevel(20).requiredLevel(65)
			.cd(CoolDowns.HUGE_SLOW).rank(Ranks.PURPLE).essence(EssenceType.EXPLOSION)
			.entityType(() -> EntityRegister.EXPLODE_O_NUT.get())
			.summonCard(() -> ItemRegister.EXPLODE_O_NUT_CARD.get())
			.enjoyCard(() -> ItemRegister.EXPLODE_O_NUT_ENJOY_CARD.get())
			.plantModel(() -> WallNutModel::new).scale(0.4F)
	);
	
	public static final IPlantType GIANT_WALL_NUT = new PVZPlants("giant_wall_nut", new PlantFeatures()
			.cost(250).maxLevel(20).requiredLevel(75)
			.cd(CoolDowns.SUPER_SLOW).rank(Ranks.PURPLE).essence(EssenceType.DEFENCE)
			.entityType(() -> EntityRegister.GIANT_WALL_NUT.get())
			.summonCard(() -> ItemRegister.GIANT_WALL_NUT_CARD.get())
			.enjoyCard(() -> ItemRegister.GIANT_WALL_NUT_ENJOY_CARD.get())
			.plantModel(() -> WallNutModel::new).scale(1F)
			.upgradeFrom(() -> PVZPlants.WALL_NUT)
	);
	
	public static void register() {
		PVZAPI.get().registerPlantTypes(LIST);
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
