package com.hungteen.pvz.api.interfaces;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-30 14:37
 *
 * similar to plant food in pvz 2.
 **/
public interface ICanBeEnergetic {

    /**
     * check condition.
     */
    default boolean canBeEnergetic(){
        return true;
    }

    /**
     *
     * @param first is used for special check.
     */
    void onEnergetic(boolean first);

    /**
     * the length of super mode.
     */
    int getEnergeticDuration();
}
