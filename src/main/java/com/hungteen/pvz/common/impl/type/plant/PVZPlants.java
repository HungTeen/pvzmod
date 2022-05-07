package com.hungteen.pvz.common.impl.type.plant;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.common.entity.PVZEntities;
import com.hungteen.pvz.common.impl.type.*;
import com.hungteen.pvz.utils.enums.CDTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-27 17:03
 **/
public class PVZPlants {

    private static final List<IPAZType> PLANTS = new ArrayList<>();

    /*
     * grass day.
     */

    public static final IPlantType PEA_SHOOTER = new PVZPlantType("pea_shooter")
            .sunCost(100).xp(10)
            .essence(EssenceTypes.APPEASE)
            .rank(RankTypes.WHITE)
            .cardType(CardTypes.SAPLING)
            .cd(CDTypes.VERY_FAST)
            .entity(() -> PVZEntities.PEA_SHOOTER.get())
            .skills(Arrays.asList(SkillTypes.PLANT_MORE_LIFE, SkillTypes.PEA_DAMAGE, SkillTypes.PEA_SPEED_UP));

    public static final IPlantType SUN_FLOWER = new PVZPlantType("sun_flower")
            .sunCost(50).xp(10)
            .essence(EssenceTypes.LIGHT)
            .rank(RankTypes.WHITE)
            .cardType(CardTypes.SAPLING)
            .cd(CDTypes.FAST)
            .entity(() -> PVZEntities.SUN_FLOWER.get())
            .skills(Arrays.asList(SkillTypes.PLANT_MORE_LIFE));

    public static final IPlantType WALL_NUT = new PVZPlantType("wall_nut")
            .sunCost(50).xp(30)
            .essence(EssenceTypes.DEFENCE)
            .cardType(CardTypes.SAPLING)
            .rank(RankTypes.WHITE)
            .cd(CDTypes.LITTLE_SLOW)
            .entity(() -> PVZEntities.WALL_NUT.get())
            .skills(Arrays.asList(SkillTypes.NUT_MORE_LIFE, SkillTypes.BOWLING_NUT, SkillTypes.EXPLOSION_NUT));

    public static final IPlantType POTATO_MINE = new PVZPlantType("potato_mine")
            .sunCost(25).xp(10)
            .essence(EssenceTypes.EXPLOSION)
            .rank(RankTypes.WHITE)
            .cardType(CardTypes.SAPLING)
            .cd(CDTypes.NORMAL)
            .entity(() -> PVZEntities.POTATO_MINE.get())
            .skills(Arrays.asList(SkillTypes.PLANT_MORE_LIFE, SkillTypes.POTATO_BOMB_DAMAGE, SkillTypes.MINE_FAST_PREPARE));

    public static final IPlantType SNOW_PEA = new PVZPlantType("snow_pea")
            .sunCost(175).xp(20)
            .essence(EssenceTypes.ICE)
            .rank(RankTypes.GOLD)
            .cardType(CardTypes.SAPLING)
            .cd(CDTypes.VERY_FAST)
            .entity(() -> PVZEntities.SNOW_PEA.get())
            .skills(Arrays.asList(SkillTypes.PLANT_MORE_LIFE, SkillTypes.PEA_DAMAGE, SkillTypes.PEA_SPEED_UP));

    public static final IPlantType REPEATER = new PVZPlantType("repeater")
            .sunCost(225).xp(20)
            .essence(EssenceTypes.APPEASE)
            .rank(RankTypes.BLUE)
            .cardType(CardTypes.SAPLING)
            .cd(CDTypes.VERY_FAST)
            .entity(() -> PVZEntities.REPEATER.get())
            .skills(Arrays.asList(SkillTypes.PLANT_MORE_LIFE, SkillTypes.PEA_DAMAGE, SkillTypes.PEA_SPEED_UP));

    public static final IPlantType LILY_PAD = new PVZPlantType("lily_pad")
            .sunCost(25).xp(1)
            .essence(EssenceTypes.ASSIST)
            .rank(RankTypes.WHITE)
            .cardType(CardTypes.SAPLING)
            .cd(CDTypes.VERY_FAST)
            .entity(() -> PVZEntities.LILY_PAD.get())
            .block(() -> PVZBlocks.LILY_PAD.get());

    public static final IPlantType CABBAGE_PULT = new PVZPlantType("cabbage_pult")
            .sunCost(100).xp(10)
            .essence(EssenceTypes.ARMA)
            .rank(RankTypes.WHITE)
            .cardType(CardTypes.SAPLING)
            .cd(CDTypes.VERY_FAST)
            .entity(() -> PVZEntities.CABBAGE_PULT.get())
            .skills(Arrays.asList(SkillTypes.PLANT_MORE_LIFE));

    public static final IPlantType FLOWER_POT = new PVZPlantType("flower_pot")
            .sunCost(25).xp(1)
            .essence(EssenceTypes.ASSIST)
            .rank(RankTypes.WHITE)
            .cardType(CardTypes.SAPLING)
            .cd(CDTypes.VERY_FAST)
            .entity(() -> PVZEntities.FLOWER_POT.get())
            .block(() -> PVZBlocks.FLOWER_POT.get());

    public static final IPlantType ICEBERG_LETTUCE = new PVZPlantType("iceberg_lettuce")
            .sunCost(0).xp(10)
            .essence(EssenceTypes.ICE)
            .rank(RankTypes.WHITE)
            .cardType(CardTypes.SAPLING)
            .cd(CDTypes.LITTLE_FAST)
            .entity(() -> PVZEntities.ICEBERG_LETTUCE.get())
            .skills(Arrays.asList(SkillTypes.PLANT_MORE_LIFE));

//    public static final IPlantType CHERRY_BOMB = new PVZPlants("cherry_bomb", new PlantFeatures()
//            .cost(150).requiredLevel(35)
//            .cd(CoolDowns.HUGE_SLOW).rank(RankTypes.BLUE).essence(EssenceTypes.EXPLOSION)
//            .entityType(() -> EntityRegister.CHERRY_BOMB.get())
//            .summonCard(() -> ItemRegister.CHERRY_BOMB_CARD.get())
//            .enjoyCard(() -> ItemRegister.CHERRY_BOMB_ENJOY_CARD.get())
//            .plantModel(() -> CherryBombModel::new).scale(0.5F)
//            .cdSkill(Arrays.asList(SkillTypes.NORMAL_BOMB_DAMAGE))
//    );
//
//    public static final IPlantType WALL_NUT = new PVZPlants("wall_nut", new PlantFeatures()
//            .cost(50).requiredLevel(5)
//            .cd(CoolDowns.SLOW).rank(RankTypes.WHITE).essence(EssenceTypes.DEFENCE)
//            .entityType(() -> EntityRegister.WALL_NUT.get())
//            .summonCard(() -> ItemRegister.WALL_NUT_CARD.get())
//            .enjoyCard(() -> ItemRegister.WALL_NUT_ENJOY_CARD.get())
//            .plantModel(() -> WallNutModel::new).scale(1F)
//            .cdSkill(Arrays.asList(SkillTypes.NUT_MORE_LIFE))
//    );
//
//    public static final IPlantType POTATO_MINE = new PVZPlants("potato_mine", new PlantFeatures()
//            .cost(25).requiredLevel(10)
//            .cd(CoolDowns.LITTLE_SLOW).rank(RankTypes.WHITE).essence(EssenceTypes.EXPLOSION)
//            .entityType(() -> EntityRegister.POTATO_MINE.get())
//            .summonCard(() -> ItemRegister.POTATO_MINE_CARD.get())
//            .enjoyCard(() -> ItemRegister.POTATO_MINE_ENJOY_CARD.get())
//            .plantModel(() -> PotatoMineModel::new).scale(0.6F)
//            .commonSkill(Arrays.asList(SkillTypes.NORMAL_BOMB_DAMAGE, SkillTypes.MINE_FAST_PREPARE))
//            .placement(Placements.STABLE)
//    );
//
//    public static final IPlantType SNOW_PEA = new PVZPlants("snow_pea", new PlantFeatures()
//            .cost(175).requiredLevel(20)
//            .cd(CoolDowns.VERY_FAST).rank(RankTypes.GREEN).essence(EssenceTypes.ICE)
//            .entityType(() -> EntityRegister.SNOW_PEA.get())
//            .summonCard(() -> ItemRegister.SNOW_PEA_CARD.get())
//            .enjoyCard(() -> ItemRegister.SNOW_PEA_ENJOY_CARD.get())
//            .plantModel(() -> SnowPeaModel::new).scale(1F)
//            .commonSkill(Arrays.asList(SkillTypes.PEA_DAMAGE))
//    );
//
//    public static final IPlantType CHOMPER = new PVZPlants("chomper", new PlantFeatures()
//            .cost(150).requiredLevel(40)
//            .cd(CoolDowns.NORMAL).rank(RankTypes.BLUE).essence(EssenceTypes.ENFORCE)
//            .entityType(() -> EntityRegister.CHOMPER.get())
//            .summonCard(() -> ItemRegister.CHOMPER_CARD.get())
//            .enjoyCard(() -> ItemRegister.CHOMPER_ENJOY_CARD.get())
//            .plantModel(() -> ChomperModel::new).scale(0.85F)
//            .commonSkill(Arrays.asList(SkillTypes.NORMAL_ENHANCE_STRENGTH))
//    );

//    public static final IPlantType REPEATER = new PVZPlants("repeater", new PlantFeatures()
//            .cost(225).requiredLevel(30)
//            .cd(CoolDowns.VERY_FAST).rank(RankTypes.BLUE).essence(EssenceTypes.APPEASE)
//            .entityType(() -> EntityRegister.REPEATER.get())
//            .summonCard(() -> ItemRegister.REPEATER_CARD.get())
//            .enjoyCard(() -> ItemRegister.REPEATER_ENJOY_CARD.get())
//            .plantModel(() -> RepeaterModel::new).scale(1F)
//            .upgradeTo(() -> PVZPlants.GATLING_PEA)
//            .commonSunSkill(Arrays.asList(SkillTypes.PEA_DAMAGE))
//    );
//
//    /*
//     * grass night.
//     */
//
//    public static final IPlantType PUFF_SHROOM = new PVZPlants("puff_shroom", new PlantFeatures().isShroomPlant()
//            .cost(1).requiredLevel(15)
//            .cd(CoolDowns.VERY_FAST).rank(RankTypes.GREEN).essence(EssenceTypes.TOXIC)
//            .entityType(() -> EntityRegister.PUFF_SHROOM.get())
//            .summonCard(() -> ItemRegister.PUFF_SHROOM_CARD.get())
//            .enjoyCard(() -> ItemRegister.PUFF_SHROOM_ENJOY_CARD.get())
//            .plantModel(() -> PuffShroomModel::new).scale(0.6F)
//            .cdSkill(Arrays.asList(SkillTypes.SPORE_DAMAGE))
//    );
//
//    public static final IPlantType SUN_SHROOM = new PVZPlants("sun_shroom", new PlantFeatures().isShroomPlant()
//            .cost(25).requiredLevel(15)
//            .cd(CoolDowns.VERY_FAST).rank(RankTypes.BLUE).essence(EssenceTypes.LIGHT)
//            .entityType(() -> EntityRegister.SUN_SHROOM.get())
//            .summonCard(() -> ItemRegister.SUN_SHROOM_CARD.get())
//            .enjoyCard(() -> ItemRegister.SUN_SHROOM_ENJOY_CARD.get())
//            .plantModel(() -> SunShroomModel::new).scale(0.4F)
//            .commonSkill(Arrays.asList())
//    );
//
//    public static final IPlantType FUME_SHROOM = new PVZPlants("fume_shroom", new PlantFeatures().isShroomPlant()
//            .cost(100).requiredLevel(40)
//            .cd(CoolDowns.LITTLE_FAST).rank(RankTypes.BLUE).essence(EssenceTypes.TOXIC)
//            .entityType(() -> EntityRegister.FUME_SHROOM.get())
//            .summonCard(() -> ItemRegister.FUME_SHROOM_CARD.get())
//            .enjoyCard(() -> ItemRegister.FUME_SHROOM_ENJOY_CARD.get())
//            .plantModel(() -> FumeShroomModel::new).scale(0.9F)
//            .upgradeTo(() -> PVZPlants.GLOOM_SHROOM)
//            .commonSkill(Arrays.asList(SkillTypes.SPORE_DAMAGE))
//    );
//
//    public static final IPlantType GRAVE_BUSTER = new PVZPlants("grave_buster", new PlantFeatures()
//            .cost(75).requiredLevel(10)
//            .cd(CoolDowns.HUGE_FAST).rank(RankTypes.WHITE).essence(EssenceTypes.ASSIST)
//            .entityType(() -> EntityRegister.GRAVE_BUSTER.get())
//            .summonCard(() -> ItemRegister.GRAVE_BUSTER_CARD.get())
//            .enjoyCard(() -> ItemRegister.GRAVE_BUSTER_ENJOY_CARD.get())
//            .plantModel(() -> GraveBusterModel::new).scale(1F)
//            .commonSkill(Arrays.asList())
//    );
//
//    public static final IPlantType HYPNO_SHROOM = new PVZPlants("hypno_shroom", new PlantFeatures().isShroomPlant()
//            .cost(75).requiredLevel(45)
//            .cd(CoolDowns.LITTLE_SLOW).rank(RankTypes.BLUE).essence(EssenceTypes.MAGIC)
//            .entityType(() -> EntityRegister.HYPNO_SHROOM.get())
//            .summonCard(() -> ItemRegister.HYPNO_SHROOM_CARD.get())
//            .enjoyCard(() -> ItemRegister.HYPNO_SHROOM_ENJOY_CARD.get())
//            .plantModel(() -> HypnoShroomModel::new).scale(0.6F)
//            .cdSkill(Arrays.asList())
//    );
//
//    public static final IPlantType SCAREDY_SHROOM = new PVZPlants("scaredy_shroom", new PlantFeatures().isShroomPlant()
//            .cost(25).requiredLevel(25)
//            .cd(CoolDowns.VERY_FAST).rank(RankTypes.WHITE).essence(EssenceTypes.TOXIC)
//            .entityType(() -> EntityRegister.SCAREDY_SHROOM.get())
//            .summonCard(() -> ItemRegister.SCAREDY_SHROOM_CARD.get())
//            .enjoyCard(() -> ItemRegister.SCAREDY_SHROOM_ENJOY_CARD.get())
//            .plantModel(() -> ScaredyShroomModel::new).scale(0.7F)
//            .commonSkill(Arrays.asList(SkillTypes.SPORE_DAMAGE))
//    );
//
//    public static final IPlantType ICE_SHROOM = new PVZPlants("ice_shroom", new PlantFeatures().isShroomPlant()
//            .cost(75).requiredLevel(35)
//            .cd(CoolDowns.SLOW).rank(RankTypes.PURPLE).essence(EssenceTypes.ICE)
//            .entityType(() -> EntityRegister.ICE_SHROOM.get())
//            .summonCard(() -> ItemRegister.ICE_SHROOM_CARD.get())
//            .enjoyCard(() -> ItemRegister.ICE_SHROOM_ENJOY_CARD.get())
//            .plantModel(() -> IceShroomModel::new).scale(0.82F)
//            .commonSkill(Arrays.asList())
//    );
//
//    public static final IPlantType DOOM_SHROOM = new PVZPlants("doom_shroom", new PlantFeatures().isShroomPlant()
//            .cost(225).requiredLevel(65)
//            .cd(CoolDowns.SUPER_SLOW).rank(RankTypes.RED).essence(EssenceTypes.EXPLOSION)
//            .entityType(() -> EntityRegister.DOOM_SHROOM.get())
//            .summonCard(() -> ItemRegister.DOOM_SHROOM_CARD.get())
//            .enjoyCard(() -> ItemRegister.DOOM_SHROOM_ENJOY_CARD.get())
//            .plantModel(() -> DoomShroomModel::new).scale(1F)
//            .commonSunSkill(Arrays.asList(SkillTypes.HIGH_EXPLODE_DAMAGE))
//    );
//
//    /*
//     * pool day.
//     */
//    public static final IPlantType LILY_PAD = new PVZPlants("lily_pad", new PlantFeatures().isWaterPlant()
//            .cost(25).requiredLevel(10)
//            .cd(CoolDowns.SUPER_FAST).rank(RankTypes.GRAY).essence(EssenceTypes.ASSIST)
//            .plantBlock(() -> BlockRegister.LILY_PAD.get())
//            .entityType(() -> EntityRegister.LILY_PAD.get())
//            .summonCard(() -> ItemRegister.LILY_PAD_CARD.get())
//            .enjoyCard(() -> ItemRegister.LILY_PAD_ENJOY_CARD.get())
//            .plantModel(() -> LilyPadModel::new).scale(1F)
//            .upgradeTo(() -> PVZPlants.CAT_TAIL)
//            .cdSkill(Arrays.asList())
//    );
//
//    public static final IPlantType SQUASH = new PVZPlants("squash", new PlantFeatures()
//            .cost(50).requiredLevel(35)
//            .cd(CoolDowns.LITTLE_SLOW).rank(RankTypes.GREEN).essence(EssenceTypes.ENFORCE)
//            .entityType(() -> EntityRegister.SQUASH.get())
//            .summonCard(() -> ItemRegister.SQUASH_CARD.get())
//            .enjoyCard(() -> ItemRegister.SQUASH_ENJOY_CARD.get())
//            .plantModel(() -> SquashModel::new).scale(0.5F)
//            .commonSkill(Arrays.asList(SkillTypes.NORMAL_ENHANCE_STRENGTH))
//    );
//
//    public static final IPlantType THREE_PEATER = new PVZPlants("three_peater", new PlantFeatures()
//            .cost(325).requiredLevel(40)
//            .cd(CoolDowns.LITTLE_FAST).rank(RankTypes.GREEN).essence(EssenceTypes.APPEASE)
//            .entityType(() -> EntityRegister.THREE_PEATER.get())
//            .summonCard(() -> ItemRegister.THREE_PEATER_CARD.get())
//            .enjoyCard(() -> ItemRegister.THREE_PEATER_ENJOY_CARD.get())
//            .plantModel(() -> ThreePeaterModel::new).scale(0.98F)
//            .commonSunSkill(Arrays.asList(SkillTypes.PEA_DAMAGE))
//    );
//
//    public static final IPlantType TANGLE_KELP = new PVZPlants("tangle_kelp", new PlantFeatures().isWaterPlant()
//            .cost(25).requiredLevel(15)
//            .cd(CoolDowns.LITTLE_SLOW).rank(RankTypes.WHITE).essence(EssenceTypes.ENFORCE)
//            .entityType(() -> EntityRegister.TANGLE_KELP.get())
//            .summonCard(() -> ItemRegister.TANGLE_KELP_CARD.get())
//            .enjoyCard(() -> ItemRegister.TANGLE_KELP_ENJOY_CARD.get())
//            .plantModel(() -> TangleKelpModel::new).scale(0.6F)
//            .commonSkill(Arrays.asList(SkillTypes.NORMAL_ENHANCE_STRENGTH))
//    );
//
//    public static final IPlantType JALAPENO = new PVZPlants("jalapeno", new PlantFeatures()
//            .cost(175).requiredLevel(45)
//            .cd(CoolDowns.HUGE_SLOW).rank(RankTypes.PURPLE).essence(EssenceTypes.FLAME)
//            .entityType(() -> EntityRegister.JALAPENO.get())
//            .summonCard(() -> ItemRegister.JALAPENO_CARD.get())
//            .enjoyCard(() -> ItemRegister.JALAPENO_ENJOY_CARD.get())
//            .plantModel(() -> JalapenoModel::new).scale(0.5F)
//            .cdSkill(Arrays.asList(SkillTypes.NORMAL_BOMB_DAMAGE))
//    );
//
//    public static final IPlantType SPIKE_WEED = new PVZPlants("spike_weed", new PlantFeatures()
//            .cost(100).requiredLevel(20)
//            .cd(CoolDowns.LITTLE_FAST).rank(RankTypes.WHITE).essence(EssenceTypes.SPEAR)
//            .entityType(() -> EntityRegister.SPIKE_WEED.get())
//            .summonCard(() -> ItemRegister.SPIKE_WEED_CARD.get())
//            .enjoyCard(() -> ItemRegister.SPIKE_WEED_ENJOY_CARD.get())
//            .plantModel(() -> SpikeWeedModel::new).scale(0.5F)
//            .placement(Placements.STABLE)
//            .upgradeTo(() -> PVZPlants.SPIKE_ROCK)
//            .commonSkill(Arrays.asList(SkillTypes.SPIKE_DAMAGE))
//    );
//
//    public static final IPlantType TORCH_WOOD = new PVZPlants("torch_wood", new PlantFeatures()
//            .cost(200).requiredLevel(80)
//            .cd(CoolDowns.NORMAL).rank(RankTypes.GOLD).essence(EssenceTypes.FLAME)
//            .entityType(() -> EntityRegister.TORCH_WOOD.get())
//            .summonCard(() -> ItemRegister.TORCH_WOOD_CARD.get())
//            .enjoyCard(() -> ItemRegister.TORCH_WOOD_ENJOY_CARD.get())
//            .plantModel(() -> TorchWoodModel::new).scale(0.5F)
//            .cdSkill(Arrays.asList(SkillTypes.WOOD_MORE_LIFE, SkillTypes.HEAT_PEA_RANGE, SkillTypes.LESS_SUN))
//    );

//    public static final IPlantType TALL_NUT = new PVZPlants("tall_nut", new PlantFeatures()
//            .cost(125).requiredLevel(30)
//            .cd(CoolDowns.VERY_SLOW).rank(RankTypes.PURPLE).essence(EssenceTypes.DEFENCE)
//            .entityType(() -> EntityRegister.TALL_NUT.get())
//            .summonCard(() -> ItemRegister.TALL_NUT_CARD.get())
//            .enjoyCard(() -> ItemRegister.TALL_NUT_ENJOY_CARD.get())
//            .plantModel(() -> TallNutModel::new).scale(0.4F)
//            .cdSkill(Arrays.asList(SkillTypes.NUT_MORE_LIFE))
//    );
//
//    /*
//     * pool night.
//     */
//    public static final IPlantType SEA_SHROOM = new PVZPlants("sea_shroom", new PlantFeatures().isShroomPlant().isWaterPlant()
//            .cost(1).requiredLevel(5)
//            .cd(CoolDowns.FAST).rank(RankTypes.WHITE).essence(EssenceTypes.TOXIC)
//            .entityType(() -> EntityRegister.SEA_SHROOM.get())
//            .summonCard(() -> ItemRegister.SEA_SHROOM_CARD.get())
//            .enjoyCard(() -> ItemRegister.SEA_SHROOM_ENJOY_CARD.get())
//            .plantModel(() -> SeaShroomModel::new).scale(0.6F)
//            .commonSkill(Arrays.asList(SkillTypes.SPORE_DAMAGE))
//    );
//
//    public static final IPlantType PLANTERN = new PVZPlants("plantern", new PlantFeatures()
//            .cost(75).requiredLevel(5)
//            .cd(CoolDowns.FAST).rank(RankTypes.GRAY).essence(EssenceTypes.LIGHT)
//            .entityType(() -> EntityRegister.PLANTERN.get())
//            .summonCard(() -> ItemRegister.PLANTERN_CARD.get())
//            .enjoyCard(() -> ItemRegister.PLANTERN_ENJOY_CARD.get())
//            .plantModel(() -> PlanternModel::new).scale(0.7F)
//            .commonSkill(Arrays.asList(SkillTypes.MORE_LIGHT_RANGE, SkillTypes.NIGHT_VISION))
//    );
//
//    public static final IPlantType CACTUS = new PVZPlants("cactus", new PlantFeatures()
//            .cost(125).requiredLevel(15)
//            .cd(CoolDowns.FAST).rank(RankTypes.GREEN).essence(EssenceTypes.SPEAR)
//            .entityType(() -> EntityRegister.CACTUS.get())
//            .summonCard(() -> ItemRegister.CACTUS_CARD.get())
//            .enjoyCard(() -> ItemRegister.CACTUS_ENJOY_CARD.get())
//            .plantModel(() -> CactusModel::new).scale(1F)
//            .placement(Placements.SAND)
//            .commonSkill(Arrays.asList(SkillTypes.MORE_THORN_DAMAGE))
//    );
//
//    public static final IPlantType BLOVER = new PVZPlants("blover", new PlantFeatures()
//            .cost(50).requiredLevel(20)
//            .cd(CoolDowns.FAST).rank(RankTypes.GREEN).essence(EssenceTypes.ASSIST)
//            .entityType(() -> EntityRegister.BLOVER.get())
//            .summonCard(() -> ItemRegister.BLOVER_CARD.get())
//            .enjoyCard(() -> ItemRegister.BLOVER_ENJOY_CARD.get())
//            .plantModel(() -> BloverModel::new).scale(1.2F)
//            .cdSkill(Arrays.asList(SkillTypes.BLOW_STRENGTH))
//    );
//
//    public static final IPlantType SPLIT_PEA = new PVZPlants("split_pea", new PlantFeatures()
//            .cost(125).requiredLevel(10)
//            .cd(CoolDowns.HUGE_FAST).rank(RankTypes.GREEN).essence(EssenceTypes.APPEASE)
//            .entityType(() -> EntityRegister.SPLIT_PEA.get())
//            .summonCard(() -> ItemRegister.SPLIT_PEA_CARD.get())
//            .enjoyCard(() -> ItemRegister.SPLIT_PEA_ENJOY_CARD.get())
//            .plantModel(() -> SplitPeaModel::new).scale(1F)
//            .commonSkill(Arrays.asList(SkillTypes.PEA_DAMAGE, SkillTypes.SPLIT_DOUBLE_CHANCE))
//    );
//
//    public static final IPlantType STAR_FRUIT = new PVZPlants("star_fruit", new PlantFeatures()
//            .cost(150).requiredLevel(25)
//            .cd(CoolDowns.FAST).rank(RankTypes.GREEN).essence(EssenceTypes.APPEASE)
//            .entityType(() -> EntityRegister.STAR_FRUIT.get())
//            .summonCard(() -> ItemRegister.STAR_FRUIT_CARD.get())
//            .enjoyCard(() -> ItemRegister.STAR_FRUIT_ENJOY_CARD.get())
//            .plantModel(() -> StarFruitModel::new).scale(0.82F)
//            .commonSkill(Arrays.asList(SkillTypes.MORE_STAR_DAMAGE))
//    );
//
//    public static final IPlantType PUMPKIN = new PVZPlants("pumpkin", new PlantFeatures()
//            .cost(125).requiredLevel(50)
//            .cd(CoolDowns.SLOW).rank(RankTypes.BLUE).essence(EssenceTypes.DEFENCE)
//            .outerPlant(() -> new PumpkinInfo())
//            .entityType(() -> EntityRegister.PUMPKIN.get())
//            .summonCard(() -> ItemRegister.PUMPKIN_CARD.get())
//            .enjoyCard(() -> ItemRegister.PUMPKIN_ENJOY_CARD.get())
//            //TODO (removed by GrassCarp when remodeling Pumpkin).plantModel(() -> PumpkinModel::new).scale(1F)
//            .cdSkill(Arrays.asList())
//    );
//
//    public static final IPlantType MAGNET_SHROOM = new PVZPlants("magnet_shroom", new PlantFeatures().isShroomPlant()
//            .cost(100).requiredLevel(45)
//            .cd(CoolDowns.LITTLE_FAST).rank(RankTypes.BLUE).essence(EssenceTypes.ASSIST)
//            .entityType(() -> EntityRegister.MAGNET_SHROOM.get())
//            .summonCard(() -> ItemRegister.MAGNET_SHROOM_CARD.get())
//            .enjoyCard(() -> ItemRegister.MAGNET_SHROOM_ENJOY_CARD.get())
//            .plantModel(() -> MagnetShroomModel::new).scale(1.2F)
//            .upgradeTo(() -> PVZPlants.GOLD_MAGNET)
//            .commonSkill(Arrays.asList(SkillTypes.LESS_WORK_CD))
//    );
//
//    /*
//     * roof.
//     */
//    public static final IPlantType CABBAGE_PULT = new PVZPlants("cabbage_pult", new PlantFeatures()
//            .cost(100).requiredLevel(10)
//            .cd(CoolDowns.VERY_FAST).rank(RankTypes.GREEN).essence(EssenceTypes.ARMA)
//            .entityType(() -> EntityRegister.CABBAGE_PULT.get())
//            .summonCard(() -> ItemRegister.CABBAGE_PULT_CARD.get())
//            .enjoyCard(() -> ItemRegister.CABBAGE_PULT_ENJOY_CARD.get())
//            .plantModel(() -> CabbagePultModel::new).scale(0.9F)
//            .commonSkill(Arrays.asList(SkillTypes.MORE_CABBAGE_DAMAGE))
//    );
//
//    public static final IPlantType FLOWER_POT = new PVZPlants("flower_pot", new PlantFeatures()
//            .cost(25).requiredLevel(40)
//            .cd(CoolDowns.HUGE_FAST).rank(RankTypes.GREEN).essence(EssenceTypes.ASSIST)
//            .plantBlock(() -> BlockRegister.FLOWER_POT.get())
//            .entityType(() -> EntityRegister.FLOWER_POT.get())
//            .summonCard(() -> ItemRegister.FLOWER_POT_CARD.get())
//            .enjoyCard(() -> ItemRegister.FLOWER_POT_ENJOY_CARD.get())
//            .plantModel(() -> FlowerPotModel::new).scale(0.85F)
//            .placement(Placements.ANY)
//            .cdSkill(Arrays.asList())
//    );
//
//    public static final IPlantType KERNEL_PULT = new PVZPlants("kernel_pult", new PlantFeatures()
//            .cost(125).requiredLevel(35)
//            .cd(CoolDowns.FAST).rank(RankTypes.BLUE).essence(EssenceTypes.ARMA)
//            .entityType(() -> EntityRegister.KERNEL_PULT.get())
//            .summonCard(() -> ItemRegister.KERNEL_PULT_CARD.get())
//            .enjoyCard(() -> ItemRegister.KERNEL_PULT_ENJOY_CARD.get())
//            .plantModel(() -> KernelPultModel::new).scale(0.9F)
//            .upgradeTo(() -> PVZPlants.COB_CANNON)
//            .commonSkill(Arrays.asList(SkillTypes.MORE_KERNEL_DAMAGE))
//    );
//
//    public static final IPlantType COFFEE_BEAN = new PVZPlants("coffee_bean", new PlantFeatures()
//            .cost(75).requiredLevel(50)
//            .cd(CoolDowns.FAST).rank(RankTypes.GREEN).essence(EssenceTypes.MAGIC)
//            .entityType(() -> EntityRegister.COFFEE_BEAN.get())
//            .summonCard(() -> ItemRegister.COFFEE_BEAN_CARD.get())
//            .enjoyCard(() -> ItemRegister.COFFEE_BEAN_ENJOY_CARD.get())
//            .plantModel(() -> CoffeeBeanModel::new).scale(0.6F)
//            .cdSkill(Arrays.asList())
//    );
//
//    public static final IPlantType GARLIC = new PVZPlants("garlic", new PlantFeatures()
//            .cost(50).requiredLevel(35)
//            .cd(CoolDowns.FAST).rank(RankTypes.GREEN).essence(EssenceTypes.DEFENCE)
//            .entityType(() -> EntityRegister.GARLIC.get())
//            .summonCard(() -> ItemRegister.GARLIC_CARD.get())
//            .enjoyCard(() -> ItemRegister.GARLIC_ENJOY_CARD.get())
//            .plantModel(() -> GarlicModel::new).scale(0.9F)
//            .cdSkill(Arrays.asList(SkillTypes.MORE_GARLIC_LIFE))
//    );
//
//    public static final IPlantType UMBRELLA_LEAF = new PVZPlants("umbrella_leaf", new PlantFeatures()
//            .cost(100).requiredLevel(25)
//            .cd(CoolDowns.LITTLE_FAST).rank(RankTypes.GREEN).essence(EssenceTypes.ENFORCE)
//            .entityType(() -> EntityRegister.UMBRELLA_LEAF.get())
//            .summonCard(() -> ItemRegister.UMBRELLA_LEAF_CARD.get())
//            .enjoyCard(() -> ItemRegister.UMBRELLA_LEAF_ENJOY_CARD.get())
//            .plantModel(() -> UmbrellaLeafModel::new).scale(0.8F)
//            .commonSkill(Arrays.asList())
//    );
//
//    public static final IPlantType MARIGOLD = new PVZPlants("marigold", new PlantFeatures()
//            .cost(50).requiredLevel(65)
//            .cd(CoolDowns.NORMAL).rank(RankTypes.PURPLE).essence(EssenceTypes.ASSIST)
//            .entityType(() -> EntityRegister.MARIGOLD.get())
//            .summonCard(() -> ItemRegister.MARIGOLD_CARD.get())
//            .enjoyCard(() -> ItemRegister.MARIGOLD_ENJOY_CARD.get())
//            .plantModel(() -> MariGoldModel::new).scale(0.5F)
//            .commonSkill(Arrays.asList())
//    );
//
//    public static final IPlantType MELON_PULT = new PVZPlants("melon_pult", new PlantFeatures()
//            .cost(325).requiredLevel(50)
//            .cd(CoolDowns.FAST).rank(RankTypes.GOLD).essence(EssenceTypes.ARMA)
//            .entityType(() -> EntityRegister.MELON_PULT.get())
//            .summonCard(() -> ItemRegister.MELON_PULT_CARD.get())
//            .enjoyCard(() -> ItemRegister.MELON_PULT_ENJOY_CARD.get())
//            .plantModel(() -> MelonPultModel::new).scale(0.9F)
//            .upgradeTo(() -> PVZPlants.WINTER_MELON)
//            .commonSunSkill(Arrays.asList(SkillTypes.MORE_MELON_DAMAGE))
//    );
//
//    /*
//     * upgrade
//     */
//    public static final IPlantType GATLING_PEA = new PVZPlants("gatling_pea", new PlantFeatures()
//            .cost(450).requiredLevel(60)
//            .cd(CoolDowns.VERY_SLOW).rank(RankTypes.GOLD).essence(EssenceTypes.APPEASE)
//            .entityType(() -> EntityRegister.GATLING_PEA.get())
//            .summonCard(() -> ItemRegister.GATLING_PEA_CARD.get())
//            .enjoyCard(() -> ItemRegister.GATLING_PEA_ENJOY_CARD.get())
//            .plantModel(() -> GatlingPeaModel::new).scale(0.5F)
//            .upgradeFrom(() -> PVZPlants.REPEATER)
//            .commonSunSkill(Arrays.asList(SkillTypes.PEA_DAMAGE))
//    );
//
//    public static final IPlantType TWIN_SUNFLOWER = new PVZPlants("twin_sunflower", new PlantFeatures()
//            .cost(200).requiredLevel(25)
//            .cd(CoolDowns.VERY_SLOW).rank(RankTypes.GOLD).essence(EssenceTypes.LIGHT)
//            .entityType(() -> EntityRegister.TWIN_SUNFLOWER.get())
//            .summonCard(() -> ItemRegister.TWIN_SUNFLOWER_CARD.get())
//            .enjoyCard(() -> ItemRegister.TWIN_SUNFLOWER_ENJOY_CARD.get())
//            .plantModel(() -> TwinSunFlowerModel::new).scale(0.45F)
//            .upgradeFrom(() -> PVZPlants.SUN_FLOWER)
//            .commonSunSkill(Arrays.asList())
//    );
//
//    public static final IPlantType GLOOM_SHROOM = new PVZPlants("gloom_shroom", new PlantFeatures().isShroomPlant()
//            .cost(250).requiredLevel(55)
//            .cd(CoolDowns.VERY_SLOW).rank(RankTypes.GOLD).essence(EssenceTypes.TOXIC)
//            .entityType(() -> EntityRegister.GLOOM_SHROOM.get())
//            .summonCard(() -> ItemRegister.GLOOM_SHROOM_CARD.get())
//            .enjoyCard(() -> ItemRegister.GLOOM_SHROOM_ENJOY_CARD.get())
//            .plantModel(() -> GloomShroomModel::new).scale(1F)
//            .upgradeFrom(() -> PVZPlants.FUME_SHROOM)
//            .commonSunSkill(Arrays.asList(SkillTypes.SPORE_DAMAGE))
//    );
//
//    public static final IPlantType CAT_TAIL = new PVZPlants("cat_tail", new PlantFeatures().isWaterPlant()
//            .cost(350).requiredLevel(70)
//            .cd(CoolDowns.VERY_SLOW).rank(RankTypes.RED).essence(EssenceTypes.SPEAR)
//            .placement(Placements.LILY_PAD)
//            .entityType(() -> EntityRegister.CAT_TAIL.get())
//            .summonCard(() -> ItemRegister.CAT_TAIL_CARD.get())
//            .enjoyCard(() -> ItemRegister.CAT_TAIL_ENJOY_CARD.get())
//            .plantModel(() -> CatTailModel::new).scale(1F)
//            .commonSunSkill(Arrays.asList(SkillTypes.MORE_THORN_DAMAGE))
//    );
//
//    public static final IPlantType WINTER_MELON = new PVZPlants("winter_melon", new PlantFeatures()
//            .cost(550).requiredLevel(65)
//            .cd(CoolDowns.VERY_SLOW).rank(RankTypes.RED).essence(EssenceTypes.ICE)
//            .entityType(() -> EntityRegister.WINTER_MELON.get())
//            .summonCard(() -> ItemRegister.WINTER_MELON_CARD.get())
//            .enjoyCard(() -> ItemRegister.WINTER_MELON_ENJOY_CARD.get())
//            .plantModel(() -> WinterMelonModel::new).scale(0.9F)
//            .upgradeFrom(() -> PVZPlants.MELON_PULT)
//            .commonSunSkill(Arrays.asList(SkillTypes.MORE_MELON_DAMAGE))
//    );
//
//    public static final IPlantType GOLD_MAGNET = new PVZPlants("gold_magnet", new PlantFeatures()
//            .cost(200).requiredLevel(55)
//            .cd(CoolDowns.VERY_SLOW).rank(RankTypes.WHITE).essence(EssenceTypes.ASSIST)
//            .entityType(() -> EntityRegister.GOLD_MAGNET.get())
//            .summonCard(() -> ItemRegister.GOLD_MAGNET_CARD.get())
//            .enjoyCard(() -> ItemRegister.GOLD_MAGNET_ENJOY_CARD.get())
//            .plantModel(() -> GoldMagnetModel::new).scale(1.2F)
//            .upgradeFrom(() -> PVZPlants.MAGNET_SHROOM)
//            .commonSunSkill(Arrays.asList())
//    );
//
//    public static final IPlantType SPIKE_ROCK = new PVZPlants("spike_rock", new PlantFeatures()
//            .cost(275).requiredLevel(55)
//            .cd(CoolDowns.VERY_SLOW).rank(RankTypes.PURPLE).essence(EssenceTypes.SPEAR)
//            .entityType(() -> EntityRegister.SPIKE_ROCK.get())
//            .summonCard(() -> ItemRegister.SPIKE_ROCK_CARD.get())
//            .enjoyCard(() -> ItemRegister.SPIKE_ROCK_ENJOY_CARD.get())
//            .plantModel(() -> SpikeRockModel::new).scale(0.5F)
//            .placement(Placements.STABLE)
//            .upgradeFrom(() -> PVZPlants.SPIKE_WEED)
//            .commonSunSkill(Arrays.asList(SkillTypes.SPIKE_DAMAGE, SkillTypes.MORE_SPIKE))
//    );
//
//    public static final IPlantType COB_CANNON = new PVZPlants("cob_cannon", new PlantFeatures()
//            .cost(700).requiredLevel(70)
//            .cd(CoolDowns.HUGE_SLOW).rank(RankTypes.RED).essence(EssenceTypes.EXPLOSION)
//            .entityType(() -> EntityRegister.COB_CANNON.get())
//            .summonCard(() -> ItemRegister.COB_CANNON_CARD.get())
//            .enjoyCard(() -> ItemRegister.COB_CANNON_ENJOY_CARD.get())
//            .plantModel(() -> CobCannonModel::new).scale(1.1F)
//            .placement(Placements.ANY)
//            .upgradeFrom(() -> PVZPlants.KERNEL_PULT)
//            .commonSunSkill(Arrays.asList(SkillTypes.NORMAL_BOMB_DAMAGE))
//    );
//
//    public static final IPlantType IMITATER = new PVZPlants("imitater", new PlantFeatures()
//            .requiredLevel(60)
//            .rank(RankTypes.GOLD).essence(EssenceTypes.MAGIC)
//            .entityType(() -> EntityRegister.IMITATER.get())
//            .summonCard(() -> ItemRegister.IMITATER_CARD.get())
//            .enjoyCard(() -> ItemRegister.IMITATER_ENJOY_CARD.get())
//            .plantModel(() -> ImitaterModel::new).scale(0.9F)
//            .placement(Placements.NONE)
//    );


    public static class PVZPlantType extends PlantType {

        protected PVZPlantType(String name) {
            super(name);
            PLANTS.add(this);
        }

        /**
         * {@link PVZMod#coreRegister()}
         */
        public static void register() {
            PLANTS.forEach(paz -> PVZAPI.get().registerPAZType(paz));
        }

        @Override
        public int getSortPriority() {
            return 100;
        }

        @Override
        public String getCategoryName() {
            return "pvz_plant";
        }

        @Override
        public String getModID() {
            return PVZMod.MOD_ID;
        }

    }

}
