package com.hungteen.pvz.utils.enums;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.api.enums.PVZGroupType;
import com.hungteen.pvz.common.capability.player.PlayerDataManager;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 15:49
 *
 * resources are some important statistics in pvz mod.
 **/
public enum Resources {

    /**
     * tree level is a unique level system from default one. <br>
     * all player start with level 1, and they will struggle to reach 100.
     */
    TREE_LVL(1, 200),

    /**
     * the most common currency in pvz, used to trade in shop.
     */
    MONEY(0, 9999999),

    /**
     * a special currency, much more rare than money.
     */
    GEM_NUM(0, 9999999),

    /**
     * sun amount, its upper limit will change with tree level.
     */
    SUN_NUM,

    /**
     * the upper limit of sun num.
     */
    MAX_SUN_NUM(0, 100000),

    /**
     * energy num, many people call it plant food amount.
     */
    ENERGY_NUM(0, 0),

    /**
     * the upper limit of energy num.
     */
    MAX_ENERGY_NUM(1, 10),

    /**
     * the chance to use slot machine.
     */
    LOTTERY_CHANCE(0, 9999999),

    /**
     * player group type. <br>
     * @see PVZGroupType
     */
    GROUP_TYPE(- 2, 2),

//    /* mission use */
//    MISSION_FINISH_TIME(0, 9999999),
//    MISSION_TYPE(0, 3),
//    MISSION_STAGE(0, 4),
//    MISSION_VALUE(0, 9999999)
    ;

    /**
     * {@link PlayerDataManager#PlayerDataManager(Player)} )}
     */
    public static int getInitialValue(Resources res) {
        switch (res) {
            case SUN_NUM: return 50;
            case LOTTERY_CHANCE: return 10;
            case GROUP_TYPE: return PVZConfig.getPlayerInitialGroup();
            case MAX_SUN_NUM: return PVZConfig.getPlayerInitialMaxSun();
            default: return res.min;
        }
    }

    public final int min;
    public final int max;

    Resources(int min, int max) {
        this.min = min;
        this.max = max;
    }

    Resources(){
        this.min = 0;
        this.max = 0;
    }

    public TranslatableComponent getText() {
        return new TranslatableComponent("resource.pvz." + this.toString().toLowerCase());
    }

}
