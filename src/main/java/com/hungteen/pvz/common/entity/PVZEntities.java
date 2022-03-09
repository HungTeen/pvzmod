package com.hungteen.pvz.common.entity;

import com.hungteen.pvz.PVZMod;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-09 14:04
 **/
public class PVZEntities {

    /**
     * {@link PVZMod#deferredRegister(IEventBus)}
     */
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =  DeferredRegister.create(ForgeRegistries.ENTITIES, PVZMod.MOD_ID);

    /*
     * drop entity.
     */
//    public static final RegistryObject<EntityType<SunEntity>> SUN = registerEntityType(SunEntity::new, "sun", EntityClassification.AMBIENT);
//    public static final RegistryObject<EntityType<CoinEntity>> COIN = registerEntityType(CoinEntity::new, "coin", EntityClassification.MISC);
//    public static final RegistryObject<EntityType<JewelEntity>> JEWEL = registerEntityType(JewelEntity::new, "jewel", EntityClassification.MISC);
//    public static final RegistryObject<EntityType<EnergyEntity>> ENERGY = registerEntityType(EnergyEntity::new, "energy", EntityClassification.MISC, 0.9f, 2f);
//    public static final RegistryObject<EntityType<GiftBoxEntity>> GIFT_BOX = registerEntityType(GiftBoxEntity::new, "gift_box", EntityClassification.MISC, 0.9f, 1f);

}
