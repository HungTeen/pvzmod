package com.hungteen.pvz.compat.craid;

import com.hungteen.craid.api.CRaidAPI;

public class CRaidRegister {

    public static void register(){
        CRaidAPI.get().registerRaidType(PVZRaidComponent.NAME, PVZRaidComponent.class);
    }
}
