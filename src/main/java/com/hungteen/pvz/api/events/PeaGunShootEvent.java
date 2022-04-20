package com.hungteen.pvz.api.events;

import com.hungteen.pvz.api.types.IPlantType;
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
    private final String mode;

    public PeaGunShootEvent(Player player, ItemStack stack, String mode) {
        this.player = player;
        this.stack = stack;
        this.mode = mode;
    }

    public Player getPlayer() {
        return player;
    }

    public String getMode() {
        return mode;
    }

    public ItemStack getStack() {
        return stack;
    }
}
