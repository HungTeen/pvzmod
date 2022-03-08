package com.hungteen.pvz.common.potion;

import java.util.UUID;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.utils.enums.Colors;

import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.effect.Effect;
import net.minecraft.world.effect.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectRegister {

	public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS,
			PVZMod.MOD_ID);

	public static final UUID COLD_EFFECT_UUID = UUID.fromString("968019bc-e212-11ea-87d0-0242ac130003");
	public static final UUID FROZEN_EFFECT_UUID = UUID.fromString("293e07aa-e213-11ea-87d0-0242ac130003");
	public static final UUID EXCITE_EFFECT_UUID = UUID.fromString("a211dbca-19f9-11eb-adc1-0242ac120002");
	public static final UUID LIGHT_EYE_EFFECT_UUID = UUID.fromString("aa7a51c2-3e73-11eb-b378-0242ac130002");
	public static final UUID BUTTER_EFFECT_UUID = UUID.fromString("01c75056-5e45-11eb-ae93-0242ac130002");
	public static final UUID ENERGETIC_EFFECT_UUID = UUID.fromString("b46170d4-6957-11ec-90d6-0242ac120003");

	public static final RegistryObject<Effect> COLD_EFFECT = EFFECTS.register("cold", () -> {
		return new PVZEffect(EffectType.HARMFUL, Colors.IRIS_BLUE).addAttributeModifier(Attributes.MOVEMENT_SPEED,
				COLD_EFFECT_UUID.toString(), -0.05f, Operation.MULTIPLY_TOTAL);
	});

	public static final RegistryObject<Effect> FROZEN_EFFECT = EFFECTS.register("frozen", () -> {
		return new PVZEffect(EffectType.HARMFUL, Colors.ELECTRIC_BLUE).addAttributeModifier(Attributes.MOVEMENT_SPEED,
				FROZEN_EFFECT_UUID.toString(), -1f, Operation.MULTIPLY_TOTAL);
	});

	public static final RegistryObject<Effect> EXCITE_EFFECT = EFFECTS.register("excite", () -> {
		return new PVZEffect(EffectType.BENEFICIAL, Colors.GOLD_YELLOW);
	});

	public static final RegistryObject<Effect> LIGHT_EYE_EFFECT = EFFECTS.register("light_eye", () -> {
		return new PVZEffect(EffectType.BENEFICIAL, Colors.LITTLE_YELLOW1);
	});

	public static final RegistryObject<Effect> BUTTER_EFFECT = EFFECTS.register("butter", () -> {
		return new PVZEffect(EffectType.HARMFUL, Colors.LITTLE_YELLOW1).addAttributeModifier(Attributes.MOVEMENT_SPEED,
				BUTTER_EFFECT_UUID.toString(), -1f, Operation.MULTIPLY_TOTAL);
	});

	public static final RegistryObject<Effect> ENERGETIC_EFFECT = EFFECTS.register("energetic", () -> {
		return new PVZEffect(EffectType.BENEFICIAL, Colors.CREEPER_GREEN);
	});

}
