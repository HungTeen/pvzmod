package com.hungteen.pvz.utils;

import com.hungteen.pvz.api.types.IPAZType;

/**
 * utilities about plants and zombies.
 */
public class PAZUtil {

    public static int getPAZLevelUpXp(IPAZType plant, int lvl){
        if(lvl == plant.getMaxLevel()) {
            return 999999999;
        }
        if(lvl <= 18) {
            int now = (lvl - 1) / 2;
            return 10 + 5 * now;
        }
        return 60;
    }

}
