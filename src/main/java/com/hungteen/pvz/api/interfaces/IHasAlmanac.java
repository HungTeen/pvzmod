package com.hungteen.pvz.api.interfaces;

import com.mojang.datafixers.util.Pair;

import java.util.List;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-23 10:30
 **/
public interface IHasAlmanac {

    /**
     * display in almanac.
     */
    void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list);

}
