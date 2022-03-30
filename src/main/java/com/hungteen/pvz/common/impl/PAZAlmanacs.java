package com.hungteen.pvz.common.impl;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import net.minecraft.network.chat.TranslatableComponent;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-30 13:00
 **/
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
        return new TranslatableComponent("almanac.pvz." + this.toString().toLowerCase()).getString();
    }
}
