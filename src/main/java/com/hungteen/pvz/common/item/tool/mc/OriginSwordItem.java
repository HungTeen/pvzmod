package com.hungteen.pvz.common.item.tool.mc;

import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.common.item.PVZItemTier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-02-03 10:08
 **/
public class OriginSwordItem extends SwordItem {

    public OriginSwordItem() {
        super(PVZItemTier.ORIGIN, 3, -2.4F, new Item.Properties().tab(PVZItemGroups.PVZ_USEFUL));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> textComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, world, textComponents, tooltipFlag);
        textComponents.add(new TranslatableComponent("tooltip.pvz.origin_sword").withStyle(ChatFormatting.DARK_GREEN));
    }
}
