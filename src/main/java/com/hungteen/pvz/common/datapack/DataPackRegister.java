package com.hungteen.pvz.common.datapack;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.impl.PlantType;
import net.minecraftforge.event.AddReloadListenerEvent;

public class DataPackRegister {

    /**
     * {@link PVZMod#PVZMod()}
     */
    public static void addReloadListenerEvent(AddReloadListenerEvent event) {
        event.addListener(new PlantType.PlantTypeLoader());
        event.addListener(new LotteryTypeLoader());
        event.addListener(new InvasionTypeLoader());
    }

}
