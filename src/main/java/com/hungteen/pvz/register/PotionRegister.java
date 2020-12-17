package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionRegister {

	public static final DeferredRegister<Potion> POTIONS = new DeferredRegister<>(ForgeRegistries.POTION_TYPES, PVZMod.MOD_ID);

	public static final RegistryObject<Potion> EXCITE_POTION_1 = POTIONS.register("excite_potion_1", () -> {return new Potion(new EffectInstance(EffectRegister.EXCITE_EFFECT.get(), 600, 0));});
	public static final RegistryObject<Potion> EXCITE_POTION_2 = POTIONS.register("excite_potion_2", () -> {return new Potion(new EffectInstance(EffectRegister.EXCITE_EFFECT.get(), 1800, 1));});
	public static final RegistryObject<Potion> EXCITE_POTION_3 = POTIONS.register("excite_potion_3", () -> {return new Potion(new EffectInstance(EffectRegister.EXCITE_EFFECT.get(), 3600, 0));});
	public static final RegistryObject<Potion> LIGHT_EYE_POTION_1 = POTIONS.register("light_eye_potion_1", () -> {return new Potion(new EffectInstance(EffectRegister.LIGHT_EYE_EFFECT.get(), 1200, 0));});
	public static final RegistryObject<Potion> LIGHT_EYE_POTION_2 = POTIONS.register("light_eye_potion_2", () -> {return new Potion(new EffectInstance(EffectRegister.LIGHT_EYE_EFFECT.get(), 9600, 0));});

}
