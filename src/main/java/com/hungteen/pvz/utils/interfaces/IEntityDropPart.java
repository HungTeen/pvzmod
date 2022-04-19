package com.hungteen.pvz.utils.interfaces;

import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.utils.enums.DropPartTypes;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-05 08:27
 **/
public interface IEntityDropPart {

    IPAZType getOwnerType();

    DropPartTypes getDropPartType();

    boolean hasHandDefence();

    boolean isMini();

    int getAnimTime();

}
