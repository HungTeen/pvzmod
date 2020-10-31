package com.hungteen.pvz.item;


import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class PVZFoods {

	public static final Food FAKE_BRAIN = (new Food.Builder()).hunger(7).saturation(0.7F).build();
	@SuppressWarnings("deprecation")
	public static final Food REAL_BRAIN = (new Food.Builder()).hunger(6).saturation(0.5F).effect(new EffectInstance(Effects.NAUSEA, 200, 1), 1.0f).build();
	public static final Food CANDY = (new Food.Builder()).hunger(1).saturation(0.4F).build();
	public static final Food CHOCOLATE = (new Food.Builder()).hunger(3).saturation(2F).build();
}
