package com.hungteen.pvz.common.entity;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.entity.drop.*;
import com.hungteen.pvz.common.entity.effect.OriginEffectEntity;
import com.hungteen.pvz.utils.Util;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;

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
     * drop entity.
     */
    public static final RegistryObject<EntityType<Sun>> SUN = registerEntityType(Sun::new, "sun", MobCategory.AMBIENT);
    public static final RegistryObject<EntityType<Coin>> COPPER_COIN = registerEntityType(Coin.CopperCoin::new, "copper_coin", MobCategory.MISC);
    public static final RegistryObject<EntityType<Coin>> SILVER_COIN = registerEntityType(Coin.SilverCoin::new, "silver_coin", MobCategory.MISC);
    public static final RegistryObject<EntityType<Coin>> GOLD_COIN = registerEntityType(Coin.GoldCoin::new, "gold_coin", MobCategory.MISC);
    public static final RegistryObject<EntityType<Jewel>> JEWEL = registerEntityType(Jewel::new, "jewel", MobCategory.MISC);
    public static final RegistryObject<EntityType<PlantFood>> PLANT_FOOD = registerEntityType(PlantFood::new, "plant_food", MobCategory.MISC);
    public static final RegistryObject<EntityType<OriginOrb>> ORIGIN_ORB = registerEntityType(OriginOrb::new, "origin_orb", MobCategory.MISC);
//    public static final RegistryObject<EntityType<GiftBoxEntity>> GIFT_BOX = registerEntityType(GiftBoxEntity::new, "gift_box", EntityClassification.MISC, 0.9f, 1f);

    /**
     * bullets
     */
//    public static final RegistryObject<EntityType<PeaEntity>> PEA = registerEntityType(PeaEntity::new, "pea", EntityClassification.MISC);
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


    public static void addEntityAttributes(EntityAttributeCreationEvent ev) {
//        PlantType.initPlants();
//        ZombieType.initZombies();
//        PlantType.postInitPlants();
//        ZombieType.postInitZombies();
//        //init all plants' attributes.
//        for(IPlantType p : PlantType.getPlants()) {
//            p.getEntityType().ifPresent(obj -> {
//                ev.put(obj, PVZPlantEntity.createPlantAttributes());
//            });
//        }
//        //init all zombies' attributes.
//        for(IZombieType z : ZombieType.getZombies()) {
//            z.getEntityType().ifPresent(obj -> {
//                ev.put(obj, PVZZombieEntity.createZombieAttributes());
//            });
//        }
//        Arrays.asList(
////                CRAZY_DAVE.get(), SUN_DAVE.get(), PANNEY.get(),
////                FOODIE_ZOMBIE.get()
//        ).forEach(obj -> {
//            ev.put(obj.get(), Mob.createMobAttributes().build());
//        });
    }

    /**
     * effects
     */
    public static final RegistryObject<EntityType<OriginEffectEntity>> ORIGIN_EFFECT = registerEntityType(OriginEffectEntity::new, "origin_effect", MobCategory.MISC);
//    public static final RegistryObject<EntityType<DoomFixerEntity>> DOOM_FIXER = registerEntityType(DoomFixerEntity::new, "doom_fixer", EntityClassification.MISC);

    private static <T extends Entity> RegistryObject<EntityType<T>> registerEntityType(EntityType.EntityFactory factory, String name, MobCategory classification){
        return ENTITY_TYPES.register(name, () -> {return EntityType.Builder.of(factory, classification).build(Util.prefix(name).toString());});
    }
}
