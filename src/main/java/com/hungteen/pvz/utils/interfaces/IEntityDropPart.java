package com.hungteen.pvz.utils.interfaces;

import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.api.types.base.IPAZType;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-05 08:27
 **/
public interface IEntityDropPart {

    IPAZType getPAZType();

    String getDropPartType();

    boolean hasHandDefence();

    boolean isMini();

    int getAnimTime();

}
