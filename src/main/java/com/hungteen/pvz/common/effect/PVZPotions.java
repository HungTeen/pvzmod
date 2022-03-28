package com.hungteen.pvz.common.effect;

import com.hungteen.pvz.PVZMod;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-28 13:22
 **/
public class PVZPotions {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, PVZMod.MOD_ID);

//    public static final RegistryObject<Potion> EXCITE_POTION_1 = POTIONS.register("excite_potion_1", () -> new Potion(new MobEffectInstance(PVZEffects.EXCITE_EFFECT.get(), 600, 0)));
//    public static final RegistryObject<Potion> EXCITE_POTION_2 = POTIONS.register("excite_potion_2", () -> new Potion(new MobEffectInstance(PVZEffects.EXCITE_EFFECT.get(), 1800, 1)));
//    public static final RegistryObject<Potion> EXCITE_POTION_3 = POTIONS.register("excite_potion_3", () -> new Potion(new MobEffectInstance(PVZEffects.EXCITE_EFFECT.get(), 3600, 0)));
//    public static final RegistryObject<Potion> LIGHT_EYE_POTION_1 = POTIONS.register("light_eye_potion_1", () -> {return new Potion(new EffectInstance(EffectRegister.LIGHT_EYE_EFFECT.get(), 1200, 0));});
//    public static final RegistryObject<Potion> LIGHT_EYE_POTION_2 = POTIONS.register("light_eye_potion_2", () -> {return new Potion(new EffectInstance(EffectRegister.LIGHT_EYE_EFFECT.get(), 9600, 0));});

}
