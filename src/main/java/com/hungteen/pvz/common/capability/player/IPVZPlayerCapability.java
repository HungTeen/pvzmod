package com.hungteen.pvz.common.capability.player;

import net.minecraft.world.entity.player.Player;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 13:04
 **/
public interface IPVZPlayerCapability {

    void init(Player player);

    PlayerDataManager get();

}
