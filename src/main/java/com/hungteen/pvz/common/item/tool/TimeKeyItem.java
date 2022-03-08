package com.hungteen.pvz.common.item.tool;

import com.hungteen.pvz.common.item.PVZItemGroups;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-02-05 22:03
 **/
public class TimeKeyItem extends Item {

    public TimeKeyItem() {
        super(new Properties().tab(PVZItemGroups.PVZ_USEFUL));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> textComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, world, textComponents, tooltipFlag);
        textComponents.add(new TranslatableComponent("tooltip.pvz.wait_for_update").withStyle(ChatFormatting.DARK_RED));
    }
}
