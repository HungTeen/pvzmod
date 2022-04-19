package com.hungteen.pvz.api;

import com.hungteen.pvz.api.types.ICardType;
import com.hungteen.pvz.api.types.IEssenceType;
import com.hungteen.pvz.api.types.IRankType;
import com.hungteen.pvz.api.types.base.IPAZType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 20:46
 *
 * the fake api for pvz, often be used to avoid strange mistake when {@link PVZAPI#get()}
 **/
public class DummyAPI implements PVZAPI.IPVZAPI {

    public static final PVZAPI.IPVZAPI INSTANCE = new DummyAPI();


    @Override
    public void registerPAZType(IPAZType type) {
    }

    @Override
    public Optional<IPAZType> getPAZType(String identity) {
        return Optional.empty();
    }

    @Override
    public List<IPAZType> getPAZTypes() {
        return new ArrayList<>();
    }

    @Override
    public void registerEssenceType(IEssenceType type) {
    }

    @Override
    public List<IEssenceType> getEssenceTypes() {
        return new ArrayList<>();
    }

    @Override
    public void registerRankType(IRankType type) {
    }

    @Override
    public void registerCardType(ICardType type) {

    }

    @Override
    public List<IRankType> getRankTypes() {
        return new ArrayList<>();
    }

    @Override
    public List<ICardType> getCardTypes() {
        return new ArrayList<>();
    }
}
