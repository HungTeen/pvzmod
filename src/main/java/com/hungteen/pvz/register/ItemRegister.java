package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.items.PVZItemBase;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegister {

    public static final DeferredRegister<Item> ITEMS =new DeferredRegister<>(ForgeRegistries.ITEMS, PVZMod.MOD_ID);
	
    //ingot essence
    public static final RegistryObject<Item> OriginEssence = ITEMS.register("origin_essence", PVZItemBase::new);
    public static final RegistryObject<Item> AppeaseEssence = ITEMS.register("appease_essence", PVZItemBase::new);
    public static final RegistryObject<Item> LightEssence = ITEMS.register("light_essence", PVZItemBase::new);
    public static final RegistryObject<Item> ExplosionEssence = ITEMS.register("explosion_essence", PVZItemBase::new);
    public static final RegistryObject<Item> DefenceEssence = ITEMS.register("defence_essence", PVZItemBase::new);
    public static final RegistryObject<Item> IceEssence = ITEMS.register("ice_essence", PVZItemBase::new);
    public static final RegistryObject<Item> EnforceEssence = ITEMS.register("enforce_essence", PVZItemBase::new);
    public static final RegistryObject<Item> ToxicEssence = ITEMS.register("toxic_essence", PVZItemBase::new);
    public static final RegistryObject<Item> AssistEssence = ITEMS.register("assist_essence", PVZItemBase::new);
    public static final RegistryObject<Item> MagicEssence = ITEMS.register("magic_essence", PVZItemBase::new);
    public static final RegistryObject<Item> FlameEssence = ITEMS.register("flame_essence", PVZItemBase::new);
    public static final RegistryObject<Item> SpearEssence = ITEMS.register("spear_essence", PVZItemBase::new);
    public static final RegistryObject<Item> ArmaEssence = ITEMS.register("arma_essence", PVZItemBase::new);
    public static final RegistryObject<Item> ElectricEssence = ITEMS.register("electric_essence", PVZItemBase::new);
    public static final RegistryObject<Item> ShadowEssence = ITEMS.register("shadow_essence", PVZItemBase::new);
    public static final RegistryObject<Item> AmethystIngot = ITEMS.register("amethyst_ingot", PVZItemBase::new);
    
	public static final RegistryObject<Item> Pea = ITEMS.register("pea", PVZItemBase::new);
}
