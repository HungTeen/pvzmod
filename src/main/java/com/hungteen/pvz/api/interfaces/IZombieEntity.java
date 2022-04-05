package com.hungteen.pvz.api.interfaces;

import com.hungteen.pvz.api.types.IZombieType;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-04 19:20
 **/
public interface IZombieEntity extends IPAZEntity {

    IZombieType getZombieType();
}
