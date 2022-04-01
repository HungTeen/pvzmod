package com.hungteen.pvz.common.entity;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.common.entity.animal.GrassCarp;
import com.hungteen.pvz.common.entity.bullet.PeaBullet;
import com.hungteen.pvz.common.entity.drop.*;
import com.hungteen.pvz.common.entity.effect.OriginEffectEntity;
import com.hungteen.pvz.common.entity.plant.PeaShooter;
import com.hungteen.pvz.common.entity.plant.base.PVZPlant;
import com.hungteen.pvz.common.entity.plant.SunFlower;
import com.hungteen.pvz.common.impl.type.PAZTypes;
import com.hungteen.pvz.utils.Util;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-09 14:04
 *
 * step 1 : register entity type. <br>
 * step 2 : make a model for entity. <br>
 * step 3 : register entity render at {@link com.hungteen.pvz.client.ClientRegister#registerRenderers(EntityRenderersEvent.RegisterRenderers)}. <br>
 * step 4 : if entity has attributes, just register them. <br>
 **/
public class PVZEntities {

    /**
     * {@link PVZMod#deferredRegister(IEventBus)}
     */
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =  DeferredRegister.create(ForgeRegistries.ENTITIES, PVZMod.MOD_ID);

    /*
     Drop entity.
     */
    public static final RegistryObject<EntityType<Sun>> SUN = registerEntityType(Sun::new, "sun", MobCategory.AMBIENT);
    public static final RegistryObject<EntityType<Coin>> COPPER_COIN = registerEntityType(Coin.CopperCoin::new, "copper_coin", MobCategory.MISC);
    public static final RegistryObject<EntityType<Coin>> SILVER_COIN = registerEntityType(Coin.SilverCoin::new, "silver_coin", MobCategory.MISC);
    public static final RegistryObject<EntityType<Coin>> GOLD_COIN = registerEntityType(Coin.GoldCoin::new, "gold_coin", MobCategory.MISC);
    public static final RegistryObject<EntityType<Jewel>> JEWEL = registerEntityType(Jewel::new, "jewel", MobCategory.MISC);
    public static final RegistryObject<EntityType<PlantFood>> PLANT_FOOD = registerEntityType(PlantFood::new, "plant_food", MobCategory.MISC);
    public static final RegistryObject<EntityType<OriginOrb>> ORIGIN_ORB = registerEntityType(OriginOrb::new, "origin_orb", MobCategory.MISC);
//    public static final RegistryObject<EntityType<GiftBoxEntity>> GIFT_BOX = registerEntityType(GiftBoxEntity::new, "gift_box", EntityClassification.MISC, 0.9f, 1f);

    /*
     Effects
     */
    public static final RegistryObject<EntityType<OriginEffectEntity>> ORIGIN_EFFECT = registerEntityType(OriginEffectEntity::new, "origin_effect", MobCategory.MISC);
//    public static final RegistryObject<EntityType<DoomFixerEntity>> DOOM_FIXER = registerEntityType(DoomFixerEntity::new, "doom_fixer", EntityClassification.MISC);

    /*
     Bullets
     */
    public static final RegistryObject<EntityType<PeaBullet>> PEA_BULLET = registerEntityType(PeaBullet::new, "pea_bullet", MobCategory.MISC);
//    public static final RegistryObject<EntityType<PotatoEntity>> POTATO = registerEntityType(PotatoEntity::new, "potato", EntityClassification.MISC);
//    public static final RegistryObject<EntityType<SporeEntity>> SPORE = registerEntityType(SporeEntity::new, "spore", EntityClassification.MISC);
//    public static final RegistryObject<EntityType<FumeEntity>> FUME = registerEntityType(FumeEntity::new, "fume", EntityClassification.MISC);
//    public static final RegistryObject<EntityType<MetalItemEntity>> METAL = registerEntityType(MetalItemEntity::new, "metal", EntityClassification.MISC);
//    public static final RegistryObject<EntityType<ThornEntity>> THORN = registerEntityType(ThornEntity::new, "thorn", EntityClassification.MISC);
//    public static final RegistryObject<EntityType<StarEntity>> STAR = registerEntityType(StarEntity::new, "star", EntityClassification.MISC);
//    public static final RegistryObject<EntityType<CabbageEntity>> CABBAGE = registerEntityType(CabbageEntity::new, "cabbage", EntityClassification.MISC);
//    public static final RegistryObject<EntityType<KernelEntity>> KERNEL = registerEntityType(KernelEntity::new, "kernel", EntityClassification.MISC);
//    public static final RegistryObject<EntityType<ButterEntity>> BUTTER = registerEntityType(ButterEntity::new, "butter", EntityClassification.MISC);
//    public static final RegistryObject<EntityType<TargetArrowEntity>> TARGET_ARROW = registerEntityType(TargetArrowEntity::new, "target_arrow", EntityClassification.MISC);
//    public static final RegistryObject<EntityType<MelonEntity>> MELON = registerEntityType(MelonEntity::new, "melon", EntityClassification.MISC);
//    public static final RegistryObject<EntityType<FireCrackerEntity>> FIRE_CRACKER = registerEntityType(FireCrackerEntity::new, "fire_cracker", EntityClassification.MISC);
//    public static final RegistryObject<EntityType<BallEntity>> BALL = registerEntityType(BallEntity::new, "ball", EntityClassification.MISC);
//    public static final RegistryObject<EntityType<CornEntity>> CORN = registerEntityType(CornEntity::new, "corn", EntityClassification.MISC);


    /*
    Animals
     */
    public static final RegistryObject<EntityType<GrassCarp>> GRASS_CARP = registerEntityType(GrassCarp::new, "grass_carp", MobCategory.WATER_CREATURE);

    /*
     Plants
     */
    public static final RegistryObject<EntityType<PeaShooter>> PEA_SHOOTER = registerPlantEntityType(PeaShooter::new, "pea_shooter");
    public static final RegistryObject<EntityType<SunFlower>> SUN_FLOWER = registerPlantEntityType(SunFlower::new, "sun_flower");
//    public static final RegistryObject<EntityType<CherryBombEntity>> CHERRY_BOMB = registerPlantEntityType(CherryBombEntity::new, "cherry_bomb");
//    public static final RegistryObject<EntityType<WallNutEntity>> WALL_NUT = registerPlantEntityType(WallNutEntity::new, "wall_nut");
//    public static final RegistryObject<EntityType<PotatoMineEntity>> POTATO_MINE = registerPlantEntityType(PotatoMineEntity::new, "potato_mine");
//    public static final RegistryObject<EntityType<SnowPeaEntity>> SNOW_PEA = registerPlantEntityType(SnowPeaEntity::new, "snow_pea");
//    public static final RegistryObject<EntityType<ChomperEntity>> CHOMPER = registerPlantEntityType(ChomperEntity::new, "chomper");
//    public static final RegistryObject<EntityType<RepeaterEntity>> REPEATER = registerPlantEntityType(RepeaterEntity::new, "repeater");
//    public static final RegistryObject<EntityType<LilyPadEntity>> LILY_PAD = registerPlantEntityType(LilyPadEntity::new, "lily_pad");
//    public static final RegistryObject<EntityType<SquashEntity>> SQUASH = registerPlantEntityType(SquashEntity::new, "squash");
//    public static final RegistryObject<EntityType<ThreePeaterEntity>> THREE_PEATER = registerPlantEntityType(ThreePeaterEntity::new, "three_peater");
//    public static final RegistryObject<EntityType<TangleKelpEntity>> TANGLE_KELP = registerPlantEntityType(TangleKelpEntity::new, "tangle_kelp");
//    public static final RegistryObject<EntityType<JalapenoEntity>> JALAPENO = registerPlantEntityType(JalapenoEntity::new, "jalapeno");
//    public static final RegistryObject<EntityType<SpikeWeedEntity>> SPIKE_WEED = registerPlantEntityType(SpikeWeedEntity::new, "spike_weed");
//    public static final RegistryObject<EntityType<TorchWoodEntity>> TORCH_WOOD = registerPlantEntityType(TorchWoodEntity::new, "torch_wood");
//    public static final RegistryObject<EntityType<TallNutEntity>> TALL_NUT = registerPlantEntityType(TallNutEntity::new, "tall_nut");
//    public static final RegistryObject<EntityType<PuffShroomEntity>> PUFF_SHROOM = registerPlantEntityType(PuffShroomEntity::new, "puff_shroom");
//    public static final RegistryObject<EntityType<SunShroomEntity>> SUN_SHROOM = registerPlantEntityType(SunShroomEntity::new, "sun_shroom");
//    public static final RegistryObject<EntityType<FumeShroomEntity>> FUME_SHROOM = registerPlantEntityType(FumeShroomEntity::new, "fume_shroom");
//    public static final RegistryObject<EntityType<GraveBusterEntity>> GRAVE_BUSTER = registerPlantEntityType(GraveBusterEntity::new, "grave_buster");
//    public static final RegistryObject<EntityType<HypnoShroomEntity>> HYPNO_SHROOM = registerPlantEntityType(HypnoShroomEntity::new, "hypno_shroom");
//    public static final RegistryObject<EntityType<ScaredyShroomEntity>> SCAREDY_SHROOM = registerPlantEntityType(ScaredyShroomEntity::new, "scaredy_shroom");
//    public static final RegistryObject<EntityType<IceShroomEntity>> ICE_SHROOM = registerPlantEntityType(IceShroomEntity::new, "ice_shroom");
//    public static final RegistryObject<EntityType<DoomShroomEntity>> DOOM_SHROOM = registerPlantEntityType(DoomShroomEntity::new, "doom_shroom");
//    public static final RegistryObject<EntityType<SeaShroomEntity>> SEA_SHROOM = registerPlantEntityType(SeaShroomEntity::new, "sea_shroom");
//    public static final RegistryObject<EntityType<SplitPeaEntity>> SPLIT_PEA = registerPlantEntityType(SplitPeaEntity::new, "split_pea");
//    public static final RegistryObject<EntityType<CoffeeBeanEntity>> COFFEE_BEAN = registerPlantEntityType(CoffeeBeanEntity::new, "coffee_bean");
//    public static final RegistryObject<EntityType<MariGoldEntity>> MARIGOLD = registerPlantEntityType(MariGoldEntity::new, "marigold");
//    public static final RegistryObject<EntityType<GatlingPeaEntity>> GATLING_PEA = registerPlantEntityType(GatlingPeaEntity::new, "gatling_pea");
//    public static final RegistryObject<EntityType<TwinSunFlowerEntity>> TWIN_SUNFLOWER = registerPlantEntityType(TwinSunFlowerEntity::new, "twin_sunflower");
//    public static final RegistryObject<EntityType<WaterGuardEntity>> WATER_GUARD = registerPlantEntityType(WaterGuardEntity::new, "water_guard");
//    public static final RegistryObject<EntityType<PumpkinEntity>> PUMPKIN = registerPlantEntityType(PumpkinEntity::new, "pumpkin");
//    public static final RegistryObject<EntityType<PlanternEntity>> PLANTERN = registerPlantEntityType(PlanternEntity::new, "plantern");
//    public static final RegistryObject<EntityType<MagnetShroomEntity>> MAGNET_SHROOM = registerPlantEntityType(MagnetShroomEntity::new, "magnet_shroom");
//    public static final RegistryObject<EntityType<CatTailEntity>> CAT_TAIL = registerPlantEntityType(CatTailEntity::new, "cat_tail");
//    public static final RegistryObject<EntityType<StrangeCatEntity>> STRANGE_CAT = registerPlantEntityType(StrangeCatEntity::new, "strange_cat");
//    public static final RegistryObject<EntityType<StarFruitEntity>> STAR_FRUIT = registerPlantEntityType(StarFruitEntity::new, "star_fruit");
//    public static final RegistryObject<EntityType<AngelStarFruitEntity>> ANGEL_STAR_FRUIT = registerPlantEntityType(AngelStarFruitEntity::new, "angel_star_fruit");
//    public static final RegistryObject<EntityType<CactusEntity>> CACTUS = registerPlantEntityType(CactusEntity::new, "cactus");
//    public static final RegistryObject<EntityType<BloverEntity>> BLOVER = registerPlantEntityType(BloverEntity::new, "blover");
//    public static final RegistryObject<EntityType<GloomShroomEntity>> GLOOM_SHROOM = registerPlantEntityType(GloomShroomEntity::new, "gloom_shroom");
//    public static final RegistryObject<EntityType<GoldMagnetEntity>> GOLD_MAGNET = registerPlantEntityType(GoldMagnetEntity::new, "gold_magnet");
//    public static final RegistryObject<EntityType<GoldLeafEntity>> GOLD_LEAF = registerPlantEntityType(GoldLeafEntity::new, "gold_leaf");
//    public static final RegistryObject<EntityType<FlowerPotEntity>> FLOWER_POT = registerPlantEntityType(FlowerPotEntity::new, "flower_pot");
//    public static final RegistryObject<EntityType<CabbagePultEntity>> CABBAGE_PULT = registerPlantEntityType(CabbagePultEntity::new, "cabbage_pult");
//    public static final RegistryObject<EntityType<KernelPultEntity>> KERNEL_PULT = registerPlantEntityType(KernelPultEntity::new, "kernel_pult");
//    public static final RegistryObject<EntityType<ButterPultEntity>> BUTTER_PULT = registerPlantEntityType(ButterPultEntity::new, "butter_pult");
//    public static final RegistryObject<EntityType<GarlicEntity>> GARLIC = registerPlantEntityType(GarlicEntity::new, "garlic");
//    public static final RegistryObject<EntityType<UmbrellaLeafEntity>> UMBRELLA_LEAF = registerPlantEntityType(UmbrellaLeafEntity::new, "umbrella_leaf");
//    public static final RegistryObject<EntityType<MelonPultEntity>> MELON_PULT = registerPlantEntityType(MelonPultEntity::new, "melon_pult");
//    public static final RegistryObject<EntityType<WinterMelonEntity>> WINTER_MELON = registerPlantEntityType(WinterMelonEntity::new, "winter_melon");
//    public static final RegistryObject<EntityType<BambooLordEntity>> BAMBOO_LORD = registerPlantEntityType(BambooLordEntity::new, "bamboo_lord");
//    public static final RegistryObject<EntityType<IcebergLettuceEntity>> ICEBERG_LETTUCE = registerPlantEntityType(IcebergLettuceEntity::new, "iceberg_lettuce");
//    public static final RegistryObject<EntityType<SpikeRockEntity>> SPIKE_ROCK = registerPlantEntityType(SpikeRockEntity::new, "spike_rock");
//    public static final RegistryObject<EntityType<BonkChoyEntity>> BONK_CHOY = registerPlantEntityType(BonkChoyEntity::new, "bonk_choy");
//    public static final RegistryObject<EntityType<ImitaterEntity>> IMITATER = registerPlantEntityType(ImitaterEntity::new, "imitater");
//    public static final RegistryObject<EntityType<CobCannonEntity>> COB_CANNON = registerPlantEntityType(CobCannonEntity::new, "cob_cannon");


    public static void addEntityAttributes(EntityAttributeCreationEvent ev) {
        PAZTypes.postInit();
        //init all plants and zombies attributes.
        PVZAPI.get().getPAZTypes().forEach(type -> {
            type.getEntityType().ifPresent(entityType -> {
                ev.put(entityType, PVZPAZ.createPAZAttributes().build());
            });
        });
        //others.
        ev.put(GRASS_CARP.get(), GrassCarp.createAttributes().build());
//        Arrays.asList(
//                CRAZY_DAVE.get(), SUN_DAVE.get(), PANNEY.get(),
//                FOODIE_ZOMBIE.get()
//        ).forEach(obj -> {
//            ev.put(obj.get(), Mob.createMobAttributes().build());
//        });
    }

    private static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(EntityType.EntityFactory factory, String name, MobCategory classification){
        return ENTITY_TYPES.register(name, () -> {return EntityType.Builder.of(factory, classification).build(Util.prefix(name).toString());});
    }

//    private static <T extends PVZZombieEntity> RegistryObject<EntityType<T>> registerZombieEntityType(IFactory<T> factory, String name){
//        return ENTITY_TYPES.register(name, () -> {return EntityType.Builder.of(factory, PVZEntityClassifications.PVZ_ZOMBIE).fireImmune().build(StringUtil.prefix(name).toString());});
//    }

    private static <T extends PVZPlant> RegistryObject<EntityType<T>> registerPlantEntityType(EntityType.EntityFactory factory, String name){
        return ENTITY_TYPES.register(name, () -> {return EntityType.Builder.of(factory, PVZMobCategories.PVZ_PLANT).build(Util.prefix(name).toString());});
    }

}
