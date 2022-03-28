package com.hungteen.pvz.common.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.hungteen.pvz.api.PVZAPI;
import com.hungteen.pvz.api.types.IEssenceType;
import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.impl.type.PAZTypes;
import com.hungteen.pvz.utils.Util;

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
    public void registerPAZType(IPAZType type) {
        PAZTypes.registerPAZType(type);
    }

    @Override
    public Optional<IPAZType> getPAZType(String identity) {
        return PAZTypes.getPAZType(identity);
    }

    @Override
    public List<IPAZType> getPAZTypes() {
        return PAZTypes.getPAZs();
    }

    @Override
    public void registerEssenceType(IEssenceType type) {
        if (! ESSENCES.contains(type)) {
            ESSENCES.add(type);
        } else{
            Util.warn("Essence Register : Duplicate Type !");
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
        } else{
            Util.warn("Rank Register : Duplicate Type !");
        }
    }

    @Override
    public List<IRankType> getRanks() {
        return Collections.unmodifiableList(RANKS);
    }

}
