package com.hungteen.pvz.client.model.entity.plant;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import com.hungteen.pvz.api.interfaces.IPVZPlant;
import com.hungteen.pvz.client.model.entity.plant.appease.AngelStarFruitModel;
import com.hungteen.pvz.client.model.entity.plant.appease.PeaShooterModel;
import com.hungteen.pvz.client.model.entity.plant.appease.RepeaterModel;
import com.hungteen.pvz.client.model.entity.plant.appease.SplitPeaModel;
import com.hungteen.pvz.client.model.entity.plant.appease.StarFruitModel;
import com.hungteen.pvz.client.model.entity.plant.appease.ThreePeaterModel;
import com.hungteen.pvz.client.model.entity.plant.arma.CabbagePultModel;
import com.hungteen.pvz.client.model.entity.plant.arma.KernelPultModel;
import com.hungteen.pvz.client.model.entity.plant.arma.MelonPultModel;
import com.hungteen.pvz.client.model.entity.plant.assist.BloverModel;
import com.hungteen.pvz.client.model.entity.plant.assist.GoldMagnetModel;
import com.hungteen.pvz.client.model.entity.plant.assist.GraveBusterModel;
import com.hungteen.pvz.client.model.entity.plant.assist.LilyPadModel;
import com.hungteen.pvz.client.model.entity.plant.assist.MagnetShroomModel;
import com.hungteen.pvz.client.model.entity.plant.defence.GarlicModel;
import com.hungteen.pvz.client.model.entity.plant.defence.PumpkinModel;
import com.hungteen.pvz.client.model.entity.plant.defence.TallNutModel;
import com.hungteen.pvz.client.model.entity.plant.defence.WallNutModel;
import com.hungteen.pvz.client.model.entity.plant.defence.WaterGuardModel;
import com.hungteen.pvz.client.model.entity.plant.enforce.BonkChoyModel;
import com.hungteen.pvz.client.model.entity.plant.enforce.ChomperModel;
import com.hungteen.pvz.client.model.entity.plant.enforce.SquashModel;
import com.hungteen.pvz.client.model.entity.plant.enforce.TangleKelpModel;
import com.hungteen.pvz.client.model.entity.plant.enforce.UmbrellaLeafModel;
import com.hungteen.pvz.client.model.entity.plant.explosion.BambooLordModel;
import com.hungteen.pvz.client.model.entity.plant.explosion.CherryBombModel;
import com.hungteen.pvz.client.model.entity.plant.explosion.CobCannonModel;
import com.hungteen.pvz.client.model.entity.plant.explosion.DoomShroomModel;
import com.hungteen.pvz.client.model.entity.plant.explosion.PotatoMineModel;
import com.hungteen.pvz.client.model.entity.plant.flame.JalapenoModel;
import com.hungteen.pvz.client.model.entity.plant.flame.TorchWoodModel;
import com.hungteen.pvz.client.model.entity.plant.ice.IceShroomModel;
import com.hungteen.pvz.client.model.entity.plant.ice.IcebergLettuceModel;
import com.hungteen.pvz.client.model.entity.plant.ice.SnowPeaModel;
import com.hungteen.pvz.client.model.entity.plant.ice.WinterMelonModel;
import com.hungteen.pvz.client.model.entity.plant.light.GoldLeafModel;
import com.hungteen.pvz.client.model.entity.plant.light.PlanternModel;
import com.hungteen.pvz.client.model.entity.plant.light.SunFlowerModel;
import com.hungteen.pvz.client.model.entity.plant.light.SunShroomModel;
import com.hungteen.pvz.client.model.entity.plant.magic.CoffeeBeanModel;
import com.hungteen.pvz.client.model.entity.plant.magic.HypnoShroomModel;
import com.hungteen.pvz.client.model.entity.plant.magic.ImitaterModel;
import com.hungteen.pvz.client.model.entity.plant.magic.MariGoldModel;
import com.hungteen.pvz.client.model.entity.plant.magic.StrangeCatModel;
import com.hungteen.pvz.client.model.entity.plant.spear.CactusModel;
import com.hungteen.pvz.client.model.entity.plant.spear.CatTailModel;
import com.hungteen.pvz.client.model.entity.plant.spear.SpikeRockModel;
import com.hungteen.pvz.client.model.entity.plant.spear.SpikeWeedModel;
import com.hungteen.pvz.client.model.entity.plant.toxic.FumeShroomModel;
import com.hungteen.pvz.client.model.entity.plant.toxic.GloomShroomModel;
import com.hungteen.pvz.client.model.entity.plant.toxic.PuffShroomModel;
import com.hungteen.pvz.client.model.entity.plant.toxic.ScaredyShroomModel;
import com.hungteen.pvz.client.model.entity.plant.toxic.SeaShroomModel;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PlantModelHandler {

	public static final Map<Plants, IPlantModel<? extends IPVZPlant>> PLANT_PART1_MODEL = new EnumMap<>(Plants.class);
	public static final Map<Plants, IPlantModel<? extends IPVZPlant>> PLANT_PART2_MODEL = new EnumMap<>(Plants.class);
	
	static {
		//grass day
		putModel(Plants.PEA_SHOOTER, PeaShooterModel::new);
		putModel(Plants.SUN_FLOWER, SunFlowerModel::new);
		putModel(Plants.CHERRY_BOMB, CherryBombModel::new);
		putModel(Plants.WALL_NUT, WallNutModel::new);
		putModel(Plants.POTATO_MINE, PotatoMineModel::new);
		putModel(Plants.SNOW_PEA, SnowPeaModel::new);
		putModel(Plants.CHOMPER, ChomperModel::new);
		putModel(Plants.REPEATER, RepeaterModel::new);
		//grass night
		putModel(Plants.PUFF_SHROOM, PuffShroomModel::new);
		putModel(Plants.SUN_SHROOM, SunShroomModel::new);
		putModel(Plants.FUME_SHROOM, FumeShroomModel::new);
		putModel(Plants.GRAVE_BUSTER, GraveBusterModel::new);
		putModel(Plants.HYPNO_SHROOM, HypnoShroomModel::new);
		putModel(Plants.SCAREDY_SHROOM, ScaredyShroomModel::new);
		putModel(Plants.ICE_SHROOM, IceShroomModel::new);
		putModel(Plants.DOOM_SHROOM, DoomShroomModel::new);
		//pool day
		putModel(Plants.LILY_PAD, LilyPadModel::new);
		putModel(Plants.SQUASH, SquashModel::new);
		putModel(Plants.THREE_PEATER, ThreePeaterModel::new);
		putModel(Plants.TANGLE_KELP, TangleKelpModel::new);
		putModel(Plants.JALAPENO, JalapenoModel::new);
		putModel(Plants.SPIKE_WEED, SpikeWeedModel::new);
		putModel(Plants.TORCH_WOOD, TorchWoodModel::new);
		putModel(Plants.TALL_NUT, TallNutModel::new);
		//pool night
		putModel(Plants.SEA_SHROOM, SeaShroomModel::new);
		putModel(Plants.PLANTERN, PlanternModel::new);
		putModel(Plants.CACTUS, CactusModel::new);
		putModel(Plants.BLOVER, BloverModel::new);
		putModel(Plants.SPLIT_PEA, SplitPeaModel::new);
		putModel(Plants.STAR_FRUIT, StarFruitModel::new);
		putModel(Plants.PUMPKIN, PumpkinModel::new);
		putModel(Plants.MAGNET_SHROOM, MagnetShroomModel::new);
		//roof(no flower pot)
		putModel(Plants.CABBAGE_PULT, CabbagePultModel::new);
//		putModel(Plants.FLOWER_POT, SeaShroomModel::new);
		putModel(Plants.KERNEL_PULT, KernelPultModel::new);
		putModel(Plants.COFFEE_BEAN, CoffeeBeanModel::new);
		putModel(Plants.GARLIC, GarlicModel::new);
		putModel(Plants.UMBRELLA_LEAF, UmbrellaLeafModel::new);
		putModel(Plants.MARIGOLD, MariGoldModel::new);
		putModel(Plants.MELON_PULT, MelonPultModel::new);
		//upgrade
		putModel(Plants.GATLING_PEA, PuffShroomModel::new);
		putModel(Plants.TWIN_SUNFLOWER, SunShroomModel::new);
		putModel(Plants.GLOOM_SHROOM, GloomShroomModel::new);
		putModel(Plants.CAT_TAIL, CatTailModel::new);
		putModel(Plants.WINTER_MELON, WinterMelonModel::new);
		putModel(Plants.GOLD_MAGNET, GoldMagnetModel::new);
		putModel(Plants.SPIKE_ROCK, SpikeRockModel::new);
		putModel(Plants.COB_CANNON, CobCannonModel::new);
		putModel(Plants.IMITATER, ImitaterModel::new);
		//other
		putModel(Plants.WATER_GUARD, WaterGuardModel::new);
		putModel(Plants.STRANGE_CAT, StrangeCatModel::new);
		putModel(Plants.ANGEL_STAR_FRUIT, AngelStarFruitModel::new);
		putModel(Plants.GOLD_LEAF, GoldLeafModel::new);
		putModel(Plants.EXPLODE_O_NUT, WallNutModel::new);
		putModel(Plants.GIANT_WALL_NUT, WallNutModel::new);
		putModel(Plants.BUTTER_PULT, KernelPultModel::new);
		putModel(Plants.BAMBOO_LORD, BambooLordModel::new);
		putModel(Plants.ICEBERG_LETTUCE, IcebergLettuceModel::new);
		putModel(Plants.BONK_CHOY, BonkChoyModel::new);
	}
	
	public static Optional<IPlantModel<? extends IPVZPlant>> getPart1Model(Plants plant) {
		return Optional.ofNullable(PLANT_PART1_MODEL.get(plant));
	}
	
	public static Optional<IPlantModel<? extends IPVZPlant>> getPart2Model(Plants plant) {
		return Optional.ofNullable(PLANT_PART2_MODEL.get(plant));
	}
	
	private static void putModel(Plants plant, Supplier<? extends IPlantModel<? extends IPVZPlant>> sup) {
		PLANT_PART1_MODEL.put(plant, sup.get());
		PLANT_PART2_MODEL.put(plant, sup.get());
	}
}
