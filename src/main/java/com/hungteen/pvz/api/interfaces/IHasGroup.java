package com.hungteen.pvz.api.interfaces;

import com.hungteen.pvz.api.enums.PVZGroupType;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-27 16:06
 **/
public interface IHasGroup {

    /**
     * get the group of entity.
     */
    PVZGroupType getGroupType();

}
