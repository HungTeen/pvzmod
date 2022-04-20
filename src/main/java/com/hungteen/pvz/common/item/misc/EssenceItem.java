package com.hungteen.pvz.common.item.misc;

import com.hungteen.pvz.api.types.IEssenceType;
import com.hungteen.pvz.common.item.base.PVZMiscItem;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 22:36
 **/
public class EssenceItem extends PVZMiscItem {

    public final IEssenceType essence;

    public EssenceItem(IEssenceType essence) {
        this.essence = essence;
    }

}
