package com.hungteen.pvz.common.datapack;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.impl.PlantType;
import net.minecraftforge.event.AddReloadListenerEvent;

public class DataPackRegister {

    /**
     * {@link PVZMod#PVZMod()}
     */
    public static void addReloadListenerEvent(AddReloadListenerEvent event) {
        //TODO 自定义阳光等属性
        event.addListener(new PlantType.PlantTypeLoader());
    }

}
