package com.hungteen.pvz.common.item.misc;

import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.common.item.PVZMiscItem;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-09 20:57
 **/
public class TemplateCardItem extends PVZMiscItem {

    public final IRankType Rank;

    public TemplateCardItem(IRankType rank) {
        this.Rank = rank;
    }

}
