package com.hungteen.pvz.common.impl;

import com.google.common.collect.ImmutableList;
import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.type.IEssenceType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 20:53
 *
 * the real api for pvz mod.
 **/
public class PVZAPIImpl implements PVZAPI.IPVZAPI {

    private static final List<IEssenceType> ESSENCES = new ArrayList<>();

    @Override
    public void registerEssenceType(IEssenceType type) {
        if (! ESSENCES.contains(type)) {
            ESSENCES.add(type);
        }
    }

    @Override
    public List<IEssenceType> getEssences() {
        return Collections.unmodifiableList(ESSENCES);
    }

}
