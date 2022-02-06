package com.hungteen.pvz.utils.enums;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import net.minecraft.util.text.TranslationTextComponent;

public enum PAZAlmanacs implements IAlmanacEntry {

    HEALTH,
    SUN_COST,
    COOL_DOWN,
    BULLET_DAMAGE,
    SHOOT_CD,
    SHOOT_RANGE,
    COLD_LEVEL,
    COLD_TIME,
    GEN_CD,
    GEN_SUN_AMOUNT,
    SMALL_GEN_SUN_AMOUNT,
    ARMOR,
    ARMOR_TOUGHNESS,
    EXPLODE_DAMAGE,
    EXPLODE_RANGE,
    PREPARE_CD,
    ATTACK_DAMAGE,
    REST_TIME,
    WORK_TIME,
    FROZEN_LEVEL,
    FROZEN_TIME,
    AGAIN_CHANCE,
    SPIKE_COUNT,
    ATTACK_CD,
    HEAT_PEA_RANGE,
    ATTACK_RANGE,
    WORK_CD,
    WORK_RANGE,
    AWAKE_TIME,
    EFFECT_TIME
    ;

    @Override
    public String getText() {
        return new TranslationTextComponent("almanac.pvz." + this.toString().toLowerCase()).getString();
    }
}
