package com.hungteen.pvz.utils.enums;

import java.util.EnumMap;

import com.hungteen.pvz.register.ItemRegister;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public enum Essences {

	ORIGIN,
	APPEASE,
	LIGHT,
	EXPLOSION,
	DEFENCE,
	ICE,
	ENFORCE,
	TOXIC,
	ASSIST,
	MAGIC,
	FLAME,
	SPEAR,
	ARMA,
	ELECTRIC,
	SHADOW;
	
	public static final EnumMap<Essences, RegistryObject<? extends Item>> ESSENCES = new EnumMap<>(Essences.class);
	
	static {
		ESSENCES.put(ORIGIN, ItemRegister.ORIGIN_ESSENCE);
		ESSENCES.put(APPEASE, ItemRegister.APPEASE_ESSENCE);
		ESSENCES.put(LIGHT, ItemRegister.LIGHT_ESSENCE);
		ESSENCES.put(EXPLOSION, ItemRegister.EXPLOSION_ESSENCE);
		ESSENCES.put(DEFENCE, ItemRegister.DEFENCE_ESSENCE);
		ESSENCES.put(ICE, ItemRegister.ICE_ESSENCE);
		ESSENCES.put(ENFORCE, ItemRegister.ENFORCE_ESSENCE);
		ESSENCES.put(TOXIC, ItemRegister.TOXIC_ESSENCE);
		ESSENCES.put(ASSIST, ItemRegister.ASSIST_ESSENCE);
		ESSENCES.put(MAGIC, ItemRegister.MAGIC_ESSENCE);
		ESSENCES.put(FLAME, ItemRegister.FLAME_ESSENCE);
		ESSENCES.put(SPEAR, ItemRegister.SPEAR_ESSENCE);
		ESSENCES.put(ARMA, ItemRegister.ARMA_ESSENCE);
		ESSENCES.put(ELECTRIC, ItemRegister.ELECTRIC_ESSENCE);
		ESSENCES.put(SHADOW, ItemRegister.SHADOW_ESSENCE);
	}
	
	public static Item getEssenceItem(Essences e) {
		if(ESSENCES.containsKey(e)) {
			return ESSENCES.get(e).get();
		}
		System.out.println("Error: get Essence item !");
		return null;
	}
}
