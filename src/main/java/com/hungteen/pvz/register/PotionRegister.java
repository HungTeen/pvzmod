package com.hungteen.pvz.register;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.potion.PVZEffect;
import com.hungteen.pvz.utils.enums.Colors;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionRegister {

	public static final DeferredRegister<Effect> EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS, PVZMod.MOD_ID);
	
	public static final RegistryObject<Effect> COLD_EFFECT = EFFECTS.register("cold", ()->{
		return new PVZEffect(EffectType.HARMFUL, Colors.IRIS_BLUE)
		.addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, 
		MathHelper.getRandomUUID().toString(), -0.05f, Operation.MULTIPLY_TOTAL);
	});
	
	public static final RegistryObject<Effect> FROZEN_EFFECT = EFFECTS.register("frozen", ()->{
		return new PVZEffect(EffectType.HARMFUL, Colors.ELECTRIC_BLUE)
		.addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED,
		MathHelper.getRandomUUID().toString(), -1f, Operation.MULTIPLY_TOTAL);
	});
}
