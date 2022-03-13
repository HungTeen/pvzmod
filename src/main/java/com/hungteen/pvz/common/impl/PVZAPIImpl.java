package com.hungteen.pvz.common.impl;

import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IEssenceType;
import com.hungteen.pvz.api.types.IRankType;

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
    private static final List<IRankType> RANKS = new ArrayList<>();

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

    @Override
    public void registerRankType(IRankType type) {
        if(! RANKS.contains(type)){
            RANKS.add(type);
        }
    }

    @Override
    public List<IRankType> getRanks() {
        return Collections.unmodifiableList(RANKS);
    }

}
