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
 * @create: 2022-04-01 23:06
 **/
public class OriginSwordItem extends SwordItem {

    public OriginSwordItem(){
        super(PVZTiers.ORIGIN, 3, -2.4F, new Item.Properties().tab(PVZItemTabs.PVZ_USEFUL));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> textComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, level, textComponents, tooltipFlag);
        textComponents.add(new TranslatableComponent("tooltip.pvz.origin_sword").withStyle(ChatFormatting.DARK_GREEN));

    }
}
