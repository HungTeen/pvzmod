package com.hungteen.pvz.common.item;

import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-19 10:04
 *
 * {@link Foods}
 **/
public class PVZFoods {

    public static final FoodProperties FAKE_BRAIN = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.4F).build();
    public static final FoodProperties COOKED_BRAIN = (new FoodProperties.Builder()).nutrition(7).saturationMod(0.8f).build();

//    public static final FoodProperties REAL_BRAIN = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.5F).effect(new MobEffectInstance(MobEffects.CONFUSION, 200, 1), 1.0f).build();
//    public static final FoodProperties CANDY = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.4F).build();
//    public static final FoodProperties CHOCOLATE = (new FoodProperties.Builder()).nutrition(3).saturationMod(1F).build();
//    public static final FoodProperties PEA_SOUP = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.6f).build();
    public static final FoodProperties CABBAGE = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.5f).build();
    public static final FoodProperties CORN = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.4f).build();
//    public static final FoodProperties POP_CORN = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.5f).build();
//    public static final FoodProperties TACOS = (new FoodProperties.Builder()).nutrition(8).saturationMod(1f).effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1), 1.0f)
//            .effect(new MobEffectInstance(MobEffects.JUMP, 200, 1), 1.0f).effect(new MobEffectInstance(MobEffects.DIG_SPEED, 200, 1), 1.0f).effect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1), 1.0f).build();

}
