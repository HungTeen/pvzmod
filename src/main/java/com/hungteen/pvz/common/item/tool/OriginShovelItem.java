package com.hungteen.pvz.common.item.tool;

import com.hungteen.pvz.common.item.PVZItemTabs;
import com.hungteen.pvz.common.item.PVZTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-01 23:10
 **/
public class OriginShovelItem extends ShovelItem {
    public OriginShovelItem() {
        super(PVZTiers.ORIGIN, 1.5F, -3.0F, new Item.Properties().tab(PVZItemTabs.PVZ_USEFUL));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, level, components, tooltipFlag);
        components.add(new TranslatableComponent("tooltip.pvz.origin_shovel").withStyle(ChatFormatting.DARK_GREEN));

    }
}
