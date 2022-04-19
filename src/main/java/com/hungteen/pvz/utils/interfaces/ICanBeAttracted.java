package com.hungteen.pvz.utils.interfaces;

import com.hungteen.pvz.utils.interfaces.ICanAttract;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-01 17:48
 *
 * use to check can entity be attracted by Defence Plants like WallNut.
 **/
public interface ICanBeAttracted {

    /**
     * can be attracted by the specific defender.
     */
    boolean canBeAttractedBy(ICanAttract defender);

    /**
     * run when be attracted.
     */
    void attractBy(ICanAttract defender);

}
