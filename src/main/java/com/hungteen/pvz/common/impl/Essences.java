package com.hungteen.pvz.common.impl;

import java.util.List;

import com.hungteen.pvz.common.core.EssenceType;
import com.hungteen.pvz.register.ItemRegister;

public class Essences {

	public static final EssenceType ORIGIN = new EssenceType("origin", () -> ItemRegister.ORIGIN_ESSENCE.get());
	
	public static final EssenceType APPEASE = new EssenceType("appease", () -> ItemRegister.APPEASE_ESSENCE.get());
	
	public static final EssenceType LIGHT = new EssenceType("light", () -> ItemRegister.LIGHT_ESSENCE.get());
	
	public static final EssenceType EXPLOSION = new EssenceType("explosion", () -> ItemRegister.EXPLOSION_ESSENCE.get());
	
	public static final EssenceType DEFENCE = new EssenceType("defence", () -> ItemRegister.DEFENCE_ESSENCE.get());

	public static final EssenceType ICE = new EssenceType("ice", () -> ItemRegister.ICE_ESSENCE.get());
	
	public static final EssenceType ENFORCE = new EssenceType("enforce", () -> ItemRegister.ENFORCE_ESSENCE.get());
	
	public static final EssenceType TOXIC = new EssenceType("toxic", () -> ItemRegister.TOXIC_ESSENCE.get());
	
	public static final EssenceType ASSIST = new EssenceType("assist", () -> ItemRegister.ASSIST_ESSENCE.get());
	
	public static final EssenceType MAGIC = new EssenceType("magic", () -> ItemRegister.MAGIC_ESSENCE.get());
	
	public static final EssenceType FLAME = new EssenceType("flame", () -> ItemRegister.FLAME_ESSENCE.get());
	
	public static final EssenceType SPEAR = new EssenceType("spear", () -> ItemRegister.SPEAR_ESSENCE.get());
	
	public static final EssenceType ARMA = new EssenceType("arma", () -> ItemRegister.ARMA_ESSENCE.get());
	
	public static final EssenceType ELECTRIC = new EssenceType("electric", () -> ItemRegister.ELECTRIC_ESSENCE.get());
	
	public static final EssenceType SHADOW = new EssenceType("shadow", () -> ItemRegister.SHADOW_ESSENCE.get());
	
	public static List<EssenceType> getEssences(){
		return EssenceType.ESSENCES;
	}
	
//	public static Optional<Item> getEssenceItem(Essences e) {
//		if(ESSENCES.containsKey(e)) return Optional.ofNullable(ESSENCES.get(e).get());
//		System.out.println("Error: get Essence item !".);
//		return Optional.empty();
//	}
}
