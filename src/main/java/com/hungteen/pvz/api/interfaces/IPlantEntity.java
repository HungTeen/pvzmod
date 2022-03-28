package com.hungteen.pvz.api.interfaces;

import com.hungteen.pvz.api.types.IPlantType;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-27 16:53
 **/
public interface IPlantEntity extends IPAZEntity {

    IPlantType getPlantType();

}
