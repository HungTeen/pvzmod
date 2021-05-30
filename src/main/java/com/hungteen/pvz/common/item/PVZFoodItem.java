package com.hungteen.pvz.common.item;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

@SuppressWarnings("deprecation")
public class PVZFoodItem extends Item{

	public static final Food FAKE_BRAIN = (new Food.Builder()).nutrition(7).saturationMod(0.7F).build();
	
	public static final Food REAL_BRAIN = (new Food.Builder()).nutrition(6).saturationMod(0.5F).effect(new EffectInstance(Effects.CONFUSION, 200, 1), 1.0f).build();
	public static final Food CANDY = (new Food.Builder()).nutrition(1).saturationMod(0.4F).build();
	public static final Food CHOCOLATE = (new Food.Builder()).nutrition(3).saturationMod(1F).build();
	public static final Food PEA_SOUP = (new Food.Builder()).nutrition(5).saturationMod(0.6f).build();
	public static final Food COOKED_BRAIN = (new Food.Builder()).nutrition(7).saturationMod(1.0f).build();
	public static final Food CABBAGE = (new Food.Builder()).nutrition(3).saturationMod(0.5f).build();
	public static final Food CORN = (new Food.Builder()).nutrition(4).saturationMod(0.4f).build();
	public static final Food POP_CORN = (new Food.Builder()).nutrition(5).saturationMod(0.5f).build();
	public static final Food TACOS = (new Food.Builder()).nutrition(8).saturationMod(1f).effect(new EffectInstance(Effects.MOVEMENT_SPEED, 200, 1), 1.0f)
			.effect(new EffectInstance(Effects.JUMP, 200, 1), 1.0f).effect(new EffectInstance(Effects.DIG_SPEED, 200, 1), 1.0f).effect(new EffectInstance(Effects.REGENERATION, 200, 1), 1.0f).build();
	
	public PVZFoodItem(Food food) {
		super(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(food));
	}
	
}
