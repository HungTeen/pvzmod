package com.hungteen.pvz.api;

import com.hungteen.pvz.api.type.IEssenceType;

import java.util.ArrayList;
import java.util.List;

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
    public void registerEssenceType(IEssenceType type) {

    }

    @Override
    public List<IEssenceType> getEssences() {
        return new ArrayList<>();
    }
}
