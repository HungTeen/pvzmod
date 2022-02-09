package com.hungteen.pvz.client.events.handler;

import com.hungteen.pvz.client.ClientProxy;
import com.hungteen.pvz.common.capability.CapabilityHandler;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-02-09 19:14
 **/
public class PVZSwitchSlotHander {

    /**
     * {@link com.hungteen.pvz.client.events.PVZInputEvents#onKeyDown(net.minecraftforge.client.event.InputEvent.KeyInputEvent)}
     * {@link com.hungteen.pvz.client.events.PVZInputEvents#onMouseScroll(net.minecraftforge.client.event.InputEvent.MouseScrollEvent)}
     */
    public static void changeCardSlot(double delta){
        ClientProxy.MC.player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent(l -> {
            if(delta == 0) {
                l.getPlayerData().onSwitchCard();
            } else {
                l.getPlayerData().onScrollInventory(delta);
            }
        });
    }

}
