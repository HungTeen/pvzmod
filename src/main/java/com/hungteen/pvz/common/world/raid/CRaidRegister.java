package com.hungteen.pvz.common.world.raid;

import com.hungteen.pvz.api.PVZAPI;

public class CRaidRegister {

    public static void register(){
        PVZAPI.get().registerRaidType(PVZRaidComponent.NAME, PVZRaidComponent.class);
    }
}
