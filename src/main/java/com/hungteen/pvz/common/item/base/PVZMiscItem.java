package com.hungteen.pvz.common.item.base;

import com.hungteen.pvz.common.item.PVZItemTabs;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-08 22:37
 **/
public class PVZMiscItem extends Item {

    public PVZMiscItem() {
        super(new Properties().tab(PVZItemTabs.PVZ_MISC));
    }

    public PVZMiscItem(Properties p) {
        super(p.tab(PVZItemTabs.PVZ_MISC));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> textComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, world, textComponents, tooltipFlag);
    }
}
