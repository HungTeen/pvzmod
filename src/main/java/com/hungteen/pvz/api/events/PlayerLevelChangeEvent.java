package com.hungteen.pvz.api.events;

import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 17:21
 *
 * {@link com.hungteen.pvz.common.capability.player.PlayerDataManager#addResource(Resources, int)}
 **/
public class PlayerLevelChangeEvent extends PlayerEvent {

    private final int oldLevel;
    private final int newLevel;

    public PlayerLevelChangeEvent(Player player, int oldlevel, int newLevel) {
        super(player);
        this.oldLevel = oldlevel;
        this.newLevel = newLevel;
    }

    public int getCurrentLevel() {
        return this.newLevel;
    }

    public int getOldLevel(){return this.oldLevel;}

    public boolean isLevelUp(){
        return this.getCurrentLevel() > this.getOldLevel();
    }

}
