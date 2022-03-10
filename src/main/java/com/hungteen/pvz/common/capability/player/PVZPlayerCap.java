package com.hungteen.pvz.common.capability.player;

import net.minecraft.world.entity.player.Player;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 13:06
 **/
public class PVZPlayerCap implements IPVZPlayerCapability {

    private PlayerDataManager playerDataManager = null;

    @Override
    public void init(Player player) {
        if(playerDataManager == null){
            playerDataManager = new PlayerDataManager(player);
        }
    }

    @Override
    public PlayerDataManager get() {
        return playerDataManager;
    }
}
