package com.hungteen.pvz.common.container.provider;

import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-02-07 10:04
 **/
public abstract class PVZContainerProvider implements INamedContainerProvider {

    @Override
    public ITextComponent getDisplayName() {
        return StringUtil.EMPTY;
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory inventory, PlayerEntity player) {
        return null;
    }
}
