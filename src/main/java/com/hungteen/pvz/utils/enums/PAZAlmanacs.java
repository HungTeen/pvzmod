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
    COLD_TIME
    ;

    @Override
    public String getText() {
        return new TranslationTextComponent("almanac.pvz." + this.toString().toLowerCase()).getString();
    }
}
