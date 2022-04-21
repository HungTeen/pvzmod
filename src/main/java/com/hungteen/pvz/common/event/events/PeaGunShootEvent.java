package com.hungteen.pvz.common.event.events;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.api.types.base.IPAZType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-19 21:25
 **/
@Cancelable
public class PeaGunShootEvent extends Event {

    private final Player player;
    private final ItemStack stack;
    private final IPAZType mode;

    public PeaGunShootEvent(Player player, ItemStack stack, IPAZType mode) {
        this.player = player;
        this.stack = stack;
        this.mode = mode;
    }

    public Player getPlayer() {
        return player;
    }

    public IPAZType getMode() {
        return mode;
    }

    public ItemStack getStack() {
        return stack;
    }
}
