package com.hungteen.pvz.api.types;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-26 15:28
 **/
public interface ICDType {

    /**
     * get cool down by level.
     */
    int getCD(int lvl);

    String getTranslateKey();

}
