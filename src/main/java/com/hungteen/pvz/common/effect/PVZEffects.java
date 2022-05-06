package com.hungteen.pvz.common.effect;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.entity.PVZAttributes;
import com.hungteen.pvz.utils.enums.Colors;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.UUID;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-28 13:18
 **/
public class PVZEffects {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, PVZMod.MOD_ID);

    public static final UUID COLD_EFFECT_SPEED_UUID = UUID.fromString("968019bc-e212-11ea-87d0-0242ac130003");
    public static final UUID FROZEN_EFFECT_UUID = UUID.fromString("293e07aa-e213-11ea-87d0-0242ac130003");
//    public static final UUID LIGHT_EYE_EFFECT_UUID = UUID.fromString("aa7a51c2-3e73-11eb-b378-0242ac130002");
//    public static final UUID BUTTER_EFFECT_UUID = UUID.fromString("01c75056-5e45-11eb-ae93-0242ac130002");

    public static final RegistryObject<MobEffect> COLD_EFFECT = EFFECTS.register("cold", () -> {
        return new PVZMobEffect(MobEffectCategory.HARMFUL, Colors.IRIS_BLUE)
                .addAttributeModifier(Attributes.MOVEMENT_SPEED, COLD_EFFECT_SPEED_UUID.toString(), -0.05f, AttributeModifier.Operation.MULTIPLY_TOTAL)
                .addAttributeModifier(PVZAttributes.WORK_CD.get(), "b46170d4-6957-11ec-90d6-0242ac120003", 0.05F, AttributeModifier.Operation.MULTIPLY_TOTAL);
    });

    public static final RegistryObject<MobEffect> FROZEN_EFFECT = EFFECTS.register("frozen", () -> {
        return new PVZMobEffect(MobEffectCategory.HARMFUL, Colors.ELECTRIC_BLUE).addAttributeModifier(Attributes.MOVEMENT_SPEED,
                FROZEN_EFFECT_UUID.toString(), -1f, AttributeModifier.Operation.MULTIPLY_TOTAL);
    });

    public static final RegistryObject<MobEffect> EXCITE_EFFECT = EFFECTS.register("excite", () -> {
        return new PVZMobEffect(MobEffectCategory.BENEFICIAL, Colors.GOLD_YELLOW)
                .addAttributeModifier(PVZAttributes.WORK_CD.get(), "a211dbca-19f9-11eb-adc1-0242ac120002", -0.1, AttributeModifier.Operation.MULTIPLY_TOTAL);
    });

//    public static final RegistryObject<MobEffect> LIGHT_EYE_EFFECT = EFFECTS.register("light_eye", () -> {
//        return new PVZEffect(EffectType.BENEFICIAL, Colors.LITTLE_YELLOW1);
//    });
//
//    public static final RegistryObject<MobEffect> BUTTER_EFFECT = EFFECTS.register("butter", () -> {
//        return new PVZEffect(EffectType.HARMFUL, Colors.LITTLE_YELLOW1).addAttributeModifier(Attributes.MOVEMENT_SPEED,
//                BUTTER_EFFECT_UUID.toString(), -1f, Operation.MULTIPLY_TOTAL);
//    });

    public static final RegistryObject<MobEffect> ENERGETIC_EFFECT = EFFECTS.register("energetic", () -> {
        return new PVZMobEffect(MobEffectCategory.BENEFICIAL, Colors.CREEPER_GREEN);
    });

}
