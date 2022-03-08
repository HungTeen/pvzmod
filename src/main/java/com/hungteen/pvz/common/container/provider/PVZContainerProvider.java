package com.hungteen.pvz.common.container.provider;

import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-02-07 10:04
 **/
public abstract class PVZContainerProvider implements INamedContainerProvider {

    @Override
    public Component getDisplayName() {
        return StringUtil.EMPTY;
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory inventory, Player player) {
        return null;
    }
}
